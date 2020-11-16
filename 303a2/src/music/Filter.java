package music;

import java.util.*;
/**
 * A Filter object contains a method filterCriteria that describes the filtering behavior
 */
public interface Filter 
{
    public List<Song> filterCriteria(List<Song> pSongs);
}
