package eu.spiritplayers.pdr;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import eu.spiritplayers.pdr.panel.ClickBox;

/**
 * Handles all the program input
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class InputListener implements InputProcessor
{
	private PierreDuRavage game;

	public InputListener(PierreDuRavage game)
	{
		this.game = game;
	}

	public PierreDuRavage getGame()
	{
		return this.game;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if(getGame().getPanel().getChat().isOpen())
		{
			getGame().getPanel().getChat().input(keycode);
			return true;
		}

		if(keycode == Input.Keys.ESCAPE)
		{
			game.getPanel().getMenu().setOpen(!game.getPanel().getMenu().isOpen());
			return true;
		}

		if(keycode == Input.Keys.ENTER)
		{
			getGame().getPanel().getChat().setOpen(true);
			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		if(keycode == Input.Keys.F11 && Gdx.app.getType() == Application.ApplicationType.Desktop)
		{
			if(Gdx.graphics.isFullscreen())
				Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width / 2, Gdx.graphics.getDisplayMode().height / 2);
			else
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			return true;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		if(getGame().getPanel().getChat().isOpen())
		{
			getGame().getPanel().getChat().input(character);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if(game.getPanel().getMenu().isOpen())
		{
			game.getPanel().getMenu().onClick(screenX, screenY);
			return true;
		}

		screenY = Gdx.graphics.getHeight() - screenY;

		boolean clickedOne = false;

		for(ClickBox box : game.getPanel().getClickBoxes())
		{
			if(box.contains(screenX, screenY))
			{
				box.click();
				clickedOne = true;
			}
		}

		return clickedOne;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
}
