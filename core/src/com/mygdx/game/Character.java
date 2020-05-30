package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character {

    protected Texture texture;
    protected TextureRegion region;

    protected final int X_SCALE = 32;
    protected final int Y_SCALE = 32;

    protected float x = 0;
    protected float y = 0;
    protected float walkCycle = 0;

    protected int direction = Input.DOWN;


    public Character(Texture texture) {
        this.texture = texture;
        region = new TextureRegion(texture, 0, direction * Y_SCALE, X_SCALE, Y_SCALE);
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void walk(int xInput, int yInput) {
        if (xInput != 0 || yInput != 0)
            if (xInput != 0 && yInput != 0) {
                //diagonals
                x += xInput * Math.sin(Math.PI / 4);
                y += yInput * Math.sin(Math.PI / 4);
            } else {
                x += xInput;
                y += yInput;
                if (xInput > 0) {
                    direction = Input.RIGHT;
                }
                if (xInput < 0) {
                    direction = Input.LEFT;
                }
                if (yInput > 0) {
                    direction = Input.UP;
                }
                if (yInput < 0) {
                    direction = Input.DOWN;
                }
            }
        walkCycle += MyGdxGame.STEP;
        walkCycle %= 1;
        updateSprite();
    }


    private void updateSprite() {
        int xSprite = 0;
        int ySprite = 0;
        ySprite = Y_SCALE * direction;
        if (walkCycle > 0.25 && walkCycle < 0.5) {
            xSprite = X_SCALE * 1;
        } else if (walkCycle > 0.75) {
            xSprite = X_SCALE * 2;
        }
        region.setRegion(xSprite, ySprite, X_SCALE, Y_SCALE);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
