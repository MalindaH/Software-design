package activity7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a menu that can be displayed in the menu display.
 */
public class Menu implements Iterable<MenuItem>
{
	private static List<MenuItem> aMenu = new ArrayList<MenuItem>();
	
	/**
	 * @param pItem
	 * @pre pItem != null
	 */
	public void addItem(MenuItem pItem)
	{
		assert pItem != null;
		if (!aMenu.contains(pItem)) {
			aMenu.add(pItem);
			MenuDisplay.updateView();
			System.out.println("added "+pItem);
		} else {
			System.out.println("duplicate item, not added");
		}
	}
	
	/**
	 * @param pItem
	 * @pre pItem != null
	 */
	public void removeItem(MenuItem pItem)
	{
		assert pItem != null;	
		if(aMenu.contains(pItem)) {
			aMenu.remove(pItem);
			MenuDisplay.updateView();
			System.out.println("removed "+pItem);
		}	
	}	

	public void addItems(MenuItem...items)
	{
		for(MenuItem item : items) {
			this.addItem(item);
		}
	}
	
	
	public List<MenuItem> getItems()
	{
		return new ArrayList<MenuItem>(aMenu);
	}
	
	//if a does not contain one item in b -> false
	//if all item in b are contained in a -> true
	public static boolean does_a_contain_b(List<DietaryCategory> a, DietaryCategory...b) {
		boolean contains;
		for (DietaryCategory s:b) {
			contains = a.contains(s);
			if (contains==false) {
				return false;
			}
		}
		return true;
	}
	
	public List<MenuItem> filtermenuItems(List<Predicate<MenuItem>> allPredicates){
		List<MenuItem> items = new ArrayList<MenuItem>(); 
		allPredicates.stream().forEach(p -> items.addAll(filtermenuItems(p)));
		List<MenuItem> result = items.stream().distinct().collect(Collectors.toList());
		return result;
	}
	
	public List<MenuItem> filtermenuItems(Predicate<MenuItem> pre){
		 List<MenuItem> result = (List<MenuItem>) aMenu.stream().filter(pre).collect(Collectors.toList());
		 return result;
	}

	public static Predicate<MenuItem> isFoodCategory(FoodCategory pCategory){
		return c -> (c.getFoodCategory() == pCategory);
	}
	
	public static Predicate<MenuItem> isDietaryCategory(DietaryCategory ... pCategories){
		return c -> does_a_contain_b(c.getDietaryCategories(), pCategories);
	}
	
	public static Predicate<MenuItem> isPrice(double pPrice){
		return c -> c.price() == pPrice;
	}
	
	public static Predicate<MenuItem> isInRange(double pLower, double pUpper){
		assert pLower <= pUpper;
		return c -> (pLower<=c.price())&&(pUpper>=c.price());
	}
	
	/**
	 * filter items in the menu in ascending-price order
	 */
	public List<MenuItem> filterByAscendingPrice()
	{
		List<MenuItem> result = aMenu.stream()
				.sorted(Comparator.comparing(MenuItem::price))
				.collect(Collectors.toList());
		return result;
	}
	
	/**
	 * get the first n items in the menu in ascending-price order
	 * @pre n >= 0;
	 * @param number
	 */
	public List<MenuItem> filterByAscendingPrice(int n)
	{
		assert n>=0;
		List<MenuItem> result;
		if(n<=aMenu.size()) {
			result = this.filterByAscendingPrice().subList(0, n);
		}else {
			result = this.filterByAscendingPrice();
		}
		return result;
	}
	
	/**
	 * filter items in the menu in descending-price order
	 */
	public List<MenuItem> filterByDescendingPrice()
	{
		List<MenuItem> result = aMenu.stream()
				.sorted(Comparator.comparing(MenuItem::price))
				.collect(Collectors.toList());
		Collections.reverse(result);
		return result;
	}
	
	/**
	 * get the first n items in the menu in descending-price order
	 * @pre n >= 0;
	 * @param number
	 */
	public List<MenuItem> filterByDescendingPrice(int n)
	{
		assert n>=0;
		List<MenuItem> result;
		if(n<=aMenu.size()) {
			result = this.filterByDescendingPrice().subList(0, n);
		}else {
			result = this.filterByDescendingPrice();
		}
		return result;
	}
	
