package eu.spiritplayers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
	private int price;

	public ItemSlot(GamePanel panel, float x, float y)
	{
		this.panel = panel;
		this.texture = new Texture("itemslot.png");
		this.x = x;
		this.y = y;

		this.item = null;
		this.price = -1;
	}

	public void render(SpriteBatch batch)
	{
		float spriteRatio = (float)texture.getWidth() / (float)texture.getHeight();

		int width = getPanel().getWidth() / 10;
		int height = (int)(1 / spriteRatio * width);
		int x = getPanel().getBackgroundX() + (int)(this.x * panel.getWidth());
		int y = getPanel().getBackgroundY() + (int)(this.y * panel.getHeight());

		batch.draw(texture, x - width / 2, y - height / 2, width, height);

		if(!hasItem())
			return;

		item.render(batch, x, y);
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
		return this.price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public boolean buy(Player player)
	{
		if(player.getMoney() < this.price || this.item == null || this.price < 0)
			return false;

		player.setMoney(player.getMoney() - price);
		player.getItems().add(this.item);

		this.item = null;
		this.price = -1;
		return true;
	}
}
