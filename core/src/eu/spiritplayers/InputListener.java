package eu.spiritplayers;

import com.badlogic.gdx.InputProcessor;

/**
 * Handles all the program input
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class InputListener implements InputProcessor
{
	private StoneGame game;

	public InputListener(StoneGame game)
	{
		this.game = game;
	}

	public StoneGame getGame()
	{
		return this.game;
	}

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
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
		return false;
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