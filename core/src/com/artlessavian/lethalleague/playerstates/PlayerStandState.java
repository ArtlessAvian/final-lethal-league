package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
	public boolean changeStateMaybe()
	{
		if (player.input.downPressed)
		{
		    sm.gotoState(PlayerCrouchState.class);
		    return true;
		}
		else if (player.input.jumpPressed)
		{
		    sm.gotoState(PlayerJumpSquatState.class);
		    return true;
		}
		else if (player.input.swingPressed)
		{
		    sm.gotoState(PlayerSwingState.class);
		    return true;
		}

		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		CommonPlayerFuncts.horizontalInput(player, physicsC);
		CommonPlayerFuncts.clampMovement(player, physicsC);
	}

	@Override
	public void editSprite(Sprite sprite)
	{

	}
}
