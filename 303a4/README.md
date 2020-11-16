# activity4

Starter code for [activity 4](https://gitlab.cs.mcgill.ca/martin/comp-303-fall-2020/-/blob/master/activities/Activity4.md)

# Design Activity 4

*Start on 20 October, complete by 27 October end of day Montreal time zone. Covers Chapters 5-6 of the book. This activity is to be completed in groups of maximum 7 students. Some components of the activity can be completed individually.*

## Baseline Code

You can download a _project skeleton_ by pulling the [https://gitlab.cs.mcgill.ca/martin/activity3](https://gitlab.cs.mcgill.ca/martin/activity3) repo. You can this code as necessary.

## Rules of Engagement

* The **goal of this activity** is to explore as much of the design space as possible in a constructive manner, and eventually to arrive at a solution for which all the trade-offs are well understood.
* The activity is organized into **three design problems**.
* For each problem there are three roles you can play: **implementer**, **reviewer**, **tester**. As a necessary (but not sufficient) condition for full marks, you must earn one credit for three different problems, and the roles must be different for each problem. The roles are explained below.
* Contribute *only* through GitLabs (as opposed to other social media) so that your contributions can be accounted for. The teaching assistants will not be expected to review any contribution not made on GitLabs when assigning grades.
* Please be mindful that some teammates may be in a different time zone, please give everyone the time necessary to contribute to the activity.

## Process

* Open an issue "Coordination" to discuss who wants to work on which problem in which role.

* Team members should try to submit an early/initial implementation for each of the three problems. This can be done by one person alone, or a small subset working in collaboration. Committing code earns an **implementer** credit.

* Once code is committed, one or more team members can review one aspect of the code as a new issue. The review should include design issues, discussions of tradeoffs, and alternatives/constructive feedback. This earns a **reviewer** credit, along with any additional discussion of the issue. It is especially encouraged to contribute **design diagrams** to issue descriptions and comments.
* Team members should implement the tests for the production code. This earns a **tester** credit. Note that tests can be contributed at any stage. Often, writing tests early helps identify  a design or implementation problem, and thus constitutes an important contribution even if they need to be rewritten to accommodate the change. This earns a **tester** credit.

* Team members implement revisions of production or test code based on comments and submit a merge request. This earns an **implementer** or tester credit, as applicable.

* You can also review the details of the code of a merge requests, thus earning a **reviewer** credit.

## Design Problems

**For each problem, write unit tests for the code you implement. Consider the quality of your test suite in terms of statement and branch coverage.** 

### Problem 1: Supporting Rich Playlists

Make it possible to create a playlist by assembling not just songs, but also albums, other playlists, and anything else that might be applicable, in any possible combination, including recursively (e.g., playlists of playlists). Various elements in a playlist (e.g., albums) should retain their identity (as opposed to just copying their content). It should still be possible to check playlists for equality.

### Problem 2: Effects for Playlist Elements

Make it possible to enhance a playlist element with an open-ended number of effects that can be applied to the elements of the playlist. One example of an effect could be a "play count", which will count the number of times the element (e.g., song) is played. As another example of effect could be "buffer", which could add a parameterizable amount of silence before and after the song, etc.

### Problem 3: Playlist Copying

Make it possible to copy a playlist so that any mutable object that is a component of the playlist is copied, recursively. Instances of enumerated types and other low-level immutable objects can remain shared.

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