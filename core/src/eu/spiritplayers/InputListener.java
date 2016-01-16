package eu.spiritplayers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
		switch(keycode)
		{
			case Input.Keys.NUM_1:
				game.getPanel().getPlayer1().changeLocation();
				return true;

			case Input.Keys.NUM_2:
				game.getPanel().getPlayer2().changeLocation();
				return true;

			case Input.Keys.NUM_3:
				game.getPanel().getPlayer3().changeLocation();
				return true;

			case Input.Keys.NUM_4:
				game.getPanel().getDice().roll();
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
