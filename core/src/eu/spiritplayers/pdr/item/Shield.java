package eu.spiritplayers.pdr.item;

import com.badlogic.gdx.graphics.Texture;

/**
 * An item boosting defense for next time a player gets attacked
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class Shield extends Item
{
	public Shield()
	{
	}

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
