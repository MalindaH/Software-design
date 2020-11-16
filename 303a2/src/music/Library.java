package music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import music.FilterClass.*;
import music.tags.*;

/**
 * a Library includes Songs, Playlists, and Albums
 */
public class Library implements Iterable<Song>, SongList, Shufflable {
	private List<Song> aSongs = new ArrayList<>();
	private List<Playlist> aPlaylists = new ArrayList<>();
	private List<Album> aAlbums = new ArrayList<>();
	private List<Song> aUnshuffledSongs = new ArrayList<>();
	private boolean isShuffled = false;
	private boolean isFavorite = false;

	/**
	 * Constructs an empty library
	 */
	public Library() {
		// do nothing
	}

	/**
	 * Constructs a library with input songs, playlists, and albums
	 * 
	 * @param pSongs    an ArrayList of Songs to be added
	 * @param pPlaylist an ArrayList of Playlists to be added
	 * @param pAlbum    an ArrayList of Albums to be added
	 */
	public Library(ArrayList<Song> pSongs, ArrayList<Playlist> pPlaylist, ArrayList<Album> pAlbum) {
		// initialize the attributes with new ArrayLists to make sure of info hiding
		this.aSongs = new ArrayList<>(pSongs);
		this.aPlaylists = new ArrayList<>(pPlaylist);
		this.aAlbums = new ArrayList<>(pAlbum);
	}

