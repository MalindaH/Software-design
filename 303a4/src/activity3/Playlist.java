package activity3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a named list of songs.
 */
public class Playlist implements SongCollection {
	private String aTitle;
	private List<SongCollection> aSongCollections;

	/**
	 * Constructs a Playlist of the input title
	 * @param pTitle String representing the title
	 */
	public Playlist(String pTitle) {
		aTitle = pTitle;
		aSongCollections = new ArrayList<>();
	}

	/**
	 * Constructs a composite Playlist
	 * @param pTitle String representing the title
	 * @param pSongCollections Any number of SongCollection to add to this Playlist
	 */
	public Playlist(String pTitle, SongCollection... pSongCollections) {
		aTitle = pTitle;
		aSongCollections = new ArrayList<SongCollection>(Arrays.asList(pSongCollections));
	}

	/**
	 * Sets the title
	 * @param pTitle String representing the title
	 */
	public void setTitle(String pTitle) {
		aTitle = pTitle;
	}

	/**
	 * Gets the title
	 */
	public String getTitle() {
		return aTitle;
	}

	/**
	 * Adds the input SongCollection without allowing duplicates
	 * @param pSongCollection the SongCollection to be added
	 * @return the added SongCollection
	 * @pre pSongCollection != null && !pSongCollection.equals(this) && !pSongCollection.contains(this)
	 */
	public SongCollection addUnique(SongCollection pSongCollection) {
		// don't allow to add itself to avoid infinite loops
		assert pSongCollection != null && !pSongCollection.equals(this) && !pSongCollection.contains(this);
		
		for (SongCollection sc : aSongCollections)
		{
			if (sc.equals(pSongCollection)) {
				return sc;
			}
		}
		aSongCollections.add(pSongCollection);
		return pSongCollection;
	}

	/**
	 * Adds the input SongCollection to this Playlist
	 * @param pSongCollection the SongCollection to be added
	 * @pre pSongCollection != null && !pSongCollection.equals(this) && !pSongCollection.contains(this)
	 */
	public void add(SongCollection pSongCollection) {
		// don't allow to add itself to avoid infinite loops
		assert pSongCollection != null && !pSongCollection.equals(this) && !pSongCollection.contains(this);
		aSongCollections.add(pSongCollection);
	}

	/**
	 * removes a part of the playlist
	 * 
	 * @param pSongColletion the part to remove
	 * @pre pSongColletion != null
	 */
	public void remove(SongCollection pSongColletion) {
		assert pSongColletion != null;
		aSongCollections.remove(pSongColletion);
	}

	/**
	 * removes everything from the playlist
	 */
	public void clearContent() {
		aSongCollections.clear();
	}

	public void backup() {
		System.out.println("Backing up the library");
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.aTitle, this.aSongCollections);
	}

	/**
	 * This method returns true iff the elements of this are equal and in the same
	 * order to that of pObject, and the titles are equal.
	 * 
	 * @param pObject the object to be compared with this
	 * @return true if these conditions are met.
	 */
	@Override
	public boolean equals(Object pObject) {
		if (this == pObject)
			return true;
		if ((pObject.getClass() != this.getClass()) || (!this.aTitle.equals(((Playlist) pObject).getTitle())))
			return false;
		List<SongCollection> pElements = ((Playlist) pObject).getContents();
		if (this.aSongCollections.size() != pElements.size())
			return false;

		for (int i = 0; i < this.aSongCollections.size(); i++) {
			if (!pElements.get(i).equals(this.aSongCollections.get(i)))
				return false;
		}
		return true;
	}

	/**
	 * This method returns true iff the elements of this are equal to that of
	 * pObject. The elements can be in any order. Note that this method will not
	 * account for different numbers of the same entry. For example this method
	 * would have: {SongCollection1, SongCollection2,
	 * SongCollection2}.contentEquals({SongCollection1, SongCollection2}) == true
	 * 
	 * @param pObject the object to be compared with this
	 * @return true if these conditions are met.
	 */
	public boolean contentEquals(Object pObject) {
		if (this == pObject)
			return true;
		if (!(pObject instanceof Playlist))
			return false;
		List<SongCollection> pElements = ((Playlist) pObject).getContents();

		for (SongCollection s : this.aSongCollections) {
			if (!pElements.contains(s))
				return false;
		}

		return true;
	}

	@Override
	public List<SongCollection> getContents() {
		ArrayList<SongCollection> listOfSongs = new ArrayList<>();
		for(SongCollection s: this.aSongCollections) {
			listOfSongs.add(s.clone());
		}
		return listOfSongs;
	}

	@Override
	public int getRuntime() {
		int runtime = 0;
		for (SongCollection s : this.aSongCollections) {
			runtime += s.getRuntime();
		}
		return runtime;
	}
	
	/**
	 * returns a copy of the playlist
	 */
	@Override
	public Playlist clone() {
		try {
			Playlist clone = (Playlist) super.clone();
			clone.aSongCollections = new ArrayList<>();
			for (SongCollection s : this.aSongCollections)
				clone.add(s.clone());
			return clone;
		} catch (CloneNotSupportedException e) {
			assert false;
			return null;
		}
	}

	@Override
	public String description() {
		StringBuilder des = new StringBuilder("---Now playing: Playlist " + this.getTitle() + "---");
		for (SongCollection cur : aSongCollections) {
			des.append("\n\t" + cur.description());
		}
		return des.toString();
	}

	/**
	 * @pre pSongCollection != null
	 */
	@Override
	public boolean contains(SongCollection pSongCollection) {
		assert pSongCollection != null;
		for (SongCollection s : aSongCollections)
			if (s.contains(pSongCollection) || s.equals(pSongCollection)) return true;
		return false;
	}
}
