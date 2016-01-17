package eu.spiritplayers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents an item a player can own.
 * Also displayed on screen when the player obtains it.
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public abstract class Item
{
	private Player owner;

	public Item()
	{
		this.owner = null;
	}

	public abstract Texture getTexture();

	public void render(SpriteBatch batch, int x, int y)
	{
		float spriteRatio = (float)getTexture().getWidth() / (float)getTexture().getHeight();

		int width = owner.getPanel().getWidth() / 10;
		int height = (int)(1 / spriteRatio * width);

		batch.draw(getTexture(), x - width / 2, y - height / 2, width, height);
	}

	public boolean isOwned()
	{
		return getOwner() != null;
	}

	public Player getOwner()
	{
		return owner;
	}

	public void setOwner(Player owner)
	{
		this.owner = owner;
	}
}
