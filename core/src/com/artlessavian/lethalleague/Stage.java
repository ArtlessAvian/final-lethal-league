package com.artlessavian.lethalleague;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Stage
{
	int yBottom;
	int yTop;
	int xLeft;

	Sprite sprite;

	public Stage()
	{
		sprite = new Sprite(new Texture("windows_xp_bliss-wide.jpg"));
	}
}