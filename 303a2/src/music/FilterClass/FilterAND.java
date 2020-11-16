package music.FilterClass;

import music.*;
import java.util.*;

/**
 * Filters with two AND conditions
 */
public class FilterAND implements Filter 
{
    private Filter aFirstFilter;
    private Filter aSecondFilter;
    
    /**
     * Creates an object of type FilterAND
     * @param pFirstFilter first filter to apply
     * @param pSecondFilter second filter to apply
     * @pre pFirstFilter != null && pSecondFilter != null
     */
    public FilterAND(Filter pFirstFilter, Filter pSecondFilter)
    {
    	assert pFirstFilter != null && pSecondFilter != null;
        aFirstFilter = pFirstFilter;
        aSecondFilter = pSecondFilter;
    }

    
    /**
     * filters according to AND condition
     * @param pSongs is the list of songs to filter through
     * @pre pSongs != null
     */
    @Override
    public List<Song> filterCriteria(List<Song> pSongs) 
    {
    	assert pSongs != null;
    	//Apply filters
        List<Song> firstFilteredList = aFirstFilter.filterCriteria(pSongs);
		List<Song> secondFilteredList = aSecondFilter.filterCriteria(pSongs);
		ArrayList<Song> filteredList = new ArrayList<Song>();
        for (Song song : secondFilteredList) 
        {
            if (firstFilteredList.contains(song)) 
            {
                filteredList.add(song);
            }
        }
		return filteredList;
    }
}
