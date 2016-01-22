package eu.spiritplayers.pdr.game.state;

import eu.spiritplayers.pdr.game.Game;
import eu.spiritplayers.pdr.panel.dice.Dice;
import eu.spiritplayers.pdr.panel.dice.OrderDeterminingDice;
import eu.spiritplayers.pdr.panel.player.Player;
import me.winterguardian.scheduling.Task;
import me.winterguardian.util.SortingUtil;

import java.util.*;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class OrderDetermineState extends GameState
{
	private HashMap<Player, Integer> rolls;

	public OrderDetermineState(Game game)
	{
		super(game);
		rolls = new HashMap<>();
	}

	@Override
	public void start()
	{
		getCurrentGame().broadcast("La partie va désormais commencer.");
		getCurrentGame().getApp().getScheduler().addTask(new Task(1000, false)
		{
			@Override
			public void run()
			{
				for(Player player : getCurrentGame().getPlayers())
				{
					player.setDice(new OrderDeterminingDice(player));
					player.getDice().setVisible(true);
				}

				getCurrentGame().broadcast("Le dé va d'abord déterminer l'ordre des joueurs.");
			}
		});

		getCurrentGame().getApp().getScheduler().addTask(new Task(2000, false)
		{
			@Override
			public void run()
			{
				for(Player player : getCurrentGame().getPlayers())
					((OrderDeterminingDice)player.getDice()).roll();
			}
		});
	}

	public void roll(Player player, int face)
	{
		getCurrentGame().broadcast(player.getName() + " obtient un " + face + " !");
		rolls.put(player, face);

		if(rolls.size() == getCurrentGame().getPlayers().size())
		{
			getCurrentGame().getApp().getScheduler().addTask(new Task(1000, false)
			{
				@Override
				public void run()
				{
					setOrder();
					//getCurrentGame().setState();
					//getCurrentGame().getState().start();
				}
			});

		}

	}

	private void setOrder()
	{
		Player[] players = new Player[rolls.size()];
		int[] array = new int[rolls.size()];

		for(int i = 0; i < rolls.size(); i++)
		{
			players[i] = (Player)rolls.keySet().toArray()[i];
			array[i] = (int)((Map.Entry)rolls.entrySet().toArray()[i]).getValue();
		}

		SortingUtil.quickSort(players, array);

		for(int i = 0; i < players.length; i++)
			players[i].setOrder(3 - i);

		getCurrentGame().broadcast(players[0].getName() + " sera le premier à jouer suivi de " + players[1].getName() + " et " + players[2].getName() + " !");
	}

}
