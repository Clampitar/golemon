package gdx.game.Menus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.Golem;
import gdx.game.Input;
import gdx.game.Material;

import java.util.ArrayList;

public class MenuManager {
	private CraftingMenu craftingMenu;
	private InventoryMenu inventoryMenu;
	private boolean limbSelected = false;// placeholder

	public MenuManager(SpriteBatch batch, OrthographicCamera cam, Golem golem) {
		craftingMenu = new CraftingMenu("menu/menu.png", batch, cam, golem);
		inventoryMenu = new InventoryMenu("menu/menu.png", batch, cam, 150f, 0f);
	}

	public int input() {
		int input;
		if (limbSelected) {
			input = inventoryMenu.input();
			if (input == Menu.SELECT) {
				craftingMenu.receiveMaterial(inventoryMenu.cursorSelect());
				limbSelected = !limbSelected;
			} else if (input == Menu.EXIT) {
				limbSelected = false;
				input = Menu.IDLE;
			}
		} else {
			input = craftingMenu.input();
			if(input == Menu.SELECT){
				limbSelected = true;
			}
		}

		return input;
	}

	public void render() {
		craftingMenu.render();
		if (limbSelected) {
			inventoryMenu.render();
		}
	}

	public void inventoryAdd(Material material) {
		inventoryMenu.add(material);
	}

}
