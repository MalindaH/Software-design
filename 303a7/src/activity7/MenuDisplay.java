package activity7;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Skeleton code that illustrates the general layout
 * expected. Modify as necessary.
 */
public class MenuDisplay extends Application
{
	private static final int WIDTH_SIDE = 150;
	private static final int WIDTH_CENTER = 200;

	private static Menu menu = new Menu();
	private static BorderPane main = new BorderPane();
	private static MenuViewPanel left, center, right;
	private static Configurations config = new Configurations();
	private static ToggleGroup group = new ToggleGroup();
	private static ArrayList<RegularItem> comboItems = new ArrayList<RegularItem>();

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
		setUp();
		left = new MenuViewPanel(menu);
		main.setLeft(left.createPanel(WIDTH_SIDE, config.getLeft(0)));
		center = new MenuViewPanel(menu);
		main.setCenter(center.createPanel(WIDTH_CENTER, config.getCenter(0)));
		right = new MenuViewPanel(menu);
		main.setRight(right.createPanel(WIDTH_SIDE, config.getRight(0)));

		pStage.setScene(new Scene(new VBox(main, createControl(main, config), createButtons(pStage))));
		pStage.setTitle("Menu");
		pStage.show();
	}

	/**
	 * add items to the menu and set up three different configurations
	 */
	private static void setUp() {
		menu.addItem(new RegularItem("Coffee", 2.00, FoodCategory.DRINK));
		menu.addItem(new RegularItem("Tea", 1.75, FoodCategory.DRINK));
		menu.addItem(new RegularItem("Beer", 2.50, FoodCategory.DRINK));

		menu.addItem(new RegularItem("Poutine", 3.00, FoodCategory.MAIN));
		menu.addItem(new RegularItem("Spaghetti", 5.75, FoodCategory.MAIN));
		menu.addItem(new RegularItem("Soup", 2.50, FoodCategory.MAIN));
		menu.addItem(new RegularItem("Okonomiyaki", 5.00, FoodCategory.MAIN));
		
		menu.addItem(new RegularItem("Peanuts", 1.00, FoodCategory.SNACK));
		menu.addItem(new RegularItem("Chocolate Bar", 1.75, FoodCategory.SNACK));

		RegularItem fish = new RegularItem("Fried fish", 4.00, FoodCategory.MAIN);
		menu.addItem(fish);
		RegularItem chips = new RegularItem("Chips", 1.00, FoodCategory.SNACK);
		menu.addItem(chips);
		menu.addItem(new ComboItem("Fish & Chips", fish, chips));

		menu.addItem(new RegularItem("Pasta", 4.50, FoodCategory.MAIN, DietaryCategory.HALAL));

		config.addConfig("Cheap/All/Combo", PriceCategory.ASCENDING, PriceCategory.ALL, FoodCategory.COMBO);
		config.addConfig("Snack/Vegan/Expensive", FoodCategory.SNACK, DietaryCategory.VEGAN, PriceCategory.DESCENDING);
		config.addConfig("Halal/Combo/Main", DietaryCategory.HALAL, FoodCategory.COMBO, FoodCategory.MAIN);

	}

	/**
	 * @param pStage the primary stage
	 * @return HBox containing a few buttons (add different types of items, remove items, apply sale)
	 */
	private static HBox createButtons(Stage pStage) {
		HBox box = new HBox();

		Button addButton = new Button("Add non-combo item");
		Button addToComboButton = new Button("Add to combo item");
		Button addComboButton = new Button("Add combo item");
		Button deleteButton = new Button("Remove item");
		Button saleButton = new Button("Apply discount");

		box.getChildren().addAll(addButton, addToComboButton, addComboButton, deleteButton, saleButton);
		box.setAlignment(Pos.BASELINE_CENTER);

		
		
		addButton.setOnAction((event) -> {	
			final Stage dialog = new Stage();
			dialog.initOwner(pStage);

			ComboBox<String> foodComboBox = new ComboBox<>();
			foodComboBox.getItems().addAll("Drink","Main","Snack");
			foodComboBox.getSelectionModel().select(0);

			CheckBox halal = new CheckBox("Halal");   
			CheckBox keto = new CheckBox("Keto"); 
			CheckBox vegan = new CheckBox("Vegan"); 
			CheckBox veget = new CheckBox("Vegetarian"); 
			CheckBox pesce = new CheckBox("Pescetarians");
			HBox hb = new HBox();
			hb.getChildren().addAll(halal, keto, vegan, veget, pesce);

			ComboBox<String> sizeComboBox = new ComboBox<>();
			sizeComboBox.getItems().addAll("N/A","small", "regular", "large");
			sizeComboBox.getSelectionModel().select(0);
			TextField nameText = new TextField();
			TextField priceText = new TextField();
			Button confirm = new Button("add");

			confirm.setOnAction((e) -> {                	
				try {
					FoodCategory fc = null;
					if(foodComboBox.getValue().equals("Snack")) fc = FoodCategory.SNACK;
					if(foodComboBox.getValue().equals("Drink")) fc = FoodCategory.DRINK;
					if(foodComboBox.getValue().equals("Main")) fc = FoodCategory.MAIN;

					ArrayList<DietaryCategory> dc = new ArrayList<DietaryCategory>();
					if(halal.isSelected()) dc.add(DietaryCategory.HALAL);
					if(keto.isSelected()) dc.add(DietaryCategory.KETO);
					if(vegan.isSelected()) dc.add(DietaryCategory.VEGAN);
					if(veget.isSelected()) dc.add(DietaryCategory.VEGETARIAN);
					if(pesce.isSelected()) dc.add(DietaryCategory.PESCETARIANS);

					Double price = Double.parseDouble(priceText.getText());
					if(price <= 0) throw new IllegalArgumentException();

					if(sizeComboBox.getValue().equals("N/A")) {
						RegularItem item = new RegularItem(nameText.getText(), 
								price, fc, dc.toArray(new DietaryCategory[dc.size()]));
						menu.addItem(item);
					} else {
						SizeableItem item = new SizeableItem(nameText.getText(), 
								price, fc, dc.toArray(new DietaryCategory[dc.size()]));
						if(sizeComboBox.getValue().equals("small")) {
							item.setSize(Size.SMALL);
						} else if(sizeComboBox.getValue().equals("regular")) {
							item.setSize(Size.REGULAR);
						} else {
							item.setSize(Size.LARGE);
						}
						menu.addItem(item);
					}
					dialog.close();
				} catch (NullPointerException | IllegalArgumentException err) {
					System.out.println("error values entered");
				}
			});
			
			

			GridPane grid = new GridPane();
			grid.setVgap(4);
			grid.setHgap(10);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(new Text("Add a menu item:"), 0,0);
			grid.add(new Text("Food category: "), 0, 1);
			grid.add(foodComboBox, 1, 1);
			grid.add(new Text("Dietary category (optional): "), 0, 2);
			//                grid.add(dietComboBox, 1, 2);
			grid.add(hb, 1, 2);               
			grid.add(new Text("Size (optional): "), 0, 3);
			grid.add(sizeComboBox, 1, 3);
			grid.add(new Text("Name: "), 0, 4);
			grid.add(nameText, 1, 4);
			grid.add(new Text("Price ($): "), 0, 5);
			grid.add(priceText, 1, 5);
			grid.add(confirm, 1, 6);

			Scene dialogScene = new Scene(grid, 500, 250);                
			dialog.setScene(dialogScene);
			dialog.setTitle("Add Item");
			dialog.show();			
		});
		
		addToComboButton.setOnAction((event) -> {
			try
			{
				MenuItem selected = getSelected();
				final Stage dialog = new Stage();
				dialog.initOwner(pStage);
			
			if(!comboItems.contains(selected))
			{
				GridPane grid = new GridPane();
				grid.setVgap(4);
				grid.setHgap(10);
				grid.setPadding(new Insets(5, 5, 5, 5));
				try
				{
					comboItems.add((RegularItem)selected);
					//comboItems.add(new RegularItem(selected.name(), selected.price(), selected.getFoodCategory(),
					//		selected.getDietaryCategories().toArray(new DietaryCategory[0])));
					System.out.println("Added " + selected.name() + " to make a new combo.");
					
					grid.add(new Text("Added " + selected.name() + " to make a new combo."), 0,0);
				}
				catch (ClassCastException e)
				{
					grid.add(new Text(selected.name() + " could not be added. Only add non-combo items."), 0,0);
				}
				
				Button confirm = new Button("OK");

				confirm.setOnAction((e) -> { 
						dialog.close();
				});
				
				grid.add(confirm, 2, 0);
				
				Scene dialogScene = new Scene(grid, 400, 40);                
				dialog.setScene(dialogScene);
				dialog.setTitle("Add item to combo");
			}
			else
			{
				GridPane grid = new GridPane();
				grid.setVgap(4);
				grid.setHgap(10);
				grid.setPadding(new Insets(5, 5, 5, 5));
				grid.add(new Text("The item is already selected for the new combo. Do you wish to remove this item from the combo?"), 0,0);
				
				Button yes = new Button("Yes");
				yes.setOnAction((e) -> { 
					comboItems.remove(selected);
					System.out.println("Removed " + selected.name() + " from combo.");
					dialog.close();
				});
				
				Button no = new Button("No");
				no.setOnAction((e)->{
					dialog.close();
				});
				
				grid.add(yes, 1, 0);
				grid.add(no, 2, 0);
				
				Scene dialogScene = new Scene(grid, 620, 40);                
				dialog.setScene(dialogScene);
				dialog.setTitle("Remove item from combo");
			}
			
			dialog.show();
			}
			catch (NullPointerException e)
			{
				System.out.println("No item selected");
			}
		});
		
		addComboButton.setOnAction((event) -> {
			final Stage dialog = new Stage();
			dialog.initOwner(pStage);
			
			if (comboItems.size() >= 2)
			{
				TextField nameText = new TextField();
				Button confirm = new Button("add");

				confirm.setOnAction((e) -> {                	
					try {
						menu.addItem(new ComboItem(nameText.getText(), comboItems.get(0), comboItems.get(1),
								comboItems.subList(2, comboItems.size()).toArray(new RegularItem[0])));
						comboItems.clear();
						updateView();
						dialog.close();
					} catch (NullPointerException | IllegalArgumentException err) {
						System.out.println("error values entered");
					}
				});
				
				

				GridPane grid = new GridPane();
				grid.setVgap(4);
				grid.setHgap(10);
				grid.setPadding(new Insets(5, 5, 5, 5));
				grid.add(new Text("Add a combo item:"), 0,0);
				grid.add(new Text("Name: "), 0, 4);
				grid.add(nameText, 1, 4);
				grid.add(confirm, 1, 6);

				Scene dialogScene = new Scene(grid, 300, 200);                
				dialog.setScene(dialogScene);
				dialog.setTitle("Add Item");
			}
			else
			{
				GridPane grid = new GridPane();
				grid.setVgap(4);
				grid.setHgap(10);
				grid.setPadding(new Insets(5, 5, 5, 5));
				grid.add(new Text("At least two items are needed to create a combo.\n Use the \"Add to combo item\" button first to add selected items."), 0,0);
				
				Button confirm = new Button("OK");
				confirm.setOnAction((e) -> { 
					dialog.close();
				});
				
				grid.add(confirm, 2, 0);
				
				Scene dialogScene = new Scene(grid, 420, 40);                
				dialog.setScene(dialogScene);
				dialog.setTitle("Insufficient items in combo");
			}
			
			dialog.show();
		});

		deleteButton.setOnAction((event) -> {
			try {
				menu.removeItem(getSelected());
			} catch (NullPointerException e) { // if no item is selected
				System.out.println("No item selected");
			}
		});

		saleButton.setOnAction((event) -> {
			try {
				MenuItem selected = getSelected();
				
				final Stage dialog = new Stage();
				dialog.initOwner(pStage);
				
				if(!selected.isOnSale()) // prompt new sale command
				{
					
					GridPane grid = new GridPane();
					grid.setVgap(4);
					grid.setHgap(10);
					grid.setPadding(new Insets(5, 5, 5, 5));
					grid.add(new Text("Discount rate:"), 0,0);

					TextField priceText = new TextField();
					grid.add(priceText, 1, 0);
					Button confirm = new Button("confirm");

					confirm.setOnAction((e) -> { 
						try {
							double pSale = Double.parseDouble(priceText.getText());
							if(pSale <= 0 || pSale >= 1) throw new IllegalArgumentException();
							
							selected.createSaleCommand(pSale);
							selected.getSaleCommand().execute();
							updateView();
							dialog.close();
							
						} catch (IllegalArgumentException err) {
							System.out.println("Discount rate must be between 0 and 1 (exclusive)");
						}
					});
					
					grid.add(confirm, 2, 0);
					
					Scene dialogScene = new Scene(grid, 350, 40);                
					dialog.setScene(dialogScene);
					dialog.setTitle("Put an item on sale");
					
				}
				else // remove sale.
				{
					GridPane grid = new GridPane();
					grid.setVgap(4);
					grid.setHgap(10);
					grid.setPadding(new Insets(5, 5, 5, 5));
					grid.add(new Text("The item is already on special discount. Do you wish to remove the discount?"), 0,0);
					
					Button yes = new Button("Yes");
					yes.setOnAction((e) -> { 
						selected.getSaleCommand().undo();
						updateView();
						dialog.close();
					});
					
					Button no = new Button("No");
					no.setOnAction((e)->{
						dialog.close();
					});
					
					grid.add(yes, 1, 0);
					grid.add(no, 2, 0);
					
					Scene dialogScene = new Scene(grid, 550, 40);                
					dialog.setScene(dialogScene);
					dialog.setTitle("Remove the discount");
				}
				
				dialog.show();
				
			} catch (NullPointerException e) {
				System.out.println("No item selected");
			}
		});
		
		return box;
	}

	/**
	 * @return the selected MenuItem among the 3 ListViews
	 */
	private static MenuItem getSelected()
	{
		if(left.getListView().getSelectionModel().getSelectedItem() != null) {
			return left.getListView().getSelectionModel().getSelectedItem();
		}
		if(center.getListView().getSelectionModel().getSelectedItem() != null) {
			return center.getListView().getSelectionModel().getSelectedItem();
		}
		if(right.getListView().getSelectionModel().getSelectedItem() != null) {
			return right.getListView().getSelectionModel().getSelectedItem();
		}
		throw new NullPointerException();
	}

	/**
	 * @param main the BorderPane in the stage
	 * @param pConfiguration the Configurations of the stage
	 * @return HBox containing ToggleGroup to select configurations
	 * @pre pConfiguration.getSize() > 0
	 */
	private static HBox createControl(BorderPane main, Configurations pConfiguration)
	{
		assert pConfiguration.getSize() > 0;
		HBox control = new HBox();
		control.setPadding(new Insets(5));
		control.setAlignment(Pos.CENTER);

		for( int i=0; i<pConfiguration.getSize(); i++)
		{
			RadioButton button = new RadioButton(config.getConfigName(i));
			button.setPadding(new Insets(5));
			button.setToggleGroup(group);
			control.getChildren().add(button);
		}
		((RadioButton)control.getChildren().get(0)).setSelected(true);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2)
			{
				updateView();
			}
		});

		return control;
	}

	/**
	 * updates the ListViews on the stage
	 */
	public static void updateView() {
		RadioButton selection = (RadioButton) group.getSelectedToggle();
		if (selection != null)
		{
			int position = config.getConfigPosition(selection.getText());
			main.setLeft(left.createPanel(WIDTH_SIDE, config.getLeft(position)));
			main.setCenter(center.createPanel(WIDTH_CENTER, config.getCenter(position)));
			main.setRight(right.createPanel(WIDTH_SIDE, config.getRight(position)));
		}
	}
}
