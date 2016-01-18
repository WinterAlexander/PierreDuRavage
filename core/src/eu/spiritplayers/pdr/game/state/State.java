package eu.spiritplayers.pdr.game.state;

import eu.spiritplayers.pdr.game.Game;
import eu.spiritplayers.pdr.panel.player.Player;

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
