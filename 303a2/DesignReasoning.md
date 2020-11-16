## Requirement 1
![requirements 1,2,3,5](./images/interfaces-classdiagram.png)
- `Library`, `Playlist` and `Album` implement `Songlist` interface to have methods to add song/songs, remove song/songs, get song/songs, and `numOfSongs()`.
- `TagType` (enum for expected and optional tags) and `CustomTag` implement `Tag` interface, so that methods like `getTagValue(Tag), sortBy(Tag), sortBy(List<Song>,Tag)` can all use `Tag` as parameter instead of making separate methods for the 2 types of tags, and client can call these methods using either `TagType` or `CustomTag`.
- `SongList` interface extends `Playable` interface so that `Playlist` and `Album` implements `Playable`. (see requrement 3)
- `Playlist`, `Album`, and `Library` implements `Shuffable` to shuffle or unshuffle songs. (see requirement 3)

## Requirement 2
- One way to obtain the songs is to call `getAllSongs()` which returns a new `ArrayList<Song>` containing all songs in `Playlist`, `Album`, or `Library`.
- Another way is to use the iterator. `Playlist`, `Album`, and `Library` all implement `iterable<Song>`, so user can use foreach loop to loop over all songs.
- The user can also use filter methods to obtain lists of songs. (see requirement 4)
- There are also methods to get the songs by a given tag. These methods electe song from the private field if only it contains the input tag, and return it. If many songs contain the input tag, store these songs in a arraylist and return the list.

## Requirement 3
![alt text](./images/DesignISP.png)

- This requirement was based on the Interface Segregation Principle to exercise the various scenarios without dependencies to services that might not be needed. The interfaces were implemented and extended with the aim of reducing client dependencies on multiple interfaces as much as possible. 
- The `Playable` interface is extended by the `Songlist` Interface. The `Playable` interface is also implemented by `Song` Class. It consists of all the basic functionalities expected by a Playable element, like adding(`addToFavorites()`)/removing(`removeFromFavorites()`)/verifying(`isFavorite()`) favorites, playing song(s) (`play()`) and checking the validity (`isEmpty()`) for the Song, Playlist and Album classes. The 'favorite' status is kept track of by usinf a private boolean stored in the respective classes. The `play()` method is responsible for playing all song(s) stored within a class. The `isEmpty()` method checks for the validity and emptiness. 
- The `SongList` interface is implemented by `Album` and `Playlist` and it extends `Playable` interface. The `SongList` class (from previous assignment requirements, refer req-1) deals with basic operations required by any class the holds multiple instances of `Song`.
- The `Shufflable` interface is implemented by the `Library`, `Album` and `Playlist` class. It provides the methods `shuffle()` which shuffles any songs contained in them and `unshuffle()` which reverts any shuffling or change caused in the order. All these classes also keep track of the original unshuffled states for reverting any changes using the `unshuffle()` method. 


## Requirement 4 

- For this requirement we went with strategy design pattern
- `Filter` interface provides a supertype for the Concrete filters classes.
- Objects of type `Filter`- `FilterAnyTag` takes a criteria `aFilterCriteria` (what the client wants to filter for ) and filters the songs in library by looking through all tags and returns `List<Song>` which contains songs that match the search criteria
- Objects of type `Filter` - `FilterByTag` take a criteria `aFilterCriteria` (what the client wants to filter for) and a tag type `aTagType` (the type of tag the client wants to search through) and filters the songs in the library by looking through the specified `TagType` and returning `List<Song>` which contains songs that match the search criteria
- This method filters list of songs using different criteria and chaining them in a decoupled way through logical operations. 
- `FilterAND` objects takes two filters of type `Filter` during object initialization and applies both filters to the songs list. The `List<Song>` returned contains songs matching both filtering criteria. 
- `FilterOR` objects takes two filters of type `Filter` during object initialization and apply both filters to the songs list. The `List<Song>` returned contains songs that matching at least one of the filtering criteria.
- Combination of `FilterAND` and `FilterOR` gives many different possibilities for filtering the list of songs. 
- This design respects the open ended aspects by letting client choose from endless combination of filters and doesn't limit client to use predefined finite list of options. 

![alt text](/images/Filter.png)

## Requirement 5
- A static `sortBy(List<Song>,Tag,boolean)` method was implemented in `Library` using an anonymous comparator class (the boolean indicates to sort the list of songs by the tag in ascending or descending order). We assume that the values of tags are String, Integer, Double, or Time. Comparing Strings, Integers, and Doubles are done using their default `compareTo()` methods. Comparing Times was made possible by making `Time` implement `Comparable<Time>` with a `compareTo()` method which compares their values in seconds.
- `sortBy(Tag,boolean)` and `sortBy(Tag)` (in ascending order by default) were implemented in `Library`, `Playlist`, and `Album` to sort the songs. These methods call the static `sortBy(List<Song>,Tag,boolean)` method in Library.
- `Library`, `Album`, `Playlist` also have 6 methods like `sortBySongTitle()` to sort according to expected and optional tags (i.e. title, time, artist, BPM, genre, and composer). These methods call the `sortBy(Tag)` methods in their class.


## Requirement 6

- For this requirement we used the existing Filter objects for generating a Playlist in the library class. 
- The Library class has two overloaded functions `generatePlaylist` that generated the Playlist based on the filters applied to the library songs. 
- First overloaded method `generatePlaylist(String pName , Filter pFilter)` takes a `String` representing the Playlist name as an argument and an object of type `Filter`. Method than applies the Filter to the list of songs present in Library and returns a `Playlist` object that contains all the songs that meets the filtering criteria. Returns null (no Playlist object is created) if the search result yielded an empty list of Songs. 
- Second overloaded method `generatePlaylist(String pName , boolean AND,  Filter... filters)` takes Playlist name as argument , a `boolean AND` value that decides whether to apply all the filters to the list of songs or just one of the filters passed as arguments. Returns null (no Playlist object is created) if the search result was an empty list of songs. 
- Overloaded methods gives freedom to the `Client` to generate a Playlist using either just one filter criteria or multiple filter criteria on the list of songs in library class. 

![Requirement6](../images/Requirement6.png)

