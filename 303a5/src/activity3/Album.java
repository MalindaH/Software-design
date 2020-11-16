package activity3;

import java.util.Map;

/**
 * Represents an album with a title (mandatory) and 
 * artist (can be absent). An album can be incomplete,
 * in the sense that some tracks can be missing.
 */
public class Album
{
	private String aTitle;
	private Artist aArtist;
	private Map<Integer, Song> aTracks;
	
	public Album() {}
	
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
}
