package activity6;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.embed.swing.JFXPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRemove
{
	private Library aLibrary;
	private ListViewPanel aList;
	private Song song1;
	private Song song2;
	private Song song3;
	private Playlist list1;
	
	@BeforeEach
	private void setup() {
		final JFXPanel aJFXPanel = new JFXPanel();

		aLibrary = new Library();
		aList = new ListViewPanel();
		aLibrary.addObserver(aList);
		song1 = new Song(new File("files/7rings.mp3"),"song1");
		song2 = new Song(new File("files/Around_the_World.mp3"),"song2");
		song3 = new Song(new File("files/By_the_Way.mp3"),"song3");
		list1 = new Playlist("test");
		aLibrary.addItem(song1);
	}
	
	/**
	 * Ensures the desired item is removed from the list
	 */
	@Test
	public void testSongRemoved() {
		aLibrary.removeItem(song1);
		assertFalse(aLibrary.containsItem(song1));
	}
	
	/**
	 * Ensures the desired item is removed from the list view
	 */
	@Test
	public void testSongRemoved_ViewUpdated() {
		aLibrary.removeItem(song1);
		assertFalse(aList.getView().getItems().contains(song1));
	}
	
	/**
	 * Ensures only the desired item is removed from the list
	 */
	@Test
	public void testSongRemoved_OnlyOneRemoved() {
		aLibrary.addItem(song2);
		aLibrary.addItem(song3);
		List<Playable> difference = new ArrayList<Playable>(aLibrary.getPlayables());
		
		aLibrary.removeItem(song1);
		difference.removeAll(aLibrary.getPlayables());
		
		assertEquals(difference.size(), 1); // ensures only one item was removed. If testSongRemoved passed, we know this must be the desired one.
	}
	
	/**
	 * Ensures only the desired item is removed from the list view
	 */
	@Test
	public void testSongRemoved_OnlyOneRemoved_View() {
		aLibrary.addItem(song2);
		aLibrary.addItem(song1);
		List<Playable> difference = new ArrayList<Playable>(aList.getView().getItems());
		
		aLibrary.removeItem(song1);
		difference.removeAll(aList.getView().getItems());
		
		assertEquals(difference.size(), 1);	// ensures only one item was removed. If testSongRemoved_ViewUpdated passed, we know this must be the desired one.
	}
	
	/**
	 * Ensures the desired playlist is removed from the list (should not be functionally different from testSongRemoved())
	 */
	@Test
	public void testPlaylistRemoved() {
		aLibrary.addItem(list1);
		aLibrary.removeItem(list1);
		assertFalse(aLibrary.containsItem(list1));
	}
	
	/**
	 * Ensures the desired playlist is removed from the list view (should not be functionally different from testSongRemoved_ViewUpdated())
	 */
	@Test
	public void testPlaylistRemoved_ViewUpdated() {
		aLibrary.addItem(list1);
		aLibrary.removeItem(list1);
		assertFalse(aList.getView().getItems().contains(list1));
	}
	
}