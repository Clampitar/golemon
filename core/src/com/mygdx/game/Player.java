package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * The character that the player can control in the overworld.
 */
public class Player extends Character {


    private final int MATERIAL_LAYER = 0;
    private final int COLLISION_LAYER = 1;


    public Player(Texture texture) {
        super(texture);
    }

    /**
     * Moves a player according to the x and y inputs, except if the player would
     * collide with an object on the map.
     * @param xInput how much the player wants to move left or right
     * @param yInput how much the player want to move up or down
     * @param layers the map layers that the player can collide with
     */
    public void walk(int xInput, int yInput, MapLayers layers) {
        if(detectCollision((int) xOffset + X_SCALE/2 + xInput, (int) yOffset, layers)) {
            xInput = 0;
        }
        if(detectCollision((int) xOffset + X_SCALE/2, (int) yOffset + yInput, layers)) {
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

    /**
     * Verifies if the player is in a spot where a material can be picked up.
     * @param layers the map with witch the player interacts with
     * @return the material the the player can pick up
     */
    public Material detectPickup(MapLayers layers){
        Material material;
        int detectX = (int) xOffset;
        int detectY = (int) yOffset;
        // The place where the player is in front of it, centered to its sprite's rectangle
        detectX += X_SCALE / 2;
        switch (direction){
            case Input.UP:
                detectY += Y_SCALE / 2 ;
                break;
            case Input.DOWN:
                detectY -= Y_SCALE / 2;
                break;
            case Input.RIGHT:
                detectX += X_SCALE / 2;
                break;
            case Input.LEFT:
                detectX -= X_SCALE / 2;
        }

        TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(MATERIAL_LAYER);
        TiledMapTileLayer.Cell cell = layer.getCell(detectX / X_SCALE, detectY / Y_SCALE);
        try {
            material = obtainTileMaterial(cell.getTile());
        } catch (NullPointerException e){
            material = Material.Nothing;
        }
        
        return material;
    }

    /**
     *
     * @param tile the tile whose material is retrieved
     * @return The material that is at the specific tile
     */
    private Material obtainTileMaterial(TiledMapTile tile) throws NullPointerException{
            if (tile == null) return Material.Flesh;
            switch (tile.getId()) {
                case 1:
                    return Material.Sand;
                case 3:
                    return Material.Rock;
                default:
                    return Material.Flesh;
            }

    }
}
