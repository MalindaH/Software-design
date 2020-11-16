package activity5;

public abstract class AbstractPlayable implements Playable
{
	private int aCount;
	// associate a playable with play count information by default
	private boolean hasPlayCount = true;
	private int aSilenceLength;

	protected AbstractPlayable()
	{
		aSilenceLength = 0;
	};

	/**
	 * Following template method design pattern, this method is implemented following a series of steps for Problem 2:
	 * 
	 * addCount() - Step 1: Increment play count by 1
	 * 
	 * playSilence() - Step 2: Play x seconds of silence before
	 * 
	 * Playable playing() - Step 3: Play the Playable
	 * 
	 * @pre pSilenceLength
	 */
	@Override
	public final void play()
	{
		addCount();
		playSilence(getSilenceLength());

		// perform the actual play here, rename to playing() to avoid recursive call
		playing();
	}

	/**
	 * Step 1: Increment play count by 1 before playing Playable
	 * 
	 * @pre hasPlayCount Checks if Playable has a play count (true/false)
	 */
	private void addCount()
	{
		if (hasPlayCount)
			aCount++;
	}

	/**
	 * Step 2: Play any silence that the Playable has.
	 * 
	 * @param pSilenceLength
	 */
	private void playSilence(int pSilenceLength)
	{
		MediaSystem.instance().playSilence(pSilenceLength);
	}

	/**
	 * Step 3: Plays the Playable
	 */
	protected abstract void playing();

	/**
	 * Reset play count of a Playable.
	 * 
	 * @pre hasPlayCount
	 */
	public final void resetPlayCount()
	{
		// Playable needs to have play count to reset play count
		assert hasPlayCount;
		aCount = 0;
	}

	/**
	 * Get play count of a Playable.
	 * 
	 * @pre hasPlayCount If Playable has play count
	 * @return aCount Play count value
	 */
	public final int getPlayCount()
	{
		assert hasPlayCount;
		return aCount;
	}

	/**
	 * Set play count to true/false. Play count defaults to true.
	 * 
	 * @param pHasPlayCount
	 */
	public final void setHasPlayCount(boolean pHasPlayCount)
	{
		hasPlayCount = pHasPlayCount;
	}

	/**
	 * Get play count of Playable. Play count defaults to true.
	 * 
	 * @return hasPlayCount boolean variable indicates whether the clients want to associate PlayCount information with a
	 *         playable true: associate false: don't associate
	 */
	public final boolean getHasPlayCount()
	{
		return hasPlayCount;
	}

	/**
	 * Get silence length of Playable.
	 * 
	 * @return aSilenceLength Length of silence associated with Playable
	 */
	public final int getSilenceLength()
	{
		return aSilenceLength;
	}

	/**
	 * Set silence length of Playable.
	 * 
	 * @pre pSilenceLength >= 0
	 * @param pSilenceLength
	 */
	public final void setSilenceLength(int pSilenceLength)
	{
		assert pSilenceLength >= 0;
		aSilenceLength = pSilenceLength;
	}

}
