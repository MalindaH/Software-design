package activity3;
import java.util.List;

public interface SongCollection extends Playable, Cloneable
{
    /**
     * This method returns true iff the songs in this.SongCollection's are equal to that of pObject. The entries must
     * be in the same order. Song's are considered equal if they have the same File, Title, Artist, Duration, and Genre
     * 
     * @param pObject the object to be compared with this
     * @return true if these conditions are met.
     * @pre Object != null 
     */
    public boolean equals(Object pObject);

    /**
     * Since equals is being overwritten, those who implement SongCollection must also overide hashCode
     * 
     * @return the coresponding hashcode
     */
    public int hashCode();

    /**
     * @return the total runtime of this SongCollection
     */
    public int getRuntime();

    /**
     * @return a string corresponding to the name of this
     */
    public String getTitle();

    /** 
     * Since SongCollections are mutable, they must only return a copy of there contents
     * 
     * @return a copy of the content of this SongCollection
     */
    public List<SongCollection> getContents();
    
    /**
     * returns a copy of the songCollection
     * @return a copy of the songCollection
     */
    public SongCollection clone();
    
    /**
     * default implementation of play() method from Playable interface
     * @pre pPlayer != null
     */
    @Override
	default public void play(Player pPlayer)
	{
    	assert pPlayer != null;
		System.out.print(this.description());
	}

    /**
     * default implementation of duration() method from Playable interface
     */
	@Override
	default public int duration()
	{
		return this.getRuntime();
	}
    /**
     * returns true if the SongCollection is contained in ths SongCollection
     * @param pSongCollection the SongCollection to search for
     * @return true if the SongCollection is contained in ths SongCollection
     * @pre pSongCollection != null
     */
    public boolean contains(SongCollection pSongCollection);
}
