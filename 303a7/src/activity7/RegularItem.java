package activity7;

import java.util.ArrayList;

public class RegularItem extends AbstractItem
{
	/**
	 * constructs a RegularItem
	 * @param pName String representing name of this RegularItem
	 * @param pPrice double representing price
	 * @param pFoodCategory FoodCategory
	 * @param pCategories any number of DietaryCategories
	 * @pre pFoodCategory != null && pCategories != null
	 */
	public RegularItem(String pName, double pPrice, FoodCategory pFoodCategory, DietaryCategory... pCategories) 
	{
		super(pName, pPrice, pFoodCategory, pCategories);
	}
	
	/**
	 * copy constructor
	 */
	public RegularItem(RegularItem aRegularItem) {
		this(aRegularItem.name(), aRegularItem.price(), aRegularItem.getFoodCategory());
		ArrayList<DietaryCategory> dc = new ArrayList<DietaryCategory>(aRegularItem.getDietaryCategories());
		this.addDietaryCategories(dc.toArray(new DietaryCategory[dc.size()]));
	}

	@Override
	public String description()
	{
		return String.format("[%s: $ %.2f]", name(), price());
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o.getClass() != this.getClass()) return false;
		if(this.getDietaryCategories().size() == ((RegularItem)o).getDietaryCategories().size()) {
			for(DietaryCategory d: this.getDietaryCategories()) {
				if(!((RegularItem)o).getDietaryCategories().contains(d)) return false;
			}
		}
		return (this.getFoodCategory() == ((RegularItem)o).getFoodCategory()) && 
				this.name().equals(((RegularItem)o).name()) && this.price()==((RegularItem)o).price(); 
	}
	
}
