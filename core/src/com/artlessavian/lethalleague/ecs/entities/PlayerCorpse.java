package com.artlessavian.lethalleague.ecs.entities;

import com.artlessavian.lethalleague.ecs.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerCorpse extends Entity
{
	public PlayerCorpse(Player p)
	{
		Sprite s = new Sprite(p.getComponent(PlayerComponent.class).playerInfo.texture);
		s.setSize(144,144);
		SpriteComponent spriteC = new SpriteComponent(s);
		spriteC.sprite.setV(3/8f);
		spriteC.sprite.setV2(4/8f);
		spriteC.sprite.setU(0/8f);
		spriteC.sprite.setU2(1/8f);
		spriteC.passiveSpin = 5;
		this.add(spriteC);

		PhysicsComponent physicsC = new PhysicsComponent();
		physicsC.passiveGravity = 2000;
		physicsC.vel.y = 900;
		physicsC.vel.setAngle((float)(Math.random() * 90) + 45);
		physicsC.pos.set(p.getComponent(PhysicsComponent.class).pos);
		this.add(physicsC);

		HitlagComponent hitlagC = new HitlagComponent();
		hitlagC.hitlag = 15;
		this.add(hitlagC);

		RemoveComponent removeC = new RemoveComponent();
		removeC.removeTimer = 1000;
		this.add(removeC);

		HitboxComponent hitboxC = new HitboxComponent(new CorpseHitBehavior(), -1);
//		hitboxC.hurtbox
		this.add(hitboxC);
	}

	private static class CorpseHitBehavior implements HitboxComponent.HitBehavior
	{
		@Override
		public void onHit(Entity thisEntity, Entity other, boolean isSmash, Engine engine)
		{
			// lol why
		}

		@Override
		public void onGetHit(Entity thisEntity, Entity other, boolean isSmash, Engine engine)
		{
			if (other instanceof Player)
			{
				PhysicsComponent physicsC = thisEntity.getComponent(PhysicsComponent.class);
				HitlagComponent hitlagC = thisEntity.getComponent(HitlagComponent.class);
//				HitlagComponent otherHitlagC = other.getComponent(HitlagComponent.class);
				SpriteComponent spriteC = thisEntity.getComponent(SpriteComponent.class);
//				physicsC.passiveGravity = 0;
				if (!isSmash)
				{
					spriteC.passiveSpin = 20;
//					hitlagC.hitlag = 10;
//					other.hitlag = 10;
					physicsC.vel.setLength(600);
					physicsC.vel.setAngle(((Player)other).getAngle());
				}
				else
				{
					spriteC.passiveSpin = 200;
					hitlagC.hitlag = 10;
					physicsC.vel.setLength(1000);
					physicsC.vel.setAngle(((Player)other).getSmashAngle());
				}
			}
		}
	}
}
