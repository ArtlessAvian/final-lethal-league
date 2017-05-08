package com.artlessavian.lethalleague;

//import com.artlessavian.lethalleague.entities.Ball;

import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.artlessavian.lethalleague.ecs.systems.DebugDrawSystem;
import com.artlessavian.lethalleague.ecs.systems.DrawSystem;
import com.artlessavian.lethalleague.ecs.systems.PhysicsSystem;
import com.artlessavian.lethalleague.ecs.systems.StateSystem;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter
{
	Maineroni main;

	Engine engine;
	EntitySystem[] drawSystems;

	public Stage stage;

	Player p1;
	Player p2;
	Ball ball;

	public GameScreen(Maineroni main)
	{
		this.main = main;

		this.stage = new Stage();

		engine = new Engine();
		engine.addSystem(new StateSystem());
		engine.addSystem(new PhysicsSystem(stage));

		drawSystems = new EntitySystem[2];
		DrawSystem drawSystem = new DrawSystem(main, this);
		engine.addSystem(drawSystem);
		drawSystems[0] = drawSystem;

		DebugDrawSystem debugDrawSystem = new DebugDrawSystem(main, this, drawSystem);
		engine.addSystem(debugDrawSystem);
		drawSystems[1] = debugDrawSystem;

		p1 = new Player(main.getInput(0));
//		p1.vel.add(2, 2);
		p2 = new Player(main.getInput(1));
		ball = new Ball();

		engine.addEntity(p1);
		engine.addEntity(p2);
		engine.addEntity(ball);
	}

	float rollover = 0;

	public void render(float delta)
	{
		rollover += delta;
		for (; rollover > 0; rollover -= 1 / 60f) {engine.update(0);}

		for (EntitySystem sys : drawSystems) {sys.update(0);}
	}
}