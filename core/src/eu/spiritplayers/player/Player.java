package eu.spiritplayers.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.GamePanel;
import eu.spiritplayers.Location;
import eu.spiritplayers.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the GamePanel
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public abstract class Player
{
	private GamePanel panel;
	private int id;
	private String name;

	private Location location;
	private int points, health, money;
	private List<Item> items;

	private int previousX, previousY;
	private Texture[] sprites;

	public Player(GamePanel panel, int id, String name)
	{
		this.panel = panel;

		this.id = id;
		this.name = name;

		this.location = Location.SOUTH;
		this.points = 0;
		this.health = 5;
		this.money = 2;
		this.items = new ArrayList<>();

		this.previousX = -1;
		this.previousY = -1;

		this.sprites = new Texture[3];
		this.sprites[0] = new Texture("player_back_idle.png");
		this.sprites[1] = new Texture("player_back_fight.png");
		this.sprites[2] = new Texture("player_front.png");


	}

	public void render(SpriteBatch batch)
	{
		float spriteRatio = (float)getSprite().getWidth() / (float)getSprite().getHeight();

		int width = getPanel().getWidth() / 10;
		int height = (int)(1 / spriteRatio * width);
		int x = this.location.getX(panel) - width / 2;
		int y = this.location.getY(panel) - height / 2;



		if(previousX != -1)
			x = (x + previousX) / 2;

		if(previousY != -1)
			y = (y + previousY) / 2;
		batch.draw(getSprite(), x, y, width, height);

		this.previousX = x;
		this.previousY = y;
	}

	public Texture getSprite()
	{
		switch(location)
		{
			case SOUTH:
				return sprites[0];

			case FIGHT:
				return sprites[1];

			case NORTH:
				return sprites[2];
		}
		return null;
	}

	public int getId()
	{
		return this.id;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public GamePanel getPanel()
	{
		return panel;
	}

	public int getPoints()
	{
		return points;
	}

	public void setPoints(int points)
	{
		this.points = points;
	}

	public void addRoundPoints()
	{
		if(location == null)
			return;

		this.points += this.location.getPointsPerRound();
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getMoney()
	{
		return this.money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public List<Item> getItems()
	{
		return items;
	}

	public void changeLocation()
	{
		int newId = location.ordinal();
		newId++;
		newId %= Location.values().length;

		setLocation(Location.values()[newId]);
	}
}
