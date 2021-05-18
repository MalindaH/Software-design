package activity7;

import java.util.ArrayList;
import static java.util.Comparator.comparing;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class MenuViewPanel
{	
	private enum DisplayOrder {
		// alphabetical order of description, or increasing order of price
		DESCRIPTION, PRICE
	}
	
	// change this constant to change display order
	private static DisplayOrder ORDER = DisplayOrder.PRICE;
	private static Menu menu;
	private static List<MenuItem> items = new ArrayList<>();
	private ListView<MenuItem> aListView = new ListView<>();
	private static final String STYLE = 
			"-fx-pref-height: 300px; -fx-border-width: 1; -fx-border-color: black;" +
					"-fx-background-color: lightgrey; -fx-padding: 5px; -fx-alignment: top-center";

	/**
	 * constructs a MenuViewPanel
	 * @param pMenu the Menu that this refers to
	 * @pre pMenu != null
	 */
	public MenuViewPanel(Menu pMenu) {
		assert pMenu != null;
		menu = pMenu;
		items.addAll(pMenu.getItems());
	}

	/**
	 * @return the ListView of this MenuViewPanel
	 */
	public ListView<MenuItem> getListView(){
		return aListView;
	}

	/**
	 * filters the MenuItems by the input Category
	 * @param pCategory the Category to filter by
	 * @return a filtered List of MenuItem
	 */
	public static List<MenuItem> filter(Category pCategory){
		if(pCategory.getClass() == DietaryCategory.class) {
			items = menu.filtermenuItems(Menu.isDietaryCategory((DietaryCategory) pCategory));
		} else if(pCategory.getClass() == FoodCategory.class) {
			items = menu.filtermenuItems(Menu.isFoodCategory((FoodCategory) pCategory));
		} else if(pCategory.getClass() == PriceCategory.class) {
			if(pCategory == PriceCategory.ASCENDING) {
				items = menu.filterByAscendingPrice(10);
			}else if (pCategory == PriceCategory.DESCENDING) {
				items = menu.filterByDescendingPrice(10);
			}else if(pCategory == PriceCategory.ALL) {
				items = menu.getItems();
			}
		} else {
			items = menu.getItems();
		}
		return new ArrayList<MenuItem>(items);
	}

	/**
	 * creates a VBox containing the ListView
	 * @param pWidth int representing width of the VBox
	 * @param pCategory the Category to filter the MenuItems by
	 * @return VBox containing the ListView
	 * @pre pCategory!= null
	 */
	public VBox createPanel(int pWidth, Category pCategory)
	{
		assert pCategory!= null;
		VBox panel = new VBox();

		panel.setStyle(STYLE);
		panel.setPrefWidth(pWidth);
		Label title = new Label(pCategory.toString());
		title.setStyle("-fx-font-weight: bold");
		List<MenuItem> filtered = filter(pCategory);
		// display in selected order
		if(ORDER == DisplayOrder.DESCRIPTION) {
			filtered.sort(comparing(MenuItem::description).thenComparing(MenuItem::price));
		} else if(ORDER == DisplayOrder.PRICE) {
			filtered.sort(comparing(MenuItem::price).thenComparing(MenuItem::description));
		}
		
		aListView.getItems().clear();
		aListView.getItems().addAll(filtered);

		panel.getChildren().add(title);
		panel.getChildren().add(aListView);
		return panel;
	}
	
}
