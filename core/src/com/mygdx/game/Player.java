package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;

/**
 * The character that the player can control in the overworld.
 */
public class Player extends Character {

    private ArrayList<Material> inventory;

    private final int MATERIAL_LAYER = 0;
    private final int COLLISION_LAYER = 1;


    public Player(Texture texture) {
        super(texture);
        inventory = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            inventory.add(Material.rock);
        }
    }

    /**
     * Moves a player according to the x and y inputs, except if the player would
     * collide with an object on the map.
     * @param xInput how much the player wants to move left or right
     * @param yInput how much the player want to move up or down
     * @param layers the map layers that the player can collide with
     */
    public void walk(int xInput, int yInput, MapLayers layers) {
        if(detectCollision((int)x + X_SCALE/2 + xInput, (int)y, layers)) {
            xInput = 0;
        }
        if(detectCollision((int)x + X_SCALE/2, (int)y + yInput, layers)) {
            yInput = 0;
        }
        super.walk(xInput, yInput);
    }

    /**
     *
     * @param x the player's x position
     * @param y the player's y position
     * @param layers the map layers that the player can collide with
     * @return whether the player in their current position collides with an object on the map
     */
    public boolean detectCollision(int x, int y, MapLayers layers){
        x /= X_SCALE;
        y /= Y_SCALE;
        TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(COLLISION_LAYER);
        TiledMapTileLayer.Cell cell = layer.getCell(x, y);
        return cell != null;
    }

    public Material detectPickup(MapLayers layers){
        Material material;
        int detectX = (int) x;
        int detectY = (int) y;
        // The place where the player is in front of it, centered to its sprite's rectangle
        switch (direction){
            case Input.UP:
                detectY += Y_SCALE - 1;
            case Input.DOWN:
                detectX += X_SCALE / 2;
                break;
            case Input.RIGHT:
                detectX += X_SCALE - 1;
                case Input.LEFT:
                    detectY += Y_SCALE / 2;
        }

        TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(MATERIAL_LAYER);
        TiledMapTileLayer.Cell cell = layer.getCell(detectX / X_SCALE, detectY / Y_SCALE);
        try {
            material = obtainTileMaterial(cell.getTile());
        } catch (NullPointerException e){
            material = Material.nothing;
        }


        return material;
    }

    public ArrayList<Material> getInventory() {
        return inventory;
    }

    private Material obtainTileMaterial(TiledMapTile tile){
        if(tile == null) return  Material.rock;
        switch (tile.getId()){
            case 1: return  Material.sand;
            default:return Material.flesh;
        }
    }
}
