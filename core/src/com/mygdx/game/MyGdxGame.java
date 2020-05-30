package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

	public static final String TITLE = "get a title";
	public static final int V_WIDTH = 256;
	public static final int V_HEIGHT = 144;
	public static final int SCALE = 2;

	public static final float STEP = 1 / 60f;
	private float timeAcumulator = 0;

	SpriteBatch batch;
	Texture img;

	Player player;


	@Override
	public void create () {
		Gdx.input.setInputProcessor(new Input());
		batch = new SpriteBatch();
		img = new Texture("hiprechaun.png");
		//region = new TextureRegion(img, 32, 32, 32, 32);
		player = new Player(img);
	}

	//render() runs once per frame
	@Override
	public void render () {
		timeAcumulator += Gdx.graphics.getDeltaTime();
		while(timeAcumulator >= STEP){
			timeAcumulator -= STEP;
			frame();
		}
	}

	public void frame() {
		handleInput();
		Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(player.getRegion(), player.getX(), player.getY());
		batch.end();
	}

	public void handleInput() {
		int x = 0;
		int y = 0;
		if(Input.isDown(Input.RIGHT)){
			x += 3;
		}
		if(Input.isDown(Input.UP)){
			y += 3;
		}
		if(Input.isDown(Input.LEFT)){
			x -= 3;
		}
		if(Input.isDown(Input.DOWN)){
			y -= 3;
		}
		player.walk(x, y);
		Input.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
