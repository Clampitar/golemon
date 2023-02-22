package gdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * faces the players during the Battle state
 */
public class Opponent extends SpritedObject {
    

    public Opponent() {
        this(0,0);
    }

    public Opponent(float x, float y) {
        super(new Texture("fighters/dummy.png"),
                x + MyGdxGame.V_WIDTH * - 64,
                y + MyGdxGame.V_HEIGHT * MyGdxGame.SCALE - 64);
    }

    @Override
    protected void setPosition(float x, float y) {
        super.setPosition(x+MyGdxGame.V_WIDTH/MyGdxGame.SCALE - 128, y +MyGdxGame.V_HEIGHT - 256);
    }
}
