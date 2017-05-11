package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.OffsetRectangle;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.HitlagComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class HitboxCollisionSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> entities2;

	public HitboxCollisionSystem()
	{
	}

	public void addedToEngine(Engine engine)
	{
		// why
		entities = engine.getEntitiesFor(Family.all(HitboxComponent.class).get());
		entities2 = engine.getEntitiesFor(Family.all(HitboxComponent.class, PhysicsComponent.class).get());
	}

	@Override
	public void update(float rollover)
	{
		for (Entity e : entities2)
		{
			HitboxComponent hitboxC = e.getComponent(HitboxComponent.class);
			PhysicsComponent physicsC = e.getComponent(PhysicsComponent.class);

			for (OffsetRectangle r : hitboxC.hitboxes)
			{
				r.setFlip(physicsC.facingLeft);
				r.setPosition(physicsC.pos.x, physicsC.pos.y);
			}

			hitboxC.hurtbox.setFlip(physicsC.facingLeft);
			hitboxC.hurtbox.setPosition(physicsC.pos.x, physicsC.pos.y);
		}

		for (Entity e1 : entities)
		{
			HitlagComponent hitlagC = e1.getComponent(HitlagComponent.class);
			if (hitlagC != null && hitlagC.hitlag > 0) {continue;}

			for (Entity e2 : entities2)
			{
				if (e1 == e2) {continue;}

				HitlagComponent hitlagC2 = e2.getComponent(HitlagComponent.class);
				if (hitlagC != null && hitlagC.hitlag != 0) {continue;}

				HitboxComponent e1Hitboxes = e1.getComponent(HitboxComponent.class);

				if (e1Hitboxes.cannotHit.contains(e2)) {continue;}

				HitboxComponent e2Hurtbox = e2.getComponent(HitboxComponent.class);

				for (OffsetRectangle e1Hitbox : e1Hitboxes.hitboxes)
				{
					if (e1Hitbox.overlaps(e2Hurtbox.hurtbox))
					{
						System.out.println("hi");
						e1Hitboxes.behavior.onHit(e1, e2);
						e2Hurtbox.behavior.onGetHit(e2, e1);
						break;
					}
				}
			}
		}
	}
}
