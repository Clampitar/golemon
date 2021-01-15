package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class Input extends InputAdapter {

    public static boolean[] keys;
    public static boolean[] pKeys;

    public static final int NUM_KEYS = 7;

    public static final int RIGHT = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;

    public static final int SELECT = 4;
    public static final int START = 5;
    public static final int TEST_BATTLE = 6;

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
        return findKey(keycode, true);
    }

    @Override
    public boolean keyUp(int keycode) {
        return findKey(keycode, false);
    }

    public boolean findKey(int keycode, boolean b){
        switch (keycode) {
            case Keys.RIGHT:
                setKey(RIGHT, b);
                break;
            case Keys.UP:
                setKey(UP, b);
                break;
            case Keys.LEFT:
                setKey(LEFT, b);
                break;
            case Keys.DOWN:
                setKey(DOWN, b);
                break;
            case Keys.Z:
                setKey(SELECT, b);
                break;
            case Keys.X:
                setKey(START, b);
                break;
            case Keys.M:
                setKey(TEST_BATTLE, b);
                break;
            default: return false;
        }
        return true;
    }
}
