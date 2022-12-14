package com.cat.server.core.config.container;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.cat.server.common.ServerConstant;
import com.cat.server.core.config.ConfigManager;
import com.cat.server.core.config.annotation.ConfigPath;
import com.cat.server.core.config.event.RemoteConfigRefreshEvent;
import com.cat.server.core.context.SpringContextHolder;
import com.cat.server.core.event.GameEventBus;

/**
 * 本地配置容器
 * @author Jeremy
 *
 */
public class DefaultRemoteConfigContainer<T extends IGameConfig> extends AbstractConfigContainer<T> {

	public DefaultRemoteConfigContainer(Class<T> configClazz) {
		super(configClazz);
	}

	@Override
	public String getFileName() {
		ConfigPath configPath = configClazz.getAnnotation(ConfigPath.class);
		if (configPath == null) {
			logger.error("config[{}] file path is null.", configClazz.getName());
			throw new NullPointerException("config[" + configClazz.getName() + "] file path is null.");
		}
		return configPath.value();
	}

	@Override
	protected String readContent() throws IOException {
		String fileName = ServerConstant.JsonPath + getFileName();
		return FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8);
	}

	/**
	 * 本地配置刷新, 通常热更调用, 刷新全部配置
	 */
	@Override
	public void refresh() {
		try {
			loadAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterLoad(boolean startup) {
		//如果是启动加载, 则不通知
		if (startup) return;
		GameEventBus.getInstance().post(RemoteConfigRefreshEvent.create(configClazz, getFileName()));
	}
}
