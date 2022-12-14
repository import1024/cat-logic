package com.cat.server.game.module.equip;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.server.core.config.ConfigManager;
import com.cat.server.core.server.IModuleManager;
import com.cat.server.game.data.config.local.ConfigEquip;
import com.cat.server.game.data.config.local.ConfigEquipHole;
import com.cat.server.game.data.config.local.ConfigEquipStar;
import com.cat.server.game.data.proto.PBEquip.ReqEquipInfo;
import com.cat.server.game.data.proto.PBEquip.ReqEquipPunching;
import com.cat.server.game.data.proto.PBEquip.ReqEquipUpgrade;
import com.cat.server.game.data.proto.PBEquip.ReqTakeoutEquip;
import com.cat.server.game.data.proto.PBEquip.ReqWearEquip;
import com.cat.server.game.helper.ModuleDefine;
import com.cat.server.game.helper.ResourceType;
import com.cat.server.game.helper.log.NatureEnum;
import com.cat.server.game.helper.result.ErrorCode;
import com.cat.server.game.module.equip.domain.Equip;
import com.cat.server.game.module.equip.domain.EquipDomain;
import com.cat.server.game.module.equip.proto.RespEquipInfoBuilder;
import com.cat.server.game.module.equip.proto.RespEquipPunchingBuilder;
import com.cat.server.game.module.equip.proto.RespEquipUpdateBuilder;
import com.cat.server.game.module.equip.proto.RespEquipUpgradeBuilder;
import com.cat.server.game.module.equip.proto.RespTakeoutEquipBuilder;
import com.cat.server.game.module.equip.proto.RespWearEquipBuilder;
import com.cat.server.game.module.hero.IHeroService;
import com.cat.server.game.module.hero.domain.Hero;
import com.cat.server.game.module.hero.domain.HeroDomain;
import com.cat.server.game.module.item.proto.RespItemDeleteBuilder;
import com.cat.server.game.module.player.IPlayerService;
import com.cat.server.game.module.resource.IResource;
import com.cat.server.game.module.resource.IResourceGroupService;
import com.cat.server.game.module.resource.IResourceService;
import com.cat.server.utils.RandomUtil;
import com.google.common.collect.Lists;

/**
 * Equip?????????
 * @author Jeremy
 */
@Service
class EquipService implements IEquipService, IResourceService {
	
	private static final Logger log = LoggerFactory.getLogger(EquipService.class);
	
	@Autowired private IPlayerService playerService;
	
	@Autowired private EquipManager equipManager;
	
	@Autowired private IHeroService heroService;
	
	@Autowired private IResourceGroupService resourceGroupService;
	
	@Override
	public void responseModuleInfo(long playerId) {
		EquipDomain domain = equipManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		RespEquipInfoBuilder resp = RespEquipInfoBuilder.newInstance();
		/* ???????????????????????????????????????,????????????????????????, ???????????????????????????????????????????????? */
		Collection<Equip> beans = domain.getBeans();
		for (Equip equip : beans) {
			resp.addEquips(equip.toProto());
		}
		playerService.sendMessage(domain.getId(), resp);
	}
	
	// ?????????????????????????????????
	public void responseUpdateList(long playerId, Collection<Equip> updates) {
		// ????????????
		if (!updates.isEmpty()) {
			RespEquipUpdateBuilder ack = RespEquipUpdateBuilder.newInstance();
			updates.forEach((equip)->{
				ack.addEquips(equip.toProto());
				log.info("responseUpdateList equip:{}", equip);
			});
			playerService.sendMessage(playerId, ack);
		}
	}
	
	//?????????????????????????????????
	public void responseDeleteList(long playerId, Collection<Long> deletes){
		//????????????
		if (!deletes.isEmpty()) {
			RespItemDeleteBuilder ack = RespItemDeleteBuilder.newInstance();
			deletes.forEach((uniqueId)->{
				ack.addIds(uniqueId);
				log.info("equip delete...equip:{}", uniqueId);
			});
			playerService.sendMessage(playerId, ack);
		}
	}
	
	/////////////????????????//////////////////
	
	/**
	* ??????????????????
	* @param long playerId
	* @param ReqEquipInfo req
	* @param RespEquipInfoResp ack
	*/
	public ErrorCode reqEquipInfo(long playerId, ReqEquipInfo req, RespEquipInfoBuilder ack){
		try {
			EquipDomain domain = equipManager.getDomain(playerId);
			if (domain == null) {
				return ErrorCode.DOMAIN_IS_NULL;
			}
			this.responseModuleInfo(playerId);
			return ErrorCode.SUCCESS;
		} catch (Exception e) {
			log.error("reqEquipInfo error, playerId:{}", playerId, e);
			return ErrorCode.UNKNOWN_ERROR;
		}
	}
	
