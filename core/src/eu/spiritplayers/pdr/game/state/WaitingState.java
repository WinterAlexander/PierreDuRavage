package eu.spiritplayers.pdr.game.state;

import eu.spiritplayers.pdr.game.Game;
import eu.spiritplayers.pdr.panel.player.Player;
import me.winterguardian.scheduling.Task;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class WaitingState extends GameState
{
	private int timer;
	private State next;

	public WaitingState(Game game, int timer, State next)
	{
		super(game);
		this.timer = timer;
		this.next = next;
	}

	@Override
	public void start()
	{
		getCurrentGame().getApp().getScheduler().addTask(new Task(1000, true)
		{
			@Override
			public void run()
			{
				switch(timer)
				{
					case 120:
					case 90:
					case 75:
					case 60:
					case 45:
					case 30:
					case 20:
					case 15:
					case 10:
					case 5:
					case 4:
					case 3:
					case 2:
					case 1:
						broadcastRemainingTime(timer);
						break;

					case 0:
						getCurrentGame().setState(next);
						getCurrentGame().getState().start();
						cancel();
						break;

				}

				timer--;
			}
		});
	}

	private void broadcastRemainingTime(int time)
	{
		int minutes = time / 60;
		int seconds = time % 60;

		String minutesString = "";
		if(minutes > 0)
			minutesString = minutes + " minute" + (minutes > 1 ? "s" : "") + " ";

		String secondsString = "";
		if(seconds > 0)
			secondsString = seconds + " seconde" + (seconds > 1 ? "s" : "") + " ";

		getCurrentGame().broadcast("La partie commence dans " + minutesString + secondsString + ".");
	}

	@Override
	public boolean canBuy(Player player)
	{
		return false;
	}
}
