package activity5;

import java.util.*;

/**
 * Represents a named list of Playable items.
 */
public class Playlist extends AbstractPlayable
{
	private final List<Playable> aItems = new ArrayList<>();
	private String aTitle;
	private final static String UNTITLED = "<untitled>";

	/**
	 * Create a new, empty playlist.
	 * 
	 * @param pTitle
	 *          The title of the playlist.
	 * @param aSilenceLength
	 * @pre pTitle != null;
	 */
	public Playlist(String pTitle)
	{
		aTitle = pTitle;
	}

	/**
	 * @return The title of this song. Never null. If the title is empty, returns a string describing this instead of the
	 *         empty string.
	 */
	public String getTitle()
	{
		if (aTitle.isBlank())
		{
			return UNTITLED;
		}
		return aTitle;
	}

	@Override
	public int duration()
	{
		int duration = 0;
		for (Playable playable : aItems)
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

	/**
	 * Add an item to the playlist.
	 * 
	 * @param pPlayable
	 *          The item to add.
	 * @pre pPlayable != null;
	 */
	public void add(Playable pPlayable)
	{
		assert pPlayable != null;
		aItems.add(pPlayable);
	}

	/**
	 * 
	 * removes song ( playable from the list)
	 * 
	 * @param pPlayable
	 *          (song)
	 */
	public void remove(Playable pPlayable)
	{
		if (isEmpty() == false)
		{
			for (Playable aPlayable : this.aItems)// search if the playable(song) exists or not
			{
				if ( aPlayable.equals(pPlayable))
				{ // playable(song) exists in the playlist
					this.aItems.remove(pPlayable);
					break;
				}
			}
		}
		else
		{
			System.out.println("playlist is empty :: nothing to remove");
		}
	}

	public boolean isEmpty()
	{
		return this.aItems.size() == 0;

	}

	/**
	 * for comparing two playlist object
	 * 
	 */
	@Override
	public boolean equals(Object pObject)
	{
		if (pObject == null)
			return false;
		else if (pObject == this) // same object
		{
			return true;
		}
		else if (pObject.getClass() != this.getClass())
		{
			return false;
		}
		else
		{
			// unwrap the object
			Playlist pPlaylist = (Playlist) pObject;
			return this.aTitle.equals(pPlaylist.aTitle) && this.duration() == pPlaylist.duration()
					&& this.aItems.containsAll(pPlaylist.aItems) && pPlaylist.aItems.containsAll(this.aItems);
		}

	}

	/**
	 * Overriding the hashcode of playlist object
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(aTitle, duration(), Arrays.hashCode(this.aItems.toArray()));
	}

	public void printSongsInPlaylist()
	{
		if (!isEmpty()) {
			System.out.println("printSongsInPlaylist:\n"+this.toString());
		} else {
			System.out.println("printSongsInPlaylist: playlist is empty :: nothing to print");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Playable playable : this.aItems)
		{
			str.append(playable.description()+"\n");
		}
		return str.toString();
	}

	@Override
	protected void playing()
	{
		//System.out.println("Play playlist " + aTitle + ": ");
		System.out.println(description());
		for (Playable playable : aItems)
		{
			// if a playlist is not associated with playcount information
			// then all songs within this playlist should also be set to not associate with playCount information
			 ((AbstractPlayable) playable).setHasPlayCount(this.getHasPlayCount());
			playable.play();
		}
	}

}
