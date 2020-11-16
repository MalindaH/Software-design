package music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import music.tags.*;

public class Album implements SongList, Iterable<Song>, Shufflable {
	private String aTitle;
	private String aArtistName;
	// key is track number, value is Song
	private HashMap<Integer, Song> aSongs = new HashMap<>();
	// this int stores the current max track number
	private int aMaxTrackNumber;
	private boolean favorite = false;
	private boolean isShuffled = false;
	private List<Song> aUnshuffledSongs = new ArrayList<>();
	
	/**
	 * Construct an Album using only title
	 * 
	 * @param pTitle, a String of title
	 *
	 */
	public Album(String pTitle) {
		try {
			if (pTitle == null || pTitle.equals("")) {
				throw new IllegalArgumentException("Title input is invalid. Album not created.");
			}
			this.aTitle = pTitle;
			this.aMaxTrackNumber = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Construct an Album using title and an ArrayList of songs
	 * 
	 * @param pTitle, a String of title
	 * @param pSongs, an ArrayList of songs
	 */
	public Album(String pTitle, ArrayList<Song> pSongs) {		
		try {
			if (pTitle == null || pTitle.equals("") || pSongs == null) {
				throw new IllegalArgumentException("Input title or ArrayList<Song> is invalid. Album not created");
			}
			this.aTitle = pTitle;
			//index is the track number of the song
			int i = 0;
			for(Song song: pSongs) {
				if(!song.isValid()){
					throw new IllegalArgumentException("Input Songs is invalid. One or more songs aren't valid.");
				}
				aSongs.put(i, song);
				i++;
			}
			this.aMaxTrackNumber = i;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add a Song to the Album
	 * 
	 * @param pSong, the song being added, assign track number = aMaxTrackNumber+1
	 * @pre pSong != null
	 */
	@Override
	public void addSong(Song pSong) {
		assert pSong != null;
		// check whether the song is already in the album
		boolean exists = false;
		for(Song cur: aSongs.values()) {
			if(cur == pSong) {
				System.err.println(pSong + " is already in Album "+aTitle+", failed to add this song.");
				exists = true;
			}
		}
		if(!exists) {
			aSongs.put(aMaxTrackNumber+1, pSong);
			aMaxTrackNumber++;
		}
	}

	/**
	 * Add an ArrayList of Songs to the Album
	 * 
	 * @param pSongs, an ArrayList of songs
	 */
	@Override
	public void addSongs(ArrayList<Song> pSongs) {
		try {
			if (pSongs == null) {
				throw new IllegalArgumentException("Input ArrayList<Song> is invalid.");
			}
			for (Song song : pSongs) {
				this.addSong(song);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the title of the album
	 * 
	 * @param pTitle, a String of new title
	 */
	public void setTitle(String pTitle) {
		try {
			if(pTitle == null) {
				throw new IllegalArgumentException("Title input is invalid!");
			}
			this.aTitle = pTitle;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the artist name of the album
	 * 
	 * @param pArtist, a String of new artist name
	 */
	public void setArtistName(String pArtist) {
		try {
			if(pArtist == null) {
				throw new IllegalArgumentException("Artist input is invalid!");
			}
			this.aArtistName = pArtist;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the title of the album
	 * 
	 * @return a String of the title
	 */
	public String getTitle() {
		return aTitle;
	}

	/**
	 * Get the artist name of the album
	 * 
	 * @return a String of the artist name
	 */
	public String getArtistName() {
		return aArtistName;
	}

	/**
	 * Get the songs in the album
	 * 
	 * @return an ArrayList of songs in the album
	 */
	@Override
	public ArrayList<Song> getAllSongs() {
		return new ArrayList<>(aSongs.values());
	}

	/**
	 * Gets the first song in this Album
	 * 
	 * @return the first Song in this Album, return null if Album is empty
	 */
	@Override
	public Song getFirstSong() {
		for (int i = 1; i <= aSongs.size(); i++) {
			if (aSongs.get(i) != null) {
				return aSongs.get(i);
			}
		}
		return null;
	}
	

	/**
	 * Gets a song/songs based on the tag value provided
	 *
	 * @return the songs, return null if song is not exists
	 */
	public ArrayList<Song> findSongsByTag(String aTagValue) {
		ArrayList<Song> songs = new ArrayList<Song>();		
		for (Song cur: aSongs.values()) {
			if (cur.getTitleOfSong().toLowerCase().equals(aTagValue.toLowerCase()) || 
					cur.getArtistOfSong().toLowerCase().equals(aTagValue.toLowerCase()) ||
					cur.getBPMOfSong().equals(aTagValue)|| cur.getPlayingTime().toString().equals(aTagValue) ||
					cur.getGenreOfSong().toLowerCase().equals(aTagValue.toLowerCase()) ||
					cur.getComposerOfSong().toLowerCase().equals(aTagValue.toLowerCase())) {
				songs.add(cur);
			}
		} 
		if (songs.size() > 0 ) {
			return songs;
		} else {
			System.out.println("Song not found, please provide a valid tag value");
			return null; 
		}
	}
	
	/**
	 * Gets a song based on the title provided
	 *
	 * @return the song, return null if song is not exists
	 */
	public ArrayList<Song> findSongByTitle(String ptitle) {
		return this.findSongsByTag(ptitle);
	}
	
	/**
	 * Gets a song based on the artist provided
	 *
	 * @return the song, return null if song is not exists
	 */
	public ArrayList<Song> findSongByArtist(String pArtist) {
		return this.findSongsByTag(pArtist);
	}
	
	/**
	 * Gets a song based on the BPM provided
	 *
	 * @return the song, return null if song is not exists
	 */
	public ArrayList<Song> findSongByBPM(String pBPM) {
		return this.findSongsByTag(pBPM);
	}
	
	/**
	 * Gets a song based on the genre provided
	 *
	 * @return the song, return null if song is not exists
	 */
	public ArrayList<Song> findSongByGenre(String pGenre) {
		return this.findSongsByTag(pGenre);
	}
	
	/**
	 * Gets a song based on the composer provided
	 *
	 * @return the song, return null if song is not exists
	 */
	public ArrayList<Song> findSongByComposer(String pComposer) {
		return this.findSongsByTag(pComposer);
	}
	
	/**
	 * Gets a song based on the track number provided
	 *
	 * @return the song, return null if song is not exists
	 */
	public Song findASong(int tracknumber) {
		if (aSongs.get(tracknumber) != null) {
			return aSongs.get(tracknumber);
		} 
		System.out.println("Song not found, please provide a valid track number.");
		return null; 
	}

	/**
	 * Get the track number of a song in album
	 * 
	 * @param pSong, a song in album
	 * @return an int representing the track number of the song, return -1 if song
	 *         is not in this Album
	 */
	public int getTrackNumber(Song pSong) {
		for (Entry<Integer, Song> entry : aSongs.entrySet()) {
			if (entry.getValue().equals(pSong)) {
				return entry.getKey();
			}
		}
		return -1;
	}

	/**
	 * Removes all invalid songs from this album
	 */
	public void removeInvalidSongs() {
		aSongs.entrySet().removeIf(e -> e.getValue().isValid());
	}

	/**
	 * Removes the input song from this Album if it's in this album
	 * 
	 * @param pSong the Song to be removed
	 * @return the removed Song if removed successfully; return null if the Song was
	 *         not originally in this Album
	 */
	@Override
	public Song removeSong(Song pSong) {
		int track = this.getTrackNumber(pSong);
		if (track != -1) {
			aSongs.remove(track);
			return pSong;
		}
		return null;
	}

	/**
	 * Removes songs in the input ArrayList
	 * 
	 * @param pSongs the ArrayList of songs to be removed
	 */
	@Override
	public void removeSongs(ArrayList<Song> pSongs) {
		for (Song cur : pSongs) {
			this.removeSong(cur);
		}
	}

	/**
	 * First sort the songs by track numbers, then return a String containing info
	 * of the album, including title, artist name and songs with their track numbers
	 * 
	 * @return a String of information
	 */
	public String toString() {
		String strOfSongs = "";
		// by default: sort by ascending track numbers
		List<Entry<Integer, Song>> list = sortByTrackNumber(true);
		for (Entry<Integer, Song> entry : list) {
			strOfSongs = strOfSongs + entry.getKey() + " - " + entry.getValue() + "\n";
		}

		if (aArtistName != null) {
			return "---Album " + aTitle + " by " + aArtistName + "---\n" + strOfSongs;
		}
		return "---Album " + aTitle + "---\n" + strOfSongs;
	}

	/**
	 * Sorts all songs by their track numbers
	 * @param ascending true for ascending sort, false for descending sort
	 * @return a List<Entry<Song, Integer>> of sorted (song, track number) pairs
	 */
	private List<Entry<Integer, Song>> sortByTrackNumber(boolean ascending) {
		List<Entry<Integer, Song>> list = new ArrayList<Entry<Integer, Song>>(aSongs.entrySet());
		Collections.sort(list, new Comparator<Entry<Integer, Song>>() {
			public int compare(Entry<Integer, Song> a, Entry<Integer, Song> b) {
				if (ascending) {
					return a.getKey().compareTo(b.getKey());
				} else {
					return b.getKey().compareTo(a.getKey());
				}
			}
		});
		return list;
	}

	/**
	 * Sorts songs by the specified tag, in ascending order by default
	 * has to return the sorted List<Song> converted from the HashMap
	 * @param pTag the tag to sort according to
	 * @return a List<Song> of sorted songs
	 */
	public List<Song> sortBy(Tag pTag) {
		List<Song> list = new ArrayList<Song>(aSongs.values());
		Library.sortBy(list, pTag, true);
		return list;	
	}
	
	/**
	 * Sorts songs by the specified tag, in ascending or descending order
	 * @param pTag the tag to sort according to
	 * @return a List<Song> of sorted songs
	 */
	public List<Song> sortBy(Tag pTag, boolean ascending) {
		List<Song> list = new ArrayList<Song>(aSongs.values());
		Library.sortBy(list, pTag, ascending);
		return list;
	}
	
	/**
	 * Sorts all songs by title
	 * @return a List<Song> of sorted songs
	 */
	public List<Song> sortBySongTitle() {
		return this.sortBy(TagType.TITLE);
	}
	
	/**
	 * Sorts all songs by genre
	 * @return a List<Song> of sorted songs
	 */
	public List<Song> sortBySongGenre() {
		return this.sortBy(TagType.GENRE);
	}
	
	/**
	 * Sorts all songs by length
	 * @return a List<Song> of sorted songs
	 */
	public List<Song> sortBySongLength() {
		return this.sortBy(TagType.TIME);
	}
	
	/**
	 * Sorts all songs by artist
	 * @return a List<Song> of sorted songs
	 */
	public List<Song> sortBySongArtist() {
		return this.sortBy(TagType.ARTIST);
	}
	
	/**
	 * Sorts all songs by BPM
	 * @return a List<Song> of sorted songs
	 */
	public List<Song> sortBySongBPM() {
		return this.sortBy(TagType.BPM);
	}
	
	/**
	 * Sorts all songs by composer
	 */
	public List<Song> sortBySongComposer() {
		return this.sortBy(TagType.COMPOSER);
	}

	/**
	 * Returns the number of songs in this Album
	 * @return an int indicating the number of songs
	 */
	@Override
	public int numOfSongs() {
		return aSongs.size();
	}

	/**
	 * Returns an iterator for this Album
	 * @return an Iterator<Song>
	 */
	@Override
	public Iterator<Song> iterator() {
		return new Iterator<Song>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				// get keySet() each time call hasNext() just in case client adds/removes a Song
				// System.out.println(Arrays.toString(aSongs.keySet().toArray()));
				Object[] arr = aSongs.keySet().toArray();
				Arrays.sort(arr);
				if (arr.length >= 1) {
					if (index < (int) arr[0]) {
						return true;
					}
					for (int i = 0; i < arr.length - 1; i++) {
						if (index >= (int) arr[i] && index < (int) arr[i + 1]) {
							return true;
						}
					}
				}
				return false;
			}

			@Override
			public Song next() {
				// get keySet() each time call next() just in case client adds/removes a Song
				Object[] arr = aSongs.keySet().toArray();
				Arrays.sort(arr);
				if (arr.length >= 1) {
					if (index < (int) arr[0]) {
						index = (int) arr[0];
						return aSongs.get(index);
					}
					for (int i = 0; i < arr.length - 1; i++) {
						if (index >= (int) arr[i] && index < (int) arr[i + 1]) {
							index = (int) arr[i + 1];
							return aSongs.get(index);
						}
					}
				}
				return null;
			}
		};
	}
	
	/**
	 * Play songs in this album
	 */
	@Override
	public void play() {
		System.out.println("Currently playing Album: " + aTitle + " by: " + aArtistName);
		for (Song song : aSongs.values()) {
		    song.play();
		}
	};	

	/**
	 * Add all songs in an album to favorites
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
	 * Check validity or emptiness of this album
	 * @return a boolean indicating whether the album is empty
	 */
	@Override
	public boolean isEmpty() {
		return aSongs.isEmpty();
	}

	/**
	 * Shuffle the Album
	 */
	@Override
	public void shuffle() {
		if (isShuffled) {
			ArrayList <Object> forShuffle = new ArrayList<Object>(aSongs.values());
			Collections.shuffle(forShuffle);
			final Iterator<Object> vIter = forShuffle.iterator();
			for (Integer i : aSongs.keySet()) {
				aSongs.put(i, (Song) vIter.next());
			}
		}
		else {
			aUnshuffledSongs = new ArrayList<>(aSongs.values());
			isShuffled = true;
			ArrayList <Object> forShuffle = new ArrayList<Object>(aSongs.values());
			Collections.shuffle(forShuffle);
			final Iterator<Object> vIter = forShuffle.iterator();
			for (Integer i : aSongs.keySet()) {
				aSongs.put(i, (Song) vIter.next());
			}
		}	
	}

	/**
	 * Unshuffle the Album
	 */
	@Override
	public void unshuffle() {
		if ((aUnshuffledSongs.isEmpty())||(!isShuffled)) {
			System.out.println("Playlist has not been Shuffled!\n");
		}
		else {
			isShuffled = false;
			ArrayList <Object> forShuffle2 = new ArrayList<Object>(aUnshuffledSongs);
			final Iterator<Object> vIter = forShuffle2.iterator();
			for (Integer i : aSongs.keySet()) {
				aSongs.put(i, (Song) vIter.next());
			}
		}
	}
	
}
