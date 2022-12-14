package com.cat.server.common;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 游戏服配置
 * 
 * @author Jeremy
 * @date 2020年6月29日
 *
 */
@Configuration
public class ServerConfig {

	// 服务器ip
	@Value("${cat.game.server.serverId}")
	private int serverId;
	// 后台地址
	@Value("${cat.game.server.backstageUrl}")
	private String backstageUrl;
//	// 后台地址
//	@Value("${cat.game.server.remoteUrl}")
//	private String remoteUrl;
	// 登录地址
	@Value("${cat.game.server.loginUrl}")
	private String loginUrl;
	// 开服时间-此值从后台的配置获取是否会更加的合适
	@Value("#{T(java.time.LocalDate).parse('${cat.game.server.openDate}')}")
	private LocalDate openDate;
	
	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getBackstageUrl() {
		return backstageUrl;
	}

	public void setBackstageUrl(String backstageUrl) {
		this.backstageUrl = backstageUrl;
	}

//	public String getRemoteUrl() {
//		return remoteUrl;
//	}
//
//	public void setRemoteUrl(String remoteUrl) {
//		this.remoteUrl = remoteUrl;
//	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

}