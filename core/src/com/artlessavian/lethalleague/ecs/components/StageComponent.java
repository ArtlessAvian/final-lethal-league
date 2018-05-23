package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.Stage;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * What is this janky class
 */
public class StageComponent implements Component
{
	public static abstract class CollisionBehavior
	{
		public abstract void onTouchCeil(Stage stage, PhysicsComponent physicsC, Entity thisEntity);

		public abstract void onTouchFloor(Stage stage, PhysicsComponent physicsC, Entity thisEntity);

		public abstract void onTouchLeft(Stage stage, PhysicsComponent physicsC, Entity thisEntity);

		public abstract void onTouchRight(Stage stage, PhysicsComponent physicsC, Entity thisEntity);
	}

	public CollisionBehavior behavior;

	public StageComponent(CollisionBehavior behavior)
	{
		this.behavior = behavior;
	}
}
