package eu.spiritplayers.pdr.panel.menu;

import com.badlogic.gdx.Gdx;
import eu.spiritplayers.pdr.panel.GamePanel;
import eu.spiritplayers.pdr.game.SoloGame;

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
				getPanel().getApp().setGame(new SoloGame(getPanel().getApp()));
				getPanel().getApp().getGame().start();
			}

		}));
		addButton(new Button(5, "Jouer en ligne", new Runnable()
		{

			@Override
			public void run()
			{
				getPanel().setMenu(new MultiSelectMenu(getPanel()));
				getPanel().getMenu().setOpen(true);
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