	@Override
	public Iterator<MenuItem> iterator()
	{
		List<MenuItem> duplicate = new ArrayList<>();
		
		for (MenuItem item : aMenu) {
			duplicate.add(item);
			
		}
		return duplicate.iterator();
	}
	
	
	public static void main (String [] args) {
		// tests
		Menu menu = new Menu();
		
		RegularItem chips = new RegularItem("Chips", 10.50, FoodCategory.SNACK, DietaryCategory.HALAL);
		RegularItem chips1 = new RegularItem("Chips1", 3.50, FoodCategory.SNACK, DietaryCategory.VEGETARIAN);
		RegularItem chips2 = new RegularItem("Chips2", 4.50, FoodCategory.SNACK, DietaryCategory.HALAL, DietaryCategory.VEGETARIAN);
		menu.addItem(chips);
		menu.addItem(chips1);
		menu.addItem(chips2);
		RegularItem chips3 = new RegularItem("Chips3", 9.50, FoodCategory.SNACK);
		//Menu.removeItem(chips2);
		menu.addItem(chips3);
		//filtermenuItems(Menu.isDietaryCategory(DietaryCategory.HALAL,DietaryCategory.VEGETARIAN)).forEach(System.out::println);
		//filtermenuItems(Menu.isDietaryCategory(DietaryCategory.HALAL,DietaryCategory.VEGETARIAN)).forEach(System.out::println);
		
		List<Predicate<MenuItem>> l = new ArrayList<Predicate<MenuItem>>();
		l.add((Predicate<MenuItem>) Menu.isDietaryCategory(DietaryCategory.HALAL));
		l.add((Predicate<MenuItem>) Menu.isDietaryCategory(DietaryCategory.VEGETARIAN));
		
		menu.filtermenuItems(l).forEach(System.out::println);
		
		menu.filterByAscendingPrice();
		System.out.println(menu.getItems().toString());
		
		// check can't add duplicate item
		menu.addItem(new RegularItem("Chips", 10.50, FoodCategory.SNACK, DietaryCategory.HALAL));
		System.out.println(menu.getItems().toString());
		
		
		// area2
		Menu menu2 = new Menu();
		RegularItem chipss = new RegularItem("Chips", 1.50, FoodCategory.SNACK, DietaryCategory.VEGETARIAN);
		SizeableItem burger = new SizeableItem("Burger", 3.50, FoodCategory.MAIN);
		ComboItem bigMeal = new ComboItem("Burger+chips", new SizeableItem(burger, Size.LARGE), chipss);
		ComboItem smallMeal = new ComboItem("Burger+chips", new SizeableItem(burger, Size.SMALL), chipss);
		menu2.addItems(chipss, burger, bigMeal, smallMeal);
		menu2.getItems().forEach(System.out::println);
		
		
		// to be removed-- sample code to see if price is correct for changing size
		System.out.println("\n"+burger);
		burger.setSize(Size.LARGE);
		System.out.println(burger);
		burger.setSize(Size.REGULAR);
		System.out.println(burger);
		burger.setSize(Size.SMALL);
		System.out.println(burger);
		burger.setSize(Size.LARGE);
		System.out.println(burger);
		burger.setSize(Size.LARGE);
		System.out.println(burger+"\n");
		
		SizeableItem burger2 = new SizeableItem(burger, Size.LARGE);
		System.out.println(burger2);
		burger2.setSize(Size.LARGE);
		System.out.println(burger2);
		burger2.setSize(Size.SMALL);
		System.out.println(burger2+"\n");
		
		SizeableItem burger3 = new SizeableItem(burger2);
		System.out.println(burger3);
		burger3.setSize(Size.LARGE);
		System.out.println(burger3);
		burger3.setSize(Size.REGULAR);
		System.out.println(burger3);
		
		// check can't add duplicate item
//		System.out.println(menu2.getItems().toString());
		SizeableItem s1 = new SizeableItem("Burger", 3.50, FoodCategory.MAIN);
		s1.setSize(Size.SMALL);
		SizeableItem s2 = new SizeableItem("Burger", 3.50, FoodCategory.MAIN);
		s2.setSize(Size.SMALL);
		menu2.addItem(s1);
		menu2.addItem(s2);
//		System.out.println(menu2.getItems().toString());
	}
	

}


