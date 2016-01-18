package eu.spiritplayers.pdr.state;

import eu.spiritplayers.pdr.Game;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class InitState extends GameState
{
	public InitState(Game game)
	{
		super(game);
	}

	@Override
	public void start()
	{
		getCurrentGame().broadcast("La partie va désormais commencer.");
	}
}
