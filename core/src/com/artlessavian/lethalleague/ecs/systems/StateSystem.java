package com.artlessavian.lethalleague.ecs.systems;

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

	/**
	 * Accelerates a player based on their associated inputs.
	 *
	 * @param entity
	 * @param deltaTime
	 */
	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		StateComponent stateC = entity.getComponent(StateComponent.class);

		stateC.machine.run();
	}
}
