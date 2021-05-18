package activity7;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestItems
{	
	private SizeableItem coke = new SizeableItem("Coke",1.00,FoodCategory.DRINK);
	private SizeableItem pasta = new SizeableItem("Pasta", 4.50, FoodCategory.MAIN, DietaryCategory.HALAL);
	
	private RegularItem fish = new RegularItem("Fried fish", 4.00, FoodCategory.MAIN);
	private RegularItem chips = new RegularItem("Chips", 1.00, FoodCategory.SNACK);
	private DietaryCategory [] d = {DietaryCategory.HALAL} ;

	@Test
	public void testEqualsRegular() {
		RegularItem fi1 = new RegularItem("nintendo switch", 1000D, FoodCategory.MAIN, DietaryCategory.HALAL ); 
		RegularItem fi2;
		try {
			Constructor<RegularItem> constr = RegularItem.class.getDeclaredConstructor(String.class, double.class, String.class, Collection.class);
			fi2 = constr.newInstance("nintendo switch", 123123D, "orange", Collections.emptyList());
			//Make sure the illegally procured instance is not the same.
			assertNotSame(fi1, fi2);
			//Make sure the equality check works for two separate instances.
			assertEquals(fi1, fi2);
		} catch (Exception ignored) { }
	}

	@Test
	public void testEqualsCombo() {
		ComboItem fi1 = new ComboItem("nintendo switch", fish,chips); 
		ComboItem fi2;
		try {
			Constructor<ComboItem> constr = ComboItem.class.getDeclaredConstructor(String.class, RegularItem.class, RegularItem.class);
			fi2 = constr.newInstance("nintendo switch", fish,chips);
			//Make sure the illegally procured instance is not the same.
			assertNotSame(fi1, fi2);
			//Make sure the equality check works for two separate instances.
			assertEquals(fi1, fi2);
		} catch (Exception ignored) { }
	}
	
	@Test
	public void testEqualsSizeable() {
		SizeableItem fi1 = new SizeableItem("nintendo switch", 1000D, FoodCategory.MAIN, DietaryCategory.HALAL ); 
		SizeableItem fi2;
		try {
			Constructor<SizeableItem> constr = SizeableItem.class.getDeclaredConstructor(String.class, double.class, String.class, Collection.class);
			fi2 = constr.newInstance("nintendo switch", 1000D,FoodCategory.MAIN, DietaryCategory.HALAL );
			//Make sure the illegally procured instance is not the same.
			assertNotSame(fi1, fi2);
			//Make sure the equality check works for two separate instances.
			assertEquals(fi1, fi2);
		} catch (Exception ignored) { }
	}

	@Test
	public void testGetDietCat() {
		RegularItem fi = new RegularItem("desgrbfniujb", 1999, FoodCategory.SNACK,d);
		
		List<DietaryCategory> hi = fi.getDietaryCategories() ;
		assertFalse(hi.isEmpty());

		
		assertTrue(fi.getDietaryCategories().contains(DietaryCategory.HALAL)) ;
	}


	@Test
	public void testGetFoodCat() {
		RegularItem fi = new RegularItem("desgrbfniujb", 1999, FoodCategory.SNACK,d);
		
		FoodCategory hi = fi.getFoodCategory() ;
		assertEquals(hi,FoodCategory.SNACK) ;

		
	}

	@Test
	public void testComboConstructor() {
		ComboItem c1 =  new ComboItem("Combo" , fish, chips) ;
		
		ComboItem c2 = new ComboItem("Combo" , fish, chips) ;
		assertEquals(c1,c2) ;
		
		ComboItem c3 = new ComboItem("Combo", coke, chips) ;
		assertNotEquals(c1,c3) ;
	}



	@Test
	void test_smallSizeItem()
	{
		double cokePrice = coke.price() - getAdjusted(coke);
		coke.setSize(Size.SMALL);
		assertEquals(coke.getSize(),Size.SMALL);
		assertEquals(getAdjusted(coke),cokePrice * getFactor(coke) * (Size.SMALL.ordinal() - Size.REGULAR.ordinal()));
		assertEquals(coke.price(), cokePrice + getAdjusted(coke));
		
		double pastaPrice = pasta.price() - getAdjusted(pasta);
		pasta.setSize(Size.SMALL);
		assertEquals(pasta.getSize(),Size.SMALL);
		assertEquals(getAdjusted(pasta),pastaPrice * getFactor(pasta) * (Size.SMALL.ordinal() - Size.REGULAR.ordinal()));
		assertEquals(pasta.price(), pastaPrice + getAdjusted(pasta));
	}
	
	@Test
	void test_largeSizeItem() {
		double cokeOriginalPrice = coke.price() - getAdjusted(coke);
		coke.setSize(Size.LARGE);
		assertEquals(coke.getSize(),Size.LARGE);
		assertEquals(getAdjusted(coke),cokeOriginalPrice * getFactor(coke) * (Size.LARGE.ordinal() - Size.REGULAR.ordinal()));
		assertEquals(coke.price(), cokeOriginalPrice + getAdjusted(coke));
		
		double pastaOriginalPrice = pasta.price() - getAdjusted(pasta);
		pasta.setSize(Size.LARGE);
		assertEquals(pasta.getSize(),Size.LARGE);
		assertEquals(getAdjusted(pasta),pastaOriginalPrice * getFactor(pasta) * (Size.LARGE.ordinal() - Size.REGULAR.ordinal()));
		assertEquals(pasta.price(), pastaOriginalPrice + getAdjusted(pasta));
	}
	
	@Test
	void test_comboItem() {
		ComboItem fishAndChips = new ComboItem("Fish & Chips", fish, chips);
		assertTrue(getComboItems(fishAndChips).contains(fish));
		assertTrue(getComboItems(fishAndChips).contains(chips));
		assertEquals(fishAndChips.price(), (fish.price()+chips.price())*(1-getDiscount(fishAndChips)));
		
		ComboItem combo = new ComboItem("Combo", fish, chips, coke, pasta);
		assertTrue(getComboItems(combo).contains(fish));
		assertTrue(getComboItems(combo).contains(chips));
		assertTrue(getComboItems(combo).contains(coke));
		assertTrue(getComboItems(combo).contains(pasta));
		assertEquals(combo.price(), (fish.price()+chips.price()+coke.price()+pasta.price())*(1-getDiscount(combo)));
	}
	
	@Test
	void test_specialItem() {
		double sale = 0.4;
		double chipsOriginalPrice = chips.price();
		chips.createSaleCommand(sale);
		Command cmd1 = chips.getSaleCommand();
		cmd1.execute();
		assertEquals(chips.price(),chipsOriginalPrice * (1 - sale));
		cmd1.undo();
		assertEquals(chips.price(),chipsOriginalPrice);
		
		sale = 0.5;
		ComboItem combo = new ComboItem("Combo", fish, chips, coke, pasta);
		double comboOriginalPrice = combo.price();
		combo.createSaleCommand(sale);
		Command cmd2 = combo.getSaleCommand();
		cmd2.execute();
		assertEquals(combo.price(),comboOriginalPrice * (1 - sale));
		cmd2.undo();
		assertEquals(combo.price(),comboOriginalPrice);
		
	}
	
	@Test
	void test_description() {
		RegularItem cola = new RegularItem(coke);
		assertTrue(cola.getClass()==RegularItem.class);
		assertEquals(cola.description(), String.format("[%s: $ %.2f]", cola.name(), cola.price()));
		
		ComboItem combo = new ComboItem("Combo", fish, chips, coke, pasta);
		String[] itemNames = new String[getComboItems(combo).size()];
		for (int i = 0; i < getComboItems(combo).size(); i++) itemNames[i] = getComboItems(combo).get(i).description();
		assertTrue(combo.getClass()==ComboItem.class);
		assertEquals(combo.description(), String.format("%s COMBO (%.0f%%, or $ %.2f off), with {%s}: $ %.2f",
				combo.name(), getDiscount(combo)*100, originalPrice(getComboItems(combo).toArray(new RegularItem[0])) - combo.price(), String.join(", ", itemNames), combo.price()));
		
		assertTrue(coke.getClass()==SizeableItem.class);
		assertEquals(coke.description(), String.format("[%s: (%s, $ %.2f from regular) $ %.2f]", coke.name(), coke.getSize(), getAdjusted(coke), coke.price()));
		
	}
	
	private static double originalPrice(RegularItem...items) 
	{		
		Arrays.asList(items).forEach(item -> { assert item != null; });
		return (double) Arrays.asList(items)
				.stream()
				.mapToDouble(item -> item.price())
				.sum();
	}
	
	
	private static double getAdjusted(SizeableItem item){
		try
		{
			Field field = SizeableItem.class.getDeclaredField("aAdjusted");
			field.setAccessible(true);
			double Adjusted = (double) field.get(item);
			return Adjusted;
		}
		catch (ReflectiveOperationException e)
		{
			fail();
			return 0;
		}
	}

	private static double getFactor(SizeableItem item){
		try
		{
			Field field = SizeableItem.class.getDeclaredField("ADJUSTMENT_FACTOR");
			field.setAccessible(true);
			double Factor = (double) field.get(item);
			return Factor;
		}
		catch (ReflectiveOperationException e)
		{
			fail();
			return 0;
		}
	}
	
	private static double getDiscount(ComboItem item){
		try
		{
			Field field = ComboItem.class.getDeclaredField("DISCOUNT");
			field.setAccessible(true);
			double Discount = (double) field.get(item);
			return Discount;
		}
		catch (ReflectiveOperationException e)
		{
			fail();
			return 0;
		}
	}
	
	private static List<RegularItem> getComboItems(ComboItem item){
		try
		{
			Field field = ComboItem.class.getDeclaredField("aItems");
			field.setAccessible(true);
			List<RegularItem> ComboItems = (List<RegularItem>) field.get(item);
			return ComboItems;
		}
		catch (ReflectiveOperationException e)
		{
			fail();
			return null;
		}
	}
}