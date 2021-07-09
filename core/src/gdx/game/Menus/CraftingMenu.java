package gdx.game.Menus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gdx.game.Golem;
import gdx.game.Material;

public class CraftingMenu extends Menu{

    Golem golem;
    static final int NUM_LIMBS = 5;

    public CraftingMenu(String texturePath, SpriteBatch batch, OrthographicCamera cam, Golem golem) {
        super(texturePath, batch, cam);
        this.golem = golem;
    }

    @Override
    public void render() {
            textBox.setText(golem.toList());
            textBox.renderList(cursor.img, cursor.position, 0);
    }

    @Override
    public void input() {
        super.input();
    }

    @Override
    protected Selection cursorSelect() {
        activeChild = true;
        return cursor;
    }

    @Override
    protected boolean inBounds(int position) {
        return super.inBounds(position) && position < NUM_LIMBS;
    }

    public void receiveMaterial(Material material){
        golem.receiveMaterial(material, cursor.position);
    }

}
