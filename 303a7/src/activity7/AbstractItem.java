package activity7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractItem implements MenuItem
{
	private final String aName;
	private double aPrice;
	private final FoodCategory aFoodCategory;
	private final List<DietaryCategory> aDietaryCategories;
	private boolean aOnSale;
	private Command aSaleCommand;
	
	/**
	 * Create an abstract item.
	 * 
	 * @param pName
	 * @param pPrice
	 * @pre pName != null && pPrice > 0
	 */
	protected AbstractItem(String pName, double pPrice, FoodCategory pFoodCategory, DietaryCategory...pDietaryCategories)
	{
		assert pName != null && pPrice > 0 && pFoodCategory != null;
		aName = pName;
		aPrice = pPrice;
		aFoodCategory = pFoodCategory;
		aDietaryCategories = Arrays.asList(pDietaryCategories);
		aOnSale = false;
	}
	
	protected AbstractItem(AbstractItem pAbstractItem)
	{
		assert pAbstractItem != null;
		aName = pAbstractItem.name();
		aPrice = pAbstractItem.price();
		aFoodCategory = pAbstractItem.getFoodCategory();
		aDietaryCategories = pAbstractItem.getDietaryCategories();
		aOnSale = pAbstractItem.isOnSale();
	}	

	@Override
	public final boolean isOnSale()
	{
		return aOnSale;
	}
	
	@Override
	public final void createSaleCommand(double pSale)
	{
		assert pSale > 0 && pSale <1;
		aSaleCommand = new Command()
		{
			double aSale = pSale;
			
			@Override
			public void execute()
			{
				if(!aOnSale)
				{
					setPrice(aPrice * (1 - aSale));
					aOnSale = true;
				}
			}

			@Override
			public void undo()
			{
				if(aOnSale)
				{
					setPrice(aPrice / (1 - aSale));
					aOnSale = false;
				}
			}
		};
	}
	
	@Override
	public final Command getSaleCommand()
	{
		return aSaleCommand;
	}

	@Override
	public final String name()
	{
		return aName;
	}

	@Override
	public final double price()
	{
		return aPrice;
	}
	
	public final void setPrice(double pPrice) // maybe set this private, and have public void discount(double pRate) for onsale and sizeableitem
	{
		assert pPrice > 0;
		aPrice = pPrice;
	}

	@Override
	public final FoodCategory getFoodCategory()
	{
		return aFoodCategory;
	}
	
	@Override
	public final List<DietaryCategory> getDietaryCategories()
	{
		List<DietaryCategory> copy = new ArrayList<>();
		for (DietaryCategory dc : aDietaryCategories)
		{
			copy.add(dc);
		}
		return copy;
	}
	
	public final void addDietaryCategory(DietaryCategory pCategory) {
		assert pCategory != null;
		if (this.aDietaryCategories.isEmpty()) {
			this.aDietaryCategories.add(pCategory) ;
		}
		else if (!this.aDietaryCategories.contains(pCategory)) {
			this.aDietaryCategories.add(pCategory) ;
		}
	}	
	
	public final void removeDietaryCategory(DietaryCategory pCategory) {
		if (!this.aDietaryCategories.isEmpty() && this.aDietaryCategories.contains(pCategory)) {
			this.aDietaryCategories.remove(pCategory) ;
		}
	}
	
	public final void addDietaryCategories(DietaryCategory ... pCategories) {
		if (this.aDietaryCategories.isEmpty()) {
			this.aDietaryCategories.addAll(Arrays.asList(pCategories));
		}
		else {
			for (DietaryCategory category : pCategories) {
				 if (!this.aDietaryCategories.contains(category)) {
					this.aDietaryCategories.add(category) ;
				}
			}
		}
	}
	
	public final void removeDietaryCategories(DietaryCategory ... pCategories) {
		for (DietaryCategory category : pCategories) {
			if (!this.aDietaryCategories.isEmpty() && this.aDietaryCategories.contains(category)) {
				this.aDietaryCategories.remove(category) ;
			}
		}
	}
	
	@Override
	public final String toString()
	{
		return description();
	}
}
