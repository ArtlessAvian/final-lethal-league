package com.artlessavian.lethalleague.ecs.systems;


import com.artlessavian.lethalleague.ecs.components.BallComponent;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.HitlagComponent;
import com.artlessavian.lethalleague.ecs.components.RemoveComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class TimersSystem extends IteratingSystem
{
	public TimersSystem()
	{
		super(Family.one(
			BallComponent.class,
			HitlagComponent.class,
			RemoveComponent.class,
			HitboxComponent.class
		).get());
	}

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
		HitlagComponent hitlagC = entity.getComponent(HitlagComponent.class);
		HitboxComponent hitboxC = entity.getComponent(HitboxComponent.class);
		RemoveComponent removeC = entity.getComponent(RemoveComponent.class);

		if (ballC != null && ballC.intangible > 0) {ballC.intangible--;}
		if (hitboxC != null && hitboxC.intangible > 0) {hitboxC.intangible--;}
		if (hitlagC != null && hitlagC.hitlag > 0) {hitlagC.hitlag--;}
		if (removeC != null && removeC.removeTimer > 0) {removeC.removeTimer--;}
	}
}
