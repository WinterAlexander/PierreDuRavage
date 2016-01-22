package eu.spiritplayers.pdr.panel.dice;

import eu.spiritplayers.pdr.panel.ClickBox;
import eu.spiritplayers.pdr.panel.player.Player;
import me.winterguardian.scheduling.Task;

/**
 *
 * Created by Alexander Winter on 2016-01-19.
 */
public abstract class PlayerDice extends Dice
{
	private Player player;

	public PlayerDice(Player player, int faceCount)
	{
		super(player.getPanel(), faceCount);
		this.player = player;
	}

	@Override
	public ClickBox getClickBox()
	{
		ClickBox box = player.getClickBox();
		box.setY(box.getY() + box.getHeight());
		box.setWidth(box.getWidth() / 2);
		box.setHeight(box.getHeight() / 2);
		box.setX(box.getX() + box.getWidth() / 4);

		if(isRolling())
		{
			Task task = getRollingTask();

			double delta = (double)Math.min(getPanel().getApp().getScheduler().getGameTimeMillis() - task.getLastWork(), task.getDelay());
			box.setY((int)(box.getY() +  box.getHeight() / 4d * Math.abs(Math.sin(delta / (double)task.getDelay() * Math.PI))));
		}

		return box;
	}

	public void roll()
	{
		roll(this.player);
	}
}
