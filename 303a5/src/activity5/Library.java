package activity5;

import activity5.Interface.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Represents a collection of songs, playlists, and albums.
 */
public class Library implements Iterable<Playable>
{
	private static Library instance = null;
	private final List<Playable> aPlayables = new ArrayList<>();
	private static final Stack<Command> aCommand = new Stack<>(); // command stack
	private static final Stack<Command> aUndone = new Stack<>(); // redo stack

	/**
	 * Creates a new empty library.
	 */
	private Library() {}

	/**
	 * 
	 * @return library singleton
	 */
	public static Library getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		else
			return new Library();

	}

	/**
	 * Adds a playable item to the library.
	 * 
	 * @param pPlayable
	 *            a playable item (song, album, playlist)
	 * @pre pPlayable != null
	 */
	private boolean addItem(Playable pPlayable)
	{
		assert pPlayable != null;
		if (aPlayables.contains(pPlayable))
		{
			return false; // already contains the object
		}
		else {
			aPlayables.add(pPlayable);
			return true;
		}
	}

	/**
	 * Removes a playable item from the library.
	 * 
	 * @param pPlayable
	 *            a playable item (song, album, playlist)
	 */
	private boolean removeItem(Playable pPlayable)
	{
		if (this.aPlayables.contains(pPlayable))
		{
			this.aPlayables.remove(pPlayable);
			return true;
		}
		else
			return false;
	}

	@Override
	public Iterator<Playable> iterator()
	{
		return new ArrayList<>(aPlayables).iterator();
	}

	/**
	 * Command: adds a playable item to the library
	 * 
	 * @param pPlayable
	 *            a playable item (song, album, playlist)
	 * @return a new command
	 * @pre pPlayable != null ;
	 */
	public Command ADD(Playable pPlayable)
	{
		assert pPlayable != null;
		return new Command() {
			private boolean success;

			@Override
			public void execute()
			{
				System.out.println("adding " + pPlayable.description() + " in library");
				success = addItem(pPlayable); // adding playable in the library
				if (success)
				{
					aCommand.push(this);
				}
				// else don't push it on stack because add operation was not performed
			}

			@Override
			public void undo()
			{
				System.out.println("undo the last ADD command");
				aUndone.push(aCommand.pop());
				removeItem(pPlayable); // remove the playable in the library as part of the undo operation
			}
		};
	}

	/**
	 * Command: removes a playable item from the library
	 * 
	 * @param pPlayable
	 *            a playable item (song, album, playlist)
	 * @return removed command
	 * @pre pPlayable != null ;
	 */
	public Command REMOVE(Playable pPlayable)
	{
		assert pPlayable != null;
		return new Command()
		{
			private boolean success;

			@Override
			public void execute()
			{
				System.out.println("removing " + pPlayable.description() + " from the library");
				success = removeItem(pPlayable); // removing playable from the library
				if (success)
				{
					aCommand.push(this);
				}else
					// else don't push it on stack because operation was never performed
				{
					System.out.println("Command invalid ! Library does not contain " + pPlayable.description());
				}

			}

			@Override
			public void undo()
			{
				System.out.println("undo the last REMOVE command");
				aUndone.push(aCommand.pop());
				addItem(pPlayable); // add back the playable in the library as part of the undo operation
			}
		};
	}

	/**
	 * undo for specific number of steps
	 * 
	 * @param times
	 */
	public void Undo(int times)
	{
		if (times > aCommand.size()) {
			System.out.println("no commands to undo");
		} else {
			for (int i = 0; i < times; i++)
			{
				Command command = aCommand.peek();
				command.undo();
			}
		}
	}

	/**
	 * undo the last command
	 */
	public void Undo()
	{
		Undo(1);
	}

	/**
	 * redo for specific number of steps
	 * 
	 * @param times
	 */
	public void Redo(int times)
	{
		assert times != aUndone.size();
		if (times > aUndone.size())
		{
			System.out.println("no commands to redo");
		} else {
			for (int i = 0; i < times; i++)
			{
				Command cmd = aUndone.pop();
				cmd.execute();
			}
		}
	}

	/**
	 * redo the last command
	 */
	public void Redo()
	{
		Redo(1);
	}

	/**
	 * Returns the size of the library.
	 * 
	 * @return size of playables array in library
	 */
	public int getSize()
	{
		return this.aPlayables.size();
	}

	/**
	 * Returns if the library is empty.
	 * 
	 * @return true/false if library is empty
	 */
	public boolean isEmpty()
	{
		return getSize() == 0; // if empty return true

	}

	/**
	 * Print list of playables in library
	 * 
	 */
	public void printLibrary()
	{
		if (!isEmpty()) {
			System.out.println("printLibrary:\n"+this.toString());
		} else {
			System.out.println("printLibrary: Library is empty and contains no playable items.");
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Playable playable : this.aPlayables)
		{
			str.append(playable.description()+"\n");
		}
		return str.toString();
	}

	public void getaCommandSize()
	{
		System.out.println(Library.aCommand.size());
	}

	public void getaUndoSize()
	{
		System.out.println(Library.aUndone.size());
	}

}
