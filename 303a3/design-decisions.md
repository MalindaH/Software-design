# Design Decisions

## Task 1: Song Implementation
	There are 6 fields in the song class. I considered 1) Bogus value design 2) Flag variable designn 3) Optional type design 4) Null Object Design Pattern Design. After the pros and cons analysis for each of the 4 strategies, I have decided to use 3) Optional type and 4) Null Object Design Pattern.I have implemented the Optional type for the "Title", "Duration" and I considered using the Map interface to implement the Null Object design pattern, but then I realized that it doesn't make sense to create a Null map object since it would be impossible for clients to add more tags in the future. Moreover, originally I tried to create an arist interface in order to use the Null Object design pattern, but my teammates pointed out that since Artist class implement the SongInfo interface, we can create a Null Object for the SongInfo and do the same thing for Genre.  

## Task 2:
- Class diagram for this task:
![artist-genre](./contribs/images/artist-genre.png)
- Artist and Genre implement `SongInfo` interface, constructors of Artist and Genre are `protected` so that Client can't access them directly. The flyweight design is implemented in `SongInfoFactory`, which contains static factory methods `getArtist(String), getGenre(String)`, with other private attributes, methods, and enum for avoiding duplicates. Artists and Genres are stored in private Lists to check whether they already exist. Methods `getSongInfo(), contains()` and enum `SongInfoType` are implemented to be general methods, so that it's flexible to add other SongInfo in addition to Artist and Genre. (These methods in SongInfoFactory can be translated into Artist and Genre classes to achieve the same purpose, but that way we will have a lot of code replication and it will not be flexible when we add another `SongInfo`).
- In `SongInfo` interface, a static `NULL` object is initialized, to be used for null object design pattern in `Song` class. `nameEquals()` compares the name to an input String, this is used in `SongInfoFactory.contains()`, in this way we don't need to create a new Artist/Genre object to compare whether it exists or not in the list.

## Task 3:
- State diagram for Library:
![library-state-diagram](./contribs/images/library-state.png)
- At start state, the Library is empty (state at the lower right).
- After any of `addSong(Song), addAlbum(Album), addPlaylist(Playlist)`, Library becomes `!isEmpty()` (state at the top).
- After any of `removeSong(Song), removeAlbum(Album), removePlaylist(Playlist)`, Library can become empty or not empty depending on whether there are songs left in the Library after the operation.
- `backup(), restore()` makes the Library interact with the Database (methods in Database not implemented, just placeholder methods), and makes the Library `isBackedUp()`. Any other add/remove operations make the Library `!isBackedUp()`.
- Library contains a `static final Library INSTANCE`, so the constructor is private, all methods are public static factory methods, and all operations are done to `INSTANCE`.
- In `addAlbum(), addPlaylist()`, all songs the album/playlist contains are also added to the Library. (unable to add if the Library contains no song)
- In `removeAlbum(Album, boolean), removePlaylist(Playlist, boolean)`, the user can specify using the boolean whether to also remove all songs the album/playlist contains from the Library or not.
- The state diagram can also be more detailed like this: state 2 and state n are examples of Library states containing different Songs/Albums/Playlists.
![library-state-diagram-2](./contribs/images/library-state-2.png)
`restore()` makes state transition to the previously backed-up state.


## Task 4:
`equals()` methods were overloaded in Song, Album, and Playlist to check for duplicates. <br>
`SongCriteria` and `SongListCriteria` enums were used to indicate the criteria used to check equality (decision trade-offs discussed in [issue #8](https://gitlab.cs.mcgill.ca/mnassif/303a3t26/-/issues/8)).
`SongCriteria` is nested in `Song` since it related directly to the `Song` class. I couldn't nest `SongListCriteria` since it's used equally in `Album` and `Playlist`.
An abstract overloaded `equals()` method was created in the interface `SongInfo` to be implemented by each of its children classes (`Artist`, `Genre`, `NULL`) to be used to check the artist field equality when needed.
That's another use of polymorphism in the classes that implement the `SongInfo` interface.<br>

The overall logic in the `Song` class is very straight-forward: check equality of necessary fields while making sure to avoid absent ones.<br>

In both the `Album` and `Playlist` classes, I had initially implemented a helper `contains()` method to check content equality.
However, I later realized that this method would allow for song lists of the same size with the same songs but duplicated differently to be deemed equal.
I changed my implementation to avoid that, but I decided to keep the `contains()` method in the classes since I think they would be useful to a client.<br>

In `Album` and `Playlist`, before anything else, I make sure to check the sizes of the `Album` or `Playlist` objects we're comparing. If they are not the same size, they are clearly not equal by any criteria so we can skip the rest of the code.<br>

For content equality in both classes, I first made a shallow copy of the explicit argument song list. 
As I iterated through the song list of the implicit argument, I would remove each song, if found, from the shallow copy. If the shallow copy was empty by the end of it, that means each song was found!
Remember that the size was checked beforehand, so both song list must be of the same size.<br>

For total equality in `Playlist`, I iterated through the List of songs in the playlist, checking that each songs for each index corresponded in both playlists.<br>
For total equality in `Album`, I used `keySet()` to iterate through the track in the album, checking that each track number corresponded to the same song in both albums. Of course, first I had to check that both key sets contain the same values.<br>

