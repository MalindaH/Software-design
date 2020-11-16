##Vasileios Airantzis 260735880
I committed all my work to the UniqueArtist&Genre branch. Changes were made to it later during the merge request by my teammates.
###Implementer for problem 2
I implemented the solution for problem 2 focusing on writing a flexible, maintainable and reusable code. For that reason I used factory methods for generating the Artist & Genre objects.
I used the Flyweight design pattern for this implementation to ensure that if client decided to create multiple objects they would not be the same.
I focused a lot on making the code self explanatory and very easy to read and for this I used switches, enum and interface.
I submitted the solution on the 2nd day since I had some free time, but after the review was done by my teammate I changed the modifiers of the object constructors to protected to not allow client to create other objects, not using the intended way.
Finally I submitted a UML diagram of my design in the relevant issue: https://gitlab.cs.mcgill.ca/mnassif/303a3t26/-/issues/6
The code on the main branch is not exactly the same as the one I had originally designed for the solution of this problem, I believe my teammates made some modifications in order for it to connect with the rest of the code, but the general idea remains the same.

###Reviewer for the Song part of problem 1
https://gitlab.cs.mcgill.ca/mnassif/303a3t26/-/issues/16

###Discussant for problem 2 
The review left by my teammate had some good points which led to an interesting discussion and some changes to the code