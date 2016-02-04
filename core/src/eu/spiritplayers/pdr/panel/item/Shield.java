package eu.spiritplayers.pdr.panel.item;

import com.badlogic.gdx.graphics.Texture;

/**
 * An item boosting defense for next time a player gets attacked
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class Shield extends Item
{
	private Texture texture;

	public Shield()
	{
		this.texture = new Texture("item_sword.png");
	}

	@Override
	public String getName()
	{
		return "Shield";
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
