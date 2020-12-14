package activity6;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents an album with a title and artist. 
 * An album can be incomplete, in the sense that some
 * tracks can be missing.
 */
public class Album implements Playable
{
	private final static String UNTITLED = "<untitled>";
	private final static String UNDEFINED = "<undefined %s>";
	
	private String aTitle = "";
	private String aArtist = "";
	private Map<Integer, Song> aTracks = new TreeMap<>(); // TreeMap keeps the tracks in order
	
	/**
	 * Create a new album with no track.
	 * 
	 * @param pTitle The album's title. Can be empty but not null.
	 * @param pAlbumArtist The album's artist. Can be empty but not null.
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
	 * @param pTitle The album's title. Can be empty but not null.
	 * @pre pTitle != null;
	 */
	public Album(String pTitle) 
	{
		assert pTitle != null;
		aTitle = pTitle;
	}
	
	/**
	 * @return The title of this album, or a string indicating that there is no title.
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
	 * @return The artist of this album, or a string indicating that there is no artist.
	 */
	public String getArtist()
	{
		if( aArtist .isBlank() )
		{
			return String.format(UNDEFINED, "artist");
		}
		return aArtist;
	}
	
	/**
	 * Add a track to this album. If the track already exists,
	 * it is written over.
	 * 
	 * @param pNumber The track number. Must be greater than 0.
	 * @param pSong The song for this track. 
	 * @pre pNumber > 0 && pSong != null
	 */
	public void addTrack(int pNumber, Song pSong)
	{
		assert pNumber > 0 && pSong != null;
		aTracks.put(pNumber, pSong);
	}
	
	@Override
	public void play()
	{
		for( Song track : aTracks.values() )
		{
			track.play();
		}
	}

	@Override
	public int duration()
	{
		int total = 0;
		for( Song song : aTracks.values() )
		{
			total += song.duration();
		}
		return total;
	}

	@Override
	public String description()
	{
		return "Album: " + aTitle + " by " + aArtist;
	}
}
