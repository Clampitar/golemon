package gdx.game.Interp;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import gdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = MyGdxGame.TITLE;
		config.width = MyGdxGame.V_WIDTH * MyGdxGame.SCALE;
		config.height = MyGdxGame.V_HEIGHT * MyGdxGame.SCALE;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
