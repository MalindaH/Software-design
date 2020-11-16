## Lin Xiao Zheng [260911511]

### Implementer for Problem 4. Discussed in issue #8, reviewed in issue #12.
Code in [duplicateCheck](https://gitlab.cs.mcgill.ca/mnassif/303a3t26/-/tree/duplicateCheck) branch, but later bugs were fixed directly in master.<br>

The design decisions are discussed in [design-decisions.md](https://gitlab.cs.mcgill.ca/mnassif/303a3t26/-/blob/master/design-decisions.md).<br>

What I implemented related to Problem 4:
*  `equals()` in `Song`, `Album`, and `Playlist`
*  `contains()` in `Album` and `Playlist`
*  `equals()` in all `SongInfo` children classes
*  `SongCriteria` nested enum class in `Song`
*  `SongListCriteria` enum class
*  `test4()` in `Client` for testing


### (Informal) Implementer for Problem 2. Reviewed in issue #14.
Code in [alt_uniqueness](https://gitlab.cs.mcgill.ca/mnassif/303a3t26/-/tree/alt_uniqueness) branch.<br>

I implemented a simple flyweight class design as seen in class and in the textbook for the `Artist` and `Genre` classes.<br>
The reason I decided to implement this problem was because one of our teammates was [missing](https://gitlab.cs.mcgill.ca/mnassif/303a3t26/-/issues/11#note_17645) so we had to rearrange the tasks a bit to move forward.
I coded this so that my teammate could have some code to review.<br>

I chose a simple flyweight design to insure uniqueness because I deemed the simpleton pattern inappropriate for our requirements. 
We're not looking to have only a single instance of `Artist` and `Genre` but for a way to manage multiple instances while keeping each one unique.
The flyweight does just that. <br>

The actual design we went for is also a flyweight design, but taken in a different direction. We use a factory class `SongInfoFactory` as our flyweight class to create and manage our objects.
The individual constructors of `Artist` and `Genre` are kept protected to be only used by that factory class.
The `SongInfo` interface was created to parent `Artist` and `Genre`. This was very useful since polymorphism allows the creation of the null `SongInfo` object which was used in Problem 1.
Polymorphism also allowed me to use an `equals()` method on `SongInfo` children classes without needing to always explicitely check the specific classes of the inputs.<br>

In the official version on the master branch, I contributed by implementing:
*  the `equals()` methods in `SongInfo` children (as mentioned above)
*  the `contains()` method in `SongInfoFactory`
*  an improvement to the static access method in the factory method.
I also imported the idea of storing all synonyms/alternative names as lowercase in `Artist` and `Genre` from my alternative implementation.

### Reviewer for part of Problem 1. See issue #13.
Code in [album_optional_implementation](https://gitlab.cs.mcgill.ca/mnassif/303a3t26/-/tree/album_optional_implementation) branch.