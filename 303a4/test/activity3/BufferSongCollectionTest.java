package activity3;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

public class BufferSongCollectionTest {
	
	private final Playlist p1 = new Playlist("playlist 1");
	private final Song s1 = new Song(new File("../files/7rings.mp3"), "Seven Rings", Artist.getArtist("Ariana"), Genre
			.getGenre("POP"));
	private final Playlist p2 = new Playlist("Composite Playlist 2", p1, s1);


    //Set aPlaylist, aPlaylistClone
	private final BufferSongCollection bufferMusic = new BufferSongCollection(p2, 10);
	private final BufferSongCollection bufferMusicClone = bufferMusic.clone();
	//private final BufferSongCollection bufferMusicClone = new BufferSongCollection(p2, 10);
    
    /**
     * ----------------------------- CLONE TESTS -----------------------------
     */

     /**
      * Tests if bufferMusic have different references
      */
     @Test
     public void bufferMusic_testClone_exists() {
         assertTrue(bufferMusic != bufferMusicClone);
     }
     
     /**
      * Tests if bufferMusic have same buffer length
      */
     @Test
     public void bufferMusic_testClone_sameBuffer() {
         assertTrue(bufferMusic.getBuffer() == bufferMusicClone.getBuffer());
     }
     
     /**
      * Tests if bufferMusic have same duration
      */
     @Test
     public void bufferMusic_testClone_sameDuration() {
         assertTrue(bufferMusic.duration() == bufferMusicClone.duration());
     }
     
     /**
      * Tests if bufferMusic have same title
      */
     @Test
     public void bufferMusic_testClone_sameTitle() {
         assertTrue(bufferMusic.getTitle().equals(bufferMusicClone.getTitle()));
     }
     
     /**
      * Tests if bufferMusic have same description
      */
     @Test
     public void bufferMusic_testClone_samedescription() {
         assertTrue(bufferMusic.description().equals(bufferMusicClone.description()));
     }
  
     /**
      * Tests if bufferMusic have same songCollection object
      */
     @Test
     public void bufferMusic_testClone_sameContent() {
    	 List<SongCollection> bufferContent = bufferMusic.getContents();
    	 List<SongCollection> bufferCloneContent = bufferMusicClone.getContents();
    	 
    	 assertSame(bufferContent.size(), bufferCloneContent.size());
    	 
    	 assertTrue(bufferContent.equals(bufferCloneContent));
     }
     
     
}

