package com.artlessavian.lethalleague;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Maineroni extends Game
{
	public SpriteBatch batch;
	BitmapFont font;
	OrthographicCamera screenSpace;

	PlayerInput[] inputs;
	InputMultiplexer allInputs;

	@Override
	public void create()
	{
		batch = new SpriteBatch();

		screenSpace = new OrthographicCamera(1280, 720);
		screenSpace.translate(1280f/2f, 720f/2f);
		screenSpace.update();

		font = new BitmapFont();

		allInputs = new InputMultiplexer();
		inputs = new PlayerInput[2];
		inputs[0] = new PlayerInput(0);
		inputs[1] = new PlayerInput(1);

		for (PlayerInput p : inputs)
		{
			allInputs.addProcessor(p);
		}

		Gdx.input.setInputProcessor(allInputs);

		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		super.render();

		batch.setProjectionMatrix(screenSpace.combined);
		font.draw(batch, "" + Gdx.graphics.getFramesPerSecond(), 6, 18);
		font.draw(batch, "" + Gdx.graphics.getFrameId(), 6, 30);

		batch.end();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		font.dispose();
	}

	public PlayerInput getInput(int id)
	{
		return inputs[id];
	}
}
