package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.StateMachine;
import com.badlogic.ashley.core.Component;

public class StateComponent implements Component
{
	public StateMachine machine;

	public StateComponent()
	{
		this.machine = new StateMachine();
	}
}
