package activity3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a named list of songs.
 */
public class Playlist
{
	private final List<Song> aSongs = new ArrayList<>();
	private String aTitle; 
	
	public Playlist(String pTitle)
	{
		aTitle = pTitle;
	}
	
	public void setTitle(String pTitle)
	{
		aTitle = pTitle;
	}
	
	public String getTitle()
	{
		return aTitle;
	}
	
	public void add(Song pSong)
	{
		aSongs.add(pSong);
	}
	
	public void backup() 
	{
	    System.out.println("Backing up the library");
	}
}
