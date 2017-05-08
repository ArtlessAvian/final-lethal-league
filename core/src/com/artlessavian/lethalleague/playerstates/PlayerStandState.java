package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.ecs.components.InputComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerStandState extends State
{
	Player player;

	public PlayerStandState(Player player)
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
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
		physicsC.grounded = true;
	}

	@Override
	public boolean changeStateMaybe(StateMachine sm)
	{
<<<<<<< HEAD
		if (player.input.downPressed)
		{
		    sm.gotoState(PlayerCrouchState.class);
		    return true;
		}
		else if (player.input.upPressed)
		{
		    sm.gotoState(PlayerJumpState.class);
		    return true;
		}
//		else if (player.input.swingPressed)
//		{
//		    sm.gotoState(PlayerSwingState.class);
//		    return true;
//		}
//
=======
		// TODO
		if (player.input.upPressed)
		{
			sm.gotoState(PlayerJumpState.class);
			return true;
		}

>>>>>>> refs/remotes/origin/add-functionality
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
	
		if (player.input.leftPressed != player.input.rightPressed)
		{
			if (player.input.leftPressed)
			{
				physicsC.vel.x -= 50;
			}
			else //inputC.input.rightPressed
			{
				physicsC.vel.x += 50;
			}
		}
		else
		{
			physicsC.vel.x -= Math.signum(physicsC.vel.x) * 10;
		}
		physicsC.vel.clamp(0, 300);
	}

	@Override
	public int getSpriteID()
	{
		return 0;
	}
}
