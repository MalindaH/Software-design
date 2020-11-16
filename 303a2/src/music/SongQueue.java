package music;
import java.util.*;

public interface SongQueue {

	public void addToQueue(Song qsong);
	public void resetQueue(Song qsong);
	public List<Song> getQueue();
	public void removeFromQueue(Song qsong);

}
