package activity3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a named list of songs.
 */
public class Playlist
{
	private final List<Song> aSongs = new ArrayList<>();
	private String aTitle; 
	
	/**
     * Constructor that adds the title 
     *
     * @param pTitle  The title of the playlist
     * @pre pTitle != null && !pTitle.isEmpty()
     */
	public Playlist(String pTitle)
	{
		assert pTitle != null && !pTitle.isEmpty();
		aTitle = pTitle;
	}
	
	/**
     * Constructor that creates a duplicate Playlist
     *
     * @param pPlaylist playlist to be duplicated
     * @pre pPlaylist != null
     */
	public Playlist(Playlist pPlaylist)
	{
		assert pPlaylist != null;
		aTitle = pPlaylist.getTitle();
		for(Song cur: pPlaylist.getSongs()) {
			aSongs.add(cur.copy());
		}
	}
	
	/**
	 * Sets title of playlist
	 * 
	 * @param title to be changed to
     * @pre pTitle != null && !pTitle.isEmpty()
	 */
	public void setTitle(String pTitle)
	{
		assert pTitle != null && !pTitle.isEmpty();
		aTitle = pTitle;
	}
	
	/**
	 * gets title of this playlist
	 * @return title of playlist
	 */
	public String getTitle()
	{
		return aTitle;
	}
	
	/**
	 * adds the specified song to this playlist
	 * @param pSong song to be added
	 */
	public void addSong(Song pSong)
	{
		assert pSong != null;
		aSongs.add(pSong);
	}
	
	public void backup() 
	{
	    System.out.println("Backing up the playlist");
	}
	
	/**
	 * @return number of songs
	 */
	public int size() {
		return aSongs.size();
	}
	
	/**
	 * Return a List<Song> containing a deep copy of all Songs in this Playlist
	 * @return List<Song> containing copies of all songs
	 */
	public List<Song> getSongs(){
		ArrayList<Song> output = new ArrayList<Song>();
		for(Song cur: aSongs) {
			output.add(cur.copy());
		}
		return output;
	}
	
	/**
	 * removes the specified song from this playlist
	 * @param pSong the Song to remove
	 * @return true upon success
	 * @pre pSong != null
	 */
	public boolean removeSong(Song pSong) {
		assert pSong != null;
		return aSongs.remove(pSong);
	}

	/**
	 * checks whether this playlist equals the input Playlist according to criteria
	 * @param pPlaylist the Playlist to compare to
	 * @param pPlaylistCriteria SongListCriteria
	 * @param pSongCriteria SongCriteria
	 * @return true if this playlist and pPlaylist are equal according to criterion given
	 * @pre pPlaylist != null
	 */
	public boolean equals(Playlist pPlaylist, SongListCriteria pPlaylistCriteria, Song.SongCriteria pSongCriteria){
		assert pPlaylist != null;
		if(this.size() != pPlaylist.size()){
			return false;
		}
		if(pPlaylistCriteria == SongListCriteria.TOTAL){
			if(this.aTitle.equalsIgnoreCase(pPlaylist.aTitle)){
				for(int i = 0; i < this.size(); i++){
					if(!this.aSongs.get(i).equals(pPlaylist.aSongs.get(i), pSongCriteria)){
						return false;
					}
				}
				return true;
			}
		}
		else if(pPlaylistCriteria == SongListCriteria.CONTENT){
			// Shallow copy of argument song list
			List<Song> tmpList = new ArrayList<>(pPlaylist.aSongs);
			for (Song s: this.aSongs){
				for(int i=0; i < tmpList.size(); i++){
					if(s.equals(tmpList.get(i), pSongCriteria)) {
						tmpList.remove(i);
						break;
					}
				}
			}
			if(tmpList.isEmpty()){
				return true;
			}
		}
		return false;
	}

	/**
	 * checks whether this playlist contains the specified Song according to pCriteria
	 * @param pSong the Song to check
	 * @param pCriteria SongCriteria to check according to
	 * @return true if this playlist contains pSong according to pCriteria
	 * @pre pSong != null
	 */
	public boolean contains(Song pSong, Song.SongCriteria pCriteria){
		assert pSong != null;
		for(Song s: this.aSongs){
			if(s.equals(pSong, pCriteria)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return a String containing all songs in this Playlist
	 * @return a String containing all songs in this Playlist
	 */
	public String print() {
		String output = "---Playlist "+this.getTitle()+"---\n";
		for(Song cur: this.getSongs()) {
			output = output + cur.toString() + "\n";
		}
		return output;
	}
}
