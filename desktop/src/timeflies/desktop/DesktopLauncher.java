package timeflies.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import test.MainTest;

public class DesktopLauncher {

	private static final boolean FULLSCREEN = false;
	private static final DisplayMode dm = LwjglApplicationConfiguration.getDesktopDisplayMode();
	//Resolution HD
	private static final int WINDOW_WIDTH = FULLSCREEN ? dm.width : dm.width - 15;
	private static final int WINDOW_HEIGHT = FULLSCREEN ? dm.height : dm.height - 75;

	public static void main(String[] arg) {

		LwjglApplication application = new LwjglApplication(new MainTest(), getConfig(
				"TimeFlies",
				FULLSCREEN
		));
	}

	private static LwjglApplicationConfiguration getConfig(String title, boolean fullscreen) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = title;
		config.useGL30 = false;
		config.x = 3;
		config.y = 4;
		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;
		config.fullscreen = fullscreen;

		return config;
	}
}
