package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.TimeLogger;
import com.artlessavian.lethalleague.ecs.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

public class RemoveSystem extends EntitySystem
{
	ImmutableArray<Entity> entities;
	ArrayList<Entity> tempStorage;

	public void addedToEngine(Engine engine)
	{
		tempStorage = new ArrayList<Entity>();
		entities = engine.getEntitiesFor(Family.all(RemoveComponent.class).get());
	}

	@Override
	public void update(float rollover)
	{
		if (entities.size() == 0) {return;}

		TimeLogger.logIn();

		tempStorage.clear();
		for (Entity entity : entities)
		{
			RemoveComponent removeC = entity.getComponent(RemoveComponent.class);
			if (removeC.removeTimer == 0)
			{
				tempStorage.add(entity);
			}
		}

		for (Entity entity : tempStorage)
		{
			this.getEngine().removeEntity(entity);
		}
		tempStorage.clear();

		TimeLogger.logOut("RemoveSystem");
	}
}
