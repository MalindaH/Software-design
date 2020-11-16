# Ben Golding's Contributions
#### Implementation (Problem 1)
- Worked with Yichao Yang to implement problem 1 using the COMPOSITE Design Pattern
- I added the equals(), hashCode(), getRuntime(), getTitle(), and getContents() to the SongCollection interface and implemented these methods in Song, Playlist and Album. See these commits: 
    1. [Added methods to SongCollection](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/0ec0c556c22293553fdc7a54e74c4c3b788a4a02)
    2. [Added unimplemented methods to Playlist](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/4c565c0fd224bdee91fe4008280b52acc81cf802)
    3. [Implemented methods for Album + minor bug fix in SongCollection](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/1516b893868b3d8d8bbf957488a8788f24e2dcde)
    4. [Implemented methods for Album + removed contentEquals from SongCollection
](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/fc3904ce4905d4a5cf27c5806f4d08517ec862da).

#### Review (Problem 2)
I wrote the following review for problem 2
> Hey, just saw what was pushed heres my review. Overall there was great use of Decorator but I have 2 points:

> 1 . I think you should add more than just `aRemixType+" REMIX:"` too RemixSongCollection and have a remix perform some operations on a playlist such as changing buffer time, changing the order of the songs, only remixing some of the songs or some other set of operations.

> 2 . There is quite a bit of code duplication between the decorators, specifically in getPlayCount, getRuntime, getTitle, duration, getContents which are all either very similar or the same. How would you feel about creating a BaseDecorator class containing these methods that is `extend`'ed by the other decorators or adding these as default methods of to the interface? 

> Some minor things

> 1 . RemixSongCollection.getPlayCount() and PlayCountSongCollection.getPlayCount() seem to have some documentation+design-by-contract specification missing (just has `@return` and nothing else)

> 2 . The BufferSongCollection constructor also has what appears to be missing design-by-contract elements

#### Test (Problem 3)
- Wrote 12 tests related to the clone methods of Playlist and Song using both JUnit and various metaprogramming techniques.
- To view my contribution see the following commits: 
    1. [Created initial structure + updated .classpath](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/53828c4e3b55b6c04827a1fa3789860fb27f104c)
    2. [Added test methods for Song](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/293223730497d9370dd9ad46f877b42c9cf4a5fd), 
    3. [Created basic playlist tests](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/bd76ce00822b7d180b61adcecfc18b8fd90ce12e)
    4. [More Playlist Tests](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/9c4c0c8d8e47069a6e72f1dee2808c581cc5f3ef)
    5. [Fixed song_testClone_exists()](https://gitlab.cs.mcgill.ca/mnassif/303a4t23/-/commit/069c89ecf8d8923731b53c49c69da3f1431a0dc3)
- Please note that because of an issue my group faced, my tests had to be copy/pasted by teammate so these commits my not appear in master.

#### Other
- Created Issue for Cordination, discussed potential solutions with teammates, and worked colloratively to create solutions.
