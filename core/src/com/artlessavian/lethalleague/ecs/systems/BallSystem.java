package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.OffsetRectangle;

import com.artlessavian.lethalleague.ecs.components.*;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.artlessavian.lethalleague.playerstates.PlayerSmashState;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

import java.util.HashMap;

public class BallSystem extends IteratingSystem
{
	private ImmutableArray<Entity> hitboxHavers;

	public BallSystem()
	{
		super(Family.all(BallComponent.class).get());
	}

	public void addedToEngine(Engine engine)
	{
		super.addedToEngine(engine);
		hitboxHavers = engine.getEntitiesFor(Family.all(HitboxComponent.class).get());
	}

	HashMap<OffsetRectangle, Entity> hitboxes = new HashMap<OffsetRectangle, Entity>();
	HashMap<OffsetRectangle, Entity> hurtboxes = new HashMap<OffsetRectangle, Entity>();

	@Override
	public void update(float delta)
	{
		
		super.update(delta);
		;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		BallComponent ballC = entity.getComponent(BallComponent.class);
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		if (ballC.intangible > 0) {return;}

		hurtboxes.clear();
		hitboxes.clear();
		for (Entity hitboxHaver : hitboxHavers)
		{
			HitboxComponent hitboxC2 = hitboxHaver.getComponent(HitboxComponent.class);

			if (hitboxC2.intangible <= 0 && ballC.team != hitboxC2.team && ballC.team >= 0)
			{
				hurtboxes.put(hitboxC2.hurtbox, hitboxHaver);
			}
			if (!hitboxC2.cannotHit.contains(entity))
			{
				for (OffsetRectangle r : hitboxC2.hitboxes)
				{
					hitboxes.put(r, hitboxHaver);
				}
			}
		}

		float displacement = physicsC.pos.dst(physicsC.lastPos);
		boolean hasCollided = false;
		for (float i = 0; i < displacement && !hasCollided; i += ballC.precision)
		{
			hasCollided = doThing(i/displacement, physicsC, ballC, entity);
		}
		if (!hasCollided)
		{
			hasCollided = doThing(1, physicsC, ballC, entity);
		}

//		System.out.println(hasCollided);
	}

	private boolean doThing(float interpolation, PhysicsComponent physicsC, BallComponent ballC, Entity entity)
	{
		float x = interpolation * (physicsC.pos.x - physicsC.lastPos.x) + physicsC.lastPos.x;
		float y = interpolation * (physicsC.pos.y - physicsC.lastPos.y) + physicsC.lastPos.y;
		ballC.rect.setCenter(x, y + ballC.rect.height/2f);

		for (OffsetRectangle hitbox : hitboxes.keySet())
		{
			if (ballC.rect.overlaps(hitbox))
			{
				physicsC.pos.set(x,y);
				entityHitsBall(hitboxes.get(hitbox), entity);
				return true;
			}
		}
		for (OffsetRectangle hurtbox : hurtboxes.keySet())
		{
			if (ballC.rect.overlaps(hurtbox))
			{
				physicsC.pos.set(x,y);
				ballHitsEntity(hurtboxes.get(hurtbox), entity);
				return true;
			}
		}
		return false;
	}

	private void entityHitsBall(Entity entity, Entity ball)
	{
		HitboxComponent e1HitboxC = entity.getComponent(HitboxComponent.class);
		BallComponent e2BallC = ball.getComponent(BallComponent.class);

		boolean isSmash = false;
		if (entity instanceof Player)
		{
			StateComponent stateC = entity.getComponent(StateComponent.class);
			isSmash = stateC.machine.current.getClass() == PlayerSmashState.class;
		}

//				System.out.println("hi");
		e1HitboxC.behavior.onHit(entity, ball, isSmash, getEngine());
		e2BallC.behavior.onGetHit(ball, entity, isSmash, getEngine());
	}

	private void ballHitsEntity(Entity entity, Entity ball)
	{
		HitboxComponent e1HitboxC = entity.getComponent(HitboxComponent.class);
		BallComponent e2BallC = ball.getComponent(BallComponent.class);

		e1HitboxC.behavior.onGetHit(entity, ball, false, getEngine());
		e2BallC.behavior.onHit(ball, entity, false, getEngine());
	}
}
