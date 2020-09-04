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

    class limb extends  Appendage{
        Material foreMaterial;
        Material jointMaterial;

    }

}
