package eu.spiritplayers.pdr.panel.menu;

import eu.spiritplayers.pdr.game.Demo;
import eu.spiritplayers.pdr.panel.GamePanel;
import eu.spiritplayers.pdr.game.SoloGame;

/**
 *
 * Created by Alexander Winter on 2016-01-17.
 */
public class SoloGameMenu extends Menu
{
	public SoloGameMenu(GamePanel panel)
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
		addButton(new Button(5, "Recommencer", new Runnable()
		{

			@Override
			public void run()
			{
				getPanel().reset();
				setOpen(false);
				getPanel().getApp().setGame(new SoloGame(getPanel().getApp()));
			}

		}));

		addButton(new Button(9, "Quitter la partie", new Runnable()
		{
			@Override
			public void run()
			{
				getPanel().reset();
				getPanel().setMenu(new MainMenu(getPanel()));
				getPanel().getMenu().setOpen(true);
				getPanel().getApp().setGame(new Demo(getPanel().getApp()));
				getPanel().getApp().getGame().start();
			}

		}));
	}
}
