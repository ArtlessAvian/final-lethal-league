package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerSwingState extends State
{
	Player player;
	private int swingThrough;
	private boolean hadHit;

	public PlayerSwingState(Player player)
	{
		this.player = player;
	}

	@Override
	public void exit()
	{
		// TODO
		HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
		hitboxC.hitboxes.remove(player.swingBox);

		player.ball = null;
	}

	@Override
	public void enter()
	{
		// TODO
		HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
		hitboxC.hitboxes.add(player.swingBox);

		hitboxC.cannotHit.clear();
		swingThrough = -1;

		player.ball = null;
		hadHit = false;
	}

	@Override
	public boolean changeStateMaybe()
	{
		// TODO
		if ((getTimeInState() >= 30 && swingThrough == -1) || swingThrough == 0)
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

		// TODO: Revise?
//		if (!hadHit && getTimeInState() > 20)
//		{
//			HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
//			hitboxC.hitboxes.remove(player.swingBox);
//		}
		if (swingThrough > 0) {swingThrough--;}

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
//			PhysicsComponent physicsCBall = player.ball.getComponent(PhysicsComponent.class);
//			if (player.input.upPressed)
//			{
//				physicsCBall.vel.setAngle(player.upAngle);
//			}
//			else if (player.input.downPressed)
//			{
//				physicsCBall.vel.setAngle(player.downAngle);
//			}
//			else
//			{
//				physicsCBall.vel.setAngle(player.straightAngle);
//			}
//
//			if (physicsC.facingLeft) {physicsCBall.vel.x *= -1;}

			player.ball = null;
			swingThrough = 10;
			hadHit = true;
		}
	}

	@Override
	public void editSprite(Sprite sprite)
	{
		SpriteComponent spriteC = player.getComponent(SpriteComponent.class);

		if (spriteC.usingTestSpriteSheet)
		{
			if (hadHit || getTimeInState() > 20)
			{
				CommonPlayerFuncts.setUV(7,0, sprite);
			}
			else if (player.ball != null || getTimeInState() > 15)
			{
				CommonPlayerFuncts.setUV(6,0, sprite);
			}
			else if (getTimeInState() > 13)
			{
				CommonPlayerFuncts.setUV(5,0, sprite);
			}
			else
			{
				CommonPlayerFuncts.setUV(4,0, sprite);
			}
		}
		else
		{
			sprite.rotate(12);
		}
	}
}
