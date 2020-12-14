package activity6;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStatus
{
	private Library aLibrary;
	private StatusBarView aStatusBarView;
	private Song song1;
	private Song song2;
	private Song song3;
	private Song song4;
	private List<Song> songs;
	
	@BeforeEach
	private void setup() {
		aLibrary = new Library();
		aStatusBarView = new StatusBarView();
		aLibrary.addObserver(aStatusBarView);
		song1 = new Song(new File("files/7rings.mp3"),"song1");
		song2 = new Song(new File("files/Around_the_World.mp3"),"song2");
		song3 = new Song(new File("files/By_the_Way.mp3"),"song3");
		song4 = new Song(new File("files/Californication.mp3"),"song4");
		songs = new ArrayList<>() ;
		songs.add(song1);
		songs.add(song2);
		songs.add(song3);
		songs.add(song4);
	}
	
	/**
	 * @return the String that's printed to console when pPlayable is added
	 */
	private String songAdded_toString(Playable pPlayable) {
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		
		aLibrary.addItem(pPlayable);
		String result = outputStreamCaptor.toString().trim();
		System.setOut(standardOut);
		return result;
	}
	
	/**
	 * @return the String that's printed to console when pPlayable is removed
	 */
	private String songRemoved_toString(Playable pPlayable) {
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
	
		aLibrary.removeItem(pPlayable);
		String result = outputStreamCaptor.toString().trim();
		System.setOut(standardOut);
		return result;
	}
	
	/**
	 * @return the String that's printed to console when StatusBarView.showMessage() is called
	 */
	private String showMessage_toString(String pString) {
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		
		aStatusBarView.showMessage(pString);
		String result = outputStreamCaptor.toString().trim();
		System.setOut(standardOut);
		return result;
	}
	
	/**
	 * Test correct message gets printed to console and status bar when a Song is added
	 */
	@Test
	public void testSongAdded() {
		for (Song song : songs) {
			String expected = "Added " + song.description();
			String result = songAdded_toString(song);
			assertEquals(expected, result);
			assertEquals(expected, aStatusBarView.getBar().getText());
		}	
	}
	
	/**
	 * Test correct message gets printed to console and status bar when a Song is removed
	 */
	@Test
	public void testSongRemoved() {
		for (Song song : songs) {
			aLibrary.addItem(song);
			String expected = "Removed " + song.description();
			String result = songRemoved_toString(song);
			assertEquals(expected, result);
			assertEquals(expected, aStatusBarView.getBar().getText());
		}
		// removing a song that isn't there
		String result = songRemoved_toString(song1);
		assertEquals("",result);
	}
	
	/**
	 * Test correct message gets printed to console and status bar when ShowMessage() is called
	 */
	@Test
	public void testShowMessage() {
		String expected = "This is a test!";
		String result = showMessage_toString(expected);
		assertEquals(expected, result);
		assertEquals(expected, aStatusBarView.getBar().getText());
	}
}
