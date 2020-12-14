# Activity 6

Starter code for activity 6.

You can find the instructions for the activity [here](https://gitlab.cs.mcgill.ca/martin/comp-303-fall-2020/-/blob/master/activities/Activity6.md).

# Design Activity 6

*Start on 9 November, complete by 16 November end of day Montreal time zone. Covers Chapters 5-8 of the book. This activity is to be completed in groups of maximum 7 students. Some components of the activity can be completed individually.*

## Baseline Code

You can download a _project skeleton_ by pulling the [https://gitlab.cs.mcgill.ca/martin/activity3](https://gitlab.cs.mcgill.ca/martin/activity3) repo. Please note that the baseline for activity 6 is in the package `activity6`. You can ignore the code in the other packages. Note that for this project your need to have the **JavaFX library installed in your workspace**.

## Rules of Engagement

* The **goal of this activity** is to explore as much of the design space as possible in a constructive manner, and eventually to arrive at a solution for which all the trade-offs are well understood.
* The activity is organized into **three design problems**.
* For each problem there are three roles you can play: **implementer**, **reviewer**, **tester**. As a necessary (but not sufficient) condition for full marks, you must earn one credit for three different problems, and the roles must be different for each problem. The roles are explained below.
* Contribute *only* through GitLabs (as opposed to other social media) so that your contributions can be accounted for. The teaching assistants will not be expected to review any contribution not made on GitLabs when assigning grades.
* Please be mindful that some teammates may be in a different time zone, please give everyone the time necessary to contribute to the activity.

## Process

Design, discuss, implement, and test a solution for the three problems below. 

* Team members should try to submit an early/initial implementation for each of the three problems. This can be done by one person alone, or a small subset working in collaboration. Committing code earns an **implementer** credit.

* Once code is committed, one or more team members can review one aspect of the code as a new issue. The review should include design issues, discussions of tradeoffs, and alternatives/constructive feedback. This earns a **reviewer** credit, along with any additional discussion of the issue. It is especially encouraged to contribute **design diagrams** to issue descriptions and comments.
* Team members should implement the tests for the production code. This earns a **tester** credit. Note that tests can be contributed at any stage. Often, writing tests early helps identify  a design or implementation problem, and thus constitutes an important contribution even if they need to be rewritten to accommodate the change. This earns a **tester** credit.

* Team members implement revisions of production or test code based on comments and submit a merge request. This earns an **implementer** or tester credit, as applicable.

* You can also review the details of the code of a merge requests, thus earning a **reviewer** credit.

## Design Problems

**For each problem, write unit tests for the code you implement. Consider the quality of your test suite in terms of statement and branch coverage.** Note that the solution requires GUI code to make the problems sufficiently interesting. However, there is no expectations that you must produce a professional-quality GUI. You can safely keep the GUI programming to a minimum that does not go far beyond the sample code. **Focus your efforts on effective use of the Observer pattern. Your solution must include at least one observable instance in your application code (in contrast to the observable widgets in the JavaFX library).**

### Problem 1: Add Song Feature

Add a feature that allows the user to select a file from their system and add it to the library using a file chooser. Once the file is added to the library, all appropriate views of the library should be added. Note that you can limit yourself to songs: you do not need to worry about adding playlists and albums. Do not was time with input validation (e.g., if the file is not a music file, etc.): just assume it's ok to add the selected file.

### Problem 2: Remove Playable Item Feature

Add a feature that allows user to delete a playable item from the list. The list should faithfully show all items in the library, and immediately refresh to show the current state of the library after the item is deleted. 

### Problem 3: Status and Logging

Every time a song is added or an item is removed from the library, log this information to the console. Also, add a status bar to the GUI that shows the same message as the one logged to the console.

## Deliverables

1. The documented and tested code of the solution and all associated resources as described above.
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