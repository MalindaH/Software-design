package activity6;

import javafx.scene.text.Text;

public class StatusBarView implements Observer
{
	private Text aStatus = new Text("Ready.");
	
	/**
	 * @pre addedPlayable != null
	 */
	@Override
	public void songAdded(Playable addedPlayable)
	{	
		assert addedPlayable != null;
		aStatus.setText("Added " + addedPlayable.description());
		System.out.println("Added " + addedPlayable.description());
	}

	/**
	 * @pre removedPlayable != null
	 */
	@Override
	public void songRemoved(Playable removedPlayable)
	{
		assert removedPlayable != null;
		aStatus.setText("Removed " + removedPlayable.description());
		System.out.println("Removed " + removedPlayable.description());
	}
	
	public Text getBar() {
		return aStatus;	
	}

	/**
	 * @param pMessage
	 * @pre pMessage != null
	 */
	public void showMessage(String pMessage) {
		assert pMessage != null;
		aStatus.setText(pMessage);
		System.out.println(pMessage);
	}
}
