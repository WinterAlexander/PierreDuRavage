package eu.spiritplayers.pdr.panel.player;

import eu.spiritplayers.pdr.panel.GamePanel;

/**
 * Represents a player controlled with the InputListener
 * Can have only one LocalPlayer per game
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class LocalPlayer extends Player
{
	public LocalPlayer(GamePanel panel, String name)
	{
		super(panel, name);
	}

	@Override
	public void sendMessage(String message)
	{
		getPanel().getChat().sendMessage(message);
	}
}
