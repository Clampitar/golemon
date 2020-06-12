package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu {
    private Texture img;
    private SpriteBatch batch;
    private OrthographicCamera cam;

    public Menu(Texture img, SpriteBatch batch, OrthographicCamera cam){
        this.img = img;
        this.batch = batch;
        this.cam = cam;
    }

    public void render(){
        batch.begin();
        batch.draw(img, (cam.position.x + (MyGdxGame.V_WIDTH / 2f)) - img.getWidth(), cam.position.y - (MyGdxGame.V_HEIGHT / 2f));
        batch.end();
    }
}
