package music;

import java.util.HashMap;

/**
 * ExpectedTags of a song include mandatory keys: title, time, artist
 */
public class ExpectedTags {
	private enum Tag {
		TITLE, TIME, ARTIST;
	}
	
	private HashMap<Tag, Object> aExpectedTags;
	
	/**
	 * Constructs a new ExpectedTags with input title, time, and artist
	 * @param pTitle a String representing the title of the song
	 * @param pTime a Time object representing the length of the song
	 * @param pArtist a String representing the artist of the song
	 * @pre pTitle != null && pTime != null && pArtist != null
	 */
	public ExpectedTags(String pTitle, Time pTime, String pArtist) {
		aExpectedTags = new HashMap();
		// check of input not null included in these methods
		this.setTitle(pTitle);
		this.setTime(pTime);
		this.setArtist(pArtist);
	}
	
	/**
	 * Gets the title
	 * @return a String representing the title
	 */
	public String getTitle() {
		return (String) aExpectedTags.get(Tag.TITLE);
	}
	
	/**
	 * Gets the time value
	 * @return a Time object representing the time
	 */
	public Time getTime() {
		// TIME might be null if time input of setTime() was invalid
		Object temp = aExpectedTags.get(Tag.TIME);
		if(temp == null) {
			return null;
		}
		return (Time) aExpectedTags.get(Tag.TIME);
	}
	
	/**
	 * Gets the artist
	 * @return a String representing the artist
	 */
	public String getArtist() {
		return (String) aExpectedTags.get(Tag.ARTIST);
	}
	
	/**
	 * Sets the title
	 * @param pTitle a String representing the title
	 * @pre pTitle != null
	 * public so that the tag can be changed
	 */
	public void setTitle(String pTitle) {
		try {
			if(pTitle == null) {
				throw new IllegalArgumentException("Title input is invalid!");
			}
			aExpectedTags.put(Tag.TITLE, pTitle);
		} catch (Exception e) {
			e.printStackTrace();
			aExpectedTags.put(Tag.TITLE, "unknown");
		}
	}
	
	/**
	 * Sets the time
	 * @param pTime a Time object representing the time
	 * @pre pTime.getHour() >= 0 (i.e. Time is valid, not -1:-1:-1 after checking within Time class, hence here we just check pTime.getHour())
	 * public so that the tag can be changed
	 */
	public void setTime(Time pTime) {
		try {
			if(pTime.getHour() < 0) {
				throw new IllegalArgumentException("Time input is invalid!");
			}
			aExpectedTags.put(Tag.TIME, pTime);
		} catch (Exception e) {
			e.printStackTrace();
			aExpectedTags.put(Tag.TIME, null);
		}
	}
	
	/**
	 * Sets the artist
	 * @param pArtist a String representing the artist
	 * @pre pArtist != null
	 * public so that the tag can be changed
	 */
	public void setArtist(String pArtist) {
		try {
			if(pArtist == null) {
				throw new IllegalArgumentException("Artist input is invalid!");
			}
			aExpectedTags.put(Tag.ARTIST, pArtist);
		} catch (Exception e) {
			e.printStackTrace();
			aExpectedTags.put(Tag.ARTIST, "unknown");
		}
	}
	
	/**
	 * Returns a string of all expected tags and their values
	 */
	public String toString() {
		if(this.getTime() == null) {
			return "---Expected tags---\nTitle: "+this.getTitle()+"\nTime: unknown"+"\nArtist: "+this.getArtist();
		}
		return "---Expected tags---\nTitle: "+this.getTitle()+"\nTime: "+this.getTime()+"\nArtist: "+this.getArtist();
	}
}
