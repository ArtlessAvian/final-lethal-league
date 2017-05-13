package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.TimeLogger;
import com.artlessavian.lethalleague.ecs.components.*;
import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class DrawSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;

	OrthographicCamera cam;
	private Texture map;

	public float screenShakeTime = 0;
	public float screenShakeAmount = 0;
	public float screenShakeMultiplier = 1f;

	public DrawSystem(Maineroni main, GameScreen game)
	{
		this.main = main;
		this.game = game;
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		this.cam = new OrthographicCamera(1280, 720);
		cam.position.x = 0;
		cam.position.y = cam.viewportHeight / 2f;
		cam.update();

		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class).get());
		setProcessing(false);
	}

	public void doScreenShake(float time, float strength)
	{
		screenShakeTime = time;
		screenShakeAmount = strength;
	}

	@Override
	public void update(float rollover)
	{
		TimeLogger.logIn();

		cam.position.x = 0;
		cam.position.y = cam.viewportHeight / 2f;
		if (screenShakeTime > 0)
		{
			cam.translate(
				(float)(Math.random()*2-1) * screenShakeAmount * screenShakeMultiplier,
				(float)(Math.random()*2-1) * screenShakeAmount * screenShakeMultiplier
			);
			screenShakeTime--;
		}
		cam.update();

		main.batch.setProjectionMatrix(cam.combined);
		game.stage.draw(main.batch);

		for (Entity entity : entities)
		{
			HitboxComponent hitboxC = entity.getComponent(HitboxComponent.class);
			BallComponent ballC = entity.getComponent(BallComponent.class);
			if (ballC != null && ballC.intangible > 0 ||
				hitboxC != null && hitboxC.intangible > 0)
			{
				if (Gdx.graphics.getFrameId() / 2 % 2 == 0) {continue;}
			}

			SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);
			if (spriteC.isScreenSpace) {continue;}

			StateComponent stateC = entity.getComponent(StateComponent.class);
			if (stateC != null)
			{
				// TODO: Change sprite based on sprite id
				stateC.machine.current.editSprite(spriteC.sprite);
			}

			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
			if (ballC != null && ballC.intangible == 0)
			{
				float displacement = physicsC.pos.dst(physicsC.lastPos);
				for (float i = 0; i < displacement; i += ballC.precision)
				{
					float x = i/displacement * (physicsC.pos.x - physicsC.lastPos.x) + physicsC.lastPos.x;
					float y = i/displacement * (physicsC.pos.y - physicsC.lastPos.y) + physicsC.lastPos.y;
					spriteC.sprite.setCenter(x, y + spriteC.sprite.getHeight() / 2);
					spriteC.sprite.draw(main.batch, 0.3f);
				}
			}

			if (physicsC != null)
			{
				spriteC.sprite.setFlip(physicsC.facingLeft, false);

//				spriteC.sprite.setX(physicsC.pos.x + (physicsC.pos.x - physicsC.lastPos.x) * rollover);
//				spriteC.sprite.setX(spriteC.sprite.getX() - spriteC.sprite.getWidth()/2f);
//				spriteC.sprite.setY(physicsC.pos.y + (physicsC.pos.y - physicsC.lastPos.y) * rollover);
//				spriteC.sprite.draw(main.batch);

				spriteC.sprite.setCenter(physicsC.pos.x, physicsC.pos.y + spriteC.sprite.getHeight() / 2);
			}

			spriteC.sprite.draw(main.batch);
		}

		TimeLogger.logOut("DrawSystem");
	}
}