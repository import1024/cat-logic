package com.cat.server.game.module.family;

import com.cat.server.core.server.IModuleManager;
import com.cat.server.game.helper.ModuleDefine;
import com.cat.server.game.helper.result.ErrorCode;
import com.cat.server.game.helper.result.ResultCodeData;
import com.cat.server.game.module.family.assist.FamilyPrivilege;
import com.cat.server.game.module.family.domain.Family;
import com.cat.server.game.module.family.domain.PlayerFamily;
import com.cat.server.game.module.family.domain.PlayerFamilyDomain;
import com.cat.server.game.module.player.IPlayerService;
import com.cat.server.game.module.resource.IResourceGroupService;
import com.cat.server.game.module.resource.domain.ResourceGroup;
import com.cat.server.utils.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;


/**
 * PlayerFamily控制器
 */
@Service
public class PlayerFamilyService implements IPlayerFamilyService {
	
	private static final Logger log = LoggerFactory.getLogger(PlayerFamilyService.class);
	
	@Autowired private IPlayerService playerService;
	
	@Autowired private IResourceGroupService resourceGroupService;
	
	@Autowired private FamilyService familyService;
	
	@Autowired private PlayerFamilyManager playerFamilyManager;
	
	/**
	 * 登陆
	 */
	public void onLogin(long playerId) {
//		PlayerFamilyDomain domain = playerFamilyManager.getDomain(playerId);
//		Collection<PlayerFamily> beans = domain.getBeans();
		//FSC todo somthing...
		//Codes for proto
		//playerService.sendMessage(playerId, ack);
	}
	
	/**
	 * 当玩家离线,移除掉道具模块数据
	 * @param playerId
	 */
	public void onLogout(long playerId) {
		playerFamilyManager.remove(playerId);
	}
	
	/**
	 * 更新信息
	 */
	public void responsePlayerFamilyInfo(long playerId, Family family) {
		try {
//			for (PlayerFamily playerFamily : beans) {
//				resp.addArtifactlist(playerFamily.toProto());
//			}
			//playerService.sendMessage(playerId, resp);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("responsePlayerFamilyInfo error, familyId:{}", family.getId());
			log.error("responsePlayerFamilyInfo error, e:", e);
		}
	}
	
	/////////////业务逻辑//////////////////
	
