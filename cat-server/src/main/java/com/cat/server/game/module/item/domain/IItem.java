package com.cat.server.game.module.item.domain;

import com.cat.server.core.server.IPersistence;
import com.cat.server.game.data.proto.PBItem;
import com.cat.server.game.module.resource.IResource;

/**
 * 物品抽象类
 * @author Jeremy
 */
public interface IItem extends IPersistence, IResource{

	/**
	 * 增加数量, 返回最新数量
	 * @return
	 */
	default public int addCount(int value) {
		return 0;
	}
	
	/**
	 * 扣除数量, 返回最新数量
	 * @return
	 */
	default public int deductCount(int value) {
		return 0;
	}
	
	/**
	 * 转proto对象
	 * @return 数量为0. 不下发
	 */
	default public PBItem.PBItemInfo toProto() {
		if(getCount() <= 0) return null;
		PBItem.PBItemInfo.Builder builder = PBItem.PBItemInfo.newBuilder();
		builder.setId(getUniqueId());
		builder.setCount(getCount());
		builder.setConfigId(getConfigId());
		return builder.build();
	}
	
}
