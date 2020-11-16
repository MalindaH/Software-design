package music;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

/**
 * a Song object represents a song
 */
public class Song {
    private final String aIdentity;
    private final String aFormat;
    // 3 classes for metadata
    private ExpectedTags aExpected;
    private OptionalTags aOptional;
    private CustomTags aCustom;

    // all possible formats of a song; since some formats start with a number, they can't be names of constants 
    // in enum, hence we use a final String[] instead of enum
    private final String[] FORMATS = {"3gp", "8svx", "aa", "aac", "aax", "act", "aiff", "alac", "amr", "ape", 
    		"au", "awb", "dct", "dss", "dvf", "flac", "gsm", "iklax", "ivs", "m4a", "m4b", "m4p", "mmf", "mp3", 
    		"mpc", "msv", "nmf", "ogg", "oga", "mogg", "opus", "ra", "rm", "raw", "rf64", "sln", "tta", "voc", 
    		"vox", "wav", "wma", "wv", "webm", "cda"};

    /**
     * Constructs a song using its full file path (song's full file path = pIdentity + "." + pFormat)
     * @param pIdentity a String representing the song's file path + name
     * @param pFormat a String representing the song's format
     */
    public Song(String pIdentity, String pFormat) {
    		// try-catch block in Song(String pFilePath) constructor where this constructor is called
    		if(pIdentity.equals("") || pIdentity.charAt(pIdentity.length()-1) == '/') {
        		throw new IllegalArgumentException("File name cannot be empty.");
        	}
    		if(pFormat.equals("")) {
        		throw new IllegalArgumentException("Format cannot be empty.");
        	}
            this.aIdentity = pIdentity;
            
            boolean isAudioFormat = false;
            for(int i = 0; i<FORMATS.length; i++) {
            	if(FORMATS[i].equals(pFormat)) {
            		isAudioFormat = true;
            	}
            }
            if(isAudioFormat) {
            	this.aFormat = pFormat;
            }else {
    			throw new IllegalArgumentException("Format input is invalid");
            }
    }
    
    /**
     * Sets expected tags using the inputs, and initializes optional and custom tags
     * @param pTitle a String representing the song's title
     * @param pTimeString a String representing the length of the song
     * @param pArtist a String representing the name of artist
     * @pre pTimeString == hh:mm:ss || pTimeString == mm:ss, where hh>=0, 0<=mm<60, 0<=ss<60
     * (precondition required for Time constructor)
     */
    public void setMetadata(String pTitle, String pTimeString, String pArtist) {
    	// check of input validity included in these constructors
    	Time time = new Time(pTimeString);
    	this.aExpected = new ExpectedTags(pTitle, time, pArtist);
    	this.aOptional = new OptionalTags();
    	this.aCustom = new CustomTags();
    }
    
    /**
     * Returns metadata of the song; if metadata is not initialized, return "Metadata: null"
     * @return a String of identity, format, expected tags, optional tags, and custom tags of the song
     */
    public String getMetadata() {
    	if(aExpected != null) {
    		// if aExpected != null, then aOptional and aCustom also != null because 
    		// they're initialized together in setMetadata()
    		return "----Metadata of "+aIdentity+"."+aFormat+"----\n"+aExpected.toString()+"\n"+aOptional.toString()+"\n"+aCustom.toString();
    	}
    	return "Metadata: null";
    }
    
    /**
     * Sets optional tags using the inputs
     * @param pBPM an int representing the BPM of the song
     * @param pGenre a String representing the genre of the song
     * @param pComposer a String representing the composer of the song
     */
    public void setOptionalTags(int pBPM, String pGenre, String pComposer) {
    	aOptional.setBPM(pBPM);
    	aOptional.setGenre(pGenre);
    	aOptional.setComposer(pComposer);
    }
    
    /**
     * Gets expected tags and their values
     * @return a String of expected tags
     */
    public String getExpectedTags() {
    	return aExpected.toString();
    }
    
    /**
     * Gets optional tags and their values
     * @return a String of optional tags
     */
    public String getOptionalTags() {
    	return aOptional.toString();
    }
    
    /**
     * Gets custom tags and their values
     * @return a String of custom tags
     */
    public String getCustomTags() {
    	return aCustom.toString();
    }

    /**
     * Adds a custom tag of the input name and value
     * @param pTagName a String representing the name of the tag to be added
     * @param pValue an Object (i.e. any type) representing the value of the tag
     */
    public void addCustomTag(String pTagName, Object pValue) {
    	this.aCustom.addCustomTag(pTagName, pValue);
    }

    /**
     * Returns whether the song exists at its file path
     * @return a boolean representing whether the song is valid
     */
    public boolean isValid() {
    	File tmpFile = new File(aIdentity+"."+aFormat);
        return tmpFile.exists();
    }

    /**
     * Gets the identity of the song
     * @return a String representing the song's identity
     */
    public String getIdentity() {
        return aIdentity;
    }

    /**
     * Gets the format of the song
     * @return a String representing the song's format
     */
    public String getFormat() {
        return aFormat;
    }
    
    /**
     * Gets the file path of the song
     * @return a String representing the full file path of the song
     */
    public String getFilePath() {
    	return aIdentity+"."+aFormat;
    }
    
    /**
     * Gets the time from the song's expected tags; if expected tags are not initialized, return null
     * @return a Time object representing the song's time
     */
    public Time getPlayingTime() {
    	if(aExpected != null) {
    		return aExpected.getTime();
    	}
    	return null;
    }
    
    /**
     * Gets the title from the song's expected tags; if expected tags are not initialized, return null
     * @return a String representing the song's title
     */
    public String getTitleOfSong() {
    	if(aExpected != null) {
    		return aExpected.getTitle();
    	}
    	return null;
    }
    
    /**
     * Gets the artist from the song's expected tags; if expected tags are not initialized, return null
     * @return a String representing the song's artist
     */
    public String getArtistOfSong() {
        if(aExpected != null){
            return aExpected.getArtist();
        }
    	return null;
    }
    
    /**
     * @return a String containing the song's identity and format (i.e. the full file path)
     */
    public String toString() {
    	return aIdentity+"."+aFormat;
    }
}
