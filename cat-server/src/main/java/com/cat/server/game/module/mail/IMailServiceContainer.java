package com.cat.server.game.module.mail;

import java.util.Collection;
import java.util.Map;

import com.cat.server.admin.module.mail.BackstageMail;
import com.cat.server.game.helper.result.ErrorCode;
import com.cat.server.game.helper.result.ResultCodeData;
import com.cat.server.game.module.resource.domain.ResourceGroup;

/**
 * 邮件通用容器接口
 * @author Jeremy
 */
public interface IMailServiceContainer {
	
	/**
	 * 邮件类型
	 * @return
	 */
	public int mailType();

	/**
	 * 发送一封后台邮件
	 * @param playerId 玩家id
	 * @param title 标题
	 * @param content 内容
	 * @param expiredDays 过期天数
	 * @param rewards 奖励
	 */
	public ResultCodeData<Long> sendMail(BackstageMail backstageMail);
	
	/**
	 * 发送一封邮件, 带邮件模板
	 * @param playerId 玩家id
	 * @param title 标题
	 * @param content 内容
	 * @param expiredDays 过期天数
	 * @param rewards 奖励
	 */
	public ResultCodeData<Long> sendMail(long playerId, int configID, ResourceGroup rewards, Object... args);
	/**
	 * 发送一封带文本, 奖励的邮件<br>
	 * 只要玩家存在, 不管玩家是否在线, 都应该发送成功<br>
	 * @param playerId 玩家id
	 * @param title 标题
	 * @param content 内容
	 * @param expiredDays 过期天数
	 * @param rewards 奖励
	 * @return ResultCodeData<Long> 发送成功后, 携带邮件id
	 */
	public ResultCodeData<Long> sendMail(long playerId, String title, String content, int expiredDays, ResourceGroup rewards);
	
	/**
	 * 根据邮件id删掉一封邮件, 真正意义上的删除, 从数据库删除
	 * @param mailId 邮件唯一id
	 * @param playerId 玩家唯一id
	 */
	public ErrorCode deleteMail(long mailId, long playerId);
	
	/**
	 * 根据邮件id修改一封邮件
	 * @param mailId 邮件唯一id
	 * @param playerId 玩家id
	 * @param title 标题
	 * @param content 内容
	 * @param expiredDays 过期天数
	 * @param rewards 奖励
	 */
	public ErrorCode updateMail(long mailId, long playerId, String title, String content, int expiredDays, ResourceGroup rewards);
	
	/**
	 * 获取邮件
	 * @param mailId 邮件唯一id
	 * @param playerId 玩家唯一id
	 */
	public IMail getMail(long mailId, long playerId);
	
	/**
	 * 返回所有邮件
	 * @param playerId 玩家唯一id
	 * @return 邮件列表
	 */
	public Collection<? extends IMail> getMails(long playerId);

}
