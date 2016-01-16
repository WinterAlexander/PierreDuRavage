package eu.spiritplayers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents a player in the GamePanel
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public abstract class Player
{
	private GamePanel panel;
	private Location location;
	private int points, id;

	private int previousX, previousY;

	private Texture[] sprites;

	public Player(GamePanel panel, int id)
	{
		this.previousX = 0;
		this.previousY = 0;

		this.id = id;

		this.sprites = new Texture[3];
		this.sprites[0] = new Texture("player_back_idle.png");
		this.sprites[1] = new Texture("player_back_fight.png");
		this.sprites[2] = new Texture("player_front.png");
		this.panel = panel;
		this.location = Location.SOUTH;
	}

	public void render(SpriteBatch batch)
	{
		float spriteRatio = (float)getSprite().getWidth() / (float)getSprite().getHeight();

		int width = getPanel().getWidth() / 10;
		int height = (int)(1 / spriteRatio * width);
		int x = this.location.getX(panel) - width / 2;
		int y = this.location.getY(panel) - height / 2;

		x = (x + previousX) / 2;
		y = (y + previousY) / 2;
		batch.draw(getSprite(), x, y, width, height);

		this.previousX = x;
		this.previousY = y;
	}

	public Location getLocation()
	{
		return location;
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

	public void addPoints()
	{
		if(location == null)
			return;

		this.points += this.location.getPointsPerRound();
	}

	public void changeLocation()
	{
		int newId = location.ordinal();
		newId++;
		newId %= Location.values().length;

		setLocation(Location.values()[newId]);
	}
}
