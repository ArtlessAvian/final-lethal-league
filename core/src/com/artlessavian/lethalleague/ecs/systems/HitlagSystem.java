package com.artlessavian.lethalleague.ecs.systems;


import com.artlessavian.lethalleague.ecs.components.HitlagComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class HitlagSystem extends IteratingSystem
{
	public HitlagSystem()
	{
		super(Family.all(HitlagComponent.class).get());
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
		HitlagComponent hitlagC = entity.getComponent(HitlagComponent.class);
		if (hitlagC.hitlag > 0)
		{
			hitlagC.hitlag--;
		}
	}
}
