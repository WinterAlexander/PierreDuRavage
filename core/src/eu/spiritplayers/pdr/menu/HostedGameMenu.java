package eu.spiritplayers.pdr.menu;

import eu.spiritplayers.pdr.Demo;
import eu.spiritplayers.pdr.GamePanel;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class HostedGameMenu extends Menu
{
	public HostedGameMenu(GamePanel panel)
	{
		super(panel);

		addButton(new MenuHeader());
		addButton(new Button(3, "Reprendre", new Runnable()
		{
			@Override
			public void run()
			{
				setOpen(false);
			}

		}));

		addButton(new Button(9, "Arrêter la partie", new Runnable()
		{
			@Override
			public void run()
			{
				getPanel().getApp().getGame().getState().cancel();
				getPanel().setMenu(new MainMenu(getPanel()));
				getPanel().getMenu().setOpen(true);
				getPanel().getApp().setGame(new Demo(getPanel().getApp()));
				getPanel().getApp().getGame().start();
			}

		}));
	}
}