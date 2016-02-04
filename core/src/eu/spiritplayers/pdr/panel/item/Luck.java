package eu.spiritplayers.pdr.panel.item;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents an item letting the player choose his dice for the turn
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class Luck extends Item
{
	private Texture texture;

	public Luck()
	{
		this.texture = new Texture("item_sword.png");
	}

	@Override
	public String getName()
	{
		return "Luck";
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
