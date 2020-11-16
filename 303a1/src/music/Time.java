package music;

import java.util.Arrays;

public class Time {
	private int aHour;
	private int aMinute;
	private int aSecond;

	/**
	 * Constructs a Time object using the input time String
	 * @param pTimeString a String representing the time
	 * @pre pTimeString == hh:mm:ss || pTimeString == mm:ss, where hh>=0, 0<=mm<60, 0<=ss<60
	 * If pTimeString is invalid, throw exception and initialize this Time as -1:-1:-1
	 */
	public Time(String pTimeString) {		
		String[] temp = pTimeString.split(":");
		// check pTimeString is in correct format
		try {
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

		} catch (Exception e) {
			// these -1:-1:-1 won't be printed/shown to client code, just for internal checking purposes
			aHour = -1;
			aMinute = -1;
			aSecond = -1;
            e.printStackTrace();
        }
	}
	
	/**
	 * Returns a String representing the time
	 */
	public String toString() {
		if(aHour == -1) {
			return null;
		}
		if(aHour == 0) {
			return aMinute+":"+aSecond;
		}
		return aHour+":"+aMinute+":"+aSecond;
	}
	
	/**
	 * Gets the hour value
	 * @return the hour value, return 0 if Time was invalid
	 */
	public int getHour() {
		if(aHour == -1) {
			return 0;
		}
		return aHour;
	}
	
	/**
	 * Gets the minute value
	 * @return the minute value, return 0 if Time was invalid
	 */
	public int getMinute() {
		if(aMinute == -1) {
			return 0;
		}
		return aMinute;
	}
	
	/**
	 * Gets the second value
	 * @return the second value, return 0 if Time was invalid
	 */
	public int getSecond() {
		if(aSecond == -1) {
			return 0;
		}
		return aSecond;
	}
}
