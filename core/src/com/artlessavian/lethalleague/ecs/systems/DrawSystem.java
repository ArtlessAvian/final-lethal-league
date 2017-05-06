package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class DrawSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;

	private OrthographicCamera cam;
	private Texture map;

	public DrawSystem(Maineroni main, GameScreen game)
	{
		this.main = main;
		this.game = game;
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		this.cam = new OrthographicCamera(1280, 720);
		cam.translate(0, cam.viewportHeight / 2f);
		cam.update();

		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class, PhysicsComponent.class).get());
		setProcessing(false);
	}

	@Override
	public void update(float rollover)
	{
		main.batch.setProjectionMatrix(cam.combined);
		game.stage.draw(main.batch);

		for (Entity entity : entities)
		{
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
			SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);

			spriteC.sprite.setCenter(physicsC.pos.x, physicsC.pos.y + spriteC.sprite.getHeight() / 2);
			spriteC.sprite.setFlip(physicsC.facingLeft, false);
			spriteC.sprite.draw(main.batch);
		}
	}
}