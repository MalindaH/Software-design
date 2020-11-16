package music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Album {
	private String aTitle;
	private String aArtistName;
	// key is song, value is track number
	private HashMap<Song, Integer> aSongs;
	// this int stores the current max track number
	private int aMaxTrackNumber;

	/** 
	 * Construct an Album using only title
	 * @param pTitle, a String of title
	 */
	public Album(String pTitle) {
		this.aTitle = pTitle;
		this.aSongs = new HashMap<Song,Integer>();
		this.aMaxTrackNumber = 0;
	}

	/**
	 * Construct an Album using title and an artist name
	 * @param pTitle, a String of title
	 * @param pArtistName, a String of artist name
	 */
	public Album(String pTitle, String pArtistName) {
		this.aTitle = pTitle;
		this.aArtistName = pArtistName;
		this.aSongs = new HashMap<Song,Integer>();
		this.aMaxTrackNumber = 0;
	}

	/**
	 * Construct an Album using title and an ArrayList of songs
	 * @param pTitle, a String of title
	 * @param pSongs, an ArrayList of songs
	 */
	public Album(String pTitle, ArrayList<Song> pSongs) {
		this.aTitle = pTitle;
		this.aSongs = new HashMap<Song,Integer>();
		//index is the track number of the song
		int i = 0;
		for(Song song: pSongs) {
			aSongs.put(song, i);
			i++;
		}
		this.aMaxTrackNumber = i;
	}

	/**
	 * Construct an Album using title, artist name and an ArrayList of songs
	 * @param pTitle, a String of title
	 * @param pArtistName, a String of artist name
	 * @param pSongs, an ArrayList of songs
	 */
	public Album(String pTitle, String pArtistName, ArrayList<Song> pSongs) {
		this.aTitle = pTitle;
		this.aArtistName = pArtistName;
		this.aSongs = new HashMap<Song,Integer>();
		int i = 0;
		for(Song song: pSongs) {
			aSongs.put(song, i);
			i++;
		}
		this.aMaxTrackNumber = i;
	}

	/**
	 * Add a Song to the Album
	 * @param pSong, the song being added, assign track number = aMaxTrackNumber+1
	 */
	public void addSongToAlbum (Song pSong) {
		aSongs.put(pSong, aMaxTrackNumber+1);
		aMaxTrackNumber++;
	}

	/**
	 * Add an ArrayList of Songs to the Album
	 * @param pSongs, an ArrayList of songs
	 */
	public void addSongsToAlbum (ArrayList<Song> pSongs) {
		for(Song song: pSongs) {
			this.addSongToAlbum(song);
		}
	}

	/**
	 * Change the title of the album
	 * @param pTitle, a String of new title
	 */
	public void changeTitle(String pTitle) {
		if(pTitle != null) {
			this.aTitle = pTitle;
		}
	}

	/**
	 * Change the artist name of the album
	 * @param pArtist, a String of new artist name
	 */
	public void changeArtistName(String pArtist) {
		if(pArtist != null) {
			this.aArtistName = pArtist;
		}
	}

	/**
	 * Get the title of the album
	 * @return a String of the title
	 */
	public String getTitle() {
		return aTitle;
	}

	/**
	 * Get the artist name of the album
	 * @return a String of the artist name
	 */
	public String getArtistName() {
		return aArtistName;
	}

	/**
	 * Get the songs in the album
	 * @return an ArrayList of songs in the album
	 */
	public ArrayList<Song> getSongs() {
		return new ArrayList<>(aSongs.keySet());
	}

	/**
	 * Get the track number of a song in album
	 * @param pSong, a song in album
	 * @return an int representing the track number of the song
	 */
	public int getTrackNumber(Song pSong) {
		return aSongs.get(pSong);
	}

	/**
	 * Removes all invalid songs from this album
	 */
	public void removeInvalidSongs() {
		aSongs.entrySet().removeIf(e -> !e.getKey().isValid());
	}

	/**
	 * Removes the input song from this Album if it's in this album
	 * @param pSong the Song to be removed
	 * @return the removed Song if removed successfully; return null if the Song 
	 * was not originally in this Album
	 */
	public Song removeSong(Song pSong) {
		if(aSongs.remove(pSong)!=null) {
			return pSong;
		}
		return null;
	}

	/**
	 * First sort the songs by track numbers, then return a String containing info 
	 * of the album, including title, artist name and songs with their track numbers
	 * @return a String of information
	 */
	public String toString() {
		String strOfSongs = "";
		// by default: sort by ascending track numbers
		List<Entry<Song, Integer>> list = sortByTrackNumber(true);
		for(Entry<Song, Integer> entry: list) {
			strOfSongs = strOfSongs + entry.getValue()+" - "+entry.getKey()+"\n";
		}
		
		if(aArtistName != null) {
			return "---Album "+aTitle+" by "+aArtistName+"---\n"+strOfSongs;
		}
		return "---Album "+aTitle+"---\n"+strOfSongs;
	}

	/**
	 * Sorts all songs by their track numbers
	 * @param order: true for ascending sort, false for descending sort
	 * @return a List<Entry<Song, Integer>> of sorted (song, track number) pairs
	 */
	private List<Entry<Song, Integer>> sortByTrackNumber(boolean order) {
		List<Entry<Song, Integer>> list = new ArrayList<Entry<Song, Integer>>(aSongs.entrySet());
		Collections.sort(list, new Comparator<Entry<Song, Integer>>() {
			public int compare(Entry<Song, Integer> a, Entry<Song, Integer> b) {
				if (order) {
					return a.getValue().compareTo(b.getValue());
				} else {
					return b.getValue().compareTo(a.getValue());
				}
			}
		});
		return list;
	}
}
