# Design Activity 1

*Start on 14 September, complete by 25 September end of day Montreal time zone. Covers Chapter 2 of the book. Project repos will be available on 14 September.*

## Problem Statement

Following the principles seen in Chapter 2 of the book, design and write the code necessary to support a music library with the following properties.

1. A library is made up of songs, playlists, and albums.
2. Each song corresponds to a specific file path. This path determines the identity of the song and the audio format used and can never change for a given song. Your solution needs to take into account that a file path and/or audio format (as determined through the file extension) may not exist. 
3. A song can become *invalid* if its underlying file disappears from the operating system. It should be possible to detect and remove invalid songs from a library.
4. A song has associated meta-data organized into key-value pairs. When describing songs, keys are called *tags*. There are three types of tags: expected, optional, and custom. Expected tags represent pieces of data that it would be weird not to have. These include title, time, and artist. Optional tags include common tags from a fixed set. These include BPM (beats per minute), genre, composer, etc. Custom tags is a feature that allows users to have an arbitrary key-value pair associated with a song to help classify their music. The value of tags can change during the lifetime of a song.
5. Songs can be organized into playlists. A playlist is an ordered collection of songs. A playlist must have a name. It can be possible to change this name after the playlist is created. It should be possible for client code to access all the songs in the playlist, as well as easily access pre-determined aggregated information (number of songs, total playing time, etc.)
6. Songs can be organized into albums. By default, songs are not part of any album. However, a song can be associated with an album, in which case it gains a track number. Albums can be partial, so for example it could be possible to have an album with only tracks 3, 5 and 9. Client code should be able to gain access to all the songs in an album, and find out their track number. Albums have a required title and optionally an artist name. It should be possible to change both the title and artist of an album after the album is created. It should be possible to have songs from different artists in an album. Your solution is not responsible for ensuring that the content of an album is correct in the musical sense.

## Rules of Engagement

* The goal of this activity **is** to explore as much of the design space as possible in a constructive manner, and eventually to arrive at a solution for which all the trade-offs are well understood.
* The goal of this activity **is not** just to bang out a satisfactory solution. Please be aware that simply uploading a solution, even if it's a fine solution, will not constitute a satisfactory completion of this activity.
* Try to isolate "variation points" in the solution space, and discuss alternative solutions. Use issues to organize your work. Ask help from TAs if you need guidance.
* As part of the activity, strive to contribute all of: code, design diagrams, textual comments, and code reviews.
* Do not be shy to contribute! During the two weeks, anything can be changed. If you commit some code that has a problem, it's easy to change it. If you make a comment that later proves to make no sense, it's easy to edit it. And so on.
* If you feel up for it, consider using branches to explore alternatives.
* It's completely ok to propose solutions that turn out to be, in the end, not all that great. If fact, it's explicitly encouraged. If it helped articulate why a certain option isn't worth pursuing, you've contributed something important.
* Parts of the requirement are *purposefully* left open-ended. For example, in requirement 4 we state "pre-determined aggregated information (number of songs, total playing time, etc.)". In such case, as long as you have enough operations to demonstrate the point, it's not really important for you to support a *precise* list of operations. Whenever the requirement state "etc.", it's safe to assume that we don't care exactly about the complete list. If you believe that an an ambiguity needs to be clarified, please post an issue on the class's main GitHub repo.
* Contribute *only* through GitLabs (as opposed to other social media) so that your contributions can be accounted for. The teaching assistants will not be expected to review any contribution not made on GitLabs when assigning grades.

## Deliverables

1. The documented code of the solution. Include main method that exercises the main scenarios of the code. Tag the code with a Major.Minor version. Start with `v1.0` and if for any reason you need to update your solution, increment the second digit. We will look at the last available tagged version.
2. In the file `README.md` a bullet list of the main design decisions and the rationale for them, optionally linked to the various commits, issues, files, and other resources related to the discussion.

## Design Decisions

1. All .java files are in `/src/music` folder. All music files are in `/src/files` folder.
2. `Client.java` contains the main method with some demo codes.
3. `Library.java` contains 3 private ArrayLists of songs, playlists, and albums. `Song`, `Playlist`, and `Album` are supposed to be initialized 
in this class using `addSong()`, `addAlbum()`, and `addPlaylist()` to handle possible exceptions. The `removeInvalidSongs()` method removes all invalid songs from the library, 
as well as all playlists and albums.
4. `Song.java` has a private final String[] containing all possible music formats. It also has getters and setters, and a `isValid()` method to check 
whether the song is valid. `Song` has fields that are instances of 3 other classes: `ExpectedTags`, `OptionalTags`, and `CustomTags` (metadata).
- In `ExpectedTags.java`, the 3 tags (title, time, artist) are included inside a private enum `Tag`, and a private `HashMap<Tag, Object>`
 is used to store the tags (title and artist are stored as Strings, and time is stored as a `Time` object). The setters check the input is valid with 
