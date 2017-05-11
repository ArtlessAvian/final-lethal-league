package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.ecs.systems.DrawSystem;
import com.badlogic.ashley.core.Component;

public class BallComponent implements Component
{
	// access to screen shake ;)
	public DrawSystem drawSystem;

	public BallComponent(DrawSystem drawSystem)
	{
		this.drawSystem = drawSystem;
	}
}
