package com.cat.server.core.event;

import com.cat.server.utils.TimeUtil;

/**
 * 事件基础类, 可用作于公共事件基础类
 * @author Jeremy
 */
public abstract class BaseEvent implements IEvent{
	
	/**
	 * 发送时间， 可在外部设置值.
	 */
	private final long time;
	
	public BaseEvent() {
		this.time = TimeUtil.now();
	}
	
	public BaseEvent(long time) {
		this.time = time;
	}
	
	@Override
	public long getTime() {
		return time;
	}
	
}
