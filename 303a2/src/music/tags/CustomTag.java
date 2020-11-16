package music.tags;

import java.util.ArrayList;
import java.util.List;

/**
 * Note: expected/optional tags are called by `TagType.GENRE`;
 * custom tags are called by `CustomTag.get("name")`
 */
public class CustomTag implements Tag {
	private static List<CustomTag> otherTags = new ArrayList<>();

	private String aName; // name is stored as uppercase

	/**
	 * Private constructor, only called from get()
	 * @param pName
	 * @pre pName != null
	 */
	private CustomTag(String pName) {
		assert pName != null;
		aName = pName;
	}

	/**
	 * Gets the tag of the specified name
	 * @param pName name of the tag
	 */
	public static CustomTag get(String pName) {
		try {
			if(pName == "" || pName == null) {
				throw new IllegalArgumentException("Tag name is invalid.");
			}
			if(pName.toLowerCase().equals("title") || pName.toLowerCase().equals("time") 
					|| pName.toLowerCase().equals("artist") || pName.toLowerCase().equals("bpm")
					|| pName.toLowerCase().equals("genre") || pName.toLowerCase().equals("composer")) {
				throw new IllegalArgumentException("Tag name is invalid, it's an expected / optional tag.");
			}
			String pNameCap = pName.toUpperCase();
			// check for replicates
			for(CustomTag tag: otherTags) {
				if(tag.aName.equals(pNameCap)) {
					return tag;
				}
			}
			CustomTag cur = new CustomTag(pNameCap);
			otherTags.add(cur);
			return cur;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Returns a String of this tag's name
	 */
	public String toString() {
		return this.aName;
	}
}
