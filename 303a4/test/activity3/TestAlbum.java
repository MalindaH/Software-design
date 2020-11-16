package activity3;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAlbum {
	private final Album a1 = new Album("Album 1");
	private final Song s1 = new Song(new File("../files/7rings.mp3"), "Seven Rings", Artist.getArtist("Ariana"), Genre
			.getGenre("POP"));
	private final Album a2 = new Album("Album 2");
	private final Song s2 = new Song(new File("../files/7r.mp3"), "S Rings", Artist.getArtist("Aana"), Genre
			.getGenre("P"));
	private final Artist a = Artist.getArtist("Shakira");

	@BeforeEach
	public void setup() {
		a1.addTrack(1, s1);
	}

	@Test
	public void testGetRuntime() {
		assertEquals(a1.getRuntime(),s1.getRuntime());
	}

	@Test
	public void testGetContents() {
		assertEquals(a1.getContents(), getSongCollections(a1));
	}

	// use reflection to access internal states
	@SuppressWarnings("unchecked")
	public List<SongCollection> getSongCollections(Album a){
		try {
			Field field = Album.class.getDeclaredField("aTracks");
			field.setAccessible(true);
			Map<Integer, Song> map = (Map<Integer, Song>) field.get(a);
			return new ArrayList<>(map.values());
		} catch(ReflectiveOperationException e) {
			fail();
			return null;
		}
	}
	@Test
	public void testConstructor() {
		List<SongCollection> list1 = getSongCollections(a1);
		assertSame(list1.get(0), s1);
		assertSame("Album 1", a1.getTitle());
	}
	@Test
	public void testGetTitle() {
		assertEquals("Album 1",a1.getTitle());
	}

	@Test
	public void testAddTrack() {
		assertEquals(1,a1.getContents().size());
	}

	@Test
	public void testEquals_False() {
		a1.setArtist(a);
		a2.setArtist(Artist.getArtist("50cent"));
		assertFalse(a1.equals(a2));
		assertFalse(a1.equals(new String("lol")));
		assertFalse(a1.getTitle().equals(a2.getTitle()));
		a1.addTrack(1,s1);
		a2.addTrack(1,s2);
		assertFalse(a1.equals(a2));
		a2.setArtist(a);
		assertFalse(a1.equals(a2));
	}
	
	@Test
	public void testEquals_True() {
		assertTrue(a1.equals(a1));
		a2.setArtist(a);
		a2.addTrack(1,s2);
		Album a3 = new Album("Album 2");
		a3.setArtist(a);
		assertFalse(a3.equals(a2));
		a3.addTrack(1,s2);
		assertTrue(a3.equals(a2)); // different Albums, same tracks, same title, same Artist
	}

	@Test
	public void testClearContent() {
		a1.clearContent();
		assertEquals(0,a1.getContents().size());
	}

	@Test
	public void testClone() {
		Album a3 = a1.clone();
		assertNotSame(a1, a3);
		List<SongCollection> list1 = getSongCollections(a1);
		List<SongCollection> list2 = getSongCollections(a3);
		assertNotSame(list1, list2);
		assertTrue(list1.equals(list2));
		assertTrue(a1.getTitle().equals(a3.getTitle()));
	}

	@Test
	public void testContains() {
		a1.addTrack(1, s1);
		Collection<Song> list1 = getTracks(a1).values();
		List<SongCollection> list2 = a1.getContents();
		assertTrue(list1.contains(s1));
		assertTrue(list2.contains(s1));
		assertTrue(a1.contains(s1));
		assertFalse(list1.contains(s2));
		assertFalse(list2.contains(s2));
		assertFalse(a1.contains(s2));
	}

	@Test
	public void testDuration() {
		assertEquals(0,a1.duration());
	}

	// use reflection to access internal states
	@SuppressWarnings("unchecked")
	public Map<Integer, Song> getTracks(Album a){
		try {
			Field field = Album.class.getDeclaredField("aTracks");
			field.setAccessible(true);
			return (Map<Integer, Song>) field.get(a);
		} catch(ReflectiveOperationException e) {
			fail();
			return null;
		}
	}
}



