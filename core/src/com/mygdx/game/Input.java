package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class Input extends InputAdapter {

    public static boolean keys[];
    public static boolean pkeys[];

    public static final int NUM_KEYS = 4;
    public static final int RIGHT = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;

    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public static void setKey(int i, boolean b){
        keys[i] = b;
    }

    public static boolean isDown(int i){
        return keys[i];
    }

    public static boolean ispressed(int i){
        return keys[i] && !pkeys[i];
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.RIGHT){
            setKey(RIGHT, true);
        }
        if(keycode == Keys.UP){
            setKey(UP, true);
        }
        if(keycode == Keys.LEFT){
            setKey(LEFT, true);
        }
        if(keycode == Keys.DOWN){
            setKey(DOWN, true);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.RIGHT){
            setKey(RIGHT, false);
        }
        if(keycode == Keys.UP){
            setKey(UP, false);
        }
        if(keycode == Keys.LEFT){
            setKey(LEFT, false);
        }
        if(keycode == Keys.DOWN){
            setKey(DOWN, false);
        }
        return true;
    }
}
