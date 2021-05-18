package activity7;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestDisplay
{

	private Menu menu = new Menu();

	private RegularItem coke = new RegularItem("Coke",1.00,FoodCategory.DRINK);
	private RegularItem fish = new RegularItem("Fried fish", 4.00, FoodCategory.MAIN);
	private RegularItem chips = new RegularItem("Chips", 1.00, FoodCategory.SNACK);
	private ComboItem fishAndChips = new ComboItem("Fish & Chips", fish, chips);
	private RegularItem pasta = new RegularItem("Pasta", 4.50, FoodCategory.MAIN, DietaryCategory.HALAL);
	private SizeableItem spaghetti = new SizeableItem("Spaghetti", 5.75, FoodCategory.MAIN);

	@Test
	void test_addSingleItem() throws IOException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		assertFalse(menu.getItems().contains(fishAndChips));
		menu.addItem(fishAndChips);
		assertTrue(menu.getItems().contains(fishAndChips));
		String console1 = outContent.toString().trim();
		String msg1 = "added "+fishAndChips.description();
		assertEquals(console1, msg1);
		
		outContent.close();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));

		assertFalse(menu.getItems().contains(spaghetti));
		spaghetti.setSize(Size.LARGE);
		menu.addItem(spaghetti);
		assertTrue(menu.getItems().contains(spaghetti));
		String console2 = outContent2.toString().trim();
		String msg2 = "added "+spaghetti.description();
		assertEquals(console2, msg2);

		outContent2.close();
	}

	@Test
	@DisplayName("test_addMultipleItems(): passes for Windows; on Mac, change \"\\r\\n\" to \"\\n\"")
	void test_addMultipleItems() throws IOException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		assertFalse(menu.getItems().contains(fish));
		assertFalse(menu.getItems().contains(chips));
		menu.addItems(fish,chips);
		assertTrue(menu.getItems().contains(fish));
		assertTrue(menu.getItems().contains(chips));
		String console1 = outContent.toString().trim();
		String msg1 = "added "+fish.description()+"\r\n"+"added "+chips.description();
		assertEquals(console1, msg1);

		outContent.close();
	}

	@Test
	void test_addDuplicateItem() throws IOException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		assertFalse(menu.getItems().contains(coke));
		menu.addItem(coke);
		assertTrue(menu.getItems().contains(coke));
		String console1 = outContent.toString().trim();
		String msg1 = "added "+coke.description();
		assertEquals(console1, msg1);
		
		outContent.close();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));

		menu.addItem(coke);
		String console2 = outContent2.toString().trim();
		String msg2 = "duplicate item, not added";
		assertEquals(console2, msg2);

		outContent2.close();
		ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent3));
		
		menu.addItem(new RegularItem(coke));
		String console3 = outContent3.toString().trim();
		String msg3 = "duplicate item, not added";
		assertEquals(console3, msg3);

		outContent3.close();
	}

	@Test
	void test_removeItem() throws IOException {		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		assertFalse(menu.getItems().contains(pasta));
		menu.addItem(pasta);
		assertTrue(menu.getItems().contains(pasta));
		String console1 = outContent.toString().trim();
		String msg1 = "added "+pasta.description();
		assertEquals(console1, msg1);
		
		outContent.close();
		ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent2));

		menu.removeItem(pasta);
		assertFalse(menu.getItems().contains(pasta));
		String console2 = outContent2.toString().trim();
		String msg2 = "removed "+pasta.description();
		assertEquals(console2, msg2);

		outContent2.close();		
	}

}
