package activity7;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestConfigurations
{
	private static Configurations conf;
	
	@BeforeEach
	public void setup() {
		conf = new Configurations();
	}
	
	@Test
	public void testAddConfig()
	{
		assertEquals(conf.getSize(), 1);
		conf.addConfig("Vegan/Keto/Vegetarian",
				DietaryCategory.VEGAN,
				DietaryCategory.KETO,
				DietaryCategory.VEGETARIAN);
		assertEquals(conf.getSize(), 2);
	}
	
	@Test
	public void testGetConfigPosition_existant()
	{
		assertEquals(conf.getConfigPosition("Drink/Main/Snack"), 0);
		
		conf.addConfig("Vegan/Keto/Vegetarian",
				DietaryCategory.VEGAN,
				DietaryCategory.KETO,
				DietaryCategory.VEGETARIAN);
		
		assertEquals(conf.getConfigPosition("Vegan/Keto/Vegetarian"), 1);
	}
	
	@Test
	public void testGetConfigPosition_nonExistant()
	{
		assertEquals(conf.getConfigPosition("randomConfigname"), -1);
		assertEquals(conf.getConfigPosition("Drink/Keto/Snack"), -1);
	}
	
	@Test
	public void testGetName_GetPosition_GetEnums() {
		String name = conf.getConfigName(0);
		int i = conf.getConfigPosition(name);
		assertEquals(i, 0);
		assertEquals(conf.getLeft(i), FoodCategory.DRINK);
		assertEquals(conf.getCenter(i), FoodCategory.MAIN);
		assertEquals(conf.getRight(i), FoodCategory.SNACK);
	}
	
	@Test
	public void testRemoveConfig_NoChange()
	{
		// expected to not remove anything
		assertEquals(conf.getSize(), 1);
		conf.removeConfig(1);
		assertEquals(conf.getSize(), 1);
		conf.removeConfig(5);
		assertEquals(conf.getSize(), 1);
	}
	
	@Test
	public void testRemoveConfig_notAtZero()
	{
		conf.addConfig("Vegan/Keto/Vegetarian",
				DietaryCategory.VEGAN,
				DietaryCategory.KETO,
				DietaryCategory.VEGETARIAN);
		
		assertEquals(conf.getSize(), 2);
		conf.removeConfig(1);
		assertEquals(conf.getSize(), 1);
	}
	
	@Test
	public void testGetLeft()
	{
		assertEquals(conf.getLeft(0), FoodCategory.DRINK);
		
		conf.addConfig("Vegan/Keto/Vegetarian",
				DietaryCategory.VEGAN,
				DietaryCategory.KETO,
				DietaryCategory.VEGETARIAN);
		
		assertEquals(conf.getLeft(1), DietaryCategory.VEGAN);
	}
	
	@Test
	public void testGetCenter()
	{
		assertEquals(conf.getCenter(0), FoodCategory.MAIN);
		conf.addConfig("Vegan/Keto/Vegetarian",
				DietaryCategory.VEGAN,
				DietaryCategory.KETO,
				DietaryCategory.VEGETARIAN);
		assertEquals(conf.getCenter(1), DietaryCategory.KETO);
	}
	
	@Test
	public void testGetRight()
	{
		assertEquals(conf.getRight(0), FoodCategory.SNACK);
		conf.addConfig("Vegan/Keto/Vegetarian",
				DietaryCategory.VEGAN,
				DietaryCategory.KETO,
				DietaryCategory.VEGETARIAN);
		assertEquals(conf.getRight(1), DietaryCategory.VEGETARIAN);
	}
	
	@Test
	public void testGetAllConfigNames() {
		List<String> names = new ArrayList<>();
		for(int i=0; i<conf.getSize(); i++) {
			names.add(conf.getConfigName(i));
		}
		assertEquals(names, conf.getAllConfigNames());
	}
	
	@Test
	public void testField_aConfigNames() {
		conf.addConfig("Cheap/All/Combo", PriceCategory.ASCENDING, PriceCategory.ALL, FoodCategory.COMBO);
		conf.addConfig("Snack/Vegan/Expensive", FoodCategory.SNACK, DietaryCategory.VEGAN, PriceCategory.DESCENDING);
		conf.addConfig("Halal/Combo/Main", DietaryCategory.HALAL, FoodCategory.COMBO, FoodCategory.MAIN);
		assertEquals(getConfigNames(), conf.getAllConfigNames());
	}
	
	@Test
	public void testField_aLeft() {
		conf.addConfig("Cheap/All/Combo", PriceCategory.ASCENDING, PriceCategory.ALL, FoodCategory.COMBO);
		conf.addConfig("Snack/Vegan/Expensive", FoodCategory.SNACK, DietaryCategory.VEGAN, PriceCategory.DESCENDING);
		conf.addConfig("Halal/Combo/Main", DietaryCategory.HALAL, FoodCategory.COMBO, FoodCategory.MAIN);
		List<Category> left = new ArrayList<>();
		left.add(FoodCategory.DRINK);
		left.add(PriceCategory.ASCENDING);
		left.add(FoodCategory.SNACK);
		left.add(DietaryCategory.HALAL);
		assertEquals(getCategory("aLeft"), left);
	}
	
	@Test
	public void testField_aCenter() {
		conf.addConfig("Cheap/All/Combo", PriceCategory.ASCENDING, PriceCategory.ALL, FoodCategory.COMBO);
		conf.addConfig("Snack/Vegan/Expensive", FoodCategory.SNACK, DietaryCategory.VEGAN, PriceCategory.DESCENDING);
		conf.addConfig("Halal/Combo/Main", DietaryCategory.HALAL, FoodCategory.COMBO, FoodCategory.MAIN);
		List<Category> center = new ArrayList<>();
		center.add(FoodCategory.MAIN);
		center.add(PriceCategory.ALL);
		center.add(DietaryCategory.VEGAN);
		center.add(FoodCategory.COMBO);
		assertEquals(getCategory("aCenter"), center);
	}
	
	@Test
	public void testField_aRight() {
		conf.addConfig("Cheap/All/Combo", PriceCategory.ASCENDING, PriceCategory.ALL, FoodCategory.COMBO);
		conf.addConfig("Snack/Vegan/Expensive", FoodCategory.SNACK, DietaryCategory.VEGAN, PriceCategory.DESCENDING);
		conf.addConfig("Halal/Combo/Main", DietaryCategory.HALAL, FoodCategory.COMBO, FoodCategory.MAIN);
		List<Category> right = new ArrayList<>();
		right.add(FoodCategory.SNACK);
		right.add(FoodCategory.COMBO);
		right.add(PriceCategory.DESCENDING);
		right.add(FoodCategory.MAIN);
		assertEquals(getCategory("aRight"), right);
	}
	
	private static List<String> getConfigNames(){
		try
		{
			Field field = Configurations.class.getDeclaredField("aConfigNames");
			field.setAccessible(true);
			List<String> names = (List<String>) field.get(conf);
			return names;
		}
		catch (ReflectiveOperationException e)
		{
			fail();
			return null;
		}
	}
	
	private static List<Category> getCategory(String position){
		try
		{
			Field field = Configurations.class.getDeclaredField(position);
			field.setAccessible(true);
			List<Category> list = (List<Category>) field.get(conf);
			return list;
		}
		catch (ReflectiveOperationException e)
		{
			fail();
			return null;
		}
	}
	
}
