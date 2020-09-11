package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Golem extends SpritedObject {
    protected limb leftArm;
    protected limb rightArm;
    protected limb leftLeg;
    protected limb rightLeg;


    public Golem() {
        super(new Texture("fighters/defaultGolem.png"));
    }

    @Override
    protected void setPosition(float x, float y) {
        super.setPosition(x-MyGdxGame.V_WIDTH/MyGdxGame.SCALE + 64, y-MyGdxGame.V_HEIGHT/MyGdxGame.SCALE);
    }

    class limb extends  Appendage{
        Material foreMaterial;
        Material jointMaterial;

    }

}
