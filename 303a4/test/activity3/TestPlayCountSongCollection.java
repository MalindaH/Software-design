
package activity3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.fail;
import java.io.File;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayCountSongCollection {
    private Song aSong = new Song(new File("/activity3/files/Scar_Tissue.mp3"), "Scar Tissue", Artist.getArtist("RHCP"),
            Genre.getGenre("Rock"));
    private Playlist aPlaylist = new Playlist("playlist");
    private Album aAlbum = new Album("album");
    private Song s1 = new Song(new File("/activity3/files/Prettyplease.mp3"), "Pretty Please",
            Artist.getArtist("Jackson Wang"), Genre.getGenre("Dance"));

    private Song s2 = new Song(new File("/activity3/files/Sorry.mp3"), "Sorry", Artist.getArtist("Justin Bieber"),
            Genre.getGenre("Pop"));

    private PlayCountSongCollection aPlayCountSong;
    private PlayCountSongCollection aPlayCountAlbum;
    private PlayCountSongCollection aPlayCountPlaylist;

    @BeforeEach
    public void setUp() {

        aAlbum.addTrack(1, s1);

        aPlaylist.add(s1);
        aPlaylist.add(s2);
        aPlaylist.add(aAlbum);

        aPlayCountSong = new PlayCountSongCollection(aSong);
        aPlayCountAlbum = new PlayCountSongCollection(aAlbum);
        aPlayCountPlaylist = new PlayCountSongCollection(aPlaylist);

    }

    @Test
    public void testDescription_Song() {

        String description_s = aPlayCountSong.description();

        assertTrue(aSong.description().equals(description_s) && aPlayCountSong.getPlayCount() == 1);

    }

    @Test
    public void testDescription_Album() {
        String description_a = aPlayCountAlbum.description();

        assertTrue(aAlbum.description().equals(description_a) && aPlayCountAlbum.getPlayCount() == 1);

    }

    @Test
    public void testDescription_Playlist() {

        String description_p = aPlayCountPlaylist.description();

        assertTrue(aPlaylist.description().equals(description_p) && aPlayCountPlaylist.getPlayCount() == 1);

    }

    @Test
    public void testClone_Song() {

        PlayCountSongCollection clone = aPlayCountSong.clone();

        assertTrue(clone.getPlayCount() == aPlayCountSong.getPlayCount()
                && clone.getContents().equals(aPlayCountSong.getContents()));

    }

    @Test
    public void testClone_Album() {
        PlayCountSongCollection clone = aPlayCountAlbum.clone();

        assertTrue(clone.getPlayCount() == aPlayCountAlbum.getPlayCount()
                && clone.getContents().equals(aPlayCountAlbum.getContents()));
    }

    @Test
    public void testClone_Playlist() {
        PlayCountSongCollection clone = aPlayCountPlaylist.clone();

        assertTrue(clone.getPlayCount() == aPlayCountPlaylist.getPlayCount()
                && clone.getContents().equals(aPlayCountPlaylist.getContents()));
    }

    @Test
    public void testEquals_Song_True() {
        PlayCountSongCollection playCount = new PlayCountSongCollection(aSong);
        assertTrue(playCount.equals(aPlayCountSong));
        assertTrue(aPlayCountSong.equals(aPlayCountSong));
    }

    @Test
    public void testEquals_Album_True() {
        PlayCountSongCollection playCount = new PlayCountSongCollection(aAlbum);

        assertTrue(playCount.equals(aPlayCountAlbum));
        assertTrue(aPlayCountAlbum.equals(aPlayCountAlbum));

    }

    @Test
    public void testEquals_Playlist_True() {
        PlayCountSongCollection playCount = new PlayCountSongCollection(aPlaylist);

        assertTrue(playCount.equals(aPlayCountPlaylist));
        assertTrue(aPlayCountPlaylist.equals(aPlayCountPlaylist));

    }

    @Test
    public void testEquals_False_byClass() {

        assertFalse(aPlayCountSong.equals(aPlayCountAlbum));
        assertFalse(aPlayCountSong.equals(aPlayCountPlaylist));
        assertFalse(aPlayCountPlaylist.equals(aPlayCountAlbum));

    }

    @Test
    public void testEquals_False_bySongCollections() {

        // same playcount = 0 but different SongCollections of same concrete type
        assertFalse(aPlayCountSong.equals(new PlayCountSongCollection(s1)));
        assertFalse(aPlayCountPlaylist.equals(new PlayCountSongCollection(new Playlist("another playlist"))));
        assertFalse(aPlayCountAlbum.equals(new PlayCountSongCollection(new Album("another album"))));
    }

    @Test
    public void testEquals_Song_False_byPlayCount() {

        Song s = new Song(new File("/activity3/files/Scar_Tissue.mp3"), "Scar Tissue", Artist.getArtist("RHCP"),
                Genre.getGenre("Rock"));
        PlayCountSongCollection pcs = new PlayCountSongCollection(s);

        try {

            Field field = PlayCountSongCollection.class.getDeclaredField("aPlayCount");
            field.setAccessible(true);
            field.set(aPlayCountSong, 2);

        } catch (ReflectiveOperationException e) {

            fail();
        }

        assertFalse(pcs.equals(aPlayCountSong));
    }

    @Test
    public void testEquals_Album_False_byPlayCount() {

        // aPlayCount = 0 because we have just initialized the new pc
        PlayCountSongCollection pc = new PlayCountSongCollection(aAlbum);

        try {

            Field field = PlayCountSongCollection.class.getDeclaredField("aPlayCount");
            field.setAccessible(true);
            field.set(aPlayCountAlbum, 2);

        } catch (ReflectiveOperationException e) {

            fail();
        }

        assertFalse(pc.equals(aPlayCountAlbum));
    }

    @Test
    public void testEquals_Playlist_False_byPlayCount() {

        PlayCountSongCollection pc = new PlayCountSongCollection(aPlaylist);

        try {

            Field field = PlayCountSongCollection.class.getDeclaredField("aPlayCount");
            field.setAccessible(true);
            field.set(aPlayCountPlaylist, 2);

        } catch (ReflectiveOperationException e) {

            fail();
        }

        assertFalse(pc.equals(aPlayCountAlbum));
    }
}