package activity3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


/**
 * Represents an album with a title (mandatory) and
 * artist (can be absent). An album can be incomplete,
 * in the sense that some tracks can be missing.
 */
public class Album {
    //Title will be present for every album
    private String aTitle;
    private SongInfo aArtist;
    private final Map<Integer, Song> aTracks = new HashMap<>();

    /**
     * Constructor that only takes an Album title as input
     *
     * @param pTitle The title of the album
     * @pre pTitle != null
     */
    public Album(String pTitle) {
        assert pTitle != null;
        aTitle = pTitle;
        aArtist = SongInfo.NULL;
    }

    /**
     * Constructor that adds the title and the optional artist attribute
     *
     * @param pTitle  The title of the album
     * @param pArtist The artist of the album
     * @pre pTitle != null && !pTitle.isEmpty() && pArtist != null
     */
    public Album(String pTitle, Artist pArtist) {
        assert pTitle != null && !pTitle.isEmpty() && pArtist != null;
        aTitle = pTitle;
        aArtist = pArtist;
    }

    /**
     * Constructor that adds the title and the optional artist attribute but as a string
     *
     * @param pTitle  The title of the album
     * @param pArtist The artist of the album
     * asserts are included in the above constructor and SongInfoFactory methods
     */
    public Album(String pTitle, String pArtist){
        this(pTitle, SongInfoFactory.getArtist(pArtist));
    }
    
    /**
     * Constructor that creates a duplicate Album
     *
     * @param pPlaylist playlist to be duplicated
     * @pre pAlbum != null
     */
	public Album(Album pAlbum)
	{
		assert pAlbum != null;
		aTitle = pAlbum.getTitle();
		for(Song cur: pAlbum.getSongs()) {
			this.addTrack(cur.copy());
		}
	}

    /**
     * gets the title of the album
     * @return aTitle The title of the album
     */
    public String getTitle() {
        return this.aTitle;
    }

    /**
     * gets the artist of this album
     * @return aArtist The artist of the album, return null if artist is invalid
     */
    public Artist getArtist() {
        if(!aArtist.isNull()) {
            return (Artist) aArtist;
        }
        return null;
    }

    /**
     * Setter that sets the title of the album
     *
     * @param pTitle The title of the album
     * @pre pTitle != null && !pTitle.equals("")
     */
    public void setTitle(String pTitle) {
        assert pTitle != null && !pTitle.equals("");
        aTitle = pTitle;
    }

    /**
     * Setter that sets the artist of the album
     *
     * @param pArtist The artist of the album
     * @pre pArtist != null
     */
    public void setArtist(Artist pArtist) {
    	assert pArtist != null;
        aArtist = pArtist;
    }

    /**
     * gets an Artist of the specified name and sets the artist
     * @param pArtist String representing name of the artist
     * @pre pArtist != null && !pArtist.isEmpty()
     */
    public void setArtist(String pArtist){
        assert pArtist != null && !pArtist.isEmpty();
        aArtist = SongInfoFactory.getArtist(pArtist);
    }

    /**
     * Add a track to this album. If the track already exists,
     * prompt for whether to overwrite
     *
     * @param pNumber The track number. Must be greater than 0.
     * @param pSong   The song for this track.
     * @pre pNumber > 0 && pSong != null
     */
    public void addTrack(int pNumber, Song pSong) {
        assert pNumber > 0 && pSong != null;
        Song tmp = aTracks.put(pNumber, pSong);
        if (tmp != null) {
            // let client choose whether to overwrite the track
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to overwrite the song " + pNumber + " - " + tmp.getFile() + " in the Album? (y/n)");
            if (scanner.nextLine().charAt(0) == 'y' || scanner.nextLine().charAt(0) == 'Y') {
                // do nothing
            } else { // put back original song
                aTracks.put(pNumber, tmp);
            }
            scanner.close();
        }
    }

