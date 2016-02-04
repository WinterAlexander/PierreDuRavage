package eu.spiritplayers.pdr.game.state;

import eu.spiritplayers.pdr.game.Game;
import eu.spiritplayers.pdr.panel.Location;
import eu.spiritplayers.pdr.panel.player.Player;

/**
 *
 * Created by Alexander Winter on 2016-01-26.
 */
public class PlayState extends GameState
{
	private int index;
	private boolean canBuy;

	public PlayState(Game game)
	{
		super(game);
		this.index = 1;
		this.canBuy = false;
	}

	private Player getPlayer(int index)
	{
		for(Player player : getCurrentGame().getPlayers())
			if(player.getOrder() == index)
				return player;

		return null;
	}

	private void startPlaying(Player player)
	{
		getCurrentGame().broadcast("C'est au tour de " + player.getName() + " de jouer !");
		canBuy = true;
		getCurrentGame().getApp().getPanel().enableShop();

		player.addRoundPoints();

		if(Location.NORTH.getPlayersHere(getCurrentGame().getApp().getPanel()).size() == 0)
			player.setLocation(Location.NORTH);


	}

	@Override
	public void start()
	{
		startPlaying(getPlayer(index));
	}

	@Override
	public boolean canBuy(Player player)
	{
		return getCurrentGame().getPlayers().contains(player) && index == player.getOrder() && canBuy;
	}
}
