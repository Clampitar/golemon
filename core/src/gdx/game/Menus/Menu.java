package gdx.game.Menus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.Input;
import gdx.game.TextBox;

class Menu{

    protected TextBox textBox;
    protected Cursor cursor;
    protected boolean activeChild = false;

    public Menu(String texturePath, SpriteBatch batch, OrthographicCamera cam) {
        textBox = new TextBox(texturePath, batch, cam);

        textBox.setText(new String[]{"TODO fill the menu"});

        cursor = new Cursor();
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


    public void input(){
        upDownInput();
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
