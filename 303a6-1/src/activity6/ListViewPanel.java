package activity6;

import javafx.scene.control.ListView;

public class ListViewPanel implements Observer
{	
	private ListView<Playable> aListView = new ListView<Playable>();

	/**
	 * @pre addedPlayable != null
	 */
	@Override
	public void songAdded(Playable addedPlayable)
	{
		assert addedPlayable != null;
		aListView.getItems().add(addedPlayable);
	}

	/**
	 * @pre removedPlayable != null
	 */
	@Override
	public void songRemoved(Playable removedPlayable)
	{
		assert removedPlayable != null;
		aListView.getItems().remove(removedPlayable);
	}	
    
    public ListView<Playable> getView() {
    	return aListView; 
    }

}
