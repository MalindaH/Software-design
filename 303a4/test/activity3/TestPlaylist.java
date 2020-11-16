package activity3;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;


public class TestPlaylist {
	private final Playlist p1 = new Playlist("playlist 1");
	private final Song s1 = new Song(new File("../files/7rings.mp3"), "Seven Rings", Artist.getArtist("Ariana"), Genre
			.getGenre("POP"));
	private final Playlist p2 = new Playlist("Composite Playlist 2", p1, s1);
	private final Playlist p3 = new Playlist("Composite Playlist 2", p1);

	@Test
	public void testConstructor() {
		List<SongCollection> list1 = getSongCollections(p2);
		assertSame(list1.get(0), p1);
		assertSame(list1.get(1), s1);
		assertEquals(list1.size(), 2);
	}
	
	@Test
	public void testGetSetTitle() {
		p1.setTitle("Playlist-1");
		assertTrue(p1.getTitle().equals("Playlist-1"));
	}

	@Test
	public void testAddUnique_Unique() {
		Album a1 = new Album("Album 1");
		assertSame(p2.addUnique(a1),a1);
	}
	
	@Test
	public void testAddUnique_NotUnique() {
		assertSame(p2.addUnique(p1),p1);
	}

	@Test
	public void testAdd() {
		p2.add(p1);
		List<SongCollection> list1 = getSongCollections(p2);
		assertSame(list1.get(2), p1);
		assertEquals(list1.size(), 3);
	}
	
	@Test
	public void testRemove() {
		p2.remove(p1);
		List<SongCollection> list1 = getSongCollections(p2);
		assertSame(list1.get(0), s1);
		assertEquals(list1.size(), 1);
	}
	
	@Test
	public void testClearContent() {
		p2.clearContent();
		List<SongCollection> list1 = getSongCollections(p2);
		assertEquals(list1.size(), 0);
	}
	
	@Test
	public void testEquals_Itself() {
		assertTrue(p2.equals(p2));
	}
	
	@Test
	public void testEquals_SameAndDifferentClass() {
		assertFalse(p1.equals(p2));
		assertFalse(p3.equals(p2));
		assertFalse(p3.equals(s1));
	}
	
	@Test
	public void testEquals_SameNumberOfContents() {
		p3.add(p1);
		assertFalse(p3.equals(p2));
	}
	
	@Test
	public void testEquals_SameContentsSameTitle() {
		p3.add(s1);
		assertTrue(p3.equals(p2));
	}
	
	@Test
	public void testContentEquals_Itself() {
		assertTrue(p2.contentEquals(p2));
	}
	
	@Test
	public void testContentEquals_SameAndDifferentClass() {
		assertFalse(p2.contentEquals(p1));
		assertFalse(p2.contentEquals(s1));
	}
	
	@Test
	public void testContentEquals_SameNumberOfContents() {
		p3.add(p1);
		assertFalse(p2.contentEquals(p3));
	}
	
	@Test
	public void testContentEquals_SameContents() {
		p3.add(p1);
		p3.add(s1);
		assertTrue(p2.contentEquals(p3));
	}
	
	@Test
	public void testGetRuntime() {
		int x = p2.getRuntime();
		int y = p1.getRuntime() + s1.getRuntime();
		assertEquals(x,y);
	}
	
	@Test
	public void testClone() {
		Playlist p3 = p2.clone();
		assertNotSame(p2, p3);
		List<SongCollection> list1 = getSongCollections(p2);
		List<SongCollection> list2 = getSongCollections(p3);
		assertNotSame(list1, list2); // check deep clone
		assertTrue(list1.equals(list2));
		assertTrue(p2.getTitle().equals(p3.getTitle()));
	}

	// use reflection to access internal states
	@SuppressWarnings("unchecked")
	public List<SongCollection> getSongCollections(Playlist p){
		try {
			Field field = Playlist.class.getDeclaredField("aSongCollections");
			field.setAccessible(true);
			return (List<SongCollection>) field.get(p);
		} catch(ReflectiveOperationException e) {
			fail();
			return null;
		}
	}
}






