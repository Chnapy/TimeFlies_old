package timeflies.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import test.MainTest;

public class DesktopLauncher {

	//Resolution HD
	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 720;

	public static void main(String[] arg) {

		LwjglApplication application = new LwjglApplication(new MainTest(), getConfig(
				"TimeFlies",
				false
		));
	}

	private static LwjglApplicationConfiguration getConfig(String title, boolean fullscreen) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = title;
		config.useGL30 = false;
		if (!fullscreen) {
			config.width = WINDOW_WIDTH;
			config.height = WINDOW_HEIGHT;
		}
		config.fullscreen = fullscreen;

		return config;
	}
}
