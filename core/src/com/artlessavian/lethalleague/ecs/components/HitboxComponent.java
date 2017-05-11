package com.artlessavian.lethalleague.ecs.components;

import com.artlessavian.lethalleague.OffsetRectangle;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;

public class HitboxComponent implements Component
{
	public interface HitBehavior
	{
		void onHit(Entity thisEntity, Entity other);
		void onGetHit(Entity thisEntity, Entity other);
	}

	public class TempBehavior implements HitBehavior
	{
		@Override
		public void onHit(Entity thisEntity, Entity other)
		{
			System.out.println("hit " + thisEntity.hashCode() + " " + other.hashCode());
		}

		@Override
		public void onGetHit(Entity thisEntity, Entity other)
		{
//			System.out.println("get hit");
		}
	}

	public HitBehavior behavior;
	public ArrayList<OffsetRectangle> hitboxes;
	public OffsetRectangle hurtbox; // where you get hurt from

	public HitboxComponent()
	{
		this.behavior = new TempBehavior();
		hitboxes = new ArrayList<OffsetRectangle>();
		hurtbox = new OffsetRectangle(-36, 0, 72, 144);
	}
}
