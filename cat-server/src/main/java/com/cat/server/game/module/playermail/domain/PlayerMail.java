package com.cat.server.game.module.playermail.domain;

import java.util.HashMap;
import java.util.Map;

import com.cat.orm.core.annotation.Column;
import com.cat.orm.core.annotation.PO;
import com.cat.server.core.server.IPersistence;
import com.cat.server.game.data.proto.PBMail;
import com.cat.server.game.module.item.proto.PBRewardInfoBuilder;
import com.cat.server.game.module.playermail.PlayerMailConstant;
import com.cat.server.game.module.playermail.proto.PBMailInfoBuilder;
import com.cat.server.utils.TimeUtil;

/**
* @author Jeremy
*/
@PO(name = "player_mail")
public class PlayerMail extends PlayerMailPo implements IPersistence{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2942618617969545324L;
	
	@Column(PROP_REWARDS)
	private Map<Integer, Integer> rewardMap = new HashMap<>();

	public PlayerMail() {
		
	}
	
	public PlayerMail(long playerId) {
		this.playerId = playerId;
	}
	
	@Override
	public Object key() {
		return getPlayerId();
	}

	@Override
	public String keyColumn() {
		return PROP_PLAYERID;
	}
	
	public Map<Integer, Integer> getRewardMap() {
		return rewardMap;
	}

	public void setRewardMap(Map<Integer, Integer> rewardMap) {
		this.rewardMap = rewardMap;
	}

	/**
	 * 创建邮件对象
	 * @param playerId 玩家id
	 * @param title 标题
	 * @param content 内容
	 * @param expiredDays 过期天数
	 * @param rewards 奖励内容
	 * @return 邮件对象
	 */
	public static PlayerMail create(long playerId, String title, String content, int expiredDays, Map<Integer, Integer> rewards) {
		PlayerMail mail = new PlayerMail(playerId);
		mail.setTitle(title);
		mail.setContent(content);
		long now = TimeUtil.now();
		mail.setCreateTime(now);
		//计算过期时间
		long expireTime = now + TimeUtil.DAY_MILLISECONDS * expiredDays;
		mail.setExpireTime(expireTime);
		mail.save();
		return mail;
	}
	
	/**
	 * 实体对象转协议对象
	 * @return 邮件序列化成消息对象
	 */
	public PBMail.PBMailInfo toProto() {
		PBMailInfoBuilder builder = PBMailInfoBuilder.newInstance();
		builder.setMailId(this.getId());
		builder.setState(this.getState());
		builder.setContent(this.getContent());
		builder.setTitle(this.getTitle());
		//builder.setDate();
//		builder.addRewards(this.rewardMap.to);
		return builder.build();
	}
	
	/**
	 * 是否已经领取
	 * @return
	 */
	public boolean isRewarded() {
		if (this.getState() == PlayerMailConstant.REWARD) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否过期
	 * @return
	 */
	public boolean isExpired() {
		if ((TimeUtil.now() / 1000) >= this.getExpireTime()) {//截止时间大于当前时间,表示过期
			return true;
		}
		return false;
	}
	
	/**
	 * 是否可以删除
	 * @return
	 */
	public boolean canDel() {
		if (this.getState() == PlayerMailConstant.READ && this.getRewardMap().isEmpty()) {
			//状态为已读,并且没有奖励配置 可以删除
			return true;
		}else if (this.isRewarded()) {
			//领过奖,可以删除
			return true;
		}else if (this.isExpired()) {
			//过期可以删除
			return true;
		}
		return false;
	}
	
}
