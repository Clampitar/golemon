package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player extends Character {



    public Player(Texture texture) {
        super(texture);
    }

    public void walk(int xInput, int yInput, MapLayers layers) {
        if(detectCollisionX((int)x + xInput, (int)y + yInput, layers)) {
            super.walk(xInput, yInput);
        }
    }

    public boolean detectCollisionX(int x, int y, MapLayers layers){
        TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(1);
        TiledMapTileLayer.Cell cell = layer.getCell(x*X_SCALE, y*Y_SCALE);
        layer.setOpacity(0.2f);
        System.out.println("Cell:   "+cell);
        return layer
                .getCell(x, y)
        == null;
    }

    public boolean detectCollisionY(int x, int y, MapLayers layers){
        return false;
    }
}
