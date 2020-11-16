## Contributions
- I mainly worked on requirements 1,2,5, and modified a lot of things from the baseline code. I did my work on `malinda-branch`. I took part in discussions as much as I could.
- Implement `Songlist` interface in `Library, Playlist, Album` and implement methods `removeSongs(ArrayList<Song>), removeSong(Song):Song, addSong(ArrayList<Song>), addSongs(ArrayList<Song>), getSong():Song, getAllSongs():ArrayList<Song>, numOfSongs():int` in the classes.
- Implement `Iterable<Song>` in `Playlist, Album, Library` and implement `iterator()` method in the classes.
- Implement `CustomTag` class, where `CustomTag.get("title")` method is used to get a Tag while avoiding replicates. `TagType` enum (for expected and optional tags) and `CustomTag` class mplement `Tag` interface, so that methods like `getTagValue(Tag), sortBy(Tag), sortBy(List<Song>,Tag), setTag(Tag,Object)` can all use `Tag` as parameter, instead of making separate methods for the 2 types of tags.
- Implement `MetadataTags` class containing getters and setters of expected/optional and custom tags. `get(Tag), set(Tag, Object), getCustomTags():String, getCustomTagsArray():ArrayList<String>`, etc.
- Implement a static `sortBy(List<Song>,Tag, boolean)` method in `Library` using an anonymous Comparator class (boolean indicates whether to sort the list of songs by the specified tag in ascending or descending order). Using that, implement `sortBy(Tag), sortBy(Tag, boolean)` methods in `Library, Album, Playlist`.
- Implement `Comparable<Time>` with `compareTo(Time):int` method in `Time` class.
- Implement `play(), isFavorite(), addToFavorates(), removeFromFavorites()` in `Library` class.

- (Issue #12, #13) Fix imperfections from baseline code: Change `Song()` constructor to set expected tags and handle exceptions. Change all methods to check for input validity (public methods use exceptions, private methods use precondition and assert).

- My class diagram:
![interfaces-classdiagram](../images/interfaces-classdiagram.png)

