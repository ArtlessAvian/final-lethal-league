package com.artlessavian.lethalleague.ecs.systems;


import com.artlessavian.lethalleague.ecs.components.HitlagComponent;
import com.artlessavian.lethalleague.ecs.components.StateComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class StateSystem extends IteratingSystem
{
	public StateSystem()
	{
		super(Family.all(StateComponent.class).get());
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
		if (hitlagC != null && hitlagC.hitlag > 0) {return;}

		StateComponent stateC = entity.getComponent(StateComponent.class);

		stateC.machine.run();
	}
}
