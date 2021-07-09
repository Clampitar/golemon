package gdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Golem extends SpritedObject {
    protected limb leftArm = new limb("leftArm");
    protected limb rightArm = new limb("rightArm");
    protected limb leftLeg = new limb("leftLeg");
    protected limb rightLeg = new limb("rightLeg");

    public Golem() {
        super(new Texture("fighters/defaultGolemArmless.png"));
    }

    @Override
    protected void setPosition(float x, float y) {
        super.setPosition(x-MyGdxGame.V_WIDTH/MyGdxGame.SCALE + 64, y-MyGdxGame.V_HEIGHT/MyGdxGame.SCALE);
    }

    @Override
    protected void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(leftArm.img, xOffset, yOffset);
        batch.draw(rightArm.img, xOffset, yOffset);
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
                leftLeg.switchMaterial(material);
                break;
            case 2:
                leftArm.switchMaterial(material);
                break;
            case 3:
                rightLeg.switchMaterial(material);
                break;
            case 4:
                rightArm.switchMaterial(material);
                break;
        }
        System.out.println("received "+material+" at "+position);
    }

    class limb extends  Appendage{
        private final String LIMB_PATH = "fighters/";
        Material jointMaterial;

        protected Texture img;
        String type;
        public limb(String type){
            this.type=type;
            jointMaterial = Material.Sand;
            updateTexture();
        }
        void switchMaterial(Material material){
            jointMaterial=material;
            System.out.println( type+" switches to "+jointMaterial);
            updateTexture();
        }

        private void updateTexture(){

                img = new Texture(LIMB_PATH + type + jointMaterial + ".png");
                System.out.println("Path is now "+LIMB_PATH + type + jointMaterial + ".png");
        }
    }

}
