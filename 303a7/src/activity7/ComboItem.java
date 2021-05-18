package activity7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComboItem extends AbstractItem
{
	private List<RegularItem> aItems;
	private static double DISCOUNT = 0.10;

	/**
	 * Create a combo item whose name must be new but whose price is based off of the prices of its contained items.
	 * 
	 * @param pName String of name of this ComboItem
	 * @param 2 or more RegularItems
	 * @pre pName != null && items != null (for each item)
	 */
	public ComboItem(String pName, RegularItem pFirstItem, RegularItem pSecondItem, RegularItem...pAdditionalItems)
	{
		super(pName,
				discountedPrice(pFirstItem, pSecondItem, pAdditionalItems),
				FoodCategory.COMBO,
				inCommon(pFirstItem, pSecondItem, pAdditionalItems)
				);

		aItems = new ArrayList<RegularItem>();

		addItems(pFirstItem, pSecondItem);
		addItems(pAdditionalItems);
	}

	/**
	 * copy constructor
	 */
	public ComboItem(ComboItem pComboItem)
	{
		super(pComboItem);
		addItems(pComboItem.getItems());
	}

	/**
	 * Calculate the discounted price for the combo based on the price of the contained items.
	 * @param pFirstItem 
	 * @param pSecondItem
	 * @param pAdditionalItems
	 * @pre items != null (for each item)
	 */
	private static double discountedPrice(RegularItem pFirstItem, RegularItem pSecondItem, RegularItem...pAdditionalItems)
	{
		assert pFirstItem != null && pSecondItem != null;
		Arrays.asList(pAdditionalItems).forEach(item -> { assert item != null; });

		return (originalPrice(pFirstItem, pSecondItem) + originalPrice(pAdditionalItems)) * (1 - DISCOUNT);
	}

	private static double originalPrice(RegularItem...items) 
	{		
		Arrays.asList(items).forEach(item -> { assert item != null; });
		return (double) Arrays.asList(items)
				.stream()
				.mapToDouble(item -> item.price())
				.sum();
	}

	private static DietaryCategory[] inCommon(RegularItem pFirstItem, RegularItem pSecondItem, RegularItem...pRegularItems)
	{
		List<DietaryCategory> inCommon = pFirstItem.getDietaryCategories();
		inCommon.retainAll(pSecondItem.getDietaryCategories());
		for(RegularItem item : pRegularItems) inCommon.retainAll(item.getDietaryCategories());

		return inCommon.toArray(new DietaryCategory[0]);
	}

	/**
	 * Add an item to a combo
	 * @param pItem
	 * @pre item != null (for each item)
	 */
	private void addItems(RegularItem...pItems)
	{
		for (RegularItem item : pItems) {
			assert item != null;
			aItems.add(item);
		};
	}

	public RegularItem[] getItems()
	{
		return aItems.toArray(new RegularItem[0]);
	}

	@Override
	public String description()
	{
		String[] itemDescriptions = new String[aItems.size()];
		for (int i = 0; i < aItems.size(); i++) itemDescriptions[i] = aItems.get(i).description();

		return String.format("%s COMBO (%.0f%%, or $ %.2f off), with {%s}: $ %.2f",
				name(), DISCOUNT*100, originalPrice(aItems.toArray(new RegularItem[0])) - price(), String.join(", ", itemDescriptions), price());
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o.getClass() != this.getClass()) return false;
		List<RegularItem> arr1 = Arrays.asList(this.getItems());
		List<RegularItem> arr2 = Arrays.asList(((ComboItem)o).getItems());
		if(arr1.size() == arr2.size()) {
			for(RegularItem r: arr1) {
				if(!arr2.contains(r)) return false;
			}
		}
		else
		{
			return false;
		}
		return true;
	}
}
