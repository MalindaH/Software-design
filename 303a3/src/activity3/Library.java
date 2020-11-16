package activity3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a collection of songs, playlists, and albums.
 */
public class Library
{
	public static final Library INSTANCE = new Library();

	private final Map<String, Song> aSongs = new HashMap<>();
	private final List<Album> aAlbums = new ArrayList<>();
	private final List<Playlist> aPlaylists = new ArrayList<>();
	private boolean aBackedup = false;

	/**
	 * private constructor to create an empty library
	 */
	private Library() {	}

	/**
	 * Adds the input Song to the Library
	 * @param pSong the Song to be added
	 * @return true upon success
	 * @pre pSong != null
	 */
	public static boolean addSong(Song pSong) {
		assert pSong != null;
		Song s = pSong.copy();
		INSTANCE.aSongs.put(s.getFile(), s);
		INSTANCE.aBackedup = false;
		return true;
	}

	/**
	 * Adds the input Album and all the Songs it contains to the Library
	 * unable to add if Library doesn't contain any songs
	 * @param pAlbum the Album to be added
	 * @return true upon success
	 * @pre pAlbum != null
	 */
	public static boolean addAlbum(Album pAlbum) {
		assert pAlbum != null;
		if(Library.size()==0) {
			return false;
		}
		INSTANCE.aAlbums.add(pAlbum);
		for(Song cur: pAlbum.getSongs()) {
			Library.addSong(cur);
		}
		INSTANCE.aBackedup = false;
		return true;
	}

	/**
	 * Adds the input Playlist and all the Songs it contains to the Library
	 * unable to add if Library doesn't contain any songs
	 * @param pPlaylist the Playlist to be added
	 * @return true upon success
	 * @pre pPlaylist != null
	 */
	public static boolean addPlaylist(Playlist pPlaylist) {
		assert pPlaylist != null;
		Playlist p = new Playlist(pPlaylist.getTitle());
		if(Library.size()==0) {
			return false;
		}
		INSTANCE.aPlaylists.add(p);
		for(Song cur: pPlaylist.getSongs()) {
			Library.addSong(cur);
		}
		INSTANCE.aBackedup = false;
		return true;
	}

	/**
	 * Removes the specified Song and removes it from all albums and playlists
	 * @param pSong the song to be removed
	 * @return true upon success
	 * @pre pSong != null
	 */
	public static boolean removeSong(Song pSong) {
		assert pSong != null;
		if(INSTANCE.aSongs.remove(pSong.getFile()) != null) {
			for(Album a: INSTANCE.aAlbums) {
				a.removeSong(pSong);
			}
			for(Playlist p: INSTANCE.aPlaylists) {
				p.removeSong(pSong);
			}
			INSTANCE.aBackedup = false;
			return true;
		}
		return false;
	}

	/**
	 * Removes the specified Album from the Library, can remove all songs it contains or not
	 * @param pAlbum the Album to be removed
	 * @param pRemoveSongs true for removing all songs it contains
	 * @return true upon success
	 * @pre pAlbum != null
	 */
	public static boolean removeAlbum(Album pAlbum, boolean pRemoveSongs) {
		assert pAlbum != null;
		if(INSTANCE.aAlbums.remove(pAlbum)) {
			INSTANCE.aBackedup = false;
			if(pRemoveSongs) {
				for(Song s: pAlbum.getSongs()) {
					Library.removeSong(s);
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Removes the specified Playlist from the Library, can remove all songs it contains or not
	 * @param pPlaylist the playlist to be removed
	 * @param pRemoveSongs true for removing all songs it contains
	 * @return true upon success
	 * @pre pPlaylist != null
	 */
	public static boolean removePlaylist(Playlist pPlaylist, boolean pRemoveSongs) {
		assert pPlaylist != null;
		if(INSTANCE.aPlaylists.remove(pPlaylist)) {
			INSTANCE.aBackedup = false;
			// let client choose whether to delete all songs of the album from the library
			Scanner scanner = new Scanner(System.in);
			System.out.println("Do you want to also remove the songs of Playlist "+pPlaylist.getTitle()+" from the library? (y/n)");
			if(scanner.nextLine().charAt(0) == 'y') {
				for(Song s: pPlaylist.getSongs()) {
					Library.removeSong(s);
				}
			}
			scanner.close();
			return true;
		}
		return false;
	}

	/**
	 * Return a List<Song> containing a deep copy of all Songs in the Library
	 * @return List<Song> containing copies of all songs
	 */
	public static List<Song> getSongs(){
		ArrayList<Song> output = new ArrayList<Song>();
		for(Song cur: INSTANCE.aSongs.values()) {
			output.add(cur.copy());
		}
		return output;
	}

	/**
	 * Backs up the Library
	 */
	public static void backup() {
		Database.backup(INSTANCE);
		INSTANCE.aBackedup = true;
	}

	/**
	 * Restores the Library to the previously backed-up state
	 */
	public static void restore() {
		Database.restore(INSTANCE);
		INSTANCE.aBackedup = true;
	}

	/**
	 * Returns the number of Songs the Library contains
	 * @return number of songs in Library
	 */
	public static int size() {
		return INSTANCE.aSongs.size();
	}
	
	/**
	 * Returns whether the Library is empty of not
	 * @return boolean that indicates if Library is empty or not
	 */
	public static boolean isEmpty() {
		return INSTANCE.aSongs.size() + INSTANCE.aPlaylists.size() + INSTANCE.aAlbums.size() == 0;
	}

	/**
	 * Return true if the Library is backed up
	 * @return boolean to indicate if Library has been backed up
	 */
	public static boolean isBackedup() {
		return INSTANCE.aBackedup;
	}
	
	/**
	 * Return a String containing all songs in the Library
	 * @return a String containing all songs in the Library
	 */
	public static String print() {
		String output = "---Library---\n";
		for(Song cur: getSongs()) {
			output = output + cur.toString() + "\n";
		}
		return output;
	}
}
