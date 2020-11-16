package music;

import java.util.ArrayList;
import java.util.Iterator;

import music.FilterClass.*;
import music.tags.*;

public class Client {
	public static void main(String[] args) {
		// add a few songs to the library
		Library myLibrary = new Library();
		Song song1 = new Song("src/files/Sorry.mp3", "Sorry", "3:14", "Justin");
		Song song2 = new Song("src/files/7rings.mp3", "Seven", "2:10", "Ari");
		Song song3 = new Song("src/files/Holy.m4a", "Holy", "4:14", "Pink Floyd");
		Song song4 = new Song("src/files/Prettyplease.mp3", "Pretty Please", "4:37", "Artist3");
		Song song5 = new Song("src/files/Around_the_World.mp3"); // default title is extracted from file path
		Song song6 = new Song("src/files/Californication.mp3");
		Song song7 = new Song("src/files/7rings.mp3", "7rings", "2:10", "Pink Floyd");
		myLibrary.addSong(song1);
		myLibrary.addSong(song2);
		myLibrary.addSong(song3);
		myLibrary.addSong(song4);
		myLibrary.addSong(song5);
		myLibrary.addSong(song6);
		myLibrary.addSong(song7);

		// modify metadata of a song
		song1.setOptionalTags(133, "Dance/Electronic", "Composer1");
		song2.setOptionalTags(100, "genre", "Composer1");
		song4.setOptionalTags(133, "Dance/Electronic", "Jackson & Galantis");
		song4.addCustomTag("Release Year", 2020);
		song1.setTag(TagType.BPM, 100);
		song1.addCustomTag("tag1", "hello");

		// can iterate through Library/Playlist/Album
		System.out.println("Library/Playlist/Album implement Iterable<Song>:");
		for(Song song: myLibrary) {
			System.out.println(song + " -- Title: "+ song.getTitleOfSong());
		}

		// addSong(), removeSong(), addSongs(), removeSongs(), numOfSongs(), 
		// getAllSongs(), getFirstSong() in Library/Playlist/Album (methods of SongList interface)
		System.out.println("\nSongList interface test:");
		Album myAlbum = new Album("My-Album");
		ArrayList<Song> testList = new ArrayList<Song>();
		testList.add(song3);
		testList.add(song4);
		testList.add(song2);
		myAlbum.addSongs(testList); // addSongs(ArrayList<Song>)
		System.out.println(myAlbum);
		myAlbum.removeSong(song2); // removeSong(Song)
		myAlbum.addSong(song1); // addSong(Song)
		myAlbum.addSong(song5);
		// getFirstSong() and getAllSongs() from Library/Playlist/Album
		System.out.println("After remove and add Song, myAlbum contains: "+myAlbum.getAllSongs());
		System.out.println("First song in myAlbum: "+myAlbum.getFirstSong());
		System.out.println("Number of songs in "+myAlbum.getTitle()+": "+myAlbum.numOfSongs());

		Playlist myPlaylist = new Playlist("My-Playlist");
		myPlaylist.addSong(song1);
		myPlaylist.addSong(song5);
		myPlaylist.addSong(song3);
		myPlaylist.addSong(song4);
		System.out.println(myPlaylist);

		// sort Library/Playlist/Album by any expected/optional/custom tag
		// Note: expected/optional tags are called by `TagType.GENRE`;
		// custom tags are called by `CustomTag.get("name")`
		myPlaylist.sortBy(TagType.TITLE);
		// myPlaylist.sortBy(CustomTag.get("Release Year"));
		System.out.println("After sorting by Title,\n"+myPlaylist);

		System.out.println("Test for filter:");
		// Filter for a title
		FilterByTag titleFilter = new FilterByTag("Sorry", TagType.TITLE);
		System.out.println("Filter myLibrary for a Title: output "+titleFilter.filterCriteria(myLibrary.getAllSongs()));
		for(Song song : titleFilter.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("Title: " + song.getTitleOfSong());
		}

		//Filter for a time
		FilterByTag timeFilter = new FilterByTag("4:14", TagType.TIME);
		System.out.println("Filter myLibrary for a Time: output "+timeFilter.filterCriteria(myLibrary.getAllSongs()));
		for(Song song : timeFilter.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("Time: " + song.getPlayingTime());
		}

		//Filter for an artist
		FilterByTag artistFilter = new FilterByTag("Pink Floyd", TagType.ARTIST);
		System.out.println("Filter myLibrary for an Artist: output "+artistFilter.filterCriteria(myLibrary.getAllSongs()));
		for(Song song : artistFilter.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("Artist: " + song.getArtistOfSong());
		}

		//Filter for BPM
		FilterByTag BPMFilter = new FilterByTag("133", TagType.BPM);
		System.out.println("Filter myLibrary for a BPM: output "+BPMFilter.filterCriteria(myLibrary.getAllSongs()));
		for(Song song : BPMFilter.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("BPM: " + song.getBPMOfSong());
		}

		//Filter for Genre
		FilterByTag genreFilter = new FilterByTag("Dance/Electronic", TagType.GENRE);
		System.out.println("Filter myLibrary for a Genre: "+genreFilter.filterCriteria(myLibrary.getAllSongs()));
		for(Song song : genreFilter.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("Genre: " + song.getGenreOfSong());
		}

		//Filter for Composer
		FilterByTag composerFilter = new FilterByTag("Composer1", TagType.COMPOSER);
		System.out.println("Filter myLibrary for a Composer: "+composerFilter.filterCriteria(myLibrary.getAllSongs()));
		for(Song song : composerFilter.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("Composer: " + song.getComposerOfSong());
		}

		//Filter for anything (test customtag)
		FilterAnyTag anythingFilter = new FilterAnyTag("2020");
		System.out.println("\nFilter myLibrary for any tag: (expected/optional/custom)");
		for(Song song : anythingFilter.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("Any tag: " + song.getTitleOfSong()+" - "+song.getTagValue(CustomTag.get("Release Year")));
		}

		//Filter for anything (test time)
		FilterAnyTag anything2Filter = new FilterAnyTag("3:14");
		for(Song song : anything2Filter.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("Time test any tag "+ song.getTitleOfSong()+" - " + song.getPlayingTime());
		}

		//Filter AND --> 3:14 and Justin
		System.out.println("\nFilter myLibrary for (AND) criteria:");
		FilterByTag timeFilter2 = new FilterByTag("3:14", TagType.TIME);
		FilterAnyTag artistFilter2 = new FilterAnyTag("Justin");
		FilterAND filterAND = new FilterAND(timeFilter2, artistFilter2);
		for(Song song : filterAND.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("AND output: " + song.getTitleOfSong());
		}

		//Filter OR --> 133 BPM or Holy
		System.out.println("\nFilter myLibrary for (OR) criteria:");
		FilterAnyTag titleFilter2 = new FilterAnyTag("133");
		FilterByTag BPMFilter2 = new FilterByTag("Holy", TagType.TITLE);
		FilterOR filterOR = new FilterOR(titleFilter2, BPMFilter2);
		for(Song song : filterOR.filterCriteria(myLibrary.getAllSongs()))
		{
			System.out.println("OR output: " + song.getTitleOfSong());
		}

		// find Song
		System.out.println("Can findSongByArtist(), findSongByTitle(), etc.");
		ArrayList<Song> testsong = myLibrary.findSongByArtist("Artist3");
		System.out.println(testsong.get(0).getTitleOfSong());

		System.out.println("\nGenerating playlist for a particular artist:");
		// Test playlist that should work 
		Playlist myPlaylist1 = null;
		try {
			myPlaylist1 = myLibrary.generatePlaylist("myPlaylist", new FilterByTag("Pink Floyd", TagType.ARTIST));
			if(myPlaylist1 == null) {
				throw new IllegalArgumentException("The playlist is empty!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// use of iterator (equivalent to a foreach loop)
		Iterator<Song> myPlaylistIterator = myPlaylist1.iterator();
		while(myPlaylistIterator.hasNext())
		{
			System.out.println(myPlaylistIterator.next().getTitleOfSong());
		}

		//Test case where myPlaylist returned is null (if filtering yielded no results)
		try {
			Playlist myPlaylist2 = myLibrary.generatePlaylist("myPlaylist", new FilterByTag("Paul McCartney", TagType.ARTIST));
			if(myPlaylist2 == null) {
				throw new IllegalArgumentException("The playlist is empty!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Test cases for Req-3
		System.out.println("\n---Test cases for Req-#3---");
		System.out.println("Play a song: (Playable interface)");
		song7.play();
		System.out.println("\nPlay an album:");
		myAlbum.play();
		System.out.println("\nPlay a playlist:");
		myPlaylist.play();

		System.out.println("\nTest shuffling and unshuffling: (Shuffable interface)");
		System.out.println("Before shuffling:\n"+myPlaylist);
		myPlaylist.shuffle();
		System.out.println("After shuffling:\n"+myPlaylist);
		myPlaylist.shuffle();
		System.out.println("After shuffling again:\n"+myPlaylist);
		myPlaylist.unshuffle();
		System.out.println("After unshuffling:\n"+myPlaylist);
		
		System.out.println("\nTest shuffling and unshuffling for an album:\nAlbum.toString() automatically sort by track number, so shuffling is shown through Album.play()");
		System.out.println("Before shuffling:");
		myAlbum.play();
		System.out.println("\nAfter shuffling:");
		myAlbum.shuffle();
		myAlbum.play();
		System.out.println("\nAfter unshuffling:");
		myAlbum.unshuffle();
		myAlbum.play();

		System.out.println("\nTest isEmpty():");
		Album myAlbum2 = new Album("some album2");
		Playlist myPlaylist2 = new Playlist("myPlaylist2");
		System.out.println("myAlbum is Empty: " + myAlbum.isEmpty());
		System.out.println("myAlbum2 is Empty: " + myAlbum2.isEmpty());
		System.out.println("myPlaylist is Empty: " + myPlaylist.isEmpty());
		System.out.println("myPlaylist2 is Empty: " + myPlaylist2.isEmpty());

		System.out.println("\nTest addToFavorites() and isFavorite():");
		song1.addtToFavorites();
		myAlbum.addtToFavorites();
		System.out.println("song1 is favorite: "+song1.isFavorite());
		System.out.println("myAlbum is favorite: "+myAlbum.isFavorite());
	}
}
