package activity3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a musical genre, for example classical or jazz.
 */
public class Genre implements SongInfo {
	// All stored in lowercase
	private final List<String> aSynonyms = new ArrayList<>();
	private final String aName;

	/**
	 * constructs a Genre of the input name
	 * protected constructor, to be used only in SongInfoFactory.getGenre()
	 * @param pName String representing the name of the genre to be created
	 * @pre pName != null && !pName.equals("")
	 */
	protected Genre(String pName) {
		assert pName != null && !pName.equals("");
		this.aName = pName;
		addSynonym(pName.toLowerCase());
	}

	/**
	 * adds the specified name as a synonym
	 * @param pName String representing the input name
	 * @pre pName != null && !pName.equals("")
	 */
	public void addSynonym(String pName) {
		assert pName != null && !pName.equals("");
		if (!this.aSynonyms.contains(pName.toLowerCase())) {
			this.aSynonyms.add(pName.toLowerCase());
		}
	}

	/**
	 * gets name of this Genre
	 * @return gets name of genre
	 */
	public String getName() {
		return this.aName;
	}
	
	/**
	 * gets List<String> of synonyms of this Genre
	 * @return list of synonyms for this genre names 
	 */
	public List<String> getSynonyms()
	{
		return new ArrayList<String>(aSynonyms);
	}

	/**
	 * checks whether the input Genre equals this Genre
	 * overload instead of overwrite so that we don't need to implement hashCode()
	 * @param pGenre the SongInfo to be checked
	 * @return true if this Genre has alternative name same as the name of the input Genre
	 */
	public boolean equals(SongInfo pGenre){
		return pGenre instanceof Genre && this.aSynonyms.contains(((Genre)pGenre).aName.toLowerCase());
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String toString() {
		return this.aName;
	}

	/**
	 * checks whether this Genre's alternative names contain the input String
	 * @param String representing the name to be checked
	 * @return true if this Genre's alternative names contain the input String
	 * @pre pName != null && !pName.equals("")
	 */
	@Override
	public boolean nameEquals(String pName)
	{
		assert pName != null && !pName.equals("");
		return this.aSynonyms.contains(pName.toLowerCase());
	}
}
