package com.artlessavian.lethalleague;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Generic Stage, with no graphics. Extend to create new Stages.
 */
public class Stage
{
	public Rectangle bounds;
	Sprite sprite;

	public Stage()
	{
		bounds = new Rectangle();
		bounds.setSize(1200, 500);
		bounds.setCenter(0, -6666666);
		bounds.y = 100;

		sprite = new Sprite(new Texture("grid.png"));
		sprite.setSize(bounds.width, bounds.height);
		sprite.setPosition(bounds.x, bounds.y);
	}

	public void draw(SpriteBatch batch)
	{
		sprite.draw(batch, 0.2f);
	}
}
