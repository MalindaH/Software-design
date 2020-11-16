package activity3;

import java.util.List;

/**
 * the SongCollection will be remixed when played
 */
public class RemixSongCollection implements SongCollection
{
	/**
	 * available types of remix
	 */
	public enum RemixType {
		CLUB, RADIO, DUB, INSTRUMENTAL
	}
	
	private SongCollection aSongCollection;
	private RemixType aRemixType;
	
	/**
	 * Constructs a RemixSongCollection
	 * @param pSongCollection the SongCollection this represents
	 * @param pRemixType the RemixType of this SongCollection
	 * @pre pSongCollection != null
	 */
	public RemixSongCollection(SongCollection pSongCollection, RemixType pRemixType) {
		assert pSongCollection != null;
		aSongCollection = pSongCollection;
		aRemixType = pRemixType;
	}
	
	/**
	 * Sets the remix type
	 * @param pRemixType The RemixType to set to
	 */
	public void setRemixType(RemixType pRemixType) {
		aRemixType = pRemixType;
	}
	
	/**
	 * Gets the remix type
	 * @return the RemixType of this SongCollection
	 */
	public RemixType getRemixType() {
		return aRemixType;
	}

	@Override
	public String description()
	{
		return aRemixType+" REMIX:" + aSongCollection.description();
	}

	@Override
	public int getRuntime()
	{
		if(aRemixType == RemixType.CLUB) {
			return (int) (aSongCollection.getRuntime()*0.8);
		}
		if(aRemixType == RemixType.RADIO) {
			return (int) (aSongCollection.getRuntime()*1.2);
		}
		return aSongCollection.getRuntime();
	}

	@Override
	public String getTitle()
	{
		return aSongCollection.getTitle();
	}

	@Override
	public List<SongCollection> getContents()
	{
		return aSongCollection.getContents();
	}
	
	@Override
	public RemixSongCollection clone() {
		try {
			RemixSongCollection clone=(RemixSongCollection) super.clone();
			clone.aSongCollection = this.aSongCollection.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			assert false;
			return null;
		}
	}
	
	/**
	 * @pre pSongCollection != null
	 */
	@Override
	public boolean contains(SongCollection pSongCollection) {
		assert pSongCollection != null;
		return aSongCollection.contains(pSongCollection);
	}

	/** 
	 * @pre pObject != null
	 */
	@Override
	public boolean equals(Object pObject) {
		assert pObject != null;
		
		if (this == pObject)
			return true;

		if (pObject.getClass() != this.getClass())
			return false;

		return aSongCollection.equals(((RemixSongCollection) pObject).aSongCollection)
				&& aRemixType == ((RemixSongCollection) pObject).aRemixType;
	}
}
