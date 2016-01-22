package eu.spiritplayers.pdr.panel.dice;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.pdr.panel.ClickBox;
import eu.spiritplayers.pdr.panel.GamePanel;
import eu.spiritplayers.pdr.panel.player.Player;
import me.winterguardian.scheduling.Task;

import java.util.Random;

/**
 * Represents the dice on the GamePanel
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public abstract class Dice
{
	private GamePanel panel;
	private boolean rolling;
	private int face;
	private Task task;

	private Texture[] faces;
	private boolean visible;

	public Dice(GamePanel panel, int faceCount)
	{
		this.panel = panel;
		this.face = 1;
		this.task = null;

		this.visible = false;

		this.faces = loadTextures(faceCount);
	}

	public abstract Texture[] loadTextures(int faceCount);
	public abstract void onRoll(Player player, int face);
	public abstract ClickBox getClickBox();

	public void render(SpriteBatch batch)
	{
		if(!isVisible())
			return;

		Texture texture = faces[getFace() - 1];

		ClickBox box = getClickBox();

		batch.draw(texture, box.getX(), box.getY(), box.getWidth(), box.getHeight());
	}

	public void roll(final Player player)
	{
		if(this.isRolling())
			return;

		this.rolling = true;
		panel.getApp().getScheduler().addTask(this.task = new Task(400, true)
		{

			private int changes = 0;

			@Override
			public void run()
			{
				Random random = new Random();

				if(++changes > 5 && random.nextFloat() > 1 - (changes - 5) * 0.05)
				{
					rolling = false;
					onRoll(player, face);
					cancel();
					task = null;
					return;
				}

				int newFace;
				do
					newFace = random.nextInt(faces.length) + 1;
				while(newFace == face);
				face = newFace;
			}
		});
	}

	public void stop()
	{
		if(!this.isRolling())
			return;

		this.task.cancel();
		this.rolling = false;
	}

	public GamePanel getPanel()
	{
		return panel;
	}

	public Texture[] getTextures()
	{
		return faces;
	}

	public Task getRollingTask()
	{
		return task;
	}

	public boolean isRolling()
	{
		return this.rolling;
	}

	public int getFace()
	{
		return this.face;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
}
