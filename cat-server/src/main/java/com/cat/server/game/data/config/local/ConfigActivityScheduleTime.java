package com.cat.server.game.data.config.local;

import com.alibaba.fastjson.annotation.JSONField;
import com.cat.server.core.config.annotation.ConfigPath;
import com.cat.server.game.data.config.local.base.ConfigActivityScheduleTimeBase;
import com.cat.server.game.module.activity.time.parse.TimePointParser;
import com.cat.server.game.module.activity.time.point.ITimePoint;


/**
 * hd.活动时间表.xlsx<br>
 * activity_schedule_time.json<br>
 * 
 * @author auto gen
 *
 */
@ConfigPath("activity_schedule_time.json")
public class ConfigActivityScheduleTime extends ConfigActivityScheduleTimeBase {
	
	@JSONField(name="startTime", deserializeUsing = TimePointParser.class)
	private ITimePoint time;

	public ITimePoint getTime() {
		return time;
	}

	public void setTime(ITimePoint time) {
		this.time = time;
	}
	
	@Override
	public void parse() {
		//TODO something.
	}

}
