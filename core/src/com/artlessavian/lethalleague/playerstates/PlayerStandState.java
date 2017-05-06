package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.ecs.components.InputComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerStandState implements State
{
	Player player;

	public PlayerStandState(Player player)
	{
		this.player = player;
	}

	@Override
	public void reset()
	{

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
		InputComponent inputC = player.getComponent(InputComponent.class);

		if (inputC.input.leftPressed)
		{
			physicsC.vel.x -= 50;
		}
		if (inputC.input.rightPressed)
		{
			physicsC.vel.x += 50;
		}
		else if (!inputC.input.leftPressed)
		{
			physicsC.vel.x -= Math.signum(physicsC.vel.x) * 10;
		}
		physicsC.vel.clamp(0, 300);
	}
}
