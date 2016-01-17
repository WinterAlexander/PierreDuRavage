package eu.spiritplayers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.player.Player;
import me.winterguardian.scheduling.Task;

import java.util.Random;

/**
 * Represents the dice on the GamePanel
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class Dice
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

		this.visible = true;

		this.faces = new Texture[faceCount];
		for(int i = 0; i < faceCount; i++)
			faces[i] = new Texture("dice" + (i + 1)  + ".bmp");
	}

	public void render(SpriteBatch batch)
	{
		Texture texture = faces[getFace() - 1];

		float spriteRatio = (float)texture.getWidth() / (float)texture.getHeight();

		int width = panel.getWidth() / 8;
		int height = (int)(1 / spriteRatio * width);

		int x = panel.getBackgroundX() + panel.getWidth() * 3 / 32 - width / 2;
		int y = panel.getBackgroundY() + panel.getHeight() * 11 / 16 - height / 2;

		if(isRolling())
		{
			double delta = (double)Math.min(panel.getGame().getScheduler().getGameTimeMillis() - task.getLastWork(), task.getDelay());
			y += height / 4d * Math.abs(Math.sin(delta / (double)task.getDelay() * Math.PI));
		}
		batch.draw(texture, x, y, width, height);
	}

	public void roll(final Player player)
	{
		if(this.isRolling())
			return;

		this.rolling = true;
		panel.getGame().getScheduler().addTask(this.task = new Task(400, true)
		{

			private int changes = 0;

			@Override
			public void run()
			{
				Random random = new Random();

				if(++changes > 5 && random.nextFloat() > 1 - (changes - 5) * 0.05)
				{
					rolling = false;
					panel.broadcast(player.getName() + " a obtenu un " + face + " !");
					cancel();
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
