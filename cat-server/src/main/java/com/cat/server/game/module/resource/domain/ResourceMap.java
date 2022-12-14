package com.cat.server.game.module.resource.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cat.server.core.annotation.NotUse;
import com.cat.server.game.data.proto.PBItem.PBRewardInfo;
import com.cat.server.game.module.item.proto.PBRewardInfoBuilder;
import com.cat.server.game.module.item.proto.RespRewardsBuilder;

/**
 * Map类型的资源
 */
@NotUse
@Deprecated
public class ResourceMap {

    /**
     * key:资源id，可以是属性，道具
     * value:值
     */
    private Map<Integer, Integer> dictionary;
    
    public ResourceMap() {}

    public ResourceMap(Map<Integer, Integer> dictionary) {
        if (dictionary == null) {
            throw new NullPointerException();
        }
        //JDK
        this.dictionary = Collections.unmodifiableMap(dictionary);
        //Guava
        //this.dictionary = ImmutableMap.copyOf(dictionary);
    }

    /**
     * 	获取奖励字典
     * @return
     */
    public Map<Integer, Integer> getDictionary() {
        return dictionary;
    }
    
    /**
     * 	根据资源id获取值
     * @param configId 资源id
     */
    public Integer getCount(int configId) {
        return dictionary.getOrDefault(configId, 0);
    }

    /**
     * 	增加数量
     * @param configId 资源id
     * @param added 增加的数量
     */
    public void addCount(int configId, Integer added) {
        if(added <= 0) return;
        int count = getCount(configId);
        count+=added;
        dictionary.put(configId, count);
    }
    
    /**
     * 	根据给定的类型, 值增加属性
     * @param dictionary 需要合并的奖励
     */
    public <T extends Map<Integer, Integer>> void merge(T dictionary) {
        dictionary.forEach((key, value)->{
        	addCount(key, value);
        });
    }
    
    /**
     * 	其他字典合并到此字典
     * @param otherReward 其他奖励字典
     */
    public void merge(ResourceMap otherReward) {
        if (otherReward == null || otherReward.isEmpty()) {
            return;
        }
        this.merge(otherReward.getDictionary());
    }
    
    /**
     * 	根据给定的类型, 值减少属性
     */
    public <T extends Map<Integer, Integer>> void subCount(T dictionary) {
        if (dictionary == null) {
            return;
        }
        dictionary.forEach((key, value)->{
        	subAttr(key, (Integer)value);
        });
    }

    /**
     * 	根据基础类型减少值
     * @param configId 资源id
     * @param added 数量
     */
    public void subAttr(int configId, int added) {
        if (added <= 0) return;
        int count = getCount(configId);
        count = count - added;
        if (count <= 0){
            count = 0;
        }
        dictionary.put(configId, count);
    }

//    /**
//     * 	根据枚举类型获取百分比值
//     * @param attrType
//     * @param value
//     */
//    public double getRateAttr(AttributeType attrType) {
//        int id = attrType.getId();
//        return getRateAttr(id);
//    }
    
//    /**
//     * 	百分比值计算
//     * @param attrType
//     * @param value
//     */
//    public double getRateAttr(int attrType) {
//        return getAttr(attrType) / 10000d;
//    }

    /**
     * 	判断字典是否为空
     * @return
     */
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }
    
    /**
     * 奖励序封装成奖励消息对象
     * @return 奖励消息对象
     */
    public RespRewardsBuilder toProto(){
    	RespRewardsBuilder resp = RespRewardsBuilder.newInstance();
        this.dictionary.forEach((key, val)->{
            PBRewardInfoBuilder builder = new PBRewardInfoBuilder();
            builder.setConfigId(key);
            builder.setCount(val);
            resp.addRewards(builder.build());
        });
        return resp;
    }
    
    /**
     * 奖励序封装成奖励消息对象
     * @return 奖励消息对象
     */
    public Collection<PBRewardInfo> toCollProto(){
    	Collection<PBRewardInfo> ret = new ArrayList<>();
        this.dictionary.forEach((key, val)->{
            PBRewardInfoBuilder builder = new PBRewardInfoBuilder();
            builder.setConfigId(key);
            builder.setCount(val);
            ret.add(builder.build());
        });
        return ret;
    }
    
    @Override
	public String toString() {
		return "ResourceMap [dictionary=" + dictionary + "]";
	}

	public static void main(String[] args) {
    	Map<Integer, Integer> ret = new HashMap<>();
    	ret.put(1, 1);
    	ret.put(2, 2);
    	String json = JSON.toJSONString(ret);
    	System.out.println(json);
    	
//    	ResourceMap map = new ResourceMap(ret);
//    	json = JSON.toJSONString(map);
//    	System.out.println(json);
    	
    	ResourceMap map = JSON.parseObject(json, ResourceMap.class);
    	System.out.println(map);
	}

}
