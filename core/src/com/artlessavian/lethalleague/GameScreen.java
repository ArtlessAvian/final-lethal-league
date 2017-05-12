package com.artlessavian.lethalleague;

//import com.artlessavian.lethalleague.entities.Ball;

import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.artlessavian.lethalleague.ecs.systems.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter
{
	static final float deltaTime = 1/60f;

	Maineroni main;

	Engine engine;
	EntitySystem[] drawSystems;

	public Stage stage;

	Player p1;
	Player p2;
	public Ball ball;

	public GameScreen(Maineroni main)
	{
		this.main = main;

		this.stage = new Stage();

		engine = new Engine();
		engine.addSystem(new StateSystem());
		engine.addSystem(new PhysicsSystem(stage));
		engine.addSystem(new HitboxCollisionSystem());
		engine.addSystem(new BallSystem());
		engine.addSystem(new HitlagSystem());

		drawSystems = new EntitySystem[3];
		DrawSystem drawSystem = new DrawSystem(main, this);
		engine.addSystem(drawSystem);
		drawSystems[0] = drawSystem;

		GUIDrawSystem guiDrawSystem = new GUIDrawSystem(main, this);
		engine.addSystem(guiDrawSystem);
		drawSystems[1] = guiDrawSystem;

		DebugDrawSystem debugDrawSystem = new DebugDrawSystem(main, this, drawSystem);
		engine.addSystem(debugDrawSystem);
		drawSystems[2] = debugDrawSystem;

		p1 = new Player(main.getInput(0));
//		p1.vel.add(2, 2);
		p2 = new Player(main.getInput(1));
		ball = new Ball(drawSystem);

		engine.addEntity(p1);
		engine.addEntity(p2);
		engine.addEntity(ball);

		// hue
//		for (int i = 0; i < 360; i++)
//		{
//			engine.addEntity(new Ball(drawSystem));
//		}
	}

	public int engineRuns = 0;
	float rollover = 0;
	boolean timeStop = false;
	float timeSlow = 1/1f;

	public void render(float delta)
	{
		// TODO: Remove me!
		if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS)) {timeStop = !timeStop;}
		if (!timeStop || Gdx.input.isKeyJustPressed(Input.Keys.EQUALS))
		{
			rollover += delta * timeSlow;
		}

		for (; rollover > 0; rollover -= deltaTime)
		{
			engineRuns++;
			engine.update(deltaTime);
		}

//		System.out.println(rollover / deltaTime + 1);
		for (EntitySystem sys : drawSystems) {sys.update(rollover / deltaTime + 1);}
	}
}