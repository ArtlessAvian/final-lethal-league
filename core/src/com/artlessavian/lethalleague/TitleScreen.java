package com.artlessavian.lethalleague;

import com.badlogic.gdx.Screen;

public class TitleScreen implements Screen
{
	Maineroni main;
	private boolean p1Ready;
	private boolean p2Ready;

	public TitleScreen(Maineroni main)
	{
		this.main = main;
	}

	@Override
	public void show()
	{

	}

	@Override
	public void render(float delta)
	{
		if (main.getInput(0).swingPressed)
		{
			p1Ready = true;
		}
		if (main.getInput(1).swingPressed)
		{
			p2Ready = true;
		}

		if (p1Ready && p2Ready)
		{
			main.setScreen(new GameScreen(main));
			return;
		}

		main.batch.setProjectionMatrix(main.screenSpace.combined);

		// TODO: Make not terrible. Draw Images?
		main.font.draw(main.batch, "temporary title screen!", 300, 500);

		main.font.draw(main.batch, "game:", 500, 400);
		main.font.draw(main.batch, "hit the ball into the other person to get a point", 500, 370);
		main.font.draw(main.batch, "first to 8 wins", 500, 340);

		main.font.draw(main.batch, "controls:", 100, 400);
		main.font.draw(main.batch, "Arrow Keys and Right Shift, WASD and J", 100, 370);
		main.font.draw(main.batch, "J/Shift swings. If you're in the air, youll do a smash", 100, 340);
		main.font.draw(main.batch, "press j and right shift to ready", 100, 310);

		if (p1Ready)
		{
			main.font.draw(main.batch, "p1 ready!", 100, 290);
		}
		if (p2Ready)
		{
			main.font.draw(main.batch, "p2 ready!", 100, 250);
		}
	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void hide()
	{

	}

	@Override
	public void dispose()
	{

	}
}
