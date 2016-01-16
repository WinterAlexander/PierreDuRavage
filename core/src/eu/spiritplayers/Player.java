package eu.spiritplayers;

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
	private int points;

	public Player(GamePanel panel)
	{
		this.panel = panel;
		this.location = Location.SOUTH;
	}

	public void render(SpriteBatch batch)
	{

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

	public void addPoints()
	{
		if(location == null)
			return;

		this.points += this.location.getPointsPerRound();
	}
}
