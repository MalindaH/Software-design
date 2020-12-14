package activity6;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

public class TestAdd
{
	private Library aLibrary;
	private ListViewPanel aList;
	private Song song1;
	private Song song2;
	private Song song3;
	private Song song4;
	private Song song5;
	private List<Song> songs;
	
	@BeforeEach
	private void setup() {
		final JFXPanel aJFXPanel = new JFXPanel();

		aLibrary = new Library();
		aList = new ListViewPanel();
		aLibrary.addObserver(aList);
		song1 = new Song(new File("files/7rings.mp3"),"song1");
		song2 = new Song(new File("files/Around_the_World.mp3"),"song2");
		song3 = new Song(new File("files/By_the_Way.mp3"),"song3");
		song4 = new Song(new File("files/Californication.mp3"),"song4");
		song5 = new Song(new File("files/Can_t_Stop.mp3"),"song5");
		songs = new ArrayList<>() ;
		songs.add(song1);
		songs.add(song2);
		songs.add(song3);
		songs.add(song4);		
	}
	
	/**
	 * tests whether a song is correctly added to the library and whether the library's observer correctly 
	 * updates to reflect the added song.
	 */
	@Test
	public void testAddSong() {
		aLibrary.addItem(song1);
		assertTrue(aLibrary.containsItem(song1));
		assertFalse(aLibrary.containsItem(song2));
		assertTrue(aList.getView().getItems().contains(song1));
		assertFalse(aList.getView().getItems().contains(song2));
	}

	/**
	 * tests whether songs are correctly added to the library and whether the library's observer correctly 
	 * updates to reflect the added songs.
	 */
	@Test
	public void testAddSongs() {
		for (Song song : songs) {
			aLibrary.addItem(song);
		}
		assertEquals(aLibrary.getPlayables().size(),songs.size());
		assertEquals(aList.getView().getItems().size(),songs.size());
		assertFalse(aLibrary.containsItem(song5));
		assertFalse(aList.getView().getItems().contains(song5));
	}
		
}