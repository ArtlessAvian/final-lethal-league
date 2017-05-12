package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.ecs.components.*;
import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class GUIDrawSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;

	public GUIDrawSystem(Maineroni main, GameScreen game)
	{
		this.main = main;
		this.game = game;
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class).get());
		setProcessing(false);
	}

	float ballHitlagMax = 0;

	@Override
	public void update(float rollover)
	{
		main.batch.setProjectionMatrix(main.screenSpace.combined);

		for (Entity entity : entities)
		{
			SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);
			if (!spriteC.isScreenSpace) {continue;}

			// TODO:

		}

		HitlagComponent hitlagC = game.ball.getComponent(HitlagComponent.class);
		if (hitlagC.hitlag == 0) {ballHitlagMax = 0;}
		else if (ballHitlagMax == 0) {ballHitlagMax = hitlagC.hitlag;}

		main.font.draw(main.batch, hitlagC.hitlag / ballHitlagMax + "", 600, 30);

		main.font.draw(main.batch, game.ball.getComponent(PhysicsComponent.class).vel.len() / 60 + " units/frame", 600, 18);
	}
}
