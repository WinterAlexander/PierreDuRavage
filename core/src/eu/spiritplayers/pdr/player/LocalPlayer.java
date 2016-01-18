package eu.spiritplayers.pdr.player;

import eu.spiritplayers.pdr.GamePanel;

/**
 * Represents a player controlled with the InputListener
 * Can have only one LocalPlayer per game
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class LocalPlayer extends Player
{
	public LocalPlayer(GamePanel panel, int id, String name)
	{
		super(panel, id, name);
	}

	@Override
	public void sendMessage(String message)
	{
		getPanel().getChat().sendMessage(message);
	}
}
