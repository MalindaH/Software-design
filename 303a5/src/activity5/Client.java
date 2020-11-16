package activity5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import activity5.Interface.Command;

public class Client
{
	public static void main(String[] args)
	{

		Song s1 = new Song(new File("C://s1.mp3"));
		Song s2 = new Song(new File("C://s2.mp3"));
		Song s3 = new Song(new File("C://s3.mp3"));
		Song s4 = new Song(new File("C://s4.mp3"));

		Playlist p1 = new Playlist("P1");
		Playlist p2 = new Playlist("P2");
		Album a1 = new Album("A1");

		// testing for problem 1 and problem 2

		a1.setHasPlayCount(false);
		a1.addTrack(1, s1);
		a1.addTrack(2, s2);
		a1.addTrack(3, s4);
		a1.addTrack(3, s3);

		a1.setSilenceLength(10);
		s3.setSilenceLength(4);
		s1.resetPlayCount();
		a1.play();

		border();

		p1.setSilenceLength(5);
		p1.play();
		border();

		System.out.println(p1.getPlayCount());
		p1.resetPlayCount();
		System.out.println(p1.getPlayCount());

		System.out.println(a1.getPlayCount());
		a1.printSongInAlbum();
		a1.resetPlayCount();
		System.out.println(a1.getPlayCount());
		System.out.println(s1.getPlayCount());

		border();
		p1.play();
		System.out.println(p1.getPlayCount());
		System.out.println(s1.getPlayCount());

		// test for problem3
		border();
		System.out.println("problem 3");
		List<Command> commands = new ArrayList<>();
		Library l1 = Library.getInstance();

		a1.addTrack(1, s1);
		a1.addTrack(2, s2);
		p1.add(a1);
		commands.add(l1.ADD(s4));
		commands.add(l1.ADD(s3));
		commands.add(l1.ADD(p1));

		for (Command command : commands)
		{
			command.execute();
		}

		l1.printLibrary();
		
		border();
		l1.REMOVE(s1).execute();
		
		l1.printLibrary();
		
		l1.Undo(2);
		l1.Undo();
		
		l1.printLibrary();
		
		l1.Redo(2);
		l1.printLibrary();
		
		Library l2 = Library.getInstance();
		int size_1 = l2.getSize();
		l2.ADD(s1).execute();
		l2.REMOVE(s2).execute(); // s2 is not in the library
		int size_2 = l2.getSize();
		l2.Undo();
		int size_3 = l2.getSize();
		l2.printLibrary();
		System.out.println(size_1 + " "+size_2 + " "+ size_3);

	}

	public static void border()
	{
		System.out.println("*************************");
	}
}
