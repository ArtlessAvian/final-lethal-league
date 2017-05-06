package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.PlayerInput;
import com.artlessavian.lethalleague.PlayerInputContainer;
import com.badlogic.ashley.core.Component;

public class InputComponent implements Component
{
	public PlayerInputContainer input;

	public InputComponent(PlayerInputContainer input)
	{
		this.input = input;
	}
}
