package eu.spiritplayers.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import eu.spiritplayers.GamePanel;

import java.util.ArrayList;

public class Menu
{

	private GamePanel panel;

	private boolean open;
	private ArrayList<Button> buttons;
	private Texture texture;
	
	public Menu(GamePanel panel)
	{
		this.panel = panel;

		this.buttons = new ArrayList<>();
		this.open = false;

		this.texture = new Texture("menu.png");
	}
	
	public void render(SpriteBatch batch)
	{
		if(!open)
			return;
		
		int width = texture.getWidth();
		
		if(Gdx.graphics.getWidth() < width)
			width = Gdx.graphics.getWidth();
		
		int height = width / texture.getWidth() * texture.getHeight();
		
		batch.draw(texture, Gdx.graphics.getWidth() / 2 - width / 2, Gdx.graphics.getHeight() / 2 - height / 2, width, height);

		
		for(Button button : this.buttons)
		{
			int buttonWidth = width / 2;
			int buttonHeight = buttonWidth / button.getTexture().getWidth() * button.getTexture().getHeight();

			int x = Gdx.graphics.getWidth() / 2 - buttonWidth / 2;
			int y = Gdx.graphics.getHeight() / 2 + height / 2 - buttonHeight * (button.getSlot() + 1);
			
			Texture texture;
			
			switch(Gdx.app.getType())
			{
			case Android:
			case iOS:
				if(!Gdx.input.isTouched())
				{
					texture = button.getTexture();
					break;
				}
			case HeadlessDesktop:
			case Desktop:
			case Applet:
			case WebGL:
				if(Gdx.input.getX() >= x && Gdx.input.getX() <= x + buttonWidth && (Gdx.graphics.getHeight() - Gdx.input.getY()) >= y && (Gdx.graphics.getHeight() - Gdx.input.getY()) <= y + buttonHeight)
				{
					texture = button.getHoverTexture();
					break;
				}
				texture = button.getTexture();
				break;
			default:
				texture = null;
			}
			
			if(texture != null)
				batch.draw(texture, x, y, buttonWidth, buttonHeight);
			
			if(button.getContent() != null)
			{
				BitmapFont font = panel.getFont(buttonHeight / 2);
				font.setColor(Color.BLACK);

				font.draw(batch, button.getContent(), x, y + buttonHeight / 2 + font.getCapHeight() / 2, buttonWidth, Align.center, false);
			}
		}

	}
	
	public void onClick(int mouseX, int mouseY)
	{
		if(!open)
			return;
		
		int width = texture.getWidth();
		
		if(Gdx.graphics.getWidth() < width)
			width = Gdx.graphics.getWidth();
		
		int height = width / texture.getWidth() * texture.getHeight();
		
		for(Button button : new ArrayList<>(this.buttons))
		{
			int buttonWidth = width / 2;
			int buttonHeight = buttonWidth / button.getTexture().getWidth() * button.getTexture().getHeight();

			int x = Gdx.graphics.getWidth() / 2 - buttonWidth / 2;
			int y = Gdx.graphics.getHeight() / 2 + height / 2 - buttonHeight * (button.getSlot() + 1);
			
			if(Gdx.input.getX() >= x && Gdx.input.getX() <= x + buttonWidth && (Gdx.graphics.getHeight() - Gdx.input.getY()) >= y && (Gdx.graphics.getHeight() - Gdx.input.getY()) <= y + buttonHeight)
				button.onClick();
		}
	}

	public GamePanel getPanel()
	{
		return panel;
	}
	
	public void addButton(Button button)
	{
		if(button == null)
			return;
		
		this.buttons.add(button);
	}
	
	public void clearSlot(int slot)
	{
		for(Button button : new ArrayList<>(this.buttons))
			if(button.getSlot() == slot)
				this.buttons.remove(button);
	}

	public boolean isOpen()
	{
		return open;
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}
}
