# Design Activity 2

*Start on 28 September, complete by 6 October end of day Montreal time zone. Covers Chapters 2-3 of the book with a focus on Chapter 3. Project repos will be available on 28 September.*

## Problem Statement

Using the principles, mechanisms, and techniques seen in Chapter 3 of the book, design and write the code necessary to enhance the music library design realized in Activity 1 to meet the following requirements. Your enhanced system must not violate any of the principles of good design seen in Chapter 2.

1. Use **interfaces** to support multiple implementations of the main domain concepts in your design. This should include at the very least songs, playlists, and albums, but you are encouraged to explore other options. Provide at least two implementations offering different services and design tradeoffs. As an illustration of what this could mean in practice consider the differences between the `ArrayList` and `LinkedList` implementations of `List`. 
2. Provide at least two ways to **obtain the songs in any object** that contains songs (that is, library, albums, playlists). One of the two ways should be through the application of the Iterator design pattern.
3. Determine various _scenarios_ for using your music library APIs by the client code (e.g., playing a song), and apply the **Interface Segregation Principle** to allow the client code to exercise the various scenarios without dependencies to services in might not need for the implementation of various scenarios. 
4. Your solution should make it possible to obtain a _view_ of the library (i.e., a list of songs) that includes only the songs that respect a certain ***filtering predicate***. You need to decide how to realize this concept of a view in your design. Examples of filtering predicates include all songs of a certain genre, all songs by a certain artists, songs of certain lengths, etc. The filtering predicates should be *open-ended*, in the sense that the client code should be able to specify how to filter without having to select from a finite list of options.
5. Your solution should make it possible to **_sort_ a library view** according to different criterion (e.g., by song title, genre, length, whatever). Your design should provide both common options (e.g., by title) *and* allow client code to specify open-ended sorting strategies.
6. Your solution should provide a **playlist generation feature**. This feature should allow client code to automatically generate playlists using an extensible set of algorithms (e.g., all songs by Bob Marley). Provide at least two different algorithms to illustrate your design.

## What To Do Now

1. Determine which code base you will use to move forward. Open an issue titled "Baseline selection" and, if you are happy with your Chapter 2 solution, propose it with a brief summary, with other proposals being entered in the comments and various members voting up or down. Try to select your baseline within 3 days. You can discuss this in parallel with other issues.
2. Unless you have a better strategy, open one issue per requirement and discuss a general strategy. Try including brief UML sketches. With JetUML this is _extremely easy_: using the "Copy to Clipboard" feature, you can paste the diagram directly into the issue comments, and it will be uploaded and linked in one go.
3. As soon as an idea stabilizes, implement it and commit it, and then have other team members review the implementation.

## Deliverables

1. The documented code of the solution. Include main method that exercises the main scenarios of the code. Tag the final code as `v2.0`. If for any reason you need to make changes to the version you thought was final, increment the second number (e.g., `v2.1`) We will look at the last available tagged version.
2. In the file `README.md` a bullet list of the main design decisions and the rationale for them, linked to the various commits, issues, files, and other resources related to the discussion. *Make sure to include a summary of the rationale for selecting your baseline code.*
3. In a folder `contribs/` one file per team member, titled `Firstname-LastName.md` which lists and briefly describes your main contributions that best showcase your knowledge of the material, linking to appropriate repository resources. This deliverable is optional for Activity 2 but strongly encouraged as it will help the TA better assess your contributions.

## Design Decisions
See [a relative link](./DesignReasoning.md)

## Other Important Information

* [Rules of Engagement](Rules.md)
* [Marking Scheme](Marking.md)
* [Policy on Code Reuse](CodeReuse.md)

