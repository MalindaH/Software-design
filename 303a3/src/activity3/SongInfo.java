package activity3;

public interface SongInfo {

    String getName();
    boolean isEmpty();
    boolean equals(SongInfo pSongInfo);
    boolean nameEquals(String pName);
    
    default boolean isNull() {
		return false;
	}
    
    /**
     * SongInfo.NULL object to be used for null object design pattern in Song
     */
    public static SongInfo NULL = new SongInfo() {
    	public boolean isEmpty() {
    		return true; 
    	}
    	public String getName() {
    		assert !isEmpty();
    		return null;
    	}
    	public boolean isNull() {
    		return true; 
    	}

		@Override
		public String toString() {
			return "-";
		}

		// overload instead of overwrite so that we don't need to implement hashCode()
    	public boolean equals(SongInfo pSongInfo){
    		return this.isNull() && pSongInfo.isNull();
		}
    	
		@Override
		public boolean nameEquals(String pName)
		{
			return this.isNull() && (pName == null);
		}
    };
}
