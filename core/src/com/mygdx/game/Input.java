package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class Input extends InputAdapter {

    public static boolean[] keys;
    public static boolean[] pKeys;

    public static final int NUM_KEYS = 6;
    public static final int RIGHT = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;

    public static final int SELECT = 4;
    public static final int START = 5;

    static {
        keys = new boolean[NUM_KEYS];
        pKeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        System.arraycopy(keys, 0, pKeys, 0, NUM_KEYS);
    }

    public static void setKey(int i, boolean b){
        keys[i] = b;
    }

    public static boolean isDown(int i){
        return keys[i];
    }

    public static boolean isPressed(int i){
        return keys[i] && !pKeys[i];
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.RIGHT:
                setKey(RIGHT, true);
                break;
            case Keys.UP:
                setKey(UP, true);
                break;
            case Keys.LEFT:
                setKey(LEFT, true);
                break;
            case Keys.DOWN:
                setKey(DOWN, true);
                break;
            case Keys.Z:
                setKey(SELECT, true);
                break;
            case Keys.X:
                setKey(START, true);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.RIGHT:
                setKey(RIGHT, false);
                break;
            case Keys.UP:
                setKey(UP, false);
                break;
            case Keys.LEFT:
                setKey(LEFT, false);
                break;
            case Keys.DOWN:
                setKey(DOWN, false);
                break;
            case Keys.Z:
                setKey(SELECT, false);
                break;
            case Keys.X:
                setKey(START, false);
                break;
        }
        return true;
    }
}
