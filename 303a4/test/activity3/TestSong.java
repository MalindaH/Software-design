package activity3;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;

import org.junit.Test;


public class TestSong { 
    
	private final Song aSong = new Song(new File("../files/7rings.mp3"), "Seven Rings", 
			Artist.getArtist("Ariana"), Genre.getGenre("POP"));
	private final Song s1 = new Song(new File("../files/By_the_Way.mp3"), "By the way", 
			Artist.getArtist("Someone"), Genre.getGenre("DANCE"));
	private final Song aCloneSong = aSong.clone();
	
	@Test
	public void testEditTag() {
		s1.editTag("test", "result");
		HashMap<String,String> stuff = getContents(s1);
		assertTrue(stuff.containsKey("test"));
	}
	
	@Test
	public void isValid() {
		assertFalse(new Song(new File(".../something")).isValid());
	}

	@Test
	public void testGetFile() {
		assertEquals(new File("../files/7rings.mp3"), aSong.getFile());
	}
	
	@Test
	public void testGetTitle() {
		assertEquals("Seven Rings", aSong.getTitle());
	}
	
	@Test 
	public void testGetArtist() {
		assertEquals(Artist.getArtist("Ariana"), aSong.getArtist());
	
	}
	@Test
	public void testEquals() {
		assertTrue(s1.equals(s1));
		assertFalse(s1.equals(new String("lol")));
	}
	
	@Test
	public void testGetRuntime() {
		assertEquals(0, aSong.getRuntime());
	}
	
	@Test
	public void testClone() {
		Song s3 = s1.clone();
		assertFalse(s1 == s3);
	}
	
    /**
     * ----------------------------- CLONE TESTS -----------------------------
     */

    /**
     * Tests if clone method exists
     */
   @Test
   public void song_testClone_exists() {
       try {
           Method method = Song.class.getDeclaredMethod("clone");
       } catch (Exception e) {
           fail();
       }
   }

    /**
     * Tests that Song.clone returns a new object
     */
    @Test
    public void song_testClone_differentReference() {
        assertFalse(aSong == aCloneSong);
    }

    /**
     * Tests that a cloned Song is not null
     */
    @Test
    public void song_testClone_notNull() {
        assertNotNull(aCloneSong);
    }

    /**
     * Test that a cloned Song has the same Title
     */
    @Test
    public void song_testClone_sameTitle() {
        assertTrue(aSong.getArtist().equals(aCloneSong.getArtist()));
    }

    /**
     * Test that a cloned Song has the same File
     */
    @Test
    public void song_testClone_sameFile() {
        assertTrue(aSong.getFile().getAbsolutePath()
            .equals(aCloneSong.getFile().getAbsolutePath()));
    }

    /**
     * Test that a cloned Song has the same Duration
     */
    @Test
    public void song_testClone_sameDuration() {
        assertEquals(aSong.getRuntime(), aCloneSong.getRuntime());
    }

    /**
     * Test that a cloned Song has the same Artist
     */
    @Test
    public void song_testClone_sameArtist() {
        assertTrue(aSong.getArtist().equals(aCloneSong.getArtist()));
    }

    /**
     * Test that a cloned Song has the same Genre
     */
    @Test
    public void song_testClone_sameGenre() {
        try {            
            Optional<Genre> aSongGenre = getGenre(aSong);
            Optional<Genre> cloneSongGenre = getGenre(aCloneSong);
            assertTrue(aSongGenre.toString().equals(cloneSongGenre.toString()));
            assertTrue(aSong.getGenre().equals(aCloneSong.getGenre()));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test that a cloned Song has the same Tags
     */
    @Test
    public void song_testClone_sameTags() {
        try {
            HashMap<String, String> aSongTags = getTags(aSong);
            HashMap<String, String> aCloneTags = getTags(aCloneSong);
            assertTrue(aSongTags.equals(aCloneTags));
        } catch (Exception e) {
            fail();
        }
    }


    // use reflection to access internal states
 	@SuppressWarnings("unchecked")
 	public Optional<Genre> getGenre(Song s){
 		try {
 			Field field = Song.class.getDeclaredField("aGenre");
 			field.setAccessible(true);
 			return (Optional<Genre>) field.get(s);
 		} catch(ReflectiveOperationException e) {
 			fail();
 			return null;
 		}
 	}
 	
 	@SuppressWarnings("unchecked")
 	public HashMap<String, String> getTags(Song s){
 		try {
 			Field field = Song.class.getDeclaredField("aTags");
 			field.setAccessible(true);
 			return (HashMap<String, String>) field.get(s);
 		} catch(ReflectiveOperationException e) {
 			fail();
 			return null;
 		}
 	}
 	
 	@SuppressWarnings("unchecked")
	public static HashMap<String, String> getContents(Song song){
		try {
			Field field = Song.class.getDeclaredField("aTags");
			field.setAccessible(true);
			return (HashMap<String,String>) field.get(song);
		}catch(ReflectiveOperationException e) {
			fail();
			return null;
		}
	}
}

