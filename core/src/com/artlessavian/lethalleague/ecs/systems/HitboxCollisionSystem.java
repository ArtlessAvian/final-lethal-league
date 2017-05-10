package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.OffsetRectangle;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.artlessavian.lethalleague.ecs.components.StateComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.sun.scenario.effect.Offset;

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
				r.setPosition(physicsC.pos.x, physicsC.pos.y);
			}

			for (OffsetRectangle r : hitboxC.hurtboxes)
			{
				r.setPosition(physicsC.pos.x, physicsC.pos.y);
			}
		}

		for (Entity e1 : entities)
		{
			for (Entity e2 : entities2)
			{
				if (e1 == e2) {continue;}

				HitboxComponent e1Hitboxes = e1.getComponent(HitboxComponent.class);
				HitboxComponent e2Hurtboxes = e2.getComponent(HitboxComponent.class);

				for (OffsetRectangle e1Hitbox : e1Hitboxes.hitboxes)
				{
					for (OffsetRectangle e2Hurtbox : e2Hurtboxes.hurtboxes)
					{
						if (e1Hitbox.overlaps(e2Hurtbox))
						{
							System.out.println("hi");
//							e1Hitboxes.behavior.onHit(e1, e2);
//							e2Hurtboxes.behavior.onGetHit(e2, e1);
						}
					}
				}
			}
		}
	}
}
