package gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class TextBox extends SpritedObject {

    private final SpriteBatch batch;
    private final OrthographicCamera cam;
    private BitmapFont font;
    private String[] text;

    private float textX;
    private float textY;

    private int letterCounter = 1;
    private int lineCounter = 0;

    private final int LETTERS_PER_LINE;
    public final int LINE_HEIGHT = 9;

    /**
     *
     * @param texturePath
     * @param batch
     * @param cam
     * @param lettersPerLine
     */
    public TextBox(String texturePath, SpriteBatch batch, OrthographicCamera cam, int lettersPerLine){
        super(new Texture(texturePath), -MyGdxGame.V_WIDTH / 2f, -MyGdxGame.V_HEIGHT / 2f);
        this.batch= batch;
        this.cam = cam;
        LETTERS_PER_LINE = lettersPerLine;
        defaultFont();
        textX = xOffset + 6;
        textY = yOffset + img.getHeight() - 6;
    }

    public TextBox(String texturePath, SpriteBatch batch, OrthographicCamera cam) {
        this(texturePath, batch, cam, 48);
    }

    public TextBox(String texturePath, SpriteBatch batch, OrthographicCamera cam, float xOffset, float yOffset) {
        this(texturePath, batch, cam);
        this.xOffset += xOffset;
        this.yOffset += yOffset;
        textX += xOffset;
        textY += yOffset;
    }

    public void render(){
        float camX = cam.position.x;
        float camY = cam.position.y;
        batch.begin();
        batch.draw(img, camX + xOffset, camY + yOffset);
        font.draw(batch, text[lineCounter].substring(0, Math.min(letterCounter, LETTERS_PER_LINE)), camX +textX, camY + textY);
        if(letterCounter > LETTERS_PER_LINE){
            font.draw(batch, text[lineCounter].substring(LETTERS_PER_LINE, letterCounter).trim(), camX +textX, camY + textY - LINE_HEIGHT);
        }
        batch.end();
        letterCounter++;
        letterCounter = Math.min(letterCounter,text[lineCounter].length());
    }

    public void renderList(Texture cursorImg, int cursorPosition, int lineOffset){
        float camX = cam.position.x - lineOffset*LINE_HEIGHT;
        float camY = cam.position.y - lineOffset*LINE_HEIGHT;
        batch.begin();
        batch.draw(img, camX + xOffset, camY + yOffset);
        for(int i = 0; i < text.length; i++) {
            font.draw(batch, text[i], camX +textX, camY + textY -LINE_HEIGHT*i);
        }
        batch.draw(cursorImg, camX + textX -1, camY + textY - (LINE_HEIGHT >> 1) - cursorPosition * LINE_HEIGHT);
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
    
    public void say(String message) {
    	text = new String[1];
    	text[0] = message;
    	letterCounter = 2;
    }

    private void defaultFont(){
        font = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ElfBoyClassic-PKZgZ.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.color = new Color(0x287631ff);
        fontParameter.borderWidth = 0.95f;
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

    public boolean isNew(){
        return letterCounter == 1;
    }
}
