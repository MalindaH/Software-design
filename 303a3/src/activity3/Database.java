package activity3;

/**
 * This class is a mock-up for a database facade. 
 */
public class Database 
{
	public static void backup(Library pLibrary) 
	{
		System.out.println("++ copying information to a database ++");
	}

	public static void restore(Library pLibrary) {
		System.out.println("++ restoring information from a database to library ++");
	}
}