package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Menu{

    protected TextBox textBox;
    protected Cursor cursor;
    protected Menu childMenu = null;
    protected boolean activeChild = false;

    public Menu(String texturePath, SpriteBatch batch, OrthographicCamera cam) {
        textBox = new TextBox(texturePath, batch, cam);

        textBox.setText(new String[]{"Menu option", "second option", "golems",
                "sand", "I hate sand.", "It gets everywhere."});

        cursor = new Cursor();
    }

    public Menu(String texturePath, SpriteBatch batch, OrthographicCamera cam, float x, float y) {
        textBox = new TextBox(texturePath, batch, cam, x, y);

        textBox.setText(new String[]{"Menu option", "second option", "golems",
                "sand", "I hate sand.", "It gets everywhere."});

        cursor = new Cursor();
    }

    public void render(){
        textBox.renderList(cursor.img, cursor.position);
        //textBox.draw(cursor.getImg(), -2, cursor.position * textBox.LINE_HEIGHT);
    }

    public void render(ArrayList<Material> inventory){
        String[] inv = new String[inventory.size()];
        for (int i = 0; i < inv.length; i++) {
            inv[i] = inventory.get(i).name();
        }
        textBox.setText(inv);
        textBox.renderList(cursor.img, cursor.position);
        //textBox.draw(cursor.getImg(), -2, cursor.position * textBox.LINE_HEIGHT);
    }

    public void input(){
        upDownInput();
        if(Input.isPressed(Input.SELECT)){
            cursorSelect();
        }
    }

    public void input(Menu parentMenu){
        upDownInput();
        if(Input.isPressed(Input.SELECT)){
            cursorSelect(parentMenu);
        }
    }

    private void upDownInput(){
        if(Input.isPressed(Input.UP)){
            cursor.position--;
            if(cursor.position < 0) cursor.position = 0;
        }
        if(Input.isPressed(Input.DOWN)){
            cursor.position++;
        }
    }

    /**
     * Designates what happens when the select button is pressed in Input().
     * Separated from Input() to simplify @override
     */
    protected void cursorSelect(){

    }

    protected int cursorSelect(Menu parentMenu){
        parentMenu.activeChild = false;
        return cursor.position;
    }

    class Cursor{
        Texture img;
        protected int position;
        Cursor(){
            img = new Texture("menu/cursor.png");
            position = 0;
        }

        public Texture getImg() {
            return img;
        }

        public int getPosition() {
            return position;
        }
    }
}
