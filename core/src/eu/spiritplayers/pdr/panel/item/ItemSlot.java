package eu.spiritplayers.pdr.panel.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eu.spiritplayers.pdr.panel.ClickBox;
import eu.spiritplayers.pdr.panel.GamePanel;
import eu.spiritplayers.pdr.panel.player.Player;
import me.winterguardian.scheduling.Task;

import java.util.Random;

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
	private boolean visible, autoRefill;

	public ItemSlot(GamePanel panel, float x, float y)
	{
		this.panel = panel;
		this.texture = new Texture("itemslot.png");
		this.x = x;
		this.y = y;

		this.visible = false;
		this.autoRefill = true;
		this.item = null;
	}

	public void render(SpriteBatch batch)
	{
		if(!isVisible())
			return;

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

				Player player = getPanel().getLocalPlayer();

				if(!getPanel().getApp().getGame().getState().canBuy(player))
				{
					player.sendMessage("Vous ne pouvez pas acheter d'items pour le moment.");
					return;
				}

				if(!buy(player) || !autoRefill)
					return;

				getPanel().getApp().getScheduler().addTask(new Task(1000, false)
				{
					@Override
					public void run()
					{
						refill();
					}
				});
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
		if(this.item == null || this.getPrice() < 0)
			return false;

		if(player.getMoney() < this.getPrice())
		{
			player.sendMessage("Vous n'avez pas assez d'argent !");
			return false;
		}

		player.setMoney(player.getMoney() - getPrice());
		player.getItems().add(this.item);

		getPanel().broadcast(player.getName() + " a acheté un " + item.getName() + " pour " + getPrice() + " pièces.");

		this.item = null;
		return true;
	}

	public void refill()
	{

		if(hasItem())
			return;

		switch(new Random().nextInt(4))
		{
			case 0:
				item = new Luck();
				return;

			case 1:
				item = new Potion();
				return;

			case 2:
				item = new Shield();
				return;

			case 3:
				item = new Sword();
		}


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
