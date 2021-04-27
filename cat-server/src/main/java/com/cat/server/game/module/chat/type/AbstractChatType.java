package com.cat.server.game.module.chat.type;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cat.orm.core.db.process.DataProcessorAsyn;
import com.cat.server.core.context.SpringContextHolder;
import com.cat.server.game.helper.result.ErrorCode;
import com.cat.server.game.module.chat.domain.Chat;
import com.cat.server.game.module.chat.domain.ChatDetail;
import com.cat.server.game.module.player.domain.Player;
import com.cat.server.game.module.player.domain.PlayerContext;
import com.cat.server.game.module.player.service.PlayerService;
import com.cat.server.utils.Pair;
import com.google.common.cache.Cache;

/**
 *	 聊天抽象处理类型
 * @author Jeremy
 */
public abstract class AbstractChatType implements IChatType{
	
	private static Logger logger = LoggerFactory.getLogger(AbstractChatType.class); 
	
	protected PlayerService playerService;
	
	protected int channel;
	
//	/**
//	 * 聊天缓存, 所有频道聊天缓存, 使用Cache替代掉Map
//	 * key:BigInteger, uniqueId, 唯一id
//	 * value:聊天记录实体bean
//	 */
//	private Cache<BigInteger, Chat> chatRecordMap = CacheBuilder.newBuilder()
//			.expireAfterAccess(1, TimeUnit.HOURS)// 在给定时间内没有被读/写访问,则清除
//			.maximumSize(1000)//	最大条目,超过这个聊天记录, 根据LRU特点移除
//			.removalListener((notification)->{//移除监听,因超时被移除的聊天数据,持久化到数据库
//				Chat bean = (Chat)notification.getValue();
//				bean.delete();
//			}).build();
	
	/**
	 * TODO 具体的聊天记录, 由子类去根据需要实例化
	 * @return
	 */
	public abstract Cache<BigInteger, Chat> getChatMap();
	
	/**
	 * 根据uniqueId获取聊天内容
	 * @param uniqueId
	 * @return
	 */
	public Chat getChat(BigInteger uniqueId) {
		Chat chat = getChatMap().getIfPresent(uniqueId);
		if (chat != null) {
			return chat;
		}
		Pair<Long, Long> pair = getOriginalIds(uniqueId);
		chat = getFromDb(pair.getLeft(), pair.getRight());
		getChatMap().put(uniqueId, chat);
		return chat;
	}
	
	public AbstractChatType(int channel) {
		this.channel = channel;
	}
	
	@Override
	public int getChannel() {
		return channel;
	}
	
	/**
	 * 聊天校验
	 * @param senderId
	 * @return
	 */
	public ErrorCode checkChat(long senderId, BigInteger uniqueId) {
		PlayerContext sender = playerService.getPlayerContext(senderId);
		
		if (sender == null) {
			return ErrorCode.PLAYER_NOT_EXISTS;
		}
		//TODO 屏蔽验证,,因为后台暂时没有这个需求,没有此配置
//		ChatLimitConfig config = ConfigManager.getInstance().getConfig(ChatLimitConfig.class, getChannel());
//		if (config == null) {//后台聊天配置
//			return ErrorCode.CONFIG_NOT_EXISTS;
//		} 
//		if (sender.getLevel() < config.getLevelLimit()) {
//			return ErrorCode.CHAT_CONDITION_LEVEL_NOT_REACH;
//		} 
//		if (sender.getVipLevel() < config.getVipLimit()) {
//			return ErrorCode.CHAT_CONDITION_VIP_NOT_REACH;
//		}
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 获取原始的id
	 * @return
	 */
	public Pair<Long, Long> getOriginalIds(BigInteger uniqueId) {
		BigInteger left = uniqueId.shiftRight(64);
		long leftPlayerId = left.longValue();
		long rightPlayerId = left.shiftLeft(64).xor(uniqueId).longValue();
		return Pair.of(leftPlayerId, rightPlayerId);
	}
	
	@Override
	public ErrorCode sendMsg(ChatDetail chatDetail) {
		if (chatDetail == null) {
			return ErrorCode.CHAT_MESSAGE_IS_EMPTY;
		}
		final long senderId = chatDetail.getSenderId();
		final long targetId = chatDetail.getTargetId();
		final BigInteger uniqueId = getUniqueId(senderId, targetId);
		ErrorCode errorCode = checkChat(senderId, uniqueId);
		if (!errorCode.isSuccess()) {
			return errorCode;
		}
		Chat chat = getChat(uniqueId);
		chat.addChatDetail(chatDetail);
		
//		ChatInfoDto proto = chatDetail.toProto();
		// FSC 新增的聊天消息即时同步, 聊天, 如果需要更新状态,可以独立一条消息
//		ResChat res = new ResChat();
//		res.setChatInfo(proto);
//		res.setChannelType(getChannel());
		Collection<Long> receiverIds = findReceiverIds(uniqueId);
		receiverIds.forEach(playerId -> {
			//Player receiver = PlayerManager.getInstance().getOnlinePlayer(playerId);
			PlayerContext receiver = playerService.getPlayerContext(senderId);
			if (receiver !=null && receiver.isOnline()) {//	在线则同步,否则不同步
//				receiver.sendMessage(res);
			}
		});
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 获取所有聊天记录
	 * @return
	 */
	public List<ChatDetail> getAllChatRecord(Player sender, long targetId) {
		List<ChatDetail> ret = new ArrayList<>();
		BigInteger uniqueId = getUniqueId(sender.getPlayerId(), targetId);
		Chat chat = getChat(uniqueId);
        if (chat == null) {
			return new ArrayList<>();
		}
        Iterator<ChatDetail> iter = chat.getChatDetails().iterator();
        while (iter.hasNext()) {
        	ret.add(iter.next());
		}
        return ret;
	}
	
	/**
	 * 获取最新的一条聊天记录
	 * @param sender
	 * @param targetId
	 * @return
	 */
	public ChatDetail getLastChatRecord(Player sender, long targetId) {
		BigInteger uniqueId = getUniqueId(sender.getPlayerId(), targetId);
		Chat chat = getChat(uniqueId);
        return chat == null ? null : chat.getLastChatDetail();
	}
	
	/**
	 * 获取指定数量的聊天记录, 客户端主动拉取.
	 * @param sender
	 * @param targetId
	 * @return
	 */
	public List<ChatDetail> getChatRecord(Player sender, long targetId, int num) {
		final long senderId = sender.getPlayerId();
		BigInteger uniqueId = getUniqueId(senderId, targetId);
		Chat chat = getChat(uniqueId);
        if (chat == null) {
			return new ArrayList<>();
		}
        return chat.getChatDetail(num);
	}
	
	public Chat getFromDb(long leftKey, long rightKey) {
		try {
			DataProcessorAsyn process = SpringContextHolder.getBean(DataProcessorAsyn.class);
//			String[] cols = new String[] {Chat.PROP_LEFTKEY, Chat.PROP_RIGHTKEY};
			Chat chat = process.selectOneByIndex(Chat.class, new Object[] {leftKey, rightKey});
			return chat;
		}catch (Exception e) {
			logger.error("getFromDb error", e);
		}
		return null;
	}

}
