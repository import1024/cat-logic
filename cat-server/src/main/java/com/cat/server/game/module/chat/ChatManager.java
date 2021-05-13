package com.cat.server.game.module.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.cat.server.core.server.IModuleManager;
import com.cat.server.game.module.chat.domain.ChatDomain;
import com.cat.server.game.module.chat.domain.ChatRule;

@Component
class ChatManager implements IModuleManager<Integer, ChatDomain>{
	
	/**域缓存*/
	protected final Map<Integer, ChatDomain> domains = new ConcurrentHashMap<>();
	
	/**
	 * key: playerId
	 * value: ChatRule聊天约束
	 */
	private transient Map<Long, Map<Integer, ChatRule>> playerRuleMap = new ConcurrentHashMap<>();
		
	/**
	 * 获取数据, 获取不到从数据库获取
	 */
	public ChatDomain getDomain(Integer id) {
		ChatDomain domain = domains.get(id);
		if (domain == null) {
			domain = new ChatDomain(id);
			domains.put(id, domain);
		}
		return domain;
	}

	@Override
	public void remove(Integer id) {
		domains.remove(id);
	}
	
	/**
	 * 获取玩家指定渠道的渠道规则
	 * @param playerId
	 * @param channelType
	 * @return  
	 * @return ChatRule  
	 * @date 2020年12月15日下午11:50:44
	 */
	public ChatRule getChatRule(long playerId, int channelType) {
		Map<Integer, ChatRule> chatRuleMap = playerRuleMap.get(playerId);
		if (chatRuleMap == null) {
			chatRuleMap  = new HashMap<>();
			playerRuleMap.put(playerId, chatRuleMap);
		}
		ChatRule rule = chatRuleMap.get(channelType);
		if (rule == null) {
			rule = ChatRule.create();
			chatRuleMap.put(channelType, rule);
		}
		return rule;
	}
	
}