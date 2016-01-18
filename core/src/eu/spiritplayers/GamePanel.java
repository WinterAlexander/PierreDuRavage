package eu.spiritplayers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.item.ItemSlot;
import eu.spiritplayers.item.Sword;
import eu.spiritplayers.menu.Button;
import eu.spiritplayers.menu.MainMenu;
import eu.spiritplayers.menu.Menu;
import eu.spiritplayers.menu.MenuHeader;
import eu.spiritplayers.player.AIPlayer;
import eu.spiritplayers.player.LocalPlayer;
import eu.spiritplayers.player.Player;

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

	private StoneGame game;

	private Texture backgroundImage;

	private Menu menu;

	private List<Player> players;
	private ItemSlot slot1, slot2, slot3;
	private Dice dice;
	private Chat chat;

	private int minimumPlayers, maximumPlayers;
	private boolean started;

	private BitmapFont[] fonts;

	public GamePanel(StoneGame game)
	{
		this.game = game;

		fonts = new BitmapFont[FONT_SIZES.length];

		for(int i = 0; i < FONT_SIZES.length; i++)
			fonts[i] = new BitmapFont(Gdx.files.internal("fonts/" + FONT_SIZES[i] + ".fnt"));

		this.menu = new MainMenu(this);
		getMenu().setOpen(true);

		this.minimumPlayers = 3;
		this.maximumPlayers = 3;

		this.started = false;

		this.backgroundImage = new Texture("background.bmp");
		this.players = new ArrayList<>();

		this.slot1 = new ItemSlot(this, 0.7f, 0.30f);
		this.slot2 = new ItemSlot(this, 0.8f, 0.30f);
		this.slot3 = new ItemSlot(this, 0.9f, 0.30f);

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

	public void prepare(GameType type)
	{
		switch(type)
		{
			case SOLO:
				join(new LocalPlayer(this, 1, "Joueur"));
				join(new AIPlayer(this, 2));
				join(new AIPlayer(this, 3));
				return;
			case MULTI:
		}
	}

	public boolean join(Player player)
	{
		for(Player current : getPlayers())
			if(current.getName().equalsIgnoreCase(player.getName()))
			{
				player.sendMessage("Un joueur avec ce nom est déjà en jeu.");
				return false;
			}

		if(getPlayers().size() >= getMaximumPlayers())
		{
			player.sendMessage("Cette partie est remplie à son maximum.");
			return false;
		}

		if(isStarted())
		{
			player.sendMessage("La partie est déjà commencée.");
			return false;
		}

		getPlayers().add(player);
		broadcast(player.getName() + " a rejoint la partie.");

		if(getPlayers().size() >= getMinimumPlayers())
			start();

		return true;
	}

	public void leave(Player player)
	{
		if(!getPlayers().contains(player))
			return;

		getPlayers().remove(player);
		broadcast(player.getName() + " a quitté la partie.");

		if(getPlayers().size() < getMinimumPlayers())
			cancel();

	}

	public void start()
	{
		this.started = true;
		broadcast("La partie va désormais commencer.");
	}

	public void cancel()
	{
		this.started = false;
		broadcast("La partie a été annulée.");

		this.dice.stop();
		this.dice.setVisible(false);

		for(Player player : getPlayers())
			player.setLocation(Location.SOUTH);

		this.slot1.setItem(null);
		this.slot2.setItem(null);
		this.slot3.setItem(null);
	}

	public boolean canBuy(Player player)
	{
		return false;
	}

	public boolean isStarted()
	{
		return started;
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

	public StoneGame getGame()
	{
		return game;
	}

	public int getMinimumPlayers()
	{
		return minimumPlayers;
	}

	public void setMinimumPlayers(int minimumPlayers)
	{
		this.minimumPlayers = minimumPlayers;
	}

	public int getMaximumPlayers()
	{
		return maximumPlayers;
	}

	public void setMaximumPlayers(int maximumPlayers)
	{
		this.maximumPlayers = maximumPlayers;
	}
}
