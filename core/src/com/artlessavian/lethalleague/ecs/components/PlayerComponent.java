package com.artlessavian.lethalleague.ecs.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent implements Component
{
	public int number;
	public int team;
	int score;

	public PlayerComponent(int number, int team)
	{
		this.number = number;
		this.team = team;
	}
}
