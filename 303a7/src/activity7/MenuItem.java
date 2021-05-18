package activity7;

import java.util.List;

public interface MenuItem
{
	/**
	 * The current price of the item.
	 */
	public double price();

	/**
	 * The name of the item.
	 */
	public String name();

	/**
	 * A brief description of the item.
	 */
	public String description();

	public List<DietaryCategory> getDietaryCategories();

	public FoodCategory getFoodCategory();
	
	public boolean isOnSale();
	
	public void createSaleCommand(double pSale);
	
	public Command getSaleCommand();

}
