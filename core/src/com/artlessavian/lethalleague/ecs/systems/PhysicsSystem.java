package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.Stage;

import com.artlessavian.lethalleague.ecs.components.BallComponent;
import com.artlessavian.lethalleague.ecs.components.HitlagComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.StageComponent;
import com.artlessavian.lethalleague.ecs.entities.Ball;
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
	public void update(float delta)
	{
		
		super.update(delta);
		;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
		physicsC.lastPos.set(physicsC.pos);

		HitlagComponent hitlagC = entity.getComponent(HitlagComponent.class);
		if (hitlagC != null && hitlagC.hitlag > 0) {return;}

		BallComponent ballC = entity.getComponent(BallComponent.class);
		if (ballC != null && ballC.getAngle && hitlagC.hitlag == 0)
		{
			ballC.getAngle = false;

			//TODO: Should balls be responsible for their own angles?
			if (ballC.wasSmashed)
			{
				physicsC.vel.setAngle(ballC.lastHit.getSmashAngle());
			}
			else
			{
				physicsC.vel.setAngle(ballC.lastHit.getAngle());
			}
		}

		if (!physicsC.grounded)
		{
			physicsC.vel.y -= physicsC.passiveGravity * 1 / 60f;
		}
		physicsC.pos.x += physicsC.vel.x * 1 / 60f;
		physicsC.pos.y += physicsC.vel.y * 1 / 60f;

		physicsC.collision.setPosition(physicsC.pos.x - physicsC.collision.width/2, physicsC.pos.y);

		StageComponent stageC = entity.getComponent(StageComponent.class);
		if (stageC != null)
		{
			float timeLR = (physicsC.vel.x < 0) ?
				(stage.bounds.x + physicsC.collision.width/2 - physicsC.lastPos.x) / physicsC.vel.x :
				(stage.bounds.x + stage.bounds.width - physicsC.collision.width/2 - physicsC.lastPos.x) / physicsC.vel.x;
			float timeUD = (physicsC.vel.y < 0) ?
				(stage.bounds.y - physicsC.lastPos.y) / physicsC.vel.y :
				(stage.bounds.y + stage.bounds.height - physicsC.collision.height - physicsC.lastPos.y) / physicsC.vel.y;

//			if (entity instanceof Ball)
//			System.out.println(timeLR * 60 + " " + timeUD * 60);

			if (timeLR < timeUD)
			{
//				if (entity instanceof Ball)
//				System.out.println("x first");
				leftRightCollision(stage, entity, physicsC, stageC);
//				ceilFloorCollision(stage, entity, physicsC, stageC);
			}
			else
			{
//				if (entity instanceof Ball)
//				System.out.println("y first");
				ceilFloorCollision(stage, entity, physicsC, stageC);
//				leftRightCollision(stage, entity, physicsC, stageC);
			}
		}

//		physicsC.facingLeft = physicsC.pos.x < physicsC.lastPos.x;
	}

	private void leftRightCollision(Stage stage, Entity entity, PhysicsComponent physicsC, StageComponent stageC)
	{
		if (physicsC.collision.x < stage.bounds.x)
		{
			stageC.behavior.onTouchLeft(stage, physicsC, entity);
		}
		if (physicsC.collision.x + physicsC.collision.width > stage.bounds.x + stage.bounds.width)
		{
			stageC.behavior.onTouchRight(stage, physicsC, entity);
		}
	}

	private void ceilFloorCollision(Stage stage, Entity entity, PhysicsComponent physicsC, StageComponent stageC)
	{
		if (physicsC.collision.y + physicsC.collision.height > stage.bounds.y + stage.bounds.height)
		{
			stageC.behavior.onTouchCeil(stage, physicsC, entity);
		}
		if (physicsC.collision.y < stage.bounds.y)
		{
			stageC.behavior.onTouchFloor(stage, physicsC, entity);
		}
	}
}
