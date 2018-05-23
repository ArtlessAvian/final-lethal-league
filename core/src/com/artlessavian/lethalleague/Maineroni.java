package com.artlessavian.lethalleague;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Maineroni extends Game
{
	public SpriteBatch batch;
	public BitmapFont font;
	public GlyphLayout layout;
	public OrthographicCamera screenSpace;

	PlayerInputContainer[] inputs;
	PlayerInput[] processors;
	InputMultiplexer allInputs;

	@Override
	public void create()
	{
		batch = new SpriteBatch();

		screenSpace = new OrthographicCamera(1280, 720);
		screenSpace.translate(1280f / 2f, 720f / 2f);
		screenSpace.update();

		font = new BitmapFont();
		layout = new GlyphLayout(font, "");

		allInputs = new InputMultiplexer();
		inputs = new PlayerInputContainer[2];
		inputs[0] = new PlayerInputContainer();
		inputs[1] = new PlayerInputContainer();
		processors = new PlayerInput[2];
		processors[0] = new PlayerInput(0, inputs[0]);
		processors[1] = new PlayerInput(1, inputs[1]);

		allInputs.addProcessor(processors[0]);
		allInputs.addProcessor(processors[1]);
		Gdx.input.setInputProcessor(allInputs);

		this.setScreen(new TitleScreen(this));
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
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

	public PlayerInputContainer getInput(int id)
	{
		return inputs[id];
	}
}
