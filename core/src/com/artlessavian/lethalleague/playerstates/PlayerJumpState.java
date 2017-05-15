package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerJumpState extends State
{
	Player player;
	private boolean antiSwingSpam;

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

		if (player.input.swingPressed)
		{
			antiSwingSpam = true;
		}
	}

	@Override
	public boolean changeStateMaybe()
	{
		if (player.input.swingPressed && !antiSwingSpam)
		{
			if (!(player.input.upPressed) && (player.input.downPressed || player.input.rightPressed || player.input.leftPressed))
			{
				sm.gotoState(PlayerSmashState.class);
			}
			else
			{
				sm.gotoState(PlayerSwingState.class);
			}
			return true;
		}
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		CommonPlayerFuncts.fall(player, physicsC);
		CommonPlayerFuncts.fastfallCheck(player, physicsC);

		CommonPlayerFuncts.changeDirection(player, physicsC);
		CommonPlayerFuncts.horizontalInput(player, physicsC);
		CommonPlayerFuncts.clampMovement(player, physicsC);

		if (antiSwingSpam && !player.input.swingPressed) {antiSwingSpam = false;}
	}

	@Override
	public void editSprite(Sprite sprite)
	{

	}
}
