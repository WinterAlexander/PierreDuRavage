package eu.spiritplayers.pdr.state;

import eu.spiritplayers.pdr.Game;
import eu.spiritplayers.pdr.Location;
import eu.spiritplayers.pdr.player.Player;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public abstract class GameState implements State
{
	private Game game;

	public GameState(Game game)
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
		player.sendMessage("La partie est déjà commencée.");
		return false;
	}

	@Override
	public void leave(Player player)
	{
		if(!getCurrentGame().getPlayers().contains(player))
			return;

		getCurrentGame().getPlayers().remove(player);
		getCurrentGame().broadcast(player.getName() + " a quitté la partie.");

		if(getCurrentGame().getPlayers().size() < getCurrentGame().getMinimumPlayers())
			cancel();
	}

	@Override
	public void cancel()
	{
		getCurrentGame().broadcast("La partie a été annulée.");

		getCurrentGame().getApp().getPanel().reset();

		getCurrentGame().setState(new StandbyState(getCurrentGame(), getCurrentGame().getType().getBaseState(getCurrentGame())));
		getCurrentGame().getState().start();
	}
}
