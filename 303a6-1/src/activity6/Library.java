package activity6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of songs, playlists, and albums.
 */
public class Library implements Iterable<Playable>
{
	private final List<Playable> aPlayables = new ArrayList<>();
	private final List<Observer> aObservers = new ArrayList<>();
	
	/**
	 * Creates a new empty library.
	 */
	public Library()
	{
		// Just for demonstration
		//addItem(new Song(new File("A.mp3"), "Song A"));
		//addItem(new Song(new File("B.mp3"), "Song B"));
		//addItem(new Song(new File("C.mp3"), "Song C"));
	}
	
	/**
	 * @param pPlayable
	 * @pre pPlayable != null
	 */
	public void addItem(Playable pPlayable)
	{
		assert pPlayable != null;
		// don't allow adding repetitive songs
		if(!aPlayables.contains(pPlayable)) {
			aPlayables.add(pPlayable);
			if (pPlayable instanceof Song) {
				for (Observer observer : aObservers) {
					observer.songAdded(pPlayable);
				}
			}
		} else {
			notifyMessagetoLog(pPlayable.description()+" is already in the library");
		}
	}
	
	/**
	 * @param pPlayable
	 * @pre pPlayable != null
	 */
	public void removeItem(Playable pPlayable)
	{
		assert pPlayable != null;
		if(aPlayables.remove(pPlayable)) { // if removed successfully
			for (Observer observer : aObservers) {
				observer.songRemoved(pPlayable);
			}
		}
	}
	
	/**
	 * @param pPlayable
	 * @pre pPlayable != null
	 */
	public boolean containsItem(Playable pPlayable)
	{
		assert pPlayable != null;
		if(aPlayables.contains(pPlayable)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Iterator<Playable> iterator()
	{
		return new ArrayList<>(aPlayables).iterator();
	}
	
	/**
	 * @param pObserver
	 * @pre pObserver != null
	 */
	public void addObserver(Observer pObserver) {
		assert pObserver != null;
		aObservers.add(pObserver);
	}
	
	/**
	 * @param pObserver
	 * @pre pObserver != null
	 */
	public void removeObserver(Observer pObserver) {
		assert pObserver != null;
		aObservers.remove(pObserver);
	}
	
	/**
	 * @param pObserver
	 * @pre pObserver != null
	 */
	public boolean containsObserver(Observer pObserver) {
		assert pObserver != null;
		return aObservers.contains(pObserver);
	}
	
	public void notifyMessagetoLog(String pMessage) {
		for (Observer observer : aObservers) {
			observer.showMessage(pMessage);
		}
	}
	
	public List<Playable> getPlayables(){
		return Collections.unmodifiableList(aPlayables);
	}

}
