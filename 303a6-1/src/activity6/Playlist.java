package activity6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a named list of Playable items.
 */
public class Playlist implements Playable
{
	private final List<Playable> aItems = new ArrayList<>();
	private String aTitle; 
	private final static String UNTITLED = "<untitled>";
	
	/**
	 * Create a new, empty playlist.
	 * 
	 * @param pTitle The title of the playlist.
	 * @pre pTitle != null;
	 */
	public Playlist(String pTitle)
	{
		assert pTitle != null;
		aTitle = pTitle;
	}
	
	/**
	 * @return The title of this song. Never null. If the title
	 * is empty, returns a string describing this instead of the empty string.
	 */
	public String getTitle()
	{
		if( aTitle.isBlank() )
		{
			return UNTITLED;
		}
		return aTitle;
	}
	
	/**
	 * Add an item to the playlist.
	 * 
	 * @param pPlayable The item to add.
	 * @pre pPlayable != null;
	 */
	public void add(Playable pPlayable)
	{
		assert pPlayable != null;
		aItems.add(pPlayable);
	}
	
	/**
	 * Remove an item from the playlist.
	 * 
	 * @param pPlayable The item to remove.
	 * @pre pPlayable != null;
	 */
	public void remove(Playable pPlayable)
	{
		assert pPlayable != null;
		aItems.remove(pPlayable);
	}

	@Override
	public void play()
	{
		for( Playable playable : aItems )
		{
			playable.play();
		}
	}

	@Override
	public int duration()
	{
		int duration = 0;
		for( Playable playable : aItems )
		{
			duration += playable.duration();
		}
		return duration;
	}

	@Override
	public String description()
	{
		return "Playlist: " + getTitle() + "[" + aItems.size() + " items]";
	}
}
