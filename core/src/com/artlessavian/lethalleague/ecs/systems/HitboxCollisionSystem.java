package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
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
import com.badlogic.gdx.math.Rectangle;

public class HitboxCollisionSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;

	public HitboxCollisionSystem(Maineroni main, GameScreen game)
	{
		this.main = main;
		this.game = game;
	}

	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(HitboxComponent.class).get());
		setProcessing(false);
	}

	@Override
	public void update(float rollover)
	{
		for (Entity e1 : entities)
		{
			HitboxComponent e1Hitboxes = e1.getComponent(HitboxComponent.class);

			for (Rectangle e1Hitbox : e1Hitboxes.hitboxes)
			{
				for (Entity e2 : entities)
				{
					if (e1 == e2) {continue;}

					HitboxComponent e2Hurtboxes = e1.getComponent(HitboxComponent.class);

					for (Rectangle e2Hurtbox : e2Hurtboxes.hurtboxes)
					{
						if (e1Hitbox.overlaps(e2Hurtbox))
						{

						}
					}
				}
			}
		}
	}
}
