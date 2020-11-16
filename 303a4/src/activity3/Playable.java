package activity3;

/**
 * An item that can be played in a media player.
 */
public interface Playable
{
	/**
	 * Play the entire item.
	 */
	void play(Player pPlayer);
	
	/**
	 * @return The duration of the play at normal speed.
	 */
	int duration();
	
	/**
	 * @return A short description of the item that can be displayed while it plays.
	 */
	String description();
}
