package gdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpritedObject {


    protected Texture img;
    protected float xOffset;
    protected float yOffset;

    SpritedObject(Texture texture){
        this.img = texture;
        xOffset =0;
        yOffset =0;
    }

    SpritedObject(Texture texture, float x, float y){
        this.img = texture;
        this.xOffset = x;
        this.yOffset = y;
    }

    protected void draw(SpriteBatch batch, float xOffset, float yOffset){
        batch.draw(img, this.xOffset + xOffset, this.yOffset + yOffset);
    }

    protected void draw(SpriteBatch batch){
        draw(batch, 0, 0);
    }

    protected void draw(SpriteBatch batch, TextureRegion img){
        batch.draw(img, xOffset, yOffset);
    }

    protected void setPosition(float x, float y){
        this.xOffset = x;
        this.yOffset = y;
    }
}
