package activity7;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMenu
{
	
	private final Menu menu = new Menu();
	private final ItemStub stub = new ItemStub();
	
	class ItemStub implements MenuItem
	{
		@Override
		public String description()
		{
			return "Stub description.";
		}

		@Override
		public double price()
		{
			return 0;
		}

		@Override
		public String name()
		{
			return "Stub";
		}

		@Override
		public List<DietaryCategory> getDietaryCategories()
		{
			return null;
		}

		@Override
		public FoodCategory getFoodCategory()
		{
			return null;
		}

		@Override
		public boolean isOnSale()
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void createSaleCommand(double pSale)
		{
			
		}

		@Override
		public Command getSaleCommand()
		{
			return null;
		}
	}
	
	@BeforeEach
	public void setup()
	{
		try
		{
			Field field = Menu.class.getDeclaredField("aMenu");
			field.setAccessible(true);
			ArrayList<MenuItem> aMenu = (ArrayList<MenuItem>) field.get(menu);
			aMenu.clear();
			field.set(menu, aMenu);
		}
		catch (ReflectiveOperationException e)
		{
			fail();
		}
	}
	
	@Test
	public void testAddItem()
	{
		menu.addItem(stub);
		assertEquals(stub, getItemFromList());
	}
	
	@Test
	public void testAddItem_sameItemTwice()
	{
		menu.addItem(stub);
		menu.addItem(stub);
		assertEquals(1, getItemList().size());
	}
	
	@Test
	public void testRemoveItem_contains()
	{
		setItemList();
		menu.removeItem(stub);
		assertEquals(0, getItemList().size());
	}
	
	@Test
	public void testRemoveItem_doesNotContain()
	{
		setItemList();
		menu.removeItem(new ItemStub());
		assertEquals(1, getItemList().size());
	}
	
	@Test
	public void testGetItems()
	{
		setItemList();
		assertEquals(getItemList(), menu.getItems());
	}
	
	@Test
	public void testFilterMenuItems_onePredicate()
	{
		setItemList();
		List<MenuItem> list = menu.filtermenuItems(item -> ((MenuItem) item).name().equals("Stub"));
		assertEquals(stub, list.get(0));
	}
	
	@Test
	public void testFilterMenuItems_multiplePredicates()
	{
		setItemList();
		Predicate<MenuItem> pred1 = item -> item.name().equals("Stub");
		Predicate<MenuItem> pred2 = item -> item.description().equals("Stub description.");
		List<MenuItem> list = menu.filtermenuItems(Arrays.asList(pred1, pred2));
		assertEquals(stub, list.get(0));
	}
	
	@Test
	public void testDoes_a_contain_b_true()
	{
		DietaryCategory[] dc = DietaryCategory.values();
		List<DietaryCategory> list = new ArrayList<DietaryCategory>();
		Collections.addAll(list, dc);
		list.stream()
			.forEach(item -> 
				assertTrue(Menu.does_a_contain_b(list, item)));
	}
	
	@Test
	public void testDoes_a_contain_b_false()
	{
		DietaryCategory[] dc = DietaryCategory.values();
		List<DietaryCategory> listSome = new ArrayList<DietaryCategory>();
		Collections.addAll(listSome, dc);
		List<DietaryCategory> listAll = new ArrayList(listSome);
		listSome.remove(0);
		assertFalse(Menu.does_a_contain_b(listSome, listAll.toArray(new DietaryCategory[0])));
	}
	
	private ArrayList<MenuItem> getItemList()
	{
		try
		{
			Field field = Menu.class.getDeclaredField("aMenu");
			field.setAccessible(true);
			return (ArrayList<MenuItem>) field.get(menu);
		}
		catch (ReflectiveOperationException e)
		{
			fail();
			return null;
		}
	}
	
	private void setItemList()
	{
		try
		{
			Field field = Menu.class.getDeclaredField("aMenu");
			field.setAccessible(true);
			ArrayList<MenuItem> aMenu = (ArrayList<MenuItem>) field.get(menu);
			aMenu.add(stub);
			field.set(menu, aMenu);
		}
		catch (ReflectiveOperationException e)
		{
			fail();
		}
	}
	
	private MenuItem getItemFromList()
	{
		try
		{
			Field field = Menu.class.getDeclaredField("aMenu");
			field.setAccessible(true);
			ArrayList<MenuItem> aMenu = (ArrayList<MenuItem>) field.get(menu);
			return aMenu.get(0);
		}
		catch (ReflectiveOperationException e)
		{
			fail();
			return null;
		}
	}
	
}
