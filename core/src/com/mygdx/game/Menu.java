package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu extends TextBox {
    private Texture img;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private BitmapFont font;

    public Menu(Texture img, SpriteBatch batch, OrthographicCamera cam){
        this.img = img;
        this.batch = batch;
        this.cam = cam;
        font = new BitmapFont();
    }

    public void render(){
        batch.begin();
        batch.draw(img, (cam.position.x - (MyGdxGame.V_WIDTH / 2f)), cam.position.y - (MyGdxGame.V_HEIGHT / 2f));
        font.draw(batch, "Hello bitmap!", cam.position.x, cam.position.y);
        batch.end();
    }
}
