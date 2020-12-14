package activity6;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Mock-up of a partial user interface for the music library.
 * 
 * This code makes a simplistic use of the JavaFX library
 * to limit the code comprehension efforts not directly related 
 * to the course objectives. 
 *
 */
public class LibraryUI extends Application
{
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;

	/**
	 * Launches the application.
	 * @param pArgs This program takes no argument.
	 */
	public static void main(String[] pArgs) 
	{
		launch(pArgs);
	}

	@Override
	public void start(Stage pStage) 
	{
		Library library = new Library();

		BorderPane root = new BorderPane();

		// Create status bar observer
		StatusBarView statusBar = new StatusBarView();
		library.addObserver(statusBar);

		// Create the view of all playables 
		ListViewPanel aListViewPanel = new ListViewPanel();
		library.addObserver(aListViewPanel);

		root.setCenter(aListViewPanel.getView());

		// Create button to add files to the library
		Button addButton = new Button("Add Song");
		addButton.setMaxWidth(Double.MAX_VALUE);
		root.setTop(addButton);
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				try {
					File songFile = selectFile(pStage).get();
					// need to check validity of songFile since selectFile() returns Optional.ofNullable
					if(songFile != null) { 
						library.addItem(new Song(songFile, songFile.getName()));
						root.setCenter(aListViewPanel.getView());
					} else {
						throw new IllegalArgumentException();
					}
				} catch (NoSuchElementException e) {
					library.notifyMessagetoLog("The operation has been cancelled.");
				} catch(IllegalArgumentException e) {
					library.notifyMessagetoLog("The selected file is invalid.");
				}
			}  		
		});

		// Create bottom panel
		VBox bottom = new VBox();

		// Create delete button
		Button deleteButton = new Button("Remove item");
		deleteButton.setMaxWidth(Double.MAX_VALUE);

		// Configure bottom panel
		bottom.getChildren().addAll(statusBar.getBar(), deleteButton);
		root.setBottom(bottom);
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				try {
					library.removeItem(getSelected(aListViewPanel.getView()));
					root.setCenter(aListViewPanel.getView());
				} catch (NullPointerException e) { // if no song in the list is selected
					library.notifyMessagetoLog("No item is selected.");
				}
			}
		});

		pStage.setTitle("Library");
		pStage.setScene(new Scene(root, WIDTH, HEIGHT));
		pStage.show();
	}


	// Sample code to activate the file chooser
	private static Optional<File> selectFile(Stage pStage)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("./files/"));
		File selectedFile = fileChooser.showOpenDialog(pStage);
		return Optional.ofNullable(selectedFile);
	}

	// Sample code to get the selected object from a ListView
	private static Playable getSelected(ListView<Playable> pView)
	{
		return pView.getSelectionModel().getSelectedItem();
	}
}
