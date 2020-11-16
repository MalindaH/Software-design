package music;

import java.io.File;
import music.tags.*;

/**
 * a Song object represents a song
 */
public class Song implements Playable{
	private String aIdentity; // aIdentity = the full file path
	private String aFormat;
	private MetadataTags aMetadata;
	private boolean favorite = false;

	// all possible formats of a song; since some formats start with a number, they can't be names of constants 
	// in enum, hence we use a final String[] instead of enum
	private final String[] FORMATS = {"3gp", "8svx", "aa", "aac", "aax", "act", "aiff", "alac", "amr", "ape", 
			"au", "awb", "dct", "dss", "dvf", "flac", "gsm", "iklax", "ivs", "m4a", "m4b", "m4p", "mmf", "mp3", 
			"mpc", "msv", "nmf", "ogg", "oga", "mogg", "opus", "ra", "rm", "raw", "rf64", "sln", "tta", "voc", 
			"vox", "wav", "wma", "wv", "webm", "cda"};

	/**
	 * Constructs a song using its full file path
	 * @param pFilePath a String representing the file path of the song to be created
	 * @pre pFilePath != null && !pFilePath.equals("")
	 */
	public Song(String pFilePath) {
		assert pFilePath != null && !pFilePath.equals("");
		try {
			// check the file path exists
			File tmpFile = new File(pFilePath);
			if(!tmpFile.exists()) {
				throw new IllegalArgumentException("File path does not exist.");
			}
			String[] info = pFilePath.split("\\.");
			this.aIdentity = pFilePath;
			boolean isAudioFormat = false;
			for(int i = 0; i<FORMATS.length; i++) {
				if(FORMATS[i].equals(info[1])) {
					isAudioFormat = true;
				}
			}
			if(isAudioFormat) {
				this.aFormat = info[1];
			}else {
				throw new IllegalArgumentException("Format of song is invalid.");
			}
			// set expected tags using default values
			String title = info[0].substring(info[0].lastIndexOf("/") + 1);
			this.setMetadata(title, "0:0", "unknown artist");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructs a song using its full file path
	 * @param pFilePath a String representing the file path of the song to be created
	 * @param pTitle a String representing the song's title
	 * @param pTimeString a String representing the length of the song
	 * @param pArtist a String representing the name of artist
	 * @pre pFilePath != null && !pFilePath.equals("") && pTitle != null && !pTitle.equals("") && pTimeString != null && !pTimeString.equals("") && pArtist != null && !pArtist.equals("")
	 */
	public Song(String pFilePath, String pTitle, String pTimeString, String pArtist) {
		assert pFilePath != null && !pFilePath.equals("") && pTitle != null && !pTitle.equals("") && pTimeString != null && !pTimeString.equals("") && pArtist != null && !pArtist.equals("");
		try {
			// check the file path exists
			File tmpFile = new File(pFilePath);
			if(!tmpFile.exists()) {
				throw new IllegalArgumentException("File path does not exist.");
			}
			String[] info = pFilePath.split("\\.");
			this.aIdentity = pFilePath;
			boolean isAudioFormat = false;
			for(int i = 0; i<FORMATS.length; i++) {
				if(FORMATS[i].equals(info[1])) {
					isAudioFormat = true;
				}
			}
			if(isAudioFormat) {
				this.aFormat = info[1];
			}else {
				throw new IllegalArgumentException("Format of song is invalid.");
			}
			// set expected tags
			this.setMetadata(pTitle, pTimeString, pArtist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets expected tags using the inputs, and initializes optional and custom tags
	 * @param pTitle a String representing the song's title
	 * @param pTimeString a String representing the length of the song
	 * @param pArtist a String representing the name of artist
	 * where hh>=0, 0<=mm<60, 0<=ss<60 (precondition required for Time constructor)
	 * @pre pTitle != null && !pTitle.equals("") && pTimeString != null && !pTimeString.equals("") && pArtist != null && !pArtist.equals("")
	 */
	public void setMetadata(String pTitle, String pTimeString, String pArtist) {
		assert pTitle != null && !pTitle.equals("") && pTimeString != null && !pTimeString.equals("") && pArtist != null && !pArtist.equals("");
		// check of input validity included in these constructors
		Time time = new Time(pTimeString);
		this.aMetadata = new MetadataTags(pTitle, time, pArtist);
	}

	/**
	 * Returns metadata of the song.
	 * @return a String of identity, format, expected tags, optional tags, and custom tags of the song
	 * @pre aMetadata != null
	 */
	public String getMetadata() {
		//aMetadata can be asserted here because all constructors initialize aMetadata
		assert aMetadata != null;
		return "----Metadata of "+aIdentity+"----\n"+aMetadata.toString();
	}

	/**
	 * Sets optional tags using the inputs
	 * @param pBPM an int representing the BPM of the song
	 * @param pGenre a String representing the genre of the song
	 * @param pComposer a String representing the composer of the song
	 * @pre pGenre != null && !pGenre.equals("") && pComposer != null && !pComposer.equals("")
	 */

	public void setOptionalTags(int pBPM, String pGenre, String pComposer) {
		assert pGenre != null && !pGenre.equals("") && pComposer != null && !pComposer.equals("");
		aMetadata.set(TagType.BPM, pBPM);
		aMetadata.set(TagType.GENRE, pGenre);
		aMetadata.set(TagType.COMPOSER, pComposer);
	}
	
	/**
	 * Sets the specified tag using the input value
	 * @param pTag the tag to set
	 * @param pValue the value to set the tag to
	 * @pre pTag != null && pValue != null
	 */
	public void setTag(Tag pTag, Object pValue) {
		assert pTag != null && pValue != null;
		// check for input validity included in this method
		aMetadata.set(pTag, pValue);
	}

	/**
	 * Gets the value of the specified tag
	 * @param pTag the specified custom tag
	 * @return value of the input tag, return null if no such tag exists
	 * @pre pTag != null
	 */
	public Object getTagValue(Tag pTag) {
		assert pTag != null;
		return aMetadata.get(pTag);
	}

	/**
	 * Gets expected tags and their values
	 * @return a String of expected tags
	 */
	public String getExpectedTags() {
		return aMetadata.getExpectedTags();
	}

	/**
	 * Gets optional tags and their values
	 * @return a String of optional tags
	 */
	public String getOptionalTags() {
		return aMetadata.getOptionalTags();
	}

	/**
	 * Gets custom tags and their values
	 * @return a String of custom tags
	 */
	public String getCustomTags() {
		return aMetadata.getCustomTags();
	}

	/**
	 * Adds a custom tag of the input name and value
	 * @param pTagName a String representing the name of the tag to be added
	 * @param pValue an Object (i.e. any type) representing the value of the tag
	 * @pre pTagName != null && !pTagName.equals("") && pValue != null
	 */
	public void addCustomTag(String pTagName, Object pValue) {
		assert pTagName != null && !pTagName.equals("") && pValue != null;
		aMetadata.addCustomTag(pTagName, pValue);
	}

	/**
	 * Returns whether the song exists at its file path
	 * @return a boolean representing whether the song is valid
	 */
	public boolean isValid() {
		File tmpFile = new File(aIdentity);
		return tmpFile.exists();
	}

	/**
	 * Gets the identity (file path) of the song
	 * @return a String representing the song's identity
	 */
	public String getIdentity() { return aIdentity; }

	/**
	 * Gets the format of the song
	 * @return a String representing the song's format
	 */
	public String getFormat() {
		return aFormat;
	}

	/**
	 * Gets the time from the song's expected tags; if expected tags are not initialized, return null
	 * @return a Time object representing the song's time
	 */
	public Time getPlayingTime() {
		if(aMetadata != null) {
			return (Time) aMetadata.get(TagType.TIME);
		}
		return null;
	}

	/**
	 * Gets the title from the song's expected tags; if expected tags are not initialized, return null
	 * @return a String representing the song's title
	 */
	public String getTitleOfSong() {
		if(aMetadata != null) {
			return (String) aMetadata.get(TagType.TITLE);
		}
		return null;
	}

	/**
	 * Gets the artist from the song's expected tags; if expected tags are not initialized, return null
	 * @return a String representing the song's artist
	 */
	public String getArtistOfSong() {
		if(aMetadata != null){
			return (String) aMetadata.get(TagType.ARTIST);
		}
		return null;
	}
	
	/**
	 * Gets the genre from the song's expected tags; if expected tags are not initialized, return "Unknown"
	 * @return a String representing the song's Genre
	 */
	public String getGenreOfSong() {
		if(aMetadata != null){
			return String.valueOf(aMetadata.get(TagType.GENRE));
		}
		return null;
	}
	
	/**
	 * Gets the BPM from the song's expected tags; if expected tags are not initialized, return null
	 * @return a String representing the song's BPM
	 */
	public String getBPMOfSong() {
		if(aMetadata != null){
			return String.valueOf(aMetadata.get(TagType.BPM));
		}
		return null;
	}

	/**
	 * Gets the composer from the song's expected tags; if expected tags are not initialized, return null
	 * @return a String representing the song's artist
	 */
	public String getComposerOfSong() {
		if(aMetadata != null){
			return String.valueOf(aMetadata.get(TagType.COMPOSER));
		}
		return null;
	}
	
	/**
	 * @return a String containing the song's full file path
	 */
	public String toString() {
		return aIdentity;
	}
	
	//play song
	@Override
	public void play() {
		System.out.println("--Currently Playing Song--\nTitle: " + aMetadata.get(TagType.TITLE) + "  Length: " + aMetadata.get(TagType.TIME) + "  Artist: " + aMetadata.get(TagType.ARTIST));
	};
	
	//add song to favorites
	@Override
	public void addtToFavorites() {
		favorite = true;
	};
	
	@Override
	public void removeFromFavorites() {
		favorite = false;
	}
	
	@Override
	public boolean isFavorite() {
		return favorite;
	}
	
	//A method to check validity or emptiness
	@Override
	public boolean isEmpty() {
		return this.isValid();
	};
	
}
