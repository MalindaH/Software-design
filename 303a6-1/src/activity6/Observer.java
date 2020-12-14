package activity6;

public interface Observer
{
	void songAdded(Playable addedPlayable);
	void songRemoved(Playable removedPlayable);
	
	// by default do nothing
	default void showMessage(String pMessage) {}
}