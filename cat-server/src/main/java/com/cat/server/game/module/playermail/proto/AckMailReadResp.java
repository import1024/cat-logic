package com.cat.server.game.module.playermail.proto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cat.server.game.data.proto.PBDefine.*;
import com.cat.net.network.base.IProtocol;
import com.google.protobuf.AbstractMessageLite.Builder;
import com.cat.server.game.data.proto.PBItem.*;
import com.cat.server.game.data.proto.PBMail.*;

/**
* AckMailReadResp
* @author Jeremy
*/
public class AckMailReadResp implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(AckMailReadResp.class);
	
	private final AckMailRead.Builder builder = AckMailRead.newBuilder();
	
	public AckMailReadResp() {}
	
	public static AckMailReadResp newInstance() {
		return new AckMailReadResp();
	}
	
	public AckMailRead build() {
		return builder.build();
	}
	
	/** 错误码,非0表示弹提示**/
	public void setCode(int value){
		this.builder.setCode(value);
	}
	
	@Override
	public int protocol() {
		return PBProtocol.AckMailRead_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}
}