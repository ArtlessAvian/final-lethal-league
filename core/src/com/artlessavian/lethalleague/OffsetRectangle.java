package com.artlessavian.lethalleague;

import com.badlogic.gdx.math.Rectangle;

public class OffsetRectangle extends Rectangle
{
	public float deltaX;
	public float deltaY;
	public boolean flipped;

	public OffsetRectangle(float deltaX, float deltaY)
	{
		super();
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public OffsetRectangle(float deltaX, float deltaY, float width, float height)
	{
		super(0, 0, width, height);
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public Rectangle setPosition(float x, float y)
	{
		return super.setPosition(x + deltaX, y + deltaY);
	}

	public Rectangle flip()
	{
		flipped = !flipped;
		float ax = x - deltaX;
		deltaX = -getWidth()/2 - deltaX;
		setPosition(ax, y - deltaY);
		return this;
	}

	public Rectangle setFlip(boolean flip)
	{
		if (flip != flipped)
		{
			flip();
		}
		return this;
	}
}
