package activity3;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.Assert.*;

class TestRemixSongCollection {

   private static final Song testSongSevenRings = new Song(
            new File("../files/7rings.mp3"),
            "Seven Rings", Artist.getArtist("Ariana"),
            Genre.getGenre("POP")
   );
   private static final Song testSongScarTisue = new Song(
            new File("/activity3/files/Scar_Tissue.mp3"),
            "Scar Tissue", Artist.getArtist("RHCP"),
            Genre.getGenre("Rock")
   );

   @Test
   public void test_setGetRemixType() {
      Album album = new Album("Test Album");
      RemixSongCollection sc = new RemixSongCollection(album,
               RemixSongCollection.RemixType.CLUB);
      assertEquals(RemixSongCollection.RemixType.CLUB, sc.getRemixType());

      sc.setRemixType(RemixSongCollection.RemixType.RADIO);
      assertEquals(RemixSongCollection.RemixType.RADIO, sc.getRemixType());
   }

   @Test
   public void test_play() {
      // TODO: source method does not have implementation yet?
      assert(true);
   }

   @Test
   public void test_duration() {
      // prepare RemixSongCollection
      Album album = new Album("Test Album");
      RemixSongCollection sc = new RemixSongCollection(
               album,
               RemixSongCollection.RemixType.CLUB
      );

      // test duration with no songs
      assertEquals(sc.duration(), 0);

      // test duration with one song
      album.addTrack(1, testSongSevenRings);
      assertEquals(album.duration(), sc.duration());

      // test duration with multiple songs
      album.addTrack(2, testSongScarTisue);
      assertEquals(album.duration(), sc.duration());
   }

   @Test
   public void test_description() {
      Album album = new Album("Test Album");
      RemixSongCollection sc = new RemixSongCollection(
               album,
               RemixSongCollection.RemixType.CLUB
      );
      assertNotNull(sc.description());
   }

   @Test
   public void test_getRuntime() {
      // prepare RemixSongCollection
      Album album = new Album("Test Album");
      RemixSongCollection sc = new RemixSongCollection(
               album,
               RemixSongCollection.RemixType.CLUB
      );

      album.addTrack(1, testSongSevenRings);
      assertEquals(testSongScarTisue.getRuntime(), album.getRuntime());

      album.addTrack(2, testSongScarTisue);
      assertEquals(album.getRuntime(), sc.getRuntime());
   }

   @Test
   public void test_getTitle() {
      String albumName = "Test Album";
      Album album = new Album(albumName);
      RemixSongCollection sc = new RemixSongCollection(album,
               RemixSongCollection.RemixType.CLUB);
      assertEquals(RemixSongCollection.RemixType.CLUB, sc.getRemixType());

      assertEquals(sc.getTitle(),albumName);
   }

   @Test
   public void test_getContents() {
      Album album = new Album("Test Album");
      RemixSongCollection sc = new RemixSongCollection(
               album,
               RemixSongCollection.RemixType.CLUB
      );
      assertEquals(sc.getContents().size(),0);
      assertEquals(sc.getContents(),album.getContents());
   }

   @Test
   public void test_clone1() {
      Album album = new Album("Test Album");
      RemixSongCollection sc = new RemixSongCollection(
               album,
               RemixSongCollection.RemixType.CLUB
      );

      RemixSongCollection sc2 = sc.clone();
      assertTrue(sc.equals(sc2));
      assertFalse(sc == sc2);
   }

   @Test
   public void test_equals1() {
      Album album = new Album("Test Album");
      RemixSongCollection sc = new RemixSongCollection(
               album,
               RemixSongCollection.RemixType.CLUB
      );

      RemixSongCollection sc2 = sc.clone();
      assertTrue(sc.equals(sc2));
   }

}