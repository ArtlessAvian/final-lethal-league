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
	public Ball(PlayerInput input)
	{
		PhysicsComponent physicsC = new PhysicsComponent();
		this.add(physicsC);
//		StateComponent stateC = new StateComponent();
//		stateC.machine.current = new PlayerStandState(this);
//		this.add(stateC);
		SpriteComponent spriteC = new SpriteComponent(new Sprite(new Texture("grid.png")));
		spriteC.sprite.setSize(48, 48);
		this.add(spriteC);
	}

	// TODO
	public static class BallCollisionBehavior extends StageComponent.CollisionBehavior
	{
		@Override
		public void onTouchCeil(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{

		}

		@Override
		public void onTouchFloor(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{

		}

		@Override
		public void onTouchLeft(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{

		}

		@Override
		public void onTouchRight(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{

		}
	}
}
