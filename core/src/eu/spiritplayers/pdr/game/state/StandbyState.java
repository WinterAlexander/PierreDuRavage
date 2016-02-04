package eu.spiritplayers.pdr.game.state;

import eu.spiritplayers.pdr.game.Game;
import eu.spiritplayers.pdr.panel.player.Player;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class StandbyState implements State
{
	private Game game;
	private State next;

	public StandbyState(Game game, State next)
	{
		this.game = game;
		this.next = next;
	}

	@Override
	public Game getCurrentGame()
	{
		return game;
	}

	@Override
	public boolean join(Player player)
	{
		for(Player current : getCurrentGame().getPlayers())
			if(current.getName().equalsIgnoreCase(player.getName()))
			{
				player.sendMessage("Un joueur avec ce nom est déjà en jeu.");
				return false;
			}

		if(getCurrentGame().getPlayers().size() >= getCurrentGame().getMaximumPlayers())
		{
			player.sendMessage("Cette partie est remplie à son maximum.");
			return false;
		}

		getCurrentGame().getPlayers().add(player);
		getCurrentGame().broadcast(player.getName() + " a rejoint la partie.");

		if(getCurrentGame().getPlayers().size() >= getCurrentGame().getMinimumPlayers())
			next();

		return true;
	}

	@Override
	public void leave(Player player)
	{
		if(!getCurrentGame().getPlayers().contains(player))
			return;

		getCurrentGame().getPlayers().remove(player);
		getCurrentGame().broadcast(player.getName() + " a quitté la partie.");
	}

	@Override
	public void start()
	{

	}

	@Override
	public void cancel()
	{

	}

	private void next()
	{
		getCurrentGame().setState(next);
		getCurrentGame().getState().start();
	}

	@Override
	public boolean canBuy(Player player)
	{
		return false;
	}
}
