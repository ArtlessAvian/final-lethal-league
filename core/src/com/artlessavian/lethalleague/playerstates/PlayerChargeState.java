package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.ecs.components.InputComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerChargeState implements State
{
	Player player;

	public PlayerChargeState(Player player)
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
	public boolean changeStateMaybe(StateMachine sm)
	{
		// TODO
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		// TODO
	}
}
