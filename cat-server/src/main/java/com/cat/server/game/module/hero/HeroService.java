package com.cat.server.game.module.hero;

import com.cat.server.game.helper.ResourceType;
import com.cat.server.game.helper.log.NatureEnum;
import com.cat.server.game.module.hero.domain.Hero;
import com.cat.server.game.module.hero.domain.HeroDomain;
import com.cat.server.game.module.item.domain.Item;
import com.cat.server.game.module.item.domain.ItemDomain;
import com.cat.server.game.module.resource.IResource;
import com.cat.server.game.module.resource.IResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class HeroService implements IHeroService, IResourceService{
	
	private static final Logger log = LoggerFactory.getLogger(HeroService.class);
	
	@Autowired private HeroManager heroManager;
	
	/**
	 * 登陆
	 */
	public void onLogin(long playerId) {
		HeroDomain domain = heroManager.getOrLoadDomain(playerId);
		if (domain == null) {
			log.info("HeroService error, domain is null");
			return;
		}
		//下发玩家武将列表
	}
	
	
	/////////////业务逻辑/////////////
	@Override
	public Hero getHero(long playerId, long heroId) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return null;
		}
		return domain.getBean(heroId);
	}
	
	@Override
	public int resType() {
		return ResourceType.Hero.getType();
	}

	@Override
	public boolean checkAdd(long playerId, Integer configId, Integer count) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return false;
		}
		return domain.checkAdd(configId, count);
	}

	@Override
	public boolean checkEnough(long playerId, Integer configId, Integer count) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return false;
		}
		return domain.checkEnough(configId, count);
	}

	@Override
	public void reward(long playerId, Integer configId, Integer count, NatureEnum nEnum) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		domain.add(configId, count);
	}

	@Override
	public void cost(long playerId, Integer configId, Integer count, NatureEnum nEnum) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		domain.costByConfigId(configId, count);
	}

	@Override
	public void cost(long playerId, Long uniqueId, NatureEnum nEnum) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		domain.costById(uniqueId, 1);
	}


	@Override
	public int getCount(long playerId, Integer configId) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return 0;
		}
		return domain.getCount(configId);
	}
	
	@Override
	public void clearExpire(long playerId, int configId) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		domain.clearExpire(configId);
	}

//	@Override
//	public void addResource(long playerId, IResource res, NatureEnum nEnum) {
//		HeroDomain domain = heroManager.getDomain(playerId);
//		if (domain == null) return ;
//		if (!(res instanceof Hero)) {
//			return;
//		}
//		Hero hero = (Hero)res;
//		domain.addReource(hero.getUniqueId(), hero);
//	}
}
