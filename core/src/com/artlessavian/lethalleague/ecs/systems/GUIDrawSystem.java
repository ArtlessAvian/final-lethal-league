package com.artlessavian.lethalleague.ecs.systems;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.Maineroni;
import com.artlessavian.lethalleague.TimeLogger;
import com.artlessavian.lethalleague.ecs.components.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GUIDrawSystem extends EntitySystem
{
	Maineroni main;
	GameScreen game;

	private ImmutableArray<Entity> entities;
	Sprite rectangle;
	Sprite bottom;
//	Sprite card;

	public GUIDrawSystem(Maineroni main, GameScreen game)
	{
		this.main = main;
		this.game = game;
		this.rectangle = new Sprite(new Texture("white.png"));
		this.bottom = new Sprite(new Texture("bottom gui.png"));
//		this.card = new Sprite(new Texture("player card.png"));
	}

	public void addedToEngine(Engine engine)
	{
		this.setProcessing(false);

		entities = engine.getEntitiesFor(Family.all(SpriteComponent.class).get());
		setProcessing(false);
	}

	float ballHitlagMax = 0;
	float hitlagRectHeight = 0;
	float speedRectWidth = 0;
	Color a = new Color(1,0,0,1);

	@Override
	public void update(float rollover)
	{
		TimeLogger.logIn();

		main.batch.setProjectionMatrix(main.screenSpace.combined);

		bottom.draw(main.batch);

		for (Entity entity : entities)
		{
			SpriteComponent spriteC = entity.getComponent(SpriteComponent.class);
			if (!spriteC.isScreenSpace) {continue;}

			// TODO:

		}

		main.font.getData().setScale(40/12);

		HitlagComponent hitlagC = game.ball.instance.getComponent(HitlagComponent.class);
		if (!game.ball.isDead())
		{
			if (hitlagC.hitlag == 0) {ballHitlagMax = 0;}
			else
			{
				ballHitlagMax = Math.max(ballHitlagMax, hitlagC.hitlag);

				if (ballHitlagMax > 3)
				{
					rectangle.setColor(Color.WHITE);
					hitlagRectHeight = (50 * hitlagC.hitlag / ballHitlagMax + hitlagRectHeight) / 2f;
					rectangle.setSize(250, hitlagRectHeight);
					rectangle.setPosition(515, 20);
					rectangle.draw(main.batch);
					// main.font.draw(main.batch, Math.round(hitlagC.hitlag / ballHitlagMax * 100) + "", 600, 30);
				}
			}
		}

		float speed = game.ball.instance.getComponent(PhysicsComponent.class).vel.len() / 60;
		speedRectWidth = (15 * speedRectWidth + speed)/16f;

		if (speedRectWidth >= 300)
		{
			a.r = (float)Math.sin(Gdx.graphics.getFrameId() / 10f)/2f + 0.5f;
			a.g = (float)Math.sin(Gdx.graphics.getFrameId() / 10f + Math.PI * 2/3f)/2f + 0.5f;
			a.b = (float)Math.sin(Gdx.graphics.getFrameId() / 10f + Math.PI * 4/3f)/2f + 0.5f;

			a.lerp(1, 1, 1, 1, Math.max(0, Math.min(1, (1280 - speedRectWidth)/(1280-300))));

			rectangle.setColor(a);
		}
		rectangle.setSize(speedRectWidth, 10);
		rectangle.setCenter(640, 10);
		rectangle.draw(main.batch, 1f);

		main.layout.setText(main.font, Math.round(speed) + "");
		float width = main.layout.width;

		if (width < 300)
		{
			main.font.draw(main.batch, Math.round(speed) + "", 640 - width / 2f, 60);
		}
		else
		{
			int orderMagnitude = (int)Math.log10(speed);
			String s = (int)(speed / Math.pow(10, orderMagnitude)) + " x10^" + orderMagnitude;
			System.out.println(s);
			main.font.draw(main.batch, s, 640 - width / 2f, 60);
		}

		main.font.getData().setScale(1);
		main.font.draw(main.batch, "units/frame", 605, 18);

		main.font.getData().setScale(30/12);

		// TODO: Not hardcode
//		card.setCenter(426, 630);
//		card.draw(main.batch);
		main.font.draw(main.batch, game.p1.score + "", 426, 650);
		main.font.draw(main.batch, game.p1.stocks + "", 426, 620);
//		card.setCenter(426*2, 630);
//		card.draw(main.batch);
		main.font.draw(main.batch, game.p2.score + "", 426*2, 650);
		main.font.draw(main.batch, game.p2.stocks + "", 426*2, 620);

		main.font.getData().setScale(1);

		TimeLogger.logOut("GUIDrawSystem");
	}
}
