package com.artlessavian.lethalleague;

import com.artlessavian.lethalleague.ecs.components.PlayerComponent;
import com.artlessavian.lethalleague.ecs.components.RemoveComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PlayerInfo
{
	Constructor playerConstructor;
	public PlayerInputContainer inputs;
	public int number;
	public int team;
	public int score = 0;
	int stocks = 8;

	Player instance;

	PlayerInfo(Class<? extends Player> playerClass, PlayerInputContainer inputs, int number, int team)
	{
		try
		{
			this.playerConstructor = playerClass.getConstructor(PlayerInfo.class);
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		this.inputs = inputs;
		this.team = team;
		this.number = number;
	}

	public Player spawn()
	{
		if (instance != null)
		{
			stocks--;
		}
		try
		{
			instance = (Player)playerConstructor.newInstance(this);
			return instance;
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}

		// if this happens, i will die internally
		return null;
	}

	public boolean isDead()
	{
		return instance.getComponent(RemoveComponent.class) != null;
	}
}
