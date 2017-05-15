package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.TimeLogger;
import com.artlessavian.lethalleague.TitleScreen;
import com.artlessavian.lethalleague.ecs.components.PlayerComponent;
import com.artlessavian.lethalleague.ecs.components.RemoveComponent;
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

	boolean isInbetweenRounds = false;
	int timerInbetweenRounds = 0;

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

	@Override
	public void update(float delta)
	{
		TimeLogger.logIn();

		if (isInbetweenRounds)
		{
			timerInbetweenRounds--;
			if (timerInbetweenRounds == 0)
			{
				if (isGameOver())
				{
					System.out.println("winnr!");
					main.setScreen(new TitleScreen(main));
				}
				else
				{
					System.out.println("respawning");
					if (game.p1.isDead())
					{
						game.engine.addEntity(game.p1.spawn());
					}
					if (game.p2.isDead())
					{
						game.engine.addEntity(game.p2.spawn());
					}
					game.engine.addEntity(game.ball.spawn());
					isInbetweenRounds = false;
				}
			}
		}
		else
		{
			if (isRoundOver())
			{
				isInbetweenRounds = true;
				timerInbetweenRounds = 100;

				System.out.println("ok");
				game.ball.instance.add(new RemoveComponent());
			}
		}

		TimeLogger.logOut("GameLogicSystem");
	}

	private boolean isGameOver()
	{
		if (game.isStocks)
		{
			return isGameOverStocks();
		}
		else
		{
			return isGameOverScore();
		}
	}

	private boolean isGameOverStocks()
	{
		int withStocks = 0;

		for (Entity entity : entities)
		{
			PlayerComponent playerC = entity.getComponent(PlayerComponent.class);
			if (playerC.playerInfo.stocks > 0)
			{
				withStocks++;
			}
		}

		return withStocks <= 1;
	}

	private boolean isGameOverScore()
	{
		for (Entity entity : entities)
		{
			PlayerComponent playerC = entity.getComponent(PlayerComponent.class);
			if (playerC.playerInfo.score >= 8)
			{
				return true;
			}
		}

		return false;
	}

	private boolean isRoundOver()
	{
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
				return false;
			}
		}
		return true;
	}
}
