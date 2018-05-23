package com.artlessavian.lethalleague;

import com.artlessavian.lethalleague.ecs.components.RemoveComponent;
import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.artlessavian.lethalleague.ecs.systems.DrawSystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BallInfo
{
	public Ball instance;
	private DrawSystem drawSystem;

	BallInfo(DrawSystem drawSystem)
	{
		this.drawSystem = drawSystem;
	}

	public Ball spawn()
	{
		instance = new Ball(drawSystem, -1);
		return instance;
	}

	public boolean isDead()
	{
		return instance.getComponent(RemoveComponent.class) != null;
	}
}

