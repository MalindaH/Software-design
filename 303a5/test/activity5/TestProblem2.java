package activity5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestProblem2
{
    private static final File f1 = new File("filepath1.wav");
    private static Song s1;
    private static Album a1;
    private static Playlist p1;

    @BeforeEach
    public void init()
    {
        s1 = new Song(f1, "Song Title");
        a1 = new Album("Album Title", "Album Artist");
        p1 = new Playlist("Playlist Title");

        a1.addTrack(1, s1);
        p1.add(s1);
    }

    @DisplayName("Song play count initializes as 0")
    @Test
    public void checkInitSongPlayCount()
    {
        int expected = 0;
        int count = s1.getPlayCount();

        assertEquals(expected, count);
        assertTrue(expected == 0 && count == 0);
    }

    @DisplayName("Song play count increments by 1")
    @Test
    public void checkSongPlayCount()
    {
        int expected = 1;

        s1.play();
        int count = s1.getPlayCount();

        assertEquals(expected, count);
        assertTrue(expected == 1 && count == 1);

        expected++;

        s1.play();
        int count2 = s1.getPlayCount();

        assertEquals(expected, count2);
        assertTrue(expected == 2 && count2 == 2);
    }

    @DisplayName("Get song silence length")
    @Test
    public void checkGetSongSilenceLength()
    {
        int expected = 0;

        int silenceLength = s1.getSilenceLength();

        assertEquals(expected, silenceLength);
        assertTrue(expected == 0 && silenceLength == 0);
    }

    @DisplayName("Set song silence length")
    @Test
    public void checkSetSongSilenceLength()
    {
        int silence = 5;
        s1.setSilenceLength(silence);

        int getSilence = s1.getSilenceLength();

        assertEquals(silence, getSilence);
        assertTrue(silence == 5 && getSilence == 5);

    }

    @DisplayName("Check console output when song plays")
    @Test
    public void checkSongConsoleOutput()
    {
        PrintStream printStream = System.out;
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        s1.setSilenceLength(10);
        s1.play();

        int songSilence = s1.getSilenceLength();
        String songTitle = s1.getTitle();

        assertEquals(true, output.toString().contains("Playing " + songSilence + " seconds of silence")
                && output.toString().contains("Playing " + songTitle + " by <undefined artist>"));
        System.setOut(printStream);

    }

    @DisplayName("Album play count initializes as 0")
    @Test
    public void checkInitAlbumPlayCount()
    {
        int expected = 0;
        int count = a1.getPlayCount();

        assertEquals(expected, count);
        assertTrue(expected == 0 && count == 0);
    }

    @DisplayName("Album play count increments by 1")
    @Test
    public void checkAlbumPlayCount()
    {
        int expected = 1;

        a1.play();
        int count = a1.getPlayCount();

        assertEquals(expected, count);
        assertTrue(expected == 1 && count == 1);

        expected++;

        a1.play();
        int count2 = a1.getPlayCount();

        assertEquals(expected, count2);
        assertTrue(expected == 2 && count2 == 2);
    }

    @DisplayName("Get album silence length")
    @Test
    public void checkGetAlbumSilenceLength()
    {
        int expected = 0;

        int silenceLength = a1.getSilenceLength();

        assertEquals(expected, silenceLength);
        assertTrue(expected == 0 && silenceLength == 0);
    }

    @DisplayName("Set album silence length")
    @Test
    public void checkSetAlbumSilenceLength()
    {
        int silence = 5;
        a1.setSilenceLength(silence);

        int getSilence = a1.getSilenceLength();

        assertEquals(silence, getSilence);
        assertTrue(silence == 5 && getSilence == 5);
    }

    @DisplayName("Check console output when album plays")
    @Test
    public void checkAlbumConsoleOutput()
    {
        PrintStream printStream = System.out;
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        a1.setSilenceLength(5);
        s1.setSilenceLength(10);
        a1.play();

        int albumSilence = a1.getSilenceLength();
        int songSilence = s1.getSilenceLength();
        String songTitle = s1.getTitle();

        assertEquals(true,
                output.toString().contains("Playing " + albumSilence + " seconds of silence")
                        && output.toString().contains("Playing " + songSilence + " seconds of silence")
                        && output.toString().contains("Playing " + songTitle + " by <undefined artist>"));
        System.setOut(printStream);

    }

    @DisplayName("Playlist play count initializes as 0")
    @Test
    public void checkInitPlaylistPlayCount()
    {
        int expected = 0;
        int count = p1.getPlayCount();

        assertEquals(expected, count);
        assertTrue(expected == 0 && count == 0);
    }

    @DisplayName("Playlist play count increments by 1")
    @Test
    public void checkPlaylistPlayCount()
    {
        int expected = 1;

        p1.play();
        int count = p1.getPlayCount();

        assertEquals(expected, count);
        assertTrue(expected == 1 && count == 1);

        expected++;

        p1.play();
        int count2 = p1.getPlayCount();

        assertEquals(expected, count2);
        assertTrue(expected == 2 && count2 == 2);
    }

    @DisplayName("Get playlist silence length")
    @Test
    public void checkGetPlaylistSilenceLength()
    {
        int expected = 0;

        int silenceLength = p1.getSilenceLength();

        assertEquals(expected, silenceLength);
        assertTrue(expected == 0 && silenceLength == 0);
    }

    @DisplayName("Set playlist silence length")
    @Test
    public void checkSetPlaylistSilenceLength()
    {
        int silence = 5;
        p1.setSilenceLength(silence);

        int getSilence = p1.getSilenceLength();

        assertEquals(silence, getSilence);
        assertTrue(silence == 5 && getSilence == 5);
    }

    @DisplayName("Check console output when playlist plays")
    @Test
    public void checkPlaylistConsoleOutput()
    {
        PrintStream printStream = System.out;
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        p1.setSilenceLength(1);
        a1.setSilenceLength(5);
        s1.setSilenceLength(10);

        p1.add(a1);
        p1.play();

        int playlistSilence = p1.getSilenceLength();
        int albumSilence = a1.getSilenceLength();
        int songSilence = s1.getSilenceLength();
        String songTitle = s1.getTitle();

        assertEquals(true,
                output.toString().contains("Playing " + playlistSilence + " seconds of silence")
                        && output.toString().contains("Playing " + albumSilence + " seconds of silence")
                        && output.toString().contains("Playing " + songSilence + " seconds of silence")
                        && output.toString().contains("Playing " + songTitle + " by <undefined artist>"));
        System.setOut(printStream);

    }
}