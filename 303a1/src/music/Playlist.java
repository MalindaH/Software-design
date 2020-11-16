package music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * a Playlist contains Songs and its name
 */

public class Playlist {
	private String aName;
	private ArrayList<Song> aSongs;

	/**
	 * Construct a playlist using its name
	 * @param pName, a String representing the name of the playlist
	 */
	public Playlist (String pName) {
		this.aName = pName;
		aSongs = new ArrayList<>();
	}

	/**
	 * Construct a playlist using its name and an ArrayList of songs
	 * @param pName, a String representing the name of the playlist
	 * @param pSongs, an Arraylist of songs representing all the songs in the playlist
	 */
	public Playlist(String pName, ArrayList<Song> pSongs) {
		this.aName = pName;
		this.aSongs = new ArrayList<>(pSongs);
	}

	/**
	 * Removes the specified Song from this Playlist
	 * @param pSong the Song to be removed
	 * @return the removed Song, return null if the song was not originally in the playlist
	 */
	public Song removeSong(Song pSong) {
		if(aSongs.remove(pSong)) {
			return pSong;
		}
		return null ;
	}

	/**
	 * Removes all invalid songs from this playlist
	 */
	public void removeInvalidSongs() {
		aSongs.removeIf(song -> !song.isValid());
	}

	/**
	 * Change the name of the playlist
	 * @param pNewName, a String representing the new name
	 */
	public void changeName(String pNewName) {
		if(pNewName != null) {
			this.aName = pNewName;
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
	public ArrayList<Song> getSongs() {
		return aSongs;
	}

	/**
	 * Add a Song to the Playlist
	 * @param pSong, the song being added
	 */
	public void addSongToPlaylist(Song songToAdd) {
		aSongs.add(songToAdd);
	}

	/**
	 * Add an ArrayList of Songs to the Playlist
	 * @param pSongs, an ArrayList of songs
	 */
	public void addSongsToPlaylist (ArrayList<Song> pSongs) {
		for(Song song: pSongs) {
			this.addSongToPlaylist(song);
		}
	}

	/**
	 * Get the number of songs in the playlist
	 * @return an int representing the number of songs
	 */
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
					int o1Time = (3600*(o1.getPlayingTime().getHour())) + (60*(o1.getPlayingTime().getMinute())) + (o1.getPlayingTime().getSecond());
					int o2Time = (3600*(o2.getPlayingTime().getHour())) + (60*(o2.getPlayingTime().getMinute())) + (o2.getPlayingTime().getSecond());;
					return o1Time-o2Time;
				}
				return 0;
			};
		});
	}

	/**
	 * Get the total playing time of songs in the playlist
	 * @return a String representing total playing time
	 */
	public String totalPlayingTime() {
		int hour = 0;
		int minute = 0;
		int second = 0;
		for(Song song : aSongs) {
			Time cur = song.getPlayingTime();
			if(cur != null) {
				hour += cur.getHour();
				minute += cur.getMinute();
				second += cur.getSecond();
			}
		}

		if(second>=60) {
			minute += second/60;
			second = second%60;
		}
		if(minute>=60) {
			hour += minute/60;
			minute = minute%60;
		}

		if(hour>0) {
			return hour+":"+minute+":"+second;
		}
		return minute+":"+second;
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
}
