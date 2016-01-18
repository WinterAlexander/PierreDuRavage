package eu.spiritplayers.pdr.panel.menu;

import com.badlogic.gdx.graphics.Texture;

public class MenuHeader extends Button
{
	private Texture logo;

	public MenuHeader()
	{
		super(1, null, new Runnable()
		{
			@Override
			public void run(){}
		});
	}

	protected void loadTextures()
	{
		this.logo = new Texture("logo.png");
	}

	public Texture getTexture()
	{
		return logo;
	}
	
	public Texture getHoverTexture()
	{
		return getTexture();
	}
}
