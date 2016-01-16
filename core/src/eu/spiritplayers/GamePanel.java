package eu.spiritplayers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
		this.player1 = new TestPlayer(this);
		this.player2 = new TestPlayer(this);
		this.player3 = new TestPlayer(this);
	}

	public void render(SpriteBatch batch)
	{
		batch.draw(backgroundImage, 0, 0, getWidth(), getHeight());
		this.player1.render(batch);
		this.player2.render(batch);
		this.player3.render(batch);
	}

	public int getWidth()
	{
		return Gdx.graphics.getWidth();
	}

	public int getHeight()
	{
		return Gdx.graphics.getHeight();
	}
}
