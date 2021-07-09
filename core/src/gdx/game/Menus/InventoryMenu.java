package gdx.game.Menus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.Material;

import java.util.ArrayList;

public class InventoryMenu extends Menu {
    private ArrayList<Material> inventory;

    public InventoryMenu(String texturePath, SpriteBatch batch, OrthographicCamera cam, float x, float y) {
        super(texturePath, batch, cam, x, y);
        inventory = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            inventory.add(Material.Rock);   //placeholder
    }

    public void add(Material material){
        inventory.add(material);
    }

    /**
     * Renders the player's inventory
     */
    @Override
    public void render(){
        render(0);
    }

    @Override
    public void render(int lineOffset) {
        String[] inv = new String[inventory.size()];
        for (int i = 0; i < inv.length; i++) {
            inv[i] = inventory.get(i).name();
        }
        textBox.setText(inv);
        super.render(lineOffset);
    }

    @Override
    protected boolean inBounds(int position) {
        return super.inBounds(position) &&
                position < inventory.size();
    }

    protected Material cursorSelect() {
        return inventory.get(cursor.position);
    }

}
