package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerSwingState extends State
{
	Player player;

	public PlayerSwingState(Player player)
	{
		this.player = player;
	}

	@Override
	public void exit()
	{
		// TODO

	}

	@Override
	public void enter()
	{
		// TODO

	}

	@Override
	public boolean changeStateMaybe()
	{
		// TODO
		if(getTimeInState() > 30)
		{
			PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
			if (physicsC.grounded)
			{
				sm.gotoState(PlayerStandState.class);
			}
			else
			{
				sm.gotoState(PlayerJumpState.class);
			}
			return true;
		}
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		//TODO

		if (player.input.leftPressed != player.input.rightPressed)
		{
			if (player.input.leftPressed)
			{
				physicsC.vel.x -= player.airAccel;
			}
			else //inputC.input.rightPressed
			{
				physicsC.vel.x += player.airAccel;
			}
		}
		if (physicsC.vel.x > player.airMaxSpeed) {physicsC.vel.x = player.airMaxSpeed;}
		if (physicsC.vel.x < -player.airMaxSpeed) {physicsC.vel.x = -player.airMaxSpeed;}
	}

	@Override
	public int getSpriteID()
	{
		return 0;
	}
}
