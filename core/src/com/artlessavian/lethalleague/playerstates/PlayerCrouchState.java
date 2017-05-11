package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerCrouchState extends State
{
	Player player;

	public PlayerCrouchState(Player player)
	{
		this.player = player;
	}

	@Override
	public void exit()
	{
		// TODO
//		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
//		physicsC.collision.height = 144;
	}

	@Override
	public void enter()
	{
//		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
//		physicsC.collision.height = 50;
	}

	@Override
	public boolean changeStateMaybe()
	{
		if (!player.input.downPressed)
		{
		    sm.gotoState(PlayerStandState.class);
		    return true;
		}
		else if (player.input.jumpPressed)
		{
		    sm.gotoState(PlayerJumpState.class);
		    return true;
		}
		
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		CommonPlayerFuncts.friction(player, physicsC);
		// TODO
	}

	@Override
	public int getSpriteID()
	{
		return 0;
	}
}
