package music;

public class Time implements Comparable<Time> {
	private int aHour;
	private int aMinute;
	private int aSecond;

	/**
	 * Constructs a Time object using the input time String
	 * @param pTimeString a String representing the time
	 * pTimeString format should be hh:mm:ss or mm:ss or ss, where hh>=0, 0<=mm<60, 0<=ss<60
	 * If pTimeString is invalid, throw exception and initialize this Time as -1:-1:-1
	 */
	public Time(String pTimeString) {		
		try {
			// check pTimeString is in correct format
			if(pTimeString == null || pTimeString.equals("")) {
				throw new IllegalArgumentException("Time input is invalid, has to be hh:mm:ss, where hh>=0, 0<=mm<60, 0<=ss<60");
			}
			String[] temp = pTimeString.split(":");
			if(temp.length == 3) {
				// hh:mm:ss
				if(Integer.parseInt(temp[0])<0 || Integer.parseInt(temp[1])>=60 || Integer.parseInt(temp[1])<0 || Integer.parseInt(temp[2])>=60 || Integer.parseInt(temp[2])<0 ) {
					throw new IllegalArgumentException("Time input is invalid, has to be hh:mm:ss, where hh>=0, 0<=mm<60, 0<=ss<60");
				}
				aHour = Integer.parseInt(temp[0]);
				aMinute = Integer.parseInt(temp[1]);
				aSecond = Integer.parseInt(temp[2]);
			}
			else if(temp.length == 2) {
				// mm:ss
				if(Integer.parseInt(temp[0])<0 || Integer.parseInt(temp[0])>=60 || Integer.parseInt(temp[1])<0 || Integer.parseInt(temp[1])>=60) {
					throw new IllegalArgumentException("Time input is invalid, has to be hh:mm:ss, where hh>=0, 0<=mm<60, 0<=ss<60");
				}
				aHour = 0;
				aMinute = Integer.parseInt(temp[0]);
				aSecond = Integer.parseInt(temp[1]);
			}
			else if(temp.length == 1) {
				// ss
				if(Integer.parseInt(temp[0])<0 || Integer.parseInt(temp[0])>=60) {
					throw new IllegalArgumentException("Time input is invalid, has to be hh:mm:ss, where hh>=0, 0<=mm<60, 0<=ss<60");
				}
				aHour = 0;
				aMinute = 0;
				aSecond = Integer.parseInt(temp[0]);
			}
		} catch (Exception e) {
			// aHour = -1 indicates this Time is invalid
			aHour = -1;
			aMinute = -1;
			aSecond = -1;
            e.printStackTrace();
        }
	}
	
	/**
	 * Constructs a Time object of the specified second value
	 * @param pSecond the second value of Time
	 */
	public Time(int pSecond) {
		try {
			if(pSecond < 0) {
				throw new IllegalArgumentException("Input number is invalid. pSecond should be >= 0.");
			}
			int minute = pSecond/60;
			aHour = minute/60;
			aMinute = minute%60;
			aSecond = pSecond%60;
		} catch(Exception e) {
			aHour = -1;
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a String representing the time, return null if this Time is invalid
	 */
	public String toString() {
		if(aHour == -1) { // this Time is invalid
			return null;
		}
		if(aHour == 0) {
			return aMinute+":"+aSecond;
		}
		return aHour+":"+aMinute+":"+aSecond;
	}
	
	/**
	 * Gets the hour value
	 * @return the hour value, return -1 if this Time is invalid
	 */
	public int getHour() {
		return aHour;
	}
	
	/**
	 * Gets the minute value
	 * @return the minute value, return -1 if this Time is invalid
	 */
	public int getMinute() {
		return aMinute;
	}
	
	/**
	 * Gets the second value
	 * @return the second value, return -1 if this Time is invalid
	 */
	public int getSecond() {
		return aSecond;
	}
	
	/**
	 * Converts the Time to seconds
	 * @return the Time value in seconds, return -1 if this Time is invalid
	 */
	public int toSeconds() {
		if(aHour == -1) { // this Time is invalid
			return -1;
		}
		return this.getSecond() + this.getMinute()*60 + this.getHour()*3600;
	}

	/**
	 * Compares this Time value to the input time value
	 * @return an int indicating the result of comparison
	 */
	@Override
	public int compareTo(Time o) {
		if(this.aHour == -1 || o.toSeconds() == -1) {
			return 0; // don't compare if either Time is invalid
		}
		return this.toSeconds() - o.toSeconds();
	}
}
