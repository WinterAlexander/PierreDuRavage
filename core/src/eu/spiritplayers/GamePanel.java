package eu.spiritplayers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents the game background which players will stand on
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class GamePanel
{
	private StoneGame game;
	private Texture backgroundImage;
	private Player player1, player2, player3;

	public GamePanel(StoneGame game)
	{
		this.game = game;

		this.backgroundImage = new Texture("background.bmp");
		this.player1 = new TestPlayer(this, 1);
		this.player2 = new TestPlayer(this, 2);
		this.player3 = new TestPlayer(this, 3);
	}

	public void render(SpriteBatch batch)
	{
		batch.draw(backgroundImage, getBackgroundX(), getBackgroundY(), getWidth(), getHeight());
		this.player1.render(batch);
		this.player2.render(batch);
		this.player3.render(batch);
	}

	public int getBackgroundX()
	{
		return Gdx.graphics.getWidth() / 2 - getWidth() / 2;
	}

	public int getBackgroundY()
	{
		return Gdx.graphics.getHeight() / 2 - getHeight() / 2;
	}

	public int getWidth()
	{
		float screenRatio = (float)Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight();
		float imageRatio = (float)backgroundImage.getWidth() / (float)backgroundImage.getHeight();

		if(screenRatio > imageRatio)
			return (int)(imageRatio * Gdx.graphics.getHeight());

		return Gdx.graphics.getWidth();

	}

	public int getHeight()
	{
		float screenRatio = (float)Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight();
		float imageRatio = getRatio();

		if(screenRatio < imageRatio)
			return (int)(1 / imageRatio * Gdx.graphics.getWidth());

		return Gdx.graphics.getHeight();

	}

	public float getRatio()
	{
		return (float)backgroundImage.getWidth() / (float)backgroundImage.getHeight();
	}

	public Player getPlayer1()
	{
		return player1;
	}

	public Player getPlayer2()
	{
		return player2;
	}

	public Player getPlayer3()
	{
		return player3;
	}
}
