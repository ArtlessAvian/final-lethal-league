package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.OffsetRectangle;
import com.artlessavian.lethalleague.TimeLogger;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.StateComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DebugDrawSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;

	DrawSystem drawSystem;
	Sprite rectangle;
	Sprite dot;

	int show = 4;

	public DebugDrawSystem(Maineroni main, GameScreen game, DrawSystem drawSystem)
	{
		this.main = main;
		this.game = game;
		this.drawSystem = drawSystem;
		this.rectangle = new Sprite(new Texture("grid.png"));
		this.dot = new Sprite(new Texture("white.png"));
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
		if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSLASH))
		{
			show++;
		}

		main.batch.setProjectionMatrix(drawSystem.cam.combined);

		for (Entity entity : entities)
		{
			PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

			if (show % 2 == 1)
			{
				rectangle.setColor(Color.BLUE);
				rectangle.setSize(physicsC.collision.width, physicsC.collision.height);
				rectangle.setPosition(physicsC.collision.x, physicsC.collision.y);
				rectangle.draw(main.batch, 0.3f);
			}

			StateComponent stateC = entity.getComponent(StateComponent.class);
			if ((show/2) % 2 == 1 && stateC != null)
			{
				main.font.draw(main.batch, stateC.machine.current.getClass().getSimpleName(), physicsC.pos.x, physicsC.pos.y);
				main.font.draw(main.batch, stateC.machine.current.getTimeInState() + "", physicsC.pos.x, physicsC.pos.y - 12);
			}

			HitboxComponent hitboxC = entity.getComponent(HitboxComponent.class);
			if ((show/4) % 2 == 1 && hitboxC != null)
			{
				rectangle.setColor(Color.YELLOW);

				rectangle.setSize(hitboxC.hurtbox.width, hitboxC.hurtbox.height);
				rectangle.setPosition(hitboxC.hurtbox.x, hitboxC.hurtbox.y);
				rectangle.draw(main.batch, 0.3f);

				rectangle.setColor(Color.RED);
				for (OffsetRectangle r : hitboxC.hitboxes)
				{
					rectangle.setSize(r.width, r.height);
					rectangle.setPosition(r.x, r.y);
					rectangle.draw(main.batch, 0.3f);
				}
			}

			if ((show/8) % 2 == 1)
			{
				dot.setSize(5,5);
				dot.setColor(Color.PURPLE);
				dot.setCenter(physicsC.lastPos.x, physicsC.lastPos.y);
				dot.draw(main.batch);
				dot.setCenter(physicsC.pos.x, physicsC.pos.y);
				dot.draw(main.batch);
			}

			if ((show/16) % 2 == 1)
			{
				main.font.draw(main.batch, physicsC.vel.x + "", physicsC.pos.x, physicsC.pos.y + 12);
				main.font.draw(main.batch, physicsC.vel.y + "", physicsC.pos.x, physicsC.pos.y + 24);
				main.font.draw(main.batch, physicsC.pos.x + "", physicsC.pos.x, physicsC.pos.y + 36);
				main.font.draw(main.batch, physicsC.pos.y + "", physicsC.pos.x, physicsC.pos.y + 48);
			}
		}

		main.batch.setProjectionMatrix(main.screenSpace.combined);
		main.font.draw(main.batch, game.engineRuns + "", 6, 42);

		int i = 12;
		for (String key : TimeLogger.getKeys())
		{
			main.font.draw(main.batch, key, 100, i);
			main.font.draw(main.batch, Math.round(TimeLogger.getWorst(key)*1000)/1000f + "ms", 300, i);
			main.font.draw(main.batch, Math.round(TimeLogger.getCurrent(key)*1000)/1000f + "ms", 400, i);
			main.font.draw(main.batch, Math.round(TimeLogger.getAverage(key)*1000)/1000f + "ms", 500, i);
			i += 15;
		}
	}
}