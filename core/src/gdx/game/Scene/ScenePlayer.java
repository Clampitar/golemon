package gdx.game.Scene;

/**
 * Can play a .scene file
 */
public interface ScenePlayer {

	public void say(String s);
	
	public void walk(int i, int j);
	
	public void translateCam(int i, int j);

}
