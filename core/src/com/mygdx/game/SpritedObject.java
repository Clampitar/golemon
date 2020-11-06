package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpritedObject {


    protected Texture img;
    protected float x;
    protected float y;

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

    protected void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
}