    /**
     * Add a track to this album. use the smallest available track number
     *
     * @param pSong The song for this track.
     * @pre pSong != null
     */
    public void addTrack(Song pSong) {
        assert pSong != null;
        Song s = pSong.copy();
        int i = 1;
        while (aTracks.containsKey(i)) {
            i++;
        }
        aTracks.put(i, s);
    }

    /**
     * gets track number of the specified song
     * @param pSong Song to get the track number
     * @return the track number of the song, return -1 if song doesn't exist in this album
     * @pre pSong != null
     */
    public Integer getTrackNumber(Song pSong) {
        assert pSong != null;
        for (Entry<Integer, Song> cur : aTracks.entrySet()) {
            if (cur.getValue().equals(pSong)) {
                return cur.getKey();
            }
        }
        return -1;
    }

    /**
     * gets size of this Album
     * @return int representing size of this album
     */
    public int size() {
        return aTracks.size();
    }

    /**
	 * Return a List<Song> containing a deep copy of all Songs in this Album
	 * @return List<Song> containing copies of all songs
	 */
    public List<Song> getSongs() {
    	ArrayList<Song> output = new ArrayList<Song>();
		for(Song cur: aTracks.values()) {
			output.add(cur.copy());
		}
		return output;
    }

    /**
     * removes the specified song
     * @param pSong the Song to remove
     * @return true upon success
     * @pre pSong != null
     */
    public boolean removeSong(Song pSong) {
        assert pSong != null;
        int trackNum = this.getTrackNumber(pSong);
        if (trackNum >= 0) {
            aTracks.remove(trackNum);
            return true;
        }
        return false;
    }

    /**
     * checks whether this Album equals the specified Album according to the criteria
     * @param pAlbum the Album to check equality to
     * @param pAlbumCriteria SongListCriteria
     * @param pSongCriteria SongCriteria
     * @return true if this album and pAlbum are equal according to criterion given
     * @pre pAlbum != null
     */
    public boolean equals(Album pAlbum, SongListCriteria pAlbumCriteria, Song.SongCriteria pSongCriteria) {
        assert pAlbum != null;
        if (this.size() != pAlbum.size()) {
            return false;
        }
        if (pAlbumCriteria == SongListCriteria.TOTAL) {
            if((this.aArtist.isNull() && !pAlbum.aArtist.isNull()) || (!this.aArtist.isNull() && pAlbum.aArtist.isNull())){
                return false;
            }
            if (this.aTitle.equalsIgnoreCase(pAlbum.aTitle) && this.getArtist().equals(pAlbum.getArtist())) {
                if(!this.aTracks.keySet().equals(pAlbum.aTracks.keySet())){
                    return false;
                }
                for (Integer trackNum : this.aTracks.keySet()) {
                    if (!this.aTracks.get(trackNum).equals(pAlbum.aTracks.get(trackNum), pSongCriteria)) {
                        return false;
                    }
                }
                return true;
            }
        } else if (pAlbumCriteria == SongListCriteria.CONTENT) {
            // Shallow copy of argument song list
            List<Song> tmpList = new ArrayList<>(pAlbum.aTracks.values());
            for (Song s: this.aTracks.values()){
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
     * checks whether this Album contains the specified song according to pCriteria
     * @param pSong the Song to check
     * @param pCriteria Song Criteria
     * @return true if this album contains pSong according to pCriteria
     * @pre pSong != null
     */
    public boolean contains(Song pSong, Song.SongCriteria pCriteria) {
        assert pSong != null;
        for (Song s : this.aTracks.values()) {
            if (s.equals(pSong, pCriteria)) {
                return true;
            }
        }
        return false;
    }
    
    /**
	 * Return a String containing all songs in this Album
	 * @return a String containing all songs in this Album
	 */
	public String print() {
		String output = "---Album "+this.getTitle()+"---\n";
		for(Song cur: this.getSongs()) {
			output = output + cur.toString() + "\n";
		}
		return output;
	}
}


