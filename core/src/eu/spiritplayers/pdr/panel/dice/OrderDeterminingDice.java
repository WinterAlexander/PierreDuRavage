package eu.spiritplayers.pdr.panel.dice;

import com.badlogic.gdx.graphics.Texture;
import eu.spiritplayers.pdr.game.state.OrderDetermineState;
import eu.spiritplayers.pdr.game.state.State;
import eu.spiritplayers.pdr.panel.GamePanel;
import eu.spiritplayers.pdr.panel.player.Player;

/**
 *
 * Created by Alexander Winter on 2016-01-19.
 */
public class OrderDeterminingDice extends PlayerDice
{
	public OrderDeterminingDice(Player player)
	{
		super(player, 6);
	}

	@Override
	public Texture[] loadTextures(int faceCount)
	{
		Texture[] faces = new Texture[faceCount];
		for(int i = 0; i < faceCount; i++)
			faces[i] = new Texture("dice" + (i + 1)  + ".bmp");
		return faces;
	}

	@Override
	public void onRoll(Player player, int face)
	{
		State state = player.getPanel().getApp().getGame().getState();

		if(!(state instanceof OrderDetermineState))
			return;

		((OrderDetermineState)state).roll(player, face);
	}
}
