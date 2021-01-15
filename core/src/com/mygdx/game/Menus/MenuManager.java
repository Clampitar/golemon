package com.mygdx.game.Menus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Golem;
import com.mygdx.game.Input;
import com.mygdx.game.Material;

import java.util.ArrayList;

public class MenuManager {
    private CraftingMenu craftingMenu;
    private InventoryMenu inventoryMenu;
    private boolean limbSelected = false;//placeholder
    public MenuManager(SpriteBatch batch, OrthographicCamera cam){
        craftingMenu = new CraftingMenu("menu/menu.png", batch, cam, new Golem());
        inventoryMenu = new InventoryMenu("menu/menu.png", batch, cam, 150f, 0f);
    }

    public void input(){
        if(Input.isPressed(Input.SELECT)){
            if(limbSelected){
                craftingMenu.receiveMaterial(inventoryMenu.cursorSelect());
            }
            limbSelected = !limbSelected;
        }
        if(limbSelected){
            inventoryMenu.input();
        } else {
            craftingMenu.input();
        }
    }

    public void render(){
        if(limbSelected){
            inventoryMenu.render();
        } else {
            craftingMenu.render();
        }
    }

    public void inventoryAdd(Material material){
        inventoryMenu.add(material);
    }


}
