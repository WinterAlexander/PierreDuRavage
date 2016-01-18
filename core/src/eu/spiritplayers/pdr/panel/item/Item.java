package eu.spiritplayers.pdr.panel.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.pdr.panel.GamePanel;
import eu.spiritplayers.pdr.panel.player.Player;

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
	public abstract int getPrice();

	public void render(SpriteBatch batch, GamePanel panel, int x, int y)
	{
		float spriteRatio = (float)getTexture().getWidth() / (float)getTexture().getHeight();

		int width = panel.getWidth() / 10;
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
