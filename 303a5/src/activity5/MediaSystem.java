package activity5;

import java.io.File;
import java.util.Random;

/**
 * Stub for a media system. Accessible as a singleton.
 */
public class MediaSystem
{
	private static final MediaSystem INSTANCE = new MediaSystem();
	private static final Random RANDOM = new Random();
	
	public static MediaSystem instance()
	{
		return INSTANCE;
	}
	
	/**
	 * Stub method that pretends to be an audio API that plays
	 * the song in its entirety.
	 */
	public void playSong(Song pSong) 
	{
		System.out.println("Playing " + pSong.description());
	}
	
	public void playSilence(int pTime) 
	{
		System.out.println("Playing " + pTime + " seconds of silence" );
	}
	
	/**
	 * Stub method that pretends to obtain the duration of a song in seconds by reading
	 * the media file. See the Song class.
	 */
	public int duration(File pSong) 
	{ 
		return RANDOM.nextInt(10); 
	} 
}
