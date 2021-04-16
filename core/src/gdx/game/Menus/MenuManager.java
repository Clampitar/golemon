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

	public void input() {
		if (Input.isPressed(Input.SELECT)) {
			if (limbSelected) {
				craftingMenu.receiveMaterial(inventoryMenu.cursorSelect());
			}
			limbSelected = !limbSelected;
		}
		if (limbSelected) {
			inventoryMenu.input();
		} else {
			craftingMenu.input();
		}
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
