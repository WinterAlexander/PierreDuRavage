package eu.spiritplayers.pdr.menu;


import com.badlogic.gdx.graphics.Texture;

public class Button
{
	private int slot;
	private String content;
	private Runnable action;

	private Texture texture, hoverTexture;
	
	public Button(int slot, String content, Runnable action)
	{
		this.slot = slot;
		this.content = content;
		this.action = action;

		loadTextures();
	}
	
	public int getSlot()
	{
		return slot;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void onClick()
	{
		if(this.action == null)
			return;
		this.action.run();
	}

	protected void loadTextures()
	{
		this.texture = new Texture("button.png");
		this.hoverTexture = new Texture("button_hover.png");
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	public Texture getHoverTexture()
	{
		return hoverTexture;
	}
}

