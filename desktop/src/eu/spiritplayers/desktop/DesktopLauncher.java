package eu.spiritplayers.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import eu.spiritplayers.StoneGame;

import java.awt.*;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pierre du Ravage";
		config.fullscreen = false;
		config.vSyncEnabled = true;
		config.width = screenSize.width * 2 / 3;
		config.height = screenSize.height * 2 / 3;
		new LwjglApplication(new StoneGame(), config);
	}
}
