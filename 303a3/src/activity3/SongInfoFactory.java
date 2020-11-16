package activity3;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for flexible implementation of more SongInfo than Artist and Genre
 */
public class SongInfoFactory {
    private static final List<SongInfo> artists = new ArrayList<>();
    private static final List<SongInfo> genres = new ArrayList<>();

    /**
     * gets an object of the specified SongInfo type
     * @param pType SongInfoType enum representing the type of songInfo to be created
     * @param pName String representing the name of the SongInfo to be created
     * @return the created SongInfo
     * @pre pType != null && pName != null
     */
    private static SongInfo getSongInfo(SongInfoType pType, String pName) {
    	assert pType != null && pName != null;
        if (pType == SongInfoType.GENRE) {
        	SongInfo tmp = contains(pName, genres);
            if(!tmp.isNull()){
                return tmp;
            }
            Genre newGenre = new Genre(pName);
            genres.add(newGenre);
            return newGenre;
        }
        else {
        	SongInfo tmp = contains(pName, artists);
            if(!tmp.isNull()){
                return tmp;
            }
            Artist newArtist = new Artist(pName);
            artists.add(newArtist);
            return newArtist;
        }
    }

    /**
     * checks whether the input List contains a SongInfo of the specified name, and 
     * returns the SongInfo if it's contained
     * @param pName the String to be checked
     * @param pList the List<SongInfo> to be checked
     * @return the SongInfo if the List contains it, return SongInfo.NULL if not
     */
    private static SongInfo contains(String pName, List<SongInfo> pList){
        for(SongInfo info: pList){
            if(info.nameEquals(pName)){
                return info;
            }
        }
        return SongInfo.NULL;
    }

    /**
     * factory method to get artist, avoiding duplication
     * @param pName String representing the name of artist to be created
     * @return the created Artist
     * @pre pName != null
     */
    public static Artist getArtist(String pName) {
    	assert pName != null;
        return (Artist) getSongInfo(SongInfoType.ARTIST, pName);
    }

    /**
     * factory method to get genre, avoiding duplication
     * @param pName String representing the name of genre to be created
     * @return the created Genre
     * @pre pName != null
     */
    public static Genre getGenre(String pName) {
    	assert pName != null;
        return (Genre) getSongInfo(SongInfoType.GENRE, pName);
    }

    /**
     * enum representing types of SongInfo. flexible to add other SongInfo classes
     */
    private static enum SongInfoType {
        ARTIST,
        GENRE;
    }
}