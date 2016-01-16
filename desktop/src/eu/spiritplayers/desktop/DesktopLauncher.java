package eu.spiritplayers.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import eu.spiritplayers.StoneGame;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pierre du Ravage";
		config.fullscreen = false;
		config.vSyncEnabled = true;
		new LwjglApplication(new StoneGame(), config);
	}
}
