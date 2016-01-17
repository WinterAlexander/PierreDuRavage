package eu.spiritplayers.item;

import com.badlogic.gdx.graphics.Texture;

/**
 * An item boosting damage for the player's next attack
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class Sword extends Item
{
	private Texture texture;

	public Sword()
	{
		this.texture = new Texture("item_sword.png");
	}

	@Override
	public int getPrice()
	{
		return 1;
	}

	@Override
	public Texture getTexture()
	{
		return this.texture;
	}
}
