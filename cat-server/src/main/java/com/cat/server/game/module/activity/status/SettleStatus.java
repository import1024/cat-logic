package com.cat.server.game.module.activity.status;

import com.cat.server.game.module.activity.domain.Activity;
import com.cat.server.game.module.activity.type.IActivityType;

public class SettleStatus extends AbstractStatus {

	public SettleStatus(IActivityType activityDomain) {
		super(activityDomain);
	}

	@Override
	public boolean handle(long now) {
		if (!activityType.isBegin()) {
            return false;
        }
		Activity activity = getActivity();
		long settleTime = activity.getSettleTime();
        if (now < settleTime) {
            return false;
        }
		//先设置状态,再通知,最后清空活动数据
        activity.setStatus(getCode());
        activity.save();
        activityType.onSettle(now);
		return true;
	}
	
	@Override
	public int getCode() {
		return SETTLE;
	}

}
