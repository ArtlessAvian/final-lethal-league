package com.artlessavian.lethalleague.ecs.entities;

import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.components.RemoveComponent;
import com.artlessavian.lethalleague.ecs.components.SpriteComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Particle extends Entity
{
	public enum ParticleThing
	{
		poof("poof.png"),
		star("star.png");

		public Texture t;
		public String string;

		ParticleThing(String s)
		{
			t = new Texture(s);
			string = s;
		}

		public Texture getTexture()
		{
			if (t == null)
			{
				t = new Texture(string);
			}
			return t;
		}
	}

	public Particle(float x, float y, ParticleThing thing, int lifetime)
	{
		Sprite s = new Sprite(thing.getTexture());
		s.setCenter(x, y);
		SpriteComponent spriteC = new SpriteComponent(s);
		add(spriteC);

		PhysicsComponent physicsC = new PhysicsComponent();
		physicsC.pos.set(x, y - spriteC.sprite.getHeight()/2f);
		physicsC.vel.set(100, 0);
		physicsC.vel.setAngle((float)(Math.random() * 360));
		add(physicsC);

		RemoveComponent removeC = new RemoveComponent();
		removeC.removeTimer = lifetime;
		add(removeC);
	}
}
