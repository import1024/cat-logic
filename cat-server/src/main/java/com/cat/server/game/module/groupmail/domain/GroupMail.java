package com.cat.server.game.module.groupmail.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cat.orm.core.annotation.Column;
import com.cat.orm.core.annotation.PO;
import com.cat.orm.util.StateUtils;
import com.cat.server.core.context.SpringContextHolder;
import com.cat.server.core.server.IPersistence;
import com.cat.server.game.helper.uuid.SnowflakeGenerator;
import com.cat.server.game.module.mail.IMail;
import com.cat.server.game.module.mail.assist.MailState;
import com.cat.server.game.module.resource.domain.ResourceGroup;
import com.cat.server.utils.TimeUtil;

/**
 * @author Jeremy
 */
@PO(name = "group_mail")
public class GroupMail extends GroupMailPo implements IPersistence, IMail {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3215406404369917418L;

	@Column(value = PROP_REWARDS)
	private ResourceGroup resourceGroup = new ResourceGroup();

	/**
	 * 邮件状态信息<br>
	 * key: 玩家id<br>
	 * value: 邮件状态<br>
	 * 1: 未读取<br>
	 * 2: 已读取<br>
	 * 4: 已领奖
	 */
	@Column(value = PROP_EXTENDSTR)
	private Map<Long, Integer> stateMap = new ConcurrentHashMap<>();
	
	@Column(value = PROP_SERVERIDSTR)
	private List<Integer> serverIds = new ArrayList<>();

	public GroupMail() {

	}

	public ResourceGroup getRewardMap() {
		return resourceGroup;
	}

	Map<Long, Integer> getStateMap() {
		return stateMap;
	}
	
	public List<Integer> getServerIds() {
		return serverIds;
	}
	
	void setServerIds(List<Integer> serverIds){
		this.serverIds = serverIds;
	}

	/**
	 * 创建邮件对象
	 * 
	 * @param playerId 玩家id
	 * @param title 标题
	 * @param content 内容
	 * @param expiredDays 过期天数
	 * @param rewards 奖励内容
	 * @return 邮件对象
	 */
	public static GroupMail create(String title, String content, int expiredDays, long backstageId, ResourceGroup rewards, List<Integer> serverIds) {
		SnowflakeGenerator generator = SpringContextHolder.getBean(SnowflakeGenerator.class);
		GroupMail mail = new GroupMail();
		// 邮件id唯一,使用雪花生成器生成
		mail.setId(generator.nextId());
		mail.setTitle(title);
		mail.setContent(content);
		mail.setBackstageId(backstageId);
		mail.setServerIds(serverIds);
		long now = TimeUtil.now();
		mail.setCreateTime(now);
		// 计算过期时间
		long expireTime = now + TimeUtil.DAY_MILLISECONDS * expiredDays;
		mail.setExpireTime(expireTime);
		mail.save();
		return mail;
	}

	/**
	 * 根据玩家获取当前群邮件状态
	 * @param playerId 玩家id
	 */
	@Override
	public int getState(long playerId) {
		return stateMap.getOrDefault(playerId, MailState.NONE.getState());
	}

	/**
	 * 添加状态
	 * @param playerId 玩家id
	 * @param mailState 状态
	 */
	@Override
	public void addState(long playerId, MailState state) {
		int oldState = stateMap.getOrDefault(playerId, MailState.NONE.getState());
		int newState = (int) StateUtils.addState(oldState, state.getState());
		stateMap.put(playerId, newState);
		this.update();
	}

	@Override
	public void deleteMail(long playerId) {
		int state = stateMap.getOrDefault(playerId, MailState.NONE.getState());
		state = (int) StateUtils.addState(state, MailState.DELETE.getState());
		stateMap.put(playerId, state);
		this.update();
	}

}
