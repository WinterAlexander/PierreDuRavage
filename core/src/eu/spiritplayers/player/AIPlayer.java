package eu.spiritplayers.player;

import eu.spiritplayers.GamePanel;
import me.winterguardian.sorting.AntiRecursiveRandomSelector;

import java.util.Arrays;

/**
 * Player class controlled by an AI
 *
 * Created by Alexander Winter on 2016-01-16.
 */
public class AIPlayer extends Player
{
	private static AntiRecursiveRandomSelector<String> NAMES = new AntiRecursiveRandomSelector<>(Arrays.asList("Jean", "Christophe", "Simon", "Pierre", "Philippe", "Claude", "Claire", "Annie", "Laurence", "Marie", "Anaïs", "Amélia", "John", "Bob"));

	public AIPlayer(GamePanel panel, int id)
	{
		super(panel, id, NAMES.next());
	}

	@Override
	public void sendMessage(String message)
	{

	}
}
