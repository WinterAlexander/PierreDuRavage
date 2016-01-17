package eu.spiritplayers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.item.ItemSlot;
import eu.spiritplayers.item.Sword;
import eu.spiritplayers.player.LocalPlayer;
import eu.spiritplayers.player.Player;
import eu.spiritplayers.player.TestPlayer;

import java.util.Arrays;
import java.util.List;

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
	private ItemSlot slot1, slot2, slot3;
	private Dice dice;

	public GamePanel(StoneGame game)
	{
		this.game = game;

		this.backgroundImage = new Texture("background.bmp");
		this.player1 = new LocalPlayer(this, 1, "You");
		this.player2 = new TestPlayer(this, 2);
		this.player3 = new TestPlayer(this, 3);

		this.slot1 = new ItemSlot(this, 0.6f, 0.1f);
		this.slot2 = new ItemSlot(this, 0.75f, 0.1f);
		this.slot3 = new ItemSlot(this, 0.9f, 0.1f);

		this.slot1.setItem(new Sword());

		this.dice = new Dice(this, 6);
	}

	public void render(SpriteBatch batch)
	{
		batch.draw(backgroundImage, getBackgroundX(), getBackgroundY(), getWidth(), getHeight());
		this.player1.render(batch);
		this.player2.render(batch);
		this.player3.render(batch);
		this.slot1.render(batch);
		this.slot2.render(batch);
		this.slot3.render(batch);
		this.dice.render(batch);
	}

	public List<ClickBox> getClickBoxes()
	{
		return Arrays.asList(this.slot1.getClickBox(), this.slot2.getClickBox(), this.slot3.getClickBox());
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
		float imageRatio = getRatio();

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

	public Player getLocalPlayer()
	{
		if(player1 instanceof LocalPlayer)
			return player1;

		if(player2 instanceof LocalPlayer)
			return player2;

		if(player3 instanceof LocalPlayer)
			return player3;

		return null;
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

	public Dice getDice()
	{
		return dice;
	}

	public StoneGame getGame()
	{
		return game;
	}
}
