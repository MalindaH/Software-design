package activity3;

import java.util.*;

/**
 * Represents an album with a title (mandatory) and artist (can be absent). An
 * album can be incomplete, in the sense that some tracks can be missing.
 */
public class Album implements SongCollection {
	private String aTitle;
	private Optional<Artist> aArtist;
	private Map<Integer, Song> aTracks = new HashMap<>();

	/**
	 * Constructs an Album of the input name
	 * @param pTitle String representing name of the Album
	 * @pre pTitle != null && !pTitle.equals("")
	 */
	public Album(String pTitle) {
		assert pTitle != null && !pTitle.equals("");
		aTitle = pTitle;
		aArtist = Optional.empty();
	}

	/**
	 * Add a track to this album. If the track already exists, it is written over.
	 * 
	 * @param pNumber The track number. Must be greater than 0.
	 * @param pSong   The song for this track.
	 * @pre pNumber > 0 && pSong != null
	 */
	public void addTrack(int pNumber, Song pSong) {
		assert pNumber > 0 && pSong != null;
		aTracks.put(pNumber, pSong);
	}

	/**
	 * Returns run time of this Album
	 */
	@Override
	public int getRuntime() {
		int runtime = 0;
		for (Song s : this.aTracks.values()) {
			runtime += s.getRuntime();
		}
		return runtime;
	}

	/**
	 * Gets the title of this Album
	 */
	@Override
	public String getTitle() {
		return this.aTitle;
	}

	/**
	 * Gets the Artist of this Album
	 * @return the Artist of this Album
	 */
	public Artist getArtist() {
		if (aArtist.isPresent())
			return aArtist.get();
		else
			return null;
	}

	/**
	 * Sets the Artist
	 * @param pArtist the Artist to be set to
	 */
	public void setArtist(Artist pArtist) {
		aArtist = (pArtist == null) ? Optional.empty() : Optional.ofNullable(pArtist);
	}

	/**
	 * Gets all contents of this Album
	 * @return a List<SongCollection> of clones of contents that this Album contains
	 */
	@Override
	public List<SongCollection> getContents() {
		ArrayList<SongCollection> listOfSongs = new ArrayList<>();
		for(Song s: this.aTracks.values()) {
			listOfSongs.add(s.clone());
		}
		return listOfSongs;
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
		Album pAlbum = (Album) pObject;
		if (!pAlbum.aArtist.equals(this.aArtist))
			return false;
		if (!pAlbum.aTitle.equals(this.aTitle))
			return false;
		if (!this.getContents().equals(pAlbum.getContents()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.aTitle, this.aArtist, this.aTracks);
	}

	public void clearContent() {
		this.aTracks.clear();
	}

	public Album clone() {
		try {
			Album clone = (Album) super.clone();
			clone.aTracks = new HashMap<>();
			for (AbstractMap.Entry<Integer, Song> entry : aTracks.entrySet()) {
				Integer idx = entry.getKey();
				Song song = entry.getValue();
				clone.addTrack(idx, song);
			}
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			assert false;
			return null;
		}
	}

	@Override
	public String description() {
		StringBuilder des = new StringBuilder("---Now playing: Album " + this.getTitle() + "---\n");
		for (Song cur : aTracks.values()) {
			des.append("\t" + cur.description() + "\n");
		}
		return des.toString();
	}

	/**
	 * @return boolean of whether this Album contains the input SongCollection
	 * @pre pSongCollection != null
	 */
	@Override
	public boolean contains(SongCollection pSongCollection) {
		assert pSongCollection != null;
		for (Song s:aTracks.values() )
			if (pSongCollection.equals(s))
				return true;
		return false;
	}
}
