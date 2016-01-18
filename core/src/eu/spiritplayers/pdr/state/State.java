package eu.spiritplayers.pdr.state;

import eu.spiritplayers.pdr.Game;
import eu.spiritplayers.pdr.player.Player;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public interface State
{
	Game getCurrentGame();

	boolean join(Player player);
	void leave(Player player);
	void start();
	void cancel();
}
