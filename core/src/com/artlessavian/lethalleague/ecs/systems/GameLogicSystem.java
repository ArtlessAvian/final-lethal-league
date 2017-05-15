package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.TimeLogger;
import com.artlessavian.lethalleague.ecs.components.PlayerComponent;
import com.artlessavian.lethalleague.ecs.components.RemoveComponent;
import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class GameLogicSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;
	boolean isStocks; // otherwise its score

	public GameLogicSystem(Maineroni main, GameScreen game, boolean stocks)
	{
		this.main = main;
		this.game = game;
		this.isStocks = stocks;
	}

	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
	}

	int shouldRespawn = -1;

	@Override
	public void update(float delta)
	{
		TimeLogger.logIn();

		checkRespawn();

		TimeLogger.logOut("GameLogicSystem");
	}

	private void checkRespawn()
	{
		if (shouldRespawn == -1)
		{
			shouldRespawn = 100;

			int team = -1;
			for (Entity entity : entities)
			{
				PlayerComponent playerC = entity.getComponent(PlayerComponent.class);
				if (team == -1)
				{
					team = playerC.playerInfo.team;
				}
				else if (team != playerC.playerInfo.team)
				{
					shouldRespawn = -1;
				}
			}

			if (shouldRespawn != -1)
			{
				System.out.println("ok");
				game.ball.instance.add(new RemoveComponent());
			}
		}
		else if (shouldRespawn == 0)
		{
			shouldRespawn = -1;
			if (game.p1.isDead())
			{
				game.engine.addEntity(game.p1.spawn());
			}
			if (game.p2.isDead())
			{
				game.engine.addEntity(game.p2.spawn());
			}
			game.engine.addEntity(game.ball.spawn());
		}
		else
		{
			shouldRespawn--;
		}
	}
}
