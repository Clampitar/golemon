package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Opponent extends SpritedObject {
    

    public Opponent() {
        super(new Texture("fighters/dummy.png"),
                MyGdxGame.V_WIDTH * MyGdxGame.SCALE - 128,
                MyGdxGame.V_HEIGHT * MyGdxGame.SCALE - 128);
    }
}
