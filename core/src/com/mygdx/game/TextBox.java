package com.mygdx.game;

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

    public TextBox(Texture texture, SpriteBatch batch, OrthographicCamera cam) {
        this.img = texture;
        this.batch= batch;
        this.cam = cam;
        font = new BitmapFont();
        text = "sdsdsdsdsdsdsd";
        renderX = -MyGdxGame.V_WIDTH / 2f;
        renderY = -MyGdxGame.V_HEIGHT / 2f;
        textX = renderX + 6;
        textY = renderY + texture.getHeight() - 6;
    }


    public void render(){
        float x = cam.position.x;
        float y = cam.position.y;
        batch.begin();
        batch.draw(img, x + renderX, y + renderY);
        font.draw(batch, text, x +textX, y + textY);
        batch.end();
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public Texture getImg() {
        return img;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public void setRenderX(float renderX) {
        this.renderX = renderX;
    }

    public void setRenderY(float renderY) {
        this.renderY = renderY;
    }

    public void setTextX(float textX) {
        this.textX = textX;
    }

    public void setTextY(float textY) {
        this.textY = textY;
    }

    public void setText(String text) {
        this.text = text;
    }
}
