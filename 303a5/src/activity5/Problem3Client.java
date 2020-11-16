package activity5;

import java.io.*;

public class Problem3Client
{
	public static void main(String[] args)
	{
		// System.out.println("entry in the main methods");
		System.out.println("Starting client tests for library method");
		Library lib = Library.getInstance();
		Song song1 = new Song(new File("./song1.mp3"), "Title1");
		Song song2 = new Song(new File("./song2.mp3"), "Title2");
		Song song3 = new Song(new File("./song3.mp3"), "Title3");
		Song song4 = new Song(new File("./song4.mp3"), "Title4");

		Album album1 = new Album("Album1", "AlbumArtist_1");
		Playlist playlist1 = new Playlist("Playlist_1");

		System.out.println("----------------------------------");
		System.out.println("     LIBRARY ADD Command TEST     ");
		System.out.println("----------------------------------");
		lib.ADD(song1).execute();
		lib.ADD(song2).execute();
		lib.ADD(song2).execute();// should not put new command in stack if add operation is wrong :: correct
		System.out.println("\n<< .... Library state .... >>\n");
		System.out.println("\nnumber of commands in stack -> \n");
		lib.getaCommandSize();
		lib.printLibrary();
		System.out.println("\n--- UNDO [ should remove song2 ]\n");
		lib.Undo();
		System.out.println("\n<< .... Library state .... >>\n");
		lib.printLibrary();
		System.out.println("\n--- REDO [ should add song2 back ]\n");
		lib.Redo();
		System.out.println("\n<< .... Library state .... >>\n");
		lib.printLibrary();
		System.out.println("\n<< .... REDO [ on empty stack ]"); // correct
		lib.Redo();
		System.out.println("\n<< .... Library state .... >>\n");
		lib.printLibrary();
		lib.getaCommandSize();

	}

}
