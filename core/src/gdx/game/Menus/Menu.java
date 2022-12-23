package gdx.game.Menus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.Input;
import gdx.game.TextBox;

public class Menu{

    public static final int SELECT = 0;
    public static final int EXIT = -1;
    public static final int IDLE = -2;

    protected TextBox textBox;
    protected Cursor cursor;
    protected boolean activeChild = false;
    protected SpriteBatch batch;

    public Menu(String texturePath, SpriteBatch batch, OrthographicCamera cam) {
        textBox = new TextBox(texturePath, batch, cam);

        textBox.setText(new String[]{"TODO fill the menu"});

        cursor = new Cursor();
        this.batch = batch;
    }

    public Menu(String texturePath, SpriteBatch batch, OrthographicCamera cam, float x, float y) {
        textBox = new TextBox(texturePath, batch, cam, x, y);

        textBox.setText(new String[]{"TODO fill the menu"});

        cursor = new Cursor();
    }

    public void render(){
        render(0);
    }

    public void render(int lineOffset){
        textBox.renderList(cursor.img, cursor.position, lineOffset);
    }


    public int input(){
        upDownInput();
        if(Input.isPressed(Input.SELECT)){
            return SELECT;
        }
        if(Input.isPressed(Input.START)){
            return EXIT;
        }
        return IDLE;
    }


    private void upDownInput(){
        if(Input.isPressed(Input.UP) && inBounds(cursor.position-1)){
            cursor.position--;
            if(cursor.position < 0) cursor.position = 0;
        }
        if(Input.isPressed(Input.DOWN) && inBounds((cursor.position+1))){
            cursor.position++;
        }
    }

    /**
     * Designates what happens when the select button is pressed in Input().
     * Separated from Input() to simplify @override
     */
    protected Selection cursorSelect(){
        return cursor;
    }

    protected boolean inBounds(int position){
        return position >= 0;
    }

    class Cursor implements Selection{
        protected Texture img;
        protected int position;
        Cursor(){
            img = new Texture("menu/cursor.png");
            position = 0;
        }

    }
}
