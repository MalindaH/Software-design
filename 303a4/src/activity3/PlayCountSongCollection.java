package activity3;

import java.util.List;

/**
 * count the number of times the SongCollection is played
 */

public class PlayCountSongCollection implements SongCollection {
	private SongCollection aSongCollection;
	private int aPlayCount;

	/**
	 * Constructs a PlayCountSongCollection
	 * @param pSongCollection the SongCollection that it represents
	 * @pre pSongCollection != null
	 */
	public PlayCountSongCollection(SongCollection pSongCollection) {
		assert pSongCollection != null;
		aSongCollection = pSongCollection;
		aPlayCount = 0;
	}

	/**
	 * Gets the play count
	 * @return the number of times the SongCollection is played
	 */
	public int getPlayCount() {
		return aPlayCount;
	}

	@Override
	public int getRuntime() {
		return aSongCollection.getRuntime();
	}

	@Override
	public String getTitle() {
		return aSongCollection.getTitle();
	}

	@Override
	public List<SongCollection> getContents() {
		return aSongCollection.getContents();
	}

	@Override
	public String description()
	{
		// other composite classes that contain this object calls description() when play(), so 
		// increment aPlayCount here
		aPlayCount++;
		return aSongCollection.description();
	}

	@Override
	public PlayCountSongCollection clone() {
		try {
			PlayCountSongCollection clone = (PlayCountSongCollection) super.clone();
			clone.aSongCollection = this.aSongCollection.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			assert false;
			return null;
		}

	}	

	/**
	 * @pre pObject != null
	 */
	@Override
	public boolean equals(Object pObject) {
		assert pObject != null;
		if (this == pObject) return true;
		if (pObject.getClass() != this.getClass()) return false;

		return this.aPlayCount == ((PlayCountSongCollection) pObject).aPlayCount && 
				this.aSongCollection.equals(((PlayCountSongCollection)pObject).aSongCollection);
	}
	
	/**
	 * @pre pSongCollection != null
	 */
	@Override
	public boolean contains(SongCollection pSongCollection) {
		assert pSongCollection != null;
		return aSongCollection.contains(pSongCollection);
	}

}
