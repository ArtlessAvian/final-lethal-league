// package com.artlessavian.lethalleague.ecs.systems;

// import com.artlessavian.lethalleague.OffsetRectangle;
// 
// import com.artlessavian.lethalleague.ecs.components.*;
// import com.badlogic.ashley.core.Engine;
// import com.badlogic.ashley.core.Entity;
// import com.badlogic.ashley.core.EntitySystem;
// import com.badlogic.ashley.core.Family;
// import com.badlogic.ashley.systems.IteratingSystem;
// import com.badlogic.ashley.utils.ImmutableArray;
// import com.badlogic.gdx.math.Vector2;

// import java.util.HashMap;

// public class AntiBallStealSystem extends IteratingSystem
// {
// 	private ImmutableArray<Entity> players;

// 	public AntiBallStealSystem()
// 	{
// 		super(Family.all(BallComponent.class).get());
// 	}

// 	public void addedToEngine(Engine engine)
// 	{
// 		super.addedToEngine(engine);
// 		players = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
// 	}

// 	@Override
// 	public void update(float delta)
// 	{
// 		
// 		super.update(delta);
// 		;
// 	}

// 	Vector2 helper = new Vector2();

// 	protected void processEntity(Entity entity, float deltaTime)
// 	{
// //		BallComponent ballC = entity.getComponent(BallComponent.class);
// //		PhysicsComponent physicsC = entity.getComponent(PhysicsComponent.class);

// //		if (ballC.intangible == 0) {return;}

// //		for (Entity player : players)
// //		{
// //			HitlagComponent playerHitlagC = player.getComponent(HitlagComponent.class);
// //			if (playerHitlagC.hitlag > 0) {continue;}

// //			PhysicsComponent playerPhysicsC = player.getComponent(PhysicsComponent.class);
// //			float distance = playerPhysicsC.pos.dst(physicsC.pos);
// //			if (distance < 250)
// //			{
// //				helper.add(playerPhysicsC.pos).sub(physicsC.pos);
// //				helper.setLength(250 - distance);
// //				playerPhysicsC.pos.add(helper);
// //				physicsC.vel.
// //			}
// //		}
// 	}
// }
