package com.artlessavian.lethalleague;

import com.artlessavian.lethalleague.ecs.components.PlayerComponent;
import com.artlessavian.lethalleague.ecs.components.RemoveComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.badlogic.gdx.graphics.Texture;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PlayerInfo
{
	public Texture texture;

	// Constructor playerConstructor;
	public PlayerInputContainer inputs;
	public int number;
	public int team;
	public int score = 0;
	public int stocks = 8;

	Player instance;

	PlayerInfo(Object playerClass, PlayerInputContainer inputs, int number, int team)
	{
		// try
		// {
		// 	this.playerConstructor = playerClass.getConstructor(PlayerInfo.class);
		// }
		// catch (NoSuchMethodException e)
		// {
		// 	e.printStackTrace();
		// }
		this.inputs = inputs;
		this.team = team;
		this.number = number;

		texture = new Texture("spritesheet test.png");
	}

	public Player spawn()
	{
		instance = new Player(this);

		// try
		// {
			
		// 	instance = (Player)playerConstructor.newInstance(this);
		// 	;
			return instance;
		// }
		// catch (IllegalAccessException e)
		// {
		// 	e.printStackTrace();
		// }
		// catch (InstantiationException e)
		// {
		// 	e.printStackTrace();
		// }
		// catch (InvocationTargetException e)
		// {
		// 	e.printStackTrace();
		// }

		// // if this happens, i will die internally
		// return null;
	}

	public boolean isDead()
	{
		return instance.getComponent(RemoveComponent.class) != null;
	}
}
