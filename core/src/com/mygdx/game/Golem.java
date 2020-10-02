package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Golem extends SpritedObject {
    protected limb leftArm = new limb();
    protected limb rightArm = new limb();
    protected limb leftLeg = new limb();
    protected limb rightLeg = new limb();


    public Golem() {
        super(new Texture("fighters/defaultGolem.png"));
    }

    @Override
    protected void setPosition(float x, float y) {
        super.setPosition(x-MyGdxGame.V_WIDTH/MyGdxGame.SCALE + 64, y-MyGdxGame.V_HEIGHT/MyGdxGame.SCALE);
    }

    /**
     * to be used in menus
     * @return A list of the golem's limbs aand the material they are made of
     */
    public String[] toList(){
        String[] list = new String[5];
        list[0] = "torso:(TODO implement torso)";
        list[1] = "left leg: " + leftLeg.foreMaterial;
        list[2] = "left arm: " + leftArm.foreMaterial;
        list[3] = "right leg: " + rightLeg.foreMaterial;
        list[4] = "right arm: " + rightArm.foreMaterial;
        return list;
    }

    class limb extends  Appendage{
        Material foreMaterial = Material.nothing;
        Material jointMaterial = Material.nothing;

    }

}
