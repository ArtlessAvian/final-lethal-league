package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.Stage;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.StageComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class PhysicsSystem extends IteratingSystem
{
	private Stage stage;

	public PhysicsSystem(Stage stage)
	{
		super(Family.all(PhysicsComponent.class).get());
		this.stage = stage;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

		physicsC.lastPos.set(physicsC.pos);

		physicsC.vel.y -= physicsC.passiveGravity * 1 / 60f;
		physicsC.pos.x += physicsC.vel.x * 1 / 60f;
		physicsC.pos.y += physicsC.vel.y * 1 / 60f;

		StageComponent stageComponent = entity.getComponent(StageComponent.class);
		if (stageComponent != null)
		{
			if (physicsC.pos.y > stage.bounds.y + stage.bounds.height)
			{
				stageComponent.behavior.onTouchCeil(stage, physicsC, entity);
			}
			if (physicsC.pos.y < stage.bounds.y)
			{
				stageComponent.behavior.onTouchFloor(stage, physicsC, entity);
			}
			if (physicsC.pos.x - physicsC.collision.width / 2f < stage.bounds.x)
			{
				stageComponent.behavior.onTouchLeft(stage, physicsC, entity);
			}
			if (physicsC.pos.x + physicsC.collision.width / 2f > stage.bounds.x + stage.bounds.width)
			{
				stageComponent.behavior.onTouchRight(stage, physicsC, entity);
			}
		}


	}
}
