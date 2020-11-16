package activity3;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Song
{
	private final File aFile;
	private String aTitle;
	private int aDuration;
	private Artist aArtist;
	private String aGenre;
	private Map<String, String> aTags = new HashMap<>();
	
	public Song(File pFile)
	{
		aFile = pFile;
	}
	
	public File getFile()
	{
		return aFile;
	}
	
	public void editTag(String pTag, String pValue)
	{
		aTags.put(pTag, pValue);
	}
	
	public boolean isValid()
	{
		return aFile.exists() && !aFile.isDirectory();
	}
	
	@Override
	public boolean equals(Object pSong)
	{
		if( pSong == null )
		{
			return false;
		}
		if( pSong == this )
		{
			return true;
		}
		if( pSong.getClass() != getClass() )
		{
			return false;
		}
		Song other = (Song) pSong;
		try
		{
			return aFile.getCanonicalPath().equals(other.aFile.getCanonicalPath());
		}
		catch( IOException e)
		{
			return false;
		}
	}
	
	@Override
	public int hashCode()
	{
		try
		{
			return aFile.getCanonicalFile().hashCode();
		}
		catch( IOException e )
		{
			return aFile.hashCode();
		}
	}
}
