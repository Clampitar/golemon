package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextBox {
    //anything that contains text

    private Texture img;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private BitmapFont font;
    private String text;

    private float renderX;
    private float renderY;
    private float textX;
    private float textY;

    private int letterCounter = 0;

    public TextBox(String texturePath, SpriteBatch batch, OrthographicCamera cam) {
        this.img = new Texture(texturePath);
        this.batch= batch;
        this.cam = cam;
        font = new BitmapFont();
        //FileHandle fileHandle = new FileHandle("fonts/AGoblinAppears-o2aV.ttf");
        //FileHandle fileHandle = Gdx.files.internal("fonts/AGoblinAppears-o2aV.ttf");
        //font = new BitmapFont(fileHandle);
        text = "123456789";
        renderX = -MyGdxGame.V_WIDTH / 2f;
        renderY = -MyGdxGame.V_HEIGHT / 2f;
        textX = renderX + 6;
        textY = renderY + img.getHeight() - 6;
    }


    public void render(){
        float x = cam.position.x;
        float y = cam.position.y;
        batch.begin();
        batch.draw(img, x + renderX, y + renderY);
        font.draw(batch, text.substring(0, Math.min(letterCounter,text.length())), x +textX, y + textY);
        batch.end();
        letterCounter++;
    }

    /**
     * resets the properties of the textBox so that new dialogue appears corectly
     * (for speed, this sould only be called once, when the dialogue box is closed
     * or reset)
     */
    public void unRender(){
        letterCounter = 0;
    }


}
