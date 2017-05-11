package com.artlessavian.lethalleague.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component
{
	public Sprite sprite;
	public boolean isScreenSpace = false;

	public SpriteComponent(Sprite sprite)
	{
		this.sprite = sprite;
		this.sprite.setOriginCenter();
	}
}
