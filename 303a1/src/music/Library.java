package music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * a Library includes Songs, Playlists, and Albums
 */
public class Library {
	private ArrayList<Song> aSongs;
	private ArrayList<Playlist> aPlaylists;
	private ArrayList<Album> aAlbums;

	/**
	 * Constructs an empty library
	 */
	public Library() {
		aSongs = new ArrayList<Song>();
		aAlbums = new ArrayList<Album>();
		aPlaylists = new ArrayList<Playlist>();
	}

	/**
	 * Constructs a library with input songs, playlists, and albums
	 * @param pSongs an ArrayList of Songs to be added
	 * @param pPlaylist an ArrayList of Playlists to be added
	 * @param pAlbum an ArrayList of Albums to be added
	 */
	public Library(ArrayList<Song> pSongs, ArrayList<Playlist> pPlaylist, ArrayList<Album> pAlbum) {
		// initialize the attributes with new ArrayLists to make sure of info hiding
		this.aSongs = new ArrayList<>(pSongs);
		this.aPlaylists = new ArrayList<>(pPlaylist);
		this.aAlbums = new ArrayList<>(pAlbum);   
	}

	/**
	 * Adds a song of the input file path to the library. If the file path is invalid, 
	 * prompt for a new file path
	 * @param pFilePath a String representing the file path of the song to be added
	 * @return the initialized Song object
	 */
	public Song addSong(String pFilePath) {
		Song song1;
		try {
			String[] info = pFilePath.split("\\.");
			song1 = new Song(info[0], info[1]);
			aSongs.add(song1);
		} catch (Exception e) {
			e.printStackTrace();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter a new file path:");
			String[] info = scanner.nextLine().split("\\.");
			song1 = new Song(info[0], info[1]);
		}
		return song1;
	}
	
	/**
	 * Adds the input Song to this library
	 * @param pSong the song to be added
	 * @return the added song; return null if input is invalid
	 */
	public Song addSong(Song pSong) {
		if(pSong != null) {
			aSongs.add(pSong);
			return pSong;
		}
		return null;
	}

	/**
	 * Gets all songs in this library
	 * @return an ArrayList of all songs in this library
	 */
	public ArrayList<Song> getSongs() {
		return new ArrayList<>(aSongs);
	}

	/**
	 * Gets all playlists in this library
	 * @return an ArrayList of all playlists in this library
	 */
	public ArrayList<Playlist> getPlaylists() {
		return new ArrayList<>(aPlaylists);
	}

	/**
	 * Gets all albums in this library
	 * @return an ArrayList of all albums in this library
	 */
	public ArrayList<Album> getAlbums() {
		return new ArrayList<>(aAlbums);
	}

	/**
	 * Initializes an Album of the input name and adds it to this library
	 * @param pAlbumName a String representing the name of the Album to be added
	 * @return the new Album
	 */
	public Album addAlbum(String pAlbumName) {
		Album newAlbum = new Album(pAlbumName);
		aAlbums.add(newAlbum);
		return newAlbum;
	}

	/**
	 * Initializes an Album with the input name, artist name, and songs and adds it to this library
	 * @param pAlbumName a String representing the name of the Album to be added
	 * @param pArtistName, a String of artist name
	 * @param pSongs, an ArrayList of songs
	 * @return the new Album
	 */
	public Album addAlbum(String pAlbumName, String pArtistName, ArrayList<Song> pSongs) {
		Album newAlbum = new Album(pAlbumName, pArtistName, pSongs);
		aAlbums.add(newAlbum);
		return newAlbum;
	}
	
	/**
	 * Initializes a Playlist of the input name and adds it to this library
	 * @param playlistName a String representing the name of the playlist to be added
	 * @return the new Playlist
	 */
	public Playlist addPlaylist(String pPlaylistName) {
		Playlist newPlaylist = new Playlist(pPlaylistName);
		aPlaylists.add(newPlaylist);
		return newPlaylist;

	}

	/**
	 * Removes all invalid songs from this library, including those in all albums and playlists
	 */
	public void removeInvalidSongs() {
		aSongs.removeIf(song -> !song.isValid());
		for(Album a: aAlbums) {
			a.removeInvalidSongs();
		}
		for(Playlist p: aPlaylists) {
			p.removeInvalidSongs();
		}
	}

	/**
	 * Removes the specified Song from this library, and all albums and playlists
	 * @param pSong the Song to be removed
	 */
	public void removeSong(Song pSong) {
		aSongs.remove(pSong);
		for(Album a: aAlbums) {
			a.removeSong(pSong);
		}
		for(Playlist p: aPlaylists) {
			p.removeSong(pSong);
		}
	}

}
