package com.cat.server.game.module.function;

import com.cat.server.core.config.ConfigManager;
import com.cat.server.core.context.SpringContextHolder;
import com.cat.server.core.event.GameEventBus;
import com.cat.server.core.server.IModuleService;
import com.cat.server.game.data.config.local.ConfigFunction;
import com.cat.server.game.module.function.event.FunctionCheckReddotEvent;
import com.cat.server.game.module.function.time.point.IResetTimePoint;
import com.cat.server.utils.TimeUtil;

/**
 * 用于处理功能重置
 * @auth Jeremy
 * @date 2022年3月28日下午9:38:24
 */
public interface IFunctionResetService extends IModuleService{
	
	/**
	 * 发送检测红点事件
	 * @param playerId  
	 * @return void  
	 * @date 2022年8月13日上午9:52:43
	 */
	default public void checkReddot(long playerId) {
		GameEventBus.getInstance().post(playerId, FunctionCheckReddotEvent.create(playerId, this.getModuleId()));
	}
	
	/**
	 * 检测功能重置
	 * @param playerId
	 * @return
	 */
	default public boolean checkModuleOpen(long playerId) {
		IFunctionService service = SpringContextHolder.getBean(IFunctionService.class);
		return service.checkOpen(playerId, this.getModuleId());
	}
	
	/**
	 * 检查重置, 根据功能控制表内的时间DSL,检测是否重置, 如果重置则调用doReset方法
	 * @param playerId 玩家id
	 * @param now 当前时间
	 */
	default public boolean checkAndReset(long playerId, long now) {
		int functionId = this.getModuleId();
		ConfigFunction config = ConfigManager.getInstance().getConfig(ConfigFunction.class, functionId);
		if (config == null) {
			return true;
		}
		IResetTimePoint timePoint = config.getResetTimePoint();
		if (timePoint == null) {
			return true;
		}
		long resetTime = timePoint.getResetTimePoint(now);
		//if (this.getLastResetTime(playerId) != resetTime && resetTime!= IResetTimePoint.RESET_FREE) {
		if (resetTime!= IResetTimePoint.RESET_FREE 
				&& TimeUtil.isSameDay(this.getLastResetTime(playerId), resetTime)) {
			//判断最近一个重置时间的时间点,如果时间不一样, 则重置
			this.doReset(playerId, now);
			this.setLastResetTime(playerId, resetTime);
			return true;
		}
		return false;
	}
	
	/**
	 * 获取重置时间
	 * @return long  
	 * @date 2022年3月28日下午9:42:11
	 */
	default public long getLastResetTime(long playerId) {
		IFunctionService service = SpringContextHolder.getBean(IFunctionService.class);
		if (service == null) {
			return 0L;
		}
		return service.getLastResetTime(playerId, this.getModuleId());
	}
	
	/**
	 * 设置重置时间
	 * @return long  
	 * @date 2022年3月28日下午9:42:11
	 */
	default void setLastResetTime(long playerId, long now) {
		IFunctionService service = SpringContextHolder.getBean(IFunctionService.class);
		if (service == null) {
			return;
		}
		service.setLastResetTime(playerId, this.getModuleId(), now);
	}
	
	/**
	 * 用于处理重置细节操作,每个模块重置业务不一样
	 * @param playerId 玩家id
	 * @param now 时间戳
	 */
	default void doReset(long playerId, long now) {}
	
}
