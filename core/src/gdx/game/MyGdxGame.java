package gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import gdx.game.Scene.SceneReader;
import gdx.game.Menus.Menu;
import gdx.game.Menus.MenuManager;
import gdx.game.Scene.ScenePlayer;

public class MyGdxGame extends ApplicationAdapter implements ScenePlayer {

    public static final String TITLE = "golemon prototype";
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 225;
    public static final int SCALE = 2;

    public static final float STEP = 1 / 60f;
    public static final int FINISHED_CUTSCENE = 0;
    private float timeAccumulator = 0;

    private SpriteBatch batch;
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;
    private OrthographicCamera cam;
    private TextBox dialogue;
    private MenuManager menuManager;

    private Player player;
    private Opponent dummy;
    private Golem defaultGolem;//only a placeholder; TODO replace this so the battle still works

    private SceneReader interpreter;

   public enum GameState{
        overWorld,
        paused,
        pickup,
        battle,
        cutScene
    }


    private GameState gameState;

    private Sound dialogueSound;
    private Music battleMusic;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(new Input());
        batch = new SpriteBatch();
        player = new Player(new Texture("characters/hiprechaun.png"));
        cam = new OrthographicCamera(V_WIDTH, V_HEIGHT);
        cam.translate(player.X_SCALE >> 2, player.Y_SCALE >> 1);

        // load tiled map
        map = new TmxMapLoader().load("tiles/firstTileMap.tmx");
        tmr = new OrthogonalTiledMapRenderer(map, batch);

        //golem and opponent
        dummy = new Opponent();
        defaultGolem = new Golem();

        //setting the menu
        dialogue = new TextBox("dialogueBoxes/green.png", batch, cam);

        menuManager = new MenuManager(batch, cam, defaultGolem);

        gameState = GameState.overWorld;


        dialogueSound = Gdx.audio.newSound(Gdx.files.internal("sounds/chirp.wav"));
        battleMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/wrath.wav"));
        // "the devourer's wrath" be Kevvviiinnn on Famitracker

    }

    /**
     * function from libgdx. I modify it into frame() to assure a fixed fps.
     */
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
    private void frame() {
        if(Input.isPressed(Input.SELECT)) {
            dialogueSound.play(0.03f);
        }
        if(gameState == GameState.battle && !battleMusic.isPlaying()){
            battleMusic.play();
        } else if(gameState != GameState.battle && battleMusic.isPlaying()){
            battleMusic.stop();
        }
        switch (gameState){
            case overWorld:
                overWorldInput();
                drawOverWorld();
                break;
            case pickup:
                pickupInput();
                drawOverWorld();
                dialogue.render();
                break;
            case paused:
                drawOverWorld();
                menuInput();
                menuManager.render();
                break;
            case battle:
                battleInput();
                drawBattle();
                menuManager.render();
                break;
            case cutScene:
            	drawOverWorld();
                if(interpreter.spendFrame()){
                    gameState = GameState.overWorld;
                }
            	
        }
        Input.update();
    }


    /**
     * The way that the input is taken if the game is in the overWorld state
     * (The player can walk around and no events are happening.)
     */
    private void overWorldInput() {
        int x = 0;
        int y = 0;
        if (Input.isDown(Input.RIGHT)) x += 3;
        if (Input.isDown(Input.UP)) y += 3;
        if (Input.isDown(Input.LEFT)) x -= 3;
        if (Input.isDown(Input.DOWN)) y -= 3;
        player.walk(x, y, map.getLayers());
        cam.translate(player.xOffset - cam.position.x, player.yOffset - cam.position.y);

        if(Input.isPressed(Input.SELECT)){
            gameState = GameState.pickup;
        } else
        if(Input.isPressed(Input.START)){
            gameState = GameState.paused;
        } else
        if(Input.isPressed(Input.TEST_ITERPRETER)){
        	gameState = GameState.cutScene;
        	interpreter = new SceneReader("test.scene", this);
            interpreter.start();
        } else
        	if(Input.isPressed(Input.TEST_BATTLE)){
        		gameState = GameState.battle;
        	}
        
    }

    /**
     * What happens in a frame when a material is picked up by the player
     */
    private void pickupInput() {
        if(dialogue.isNew()){
            Material material = player.detectPickup(map.getLayers());
            if(material != null){
                dialogue.pickupDialogue(material);
                menuManager.inventoryAdd(material);
            }
        }
        if (Input.isPressed(Input.SELECT)){
                gameState = GameState.overWorld;
                dialogue.next();
        }
    }

    private void menuInput() {
        int menuState = menuManager.input();
        if(menuState == Menu.EXIT){
            gameState = GameState.overWorld;
        }
    }

    private void battleInput(){
        if(Input.isPressed(Input.START)){
            gameState = GameState.paused;
        } else if(Input.isPressed(Input.TEST_ITERPRETER)){
            gameState = GameState.overWorld;
        }
        if(Input.isPressed(Input.TEST_ITERPRETER)){
            gameState = GameState.overWorld;
        }
    }

    /**
     * Draws the overworld, with the player as its center.
     */
    private void drawOverWorld() {
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tmr.setView(cam);
        tmr.render();

        batch.begin();
        player.draw(batch, player.getRegion());
        batch.end();
    }

    /**
     * Draws a battle (INCOMPLETE)
     */
    private void drawBattle(){
        cam.update();
        defaultGolem.setPosition(cam.position.x, cam.position.y);
        dummy.setPosition(cam.position.x, cam.position.y);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        defaultGolem.draw(batch);
        dummy.draw(batch);
        batch.end();
    }

    @Override
    public void say(String message) {
    	gameState = GameState.pickup;
    	dialogue.say(message);
    }
    
    @Override
    public void walk(int i, int j) {
    	player.walk(i, j);
    }
    
    @Override
    public void translateCam(int i, int j) {
    	cam.translate(i, j);	
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
