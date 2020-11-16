package activity5;

import static org.junit.Assert.*;

import java.io.File;
import java.util.*;

import org.junit.jupiter.api.*;
import activity5.Interface.Command;

import java.lang.reflect.Field;

public class TestProblem3
{
	private static final File f1 = new File("filepath1.wav");
	private static final File f2 = new File("filepath2.wav");
	private static final File f3 = new File("filepath3.wav");
	private static Song s1;
	private static Song s2;
	private static Song s3;
	private static Album a1;
	private static Playlist p1;
	private static Library lib;
	private static List<Playable> items_in_lib = new ArrayList<>();
	private static Stack<Command> command;
	private static Stack<Command> undone;

	@BeforeEach
	public void init()
	{
		s1 = new Song(f1, "Song Title_1");
		s2 = new Song(f2, "Song Title_2");
		s3 = new Song(f3, "Song Title_3");
		a1 = new Album("Album Title", "Album Artist");
		p1 = new Playlist("Playlist 1");
		lib = Library.getInstance();
		items_in_lib = getItemsInLibrary();	
		command = getCommand();
		undone = getUndone();
	}

	@DisplayName("Test adding song command to Library")
	@Test
	public void testAddSongToLibrary()
	{
		int size1 = command.size();
		int size2 = undone.size();
		assertEquals(items_in_lib.size(), 0);
		lib.ADD(s1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		lib.ADD(p1).execute();
		lib.ADD(a1).execute();
		assertTrue(items_in_lib.contains(s1) && items_in_lib.contains(s2)
				&& items_in_lib.contains(s3) && items_in_lib.contains(a1) 
				&& items_in_lib.contains(p1) && lib.getSize() == 5);
		assertEquals(command.size(),size1+5);
		assertEquals(undone.size(),size2);
	}

	@DisplayName("Test undo adding song command to Library")
	@Test
	public void testUndoAddSongToLibrary()
	{
		lib.ADD(s1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		int size1 = command.size();
		int size2 = undone.size();
		int size_1 = items_in_lib.size();
		lib.Undo();
		lib.Undo();
		int size_2 = items_in_lib.size();
		assertTrue(size_1 == 3 && size_2 == 1 
				&& items_in_lib.contains(s1));
		assertFalse(items_in_lib.contains(s2)
				|| items_in_lib.contains(s3));
		assertEquals(command.size(),size1-2);
		assertEquals(undone.size(),size2+2);
	}

	@DisplayName("Test redo adding song command to Library")
	@Test
	public void testRedoAddSongToLibrary()
	{
		lib.ADD(s1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		lib.Undo();
		lib.Undo();
		int size1 = command.size();
		int size2 = undone.size();
		lib.Redo();
		lib.Redo();
		assertTrue(items_in_lib.size() == 3 && items_in_lib.contains(s1)
				&& items_in_lib.contains(s2) && items_in_lib.contains(s3));
		assertEquals(command.size(),size1+2);
		assertEquals(undone.size(),size2-2);
	}

	@DisplayName("Test removing song command to Library")
	@Test
	public void testRemoveSongInLibrary()
	{
		lib.ADD(s1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		lib.ADD(p1).execute();
		lib.ADD(a1).execute();
		int size1 = command.size();
		int size2 = undone.size();
		lib.REMOVE(s1).execute();
		lib.REMOVE(p1).execute();
		assertTrue(items_in_lib.size() == 3 
				&& items_in_lib.contains(s2) 
				&& items_in_lib.contains(s3) && items_in_lib.contains(a1));
		assertEquals(command.size(),size1+2);
		assertEquals(undone.size(),size2);
	}

	@DisplayName("Test undo removing song command to Library")
	@Test
	public void testUndoRemoveSongToLibrary()
	{
		lib.ADD(s1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		lib.REMOVE(s1).execute();
		int size1 = command.size();
		int size2 = undone.size();
		int size_1 = items_in_lib.size();
		lib.Undo();
		int size_2 = items_in_lib.size();
		assertTrue(size_1 == 2 && size_2 == 3 && items_in_lib.contains(s1) && items_in_lib.contains(s2)
				&& items_in_lib.contains(s3));
		assertEquals(command.size(),size1-1);
		assertEquals(undone.size(),size2+1);
	}

	@DisplayName("Test redo removing song command to Library")
	@Test
	public void testRedoRemoveSongToLibrary()
	{
		lib.ADD(s1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		lib.REMOVE(s1).execute();
		lib.Undo();
		int size1 = command.size();
		int size2 = undone.size();
		int size_1 = items_in_lib.size();
		lib.Redo();
		int size_2 = items_in_lib.size();
		assertTrue(size_1 == 3 && size_2 == 2 
				&& items_in_lib.contains(s2) 
				&& items_in_lib.contains(s3));
		assertEquals(command.size(),size1+1);
		assertEquals(undone.size(),size2-1);
	}

	@DisplayName("Test excessive undo methods in Library")
	@Test
	public void testExcessiveUndoInLibrary()
	{
		int size_1 = items_in_lib.size();
		lib.ADD(s1).execute();
		int size_2 = items_in_lib.size();
		int size1 = command.size();
		int size2 = undone.size();
		lib.Undo();
		int size_3 = items_in_lib.size();
		lib.Undo();
		int size_4 = items_in_lib.size();
		assertTrue(size_1 == 0 && size_2 == 1 && size_3 == 0 && size_4 == 0);
		assertEquals(command.size(),size1-2);
		assertEquals(undone.size(),size2+2);
	}

	@DisplayName("Test excessive redo methods in Library")
	@Test
	public void testExcessiveRedoInLibrary()
	{
		int size_1 = items_in_lib.size(); // size of an empty library
		lib.ADD(s1).execute();
		int size_2 = items_in_lib.size(); // size of library with 1 song
		lib.Undo();
		int size_3 = items_in_lib.size(); // undo ADD, library should be empty
		int size1 = command.size();
		int size2 = undone.size();
		lib.Redo();
		int size_4 = items_in_lib.size(); // redo ADD, library now has 1 song
		lib.Redo();
		int size_5 = items_in_lib.size(); // excessive redo. Should make no change.
		assertTrue(size_1 == 0 && size_2 == 1 && size_3 == 0 && 
				size_4 == 1 && size_5 == 1);
		assertEquals(command.size(),size1+2);
		assertEquals(undone.size(),size2-2);
	}

	@DisplayName("Test Undo adding with given number of steps in Library")
	@Test
	public void testUndoStepsADDInLibrary()
	{
		int size_1 = items_in_lib.size();
		lib.ADD(s1).execute();
		lib.ADD(p1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		lib.ADD(a1).execute();
		int size_2 = items_in_lib.size();
		int size1 = command.size();
		int size2 = undone.size();
		lib.Undo(3);
		int size_3 = items_in_lib.size();
		assertTrue(size_1 == 0 && size_2 == 5 && size_3 == 2 
				&& items_in_lib.contains(s1) && items_in_lib.contains(p1));
		assertEquals(command.size(),size1-3);
		assertEquals(undone.size(),size2+3);
	}

	@DisplayName("Test Redo adding with given number of steps in Library")
	@Test
	public void testRedoStepsADDInLibrary()
	{
		lib.ADD(s1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		lib.ADD(a1).execute();
		lib.Undo(3);
		int size_1 = items_in_lib.size();
		int size1 = command.size();
		int size2 = undone.size();
		lib.Redo(2);
		int size_2 = items_in_lib.size();
		assertTrue(size_1 == 1 && size_2 == 3 && items_in_lib.contains(s1) && items_in_lib.contains(s2)
				&& items_in_lib.contains(s3));
		assertEquals(command.size(),size1+2);
		assertEquals(undone.size(),size2-2);
	}

	@DisplayName("Test Undo adding with given number of steps in Library")
	@Test
	public void testUndoStepsREMOVEInLibrary()
	{
		int size_1 = items_in_lib.size();
		lib.ADD(s1).execute();
		lib.ADD(s2).execute();
		lib.ADD(s3).execute();
		int size_2 = items_in_lib.size();
		lib.REMOVE(s1).execute();
		lib.REMOVE(s2).execute();
		lib.REMOVE(s3).execute();
		int size_3 = items_in_lib.size();
		int size1 = command.size();
		int size2 = undone.size();
		lib.Undo(3);
		int size_4 = items_in_lib.size();
		assertTrue(size_1 == 0 && size_2 == 3 
				&& size_3 == 0 && size_4 == 3
				&& items_in_lib.contains(s1)
				&& items_in_lib.contains(s2)
				&& items_in_lib.contains(s3));
		assertEquals(command.size(),size1-3);
		assertEquals(undone.size(),size2+3);
	}

	@DisplayName("Test Undo adding a playable originally in Library")
	@Test
	public void testUndoExceedADDinLibrary()
	{
		int size_1 = items_in_lib.size();
		lib.ADD(s1).execute();
		lib.ADD(s1).execute(); // here only add once
		int size_2 = items_in_lib.size();
		int size1 = command.size();
		int size2 = undone.size();
		lib.Undo();
		boolean contains = items_in_lib.contains(s1);
		int size_3 = items_in_lib.size();
		lib.Undo(); // this undo is not executed
		int size_4 = items_in_lib.size();
		assertTrue(size_1 == 0 && size_2 == 1 && size_3 == 0 && size_4 == 0 && !contains);
		assertEquals(command.size(),size1-1);
		assertEquals(undone.size(),size2+1);
	}

	@DisplayName("Test Undo removing a playable originally not in Library")
	@Test
	public void testUndoExceedREMOVEinLibrary()
	{
		int size_1 = items_in_lib.size();
		lib.ADD(s1).execute();
		int size1 = command.size();
		int size2 = undone.size();
		lib.REMOVE(s2).execute(); // s2 is not in the library, so not executed
		int size_2 = items_in_lib.size();
		assertEquals(command.size(),size1);
		assertEquals(undone.size(),size2);
		lib.Undo();
		int size_3 = items_in_lib.size();
		assertTrue(size_1 == 0 && size_2 == 1 && size_3 == 0 && 
				!items_in_lib.contains(s1) && !items_in_lib.contains(s2));
		assertEquals(command.size(),size1-1);
		assertEquals(undone.size(),size2+1);
	}

	// use reflection to access internal states
	@SuppressWarnings("unchecked")
	public List<Playable> getItemsInLibrary(){
		try {
			Field field1 = Library.class.getDeclaredField("aPlayables");
			field1.setAccessible(true);
			return (List<Playable>) field1.get(lib);
		} catch (ReflectiveOperationException e) {
			fail();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Stack<Command> getCommand(){
		try {
			Field field1 = Library.class.getDeclaredField("aCommand");
			field1.setAccessible(true);
			return (Stack<Command>) field1.get(lib);
		} catch (ReflectiveOperationException e) {
			fail();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Stack<Command> getUndone(){
		try {
			Field field1 = Library.class.getDeclaredField("aUndone");
			field1.setAccessible(true);
			return (Stack<Command>) field1.get(lib);
		} catch (ReflectiveOperationException e) {
			fail();
			return null;
		}
	}
}
