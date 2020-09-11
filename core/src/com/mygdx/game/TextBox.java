package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import java.util.ArrayList;


public class TextBox {

    private Texture img;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private BitmapFont font;
    private String[] text;

    private float renderX;
    private float renderY;
    private float textX;
    private float textY;

    private int letterCounter = 1;
    private int lineCounter = 0;

    private final int LETTERS_PER_LINE;
    private final int LINE_HEIGHT = 9;

    public TextBox(String texturePath, SpriteBatch batch, OrthographicCamera cam, int lettersPerLine){
        this.img = new Texture(texturePath);
        this.batch= batch;
        this.cam = cam;
        LETTERS_PER_LINE = lettersPerLine;
        renderX = -MyGdxGame.V_WIDTH / 2f;
        renderY = -MyGdxGame.V_HEIGHT / 2f;
    }

    public TextBox(String texturePath, SpriteBatch batch, OrthographicCamera cam) {
        this.img = new Texture(texturePath);
        this.batch= batch;
        this.cam = cam;

        defaultFont();

        LETTERS_PER_LINE = 48;
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
        font.draw(batch, text[lineCounter].substring(0, Math.min(letterCounter, LETTERS_PER_LINE)), x +textX, y + textY);
        if(letterCounter > LETTERS_PER_LINE){
            font.draw(batch, text[lineCounter].substring(LETTERS_PER_LINE, letterCounter).trim(), x +textX, y + textY - LINE_HEIGHT);
        }
        batch.end();
        letterCounter++;
        letterCounter = Math.min(letterCounter,text[lineCounter].length());
    }


    public void renderList(Texture cursorImg, int cursorPosition){
        float x = cam.position.x;
        float y = cam.position.y;
        batch.begin();
        batch.draw(img, x + renderX, y + renderY);
        for(int i = 0; i < text.length; i++) {
            font.draw(batch, text[i], x +textX, y + textY -LINE_HEIGHT*i);
        }
        batch.draw(cursorImg, x + textX -1, y + textY - (LINE_HEIGHT >> 1) - cursorPosition * LINE_HEIGHT);
        batch.end();
    }

    /**
     * resets the properties of the textBox so that new dialogue appears correctly
     * (for speed, this should only be called once, when the dialogue box is closed
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


    public void pickupDialogue(Material material){
        text = new String[1];
        text[0] = "Picked up "+material.name()+".";
    }

    private void defaultFont(){
        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Elfboyclassic-PKZgZ.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.color = new Color(0x287631ff);
        fontParameter.borderWidth = 1;
        fontParameter.borderColor = new Color(0xedd908ff);
        font = generator.generateFont(fontParameter);
        generator.dispose();
    }

    /**
     * loads a cutscene located at the internal path+filename.
     * Currently only loads dialogue.
     * <br/> TODO load non-dialogue things
     * @param fileName a text file containing dialogue. Each
     * line of the file is a line of dialogue.
     */
    public void loadCutScene(String fileName){
        FileHandle file = Gdx.files.internal(fileName);
        text = file.readString().split("\n");
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public void setText(ArrayList<String> text){
        this.text = text.toArray(new String[0]);
    }

    public boolean isNew(){
        return letterCounter == 1;
    }
}
