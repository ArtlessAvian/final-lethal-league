package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;

public class MainAccessComponent implements Component
{
	Maineroni main;
	public GameScreen game;
	public Engine engine;

	public MainAccessComponent(GameScreen game)
	{
		main = game.main;
		this.game = game;
		engine = game.engine;
	}
}
