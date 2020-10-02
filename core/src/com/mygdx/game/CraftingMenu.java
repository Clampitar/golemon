package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CraftingMenu extends Menu{

    Golem golem;


    public CraftingMenu(String texturePath, SpriteBatch batch, OrthographicCamera cam, Golem golem) {
        super(texturePath, batch, cam);
        this.golem = golem;
        childMenu = new Menu(texturePath, batch, cam, 100f, 0f);
    }

    @Override
    public void render() {
        if(activeChild){
            childMenu.render();
        }
            textBox.setText(golem.toList());
            textBox.renderList(cursor.img, cursor.position);
    }

    @Override
    public void input() {
        if(activeChild){
            childMenu.input(this);
            return;
        }
        super.input();
    }

    @Override
    protected void cursorSelect() {
        activeChild = true;
    }
}
