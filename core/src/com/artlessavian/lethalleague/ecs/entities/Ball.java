package com.artlessavian.lethalleague.ecs.entities;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Stage;
import com.artlessavian.lethalleague.ecs.components.*;
import com.artlessavian.lethalleague.ecs.systems.DrawSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ball extends Entity
{
	public Ball(DrawSystem drawSystem, int team)
	{
		PhysicsComponent physicsC = new PhysicsComponent();
		physicsC.pos.y = 250;
//		physicsC.vel.set(0,2f);

//		physicsC.vel.setAngle((float)(360 * Math.random()));
//		physicsC.passiveGravity = 4000;
		physicsC.collision.setSize(48, 48);
		this.add(physicsC);
//		StateComponent stateC = new StateComponent();
//		stateC.machine.current = new PlayerStandState(this);
//		this.add(stateC);
		SpriteComponent spriteC = new SpriteComponent(new Sprite(new Texture("circle.png")));
		spriteC.sprite.setSize(48, 48);
		this.add(spriteC);
//		HitboxComponent hitboxC = new HitboxComponent(new BallHittingBehavior());
//		hitboxC.hurtbox = new OffsetRectangle(-24, 0, 48, 48);
//		this.add(hitboxC);
		StageComponent stageC = new StageComponent(new BallCollisionBehavior(7));
		this.add(stageC);

//		this.add(new MainAccessComponent(game));
		this.add(new HitlagComponent());

		this.add(new BallComponent(drawSystem, team));
	}

	// TODO
	public static class BallCollisionBehavior extends StageComponent.CollisionBehavior
	{
		Sound wallHit;
		int wallHitlag = 7;

		public BallCollisionBehavior()
		{
			try
			{
				wallHit = Gdx.audio.newSound(Gdx.files.internal("sound/wall_bounce.wav"));
			}
			catch (Exception e) {System.out.println("hey");}
		}

		public BallCollisionBehavior(int wallHitlag)
		{
			this();
			this.wallHitlag = wallHitlag;
		}

		@Override
		public void onTouchCeil(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			BallComponent ballC = thisEntity.getComponent(BallComponent.class);
			ballC.drawSystem.doScreenShake(5, 5);

			float deltaX = physicsC.pos.x - physicsC.lastPos.x;
			float deltaY = physicsC.pos.y - physicsC.lastPos.y;
			float newY = stage.bounds.y + stage.bounds.height - physicsC.collision.height;
			float newX = physicsC.lastPos.x + deltaX * (newY - physicsC.lastPos.y) / deltaY;

//			System.out.println(deltaX / deltaY);
//			System.out.println((newX - physicsC.lastPos.x) / (newY - physicsC.lastPos.y));

			physicsC.vel.y *= -1;

			HitlagComponent hitlagC = thisEntity.getComponent(HitlagComponent.class);
			hitlagC.hitlag = this.wallHitlag;

			physicsC.pos.set(newX, newY);

			GameScreen.makePoof(physicsC.pos.x, physicsC.pos.y + physicsC.collision.height);
			if (wallHit != null)
			{
				wallHit.play();
			}
		}

		@Override
		public void onTouchFloor(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			BallComponent ballC = thisEntity.getComponent(BallComponent.class);
			ballC.drawSystem.doScreenShake(5, 5);

			float deltaX = physicsC.pos.x - physicsC.lastPos.x;
			float deltaY = physicsC.pos.y - physicsC.lastPos.y;
			float newY = stage.bounds.y;
			float newX = physicsC.lastPos.x + deltaX * (newY - physicsC.lastPos.y) / deltaY;

			physicsC.vel.y *= -1;

			HitlagComponent hitlagC = thisEntity.getComponent(HitlagComponent.class);
			hitlagC.hitlag = this.wallHitlag;

			physicsC.pos.set(newX, newY);

			GameScreen.makePoof(physicsC.pos.x, physicsC.pos.y);
			if (wallHit != null)
			{
				wallHit.play();
			}
		}

		@Override
		public void onTouchLeft(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			BallComponent ballC = thisEntity.getComponent(BallComponent.class);
			ballC.drawSystem.doScreenShake(5, 5);

			float deltaX = physicsC.pos.x - physicsC.lastPos.x;
			float deltaY = physicsC.pos.y - physicsC.lastPos.y;
			float newX = stage.bounds.x + physicsC.collision.width/2;
			float newY = physicsC.lastPos.y + deltaY * (newX - physicsC.lastPos.x) / deltaX;

			physicsC.vel.x *= -1;

			HitlagComponent hitlagC = thisEntity.getComponent(HitlagComponent.class);
			hitlagC.hitlag = this.wallHitlag;

			physicsC.pos.set(newX, newY);

			GameScreen.makePoof(physicsC.pos.x - physicsC.collision.width/2f, physicsC.collision.y + physicsC.collision.height/2f);
			if (wallHit != null)
			{
				wallHit.play();
			}
		}

		@Override
		public void onTouchRight(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			BallComponent ballC = thisEntity.getComponent(BallComponent.class);
			ballC.drawSystem.doScreenShake(5, 5);

			float deltaX = physicsC.pos.x - physicsC.lastPos.x;
			float deltaY = physicsC.pos.y - physicsC.lastPos.y;
			float newX = stage.bounds.x + stage.bounds.width - physicsC.collision.width/2;
			float newY = physicsC.lastPos.y + deltaY * (newX - physicsC.lastPos.x) / deltaX;

//			System.out.println(deltaX / deltaY);
//			System.out.println((newX - physicsC.lastPos.x) / (newY - physicsC.lastPos.y));

			physicsC.vel.x *= -1;

			HitlagComponent hitlagC = thisEntity.getComponent(HitlagComponent.class);
			hitlagC.hitlag = this.wallHitlag;

			physicsC.pos.set(newX, newY);

			GameScreen.makePoof(physicsC.pos.x + physicsC.collision.width/2f, physicsC.collision.y + physicsC.collision.height/2f);
			if (wallHit != null)
			{
				wallHit.play();
			}
		}
	}

	public static class BallHittingBehavior implements HitboxComponent.HitBehavior
	{
		Sound weakHit;
		Sound weakSmash;
		Sound mediumHit;
		Sound mediumSmash;
		Sound strongHit;
		Sound strongSmash;
		int initSpeed = 180;

		public BallHittingBehavior()
		{
			try
			{
				weakHit = Gdx.audio.newSound(Gdx.files.internal("sound/weak_hit.wav"));
				weakSmash = Gdx.audio.newSound(Gdx.files.internal("sound/weak_smash.wav"));
				mediumHit = Gdx.audio.newSound(Gdx.files.internal("sound/medium_hit.wav"));
				mediumSmash = Gdx.audio.newSound(Gdx.files.internal("sound/medium_smash.wav"));
				strongHit = Gdx.audio.newSound(Gdx.files.internal("sound/strong_hit.wav"));
				strongSmash = Gdx.audio.newSound(Gdx.files.internal("sound/strong_smash.wav"));
			}
			catch (Exception e) {System.out.println("hey");}
		}

		@Override
		public void onHit(Entity thisEntity, Entity other, boolean isSmash, Engine engine)
		{
			if (other instanceof Player)
			{
				HitlagComponent hitlagC = thisEntity.getComponent(HitlagComponent.class);
				hitlagC.hitlag = 10;

				BallComponent ballC = thisEntity.getComponent(BallComponent.class);
				ballC.lastHit.getComponent(PlayerComponent.class).playerInfo.score++;
				System.out.println("hi");
			}
		}

		@Override
		public void onGetHit(Entity thisEntity, Entity other, boolean isSmash, Engine engine)
		{
			BallComponent ballC = thisEntity.getComponent(BallComponent.class);
			PhysicsComponent physicsC = thisEntity.getComponent(PhysicsComponent.class);

			if (ballC.trueSpeed == 0)
			{
				ballC.trueSpeed = initSpeed;
				if (isSmash) ballC.trueSpeed *= 2;
			}
			else
			{
				if (isSmash)
				{
					ballC.trueSpeed *= 2;
				}
				else
				{
					ballC.trueSpeed += 60;
				}
			}

			if (ballC.trueSpeed > 60 * 1000000) {ballC.trueSpeed = 60000000;}
			physicsC.vel.set(0,ballC.trueSpeed);

			physicsC.lastPos.set(physicsC.pos.x, physicsC.pos.y);

			HitlagComponent hitlagC = thisEntity.getComponent(HitlagComponent.class);
			hitlagC.hitlag = getHitlag(physicsC.vel.len());

			ballC.intangible = hitlagC.hitlag + 5;
			if (hitlagC.hitlag == 180)
			{
				ballC.drawSystem.doScreenShake(hitlagC.hitlag, 10);
			}
			else
			{
				ballC.drawSystem.doScreenShake(5, 5);
			}

			if (other instanceof Player)
			{
				ballC.lastHit = (Player)other;
				ballC.getAngle = true;
				ballC.wasSmashed = isSmash;

				HitlagComponent hitlagCOther = other.getComponent(HitlagComponent.class);
				hitlagCOther.hitlag = hitlagC.hitlag - 1;
				HitboxComponent hitboxC = other.getComponent(HitboxComponent.class);
				hitboxC.intangible = hitlagC.hitlag - 1;
				ballC.team = hitboxC.team;
			}

//			for (int i = 0; i < 1000; i++)
			for (float i = 1000; i <= ballC.trueSpeed; i *= 1.2f)
			{
				Particle p = new Particle(physicsC.lastPos.x, physicsC.lastPos.y, Particle.ParticleThing.star, 600);
				PhysicsComponent particlePhysicsC = p.getComponent(PhysicsComponent.class);
				SpriteComponent particleSpriteC = p.getComponent(SpriteComponent.class);
				GameScreen.engine.addEntity(p);
				particlePhysicsC.vel.set(500, 0);
				particlePhysicsC.vel.scl((float)(Math.random() * 0.2 + 0.9));
				particlePhysicsC.vel.setAngle((float)(Math.random() * 90 + 45));
				particlePhysicsC.passiveGravity = 1000;
				particleSpriteC.sprite.setScale(1/2f);
				if (isSmash)
				{
					particleSpriteC.passiveSpin = (float)(Math.random() * 16 + 20);
				}
				else
				{
					particleSpriteC.passiveSpin = (float)(Math.random() * 8 - 4);
				}
			}

			try
			{
				if (physicsC.vel.len() < 1000)
				{
					if (!isSmash) {weakHit.play();}
					else {weakSmash.play();}
				}
				else if (physicsC.vel.len() < 5000)
				{
					if (!isSmash) {mediumHit.play();}
					else {mediumSmash.play();}
				}
				else
				{
					if (!isSmash) {strongHit.play();}
					else {strongSmash.play();}
				}
			}
			catch (NullPointerException e) {System.out.println("woo");}
		}

		public int getHitlag(float speed)
		{
			if (speed > 5000)
			{
				return 180;
			}
			else
			{
				return (int)(55 * speed / 5000) + 5;
			}
		}
	}
}
