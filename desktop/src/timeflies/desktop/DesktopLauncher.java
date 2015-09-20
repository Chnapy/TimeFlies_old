package timeflies.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import static general.ExceptionHandler.registerExceptionHandler;
import test.MainTest;

public class DesktopLauncher {

	private static final boolean FULLSCREEN = false;
	private static final int ANTIALIASING = 4;
	private static final DisplayMode dm = LwjglApplicationConfiguration.getDesktopDisplayMode();
	//Resolution HD
	private static final int WINDOW_WIDTH = FULLSCREEN ? dm.width : dm.width * 4/5;
	private static final int WINDOW_HEIGHT = FULLSCREEN ? dm.height : dm.height * 4/5;

	public static void main(String[] arg) {
		registerExceptionHandler();
		
		LwjglApplication application = new LwjglApplication(new MainTest(), getConfig(
				"TimeFlies - developer edition",
				FULLSCREEN,
				ANTIALIASING
		));
	}

	private static LwjglApplicationConfiguration getConfig(String title, boolean fullscreen, int antialiasing) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = title;
		config.useGL30 = false;
		config.x = (dm.width - WINDOW_WIDTH) / 2;
		config.y = (dm.height - WINDOW_HEIGHT) / 2;
		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;
		config.fullscreen = fullscreen;
		config.samples = antialiasing;

		return config;
	}
}
