package com.cat.server.game.module.activityoperation.learncommunity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.server.core.event.PlayerBaseEvent;
import com.cat.server.game.data.proto.PBLearnCommunity.ReqLearnCommunityInfo;
import com.cat.server.game.data.proto.PBLearnCommunity.ReqLearnCommunityReward;
import com.cat.server.game.helper.result.ErrorCode;
import com.cat.server.game.module.activity.event.ActivityStatusUpdateEvent;
import com.cat.server.game.module.activity.status.IActivityStatus;
import com.cat.server.game.module.activity.type.ActivityTypeEnum;
import com.cat.server.game.module.activity.type.IActivityType;
import com.cat.server.game.module.activity.type.IPlayerActivityService;
import com.cat.server.game.module.activityoperation.learncommunity.domain.LearnCommunity;
import com.cat.server.game.module.activityoperation.learncommunity.domain.LearnCommunityDomain;
import com.cat.server.game.module.activityoperation.learncommunity.proto.RespLearnCommunityInfoBuilder;
import com.cat.server.game.module.activityoperation.learncommunity.proto.RespLearnCommunityRewardBuilder;
import com.cat.server.game.module.player.IPlayerService;


/**
 * LearnCommunity控制器
 * @author Jeremy
 */
@Service
public class LearnCommunityService implements ILearnCommunityService, IPlayerActivityService{
	
	private static final Logger log = LoggerFactory.getLogger(LearnCommunityService.class);
	
	@Autowired private IPlayerService playerService;
	
	@Autowired private LearnCommunityManager manager;
	
	/**
	 * 登陆
	 */
	public void onLogin(long playerId) {
		LearnCommunityDomain domain = manager.loadDomain(playerId);
		if (domain == null) {
			log.info("LearnCommunityService error, domain is null");
			return;
		}
		//检测活动结束
		IActivityType activityType = getActivityType();
		int activityId = activityType.getActivity().getConfigId();
		if (activityId != domain.getBean().getActivityId()) {
			//玩家保存的活动id跟当前活动id不一致, 表示活动已经结束,处理活动结束
			onActivityClose(domain);
			return;
		}
		//检测每日重置
		domain.checkAndReset();
		//下发最新的研习社至客户端
		this.responseLearnCommunityInfo(domain);
	}
	
	/**
	 * 当玩家离线,移除掉道具模块数据
	 * @param playerId
	 */
	public void onLogout(long playerId) {
		manager.remove(playerId);
	}
	
	/**
	 * 当活动状态变化
	 * @param event
	 */
	public void onEvent(PlayerBaseEvent event){
		long playerId = event.getPlayerId();
		LearnCommunityDomain domain = manager.getDomain(playerId);
		if (domain == null) {
			log.info("onEvent error, playerId:{}", playerId);
			return;
		}
		domain.onProcess(event);
	}
	
	/**
	 * 当活动状态变化
	 * @param event
	 */
	public void onActivityStatusUpdate(ActivityStatusUpdateEvent event){
		LearnCommunityDomain domain = manager.getDomain(event.getPlayerId());
		if (domain == null) {
			return;
		}
		if (event.getStatus() == IActivityStatus.CLOSE) {
			//研习社活动结束, 计算奖励, 发送邮件
			onActivityClose(domain);
		}
	}
	
	/**
	 * 当活动结束
	 * //1. 发送邮件领取未领取的奖励
	 * //2. 清除当前研习社所有数据,保存
	 */
	public void onActivityClose(LearnCommunityDomain domain) {
		//邮件通知
		//清理数据
		domain.clear();
	}
  
	/**
	 * 更新信息
	 */
	public void responseLearnCommunityInfo(LearnCommunityDomain domain) {
		LearnCommunity bean = domain.getBean();
		try {
			RespLearnCommunityInfoBuilder resp = RespLearnCommunityInfoBuilder.newInstance();
			resp.setLevel(bean.getLevel());
			resp.setExp(bean.getExp());
			resp.setExclusive(bean.getExclusive());
			
			playerService.sendMessage(domain.getId(), resp);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("responseLearnCommunityInfo error, playerId:{}", domain.getId());
			log.error("responseLearnCommunityInfo error, e:", e);
		}
	}
	
	/////////////业务逻辑//////////////////
	
	/**
	* 请求研习社信息	
	* @param long playerId
	* @param ReqLearnCommunityInfo req
	* @param RespLearnCommunityInfoResp ack
	*/
	public ErrorCode reqLearnCommunityInfo(long playerId, ReqLearnCommunityInfo req, RespLearnCommunityInfoBuilder ack){
		try {
			LearnCommunityDomain domain = manager.getDomain(playerId);
			if (domain == null) {
				return ErrorCode.DOMAIN_IS_NULL;
			}
			ErrorCode errorCode = isInCycle();
			if (!errorCode.isSuccess()) {
				return errorCode;
			}
			this.responseLearnCommunityInfo(domain);
			return ErrorCode.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("reqLearnCommunityInfo error, playerId:{}", playerId);
			log.error("reqLearnCommunityInfo error, e:", e);
			return ErrorCode.UNKNOWN_ERROR;
		}
	}
	/**
	* 请求领取研习社奖励
	* @param long playerId
	* @param ReqLearnCommunityReward req
	* @param RespLearnCommunityRewardResp ack
	*/
	public ErrorCode reqLearnCommunityReward(long playerId, ReqLearnCommunityReward req, RespLearnCommunityRewardBuilder ack){
		try {
			LearnCommunityDomain domain = manager.getDomain(playerId);
			if (domain == null) {
				return ErrorCode.DOMAIN_IS_NULL;
			}
			ErrorCode errorCode = isInCycle();
			if (!errorCode.isSuccess()) {
				return errorCode;
			}
			//TODO Somthing.
			this.responseLearnCommunityInfo(domain);
			return ErrorCode.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("reqLearnCommunityReward error, playerId:{}", playerId);
			log.error("reqLearnCommunityReward error, e:", e);
			return ErrorCode.UNKNOWN_ERROR;
		}
	}

	@Override
	public int activityType() {
		return ActivityTypeEnum.LEARN_COMMUNITY.getValue();
	}
	
	/////////////接口方法////////////////////////
	
}
 
 
