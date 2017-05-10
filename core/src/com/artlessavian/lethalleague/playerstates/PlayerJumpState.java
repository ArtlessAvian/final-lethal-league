package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerJumpState extends State
{
	Player player;

	public PlayerJumpState(Player player)
	{
		this.player = player;
	}

	@Override
	public void exit()
	{
		//DONE
	}

	@Override
	public void enter()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
		physicsC.grounded = false;
	}

	@Override
	public boolean changeStateMaybe(StateMachine sm)
	{
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		if (physicsC.vel.y < 0)
		{
			if (player.input.downPressed)
			{
				physicsC.vel.y = player.fastFallSpeed;
			}
			physicsC.passiveGravity = player.gravity;

		}
		else
		{
			physicsC.passiveGravity = player.lowGravity;
		}

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
