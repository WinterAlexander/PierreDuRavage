package eu.spiritplayers.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.ClickBox;
import eu.spiritplayers.GamePanel;
import eu.spiritplayers.player.Player;

/**
 * Represents a slot for an item on the GamePanel
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class ItemSlot
{
	private GamePanel panel;
	private Texture texture;
	private float x, y;

	private Item item;
	private boolean visible;

	public ItemSlot(GamePanel panel, float x, float y)
	{
		this.panel = panel;
		this.texture = new Texture("itemslot.png");
		this.x = x;
		this.y = y;

		this.visible = true;
		this.item = null;
	}

	public void render(SpriteBatch batch)
	{
		ClickBox box = getClickBox();

		batch.draw(texture, box.getX(), box.getY(), box.getWidth(), box.getHeight());

		if(!hasItem())
			return;

		item.render(batch, getPanel(), box.getCenterX(), box.getCenterY());
	}

	public ClickBox getClickBox()
	{
		int width = getPanel().getWidth() / 10;
		int height = (int)(1 / ((float)texture.getWidth() / (float)texture.getHeight()) * width);
		int x = getPanel().getBackgroundX() + (int)(this.x * panel.getWidth()) - width / 2;
		int y = getPanel().getBackgroundY() + (int)(this.y * panel.getHeight()) - height / 2;

		return new ClickBox(x, y, width, height)
		{
			@Override
			public void click()
			{
				if(!hasItem())
					return;

				buy(getPanel().getLocalPlayer());
			}
		};
	}

	public GamePanel getPanel()
	{
		return panel;
	}

	public boolean hasItem()
	{
		return item != null;
	}

	public Item getItem()
	{
		return this.item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getPrice()
	{
		if(item == null)
			return -1;

		return this.item.getPrice();
	}

	public boolean buy(Player player)
	{
		if(player.getMoney() < this.getPrice() || this.item == null || this.getPrice() < 0)
			return false;

		player.setMoney(player.getMoney() - getPrice());
		player.getItems().add(this.item);

		this.item = null;
		return true;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
}
