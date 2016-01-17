package eu.spiritplayers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.item.ItemSlot;
import eu.spiritplayers.item.Sword;
import eu.spiritplayers.player.AIPlayer;
import eu.spiritplayers.player.LocalPlayer;
import eu.spiritplayers.player.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the game background which players will stand on
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class GamePanel
{
	private static int[] FONT_SIZES = new int[]{12, 18, 24, 36, 54, 72};

	private StoneGame game;

	private Texture backgroundImage;

	private Player player1, player2, player3;
	private ItemSlot slot1, slot2, slot3;
	private Dice dice;
	private Chat chat;

	private BitmapFont[] fonts;

	public GamePanel(StoneGame game)
	{
		this.game = game;

		fonts = new BitmapFont[FONT_SIZES.length];

		for(int i = 0; i < FONT_SIZES.length; i++)
			fonts[i] = new BitmapFont(Gdx.files.internal("fonts/" + FONT_SIZES[i] + ".fnt"));

		this.backgroundImage = new Texture("background.bmp");
		this.player1 = new LocalPlayer(this, 1, "Joueur");
		this.player2 = new AIPlayer(this, 2);
		this.player3 = new AIPlayer(this, 3);

		this.slot1 = new ItemSlot(this, 0.7f, 0.30f);
		this.slot2 = new ItemSlot(this, 0.8f, 0.30f);
		this.slot3 = new ItemSlot(this, 0.9f, 0.30f);

		this.slot1.setItem(new Sword());

		this.dice = new Dice(this, 6);
		this.chat = new Chat(this);


	}

	public BitmapFont getFont(int lineHeight)
	{
		int bestDelta = Integer.MAX_VALUE;
		int bestFont = -1;

		for(int i = 0; i < FONT_SIZES.length; i++)
		{
			int delta = Math.abs(lineHeight - FONT_SIZES[i]);

			if(delta < bestDelta || (delta == bestDelta && FONT_SIZES[i] > FONT_SIZES[bestFont]))
			{
				bestDelta = delta;
				bestFont = i;
			}
		}

		return fonts[bestFont];
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
		this.chat.render(batch);
	}

	public List<ClickBox> getClickBoxes()
	{
		return Arrays.asList(this.slot1.getClickBox(), this.slot2.getClickBox(), this.slot3.getClickBox());
	}

	public List<Player> getPlayers()
	{
		return Arrays.asList(this.player1, this.player2, this.player3);
	}

	public void broadcast(String message)
	{
		for(Player player : getPlayers())
			player.sendMessage(message);
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
		for(Player player : getPlayers())
			if(player instanceof LocalPlayer)
				return player;

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

	public Chat getChat()
	{
		return this.chat;
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
