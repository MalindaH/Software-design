# Design Activity 3

*Start on 7 October, complete by 16 October end of day Montreal time zone. Covers Chapter 4 of the book. This activity is to be completed in groups of maximum 8 students. Some components of the activity can be completed individually.*

## Baseline Code

You can download a _project skeleton_ by pulling the [https://gitlab.cs.mcgill.ca/martin/activity3](https://gitlab.cs.mcgill.ca/martin/activity3) repo. You can this code as necessary.

## Rules of Engagement

* The **goal of this activity** is to explore as much of the design space as possible in a constructive manner, and eventually to arrive at a solution for which all the trade-offs are well understood.
* The activity is organized into **four relatively-independent design problems**.
* For each activity there are three roles you can play: **implementer**, **reviewer**, **discussant**. As a necessary (but not sufficient) condition for full marks, you must earn one credit for three different problems, and the roles must be different for each problem. The roles are explained below.
* Contribute *only* through GitLabs (as opposed to other social media) so that your contributions can be accounted for. The teaching assistants will not be expected to review any contribution not made on GitLabs when assigning grades.
* Please be mindful that some teammates may be in a different time zone, please give everyone the time necessary to contribute to the activity.

## Process

1. Open an issue "Coordination" to discuss who wants to work on which problem in which role.
2. Submit an early/initial implementation for each of the four problem. This can be done by one person alone, or a small subset working in collaboration. Committing code earns an **implementer** credit.
3. Once code is committed, post a detailed review of one aspect of the code as a new issue. The review should include design issues, discussions of tradeoffs, and alternatives/constructive feedback. This earns a **reviewer** credit.
4. Discuss in those issues to bring new points or disagree with a previous point. This earns a **discussant** credit.
5. Implement a revision based on the issue (or together with the issue as the alternative) and submit a merge request. This earns an implementer credit. 
6. You can also review the details of the code of a merge requests, thus earning a **reviewer** credit, or generally discuss the merit of the merge request, earning a **discussant** credit.

## Design Problems

### Problem 1: Supporting Partial Information in Song and Album

*Target type:* `Song`

1. Make it possible to create a `Song` by only specifying the file. All other pieces of data may be absent. It may also be possible to add a tag with an absent value. 
2. Similarly, make it possible to create an `Album` by only specifying its title. The artist can be absent, for example for compilation albums.
3. Use at least two different techniques seen in Chapter 4 to address this requirements, depending on the fields.  
4. Implement and document the getters and setters.

### Problem 2: Unique Artists and Genres

*Target types*: `Artist` and `Genre`

1. Complete the `Artist` and `Genre` classes so that client code cannot create two separate objects that are equal.
2. Complete the declarations of these classes to allow clients to use the information they store as documented in the code skeleton.
3. Consider interesting ways to simplify the factory methods.

### Problem 3: Library Lifecycle

*Target type:* `Library`

Complete enough of the code of class `Library` to fulfill the requirements below. In the design discussion, be sure to include a state diagram.

1. It should be possible to add or remove songs, playlists, and albums from the library, if there are some;
2. A `Library` can be empty of songs. In this case it is not possible to add albums or playlists;
3. When removing playlists or albums, it should be also be possible to delete their song from the library; In this case, the songs also get deleted from any other album or playlist in the library.
4. A `Library` can be backed up, which results in all the information being copied to a database (use a stub, don't actually implement this). Client code should be able to tell if the current library state is backed up (i.e., there was no change since the last backup);

### Problem 4: Duplicate Detection

Target types: `Song`, `Playlist`, `Album`

1. Make it possible to detect duplicate `Playlist` and `Albums` using two different criteria: totally-equal and content-equals. Totally equal playlists have the same title and songs in the same sequence and totally equal albums have the same title, artist, and tracks in the same order. Content-equal playlists and albums have the same songs, but order and title/artist do not matter.
2. Make it possible to detect duplicate `Songs` using two different criteria: same file and same title-artist. 

## Deliverables

1. The documented code of the solution and all associated resources as described above.
2. In a folder `contribs/` one file per team member, titled `Firstname-LastName.md` which lists and briefly describes your main contributions that best showcase your knowledge of the material, linking to appropriate repository resources. Be sure to include the problem number and your role for each contribution.

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
| E     | **Flawed solution:** A solution that cannot reasonably be implemented without thorough rework, and shows little to no knowledge of the course material. Includes complete absence of a solution. | 20%-100% penalty      |

### Contributions

The contributions are evaluated individually according to the following instrument. Midpoints (odd numbers) are used for assessments that fall between categories.

| Grade | Assessment                                                   |
| ----- | ------------------------------------------------------------ |
| 0     | No contribution, or exclusively irrelevant or unhelpful contributions (clutter) |
| 2     | Only shallow/token/symbolic contributions (fixing typos, formatting, generic comments, etc.) |
| 4     | The contributions have some substance but remain shallow and/or generic (reheated from the textbook) and do not reflect knowledge of the course material. |
| 6     | The contributions meet only a few of the criteria for the maximum grade, or meet some of them partially. They provide evidence of adequate knowledge of the material, but with some noticeable omissions. |
| 8     | The contributions meet most, but not all, of the criteria for the maximum grade, and generally demonstrate very good knowledge of the material and regular involvement instrumental to the solution. |
| 10    | Many of the contributions show thorough preparation and a deep understanding of the issues at hand as learned from practice; The contributions are of high quality and helpful; The contributions demonstrate mastery of the material covered up to the Chapter associated with this activity. The student showed good team spirit and facilitated contributions by others. |

## See Also

* [Policy on Code Reuse](CodeReuse.md)