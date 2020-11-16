package client;

import activity3.*;

public class Client
{
	public static void main (String[] args) {
		test1();

		test23();

		test4();
	}

	protected static void test1()
	{
		System.out.println("----Test task 1: support partial info in Song and Album");
		//Test constructors of Album
		Artist artist1 = SongInfoFactory.getArtist("Elias");
		Artist artist2 = SongInfoFactory.getArtist("Justin Bieber");
		Artist artist3 = SongInfoFactory.getArtist("Weeknd");
		Album a1 = new Album("After Hours");
		Album a2 = new Album("After Hours", "Weeknd");
		Album a3 = new Album("XX", artist1);
		
		//Get testing
		boolean getTest = true;
		if (!a1.getTitle().equalsIgnoreCase("After Hours"))
		{
			getTest = false;
		}
		if (!a3.getArtist().equals(artist1))
		{
			getTest = false;
		}
		if (getTest)
		{
		System.out.println("Get methods work in Album");
		}
		//Set testing
		a1.setArtist(artist2);
		boolean setTest = true;
		if (!a1.getArtist().equals(artist2))
		{
			setTest = false;
		}
		a1.setTitle("After Hours");
		if (!a1.getTitle().equalsIgnoreCase("After Hours"))
		{
			setTest = false;
		}
		a1.setArtist("Weeknd");
		if (!a1.getArtist().equals(artist3))
		{
			setTest = false;
		}
		if (setTest)
		{
		System.out.println("Set methods work in Album");
		}
	}
	
	protected static void test23() {
		// task 2: unique artist and genre
		System.out.println("\n----Test task 2: unique Artist and Genre");
		Artist artist1 = SongInfoFactory.getArtist("Elias");
		Artist artist2 = SongInfoFactory.getArtist("Elias");
		if(artist1 == artist2) {
			System.out.println("Unique Artist works");
		}
		artist1.addAlternativeName("Person");
		Artist artist3 = SongInfoFactory.getArtist("person");
		if(artist1.equals(artist3)) {
			System.out.println("Artist.equals() works: equal if alternative name is the same");
		}
		
		Genre genre1 = SongInfoFactory.getGenre("metal");
		Genre genre2 = SongInfoFactory.getGenre("Metal");	
		if(genre1 == genre2) {
			System.out.println("Unique Genre works");
		}
		
		Artist artist4 = SongInfoFactory.getArtist("Jackson");
		Artist artist5 = SongInfoFactory.getArtist("Ariana");
		artist5.addAlternativeName("Ariana Grande");
		artist4.addAlternativeName("Jackson Wang");
		Genre genre3 = SongInfoFactory.getGenre("pop");
		genre3.addSynonym("POP music");
		Genre genre4 = SongInfoFactory.getGenre("dance");
		
		// task3: library lifecycle
		System.out.println("\n----Test task 3: Library lifecycle");
		Song song1 = new Song("src/files/Sorry.mp3");
		Song song2 = new Song("src/files/7rings.mp3");
		Song song3 = new Song("src/files/Holy.m4a");
		Song song4 = new Song("src/files/Prettyplease.mp3");
		Song song5 = new Song("src/files/Around_the_World.mp3");
		Song song6 = new Song("src/files/Californication.mp3");

		song4.setGenre("dance");
		if(song4.getGenre().equals(genre4)) {
			System.out.println("Song.setGenre(String) and duplication check work");
		}
		
		song1.setTitle("Sorry");
		song2.setTitle("7 Rings");
		song3.setTitle("Holy");
		song4.setTitle("Pretty Please");
		song5.setTitle("Around The World");
		song6.setTitle("Cali");
		song1.setArtist(artist1);
		song1.setGenre(genre1);
		song4.setArtist(artist4);
		
		Library.addSong(song1);
		Library.addSong(song2);
		Library.addSong(song3);
		Playlist p1 = new Playlist("my playlist 1");
		Album a1 = new Album("my Album 1");
		p1.addSong(song4);
		p1.addSong(song2);
		a1.addTrack(song5);
		a1.addTrack(song6);
		a1.addTrack(song1);
		Library.addAlbum(a1);
		Library.addPlaylist(p1);
		
		// print() methods in Library, Album, Playlist
//		System.out.println(p1.print());
		
		for(Song cur: Library.getSongs()) {// deep copy of all songs
			if(cur==song1 || cur==song4) {
				System.out.println("Library.getSongs() deep copy failed");
			}
		}
		System.out.println("Library addSong(), addAlbum(), addPlaylist(), getSongs() deep copy worked");
		
		// remove Album and delete all songs of the Album
		Library.removeAlbum(a1, true);
		for(Song cur: Library.getSongs()) {
			if(cur.equals(song6, Song.SongCriteria.FILE)) {
				System.out.println("Library.removeAlbum() with deleting all songs of Album failed");
			}
		}
		System.out.println("Library.removeAlbum() with deleting all songs of Album worked");
		
		// remove Playlist and keep all songs
		Library.removePlaylist(p1, false);
		for(Song cur: Library.getSongs()) {
			if(cur.equals(song4, Song.SongCriteria.FILE)) {
				System.out.println("Library.removePlaylisy() without deleting all songs of Playlist worked");
			}
		}
		
		// Library backup() and restore()
		System.out.println("\nbackup() and restore() checks:\nBefore backup(): isBackedup() = "+Library.isBackedup());
		Library.backup();
		System.out.println("After backup(): isBackedup() = "+Library.isBackedup());
		Library.addAlbum(a1);
		System.out.println("After addAlbum(): isBackedup() = "+Library.isBackedup());
		Library.restore();
		System.out.println("Before restore(): isBackedup() = "+Library.isBackedup());
	}
	
