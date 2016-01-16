package eu.spiritplayers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents an item a player can own.
 * Also displayed on screen when the player obtains it.
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class Item
{
	private Player owner;

	public Item(Player owner)
	{
		this.owner = owner;
	}

	public void render(SpriteBatch batch, int slot)
	{

	}

	public Player getOwner()
	{
		return owner;
	}
}
