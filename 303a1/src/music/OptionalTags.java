package music;

import java.util.HashMap;

/**
 * OptionalTags of a song include optional keys: BPM, GENRE, COMPOSER
 */
public class OptionalTags {
	private enum Tag {
		BPM, GENRE, COMPOSER;
	}
	
	private HashMap<Tag, Object> aOptionalTags;
	
	/**
	 * Constructs an empty OptionalTags object
	 */
	public OptionalTags() {
		aOptionalTags = new HashMap();
	}
	
	/**
	 * Constructs OptionalTags with the input tags
	 * @param pBPM an int representing the BPM
	 * @param pGenre a String representing the genre
	 * @param pComposer a String representing the composer
	 */
	public OptionalTags(int pBPM, String pGenre, String pComposer) {
		aOptionalTags = new HashMap();
		aOptionalTags.put(Tag.BPM, pBPM);
		aOptionalTags.put(Tag.GENRE, pGenre);
		aOptionalTags.put(Tag.COMPOSER, pComposer);
	}
	
	/**
	 * Gets the BPM value
	 * @return an int of BPM value, if BPM was not assigned, return -1
	 */
	public int getBPM() {
		Integer temp = (Integer) aOptionalTags.get(Tag.BPM);
		if(temp!= null) {
			return (int) temp;
		}
		return -1;
	}
	
	/**
	 * Gets the genre
	 * @return a String representing the genre (return null if genre was not assigned)
	 */
	public String getGenre() {
		return (String) aOptionalTags.get(Tag.GENRE);
	}
	
	/**
	 * Gets the composer
	 * @return a String representing the composer (return null if genre was not assigned)
	 */
	public String getComposer() {
		return (String) aOptionalTags.get(Tag.COMPOSER);
	}
	
	/**
	 * Sets the BPM; if input int <=0, throw exception and assign BPM to -1
	 * @param pBPM an int representing the BPM
	 * @pre pBPM > 0
	 */
	public void setBPM(int pBPM) { 
		try {
			if(pBPM <= 0) {
				throw new IllegalArgumentException ("BPM has to be a positive integer!");
			}
			aOptionalTags.put(Tag.BPM, pBPM);
		} catch (Exception e) {
			e.printStackTrace();
			aOptionalTags.put(Tag.BPM, -1);
		}
		
	}
	
	/**
	 * Sets the genre; if input is null, assign genre to "unknown"
	 * @param pGenre a String representing the genre
	 * @pre pGenre != null
	 */
	public void setGenre(String pGenre) {
		try {
			if(pGenre == null) {
				throw new IllegalArgumentException ("Genre input is invalid!");
			}
			aOptionalTags.put(Tag.GENRE, pGenre);
		} catch (Exception e) {
			e.printStackTrace();
			aOptionalTags.put(Tag.GENRE, "unknown");
		}
		
	}
	
	/**
	 * Sets the composer; if input is null, assign composer to "unknown"
	 * @param pComposer a String representing the composer
	 * @pre pComposer != null
	 */
	public void setComposer(String pComposer) {
		try {
			if(pComposer == null) {
				throw new IllegalArgumentException ("Composer input is invalid!");
			}
			aOptionalTags.put(Tag.COMPOSER, pComposer);
		} catch(Exception e) {
			e.printStackTrace();
			aOptionalTags.put(Tag.COMPOSER, "unknown");
		}
		
	}
	
	/**
	 * Returns a String containing all optional tags with their values
	 */
	public String toString() {
		return "---Optional tags---\nBPM: "+this.getBPM()+"\nGenre: "+this.getGenre()+"\nComposer: "+this.getComposer();
	}
}
