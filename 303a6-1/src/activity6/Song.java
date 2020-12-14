package activity6;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Represents a piece of audio media that can be played,
 * along with its metadata.
 */
public class Song implements Playable
{
	private final static String UNTITLED = "<untitled>";
	private final static String UNDEFINED = "<undefined %s>";
	private final static String TAG_ARTIST = "artist";
	
	private final File aFile;
	private int aDuration;
	private String aTitle = "";

	private Map<String, String> aTags = new HashMap<>();
	
	/**
	 * Create a new song with no meta-data.
	 * 
	 * @param pFile The corresponding file.
	 * @pre pFile != null;
	 */
	public Song(File pFile, String pTitle)
	{
		assert pFile != null;
		aFile = pFile;
		aTitle = pTitle;
		aDuration = MediaSystem.instance().duration(pFile);
	}
	
	/**
	 * @return The file associated with this song. Never null.
	 */
	public File getFile()
	{
		return aFile;
	}
	
	/**
	 * Set the title of the song. 
	 * 
	 * @param pTitle The title. Can be empty but not null.
	 * @pre pTitle != null
	 */
	public void setTitle(String pTitle)
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
	 * Set a piece of meta-data to associate with this song.
	 * Tags are case-insensitive.
	 * 
	 * @param pTag The tag for the piece of meta data.
	 * @param pValue The value for the piece of meta-data.
	 * @pre pTag != null && pValue != null
	 */
	public void setTag(String pTag, String pValue)
	{
		assert pTag != null && pValue != null;
		aTags.put(pTag.toLowerCase(), pValue);
	}
	
	/**
	 * Obtain the value for a given tag.
	 * 
	 * @param pTag The tag to get.
	 * @return The value for the tag, or a string indicating
	 * that there is no value for this tag if that's the case.
	 * @pre pTag != null;
	 */
	public String getTag(String pTag)
	{
		assert pTag != null;
		return aTags.getOrDefault(pTag.toLowerCase(), String.format(UNDEFINED, pTag));
	}
	
	@Override
	public void play()
	{
		MediaSystem.instance().playSong(this);
	}

	@Override
	public int duration()
	{
		return aDuration;
	}
	
	@Override
	public String toString()
	{
		return description();
	}

	@Override
	public String description()
	{
		return getTitle() + " by " + getTag(TAG_ARTIST);
	}
	
	@Override
	public int hashCode()
	{
		try
		{
			return aFile.getCanonicalFile().hashCode();
		}
		catch (IOException e)
		{
			return aFile.hashCode();
		}
	}
	
	@Override
	public boolean equals(Object pSong)
	{
		if (pSong == null)
		{
			return false;
		}
		if (pSong == this)
		{
			return true;
		}
		if (pSong.getClass() != getClass())
		{
			return false;
		}
		Song other = (Song) pSong;
		try
		{
			return aFile.getCanonicalPath().equals(other.aFile.getCanonicalPath());
		}
		catch (IOException e)
		{
			return false;
		}
	}
}
