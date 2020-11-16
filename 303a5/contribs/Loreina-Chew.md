### Problem 1

- Review: Consider where it would be better to store play counts first for edge cases, and also to iterate through albums/playlists (anything with multiple playables) to match parent playCount boolean. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/issues/8)
- Discuss: Suggest storing boolean variable default to true to keep track of an optional play count. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/issues/9)
- Test: Wrote a test suite for problem 1 that covers all play count cases: initializing, incrementing by 1, obtaining, setting, reseting. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/19, https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/blob/e094bc0c209050ca4e5912d7f880440c70e5f0e0/test/activity5/Problem1TestSuite.java)
- Test: Merge my written test suite with existing one, where all tests were failing. I fixed the failed tests, updated them to be more expansive with `assertEquals()` and `assertTrue()`, and added new tests to cover more cases. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/19, https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/19#note_30729)

### Problem 2

- Implement: Add javadoc comments to `AbstractPlayable`, our abstract class we used to design the unified playing algorithm. I discuss how we implemented the template method design pattern in each of the 3 steps. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/22)
- Review: Review problem 2 implementation. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/5)
- Review: Review problem 2 fixes. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/15)
- Discuss: Share breakdown for better design for relations between methods that use play count information. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/5#note_29328)
- Discuss: Discuss tradeoffs of having `addCount()` and `playSilence()` methods since they're only used in 1 instance but kept them for more effective code reuse. Discuss having silence length variable in constructor to reduce initialization. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/issues/10)
- Discuss: Suggest more test cases to add to Problem 2 test suite for end-to-end test coverage. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/20#note_31043)
- Test: Write test suite for problem 2 that goes through methods in `AbstractPlayable` applicable to the unified playing algorithm. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/18)
- Test: Merge 2 test suites together and defined the purposes of each test case (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/23)
- Test: Fix broken test cases and add console output test cases for 2nd and 3rd step in template method design pattern, `playSilence()` and `play()`. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/31)

### Problem 3

- Implement: Fixes to Problem 3 initial implementation including switching add/remove methods to private, starting discussion on overriding command interface methods, better execution of printLibrary() that throws exception when library has no playables/is empty. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/11)
- Implement: Start implementation of idea to have Commands within an anonymous class that directly calls Command Executor after, satisfying Law of Demeter. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/merge_requests/13)
- Review: Propose 2 new design choices for Problem 3 after breaking down the current implementation with a UML. 1) To add a layer of concrete Command classes for each playable (with attached UML). 2) Disapparating the need for 2 add (`addItem()`, `ADD()`) and remove (`removeItem()`, `REMOVE()` methods) by creating commands as instances of an anonymous class within `addItem()` and `removeItem()`. (https://gitlab.cs.mcgill.ca/mnassif/303a5t4/-/issues/4#note_29392)