	/**
	 * Adds a song to the library
	 * 
	 * @param pSong the song to be added
	 * @return the added song; return null if input is invalid
	 */
	@Override
	public void addSong(Song pSong) {
		try {
			if (pSong == null) {
				throw new IllegalArgumentException("The input Song is invalid");
			}
			for (Song song : aSongs) {
				if (song.equals(pSong)) {
					// if duplicate exists, do nothing
				}
			}
			aSongs.add(pSong);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Song> findSongsByTag(String aTag) {
		ArrayList<Song> songs = new ArrayList<Song>();
		for (Song cur: aSongs) {
			if (cur.getTitleOfSong().toLowerCase().equals(aTag.toLowerCase()) || 
					cur.getArtistOfSong().toLowerCase().equals(aTag.toLowerCase())
					|| cur.getBPMOfSong().equals(aTag) || cur.getPlayingTime().toString().equals(aTag) ||
					cur.getGenreOfSong().toLowerCase().equals(aTag.toLowerCase())
					|| cur.getComposerOfSong().toLowerCase().equals(aTag.toLowerCase())) {
				songs.add(cur);
			}
		}
		if (songs.size() > 0) {
			return songs;
		} else {
			System.out.println("Song not found, please provide a valid tag.");
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
	 * Gets all songs in this library
	 * 
	 * @return an ArrayList of all songs in this library
	 */
	public ArrayList<Song> getAllSongs() {
		return new ArrayList<>(aSongs);
	}
	
	/**
	 * Gets the first song in this Library. Return null if the library is empty.
	 * @return the first Song in this Library
	 */
	public Song getFirstSong() {
		if(aSongs.size() == 0) {
			System.err.println("Library is currently empty.");
			return null;
		}
		return aSongs.get(0);
	}

	/**
	 * Gets all playlists in this library
	 * 
	 * @return an ArrayList of all playlists in this library
	 */
	public ArrayList<Playlist> getPlaylists() { // SHALLOW copy
		return new ArrayList<>(aPlaylists);
	}

	/**
	 * Gets all albums in this library
	 * 
	 * @return an ArrayList of all albums in this library
	 */
	public ArrayList<Album> getAlbums() { // SHALLOW COPY
		return new ArrayList<>(aAlbums);
	}

	/**
	 * Adds an input Album to this library, and the songs in the album are also added
	 * 
	 * @param pAlbum the Album to be added
	 * @return the added Album
	 * @pre pAlbum != null
	 */
	public Album addAlbum(Album pAlbum) {
		assert pAlbum != null;
		aAlbums.add(pAlbum);
		for (Song song : pAlbum) {
			this.addSong(song);
		}
		return pAlbum;
	}

	/**
	 * Initializes a Playlist of the input name and adds it to this library
	 * 
	 * @param playlistName a String representing the name of the playlist to be
	 *                     added
	 * @return the new Playlist
	 * @pre pPlaylist != null
	 */
	public Playlist addPlaylist(Playlist pPlaylist) {
		assert pPlaylist != null;
		aPlaylists.add(pPlaylist);
		for (Song song : pPlaylist) {
			this.addSong(song);
		}
		return pPlaylist;

	}

	/**
	 * Removes all invalid songs from this library, including those in all albums
	 * and playlists
	 */
	public void removeInvalidSongs() {
		aSongs.removeIf(song -> song.isValid());
		for (Album a : aAlbums) {
			a.removeInvalidSongs();
		}
		for (Playlist p : aPlaylists) {
			p.removeInvalidSongs();
		}
	}

	/**
	 * Removes the specified Song from this library, and all albums and playlists
	 * 
	 * @param pSong the Song to be removed
	 */
	@Override
	public Song removeSong(Song pSong) {
		assert pSong != null;
		aSongs.remove(pSong);
		for (Album a : aAlbums) {
			a.removeSong(pSong);
		}
		for (Playlist p : aPlaylists) {
			p.removeSong(pSong);
		}
		return pSong;
	}

	@Override
	public Iterator<Song> iterator() {
		return aSongs.iterator(); // returning List.iterator()
	}

	/**
	 * Sorts the list of Songs by a Tag specified by the client in ascending or
	 * descending order as specified by client
	 * 
	 * @param pTag the Tag to sort according to
	 * @pre pTag != null
	 */
	public void sortBy(Tag pTag, boolean ascending) {
		assert pTag != null;
		Library.sortBy(this.aSongs, pTag, ascending);
	}

	/**
	 * Sorts the list of Songs by a Tag specified by the client sort by ascending
	 * order by default
	 * 
	 * @param pTag the Tag to sort according to
	 * @pre pTag != null
	 */
	public void sortBy(Tag pTag) {
		assert pTag != null;
		Library.sortBy(this.aSongs, pTag, true);
	}
	
	/**
	 * Sorts the input list of Songs by a Tag specified by the client static method
	 * to be called in Playlist and Album classes, in ascending or descending order
	 * 
	 * @param pTag      : the Tag to sort according to
	 * @param ascending : true for ascending and false for descending
	 * @pre aList != null && pTag != null
	 */
	public static void sortBy(List<Song> aList, Tag pTag, boolean ascending) {
		assert aList != null && pTag != null;
		Collections.sort(aList, new Comparator<Song>() {
			@Override
			public int compare(Song o1, Song o2) {
				// for now assume tag values can be String, Integer, Double, or Time
				if (ascending) {
					if (o1.getTagValue(pTag) != null && o2.getTagValue(pTag) != null) {
						if (o1.getTagValue(pTag) instanceof String) {
							return ((String) o1.getTagValue(pTag)).toLowerCase()
									.compareTo(((String) o2.getTagValue(pTag)).toLowerCase());
						} else if (o1.getTagValue(pTag) instanceof Integer) {
							return ((Integer) o1.getTagValue(pTag)).compareTo((Integer) o2.getTagValue(pTag));
						} else if (o1.getTagValue(pTag) instanceof Double) {
							return ((Double) o1.getTagValue(pTag)).compareTo((Double) o2.getTagValue(pTag));
						} else if (o1.getTagValue(pTag) instanceof Time) {
							return ((Time) o1.getTagValue(pTag)).compareTo((Time) o2.getTagValue(pTag));
						}
					}
					// otherwise return 0 (i.e. don't sort)
					return 0;
				} else {
					if (o1.getTagValue(pTag) != null && o2.getTagValue(pTag) != null) {
						if (o1.getTagValue(pTag) instanceof String) {
							return ((String) o2.getTagValue(pTag)).toLowerCase()
									.compareTo(((String) o1.getTagValue(pTag)).toLowerCase());
						} else if (o1.getTagValue(pTag) instanceof Integer) {
							return ((Integer) o2.getTagValue(pTag)).compareTo((Integer) o1.getTagValue(pTag));
						} else if (o1.getTagValue(pTag) instanceof Double) {
							return ((Double) o2.getTagValue(pTag)).compareTo((Double) o1.getTagValue(pTag));
						} else if (o1.getTagValue(pTag) instanceof Time) {
							return ((Time) o2.getTagValue(pTag)).compareTo((Time) o1.getTagValue(pTag));
						}
					}
					// otherwise return 0 (i.e. don't sort)
					return 0;
				}
			};
		});
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
	 * Returns a String of all songs in this Library
	 */
	public String toString() {
		List<Song> songs = this.getAllSongs();
		String s = "";
		for (Song cur : songs) {
			s = s.concat(cur.toString() + "\n");
		}
		return s;
	}

	/**
	 * METHOD 1 : Generates a Playlist according to song specified by a filter
	 * 
	 * @param pName   is the name of the Playlist
	 * @param pFilter is the Filter being applied to the library to generate the Playlist
	 * @pre pName != null && pFilter != null
	 */
	public Playlist generatePlaylist(String pName, Filter pFilter) {
		assert pName != null && pFilter != null;
		ArrayList<Song> midFilteredlist = this.getAllSongs();
		Playlist generatedPlaylist;
		midFilteredlist = (ArrayList<Song>) pFilter.filterCriteria(midFilteredlist);

		// Check if there songs in the filer list or not. If not, returns null;
		if (midFilteredlist.size() == 0) {
			System.out.println("No songs found matching the filtering criteria, empty playlist generated!");
			return null;
		}
		// search is not empty
		generatedPlaylist = new Playlist(pName, midFilteredlist);
		return generatedPlaylist;
	}

	/**
	 * METHOD 2 : multiple filters applied before generating playlist
	 * 
	 * @param pName   is the name of the Playlist
	 * @param pFilter is the list of Filters being applied to the library to
	 *                generate the playlist
	 * @pre pName != null && filters != null
	 */
	public Playlist generatePlaylist(String pName, boolean AND, Filter... filters) {
		assert pName != null && filters != null;
		ArrayList<Song> midFilteredlist = this.getAllSongs();
		Playlist generatedPlaylist;
		// have to apply all the filters once by one
		Filter prevFilter = null; // extract the first filter out

		if (AND) { // if user wants to apply all the filters :: AND LOGIC
			for (int i = 1; i < filters.length; i++) { // iterating through all the filters
				if (i == 1) { // loop only runs in first iteration
					Filter firstFilter = filters[i - 1]; // because i starts from 1 not 0
					Filter secondFilter = filters[i];
					FilterAND ANDFilter = new FilterAND(firstFilter, secondFilter); // creating AND filter
					midFilteredlist = (ArrayList<Song>) ANDFilter.filterCriteria(midFilteredlist);
					prevFilter = ANDFilter; // store the previous ANDfilter for next iteration
				}
				// for i >= 2
				FilterAND ANDFilter = new FilterAND(prevFilter, filters[i]); // takes previous filter and filters[2]
				midFilteredlist = (ArrayList<Song>) ANDFilter.filterCriteria(midFilteredlist);
				prevFilter = ANDFilter;

			}

		} else { // if user wants to apply any one of the filters :: OR Logic
			for (int i = 1; i < filters.length; i++) { // iterating through all the filters
				if (i == 1) { // loop only runs in first iteration
					Filter firstFilter = filters[i - 1]; // because i starts from 1 not 0
					Filter secondFilter = filters[i];
					FilterOR ORFilter = new FilterOR(firstFilter, secondFilter); // creating AND filter
					midFilteredlist = (ArrayList<Song>) ORFilter.filterCriteria(midFilteredlist);
					prevFilter = ORFilter; // store the previous ANDfilter for next iteration
				}
				// for i >= 2
				FilterOR ORFilter = new FilterOR(prevFilter, filters[i]); // takes previous filter and filters[2]
				midFilteredlist = (ArrayList<Song>) ORFilter.filterCriteria(midFilteredlist);
				prevFilter = ORFilter;
			}
		}

		// after all the filtering is done generate a playlist with songs attained if
		// any songs left else return null
		if (midFilteredlist.size() == 0) { // no songs left filtering
			System.out.println("0 Search results for filtering, empty playlist generated");
			return new Playlist(pName);
		}
		// else filtered songs list is not empty
		generatedPlaylist = new Playlist(pName, midFilteredlist);
		return generatedPlaylist;

	}// end

	/**
	 * Shuffle the Library
	 */
	public void shuffle() {
		if (isShuffled == true) {
			Collections.shuffle(aSongs);
		} else {
			aUnshuffledSongs = new ArrayList<>(aSongs);
			isShuffled = true;
			Collections.shuffle(aSongs);
		}
	}

	/**
	 * Unshuffle the Library
	 */
	public void unshuffle() {
		if (aUnshuffledSongs.isEmpty()) {
			System.out.println("Library has not been Shuffled!\n");
		} else {
			aSongs = new ArrayList<>(aUnshuffledSongs);
			isShuffled = false;
		}
	}

	/**
	 * Finds all favorited songs
	 * @return a List of favorited songs
	 */
	public List<Song> findSongsByFavorite() {
		ArrayList<Song> favSongs = new ArrayList<>();
		for (Song song : aSongs) {
			if (song.isFavorite() == true) {
				favSongs.add(song);
			}
		}
		return favSongs;
	}

	/**
	 * Finds all favorited albums
	 * @return a List of favorited albums
	 */
	public List<Album> findAlbumsByFavorite() {
		ArrayList<Album> favAlbums = new ArrayList<>();
		for (Album album : aAlbums) {
			if (album.isFavorite() == true) {
				favAlbums.add(album);
			}
		}
		return favAlbums;
	}

	/**
	 * Finds all favorited playlists
	 * @return a List of favorited playlists
	 */
	public List<Playlist> findPlaylistsByFavorite() {
		ArrayList<Playlist> favPlaylists = new ArrayList<>();
		for (Playlist playlist : aPlaylists) {
			if (playlist.isFavorite() == true) {
				favPlaylists.add(playlist);
			}
		}
		return favPlaylists;
	}

	@Override
	public void play() {
		System.out.println("Currently playing Library ");
		aSongs.forEach((iSong) -> { iSong.play();});
	};

	@Override
	public void addtToFavorites() {
		isFavorite = true;
	}

	@Override
	public void removeFromFavorites() {
		isFavorite = false;		
	}

	@Override
	public boolean isFavorite() {
		return this.isFavorite;
	}

	@Override
	public boolean isEmpty() {
		return aSongs.size() == 0;
	}

	/**
	 * Removes the specified list of songs from this Library.
	 * @param pSongs the ArrayList of Songs to be removed
	 * @pre pSongs != null
	 */
	@Override
	public void removeSongs(ArrayList<Song> pSongs) {
		assert pSongs != null;
		for (Song cur : pSongs) {
			this.removeSong(cur);
		}
		for(Album a: aAlbums) {
			a.removeSongs(pSongs);
		}
		for(Playlist p: aPlaylists) {
			p.removeSongs(pSongs);
		}
	}

	/**
	 * Add an ArrayList of Songs to the library
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
	 * Get the number of songs in the library
	 * @return an int representing the number of songs
	 */
	@Override
	public int numOfSongs() {
		return aSongs.size();
	}
}
