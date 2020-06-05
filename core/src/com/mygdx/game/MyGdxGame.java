package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter {

    public static final String TITLE = "get a title";
    public static final int V_WIDTH = 512;
    public static final int V_HEIGHT = 288;
    public static final int SCALE = 2;

    public static final float STEP = 1 / 60f;
    private float timeAcumulator = 0;

    private SpriteBatch batch;
    private Texture img;
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;
    private OrthographicCamera cam;

    private Player player;


    @Override
    public void create() {
        Gdx.input.setInputProcessor(new Input());
        batch = new SpriteBatch();
        img = new Texture("hiprechaun.png");
        player = new Player(img);
        img = new Texture("badlogic.jpg");
        cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
        // load tiled map
        map = new TmxMapLoader().load("tiles/firstTileMap.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);
    }

    //render() runs once per frame
    @Override
    public void render() {
        timeAcumulator += Gdx.graphics.getDeltaTime();
        while (timeAcumulator >= STEP) {
            timeAcumulator -= STEP;
            frame();
        }
    }

    public void frame() {
        handleInput();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw the map BEFORE begining the batch
        tmr.setView(cam);
        tmr.render();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();


        batch.draw(player.getRegion(), player.getX(), player.getY());


        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.end();
    }

    public void handleInput() {
        int x = 0;
        int y = 0;
        if (Input.isDown(Input.RIGHT)) {
            x += 3;
        }
        if (Input.isDown(Input.UP)) {
            y += 3;
        }
        if (Input.isDown(Input.LEFT)) {
            x -= 3;
        }
        if (Input.isDown(Input.DOWN)) {
            y -= 3;
        }
        player.walk(x, y);
        cam.translate(player.x - cam.position.x + 16, player.y - cam.position.y + 16);
        Input.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
