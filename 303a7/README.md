# Activity 7

Starter code for activity 7.

You can find the instructions for the activity [here](https://gitlab.cs.mcgill.ca/martin/comp-303-fall-2020/-/blob/master/activities/Activity7.md).

# Design Activity 7 - Integration Activity

_Start on 18 November, complete by 3 December end of day Montreal time zone. Covers *all* chapters of the book. This activity is to be completed in groups of 7 students. Some components of the activity can be completed individually._

## Baseline Code

You can download a _project skeleton_ by pulling the [https://gitlab.cs.mcgill.ca/martin/activity3](https://gitlab.cs.mcgill.ca/martin/activity3) repo. Please note that the baseline for activity 7 is in the package `activity7`. You can ignore the code in the other packages. Note that for this project your need to have the **JavaFX library installed in your workspace**.

## Important Notes

* The goal of this activity is to explore as much of the design space as possible in a constructive manner, and eventually to arrive at a solution for which all the trade-offs are well understood.
* This activity requires you to revisit most of the concepts and techniques covered in the course. 
* There is not necessarily a lot of code to write to complete the activity, but there are many design decisions to consider. 
* Avoid non-essential coding (cosmetic aspects of the GUI, over-realistic feature implementation). If in doubt, seek clarification by opening a GitLabs issue or consulting your TA.
* There are three roles you can play: **implementer**, **reviewer**, **tester**. *Implementer*: Committing functional (non-testing) code to the source tree; *Reviewer*: Contributing to discussions of design trade-offs, reviewing code (including testing code), commenting on issues and pull requests; *Tester*: Contributing testing code.
* To get full marks it is strongly recommended to play different roles in the realization of requirements in different areas (see below), as this will help better demonstrate your breadth of competence in the course material.
* Contribute *only* through GitLabs (as opposed to other social media) so that your contributions can be accounted for. The teaching assistants will not be expected to review any contribution not made on GitLabs when assigning grades.
* Please be mindful that some teammates may be in a different time zone, please give everyone the time necessary to contribute to the activity.
* **Get involved as early as possible. Although you have more time for this activity, you will also *need* more time for this activity.**

## General Design Context

You will be designing the software to control an overhead menu display for a fast-food restaurant. The display is separated into three panels that represent different parts of the menu, for example drinks, main items, snacks. Run the `MenuDisplay` class to see a mock-up.

## Functional Requirements

#### Area 1: Basic Domain Objects

1. A `Menu` shall comprise multiple menu items. It shall be possible to add and remove items from the menu.
2. The information for every menu item shall include a _food type category_ (e.g., drinks, snacks, mains), a _name_ (e.g., "coffee"), a _price_ (e.g., $2.50), and a set of zero or more _dietary categories_ (vegetarian, vegan, gluten free, etc.).
3. It shall be possible to obtain from the menu well-defined subsets of menu items by filtering on their price, food type or any combination of dietary category.

#### Area 2: Special Domain Objects

4. Certain menu items can be _sizeable_. A sizeable item should come in three sizes (small, medium, large). The small and large versions should have a corresponding negative/positive price adjustment.
5. It shall be possible to create combo items (e.g., beer and chips). Combos shall have a discounted (e.g., 10% off the normal total price). The size of the discount may vary from one combo to the next.
6. It shall be possible to put some items on special (e.g., chips are 10% off this week). Combo items can also be put on special, which further discounts their price. 
7. For any item, it shall be possible to obtain a String-typed _description_ of the item that includes all relevant information. The name of the item should always come first, the price, last, and any other information (e.g., its size, whether it's on special), shall come in the middle.

#### Area 3: Notification

8. Whenever there is a change to the menu, the menu displays shall be updated accordingly to display the description of all items.
9. The items within a panel shall be displayed either in alphabetical order of description or in increasing order of price. This setting does not need to be available in the GUI, but it shall be possible to change this option through a clearly labelled constant in the code.

#### Area 4: Configurations

10. Different subsets of items shall be displayed on the three menu panel. By default, drinks on the left, mains in the center, and snacks on the right. 
11. It shall be possible to configure which subset of the menu items shall appear on each panel through the writing of a _configuration_ in the code. An example of a different configuration would be all items in the center, the ten cheapest items on the right, and the combo items on the right. The structure of the code should make it possible to add new configurations.
12. The user shall be able to choose between three different configurations using the user interface. It should be possible to change which configurations the user can choose (if there are more than three) by customizing the source code.

## Non-Functional Requirements

1. The solution shall respect the principles of good design seen in the course;
2. The solution shall be documented;
3. The solution shall be tested;
4. The solution shall make use of functional-style programming, **but only** where the context favors a functional-style solution, and such a solution leads to more robust and clear code than the imperative-style alternative.

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

The contributions are evaluated individually and separately for each role, where implementer and reviewer are worth 40% each and tester is worth 20%.

The following instrument will be used for each role, where midrange values are used for assessments that fall between categories.

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


