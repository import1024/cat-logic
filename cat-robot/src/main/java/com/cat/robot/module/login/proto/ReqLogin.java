package com.cat.robot.module.login.proto;

import com.cat.net.network.base.IProtocol;
import com.cat.server.game.data.proto.PBDefine.PBProtocol;
import com.cat.server.game.data.proto.PBLogin;
import com.google.protobuf.AbstractMessageLite.Builder;

public class ReqLogin implements IProtocol{
	
	private PBLogin.ReqLogin.Builder builder;
	
	public ReqLogin() {
		this.builder = PBLogin.ReqLogin.newBuilder();
	}
	
	public void setUserName(String value) {
		this.builder.setUserName(value);
	}
	
	public void setSessionKey(String value) {
		this.builder.setSessionKey(value);
	}
	
	public void setChannel(int value) {
		this.builder.setChannel(value);
	}
	
	public void setServerId(int value) {
		this.builder.setServerId(value);
	}
	
	public void setLoginSid(int value) {
		this.builder.setLoginSid(value);
	}
	
	public static ReqLogin create(String userName) {
		ReqLogin req = new ReqLogin();
		req.setUserName(userName);
		req.setServerId(1);
		return req;
	}
	
	@Override
	public short protocol() {
		return PBProtocol.ReqLogin_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}

}