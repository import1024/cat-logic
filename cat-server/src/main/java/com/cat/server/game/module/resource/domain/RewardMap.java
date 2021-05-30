package com.cat.server.game.module.resource.domain;

import com.cat.server.game.module.item.proto.AckRewardsResp;
import com.cat.server.game.module.item.proto.PBRewardInfoBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 奖励对象字典
 */
public class RewardMap {

    /**
     * key:资源id，可以是属性，道具
     * value:值
     */
    private final Map<Integer, Integer> dictionary;

    public RewardMap() {
        this.dictionary = new HashMap<>();
    }

    public RewardMap(Map<Integer, Integer> dictionary) {
        if (dictionary == null) {
            throw new NullPointerException();
        }
        this.dictionary = dictionary;
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
        if(added == 0) return;
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
    public void merge(RewardMap otherReward) {
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
        if (added == 0) return;
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
    public AckRewardsResp toProto(){
        AckRewardsResp resp = AckRewardsResp.newInstance();
        this.dictionary.forEach((key, val)->{
            PBRewardInfoBuilder builder = new PBRewardInfoBuilder();
            builder.setConfigId(key);
            builder.setCount(val);
            resp.addRewards(builder.build());
        });
        return resp;
    }

}
