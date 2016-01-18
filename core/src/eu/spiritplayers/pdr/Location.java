package eu.spiritplayers.pdr;

import eu.spiritplayers.pdr.player.Player;

/**
 * Represents the differents location players in the panel can stand on
 * Also gives the amount of points the player can get from it's position
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public enum Location
{
	NORTH(1, 0.70f, 0.65f), SOUTH(0, 0.30f, 0.35f), FIGHT(0, 0.60f, 0.55f);

	private int points;
	private float offsetX, offsetY;

	Location(int points, float offsetX, float offsetY)
	{
		this.points = points;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public int getPointsPerRound()
	{
		return points;
	}

	public int getX(GamePanel panel)
	{
		return (int)(panel.getBackgroundX() + offsetX * panel.getWidth());
	}

	public int getY(GamePanel panel)
	{
		return (int)(panel.getBackgroundY() + offsetY * panel.getHeight());
	}

	public int getAmountOfPlayers(GamePanel panel)
	{
		int amount = 0;

		for(Player player : panel.getPlayers())
			if(player.getLocation().equals(this))
				amount++;

		return amount;
	}
}
