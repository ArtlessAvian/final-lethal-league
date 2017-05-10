package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.OffsetRectangle;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.artlessavian.lethalleague.ecs.components.StateComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class DebugDrawSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;

	DrawSystem drawSystem;
	Sprite rectangle;

	public DebugDrawSystem(Maineroni main, GameScreen game, DrawSystem drawSystem)
	{
		this.main = main;
		this.game = game;
		this.drawSystem = drawSystem;
		this.rectangle = new Sprite(new Texture("grid.png"));
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class).get());
		setProcessing(false);
	}

	@Override
	public void update(float rollover)
	{
		main.batch.setProjectionMatrix(drawSystem.cam.combined);

		for (Entity entity : entities)
		{
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
			rectangle.setColor(Color.BLUE);
			rectangle.setSize(physicsC.collision.width, physicsC.collision.height);
			rectangle.setPosition(physicsC.collision.x, physicsC.collision.y);
			rectangle.draw(main.batch, 0.3f);

			StateComponent stateC = entity.getComponent(StateComponent.class);
			if (stateC != null)
			{
				main.font.draw(main.batch, stateC.machine.current.getClass().getSimpleName(), physicsC.pos.x, physicsC.pos.y);
			}

			HitboxComponent hitboxC = entity.getComponent(HitboxComponent.class);
			if (hitboxC != null)
			{
				rectangle.setColor(Color.YELLOW);
				for (OffsetRectangle r : hitboxC.hurtboxes)
				{
					rectangle.setSize(r.width, r.height);
					rectangle.setPosition(r.x, r.y);
					rectangle.draw(main.batch, 0.3f);
				}
				rectangle.setColor(Color.RED);
				for (OffsetRectangle r : hitboxC.hitboxes)
				{
					rectangle.setSize(r.width, r.height);
					rectangle.setPosition(r.x, r.y);
					rectangle.draw(main.batch, 0.3f);
				}
			}

			main.font.draw(main.batch, physicsC.vel.x + "", physicsC.pos.x, physicsC.pos.y + 12);
			main.font.draw(main.batch, physicsC.vel.y + "", physicsC.pos.x, physicsC.pos.y + 24);
			main.font.draw(main.batch, physicsC.pos.x + "", physicsC.pos.x, physicsC.pos.y + 36);
			main.font.draw(main.batch, physicsC.pos.y + "", physicsC.pos.x, physicsC.pos.y + 48);
		}
	}
}