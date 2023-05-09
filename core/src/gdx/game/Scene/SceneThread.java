package gdx.game.Scene;

import gdx.game.Scene.node.Start;

public class SceneThread extends Thread{

    private SceneReader sceneReader;
    public boolean doneReading;

    public SceneThread(SceneReader sceneReader){
        super();
        this.sceneReader = sceneReader;
        doneReading = false;
    }

    @Override
    public void run() {
        sceneReader.read();
        doneReading = true;
    }
}
