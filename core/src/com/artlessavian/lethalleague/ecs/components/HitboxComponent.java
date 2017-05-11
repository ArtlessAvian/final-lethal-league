package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.OffsetRectangle;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;

public class HitboxComponent implements Component
{
	public interface HitBehavior
	{
		void onHit(Entity thisEntity, Entity other, boolean isSmash);
		void onGetHit(Entity thisEntity, Entity other, boolean isSmash);
	}

	public static class TempBehavior implements HitBehavior
	{
		@Override
		public void onHit(Entity thisEntity, Entity other, boolean isSmash)
		{
			System.out.println("hit " + thisEntity.hashCode() + " " + other.hashCode());
		}

		@Override
		public void onGetHit(Entity thisEntity, Entity other, boolean isSmash)
		{
//			System.out.println("get hit");
		}
	}

	public HitBehavior behavior;
	public ArrayList<OffsetRectangle> hitboxes;
	public OffsetRectangle hurtbox; // where you get hurt from

	public ArrayList<Entity> cannotHit;

	public HitboxComponent(HitBehavior behavior)
	{
		this.behavior = behavior;
		hitboxes = new ArrayList<OffsetRectangle>();
		hurtbox = new OffsetRectangle(-36, 0, 72, 144);

		cannotHit = new ArrayList<Entity>();
	}
}
