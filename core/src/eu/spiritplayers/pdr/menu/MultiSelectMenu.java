package eu.spiritplayers.pdr.menu;

import eu.spiritplayers.pdr.Demo;
import eu.spiritplayers.pdr.GamePanel;
import eu.spiritplayers.pdr.GameType;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class MultiSelectMenu extends Menu
{
	public MultiSelectMenu(GamePanel panel)
	{
		super(panel);

		addButton(new MenuHeader());
		addButton(new Button(3, "Joindre une partie", new Runnable()
		{
			@Override
			public void run()
			{
				setOpen(false);
				getPanel().setMenu(new JoinedGameMenu(getPanel()));
				//getPanel().getApp().setGame(new JoinedGame(getPanel().getApp()));
				getPanel().getApp().getGame().start();
			}

		}));
		addButton(new Button(5, "Héberger une partie", new Runnable()
		{

			@Override
			public void run()
			{
				setOpen(false);
				getPanel().setMenu(new HostedGameMenu(getPanel()));
				//getPanel().getApp().setGame(new HostedGame(getPanel().getApp()));
				getPanel().getApp().getGame().start();
			}

		}));

		addButton(new Button(9, "Retour", new Runnable()
		{
			@Override
			public void run()
			{
				getPanel().setMenu(new MainMenu(getPanel()));
				getPanel().getMenu().setOpen(true);
			}

		}));
	}
}
