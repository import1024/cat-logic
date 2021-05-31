package com.cat.server.game.module.${entityName ? lower_case};

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cat.net.network.annotation.Cmd;
import com.cat.net.network.base.GameSession;
import com.cat.server.game.data.proto.PBDefine.PBProtocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cat.server.game.helper.result.ErrorCode;
import com.cat.server.game.module.${entityName?lower_case}.proto.*;
import ${protocolObj.getDependenceImport()};
import ${protocolObj.getJavaImport()};

/**
 * ${entityName}控制器
 */
@Controller
public class ${entityName}Controller {
	
	private static final Logger log = LoggerFactory.getLogger(${entityName}Controller.class);
	
	@Autowired
	private ${entityName}Service ${entityName ? uncap_first}Service;
	
	<#if protoReqStructList ? exists>
	<#list protoReqStructList as proto>
	
	/*
	*${proto.comment}
	*/
	@Cmd(value = PBProtocol.${proto.name}_VALUE)
	public void ${proto.name}(GameSession session, ${proto.name} req) {
		long playerId = session.getPlayerId();
		${protoAckStructMap[proto.name].name}Resp ack = ${protoAckStructMap[proto.name].name}Resp.newInstance();
		<#if protoAckStructMap[proto.name].fields ? exists>
		<#list protoAckStructMap[proto.name].fields as ackStruct>
		<#if ackStruct.name == 'code'>
		<#assign  isCode = "true"/>
		</#if>
		</#list>
		</#if>
		<#if isCode == 'true'>
		ErrorCode code = ${entityName ? uncap_first}Service.${proto.name ? uncap_first}(playerId, req, ack);
		ack.setCode(code.getCode());
		<#else>
		${entityName ? uncap_first}Service.${proto.name ? uncap_first}(playerId, req, ack);
		</#if>
		${entityName ? uncap_first}Service.sendMessage(playerId, ack);
	}
	<#assign  isCode = "false"/>
	</#list>
	</#if>
	

}
