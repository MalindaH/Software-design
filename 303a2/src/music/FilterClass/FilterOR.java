package music.FilterClass;

import java.util.*;
import music.*;

/**
 * Filters with OR conditions
 */
public class FilterOR implements Filter {

    private Filter aFirstFilter;
    private Filter aSecondFilter;

    /**
     * Creates FilterOR object
     * 
     * @param pFirstFilter  first filter to apply
     * @param pSecondFilter second filter to apply
     * @pre pFirstFilter != null && pSecondFilter != null
     */
    public FilterOR(Filter pFirstFilter, Filter pSecondFilter) {
        assert pFirstFilter != null && pSecondFilter != null;
        aFirstFilter = pFirstFilter;
        aSecondFilter = pSecondFilter;
    }

    /**
     * Filters according to OR condition
     * 
     * @param pSongs is the list of songs to filter from
     * @pre pSongs != null
     */
    @Override
    public List<Song> filterCriteria(List<Song> pSongs) {
        assert pSongs != null;
        List<Song> firstFilteredList = aFirstFilter.filterCriteria(pSongs);
        List<Song> secondFilteredList = aSecondFilter.filterCriteria(pSongs);
        for (Song song : secondFilteredList) {
            if (!firstFilteredList.contains(song)) {
                firstFilteredList.add(song); // if not contained in the first filtered list then add it
            }
        }
        return firstFilteredList;
    }
}
