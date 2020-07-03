package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyGdxGame extends ApplicationAdapter {

    public static final String TITLE = "get a title";
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 225;
    public static final int SCALE = 2;

    public static final float STEP = 1 / 60f;
    private float timeAccumulator = 0;

    private SpriteBatch batch;
    private Texture img;
    private TiledMap map;
    private TiledMapTileLayer layer;
    private OrthogonalTiledMapRenderer tmr;
    private OrthographicCamera cam;
    private Menu menu;

    private Player player;

    private boolean paused;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(new Input());
        batch = new SpriteBatch();
        img = new Texture("characters/hiprechaun.png");
        player = new Player(img);
        cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
        // load tiled map
        map = new TmxMapLoader().load("tiles/firstTileMap.tmx");
        tmr = new OrthogonalTiledMapRenderer(map, batch);
        //setting the menu
        menu = new Menu(new Texture("menu/menu.png"), batch, cam);
        paused = false;
    }


    @Override
    public void render() {
        timeAccumulator += Gdx.graphics.getDeltaTime();
        while (timeAccumulator >= STEP) {
            timeAccumulator -= STEP;
            frame();
        }
    }


    /**
     * Is called during every frame of the game.
     * Verifies the state of the game, then draws the world and handles input accordingly.
     */
    public void frame() {
        paused ^= Input.ispressed(Input.START);
        if (paused) {
            menuInput();
            drawOverWorld();
            menu.render();
        } else {
            overWorldInput();
            drawOverWorld();
        }
    }


    /**
     * The way that the input is taken if the game is in the overWorld state.
     *
     */
    public void overWorldInput() {
        int x = 0;
        int y = 0;
        if (Input.isDown(Input.RIGHT)) x += 3;
        if (Input.isDown(Input.UP)) y += 3;
        if (Input.isDown(Input.LEFT)) x -= 3;
        if (Input.isDown(Input.DOWN)) y -= 3;
        player.walk(x, y, map.getLayers());
        cam.translate(player.x - cam.position.x + 16, player.y - cam.position.y + 16);

        Input.update();
    }

    public void menuInput() {
        Input.update();
    }


    public void drawOverWorld() {
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tmr.setView(cam);
        tmr.render();

        batch.begin();
        batch.draw(player.getRegion(), player.getX(), player.getY());
        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
