package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class TextBox {
    //anything that contains text

    private Texture img;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private BitmapFont font;
    private String[] text;

    private float renderX;
    private float renderY;
    private float textX;
    private float textY;

    private int letterCounter = 0;
    private int lineCounter = 0;

    public TextBox(String texturePath, SpriteBatch batch, OrthographicCamera cam) {
        this.img = new Texture(texturePath);
        this.batch= batch;
        this.cam = cam;
        font = new BitmapFont();
        //FileHandle fileHandle = Gdx.files.internal("fonts/arial24.fnt");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Elfboyclassic-PKZgZ.ttf"));
        font = generator.generateFont(new FreeTypeFontGenerator.FreeTypeFontParameter());
        generator.dispose();
        //font = new BitmapFont(fileHandle);
        defaultDialogue();
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
        font.draw(batch, text[lineCounter].substring(0, Math.min(letterCounter,text[lineCounter].length())), x +textX, y + textY);
        batch.end();
        letterCounter++;
    }

    /**
     * resets the properties of the textBox so that new dialogue appears corectly
     * (for speed, this sould only be called once, when the dialogue box is closed
     * or reset)
     */
    public boolean next(){
        letterCounter = 0;
        lineCounter++;
        if(lineCounter >= text.length){
            lineCounter = 0;
            return true;
        }
        return false;
    }

    private void defaultDialogue(){
        text = new String[2];
        text[0] = ("Hello,");
        text[1] = ("it's ya boy.");

    }

    public void loadCutScene(String fileName){
        FileHandle file = Gdx.files.internal(fileName);
        text = file.readString().split("\n");


    }


}
