package music;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {
	public static void main(String[] args) {
		// add a few songs to the library
    	Library myLibrary = new Library();
    	// Song should only be initialized using Library.addSong() because 
    	// this method includes handling of exception
    	Song song1 = myLibrary.addSong("src/files/Sorry.mp3");
    	Song song2 = myLibrary.addSong("src/files/7rings.mp3");
    	Song song3 = myLibrary.addSong("src/files/Holy.mp3");
    	Song song4 = myLibrary.addSong("src/files/Californication.mp3");
    	Song song5 = myLibrary.addSong("src/files/Prettyplease.mp3");
    	Song song6 = myLibrary.addSong("src/files/Notitle.mp3"); // invalid song
    	Song song7 = myLibrary.addSong("src/files/Ooops.mp3"); // invalid song
//    	System.out.println("Songs: "+myLibrary.getSongs());
    	
    	// modify metadata of a song
    	Song song = myLibrary.getSongs().get(4);
    	song.setMetadata("Pretty Please", "3:14", "Jackson Wang");
    	song.setOptionalTags(133, "Dance/Electronic", "Jackson & Galantis");
    	song.addCustomTag("Release Year", 2020);
    	System.out.println(song.getMetadata());

    	// add an album to the library, Album should only be initialized using Library.addAlbum()
    	Album myAlbum = myLibrary.addAlbum("My-album-1");
    	// add and remove a song / an arraylist of songs
    	ArrayList<Song> someSongs1 = new ArrayList<>();
    	someSongs1.add(song1);
    	someSongs1.add(song2);
    	someSongs1.add(song6);
    	myAlbum.addSongsToAlbum(someSongs1);
    	myAlbum.addSongToAlbum(song5);
    	System.out.println(myAlbum);
//    	myAlbum.removeInvalidSongs();
    	System.out.println("------Removed song " +myAlbum.removeSong(song2)+" from myAlbum------");
    	System.out.println(myAlbum);
    	
    	// add a playlist to the library, Playlist should only be initialized using Library.addPlaylist()
    	Playlist myPlaylist = myLibrary.addPlaylist("my-playlist-1");
    	// add and remove a song / an arraylist of songs
    	ArrayList<Song> someSongs2 = new ArrayList<>();
    	someSongs2.add(song3);
    	someSongs2.add(song5);
    	someSongs2.add(song7);
    	myPlaylist.addSongsToPlaylist(someSongs2);
    	myPlaylist.addSongToPlaylist(song2);
    	myPlaylist.removeInvalidSongs();
    	System.out.println(myPlaylist);
    	
    	// sort playlist by title, time, or artist (make sure the song's metadata were set)
    	song2.setMetadata("7 Rings", "3:20", "Ariana Grande");
    	song3.setMetadata("Holy", "2:50", "Justin Bieber");
    	myPlaylist.sortByTitle();
    	System.out.println("Sorted by title:\n"+myPlaylist); 
    	myPlaylist.sortByTime();
    	System.out.println("Sorted by time:\n"+myPlaylist);
    	myPlaylist.sortByArtist();
    	System.out.println("Sorted by artist:\n"+myPlaylist);
    	
    	// add a few more albums to myLibrary
		// BSSM Album
		Song giveItAway = myLibrary.addSong("src/files/Give_it_Away.mp3");
		giveItAway.setMetadata("giveItAway", "0", "Red Hot Chili Peppers");
		
		Song underTheBridge = myLibrary.addSong("src/files/Under_the_Bridge.mp3");
		underTheBridge.setMetadata("aroundTheWorld", "0", "Red Hot Chili Peppers");
		
		ArrayList<Song> bssmList = new ArrayList<Song>();
		bssmList.add(giveItAway);
		bssmList.add(underTheBridge);
		
		myLibrary.addAlbum("BSSM","Red Hot Chili Peppers", bssmList);
		
		
		//BTW Album
		Song byTheWay = myLibrary.addSong("src/files/By_the_Way.mp3");
		byTheWay.setMetadata("byTheWay", "0", "Red Hot Chili Peppers");
		
		Song cantStop = myLibrary.addSong("src/files/Can't_Stop.mp3");
		cantStop.setMetadata("cantStop", "0", "Red Hot Chili Peppers");
		
		ArrayList<Song> btwList = new ArrayList<Song>();
		btwList.add(byTheWay);
		btwList.add(cantStop);
		
		myLibrary.addAlbum("BTW","Red Hot Chili Peppers", btwList);
		
		
		//CAL Album
		Song aroundTheWorld = myLibrary.addSong("src/files/Around_the_World.mp3");
		aroundTheWorld.setMetadata("aroundTheWorld", "0", "Red Hot Chili Peppers");
		
		Song scarTissue = myLibrary.addSong("src/files/Scar_Tissue.mp3");
		scarTissue.setMetadata("scarTissue", "0", "Red Hot Chili Peppers");
		
		Song californication = myLibrary.addSong("src/files/Californication.mp3");
		californication.setMetadata("californication", "0", "Red Hot Chili Peppers");
		
		ArrayList<Song> calList = new ArrayList<Song>();
		calList.add(aroundTheWorld);
		calList.add(scarTissue);
		calList.add(californication);
		
		myLibrary.addAlbum("CAL","Red Hot Chili Peppers", calList);
		
		// all songs are now in the library
		System.out.println("---------print all songs in library------------");
		for(Song cur: myLibrary.getSongs()) {
			System.out.println(cur);
		}
		
		// all albums are now in the library
		System.out.println("\n-----------print all albums in library----------");
		for(Album cur: myLibrary.getAlbums()) {
			System.out.println(cur);
		}
		
		// can removeSong() and removeInvalidSongs() from the library and all albums/playlists containing the song
		System.out.println("---------removeSong() and removeInvalidSongs()------------");
		System.out.println("Number of songs:" + myLibrary.getSongs().size());
		myLibrary.removeSong(byTheWay);
		myLibrary.removeInvalidSongs();
		System.out.println("-------after removing invalid songs--------");
		System.out.println("Number of songs:" + myLibrary.getSongs().size());

    }
}
