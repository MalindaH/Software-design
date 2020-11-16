package music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import music.tags.*;

/**
 * a Playlist contains Songs and its name
 */
public class Playlist implements SongList, Iterable<Song>, Shufflable {
	private String aName;
	private List<Song> aSongs = new ArrayList<>();
	private List<Song> aUnshuffledSongs = new ArrayList<>();
	private boolean favorite = false;
	private boolean isShuffled = false;

	/**
	 * Construct a playlist using its name
	 * @param pName, a String representing the name of the playlist
	 * @pre pName != null && !pName.equals("")
	 */
	public Playlist (String pName) {
		assert pName != null && !pName.equals("");
			this.aName = pName;
	}

	/**
	 * Construct a playlist using its name and an ArrayList of songs
	 * @param pName, a String representing the name of the playlist
	 * @param pSongs, an Arraylist of songs representing all the songs in the playlist
	 * @pre pName != null && !pName.equals("") && pSongs != null
	 */
	public Playlist(String pName, ArrayList<Song> pSongs) {
		assert pName != null && !pName.equals("") && pSongs != null;
		this.aName = pName;
		this.aSongs = new ArrayList<>(pSongs);
	}

	/**
	 * Removes the specified Song from this Playlist
	 * @param pSong the Song to be removed
	 * @return the removed Song, return null if the song was not originally in the playlist
	 * @pre pSong != null && pSong.isValid()
	 */
	@Override
	public Song removeSong(Song pSong) {
		assert pSong != null && pSong.isValid();
		if(aSongs.remove(pSong)) {
			return pSong;
		}
		return null;

	}

	/**
	 * Removes the specified list of songs from this Playlist.
	 * @param pSongs the ArrayList of Songs to be removed
	 * @pre pSongs != null
	 */
	@Override
	public void removeSongs(ArrayList<Song> pSongs) {
		assert pSongs != null;
		for (Song cur : pSongs) {
			this.removeSong(cur);
		}
	}

	/**
	 * Removes all invalid songs from this playlist
	 */
	public void removeInvalidSongs() {
		aSongs.removeIf(song -> song.isValid());
	}

