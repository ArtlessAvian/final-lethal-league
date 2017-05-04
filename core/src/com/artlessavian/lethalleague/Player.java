package com.artlessavian.lethalleague;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player
{
	Sprite sprite;
	Vector2 pos;
	Vector2 vel;
	Vector2 lastPos;

	public Player()
	{
		sprite = new Sprite();
		pos = new Vector2();
		vel = new Vector2();
		lastPos = new Vector2();
	}
}