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
		SpriteComponent spriteC = player.getComponent(SpriteComponent.class);
		spriteC.sprite.setSize(spriteC.sprite.getWidth(), 50);
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
		    sm.gotoState(PlayerJumpState.class);
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
	}

	@Override
	public void editSprite(Sprite sprite)
	{
	}
}
