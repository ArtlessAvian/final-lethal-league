package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
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

	boolean antiSwingSpam = false;

	@Override
	public void enter()
	{
		// TODO
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
		physicsC.grounded = true;

		if (player.input.swingPressed)
		{
			antiSwingSpam = true;
		}

		SpriteComponent spriteC = player.getComponent(SpriteComponent.class);
		spriteC.sprite.setRotation(0);
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
			PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
			physicsC.vel.y = player.jumpVelocity;
		    sm.gotoState(PlayerJumpState.class);
		    return true;
		}
		else if (player.input.swingPressed && !antiSwingSpam)
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
		CommonPlayerFuncts.changeDirection(player, physicsC);

		if (antiSwingSpam && !player.input.swingPressed) {antiSwingSpam = false;}
	}

	@Override
	public void editSprite(Sprite sprite)
	{
		SpriteComponent spriteC = player.getComponent(SpriteComponent.class);
		if (spriteC.usingTestSpriteSheet)
		{
			CommonPlayerFuncts.setUV(0,0, sprite);
		}
	}
}
