package com.cat.server.game.module.pet.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cat.server.core.config.ConfigManager;
import com.cat.server.core.server.AbstractModuleMultiDomain;
import com.cat.server.game.data.config.local.ConfigPetBase;
import com.cat.server.game.data.config.local.ConfigPetPrefix;
import com.cat.server.game.data.config.local.ConfigPetPropertyAptitude;
import com.cat.server.game.data.config.local.ConfigPetSkill;
import com.cat.server.game.data.config.local.ConfigPetSkillPool;
import com.cat.server.game.helper.result.ErrorCode;
import com.cat.server.game.module.resource.IResourceDomain;
import com.cat.server.game.module.resource.domain.PetResourceDomain;
import com.cat.server.utils.RandomUtil;
import com.google.common.primitives.Ints;

/**
* PetDomain
* @author Jeremy Feng
*/
public class PetDomain extends AbstractModuleMultiDomain<Long, Long, Pet> implements IResourceDomain<Long, Pet>{

	private static final Logger log = LoggerFactory.getLogger(PetDomain.class);
	
	/**
	 * 资源代理对象
	 */
	private IResourceDomain<Long, Pet> resourceDomainProxy;
	
	public PetDomain(){
		
	}
	
	////////////业务代码////////////////////
	
	/**
	 * 初始化宠物
	 * 1. 随机性别
	 * @return  
	 * @date 2022年4月10日下午7:37:42
	 */
	public ErrorCode initializeGender(Pet pet, ConfigPetBase config) {
		//1.随机性别
		List<Integer> ret = Ints.asList(config.getGender());
		Optional<Integer> opt = RandomUtil.random(ret);
		if (!opt.isPresent()) {
			log.info("initializeGender error, random is empty, configId:{}", pet.getConfigId());
			return ErrorCode.CONFIG_NOT_EXISTS;
		}
		int gender = opt.get();
		pet.setGender((short)gender);
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 初始化宠物技能池类型
	 * 1. 随机技能池类型,激活时持久化到宠物上
	 * @return  
	 * @date 2022年4月10日下午7:37:42
	 */
	public boolean initializeSkillPoolType(Pet pet, ConfigPetBase config) {
		//1.随机技能池id
		List<Integer> ret = Ints.asList(config.getSkillPoollType());
		Optional<Integer> opt = RandomUtil.random(ret);
		if (!opt.isPresent()) {
			log.info("initializeSkillPoolType error, random is empty, configId:{}", pet.getConfigId());
			return false;
		}
		int skillPoolType = opt.get();
		pet.setSkillPoolType(skillPoolType);
		return true;
	}
	
	/**
	 * 	尝试领悟技能, 此方法会记录升级后的宠物等级<br>
	 * 当激活时获得技能, 记录此等级, 实际等级就是当前等级-记录时宠物等级 +1 =技能最终等级
	 * 1.激活时领悟
	 * 2.升级后领悟
	 * @return void  
	 * @date 2022年4月9日下午5:32:11
	 */
	public boolean comprehensionSkill(Pet pet, ConfigPetBase configPet) {
		//TODO 首先按照此宠物的幸运值,这个幸运值是否是全局的,是否领悟技能
		boolean bool = RandomUtil.isHit10000(configPet.getSkillRateHit());
		if (!bool) {
			return false;
		}
		/*可领悟的技能列表
		* 1. 当前宠物等级大于等于配置等级
		* 2.当前技能未领悟过
		*/
		List<Integer> configSkills = ConfigManager.getInstance().getConfigs(ConfigPetSkill.class, 
				c-> pet.getLevel() >= c.getStartLv() && pet.getSkillLevel(c.getId()) <= 0)
				.values().stream().map(ConfigPetSkill::getId)
				.collect(Collectors.toList());
		
		final int skillPoolType = pet.getSkillPoolType();
		//过滤出技能组对应的技能,并且宠物没有获得过的
		Collection<ConfigPetSkillPool> configs = ConfigManager.getInstance()
				.getConfigs(ConfigPetSkillPool.class, c->c.getType() == skillPoolType).values()
				.stream()
				.filter((c)-> configSkills.contains(c.getSkillId()))
				.collect(Collectors.toList());
		//按照权重随机抽取一个
		Optional<ConfigPetSkillPool> result = RandomUtil.randomByWeight(configs, ConfigPetSkillPool::getWeight);
		if (!result.isPresent()) {
			return false;
		}
		final int skillId = result.get().getSkillId();
		//随机出来的技能id
		pet.getSkillMap().put(skillId, pet.getLevel());
		return true;
	}
	
	
	/**
	 * 初始化前缀
	 * @return void  
	 * @date 2022年4月10日下午7:25:44
	 */
	public boolean initializePrefix(Pet pet) {
		Collection<ConfigPetPrefix> configs = ConfigManager.getInstance().getAllConfigs(ConfigPetPrefix.class).values();
		Optional<ConfigPetPrefix> result = RandomUtil.randomByWeight(configs, ConfigPetPrefix::getWeight);
		if (!result.isPresent()) {
			return false;
		}
		final int prefixId = result.get().getId();
		pet.setPrefixId(prefixId);
		//TODO 同步宠物变化给客户端
		return true;
	}
	
	/**
	 * 随机资质<br>
	 * 根据
	 * @param pet
	 */
	public boolean randomAptitudeAttr(Pet pet) {
		//过滤出资质
		Set<Integer> configTypeIds = ConfigManager.getInstance().getAllConfigs(ConfigPetPropertyAptitude.class)
		.values()
		.stream()
		.map(ConfigPetPropertyAptitude::getType)
		.collect(Collectors.toSet());
		
		pet.getAptitudeAttrList().clear();
		
		for (Integer configTypeId : configTypeIds) {
			Collection<ConfigPetPropertyAptitude> configs = ConfigManager.getInstance().getConfigs(ConfigPetPropertyAptitude.class, c->c.getType() == configTypeId).values();
			Optional<ConfigPetPropertyAptitude> opt = RandomUtil.randomByWeight(configs, ConfigPetPropertyAptitude::getWeight);
			if (!opt.isPresent()) {
				continue;
			}
			pet.getAptitudeAttrList().add(opt.get().getId());
		}
		return true;
	}
	
	////////////接口方法////////////////////
	@Override
	public void afterInit() {
		this.resourceDomainProxy = PetResourceDomain.create(getId(), getBeanMap());
	}
	
	@Override
	public Pet getBeanByConfigId(int configId) {
		return resourceDomainProxy.getBeanByConfigId(configId);
	}

	@Override
	public boolean checkAdd(int configId, int count) {
		return resourceDomainProxy.checkAdd(configId, count);
	}

	@Override
	public List<Pet> add(int configId, int count) {
		return resourceDomainProxy.add(configId, count);
	}

	@Override
	public boolean checkEnough(int configId, int count) {
		return resourceDomainProxy.checkEnough(configId, count);
	}

	@Override
	public boolean costByConfigId(int configId, int count) {
		return resourceDomainProxy.costByConfigId(configId, count);
	}

	@Override
	public boolean costById(Long id, int count) {
		return resourceDomainProxy.costById(id, count);
	}

	@Override
	public List<Pet> getAndClearUpdateList() {
		return resourceDomainProxy.getAndClearUpdateList();
	}

	@Override
	public List<Long> getAndClearDeleteList() {
		return resourceDomainProxy.getAndClearDeleteList();
	}

	@Override
	public int getCount(int configId) {
		return resourceDomainProxy.getCount(configId);
	}

	@Override
	public void clearExpire(int configId) {
		resourceDomainProxy.clearExpire(configId);
	}

//	@Override
//	public void addReource(Long uniqueId, Pet pet) {
//		resourceDomainProxy.addReource(uniqueId, pet);
//	}
}
