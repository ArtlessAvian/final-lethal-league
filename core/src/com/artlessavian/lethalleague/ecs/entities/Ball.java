package com.artlessavian.lethalleague.ecs.entities;

import com.artlessavian.lethalleague.PlayerInput;
import com.artlessavian.lethalleague.Stage;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.artlessavian.lethalleague.ecs.components.StageComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ball extends Entity
{
	public Ball()
	{
		PhysicsComponent physicsC = new PhysicsComponent();
		physicsC.pos.y = 300;
		physicsC.vel.x = -300;
		physicsC.vel.y = -500;
		physicsC.collision.setSize(48, 48);
		this.add(physicsC);
//		StateComponent stateC = new StateComponent();
//		stateC.machine.current = new PlayerStandState(this);
//		this.add(stateC);
		SpriteComponent spriteC = new SpriteComponent(new Sprite(new Texture("grid.png")));
		spriteC.sprite.setSize(48, 48);
		this.add(spriteC);
		StageComponent stageC = new StageComponent(new BallCollisionBehavior());
		this.add(stageC);
	}

	// TODO
	public static class BallCollisionBehavior extends StageComponent.CollisionBehavior
	{
		@Override
		public void onTouchCeil(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			float deltaX = physicsC.pos.x - physicsC.lastPos.x;
			float deltaY = physicsC.pos.y - physicsC.lastPos.y;
			float newY = stage.bounds.y + stage.bounds.height - physicsC.collision.height;
			float newX = physicsC.lastPos.x + deltaX * (newY - physicsC.lastPos.y) / deltaY;

			physicsC.vel.y *= -1;

			physicsC.pos.set(newX, newY);
		}

		@Override
		public void onTouchFloor(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			float deltaX = physicsC.pos.x - physicsC.lastPos.x;
			float deltaY = physicsC.pos.y - physicsC.lastPos.y;
			float newY = stage.bounds.y;
			float newX = physicsC.lastPos.x + deltaX * (newY - physicsC.lastPos.y) / deltaY;

			physicsC.vel.y *= -1;

			physicsC.pos.set(newX, newY);
		}

		@Override
		public void onTouchLeft(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			float deltaX = physicsC.pos.x - physicsC.lastPos.x;
			float deltaY = physicsC.pos.y - physicsC.lastPos.y;
			float newX = stage.bounds.x + physicsC.collision.width/2;
			float newY = physicsC.lastPos.y + deltaY * (newX - physicsC.lastPos.x) / deltaX;

			physicsC.vel.x *= -1;

			physicsC.pos.set(newX, newY);
		}

		@Override
		public void onTouchRight(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			float deltaX = physicsC.pos.x - physicsC.lastPos.x;
			float deltaY = physicsC.pos.y - physicsC.lastPos.y;
			float newX = stage.bounds.x + stage.bounds.width - physicsC.collision.width/2;
			float newY = physicsC.lastPos.y + deltaY * (newX - physicsC.lastPos.x) / deltaX;

			physicsC.vel.x *= -1;

			physicsC.pos.set(newX, newY);
		}
	}
}
