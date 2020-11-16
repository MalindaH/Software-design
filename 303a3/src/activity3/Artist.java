package activity3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the musician or musical formation
 * that performed the song.
 */
public class Artist implements SongInfo
{
	// All stored in lowercase
	private final List<String> aAlternativeNames = new ArrayList<>();
	private final String aName;

	/**
	 * constructs an Artist of the specified name
	 * protected constructor, to be used only in SongInfoFactory.getArtist()
	 * @param pName String representing the name of the artist to be created
	 * @pre pName != null
	 */
	protected Artist(String pName) {
		assert pName != null;
		this.aName = pName;
		addAlternativeName(pName.toLowerCase());
	}

	/**
	 * adds a specified alternative name
	 * @param pName String representing the name to be added
	 * @pre pName != null
	 */
	public void addAlternativeName(String pName) {
		assert pName != null;
		if (!this.aAlternativeNames.contains(pName.toLowerCase())) {
			this.aAlternativeNames.add(pName.toLowerCase());
		}
	}
	
	/**
	 * gets the name of the artist
	 * @return name of artist
	 */
	public String getName() {
		return this.aName;
	}

	/**
	 * checks whether this Artist equals the input Artist
	 * overload instead of overwrite so that we don't need to implement hashCode()
	 * @param pArtist the SongInfo to be compared to
	 * @return true if this Artist has alternative name same as the name of the input Artist
	 */
	public boolean equals(SongInfo pArtist){
		return pArtist instanceof Artist && this.aAlternativeNames.contains(((Artist)pArtist).aName.toLowerCase());
	}

	/**
	 * checks whether this Artist's alternative names contain the input String
	 * @param String representing the name to be checked
	 * @return true if this Artist's alternative names contain the input String
	 * @pre pName != null && !pName.equals("")
	 */
	@Override
	public boolean nameEquals(String pName){
		assert pName != null && !pName.equals("");
		return this.aAlternativeNames.contains(pName.toLowerCase());
	}
	
	/**
	 * gets a List<String> of alternative names
	 * @return list of alternative names for this artist
	 */
	public List<String> getAlternativeNames()
	{
		return new ArrayList<String>(aAlternativeNames);
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String toString() {
		return this.aName;
	}
}
