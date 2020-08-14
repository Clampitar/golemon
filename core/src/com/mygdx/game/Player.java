package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

public class Player extends Character {

    private ArrayList<? extends Material> inventory = new ArrayList<>();

    public Player(Texture texture) {
        super(texture);
    }

    public void walk(int xInput, int yInput, MapLayers layers) {
        if(detectCollision((int)x + X_SCALE/2 + xInput, (int)y, layers)) {
            xInput = 0;
        }
        if(detectCollision((int)x + X_SCALE/2, (int)y + yInput, layers)) {
            yInput = 0;
        }
        super.walk(xInput, yInput);
    }

    public boolean detectCollision(int x, int y, MapLayers layers){
        x /= X_SCALE;
        y /= Y_SCALE;
        TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(1);
        TiledMapTileLayer.Cell cell = layer.getCell(x, y);
        return cell != null;
    }

}
