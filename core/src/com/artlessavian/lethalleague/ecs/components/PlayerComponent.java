package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.PlayerInfo;
import com.badlogic.ashley.core.Component;

public class PlayerComponent implements Component
{
	public PlayerInfo playerInfo;

	public PlayerComponent(PlayerInfo playerInfo)
	{
		this.playerInfo = playerInfo;
	}
}
