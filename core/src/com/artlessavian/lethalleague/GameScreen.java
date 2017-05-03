package com.artlessavian.lethalleague;

import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter
{
	Maineroni main;

	Player p1;
	Player p2;

	Ball ball;

	public GameScreen(Maineroni main)
	{
		this.main = main;

		p1 = new Player();
		p2 = new Player();
		ball = new Ball();
	}

	public void render(float delta)
	{

		main.batch.begin();
		main.font.draw(main.batch, "hey", p1.pos.x, p1.pos.y);
		main.batch.end();
	}
}