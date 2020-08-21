package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Menu{

    private TextBox textBox;
    private Cursor cursor;

    public Menu(String texturePath, SpriteBatch batch, OrthographicCamera cam) {
        textBox = new TextBox(texturePath, batch, cam);

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
        if(Input.isPressed(Input.UP)){
            cursor.position--;
            if(cursor.position < 0) cursor.position = 0;
        }
        if(Input.isPressed(Input.DOWN)){
            cursor.position++;
        }
    }

    class Cursor{
        Texture img;
        int position;
        public Cursor(){
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
