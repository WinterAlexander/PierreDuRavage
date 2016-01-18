package eu.spiritplayers.menu;

import com.badlogic.gdx.Gdx;
import eu.spiritplayers.GamePanel;
import eu.spiritplayers.GameType;

/**
 * 
 * Created by Alexander Winter on 2016-01-17.
 */
public class MainMenu extends Menu
{
	public MainMenu(GamePanel panel)
	{
		super(panel);

		addButton(new MenuHeader());
		addButton(new Button(3, "Jouer solo", new Runnable()
		{
			@Override
			public void run()
			{
				setOpen(false);
				getPanel().setMenu(new SoloGameMenu(getPanel()));
				getPanel().prepare(GameType.SOLO);
			}

		}));
		addButton(new Button(5, "Jouer en ligne", new Runnable()
		{

			@Override
			public void run()
			{

			}

		}));

		addButton(new Button(9, "Quitter", new Runnable()
		{
			@Override
			public void run()
			{
				Gdx.app.exit();
			}

		}));
	}
}
