package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character {

    protected Texture texture;
    protected TextureRegion region;

    protected final int X_SCALE = 32;
    protected final int Y_SCALE = 32;
    protected final int WALK_CYCLE_LENGTH = 60;

    protected int x = 0;
    protected int y = 0;
    protected int walkCycle = 0;



    public Character(Texture texture){
        this.texture = texture;
        region = new TextureRegion(texture, 0, 0, X_SCALE ,Y_SCALE);
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void walk(int xInput, int yInput){
        x += xInput;
        y += yInput;
        walkCycle++;
        walkCycle %= WALK_CYCLE_LENGTH;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
