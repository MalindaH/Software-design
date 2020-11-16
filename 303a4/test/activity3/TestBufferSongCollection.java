package activity3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBufferSongCollection {
	Song song;
	Album album;
	Playlist playlist;
	
	@BeforeEach
	public void setUp() {
		song=new Song(new File("/activity3/files/Scar_Tissue.mp3"), "Scar Tissue", Artist.getArtist("RHCP"), Genre.getGenre("Rock"));
		song.setDuration(100);
		album=new Album("Californication");
		album.addTrack(3, song);
		album.addTrack(1, new Song(new File("/activity3/files/Around_The_World.mp3"), "Around The World", Artist.getArtist("RHCP"), Genre.getGenre("Rock")));	
		playlist=new Playlist("playlist");
		playlist.add(album);
		playlist.add(new Song(new File("/activity3/files/By_the_Way.mp3"), "By The Way", Artist.getArtist("RHCP"), Genre.getGenre("Rock")));
	}
	
	@Test
	public void testGetRunTime_Song() {
		BufferSongCollection buffer=new BufferSongCollection(song,5);
		assertTrue(buffer.getRuntime()==110);
	}
	
	
	@Test
	public void testDescription_Song() {
		BufferSongCollection buffer=new BufferSongCollection(song,5);
		assertEquals(buffer.description(),"...buffer for 5 seconds...---Now playing: Song Scar Tissue by RHCP---...buffer for 5 seconds...");
	}
	
	@Test
	public void testDescription_Album() {
		BufferSongCollection buffer=new BufferSongCollection(album,5);
		assertEquals(buffer.description(),"...buffer for 5 seconds...---Now playing: Album Californication---\n"
				+ "\t---Now playing: Song Around The World by RHCP---\n"
				+ "\t---Now playing: Song Scar Tissue by RHCP---\n"
				+ "...buffer for 5 seconds...");
	}
	
	@Test
	public void testDescription_Playlist() {
		BufferSongCollection buffer=new BufferSongCollection(playlist,5);
		assertEquals(buffer.description(),"...buffer for 5 seconds...---Now playing: Playlist playlist---\n"
				+ "\t---Now playing: Album Californication---\n"
				+ "\t---Now playing: Song Around The World by RHCP---\n"
				+ "\t---Now playing: Song Scar Tissue by RHCP---\n\n"
				+ "\t---Now playing: Song By The Way by RHCP---"
				+ "...buffer for 5 seconds...");
	}
	
	@Test
	public void testEquals_true() {
		BufferSongCollection buffer1=new BufferSongCollection(playlist,5);
		BufferSongCollection buffer2 = new BufferSongCollection(playlist,5);
		assertTrue(buffer1.equals(buffer2));
	}
	
	@Test
	public void testEquals_false_by_SongCollection() {
		BufferSongCollection buffer1=new BufferSongCollection(playlist,5);
		BufferSongCollection buffer2 = new BufferSongCollection(album,5);
		assertFalse(buffer1.equals(buffer2));

	}
	
	@Test
	public void testEquals_false_by_Buffer() {
		BufferSongCollection buffer1=new BufferSongCollection(playlist,5);
		BufferSongCollection buffer2 = new BufferSongCollection(playlist,3);
		assertFalse(buffer1.equals(buffer2));

	}
	
	
	@Test
	public void testClone_Song() {
		song.setDuration(100);
		BufferSongCollection buffer=new BufferSongCollection(song,5);
		BufferSongCollection clone = buffer.clone();
		
		assertTrue(buffer.getBuffer()==clone.getBuffer() && buffer.getContents().equals(clone.getContents()));
	}
	
	@Test
	public void testClone_Album() {
		BufferSongCollection buffer=new BufferSongCollection(album,5);
		BufferSongCollection clone = buffer.clone();
		assertTrue(buffer.getBuffer()==clone.getBuffer() && buffer.getContents().equals(clone.getContents()));
	}
	@Test
	public void testClone_Playlist() {
		BufferSongCollection buffer=new BufferSongCollection(playlist,5);
		BufferSongCollection clone = buffer.clone();
		assertTrue(buffer.getBuffer()==clone.getBuffer() && buffer.getContents().equals(clone.getContents()));
	}

}
