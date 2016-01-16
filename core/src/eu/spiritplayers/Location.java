package eu.spiritplayers;

/**
 * Represents the differents location players in the panel can stand on
 * Also gives the amount of points the player can get from it's position
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public enum Location
{
	NORTH(1), SOUTH(0);

	private int points;

	Location(int points)
	{
		this.points = points;
	}

	public int getPointsPerRound()
	{
		return points;
	}
}
