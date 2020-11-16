package activity3;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

public class PlayCountSongCollection_Test {
	
    //Initialize playlist
	private final Playlist p1 = new Playlist("playlist 1");
	private final Song s1 = new Song(new File("../files/7rings.mp3"), "Seven Rings", Artist.getArtist("Ariana"), Genre
			.getGenre("POP"));
	private final Playlist p2 = new Playlist("Composite Playlist 2", p1, s1);

    //Set aPlaylist, aPlaylistClone
	private final PlayCountSongCollection countMusic = new PlayCountSongCollection(p2);
	private final PlayCountSongCollection countMusicClone = countMusic.clone();
    
    
    /**
     * ----------------------------- CLONE TESTS -----------------------------
     */

     /**
      * Tests if bufferMusic have different references
      */
     @Test
     public void countMusic_testClone_exists() {
         assertTrue(countMusic != countMusicClone);
     }
     
     /**
      * Tests if bufferMusic have same count number
      */
     @Test
     public void countMusic_testClone_sameCount() {
    	 Player aPlayer = new Player();
    	 
    	 countMusic.play(aPlayer);
    	 countMusicClone.play(aPlayer);
    	 
    	 countMusic.play(aPlayer);
    	 countMusicClone.play(aPlayer);
    	 
    	 countMusic.play(aPlayer);
    	 //countMusicClone.play(aPlayer);
    	 
         assertTrue(countMusic.getPlayCount() != countMusicClone.getPlayCount());
     }
     
     /**
      * Tests if bufferMusic have same duration
      */
     @Test
     public void countMusic_testClone_sameDuration() {
         assertTrue(countMusic.duration() == countMusicClone.duration());
     }
     
     /**
      * Tests if bufferMusic have same title
      */
     @Test
     public void countMusic_testClone_sameTitle() {
         assertTrue(countMusic.getTitle().equals(countMusicClone.getTitle()));
     }
     
     /**
      * Tests if bufferMusic have same songCollection object
      */
     @Test
     public void countMusic_testClone_sameContent() {
    	 List<SongCollection> bufferContent = countMusic.getContents();
    	 List<SongCollection> bufferCloneContent = countMusicClone.getContents();
    	 assertTrue(bufferContent.size() == bufferCloneContent.size());
    	 
    	 assertTrue(bufferContent.equals(bufferCloneContent));
     }
}

