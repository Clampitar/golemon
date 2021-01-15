package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    @Override
    protected void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    /**
     * to be used in menus
     * @return A list of the golem's limb's and the material they are made of
     */
    public String[] toList(){
        String[] list = new String[5];
        list[0] = "torso:(TODO implement torso)";
        list[1] = "left leg: " + leftLeg.jointMaterial;
        list[2] = "left arm: " + leftArm.jointMaterial;
        list[3] = "right leg: " + rightLeg.jointMaterial;
        list[4] = "right arm: " + rightArm.jointMaterial;
        return list;
    }

    public void receiveMaterial(Material material, int position){
        switch (position){
            case 1:
                leftLeg.jointMaterial = material;
                break;
            case 2:
                leftArm.jointMaterial = material;
                break;
            case 3:
                rightLeg.jointMaterial = material;
                break;
            case 4:
                rightArm.jointMaterial = material;
                break;
        }
        System.out.println("received "+material+" at "+position);
    }

    class limb extends  Appendage{
        Material jointMaterial = Material.nothing;
    }

}