	protected static void test4(){
		System.out.println("\n----Test task 4: duplicate check");

		// Song equality
		Song s1 = new Song("src/files/Sorry.mp3");
		Song s2 = new Song("src/files/Sorry.mp3");
		Song s3 = new Song("src/files/Sorry.mp3", "Sorry", 4, "Justin Bieber", "Pop");
		Song s4 = new Song("src/files/7rings.mp3", "7 Rings", 3, "Ariana", "Pop");
		Song s5 = new Song("src/files/7rings.m4a", "7 Rings", 3, "Ariana", "Pop");
		Song s6 = new Song("src/files/7rings.m4a");

		boolean songTestSuccess = true;

		// File only: same file, equal
		if(!s1.equals(s2, Song.SongCriteria.FILE)){
			System.out.println("Failed Song equality by file 1");
			songTestSuccess = false;
		}
		// File only: diff file, not equal
		if(s4.equals(s5, Song.SongCriteria.FILE)){
			System.out.println("Failed Song equality by file 2");
			songTestSuccess = false;
		}

		// Title/Artist: both present, equal
		if(!s4.equals(s5, Song.SongCriteria.TITLEARTIST)){
			System.out.println("Failed Song equality by fields 1");
			songTestSuccess = false;
		}
		// T/A: both present, not equal
		if(s3.equals(s4, Song.SongCriteria.TITLEARTIST)){
			System.out.println("Failed Song equality by fields 2");
			songTestSuccess = false;
		}
		// T/A: both absent, equal
		if(!s1.equals(s6, Song.SongCriteria.TITLEARTIST)){
			System.out.println("Failed Song equality by fields 3");
			songTestSuccess = false;
		}
		// T/A: one absent one missing, not equal
		s1.setArtist("Justin Bieber");
		s2.setTitle("Sorry");
		if(s1.equals(s2, Song.SongCriteria.TITLEARTIST)){
			System.out.println("Failed Song equality by fields 4");
			songTestSuccess = false;
		}

		if(songTestSuccess){
			System.out.println("Passed all Song equality tests!");
		}
		// End Song equality

		// Album equality: using File song equality only
		Album a1 = new Album("Jukebox", "Mac Ayres");
		a1.addTrack(1, s1);a1.addTrack(3, s2);
		Album a2 = new Album("Caprisun");
		a2.addTrack(1, s4);a2.addTrack(2, s5);
		Album a3 = new Album("Ology");
		a3.addTrack(1, s2);a3.addTrack(5, s1);
		Album a4 = new Album("Jukebox", "Mac Ayres");
		a4.addTrack(1, s2);a4.addTrack(2, s1);
		Album a5 = new Album("Jukebox", "Mac Ayres");
		a5.addTrack(1, s1);a5.addTrack(3, s2);
		Album a6 = new Album("Caprisun", "Kool Aid Man");
		a6.addTrack(1, s1);a6.addTrack(3, s2);

		boolean albumTestSuccess = true;

		// Content: diff songs, not equal
		if(a1.equals(a2, SongListCriteria.CONTENT, Song.SongCriteria.FILE)){
			System.out.println("Failed Album content equality 1");
			albumTestSuccess = false;
		}
		// Content: same songs, diff order, equal
		if(!a1.equals(a3, SongListCriteria.CONTENT, Song.SongCriteria.FILE)){
			System.out.println("Failed Album content equality 2");
			albumTestSuccess = false;
		}

		// Total: same fields, same songs, diff order, not equal
		if(a1.equals(a4, SongListCriteria.TOTAL, Song.SongCriteria.FILE)){
			System.out.println("Failed Album total equality 1");
			albumTestSuccess = false;
		}
		// Total: same fields, same songs, same order, equal
		if(!a1.equals(a5, SongListCriteria.TOTAL, Song.SongCriteria.FILE)){
			System.out.println("Failed Album total equality 2");
			albumTestSuccess = false;
		}
		// Total: diff fields, same song, same order, not equal
		if(a5.equals(a6, SongListCriteria.TOTAL, Song.SongCriteria.FILE)){
			System.out.println("Failed Album total equality 3");
			albumTestSuccess = false;
		}

		if(albumTestSuccess){
			System.out.println("Passed all Album equality tests!");
		}
		// End Album equality

		// Playlist equality: using t/a song equality only
		Playlist p1 = new Playlist("Workout Mix");
		p1.addSong(s1);p1.addSong(s5);p1.addSong(s4);
		Playlist p2 = new Playlist("Workout Mix");
		p2.addSong(s3);p2.addSong(s2);
		Playlist p3 = new Playlist("Workout Mix");
		p3.addSong(s4);p3.addSong(s1);p3.addSong(s5);
		Playlist p4 = new Playlist("Sad boy hours");
		p4.addSong(s4);p4.addSong(s1);p4.addSong(s5);
		Playlist p5 = new Playlist("Workout Mix");
		p5.addSong(s3);p5.addSong(s2);

		boolean playlistTestSuccess = true;

		// Content: diff songs, not equal
		if(p1.equals(p2, SongListCriteria.CONTENT, Song.SongCriteria.TITLEARTIST)){
			System.out.println("Failed Playlist content equality 1");
			playlistTestSuccess = false;
		}
		// Content: same songs, diff order, equal
		if(!p1.equals(p3, SongListCriteria.CONTENT, Song.SongCriteria.TITLEARTIST)){
			System.out.println("Failed Playlist content equality 2");
			playlistTestSuccess = false;
		}

		// Total: same title, same songs, diff order, not equal
		if(p1.equals(p3, SongListCriteria.TOTAL, Song.SongCriteria.TITLEARTIST)) {
			System.out.println("Failed Playlist total equality 1");
			playlistTestSuccess = false;
		}
		// Total: same title, same songs, same order, equal
		if(!p2.equals(p5, SongListCriteria.TOTAL, Song.SongCriteria.TITLEARTIST)){
			System.out.println("Failed Playlist total equality 2");
			playlistTestSuccess = false;
		}
		// Total: diff title, same song, same order, not equal
		if(p3.equals(p4, SongListCriteria.TOTAL, Song.SongCriteria.TITLEARTIST)){
			System.out.println("Failed Playlist total equality 3");
			playlistTestSuccess = false;
		}

		// Content: same songs, diff duplicates, not equal
		p5.addSong(s3);
		Playlist p6 = new Playlist("Workout Mix");
		p6.addSong(s3);p6.addSong(s2);p6.addSong(s2);
		if(p5.equals(p6, SongListCriteria.CONTENT,Song.SongCriteria.TITLEARTIST)) {
			System.out.println("Failed Playlist content equality 3");
			playlistTestSuccess = false;
		}

		if(playlistTestSuccess){
			System.out.println("Passed all Playlist equality tests!");
		}
		// End Playlist equality
	}
}
