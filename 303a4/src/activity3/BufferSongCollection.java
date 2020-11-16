package activity3;

import java.util.List;

/**
 * add a parameterizable amount of silence (buffering) before and after the
 * SongCollection
 */
public class BufferSongCollection implements SongCollection {
	private SongCollection aSongCollection;
	private int aBuffer;

	/**
	 * Constructs a BufferSongCollection
	 * @param pSongCollection the SongCollection that this contains
	 * @param pBuffer int representing buffering time
	 * @pre pSongCollection != null
	 */
	public BufferSongCollection(SongCollection pSongCollection, int pBuffer) {
		assert pSongCollection != null;
		aSongCollection = pSongCollection;
		aBuffer = pBuffer;
	}

	/**
	 * sets the buffering time
	 * 
	 * @param pBuffer int representing buffering time
	 */
	public void setBuffer(int pBuffer) {
		aBuffer = pBuffer;
	}

	/**
	 * Gets the buffering time
	 * @return int representing buffering time
	 */
	public int getBuffer() {
		return aBuffer;
	}

	/**
	 * Gets run time of this BufferSongCollection
	 * (buffering silence added before and after the SongCollection)
	 */
	@Override
	public int getRuntime() {
		return aSongCollection.getRuntime() + aBuffer * 2;
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
	public String description() {
		return "...buffer for " + aBuffer + " seconds..." + aSongCollection.description() + "...buffer for " + aBuffer
				+ " seconds...";
	}

	@Override
	public BufferSongCollection clone() {
		try {
			BufferSongCollection clone = (BufferSongCollection) super.clone();
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

		return this.aBuffer == ((BufferSongCollection) pObject).aBuffer && 
				this.aSongCollection.equals(((BufferSongCollection)pObject).aSongCollection);
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
