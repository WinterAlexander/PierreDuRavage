package eu.spiritplayers.pdr.panel.item;

import com.badlogic.gdx.graphics.Texture;

/**
 * An item regaining health to the player
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class Potion extends Item
{
	private Texture texture;

	public Potion()
	{
		this.texture = new Texture("item_sword.png");
	}

	@Override
	public String getName()
	{
		return "Potion";
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