	/**
	* ??????????????????
	* @param long playerId
	* @param ReqWearEquip req
	* @param RespWearEquipResp ack
	*/
	public ErrorCode reqWearEquip(long playerId, ReqWearEquip req, RespWearEquipBuilder ack){
		try {
			EquipDomain domain = equipManager.getDomain(playerId);
			if (domain == null) {
				return ErrorCode.DOMAIN_IS_NULL;
			}
			final long holderId = req.getEquipId();
			final long equipId = req.getEquipId();
			Equip equip = this.getEquip(playerId, equipId);
			if (equip == null) {
				return ErrorCode.EQUIP_NOT_EXIST;
			}
			ConfigEquip configEquip = ConfigManager.getInstance().getConfig(ConfigEquip.class, equip.getConfigId());
			if (configEquip == null) {
				return ErrorCode.CONFIG_NOT_EXISTS;
			}
			if (heroService.checkExist(playerId, holderId)) {
				return ErrorCode.CONFIG_NOT_EXISTS;
			}
			domain.wear(holderId, configEquip.getCategory(), equip);
			this.responseUpdateList(playerId, domain.getAndClearUpdateList());
			return ErrorCode.SUCCESS;
		} catch (Exception e) {
			log.error("reqWearEquip error, playerId:{}", playerId, e);
			return ErrorCode.UNKNOWN_ERROR;
		}
	}
	
	/**
	* ??????????????????
	* @param long playerId
	* @param ReqTakeoutEquip req
	* @param RespTakeoutEquipResp ack
	*/
	public ErrorCode reqTakeoutEquip(long playerId, ReqTakeoutEquip req, RespTakeoutEquipBuilder ack){
		try {
			EquipDomain domain = equipManager.getDomain(playerId);
			if (domain == null) {
				return ErrorCode.DOMAIN_IS_NULL;
			}
			final long equipId = req.getEquipId();
			Equip equip = this.getEquip(playerId, equipId);
			if (equip == null) {
				return ErrorCode.EQUIP_NOT_EXIST;
			}
			if (equip.getHolder() == 0) {
				return ErrorCode.EQUIP_NOT_WEAR;
			}
			ConfigEquip configEquip = ConfigManager.getInstance().getConfig(ConfigEquip.class, equip.getConfigId());
			if (configEquip == null) {
				return ErrorCode.CONFIG_NOT_EXISTS;
			}
			domain.takeOut(equip.getHolder(), configEquip.getStack(), equip);
			this.responseUpdateList(playerId, domain.getAndClearUpdateList());
			return ErrorCode.SUCCESS;
		} catch (Exception e) {
			log.error("reqTakeoutEquip error, playerId:{}", playerId, e);
			return ErrorCode.UNKNOWN_ERROR;
		}
	}
	
	/**
	* ??????????????????
	* @param long playerId
	* @param ReqEquipUpgrade req
	* @param RespEquipUpgradeResp ack
	*/
	public ErrorCode reqEquipUpgrade(long playerId, ReqEquipUpgrade req, RespEquipUpgradeBuilder ack){
		try {
			EquipDomain domain = equipManager.getDomain(playerId);
			if (domain == null) {
				return ErrorCode.DOMAIN_IS_NULL;
			}
			//??????????????????
			final long equipId = req.getEquipId();
			Equip equip = this.getEquip(playerId, equipId);
			if (equip == null) {
				return ErrorCode.EQUIP_NOT_EXIST;
			}
			ConfigEquip configEquip = ConfigManager.getInstance().getConfig(ConfigEquip.class, equip.getConfigId());
			if (configEquip == null) {
				return ErrorCode.CONFIG_NOT_EXISTS;
			}
			//????????????
			final int star = equip.getStar();
			ConfigEquipStar configEquipStar = ConfigManager.getInstance().getConfig(ConfigEquipStar.class, star);
			if (configEquipStar == null) {
				return ErrorCode.CONFIG_NOT_EXISTS;
			}
			if (configEquipStar.getNextStar() == -1) {
				return ErrorCode.EQUIP_STAR_LIMIT;
			}
//			if (!resourceGroupService.checkAndCost(playerId, configEquipStar.getConsume(), NatureEnum.EquipStar)) {
//				return ErrorCode.AMOUNT_NOT_ENOUGH;
//			}
			//????????????
			final boolean hit = RandomUtil.isHit10000(configEquipStar.getRate());
			//????????????, ??????+1, ???????????????????????????
			final int nextStar = hit ? configEquipStar.getNextStar() : configEquipStar.getFallbackStar();
			//????????????, ??????????????????, ??????????????????
			final int addedHiddenLv = hit && RandomUtil.isHit10000(configEquipStar.getHiddenAttrRate()) ? 1 : 0;
			
			equip.setStar(nextStar);
			equip.setStarHiddenLevel(equip.getStarHiddenLevel() + addedHiddenLv);
			//????????????????????????
			equip.getAttrRootNode().getStarNode().setAttrChange();
			equip.getAttrRootNode().getStarHiddenNode().setAttrChange();
			
			//???????????????????????????????????????
			this.responseUpdateList(playerId, Lists.newArrayList(equip));
			//?????????????????????
			ack.setResult(hit);
			return ErrorCode.SUCCESS;
		} catch (Exception e) {
			log.error("reqEquipUpgrade error, playerId:{}", playerId, e);
			return ErrorCode.UNKNOWN_ERROR;
		}
	}
	
