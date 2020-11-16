package activity3;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Song
{
	/*
	 *  it possible to create a Song by only specifying the file.
	 *  All other pieces of data may be absent. 
	 *  It may also be possible to add a tag with an absent value.
	 */
	private final String aFile;
	private Optional<String> aTitle;
	private Optional<Integer> aDuration;
	private SongInfo aArtist; // Using the null class technique since Artist class implements the SongInfo interface
	private SongInfo aGenre;
	private Map<String, Optional<String>> aTags = new HashMap<>();

	// all possible formats of a song; since some formats start with a number, they can't be names of constants 
	// in enum, hence we use a final String[] instead of enum
	private final String[] FORMATS = {"3gp", "8svx", "aa", "aac", "aax", "act", "aiff", "alac", "amr", "ape", 
			"au", "awb", "dct", "dss", "dvf", "flac", "gsm", "iklax", "ivs", "m4a", "m4b", "m4p", "mmf", "mp3", 
			"mpc", "msv", "nmf", "ogg", "oga", "mogg", "opus", "ra", "rm", "raw", "rf64", "sln", "tta", "voc", 
			"vox", "wav", "wma", "wv", "webm", "cda"};

	/**
	 * Constructs a Song using only the specified file path
	 * @param pFile String representing the file path
	 * @pre pFile != null && !pFile.equals("")
	 */
	public Song(String pFile)
	{
		assert pFile != null && !pFile.equals("");
		try {
			// check the file path exists, and the format is valid
			if (!(new File(pFile).exists())) {
				throw new IllegalArgumentException("Filepath provided for song creation is not valid.");
			}
			String[] info = pFile.split("\\.");
			boolean isAudioFormat = false;
			for(String format: FORMATS) {
				if(format.equals(info[1])) {
					isAudioFormat = true;
				}
			}
			if(!isAudioFormat) {
				throw new IllegalArgumentException("Format of song is invalid.");
			}
			// could also initialize aTitle by default value
			// String title = info[0].substring(info[0].lastIndexOf("/") + 1);
			// this.setTitle(title);
		} catch(Exception e) {
			e.printStackTrace();
		}
		aFile = pFile; // have to initialize this final field
		aTitle = Optional.empty();
		aDuration = Optional.empty(); 
		aGenre = SongInfo.NULL; // can't cast this to Genre, so use SongInfo for aGenre and aArtist fields
		aArtist = SongInfo.NULL;
	}

	/**
	 * Constructs a Song using the input values
	 * @param pFile String representing the file path
	 * @param pTitle String representing the title
	 * @param pDuration Integer representing the duration (time)
	 * @param pArtist Artist of the Song
	 * @param pGenre Genre of the Song
	 * @pre pFile != null && !pFile.isEmpty() && pArtist != null && pGenre != null
	 */
	public Song(String pFile, String pTitle, Integer pDuration, Artist pArtist, Genre pGenre)
	{
		this(pFile); // assert of pFile is included in the above constructor
		assert pArtist != null && pGenre != null;

		this.aTitle = Optional.ofNullable(pTitle);
		this.aDuration = Optional.ofNullable(pDuration);
		this.aGenre = pGenre;
		this.aArtist = pArtist;
	}

	/**
	 * Constructs a Song using the input values, creating an Artist and a Genre using the input Strings
	 * @param pFile String representing the file path
	 * @param pTitle String representing the title
	 * @param pDuration Integer representing the duration (time)
	 * @param pArtist String representing name of the Artist
	 * @param pGenre String representing the name of the Genre
	 * No need to assert since the above constructor and the SongInfoFactory getters have asserts
	 */
	public Song(String pFile, String pTitle, Integer pDuration, String pArtist, String pGenre){
		this(pFile, pTitle, pDuration, SongInfoFactory.getArtist(pArtist), SongInfoFactory.getGenre(pGenre));
	}

	/**
	 * Return a copy of this Song, to be used for creating deep copy in Library, Playlist, Album
	 * @return a new Song object having same fields as this Song
	 */
	public Song copy() {	
		Song output = new Song(this.getFile());
		output.setTitle(this.getTitle());
		output.setDuration(this.getDuration());
		output.setArtist(this.getArtist());
		output.setGenre(this.getGenre());
		return output;
	}

	/**
	 * Returns the file path
	 * @return String representing the file path
	 */
	public String getFile()
	{
		return aFile;
	}

	/**
	 * gets the title
	 * @return String representing the title, return null if title is not present
	 */
	public String getTitle()
	{
		return this.aTitle.isPresent()? this.aTitle.get() : null; 
	}

	/**
	 * gets the duration (time)
	 * @return Integer representing the duration, return null if duration is not present
	 */
	public Integer getDuration()
	{
		return this.aDuration.isPresent()? this.aDuration.get() : null; 
	}

	/**
	 * gets the Artist
	 * @return aArtist The artist of the album, return null if Artist is invalid
	 */
	public Artist getArtist()
	{
		return aArtist.isNull()? null : (Artist) aArtist;
	}

	/**
	 * gets the Genre
	 * @return aGenre The genre of the song, return null if Genre is invalid
	 */
	public Genre getGenre()
	{
		return aGenre.isNull()? null : (Genre) aGenre;
	}

	/**
	 * sets the title
	 * @param pTitle String representing the title
	 */
	public void setTitle(String pTitle) 
	{
		aTitle = Optional.ofNullable(pTitle);
	}

	/**
	 * sets the Artist
	 * accept null input because need to be able to set Artist to SongInfo.NULL in Song.copy()
	 * @param pArtist the Artist to be set
	 */
	public void setArtist(Artist pArtist) 
	{
		if(pArtist == null) {
			aArtist = SongInfo.NULL;
		} else {
			aArtist = pArtist;
		}
	}

	/**
	 * gets the Artist of the specified name and sets the Artist
	 * @param pArtist String representing name of Artist
	 * @pre pArtist != null && !pArtist.equals("")
	 */
	public void setArtist(String pArtist){
		assert pArtist != null && !pArtist.equals("");
		aArtist = SongInfoFactory.getArtist(pArtist);
	}

	/**
	 * sets duration (time)
	 * @param pDuration Integer representing the duration
	 */
	public void setDuration(Integer pDuration)
	{
		aDuration = Optional.ofNullable(pDuration);
	}

	/**
	 * sets the Genre
	 * accept null input because need to be able to set Genre to SongInfo.NULL in Song.copy()
	 * @param pGenre the Genre to be set
	 */
	public void setGenre(Genre pGenre) {
		if(pGenre == null) {
			aGenre = SongInfo.NULL;
		} else {
			aGenre = pGenre;
		}
	}

	/**
	 * gets the Genre of the specified name and sets the Genre
	 * @param pGenre String representing name of the Genre
	 * @pre pGenre != null && !pGenre.equals("")
	 */
	public void setGenre(String pGenre){
		assert pGenre != null && !pGenre.equals("");
		aGenre = SongInfoFactory.getGenre(pGenre);
	}

	/**
	 * removes the specified tag
	 * @param pTag String representing the tag to be removed
	 * @pre pTag != null && !pTag.equals("")
	 */
	public void removeTag(String pTag) {
		assert pTag != null && !pTag.equals("");
		aTags.remove(pTag);
	}

	/**
	 * returns whether the Song has the specified tag
	 * @param pTag String representing the tag to be checked
	 * @return true if this Song has the tag
	 * @pre pTag != null && !pTag.isEmpty()
	 */
	public boolean hasTag(String pTag) {
		assert pTag != null && !pTag.isEmpty();
		return aTags.containsKey(pTag);
	}

	/**
	 * adds the specified tag and sets its value to Optional.empty()
	 * @param pTag String representing the tag
	 * @return true if successful
	 * @pre pTag != null && !pTag.isEmpty()
	 */
	public boolean addTag(String pTag){
		assert pTag != null && !pTag.isEmpty();
		if(!this.hasTag(pTag)){
			aTags.put(pTag, Optional.empty());
			return true;
		}
		return false;
	}

	/**
	 * adds the tag and the specified value
	 * @param pTag String representing the tag
	 * @param pValue String representing value of the tag to be set
	 * @return true if successful
	 * @pre pTag != null && !pTag.isEmpty()
	 */
	public boolean addTag(String pTag, String pValue){
		assert pTag != null && !pTag.isEmpty();
		if(!this.hasTag(pTag)){
			aTags.put(pTag, Optional.ofNullable(pValue));
			return true;
		}
		return false;
	}

	/**
	 * changes the value of the tag
	 * @param pTag String representing the tag
	 * @param pValue String representing value of the tag
	 * @pre pTag != null && !pTag.isEmpty()
	 */
	public void editTag(String pTag, String pValue)
	{
		assert pTag != null && !pTag.isEmpty();
		aTags.replace(pTag, Optional.ofNullable(pValue));
	}

	/**
	 * checks whether the input Song equals this Song according to pCriteria
	 * @param pSong Song to be compared with
	 * @param pCriteria criteria to check equality
	 * @return true if this song and pSong are equal according to pCriteria
	 * @pre pSong != null
	 */
	public boolean equals(Song pSong, SongCriteria pCriteria){
		assert pSong != null;
		if(pCriteria == SongCriteria.FILE){
			return this.aFile.equalsIgnoreCase(pSong.aFile);
		} else if(pCriteria == SongCriteria.TITLEARTIST){
			// If the artist fields are not of the same class, the equals() method fails.
			if((this.aArtist.isNull() && !pSong.aArtist.isNull()) || (!this.aArtist.isNull() && pSong.aArtist.isNull())){
				return false;
			}
			if(this.aTitle.isPresent() && pSong.aTitle.isPresent()) {
				return (this.aTitle.get().equalsIgnoreCase(pSong.aTitle.get()) && this.aArtist.equals(pSong.aArtist));
			}
			else if(!this.aTitle.isPresent() && !pSong.aTitle.isPresent()){
				return this.aArtist.equals(pSong.aArtist);
			}
		}
		return false;
	}

	public static enum SongCriteria {
		FILE, TITLEARTIST
	}

	@Override
	public String toString() {
		String titlePrint = "-";
		if(aTitle.isPresent()){
			titlePrint = aTitle.get();
		}
		return this.aFile+" -- Title: "+titlePrint+", Artist: "+aArtist+", Genre: "+aGenre;
	}
}
