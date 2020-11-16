package activity3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a musical genre, for example classical or jazz.
 */
public class Genre
{
	private static final List<Genre> genres = new ArrayList<>();

	private final String aName;
	private final List<String> aSynonyms = new ArrayList<>();

	/**
	 * constructs a Genre of the specified name
	 * private constructor, to be used only by Genre.getGenre()
	 * @param pName String representing the name of the genre to be created
	 * @pre pName != null && !pName.equals("")
	 */
	private Genre(String pName) {
		assert pName != null && !pName.equals("");
		this.aName = pName;
		addSynonym(pName);
	}

	/**
	 * Factory method to get a Genre
	 * @param pName String representing the name of Genre
	 * @return the Genre
	 * @pre pName != null && !pName.equals("")
	 */
	public static Genre getGenre(String pName) {
		assert pName != null && !pName.equals("");
		for(Genre a: genres){
			if(a.aSynonyms.contains(pName.toLowerCase())){
				return a;
			}
		}
		Genre newGenre = new Genre(pName);
		genres.add(newGenre);
		return newGenre;
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
	 * gets the name of the genre
	 * @return name of artist
	 */
	public String getName() {
		return this.aName;
	}

	@Override
	public String toString() {
		return this.aName;
	}
}