	/**
	* ??????????????????<br>
	* 1. ?????????????????????
	* @param long playerId
	* @param ReqEquipPunching req
	* @param RespEquipPunchingResp ack
	*/
	public ErrorCode reqEquipPunching(long playerId, ReqEquipPunching req, RespEquipPunchingBuilder ack){
		try {
			EquipDomain domain = equipManager.getDomain(playerId);
			if (domain == null) {
				return ErrorCode.DOMAIN_IS_NULL;
			}
			final long equipId = req.getEquipId();
			Equip equip = this.getEquip(playerId, equipId);
			if (equip == null) {
				return ErrorCode.EQUIP_NOT_EXIST;
			}
			ConfigEquip configEquip = ConfigManager.getInstance().getConfig(ConfigEquip.class, equip.getConfigId());
			if (configEquip == null) {
				return ErrorCode.CONFIG_NOT_EXISTS;
			}
			
			final int hole = equip.getHole();
			int nextHole = hole + 1;
			ConfigEquipHole configEquipHole = ConfigManager.getInstance().getConfig(ConfigEquipHole.class, c->(c.getCategory() == configEquip.getCategory() && c.getHole() == nextHole));
			if (configEquipHole == null) {
				return ErrorCode.EQUIP_HOLE_LIMIT;
			}
//			if (!resourceGroupService.checkAndCost(playerId, configEquipHole.getConsume(), NatureEnum.EquipHole)) {
//				return ErrorCode.AMOUNT_NOT_ENOUGH;
//			}
			final boolean hit = RandomUtil.isHit10000(configEquipHole.getRate());
			if(hit) {
				//??????????????????,??????+1
				equip.setHole(equip.getHole() + 1);
				//??????????????????+1
				if (RandomUtil.isHit10000(configEquipHole.getHiddenAttrRate())) {
					equip.setHoleHiddenLevel(equip.getHoleHiddenLevel() + 1);
				}
				this.responseUpdateList(playerId, Lists.newArrayList(equip));
			}else if(RandomUtil.isHit10000(configEquipHole.getDestoryRate())){
//				equip.delete();
				this.responseDeleteList(playerId, Lists.newArrayList(equip.getId()));
			}
			ack.setResult(hit);
			return ErrorCode.SUCCESS;
		} catch (Exception e) {
			log.error("reqEquipPunching error, playerId:{}", playerId, e);
			return ErrorCode.UNKNOWN_ERROR;
		}
	}
	
	/////////////????????????////////////////////////
	
	@Override
	public Equip getEquip(long playerId, long equipId) {
		EquipDomain domain = equipManager.getDomain(playerId);
		if (domain == null) return null;
		return domain.getBean(equipId);
	}
	
	@Override
	public int resType() {
		return ResourceType.Equip.getType();
	}
	
	@Override
	public boolean checkAdd(long playerId, Integer configId, Integer value) {
		EquipDomain domain = equipManager.getDomain(playerId);
		if (domain == null) return false;
		return domain.checkAdd(configId, value);
	}
	
	@Override
	public boolean checkEnough(long playerId, Integer configId, Integer value) {
		EquipDomain domain = equipManager.getDomain(playerId);
		if (domain == null) return false;
		return domain.checkEnough(configId, value);
	}

	@Override
	public void reward(long playerId, Integer configId, Integer value, NatureEnum nEnum) {
		EquipDomain domain = equipManager.getDomain(playerId);
		if (domain == null)	return;
		//????????????????????????
		domain.add(configId, value);
	}

	@Override
	public void cost(long playerId, Integer configId, Integer value, NatureEnum nEnum) {
		EquipDomain domain = equipManager.getDomain(playerId);
		if (domain == null)	return;
		domain.costByConfigId(configId, value);
	}
	
	@Override
	public void cost(long playerId, Long id, NatureEnum nEnum) {
		EquipDomain domain = equipManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		/*
		 * ???????????????????????????, ??????????????????????????????1.
		 * ??????????????????,??????????????????????????????,????????????,????????????0?????????.
		 * ??????????????????????????????1
		 */
		domain.costById(id, 1);
	}

	@Override
	public int getCount(long playerId, Integer configId) {
		EquipDomain domain = equipManager.getDomain(playerId);
		if (domain == null) {
			return 0;
		}
		return domain.getCount(configId);
	}

	@Override
	public IModuleManager<Long, ?> getModuleManager() {
		return equipManager;
	}

	@Override
	public int getModuleId() {
		return ModuleDefine.EQUIT.getModuleId();
	}
	
//	@Override
//	public void addResource(long playerId, IResource res, NatureEnum nEnum) {
//		EquipDomain domain = equipManager.getDomain(playerId);
//		if (domain == null) return ;
//		if (!(res instanceof Equip)) {
//			return;
//		}
//		Equip equip = (Equip)res;
//		domain.addReource(equip.getUniqueId(), equip);
//	}

}