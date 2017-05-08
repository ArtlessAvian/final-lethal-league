package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.ecs.components.InputComponent;
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
	}

	@Override
	public void enter()
	{
		
	}

	@Override
	public boolean changeStateMaybe(StateMachine sm)
	{
		if (!player.input.downPressed)
		{
		    sm.gotoState(PlayerStandState.class);
		    return true;
		}
		else if (player.input.upPressed)
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

		// TODO
	}

	@Override
	public int getSpriteID()
	{
		return 0;
	}
}
