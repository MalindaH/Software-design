package music;

import java.util.ArrayList;

public interface SongList extends Playable {
	public void removeSongs(ArrayList<Song> pSongs);
	
	public Song removeSong(Song pSong);
	
	public void addSongs(ArrayList<Song> pSongs);
	
	public void addSong(Song pSong);
	
	public Song getFirstSong();
	
	public ArrayList<Song> getAllSongs();
	
	public int numOfSongs();
}
