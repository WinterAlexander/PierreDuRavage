package eu.spiritplayers.pdr.game.state;

import eu.spiritplayers.pdr.game.Game;
import eu.spiritplayers.pdr.panel.player.Player;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class IdleState implements State
{
	private Game game;

	public IdleState(Game game)
	{
		this.game = game;
	}

	@Override
	public Game getCurrentGame()
	{
		return game;
	}

	@Override
	public boolean join(Player player)
	{
		return false;
	}

	@Override
	public void leave(Player player)
	{

	}

	@Override
	public void start()
	{

	}

	@Override
	public void cancel()
	{

	}
}
