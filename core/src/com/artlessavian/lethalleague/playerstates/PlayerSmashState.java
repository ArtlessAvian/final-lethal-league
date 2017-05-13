package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerSmashState extends State
{
	Player player;
	private boolean hadHit;

	public PlayerSmashState(Player player)
	{
		this.player = player;
	}

	@Override
	public void exit()
	{
		// TODO
		HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
		hitboxC.hitboxes.remove(player.swingBox);
	}

	@Override
	public void enter()
	{
		// TODO
		HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
		hitboxC.hitboxes.add(player.swingBox);

		hitboxC.cannotHit.clear();

		hadHit = false;
	}

	@Override
	public boolean changeStateMaybe()
	{
		// TODO
		if (getTimeInState() >= 30)
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

		if (!physicsC.grounded)
		{
			CommonPlayerFuncts.fall(player, physicsC);
			CommonPlayerFuncts.fastfallCheck(player, physicsC);

			CommonPlayerFuncts.horizontalInput(player, physicsC);
			CommonPlayerFuncts.clampMovement(player, physicsC);
		}
		else
		{
			CommonPlayerFuncts.friction(player, physicsC);
		}

		if (player.ball != null)
		{
			PhysicsComponent physicsCBall = player.ball.getComponent(PhysicsComponent.class);
			physicsCBall.vel.setAngle(player.smashAngle);

			if (physicsC.facingLeft) {physicsCBall.vel.x *= -1;}

			player.ball = null;
			hadHit = true;
		}
	}

	@Override
	public void editSprite(Sprite sprite)
	{
		SpriteComponent spriteC = player.getComponent(SpriteComponent.class);
		if (spriteC.usingTestSpriteSheet)
		{
			if (player.ball != null || hadHit || getTimeInState() > 20)
			{
				CommonPlayerFuncts.setUV(3,2, sprite);
			}
			else if (getTimeInState() > 15)
			{
				CommonPlayerFuncts.setUV(2,2, sprite);
			}
			else if (getTimeInState() > 10)
			{
				CommonPlayerFuncts.setUV(1,2, sprite);
			}
			else
			{
				CommonPlayerFuncts.setUV(0,2, sprite);
			}
		}
		else
		{
			sprite.rotate(36);
		}
	}
}