package eu.spiritplayers.pdr.game;

import eu.spiritplayers.pdr.PierreDuRavage;
import eu.spiritplayers.pdr.panel.player.Player;
import eu.spiritplayers.pdr.game.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public abstract class Game
{
	private PierreDuRavage app;

	private GameType type;
	private State state;
	private int minimumPlayers, maximumPlayers;
	private int baseOrder;

	public Game(PierreDuRavage app, GameType type, int minimumPlayers, int maximumPlayers)
	{
		this.app = app;

		this.type = type;
		this.minimumPlayers = minimumPlayers;
		this.maximumPlayers = maximumPlayers;

		this.baseOrder = 1;

		this.state = type.getBaseState(this);
	}

	public abstract void start();

	public boolean join(Player player)
	{
		player.setOrder(baseOrder);
		baseOrder++;

		return getState().join(player);
	}

	public void leave(Player player)
	{
		getState().leave(player);
	}

	public void broadcast(String message)
	{
		for(Player player : getPlayers())
			player.sendMessage(message);
	}

	public List<Player> getPlayers()
	{
		if(type == GameType.SOLO || type == GameType.HOSTED_MULTI)
			return getApp().getPanel().getPlayers();

		return new ArrayList<>();
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public PierreDuRavage getApp()
	{
		return app;
	}

	public GameType getType()
	{
		return type;
	}

	public int getMinimumPlayers()
	{
		return minimumPlayers;
	}

	public int getMaximumPlayers()
	{
		return maximumPlayers;
	}
}
