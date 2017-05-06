package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.PlayerInput;
import com.badlogic.ashley.core.Component;

public class InputComponent implements Component
{
	public PlayerInput input;

	public InputComponent(PlayerInput input)
	{
		this.input = input;
	}
}
