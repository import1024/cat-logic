package com.cat.server.game.module.family;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cat.net.network.base.ISession;
import com.cat.server.game.helper.result.ErrorCode;
import com.cat.server.game.module.player.IPlayerService;

/**
 * PlayerFamily控制器
 */
@Controller
public class PlayerFamilyController {
	
	private static final Logger log = LoggerFactory.getLogger(PlayerFamilyController.class);
	
	@Autowired
	private PlayerFamilyService playerFamilyService;
	
	@Autowired
	private IPlayerService playerService;
	
	public void createFamily(ISession session)
	{
		final long playerId = session.getUserData();
		String familyName = "爱你一万年";
		ErrorCode code = playerFamilyService.createFamily(playerId, familyName);
		//playerService.sendMessage(playerId, protocol);
	} 

}
