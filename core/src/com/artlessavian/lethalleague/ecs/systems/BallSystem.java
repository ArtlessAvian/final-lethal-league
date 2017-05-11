package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.OffsetRectangle;
import com.artlessavian.lethalleague.Stage;
import com.artlessavian.lethalleague.ecs.components.BallComponent;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.StageComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import java.util.ArrayList;

public class BallSystem extends IteratingSystem
{
	public BallSystem()
	{
		super(Family.all(BallComponent.class).get());
	}

//	ArrayList<OffsetRectangle> pool = new ArrayList<OffsetRectangle>();

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);
		HitboxComponent hitboxC = entity.getComponent(HitboxComponent.class);

		hitboxC.hitboxes.clear();

		float delta = physicsC.pos.dst(physicsC.lastPos);

		for (float i = delta; i > 0; i -= 10)
		{
			float x = i/delta * (physicsC.lastPos.x - physicsC.pos.x);
			float y = i/delta * (physicsC.lastPos.y - physicsC.pos.y);
			OffsetRectangle rect = new OffsetRectangle(x - 24, y, 48, 48);
			hitboxC.hitboxes.add(rect);
		}
	}
}
