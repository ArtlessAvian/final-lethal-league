package com.artlessavian.lethalleague.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class HitboxComponent implements Component
{
	public interface HitBehavior
	{
		void onHit(Entity other);
		void onGetHit(Entity other);
	}

	public ArrayList<Rectangle> hitboxes;
	public ArrayList<Rectangle> hurtboxes; // where you get hurt from

	public HitboxComponent()
	{
		hitboxes = new ArrayList<Rectangle>();
		hurtboxes = new ArrayList<Rectangle>();
	}
}
