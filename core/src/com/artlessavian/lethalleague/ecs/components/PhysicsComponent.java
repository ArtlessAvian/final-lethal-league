package com.artlessavian.lethalleague.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PhysicsComponent implements Component
{
	public float passiveGravity = 0;

	public Vector2 pos;
	public Vector2 vel;
	public Vector2 lastPos;
	public boolean facingLeft;

	public Rectangle collision;
	public boolean grounded;

	public PhysicsComponent()
	{
		pos = new Vector2();
		vel = new Vector2();
		lastPos = new Vector2();
		collision = new Rectangle();
	}
}
