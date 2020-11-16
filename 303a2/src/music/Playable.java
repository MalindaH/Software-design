package music;

public interface Playable {
	
	void play();
	void addtToFavorites();
	void removeFromFavorites();
	boolean isFavorite();
	boolean isEmpty();
	
}
