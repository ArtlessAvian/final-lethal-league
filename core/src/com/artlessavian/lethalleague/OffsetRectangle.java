package com.artlessavian.lethalleague;

import com.badlogic.gdx.math.Rectangle;

public class OffsetRectangle
{
	protected Rectangle rectangle;
	public float deltaX;
	public float deltaY;

	public OffsetRectangle()
	{
		rectangle = new Rectangle();
	}

	public void setSize(float width, float height)
	{
		rectangle.setSize(width, height);
	}

	public void setPosition(float x, float y)
	{
		rectangle.setPosition(x + deltaX, y + deltaY);
	}

	public boolean contains(OffsetRectangle other)
	{
		return rectangle.contains(other.rectangle);
	}

	public boolean overlaps(OffsetRectangle other)
	{
		return rectangle.overlaps(other.rectangle);
	}
}
