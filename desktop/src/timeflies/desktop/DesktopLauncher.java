package timeflies.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import test.MainTest;

public class DesktopLauncher {
	
	private static final int WINDOW_WIDTH = MainTest.WINDOW_WIDTH;
	private static final int WINDOW_HEIGHT = MainTest.WINDOW_HEIGHT;

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TimeFlies";
		config.useGL30 = false;
		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;

		LwjglApplication application = new LwjglApplication(new MainTest(), config);
	}
}
