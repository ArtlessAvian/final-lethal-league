package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerCrouchState extends State
{
	Player player;
	private boolean antiSwingSpam;

	public PlayerCrouchState(Player player)
	{
		this.player = player;
	}

	@Override
	public void exit()
	{
		// TODO
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
		physicsC.collision.height = 144;
		HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
		hitboxC.hurtbox.setHeight(144);
		SpriteComponent spriteC = player.getComponent(SpriteComponent.class);
		spriteC.sprite.setSize(spriteC.sprite.getWidth(), 144);
	}

	@Override
	public void enter()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
		physicsC.collision.height = 50;
		HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
		hitboxC.hurtbox.setHeight(50);
//		SpriteComponent spriteC = player.getComponent(SpriteComponent.class);
//		spriteC.sprite.setSize(spriteC.sprite.getWidth(), 50);

		if (player.input.swingPressed)
		{
			antiSwingSpam = true;
		}
	}

	@Override
	public boolean changeStateMaybe()
	{
		if (!player.input.downPressed)
		{
		    sm.gotoState(PlayerStandState.class);
		    return true;
		}
		else if (player.input.jumpPressed)
		{
			PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
			sm.gotoState(PlayerJumpState.class);
			physicsC.vel.y = player.jumpVelocity;
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

		CommonPlayerFuncts.changeDirection(player, physicsC);
		CommonPlayerFuncts.friction(player, physicsC);
		// TODO

		if (antiSwingSpam && !player.input.swingPressed) {antiSwingSpam = false;}
	}

	@Override
	public void editSprite(Sprite sprite)
	{
		SpriteComponent spriteC = player.getComponent(SpriteComponent.class);

		if (spriteC.usingTestSpriteSheet)
		{
			CommonPlayerFuncts.setUV(1,0, sprite);
		}
		else
		{
			sprite.setSize(sprite.getWidth(), 50);
		}
	}
}
