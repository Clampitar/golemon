package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpritedObject {

    protected final float DEFAULT_X = 0;

    protected Texture img;
    protected float x;
    protected float y;

    SpritedObject(String texturePath){
        this(new Texture(texturePath));
    }

    SpritedObject(Texture texture){
        this.img = texture;
        x=0;
        y=0;
    }

    SpritedObject(Texture texture, float x, float y){
        this.img = texture;
        this.x = x;
        this.y = y;
    }

    protected void draw(SpriteBatch batch){
        batch.draw(img, x, y);
    }

    protected void draw(SpriteBatch batch, TextureRegion img){
        batch.draw(img, x, y);
    }
}
