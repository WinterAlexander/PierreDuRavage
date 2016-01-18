package eu.spiritplayers.pdr.panel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.pdr.PierreDuRavage;
import eu.spiritplayers.pdr.panel.dice.Dice;
import eu.spiritplayers.pdr.panel.dice.SimpleDice;
import eu.spiritplayers.pdr.panel.item.ItemSlot;
import eu.spiritplayers.pdr.panel.menu.MainMenu;
import eu.spiritplayers.pdr.panel.menu.Menu;
import eu.spiritplayers.pdr.panel.player.LocalPlayer;
import eu.spiritplayers.pdr.panel.player.Player;

import java.util.ArrayList;
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

	private PierreDuRavage game;

	private Texture backgroundImage;

	private Menu menu;

	private List<Player> players;
	private ItemSlot slot1, slot2, slot3;
	private Dice dice;
	private Chat chat;


	private BitmapFont[] fonts;

	public GamePanel(PierreDuRavage game)
	{
		this.game = game;

		fonts = new BitmapFont[FONT_SIZES.length];

		for(int i = 0; i < FONT_SIZES.length; i++)
			fonts[i] = new BitmapFont(Gdx.files.internal("fonts/" + FONT_SIZES[i] + ".fnt"));

		this.menu = new MainMenu(this);
		getMenu().setOpen(true);


		this.backgroundImage = new Texture("background.bmp");
		this.players = new ArrayList<>();

		this.slot1 = new ItemSlot(this, 0.7f, 0.30f);
		this.slot2 = new ItemSlot(this, 0.8f, 0.30f);
		this.slot3 = new ItemSlot(this, 0.9f, 0.30f);

		this.dice = new SimpleDice(this);
		this.chat = new Chat(this);
	}

	public void reset()
	{
		getDice().stop();
		getDice().setVisible(false);

		for(Player player : getPlayers())
			player.setLocation(Location.SOUTH);

		getItemSlot1().setItem(null);
		getItemSlot2().setItem(null);
		getItemSlot3().setItem(null);
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
		for(Player player : getPlayers())
			player.render(batch);
		this.slot1.render(batch);
		this.slot2.render(batch);
		this.slot3.render(batch);
		this.dice.render(batch);
		this.chat.render(batch);

		this.menu.render(batch);
	}

	public List<ClickBox> getClickBoxes()
	{
		return Arrays.asList(this.slot1.getClickBox(), this.slot2.getClickBox(), this.slot3.getClickBox());
	}

	public List<Player> getPlayers()
	{
		return players;
	}

	public boolean canBuy(Player player)
	{
		return false;
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
	public Menu getMenu()
	{
		return this.menu;
	}

	public void setMenu(Menu menu)
	{
		this.menu = menu;
	}

	public Chat getChat()
	{
		return this.chat;
	}

	public Dice getDice()
	{
		return dice;
	}

	public PierreDuRavage getApp()
	{
		return game;
	}


	public ItemSlot getItemSlot1()
	{
		return slot1;
	}

	public ItemSlot getItemSlot2()
	{
		return slot2;
	}

	public ItemSlot getItemSlot3()
	{
		return slot3;
	}
}
