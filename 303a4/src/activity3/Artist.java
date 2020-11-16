package activity3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the musician or musical formation that performed the song.
 */
public class Artist {
	private static final List<Artist> artists = new ArrayList<>();

	private final String aName;
	private final List<String> aAlternativeNames = new ArrayList<>();

	/**
	 * constructs an Artist of the specified name private constructor, to be used
	 * only by Artist.getArtist()
	 * 
	 * @param pName String representing the name of the artist to be created
	 * @pre pName != null && !pName.equals("")
	 */
	private Artist(String pName) {
		assert pName != null && !pName.equals("");
		this.aName = pName;
		addAlternativeName(pName);
	}

	/**
	 * Factory method to get the specified Artist
	 * @param pName String representing name of the Artist
	 * @return the Artist
	 * @pre pName != null && !pName.equals("")
	 */
	public static Artist getArtist(String pName) {
		assert pName != null && !pName.equals("");
		for (Artist a : artists) {
			if (a.aAlternativeNames.contains(pName.toLowerCase())) {
				return a;
			}
		}
		Artist newArtist = new Artist(pName);
		artists.add(newArtist);
		return newArtist;
	}

	/**
	 * adds a specified alternative name
	 * 
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
	 * 
	 * @return name of artist
	 */
	public String getName() {
		return this.aName;
	}

	@Override
	public String toString() {
		return this.aName;
	}

	@Override
	public boolean equals(Object o) {
		if (this.aName.equals(((Artist) o).aName))
			return true;

		for (String n : aAlternativeNames) {
			if (n.equals(((Artist) o).aName))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hc = aName.hashCode();

		for (String n : aAlternativeNames) {
			hc += n.hashCode();
		}

		return hc;
	}
}
