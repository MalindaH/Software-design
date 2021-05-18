package activity7;

/**
 * The possible sizes of MenuItems. Note that the order (ordinal) matters here.
 */
public enum Size
{
	SMALL, REGULAR, LARGE;
	
	@Override
	public String toString()
	{
		return this.name().toLowerCase();
	}
}
