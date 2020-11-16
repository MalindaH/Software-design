package activity5;

import activity5.Interface.*;
import java.util.*;

/**
 * Represents an album with a title and artist. An album can be incomplete, in the 
 * sense that some tracks can be missing.
 */
public class Album extends AbstractPlayable
{
	private final static String UNTITLED = "<untitled>";
	private final static String UNDEFINED = "<undefined %s>";

	private String aTitle = "";
	private String aArtist = "";
	private Map<Integer, Song> aTracks = new TreeMap<>(); // TreeMap keeps the tracks in order

	/**
	 * Create a new album with no tracks.
	 * 
	 * @param pTitle
	 *          The album's title. Can be empty but not null.
	 * @param pAlbumArtist
	 *          The album's artist. Can be empty but not null.
	 * @pre pTitle != null && pAlbumArtist != null;
	 */
	public Album(String pTitle, String pAlbumArtist)
	{
		assert pTitle != null && pAlbumArtist != null;
		aTitle = pTitle;
		aArtist = pAlbumArtist;
	}

	/**
	 * Create a new album with no track and no artist.
	 * 
	 * @pre pTitle != null;
	 * @param pTitle
	 *          The album's title. Can be empty but not null.
	 */
	public Album(String pTitle)
	{
		assert pTitle != null;
		aTitle = pTitle;
	}

	/**
	 * Get title of album.
	 * 
	 * @return The title of this album, or a string indicating that there is no title.
	 */
	public String getTitle()
	{
		if (aTitle.isBlank())
		{
			return UNTITLED;
		}

		return aTitle;
	}

	/**
	 * Get artist of album.
	 * 
	 * @return The artist of this album, or a string indicating that there is no artist.
	 */
	public String getArtist()
	{
		if (aArtist.isBlank())
		{
			return String.format(UNDEFINED, "artist");
		}

		return aArtist;
	}

	/**
	 * Add a track to this album. If the track already exists, it is written over.
	 * 
	 * @param pNumber
	 *          The track number. Must be greater than 0.
	 * @param pSong
	 *          The song for this track.
	 * @pre pNumber > 0 && pSong != null
	 */
	public void addTrack(int pNumber, Song pSong)
	{
		assert pNumber > 0 && pSong != null;
		aTracks.put(pNumber, pSong);
	}

	@Override
	public int duration()
	{
		int total = 0;
		for (Song song : aTracks.values())
		{
			total += song.duration();
		}
		return total;
	}

	@Override
	public String description()
	{
		return "Album: " + getTitle() + " by " + getArtist();
	}

	/**
	 * Remove song from album.
	 * 
	 * @param pSong
	 * @return returns the track number of the removed song
	 */
	public int remove(Song pSong)
	{
		assert pSong != null;
		if (!isEmpty())
		{
			for (Map.Entry<Integer, Song> item : this.aTracks.entrySet())
			{

				if (item.getValue().equals(pSong)) // there is a match in the album
				{
					this.aTracks.remove(item.getKey()); // remove the song from the treeMap
					return item.getKey(); // return the track number
				}

			}
			System.out.println("remove(): This album does not contain this song and the song could not be removed.");
			return 0;
		}
		else
		{ // empty album
			System.out.println("remove(): Album is empty and contains no songs.");
			return 0;
		}
	}

	/**
	 * Check if tree map is empty.
	 * 
	 * @return true/false if tree map is empty.
	 */
	public boolean isEmpty()
	{
		return this.aTracks.isEmpty();
	}



	/**
	 * Compare 2 albums to check for equality.
	 * 
	 * @param pObject
	 *          album object
	 * @return true/false if this album and param album are equal
	 */
	public boolean equals(Object pObject)
	{
		if (pObject == null)
		{
			return false;
		}
		else if (pObject == this)
		{ // same object
			return true;
		}
		else if (pObject.getClass() != this.getClass())
		{

			return false;
		}
		else // compare here
		{
			Album pAlbum = (Album) pObject;
			return this.aTracks.values().containsAll(pAlbum.aTracks.values())
					&& this.aTracks.keySet().containsAll(pAlbum.aTracks.keySet());
		}

	}

	/**
	 * overriding hashcode of album object
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(this.aTitle, this.aArtist, Arrays.hashCode(this.aTracks.values().toArray()));
	}

	public void printSongInAlbum()
	{
		if (isEmpty() == false)
		{
			for (Map.Entry<Integer, Song> item : this.aTracks.entrySet())
			{
				System.out.println("Track : " + item.getKey() + " Song : " + item.getValue().description());
			}
		}

	}

	@Override
	protected void playing()
	{
		System.out.println(description());
		for (Song track : aTracks.values())
		{
			// if an Album is not associated with playcount information
			// then all songs within this Album should also be set to not associate with playCount information
			track.setHasPlayCount(this.getHasPlayCount());
			track.play();
		}
	}
}
