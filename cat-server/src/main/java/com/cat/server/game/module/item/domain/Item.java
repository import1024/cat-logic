package com.cat.server.game.module.item.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cat.orm.core.annotation.PO;
import com.cat.server.core.config.ConfigManager;
import com.cat.server.core.context.SpringContextHolder;
import com.cat.server.game.data.config.local.ConfigItem;
import com.cat.server.game.helper.uuid.SnowflakeGenerator;

/**
* @author Jeremy
*/
@PO(name = "item")
public class Item extends ItemPo implements IItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9085231092692832763L;
	
	private final static Logger log = LoggerFactory.getLogger(Item.class);

	public Item() {

	}
	
	/**
	 * 创建一个道具对象
	 */
	public static Item create(long playerId, int configId, int count) {
		SnowflakeGenerator generator = SpringContextHolder.getBean(SnowflakeGenerator.class);
		long itemId = generator.nextId();
		Item item = new Item();
		item.setItemId(itemId);
		item.setConfigId(configId);
		item.setCount(count);
		item.setPlayerId(playerId);
		item.save();
		return item;
	}
	
	/**
	 * 增加数量
	 */
	@Override
	public int addCount(int value) {
		if(value <= 0) {
			log.info("addCount, the value must be greater than 0, value:"+value);
			return getCount();
		}
		int expect = getCount() + value;
		expect = expect > Integer.MAX_VALUE ? Integer.MAX_VALUE : expect;
		this.setCount(expect);
		return expect;
	}
	
	/**
	 * 减少数量
	 */
	@Override
	public int deductCount(int value) {
		if(value <= 0) {
			log.info("deductCount, the value must be greater than 0, value:"+value);
			return getCount();
		}
		int expect = getCount() - value;
		expect = expect < 0 ? 0 : expect;
		this.setCount(expect);
		return expect;
	}
	
	@Override
	public long getUniqueId() {
		return getItemId();
	}
	
	@Override
	public int getQuality() {
		ConfigItem config = ConfigManager.getInstance().getConfig(ConfigItem.class, getConfigId());
		if (config == null) {
			return 0;
		}
		return config.getQuality();
	}
}
