package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.artlessavian.lethalleague.ecs.systems.DrawSystem;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

/**
 * Mirrors HitboxComponent, kind of
 */
public class BallComponent implements Component
{
	// access to screen shake ;)
	public DrawSystem drawSystem;

	public HitboxComponent.HitBehavior behavior;
	public Rectangle rect;
	public int intangible = 0;

	public int trueSpeed = 0;

	public int team = -1;
	public Player lastHit;

	public boolean getAngle;
	public boolean wasSmashed;

	public static float precision = 10;

	public BallComponent(DrawSystem drawSystem, int team)
	{
		this.drawSystem = drawSystem;
		rect = new Rectangle(0,0,48,48);
		behavior = new Ball.BallHittingBehavior();
		this.team = team;
	}
}
