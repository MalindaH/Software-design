package activity3;

import org.junit.Test;

import activity3.RemixSongCollection.RemixType;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

public class RemixSongCollection_Test {

	private final Playlist p1 = new Playlist("playlist 1");
	private final Song s1 = new Song(new File("../files/7rings.mp3"), "Seven Rings", Artist.getArtist("Ariana"), Genre
			.getGenre("POP"));
	private final Playlist p2 = new Playlist("Composite Playlist 2", p1, s1);


    //Set aPlaylist, aPlaylistClone
	private final RemixSongCollection remixMusic = new RemixSongCollection(p2, RemixType.CLUB);
	private final RemixSongCollection remixMusicClone = remixMusic.clone();
	//private final RemixSongCollection remixMusicClone = new RemixSongCollection(p2, RemixType.CLUB);
    
    /**
     * ----------------------------- CLONE TESTS -----------------------------
     */

     /**
      * Tests if remixMusic have different references
      */
     @Test
     public void bufferMusic_testClone_exists() {
         assertTrue(remixMusic != remixMusicClone);
     }
     
     /**
      * Tests if remixMusic have same remix type
      */
     @Test
     public void bufferMusic_testClone_sameRemixType() {
         assertTrue(remixMusic.getRemixType() == remixMusicClone.getRemixType());
     }
     
     /**
      * Tests if remixMusic have same duration
      */
     @Test
     public void bufferMusic_testClone_sameDuration() {
         assertTrue(remixMusic.duration() == remixMusicClone.duration());
     }
     
     /**
      * Tests if remixMusic have same title
      */
     @Test
     public void bufferMusic_testClone_sameTitle() {
         assertTrue(remixMusic.getTitle().equals(remixMusicClone.getTitle()));
     }
     
     /**
      * Tests if remixMusic have same description
      */
     @Test
     public void bufferMusic_testClone_samedescription() {
         assertTrue(remixMusic.description().equals(remixMusicClone.description()));
     }
  
     /**
      * Tests if remixMusic have same songCollection object
      */
     @Test
     public void bufferMusic_testClone_sameContent() {
    	 List<SongCollection> bufferContent = remixMusic.getContents();
    	 List<SongCollection> bufferCloneContent = remixMusicClone.getContents();
    	 
    	 assertSame(bufferContent.size(), bufferCloneContent.size());
    	 
    	 assertTrue(bufferContent.equals(bufferCloneContent));
     }
}

