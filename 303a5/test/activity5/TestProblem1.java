package activity5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestProblem1
{
	private static final File f1 = new File("filepath1.wav");
	private static Song s1;
	private static Song s2;
	private static Album a1;
	private static Playlist p1;
	private static Library l1;

	@BeforeEach
	public void init()
	{
		s1 = new Song(f1, "Song Title");
		s2 = new Song(new File("s2.wav"));
		a1 = new Album("Album Title", "Album Artist");
		p1 = new Playlist("Playlist Title");
		l1 = Library.getInstance();
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

	@DisplayName("Song play count increments by one")
	@Test
	public void checkSongPlayCount()
	{
		int expected = 1;

		s1.play();
		int count = s1.getPlayCount();

		assertTrue(expected == 1 && count == 1);
		assertEquals(expected, count);

		expected++;

		s1.play();
		int count2 = s1.getPlayCount();

		assertTrue(expected == 2 && count2 == 2);
		assertEquals(expected, count2);
	}

	@DisplayName("Play song")
	@Test
	public void checkSongPlays()
	{

		int expected = 1;

		s1.play();
		int count = s1.getPlayCount();

		assertEquals(expected, count);
		assertTrue(expected == 1 && count == 1);
	}

	@DisplayName("Get song play count")
	@Test
	public void checkGetSongPlayCount()
	{
		int expected = 3;

		s1.play();
		s1.play();
		s1.play();

		int count = s1.getPlayCount();

		assertEquals(expected, count);
	}

	@DisplayName("Get if song has play count")
	@Test
	public void checkGetSongHasPlayCount()
	{
		boolean expected = true;
		boolean hasCount = s1.getHasPlayCount();

		assertEquals(expected, hasCount);
	}

	@DisplayName("Set song play count to false")
	@Test
	public void checkSetSongHasPlayCount()
	{
		boolean expected = false;

		s1.setHasPlayCount(false);
		boolean playCount = s1.getHasPlayCount();

		assertEquals(expected, playCount);
	}

	@DisplayName("Reset song play count")
	@Test
	public void checkResetSongCount()
	{

		int expected = 3;

		s1.play();
		s1.play();
		s1.play();

		int count = s1.getPlayCount();

		assertEquals(expected, count);
		assertTrue(expected == 3 && count == 3);

		s1.resetPlayCount();

		expected = 0;

		int resetCount = s1.getPlayCount();

		assertEquals(expected, resetCount);
		assertTrue(expected == 0 && resetCount == 0);
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

	@DisplayName("Album play count increments by one")
	@Test
	public void checkAlbumPlayCount()
	{
		int expected = 1;

		a1.play();
		int count = a1.getPlayCount();

		assertTrue(expected == 1 && count == 1);
		assertEquals(expected, count);

		expected++;

		a1.play();
		int count2 = a1.getPlayCount();

		assertTrue(expected == 2 && count2 == 2);
		assertEquals(expected, count2);
	}

	@DisplayName("Play album")
	@Test
	public void checkAlbumPlays()
	{

		int expected = 1;

		a1.play();
		int count = a1.getPlayCount();

		assertEquals(expected, count);
		assertTrue(expected == 1 && count == 1);
	}

	@DisplayName("Get album play count")
	@Test
	public void checkGetAlbumPlayCount()
	{
		int expected = 3;

		a1.play();
		a1.play();
		a1.play();

		int count = a1.getPlayCount();

		assertEquals(expected, count);
	}

	@DisplayName("Get if album has play count")
	@Test
	public void checkGetAlbumHasPlayCount()
	{
		boolean expected = true;
		boolean hasCount = a1.getHasPlayCount();

		assertEquals(expected, hasCount);
	}

	@DisplayName("Set album play count to false")
	@Test
	public void checkSetAlbumHasPlayCount()
	{
		boolean expected = false;

		a1.setHasPlayCount(false);
		boolean playCount = a1.getHasPlayCount();

		assertEquals(expected, playCount);
	}

	@DisplayName("Reset album play count")
	@Test
	public void checkResetAlbumCount()
	{

		int expected = 3;

		a1.play();
		a1.play();
		a1.play();

		int count = a1.getPlayCount();

		assertEquals(expected, count);
		assertTrue(expected == 3 && count == 3);

		a1.resetPlayCount();

		expected = 0;

		int resetCount = a1.getPlayCount();

		assertEquals(expected, resetCount);
		assertTrue(expected == 0 && resetCount == 0);
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

	@DisplayName("Playlist play count increments by one")
	@Test
	public void checkPlaylistPlayCount()
	{
		int expected = 1;

		p1.play();
		int count = p1.getPlayCount();

		assertTrue(expected == 1 && count == 1);
		assertEquals(expected, count);

		expected++;

		p1.play();
		int count2 = p1.getPlayCount();

		assertTrue(expected == 2 && count2 == 2);
		assertEquals(expected, count2);
	}

	@DisplayName("Play playlist")
	@Test
	public void checkPlaylistPlays()
	{

		int expected = 1;

		p1.play();
		int count = p1.getPlayCount();

		assertEquals(expected, count);
		assertTrue(expected == 1 && count == 1);
	}

	@DisplayName("Get playlist play count")
	@Test
	public void checkGetPlaylistPlayCount()
	{
		int expected = 3;

		p1.play();
		p1.play();
		p1.play();

		int count = p1.getPlayCount();

		assertEquals(expected, count);
	}

	@DisplayName("Get if playlist has play count")
	@Test
	public void checkGetPlaylistHasPlayCount()
	{
		boolean expected = true;
		boolean hasCount = p1.getHasPlayCount();

		assertEquals(expected, hasCount);
	}

	@DisplayName("Set playlist play count to false")
	@Test
	public void checkSetPlaylistHasPlayCount()
	{
		boolean expected = false;

		p1.setHasPlayCount(false);
		boolean playCount = p1.getHasPlayCount();

		assertEquals(expected, playCount);
	}

	@DisplayName("Reset playlist play count")
	@Test
	public void checkResetPlaylistCount()
	{

		int expected = 3;

		p1.play();
		p1.play();
		p1.play();

		int count = p1.getPlayCount();

		assertEquals(expected, count);
		assertTrue(expected == 3 && count == 3);

		p1.resetPlayCount();

		expected = 0;

		int resetCount = p1.getPlayCount();

		assertEquals(expected, resetCount);
		assertTrue(expected == 0 && resetCount == 0);
	}

	@DisplayName("Set silence Length")
	@Test
	public void checkSetSilenceLength()
	{

		int expected = 5;

		p1.setSilenceLength(5);

		int silenceLength = p1.getSilenceLength();

		assertEquals(expected, silenceLength);

	}

	@DisplayName("get playlist title")
	@Test
	public void checkGetPlaylistTitle()
	{
		p1 = new Playlist("");
		String expected = "<untitled>";
		assertEquals(expected, p1.getTitle());

		p1 = new Playlist("Playlist Title");
		expected = "Playlist Title";
		assertEquals(expected, p1.getTitle());

	}

	@DisplayName("get album title")
	@Test
	public void checkGetAlbumTitle()
	{
		a1 = new Album("");
		String expected = "<untitled>";
		assertEquals(expected, a1.getTitle());

		a1 = new Album("Album Title");
		expected = "Album Title";
		assertEquals(expected, a1.getTitle());

	}

	@DisplayName("get album artist")
	@Test
	public void checkGetAlbumArtist()
	{

		String expected = "Album Artist";
		assertEquals(expected, a1.getArtist());

		a1 = new Album("Album Title", "");
		expected = "<undefined artist>";
		assertEquals(expected, a1.getArtist());

	}

	@DisplayName("getSize in Library")
	@Test
	public void checkGetSize()
	{

		int expected = 0;
		assertEquals(expected, l1.getSize());

	}

	@DisplayName("isEmpty in Library")
	@Test
	public void checkLibraryIsEmptyTrue()
	{
		assertTrue(l1.isEmpty());

	}

	@DisplayName("isEmpty in Library")
	@Test
	public void checkLibraryIsEmptyFalse()
	{
		l1.ADD(s1).execute();
		assertFalse(l1.isEmpty());

	}

	@DisplayName("equals in Song Class")
	@Test
	public void checkSongEqual()
	{
		Song s1 = new Song(new File("C://song1.mp3"));
		Song s2 = new Song(new File("C://song1.mp3"));
		assertTrue(s1.equals(s2));
	}

	@DisplayName("not equals in Song Class")
	@Test
	public void checkSongNotEqual()
	{
		Song s1 = new Song(new File("C://song1.mp3"));
		Song s2 = new Song(new File("C://song2.mp3"));
		assertFalse(s1.equals(s2));
	}

	@DisplayName("equals in Album Class")
	@Test
	public void checkAlbumEqual()
	{

		Album a1 = new Album("a1");
		a1.addTrack(1, s1);
		a1.addTrack(2, s2);

		Album a2 = new Album("a1");
		a2.addTrack(1, s1);
		a2.addTrack(2, s2);

		assertTrue(a1.equals(a2));

	}

	@DisplayName("equals in Album Class")
	@Test
	public void checkAlbumNotEqual()
	{
		Album a1 = new Album("a1");
		a1.addTrack(1, s1);
		Album a2 = new Album("a1");
		a2.addTrack(2, s2);

		assertFalse(a1.equals(a2));

	}

	@DisplayName("equals in Playlist Class")
	@Test
	public void checkPlaylistEqual()
	{

		Album a1 = new Album("a1");
		a1.addTrack(1, s1);
		Album a2 = new Album("a1");
		a2.addTrack(1, s1);
		Playlist p1 = new Playlist("p1");
		Playlist p2 = new Playlist("p1");
		p1.add(a1);
		p1.add(a2);
		p2.add(a1);
		p2.add(a2);

		assertTrue(p1.equals(p2));

	}

	@DisplayName("equals in Playlist Class")
	@Test
	public void checkPlaylistNotEqual()
	{

		Album a1 = new Album("a1");
		a1.addTrack(1, s1);
		Album a2 = new Album("a1");
		a2.addTrack(1, s2);
		Playlist p1 = new Playlist("p1");
		Playlist p2 = new Playlist("p1");
		p1.add(a1);
		p2.add(a2);

		assertFalse(p1.equals(p2));

	}

	@DisplayName("playlist duration")
	@Test
	void duration_Playlist()
	{

		Playlist p = new Playlist("p1");
		p.add(s1);
		p.add(a1);
		int expected = s1.duration() + a1.duration();

		assertEquals(expected, p.duration());
	}
	
	@DisplayName("album duration")
	@Test
	void duration_Album()
	{

		Album a = new Album("a1");
		a.addTrack(1, s1);
		a.addTrack(2, s2);
		int expected = s1.duration() + s2.duration();

		assertEquals(expected, a.duration());
	}
	
	@DisplayName("remove in playlist")
	@Test
	void remove_playlist()
	{

		Playlist p = new Playlist("p1");
		p.add(s1);
		p.remove(s1);

		assertTrue(p.isEmpty());
	}
	
	
	@DisplayName("remove in album")
	@Test
	void remove_Album()
	{

		Album a = new Album("a1");
		a.addTrack(1, s1);
		a.remove(s1);

		assertTrue(a.isEmpty());
	}



}
