package eu.spiritplayers.pdr.panel.dice;

import com.badlogic.gdx.graphics.Texture;
import eu.spiritplayers.pdr.panel.ClickBox;
import eu.spiritplayers.pdr.panel.GamePanel;
import eu.spiritplayers.pdr.panel.player.Player;
import me.winterguardian.scheduling.Task;

/**
 *
 * Created by Alexander Winter on 2016-01-18.
 */
public class SimpleDice extends eu.spiritplayers.pdr.panel.dice.Dice
{
	public SimpleDice(GamePanel panel)
	{
		super(panel, 6);
	}

	@Override
	public Texture[] loadTextures(int faceCount)
	{
		Texture[] faces = new Texture[faceCount];
		for(int i = 0; i < faceCount; i++)
			faces[i] = new Texture("dice" + (i + 1)  + ".bmp");
		return faces;
	}

	@Override
	public void onRoll(Player player, int face)
	{
		getPanel().broadcast(player.getName() + " a obtenu un " + face + " !");
	}

	@Override
	public ClickBox getClickBox()
	{
		Texture texture = getTextures()[getFace() - 1];

		float spriteRatio = (float)texture.getWidth() / (float)texture.getHeight();

		int width = getPanel().getWidth() / 8;
		int height = (int)(1 / spriteRatio * width);

		int x = getPanel().getBackgroundX() + getPanel().getWidth() * 3 / 32 - width / 2;
		int y = getPanel().getBackgroundY() + getPanel().getHeight() * 11 / 16 - height / 2;

		if(isRolling())
		{
			Task task = getRollingTask();

			double delta = (double)Math.min(getPanel().getApp().getScheduler().getGameTimeMillis() - task.getLastWork(), task.getDelay());
			y += height / 4d * Math.abs(Math.sin(delta / (double)task.getDelay() * Math.PI));
		}

		return new ClickBox(x, y ,width, height)
		{
			@Override
			public void click()
			{
				roll(getPanel().getLocalPlayer());
			}
		};
	}
}
