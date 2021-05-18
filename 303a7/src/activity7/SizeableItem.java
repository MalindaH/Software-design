package activity7;

public class SizeableItem extends RegularItem
{
	private Size aSize = Size.REGULAR;
	private final static double ADJUSTMENT_FACTOR = 0.25;
	private double aAdjusted = 0;
	
	/**
	 * Create a SizeableItem without specifying the size.
	 * 
	 * @param pFoodCategory
	 * @param pDietaryCategory
	 * @param pName
	 * @param pPrice
	 * @pre pName != null && pPrice > 0 && pFoodCategory != null && categories != null (for each category)
	 */
	public SizeableItem(String pName, double pPrice, FoodCategory pFoodCategory, DietaryCategory...pDietaryCategories)
	{
		super(pName, pPrice, pFoodCategory, pDietaryCategories);
	}
	
	/**
	 * Creates a copy of a SizeableItem.
	 * 
	 * @param pSizeableItem
	 */
	public SizeableItem(SizeableItem pSizeableItem)
	{
		super(pSizeableItem);
		aAdjusted = pSizeableItem.getAdjusted();
		aSize = pSizeableItem.getSize();
	}
	
	/**
	 * Creates a copy of a SizeableItem with a newly-specified size and a price adjusted accordingly.
	 * 
	 * @param pSizeableItem
	 * @param pSize
	 */
	public SizeableItem(SizeableItem pSizeableItem, Size pSize)
	{
		this(pSizeableItem);
		setSize(pSize);
	}

	/**
	 * Adjust the item's price based on its size
	 */
	private void adjustPrice(Size pNewSize)
	{
		setPrice(price() - aAdjusted);
		aAdjusted = price() * ADJUSTMENT_FACTOR * (pNewSize.ordinal() - Size.REGULAR.ordinal());
		setPrice(price() + aAdjusted);
	}
	
	/**
	 * Set the size of the item.
	 * 
	 * @param pSize
	 * @pre pSize != null
	 */
	public void setSize(Size pSize)
	{
		assert pSize != null;
		adjustPrice(pSize);
		aSize = pSize;
	}
	
	public Size getSize()
	{
		return aSize;
	}
	
	private double getAdjusted()
	{
		return aAdjusted;
	}
	
	@Override
	public String description()
	{
		return String.format("[%s: (%s, $ %.2f from regular) $ %.2f]", name(), getSize(), getAdjusted(), price());
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o) && this.getSize()==((SizeableItem)o).getSize() && 
				this.getAdjusted()==((SizeableItem)o).getAdjusted();
	}
}
