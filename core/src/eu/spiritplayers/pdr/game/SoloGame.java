package eu.spiritplayers.pdr.game;

import eu.spiritplayers.pdr.PierreDuRavage;
import eu.spiritplayers.pdr.panel.player.AIPlayer;
import eu.spiritplayers.pdr.panel.player.LocalPlayer;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class SoloGame extends Game
{
	public SoloGame(PierreDuRavage app)
	{
		super(app, GameType.SOLO, 3, 3);
	}

	@Override
	public void start()
	{
		join(new LocalPlayer(getApp().getPanel(), 1, "Joueur"));
		join(new AIPlayer(getApp().getPanel(), 2));
		join(new AIPlayer(getApp().getPanel(), 3));
	}
}
