package music.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import music.Time;

/**
 * MetadataTags include expected, optional, and custom tags
 */
public class MetadataTags {
	// Different types of expected and optional tags are in TagType enum
	private HashMap<TagType, Object> aExpectedAndOptionalTags = new HashMap<TagType, Object>();
	private HashMap<CustomTag, Object> aCustomTags = new HashMap<CustomTag, Object>();
	// Note: expected/optional tags are called by `TagType.GENRE`;
	// custom tags are called by `CustomTag.get("name")`

	/**
	 * Constructs a new ExpectedTags with input title, time, and artist
	 * @param pTitle a String representing the title of the song
	 * @param pTime a Time object representing the length of the song
	 * @param pArtist a String representing the artist of the song
	 * @pre pTitle != null && pTime != null && pArtist != null
	 */
	public MetadataTags(String pTitle, Time pTime, String pArtist) {
		// check of input not null is included in set method
		this.set(TagType.TITLE, pTitle);
		this.set(TagType.TIME, pTime);
		this.set(TagType.ARTIST, pArtist);
	}

	/**
	 * Gets the value of the specified custom tag
	 * @param the CustomTag to get the value
	 * @return value of the input tag, return null if no such tag exists
	 */
	public Object get(Tag pTag) {
		if(pTag instanceof TagType) {
			// don't need to return a copy of it because by default objects like String, Time are immutable
			return aExpectedAndOptionalTags.get(pTag);
		}
		if(pTag instanceof CustomTag) {
			return aCustomTags.get(pTag);
		}
		return null;
	}
	
	/**
	 * Sets the custom tag using the input value (public method so that tags can be changed)
	 * @param pTag a String representing the title
	 * @return a boolean indicating success
	 */
	public boolean set(Tag pTag, Object pValue) {
		try {
			if(pTag == null) {
				throw new IllegalArgumentException("Input tag is invalid!");
			}
			if(pValue == null || pValue.equals("")) {
				throw new IllegalArgumentException ("Value input is invalid!");
			}
			if(pTag instanceof CustomTag) {
				Object prev = aCustomTags.put((CustomTag)pTag, pValue);
				// check if the same tag name exists already
				if(prev != null) {
					Scanner scanner = new Scanner(System.in);
					System.out.println("A tag called "+pTag+" already exists. Do you want to overwrite it? (y/n)");
					if(scanner.nextLine().charAt(0) == 'y') {
						System.out.println("Tag "+pTag+" overwritten.");
					} else {
						aCustomTags.put((CustomTag)pTag, prev);
						System.out.println("Tag "+pTag+" not overwritten.");
					}
					scanner.close();
				}
				return true;
			}
			if(pTag instanceof TagType) {
				if( pTag.equals(TagType.TITLE) || pTag.equals(TagType.ARTIST) ||
						pTag.equals(TagType.GENRE) || pTag.equals(TagType.COMPOSER)) {
					if(pValue == null || pValue.equals("")) {
						throw new IllegalArgumentException("Value input is invalid!");
					}
					aExpectedAndOptionalTags.put((TagType)pTag, pValue);
				} else if (pTag.equals(TagType.TIME)) {
					if(((Time) pValue).toSeconds() < 0) { // invalid Time.toSeconds() returns -1
						throw new IllegalArgumentException("Time input is invalid!");
					}
					aExpectedAndOptionalTags.put((TagType)pTag, pValue);
				} else if (pTag.equals(TagType.BPM)) {
					if((Integer) pValue <= 0) {
						throw new IllegalArgumentException ("BPM has to be a positive integer!");
					}
					aExpectedAndOptionalTags.put((TagType)pTag, pValue);
				} 
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Adds a custom tag of input tag name and value
	 * @param pTagName a String representing the tag name
	 * @param pValue an Object representing the tag value (i.e. any type)
	 * @return a boolean indicating success
	 */
	public boolean addCustomTag(String pTagName, Object pValue) {
		try {
			// check the inputs are valid
			if(pTagName == null || pValue == null || pTagName.equals("") || pValue.equals("")) {
				throw new IllegalArgumentException ("Input custom tag or value is invalid! Failed to add the tag.");
			}
			CustomTag tag = CustomTag.get(pTagName);
			this.set(tag, pValue);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Returns a string of all expected tags and their values
	 */
	public String getExpectedTags() {
		return "---Expected tags---\nTitle: "+this.get(TagType.TITLE)+"\nTime: "+this.get(TagType.TIME)+"\nArtist: "+this.get(TagType.ARTIST);
	}
	
	/**
	 * Gets expected tags and their values as an ArrayList
	 * @return an ArrayList<String> containing 3 expected tags
	 */
	public ArrayList<String> getExpectedTagsArray(){
		ArrayList<String> output = new ArrayList<String>();
		output.add("Title: "+this.get(TagType.TITLE));
		output.add("Time: "+this.get(TagType.TIME));
		output.add("Artist: "+this.get(TagType.ARTIST));
		return output;
	}

	/**
	 * Returns a String containing all optional tags with their values
	 */
	public String getOptionalTags() {
		return "---Optional tags---\nBPM: "+this.get(TagType.BPM)+"\nGenre: "+this.get(TagType.GENRE)+"\nComposer: "+this.get(TagType.COMPOSER);
	}
	
	/**
	 * Gets optional tags and their values as an ArrayList
	 * @return an ArrayList<String> containing 3 optional tags
	 */
	public ArrayList<String> getOptionalTagsArray(){
		ArrayList<String> output = new ArrayList<String>();
		output.add("BPM: "+this.get(TagType.BPM));
		output.add("Genre: "+this.get(TagType.GENRE));
		output.add("Composer: "+this.get(TagType.COMPOSER));
		return output;
	}

	/**
	 * Returns a String containing all custom tags and their values
	 */
	public String getCustomTags() {
		String output = "";
		for(CustomTag tag: aCustomTags.keySet()) {
			output = output.concat(tag.toString()+": ");
			output = output.concat(this.get(tag).toString()+"\n");
		}
		return "---Custom tags---\n"+output;
	}
	
	/**
	 * Gets custom tags and their values as an ArrayList
	 * @return an ArrayList<String> containing all custom tags
	 */
	public ArrayList<String> getCustomTagsArray(){
		ArrayList<String> output = new ArrayList<String>();
		for(CustomTag tag: aCustomTags.keySet()) {
			output.add(tag.toString()+": "+this.get(tag).toString());
		}
		return output;
	}

	/**
	 * Returns a String containing all tags and their values
	 */
	public String toString() {
		return this.getExpectedTags()+"\n"+this.getOptionalTags()+"\n"+this.getCustomTags();
	}

}
