package music.FilterClass;

import music.*;
import music.tags.CustomTag;
import music.tags.TagType;

import java.util.*;

/**
 * Filters the songs in the library by looking through all tags
 */
public class FilterAnyTag implements Filter 
{
    private String aFilterCriteria;
    
    /**
     * Creates a FilterAnyTag object which filters the songs in the library by looking through all tags
     * @param pFilterCriteria is what the client wants to filter for
     * @pre pFilterCriteria != null
     */
    public FilterAnyTag(String pFilterCriteria) 
    {
    	assert pFilterCriteria != null;
        aFilterCriteria = pFilterCriteria;
    }
    
    /**
     * filterCriteria method takes all the songs in the library and returns songs with any tag matching the search criteria
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
        	//Check all expected and optional tags
        	for(TagType tagType : TagType.values())
        	{
        		//Verify if tag is empty to prevent null pointer exceptions
        		if(song.getTagValue(tagType) == null)
        		{
        			continue;
        		}
        		if (song.getTagValue(tagType).toString().equals(aFilterCriteria)) 
        		{
        			filteredList.add(song);
        		}
        	}
        	//Check all custom tags for string match
        	if (song.getCustomTags().contains(aFilterCriteria))
       		{
       			filteredList.add(song);
       		}
        }
        return filteredList;
    }
}