package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.OffsetRectangle;
import com.artlessavian.lethalleague.ecs.components.*;
import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.artlessavian.lethalleague.playerstates.PlayerSmashState;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
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
	protected void processEntity(Entity entity, float deltaTime)
	{
		BallComponent ballC = entity.getComponent(BallComponent.class);
		if (ballC.intangible > 0) {ballC.intangible--; return;}

		hurtboxes.clear();
		hitboxes.clear();
		for (Entity hitboxHaver : hitboxHavers)
		{
			HitlagComponent hitlagC2 = hitboxHaver.getComponent(HitlagComponent.class);
			if (hitlagC2 != null && hitlagC2.hitlag > 0) {continue;}
			HitboxComponent hitboxC2 = hitboxHaver.getComponent(HitboxComponent.class);
			if (hitboxC2.cannotHit.contains(entity)) {continue;}

			hurtboxes.put(hitboxC2.hurtbox, hitboxHaver);
			for (OffsetRectangle r : hitboxC2.hitboxes)
			{
				hitboxes.put(r, hitboxHaver);
			}
		}

		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		float displacement = physicsC.pos.dst(physicsC.lastPos);
		boolean hasCollided = false;
		for (float i = 0; i < displacement && !hasCollided; i += 24)
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
				entityHitsBall(hitboxes.get(hitbox), entity);
				physicsC.pos.set(x,y);
				return true;
			}
		}
		for (OffsetRectangle hurtbox : hurtboxes.keySet())
		{
			if (ballC.rect.overlaps(hurtbox))
			{
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
		e1HitboxC.behavior.onHit(entity, ball, isSmash);
		e2BallC.behavior.onGetHit(ball, entity, isSmash);
	}

	private void ballHitsEntity(Entity e1, Entity e2)
	{
//		HitboxComponent e1HitboxC = e1.getComponent(HitboxComponent.class);
//		BallComponent e2BallC = e2.getComponent(BallComponent.class);
//
//		e1HitboxC.behavior.onGetHit(e1, e2, false);
//		e2BallC.behavior.onHit(e2, e1, false);
	}
}