	/**
	 * Change the name of the playlist
	 * @param pNewName, a String representing the new name
	 * @pre pNewName != null && !pNewName.equals("")
	 */
	public void changeName(String pNewName) {
		try {
			assert pNewName != null && !pNewName.equals("");
			if (pNewName.equals(aName)) {
				throw new IllegalArgumentException("Invalid input for NewName. New name is the same as the old name.");
			}
			this.aName = pNewName;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Get the name of a playlist
	 * @return a String representing the name of the playlist
	 */
	public String getName() {
		return aName;
	}

	/**
	 * Get the songs in the playlist
	 * @return an ArrayList<Song> of songs
	 */
	@Override
	public ArrayList<Song> getAllSongs() {
		return new ArrayList<>(aSongs);
	}
	
	/**
	 * Gets a song/songs based on the tag value provided.
	 * @return the songs, return null if no songs exist for specified tag
	 * @pre aTag != null && !aTag.equals("")
	 */
	public ArrayList<Song> findSongsByTag(String aTag) {
		assert aTag != null && !aTag.equals("");
		ArrayList<Song> songs = new ArrayList<Song>();		
		for (int i = 0; i <= aSongs.size()-1; i++) {
			if (aSongs.get(i).getTitleOfSong().equals(aTag) || aSongs.get(i).getArtistOfSong().equals(aTag) ||
					aSongs.get(i).getBPMOfSong().equals(aTag) || aSongs.get(i).getGenreOfSong().equals(aTag) ||
					aSongs.get(i).getComposerOfSong().equals(aTag)) {
				songs.add(aSongs.get(i));
			}
		} 
		if (songs.size() > 0 ) {
			return songs;
		} else {
			System.out.println("Song not found, please provide a valid Tag");
			return null; 
		}
	}
	
	/**
	 * Gets a song based on the title provided
	 *
	 * @return the song, return null if song is not exists
	 * @pre pTitle != null && !pTitle.equals("")
	 */
	public ArrayList<Song> findSongByTitle(String pTitle) {
		assert pTitle != null && !pTitle.equals("");
		return this.findSongsByTag(pTitle);
	}
	
	/**
	 * Gets a song based on the artist provided
	 *
	 * @return the song, return null if song is not exists
	 * @pre pArtist != null && !pArtist.equals("")
	 */
	public ArrayList<Song> findSongByArtist(String pArtist) {
		assert pArtist != null && !pArtist.equals("");
		return this.findSongsByTag(pArtist);
	}
	
	/**
	 * Gets a song based on the BPM provided
	 *
	 * @return the song, return null if song is not exists
	 * @pre pBPM != null & !pBPM.equals("")
	 */
	public ArrayList<Song> findSongByBPM(String pBPM) {
		assert pBPM != null & !pBPM.equals("");
		return this.findSongsByTag(pBPM);
	}
	
	/**
	 * Gets a song based on the genre provided
	 *
	 * @return the song, return null if song is not exists
	 * @pre pGenre != null && !pGenre.equals("")
	 */
	public ArrayList<Song> findSongByGenre(String pGenre) {
		assert pGenre != null && !pGenre.equals("");
		return this.findSongsByTag(pGenre);
	}
	
	/**
	 * Gets a song based on the composer provided
	 *
	 * @return the song, return null if song is not exists
	 * @pre pComposer != null && !pComposer.equals("")
	 */
	public ArrayList<Song> findSongByComposer(String pComposer) {
		assert pComposer != null && !pComposer.equals("");
		return this.findSongsByTag(pComposer);
	}
	
	
	/**
	 * Gets the first song in this Playlist. Return null if the playlist is empty.
	 * @return the first Song in this Playlist
	 */
	@Override
	public Song getFirstSong() {

		if(aSongs.size() == 0) {
			System.err.println("Playlist"  + aName + "is currently empty.");
			return null;
		}
		return aSongs.get(0);
	}

	/**
	 * Add a Song to the Playlist
	 * @param pSong, the song being added
	 * @pre pSong != null && pSong.isValid()
	 */
	@Override
	public void addSong(Song pSong) {
		assert pSong != null && pSong.isValid();
		// check whether the song is already in the playlist
		boolean exists = false;
		for(Song cur: aSongs) {
			if(cur == pSong) {
				System.err.println(pSong + " is already in Playlist "+aName+", failed to add this song.");
				exists = true;
			}
		}
		if(!exists) {
			aSongs.add(pSong);
		}
	}


	/**
	 * Add an ArrayList of Songs to the Playlist
	 * @param pSongs, an ArrayList of songs
	 * @pre pSongs != null
	 */
	@Override
	public void addSongs(ArrayList<Song> pSongs) {
		assert pSongs != null;
		for(Song song: pSongs) {
			this.addSong(song);
		}
	}

	/**
	 * Get the number of songs in the playlist
	 * @return an int representing the number of songs
	 */
	@Override
	public int numOfSongs() {
		return aSongs.size();
	}

	/**
	 * Sorts songs alphabetically in playlist by title
	 * @return void
	 */
	public void sortByTitle() {
		Collections.sort(aSongs, new Comparator<Song>() {
			@Override
			public int compare(Song o1, Song o2) {
				if(o1.getTitleOfSong()!=null && o2.getTitleOfSong()!=null) {
					return o1.getTitleOfSong().compareTo(o2.getTitleOfSong());
				}
				return 0;
			};
		});
	}

	/**
	 * Sorts songs alphabetically in playlist by artist
	 */
	public void sortByArtist() {
		Collections.sort(aSongs, new Comparator<Song>() {
			@Override
			public int compare(Song o1, Song o2) {
				if(o1.getArtistOfSong()!=null && o2.getArtistOfSong()!=null) {
					return o1.getArtistOfSong().compareTo(o2.getArtistOfSong());
				}
				return 0;
			};
		});
	}

	/**
	 * Sorts songs in ascending order of time
	 */
	public void sortByTime() {
		Collections.sort(aSongs, new Comparator<Song>() {
			@Override
			public int compare(Song o1, Song o2) {
				if(o1.getPlayingTime()!=null && o2.getPlayingTime()!=null) {
					return o1.getPlayingTime().compareTo(o2.getPlayingTime());
				}
				return 0;
			};
		});
	}

	/**
	 * Get the total playing time of songs in the playlist
	 * @return a Time representing total playing time
	 */
	public Time totalPlayingTime() {
		int second = 0;
		for(Song song : aSongs) {
			second += song.getPlayingTime().toSeconds();
		}
		return new Time(second);
	}

	/**
	 * Sorts songs by the specified tag, in ascending order by default
	 * @param pTag the tag to sort according to
	 * @pre pTag != null
	 */
	public void sortBy(Tag pTag) {
		assert pTag != null;
		Library.sortBy(aSongs, pTag, true);

	}

	/**
	 * Sorts songs by the specified tag, in ascending or descending order
	 * @param pTag the tag to sort according to
	 * @pre pTag != null
	 */
	public void sortBy(Tag pTag, boolean ascending) {
		assert pTag != null;
		Library.sortBy(aSongs, pTag, ascending);
	}

	/**
	 * Sorts all songs by title
	 */
	public void sortBySongTitle() {
		this.sortBy(TagType.TITLE);
	}

	/**
	 * Sorts all songs by genre
	 */
	public void sortBySongGenre() {
		this.sortBy(TagType.GENRE);
	}

	/**
	 * Sorts all songs by length
	 */
	public void sortBySongLength() {
		this.sortBy(TagType.TIME);
	}

	/**
	 * Sorts all songs by artist
	 */
	public void sortBySongArtist() {
		this.sortBy(TagType.ARTIST);
	}

	/**
	 * Sorts all songs by BPM
	 */
	public void sortBySongBPM() {
		this.sortBy(TagType.BPM);
	}
	
	/**
	 * Sorts all songs by composer
	 */
	public void sortBySongComposer() {
		this.sortBy(TagType.COMPOSER);
	}

	/**
	 * Returns a String containing the name and all songs of this playlist
	 */
	public String toString() {
		String strOfSongs = "";
		for(Song song: aSongs) {
			strOfSongs = strOfSongs + song.toString()+"\n";
		}
		return "---Playlist "+aName+"---\n"+strOfSongs;
	}

	@Override
	public Iterator<Song> iterator() {
		return aSongs.iterator();
	}
	
	/**
	 * play songs in a playlist
	 */	
	@Override
	public void play() {
		System.out.println("Currently playing Playlist: " + aName);
		aSongs.forEach((iSong) -> { iSong.play();});
		//System.out.println("\n");
	};

	/**
	 * add all songs in a playlist to favorites
	 */	
	@Override
	public void addtToFavorites() {
		favorite = true;
	};
	
	@Override
	public void removeFromFavorites() {
		favorite = false;
	};
	
	@Override
	public boolean isFavorite() {
		return favorite;
	}

	/**
	 *A method to check validity or emptiness
	 */
	@Override
	public boolean isEmpty() {
		return aSongs.isEmpty();
	};
	
	/**
	 * Shuffle the Playlist
	 */
	@Override
	public void shuffle() {
		if (isShuffled) {
			Collections.shuffle(aSongs);
		}
		else {
			aUnshuffledSongs = new ArrayList<>(aSongs);
			isShuffled = true;
			Collections.shuffle(aSongs);
		}
	}
	
	/**
	 * Unshuffle the Playlist
	 */
	@Override
	public void unshuffle() {
		if ((aUnshuffledSongs.isEmpty())||(!isShuffled)) {
			System.out.println("Playlist has not been Shuffled!\n");
		}
		else {
			aSongs = new ArrayList<>(aUnshuffledSongs);
			isShuffled = false;
		}
	}

}