try-catch blocks to handle the IllegalArgumentException. The setters are private so that the tags can 
only be set in ExpectedTags constructor, and can't be changed from elsewhere. The `toString()` method prints all expected tags.
- (Note: )`Time.java` represents a Time object. The constructor accepts a String of the form "hh:mm:ss" or "mm:ss". If the input is invalid, 
Time will be initialized to be -1:-1:-1. 
- Similarly, in `OptionalTags.java`, the 3 tags (BPM, genre, composer) are included inside a private enum `Tag`, and a private `HashMap<Tag, Object>`
 is used to store the tags (BPM is stored as an Integer, and genre and composer are stored as Strings). The setters check the input is valid with 
try-catch blocks to handle the IllegalArgumentException. The setters are public so that `OptionalTags` can be initialized without tags added, and the tags can 
be set later. The `toString()` method prints all optional tags.
- In `CustomTags.java`, a private `HashMap<String, Object>` is used to store the tags. Tags are stored as Strings so that we can check for repetitive tags 
in `addCustomTag()`. Also `addCustomTag()` checks the tag name and value are valid with a try-catch block to handle the IllegalArgumentException. 
The `toString()` method prints all custom tags.
5. `Playlist.java` contains a private String name and a private Arraylist of songs. It also has `changeName()` to allow client code to change the name of a playlist. 
`getName()` and `getSongs()` methods return the String name and the ArrayList of songs of the playlist respectively. `numOfSongs()` returns the number of songs in 
that playlist. `totalPlayingTime()` returns the a String that represents the total playing time of all songs in the playlist. Playlist.java has sorting methods 
`sortByTitle()`, `sortByArtist()`, and `sortByTime()` to sort songs in playlist alphabetically by either name or artist, or sort by ascending playtime.
6. `Album.java` contains a private String title, a private String artist name, a private HashMap<Song,Integer> aSongs to store songs with their track numbers, and 
a private int to store current max track number. It contains four constructors to adjust to four conditions of input. `addSongToAlbum()` helps client code add 
songs to this album, assigning track number as current max + 1. `addSongsToAlbum()` adds all songs in the input ArrayList using  
`addSongToAlbum()`. `changeTitle()` and `changeArtistName()` allows client code to change the title and artist name of a album respectively. 
`getTitle()`, `getArtistName()` and `getSongs()` are getters. `getTrackNumber()` requires a song as input and returns the track number of 
that song. `toString()` calls the private `sortByTrackNumber` method which sorts the songs by their track numbers in ascending or descending order, 
and then returns a String that contains the title, the artist name and each song with its track number in the album.

## Marking Scheme

The solution will be assessed for basic adequacy, but most of the grade will be determined by each student's individual contributions to the activity.

### Solution

The solution is evaluated for the group. As long as it falls within the [A-C] range there's nothing to worry about.

| Grade | Assessment                                                   | Outcome               |
| ----- | ------------------------------------------------------------ | --------------------- |
| A     | **Notably excellent solution**                               | 10% bonus             |
| B     | **Very good solution:** All aspects follow the principles seen in the course and show evidence of well-informed quality work, except for very minor problems than can be overlooked in practice. | No grade modification |
| C     | **Good solution:** Small problems are noticeable, but these could be fixed during implementation with some low-impact design changes. | No grade modification |
| D     | **Marginal solution:** The solution might be workable, but there are a few obvious major problems that would require some rework at the design level to be implementable. | 10% penalty           |
| F     | **Flawed solution:** A solution that cannot reasonably be implemented without thorough rework, and shows little to no knowledge of the course material. Includes complete absence of a solution. | 20%-100% penalty      |

### Contributions

The contributions are evaluated individually according to the following instrument. Midpoints (odd numbers) are used for assessments that fall between categories.

| Grade | Assessment                                                   |
| ----- | ------------------------------------------------------------ |
| 0     | No contribution, or exclusively irrelevant or unhelpful contributions (clutter) |
| 2     | Only shallow/token/symbolic contributions (fixing typos, formatting, generic comments, etc.) |
| 4     | The contributions have some substance but are limited in number, or remain shallow and/or generic (reheated from the textbook), and/or are not relevant to any on-going discussion. |
| 6     | The contributions meet only a few of the criteria for the maximum grade, or meet some of them partially. They provide evidence of adequate knowledge of the material, but with some noticeable omissions. |
| 8     | The contributions meet most, but not all, of the criteria for the maximum grade, but generally demonstrate very good knowledge of the material and regular involvement instrumental to the solution. |
| 10    | The student contributed regularly and substantially; Many of the contributions show thorough preparation and a deep understanding of the issues at hand as learned from practice; If applicable, the contributions are relevant to on-going discussions, build on previous contributions, and contribute to moving towards a common goal; The contribution are of high quality and helpful; The contributions demonstrate mastery of the material through the exercise of the following skills: coding, diagramming, and commenting. |
