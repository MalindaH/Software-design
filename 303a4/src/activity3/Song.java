package activity3;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

public class Song implements SongCollection
{
	private final File aFile;
	private String aTitle;
	private int aDuration;
	private Optional<Artist> aArtist;
	private Optional<Genre> aGenre;
	private Map<String, String> aTags = new HashMap<>();

	/**
	 * Constructs a Song using the input File
	 * @param pFile File representing the Song
	 * @pre pFile != null
	 */
	public Song(File pFile)
	{
		assert pFile != null;
		aFile = pFile;
	}
	
	/**
	 * Constructs a Song
	 * @param pFile File representing the Song
	 * @param pTitle String representing the title
	 * @param pArtist Artist representing the artist of Song
	 * @param pGenre Genre representing the genre of Song
	 * @pre pTitle != null && pFile != null
	 */
	public Song(File pFile, String pTitle, Artist pArtist, Genre pGenre)
	{
		assert pTitle != null && pFile != null;
		aFile = pFile;
		aTitle = pTitle;
		aArtist = Optional.ofNullable(pArtist);
		aGenre = Optional.ofNullable(pGenre);
	}
	
	/**
	 * sets length of this Song
	 * @param pInt int representing the duration
	 */
	public void setDuration(int pInt) {
		aDuration = pInt;
	}

	/**
	 * gets the file
	 * @return File of this Song
	 */
	public File getFile()
	{
		return aFile;
	}

	/**
	 * Edits the specified tag
	 * @param pTag String representing the tag
	 * @param pValue String representing value of the tag
	 * @pre pTag != null && pValue != null
	 */
	public void editTag(String pTag, String pValue)
	{
		assert pTag != null && pValue != null;
		aTags.put(pTag, pValue);
	}

	/**
	 * @return boolean representing whether the File of this Song is valid
	 */
	public boolean isValid()
	{
		return aFile.exists() && !aFile.isDirectory();
	}

	/**
	 * @pre pSong != null
	 */
	@Override
	public boolean equals(Object pSong)
	{
		assert pSong != null;
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
	public int getRuntime()
	{
		return aDuration;
	}

	@Override
	public String getTitle()
	{
		return aTitle;
	}
	
	public Artist getArtist() {
		if(aArtist.isPresent()) {
			return aArtist.get();
		}
		return null;
	}
	
	public Genre getGenre() {
		if(aGenre.isPresent()) {
			return aGenre.get();
		}
		return null;
	}

	/**
	 * @return a list containing a copy of this
	 */
	@Override
	public List<SongCollection> getContents()
	{
		Song newSong = this.clone();
		List<SongCollection> listToReturn = new ArrayList<>();
		listToReturn.add(newSong);
		return listToReturn;
	}
	
	/**
	 * @return returns a copy of the song
	 */
	@Override
	public Song clone()
	{
		try
		{
			Song clone = (Song) super.clone();
			clone.aTags = new HashMap<>(clone.aTags);
			// don't need to deep clone immutable Artist and Genre
			return clone;
		}
		catch (CloneNotSupportedException e)
		{
			assert false;
			return null;
		}
	}

	@Override
	public String description()
	{
		return "---Now playing: Song "+this.getTitle()+" by "+this.getArtist()+"---";
	}

	@Override
	public boolean contains(SongCollection pSongCollection) {
		return false;
	}
}
