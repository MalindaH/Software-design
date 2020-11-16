package music.FilterClass;

import music.*;
import music.tags.CustomTag;
import music.tags.*;

import java.util.*;

/**
 * Filters through the songs in the library by looking at only a specified tag
 */
public class FilterByTag implements Filter 
{
    private String aFilterCriteria;
    private Tag aTagType;

    /**
     * Creates a FilterByTag object which filters the songs in the library based on a predetermined tag
     * @param pFilterCriteria is what the client wants to filter for
     * @param pTagType is the tag type to search through
     * @pre pFilterCriteria != null && pTagType != null
     */
    public FilterByTag(String pFilterCriteria, Tag pTagType)
    {
    	assert pFilterCriteria != null && pTagType != null;
        aFilterCriteria = pFilterCriteria;
        aTagType = pTagType;
    }

    /**
     * filterCriteria method takes all the songs in the library and returns songs with a specified tag matching the search criteria
     * @param pSongs is the list of songs to filter
     * @pre pSongs != null
     */
    @Override
    public List<Song> filterCriteria(List<Song> pSongs) 
    {
    	assert pSongs != null;
        ArrayList<Song> filteredList = new ArrayList<Song>();
        for (Song song : pSongs) 
        {
        	//Verify if tag is empty to prevent null pointer exceptions
    		if(song.getTagValue(aTagType) == null)
    		{
    			continue;
    		}
    		//Search for filter criteria in specified tag type
            if (song.getTagValue(aTagType).toString().equals(aFilterCriteria)) 
            {
                filteredList.add(song);
            }
        }
        return filteredList;
    }
}
