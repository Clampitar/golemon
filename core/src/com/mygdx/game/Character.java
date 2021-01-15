package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character extends SpritedObject {

    protected TextureRegion region;

    protected final int X_SCALE = 32;
    protected final int Y_SCALE = 32;

    protected float walkCycle = 0;

    protected int direction = Input.DOWN;


    public Character(Texture texture) {
        super(texture);
        region = new TextureRegion(texture, 0, direction * Y_SCALE, X_SCALE, Y_SCALE);
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void walk(int xInput, int yInput) {
        if (xInput != 0 || yInput != 0) {
            if (xInput != 0 && yInput != 0) {
                //diagonals
                xOffset += xInput * Math.sin(Math.PI / 4);
                yOffset += yInput * Math.sin(Math.PI / 4);
            } else {
                xOffset += xInput;
                yOffset += yInput;
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
        } else {
            walkCycle = 0.1f;
        }
        updateSprite();
    }


    private void updateSprite() {
        int xSprite = 0;
        int ySprite = Y_SCALE * direction;
        if (walkCycle > 0.25 && walkCycle < 0.5) {
            xSprite = X_SCALE;      //assuming the spritesheet is 3x4 sprites
        } else if (walkCycle > 0.75) {
            xSprite = X_SCALE * 2;
        }
        region.setRegion(xSprite, ySprite, X_SCALE, Y_SCALE);
    }


}
