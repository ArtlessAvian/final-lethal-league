package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.artlessavian.lethalleague.ecs.components.StateComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class DebugDrawSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;

	DrawSystem drawSystem;

	public DebugDrawSystem(Maineroni main, GameScreen game, DrawSystem drawSystem)
	{
		this.main = main;
		this.game = game;
		this.drawSystem = drawSystem;
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class, StateComponent.class).get());
		setProcessing(false);
	}

	@Override
	public void update(float rollover)
	{
		main.batch.setProjectionMatrix(drawSystem.cam.combined);
		game.stage.draw(main.batch);

		for (Entity entity : entities)
		{
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

			StateComponent stateC = entity.getComponent(StateComponent.class);
			if (stateC != null)
			{
				main.font.draw(main.batch, stateC.machine.current.getClass().getSimpleName(), physicsC.pos.x, physicsC.pos.y);
			}
			main.font.draw(main.batch, physicsC.vel.x + "", physicsC.pos.x, physicsC.pos.y + 12);
			main.font.draw(main.batch, physicsC.vel.y + "", physicsC.pos.x, physicsC.pos.y + 24);
			main.font.draw(main.batch, physicsC.pos.x + "", physicsC.pos.x, physicsC.pos.y + 36);
			main.font.draw(main.batch, physicsC.pos.y + "", physicsC.pos.x, physicsC.pos.y + 48);
		}
	}
}