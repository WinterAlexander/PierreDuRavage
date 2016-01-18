package eu.spiritplayers.menu;

import com.badlogic.gdx.Gdx;
import eu.spiritplayers.GamePanel;

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

			}

		}));

		addButton(new Button(9, "Quitter la partie", new Runnable()
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
