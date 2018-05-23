package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.OffsetRectangle;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;

public class HitboxComponent implements Component
{
	public interface HitBehavior
	{
		void onHit(Entity thisEntity, Entity other, boolean isSmash, Engine engine);
		void onGetHit(Entity thisEntity, Entity other, boolean isSmash, Engine engine);
	}

	public HitBehavior behavior;
	public ArrayList<OffsetRectangle> hitboxes;
	public OffsetRectangle hurtbox; // where you get hurt from

	public int team = 0;

	public int intangible = 0;
	public ArrayList<Entity> cannotHit;

	public HitboxComponent(HitBehavior behavior, int team)
	{
		this.behavior = behavior;
		hitboxes = new ArrayList<OffsetRectangle>();
		hurtbox = new OffsetRectangle(-36, 0, 72, 144);

		this.team = team;

		cannotHit = new ArrayList<Entity>();
	}
}
