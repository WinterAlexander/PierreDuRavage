package eu.spiritplayers.pdr.panel.menu;

import eu.spiritplayers.pdr.game.Demo;
import eu.spiritplayers.pdr.panel.GamePanel;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class JoinedGameMenu extends Menu
{
	public JoinedGameMenu(GamePanel panel)
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

		addButton(new Button(9, "Se déconnecter", new Runnable()
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