	/**
	 * 创建家族
	 * @param playerId 创建者id
	 * @param name 家族名称
	 * @return void  
	 * @date 2021年5月10日下午11:19:24
	 */
	public ErrorCode createFamily(long playerId, String name) {
		if (StringUtils.isBlank(name)) {
			return ErrorCode.CONTENT_INVALID_NAME;
		}
		//TODO 敏感字判断,特殊字符,长度判断
		//TODO 判断消耗
		ResourceGroup costMap = new ResourceGroup();
		if (!resourceGroupService.checkEnought(playerId, costMap)) {
			return ErrorCode.AMOUNT_NOT_ENOUGH;
		}
		// 判断重复进入家族
		long familyId = familyService.getPlayerFamilyId(playerId);
		if (familyId > 0) {
			return ErrorCode.FAMILY_ALREADY_IN_FAMILY;
		}
		try {
			ResultCodeData<Family> resultCode = familyService.createFamily(playerId, name);
			if (!resultCode.isSuccess()){
				return resultCode.getErrorCode();
			}
			Family family = resultCode.getData();
			//	下发家族信息
			this.responsePlayerFamilyInfo(playerId, family);
			//	返回创建家族成功信息
		} catch (Exception e) {
			e.printStackTrace();
			log.error("createFamily error, playerId:{}, name:{}, e:{}", playerId, name, e);
		}
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 修改家族名
	 * @param playerId 修改者id
	 * @param name 修改的新名字
	 * @return void  
	 * @date 2021年5月10日下午11:19:24
	 */
	public ErrorCode editFamilyName(long playerId, String name) {
		if (StringUtils.isBlank(name)) {
			return ErrorCode.CONTENT_INVALID_NAME;
		}
		//TODO 敏感字判断,特殊字符,长度判断
		PlayerFamilyDomain domain = playerFamilyManager.getDomain(playerId);
		if (domain == null) {
			return ErrorCode.DOMAIN_IS_NULL;
		}
		final long familyId = familyService.getPlayerFamilyId(playerId);
		if (familyId == 0) {
			return ErrorCode.FAMILY_NO_FAMILY;
		}
		//	权限校验
		if (!familyService.hasPrivilege(playerId, FamilyPrivilege.RENAME)) {
			return ErrorCode.FAMILY_NO_PRIVILEGE;
		}
		try {
			familyService.editFamilyName(playerId, name);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("editFamilyName error");
		}
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 查询家族
	 * @param playerId 查询的玩家id
	 * @param keyword 查询关键字
	 * @return void  
	 * @date 2021年5月10日下午11:19:24
	 */
	public ErrorCode searchFamily(long playerId, String keyword) {
		Collection<Family> familyIds = familyService.searchFamily(keyword);
		//	TODO 组装家族信息
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 申请进入家族
	 * @param playerId 申请的玩家id
	 * @param familyId 家族id
	 * @return void  
	 * @date 2021年5月10日下午11:19:24
	 */
	public ErrorCode applyJoinFamily(long playerId, long familyId) {
		PlayerFamilyDomain domain = playerFamilyManager.getDomain(playerId);
		if (domain == null) {
			return ErrorCode.DOMAIN_IS_NULL;
		}
		//	是否满足再次进入加入的时间条件
		if (!domain.isMeetTime()) {
			return ErrorCode.FAMILY_JOIN_TIME_LIMIT;
		}
		try {
			familyService.applyJoinFamily(playerId, familyId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("applyJoinFamily error");
		}
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 退出家族, 自己意愿退出家族, 要有惩罚
	 * @param playerId
	 * @return void  
	 * @date 2021年5月10日下午11:19:24
	 */
	public ErrorCode exitFamily(long playerId) {
		PlayerFamilyDomain domain = playerFamilyManager.getDomain(playerId);
		if (domain == null) {
			return ErrorCode.DOMAIN_IS_NULL;
		}
		try {
			ErrorCode errorCode = familyService.exitFamily(playerId);
			//退出失败
			if (!errorCode.isSuccess()) {
				return errorCode;
			}
			//	退出成功
			PlayerFamily playerFamily =  domain.getBean();
			playerFamily.setLeaveTime(TimeUtil.now());
			//	威望扣除1/5
			int oldContribute = playerFamily.getContribute();
			playerFamily.setContribute(oldContribute/5);
			//	记录日志
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exitFamily error");
		}
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 查看家族申请列表
	 * @param playerId
	 * @param name  
	 * @return void  
	 * @date 2021年5月10日下午11:19:24
	 */
	public ErrorCode showFamilyApplyInfo(long playerId, String name) {
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 管理家族,任命
	 * @param playerId
	 * @param name  
	 * @return void  
	 * @date 2021年5月10日下午11:19:24
	 */
	public ErrorCode managerFamily(long playerId, String name) {
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 踢出家族
	 * @param playerId
	 * @return void  
	 * @date 2021年5月10日下午11:19:24
	 */
	public ErrorCode fire(long playerId, long firePlayerId) {
		try {
			ErrorCode errorCode = familyService.fire(playerId, firePlayerId);
			//	TODO 组装结果消息
		} catch (Exception e) {
			e.printStackTrace();
			log.error("fire error");
		}
		return ErrorCode.SUCCESS;
	}
	
	/////////////接口方法////////////////////////
	

	@Override
	public IModuleManager<Long, ?> getModuleManager() {
		return playerFamilyManager;
	}

	@Override
	public int getModuleId() {
		return ModuleDefine.FAMILY.getModuleId();
	}
	
//	@Override
//	public int checkReddot(long playerId) {
//		// TODO Auto-generated method stub
//		return IPlayerFamilyService.super.checkReddot(playerId);
//	}
	
}