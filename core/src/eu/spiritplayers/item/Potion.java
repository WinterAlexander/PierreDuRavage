package eu.spiritplayers.item;

import com.badlogic.gdx.graphics.Texture;

/**
 * An item regaining health to the player
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class Potion extends Item
{
	@Override
	public int getPrice()
	{
		return 5;
	}

	@Override
	public Texture getTexture()
	{
		return null;
	}
}
