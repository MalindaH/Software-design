### Requirement 4
- Initially introduced the idea of using strategy design pattern for the requirement 4. Where various concrete classes of type `Filter` used filtering criteria on tags suggested by client to filter the list of songs. 
- Donovan further improved the implementatin by removing the to have separate concrete defintion for fitering through different tags and allowed user to specify tags or search through all the tags. 
- I created `FilterAND` and `FilterOR` classes of type `Filter` that applies two filters on the list of songs. 
- I wanted to give a bit freedom to user to use the same fitlers again so using filter objects in `FitlerAND` and `FilterOR` was most sensical because it allowed client to either Filter the list of songs such that all the filtering criteria is met or any one of the filter criteria is met making the design more open ended and giving client infinite choices. 

- `FilterAND` : takes two filters objects and filter the list of songs such that list returend after filter meets both filter criteria. 
- `FilterOR` : takes two filters objects and filter ths list of songs such that list returned after filter meets either one of the filter criteria. 
- Both `FilterAND` and `FilterOR` gives more freedom and choices to the client and allows clien to combine various filters together in any logica way possible. 
- Design was tested in the client class ensure proper functionality. 


### Requirement 6
- modified the original `generatePlaylist` method in the library class because it didn't take into account that search can be empty and no songs will be returned by filters. 
- so if the filter returns empty list of songs that means library shouldn't be able to generate the playlist at all because if it does that then it defeats the purpose of generating playlist based on search result. 
- Added second method `generatePlaylist` ( function overloading ) Where client can specify multiple filters as method arguments and a boolean value for logical operator. 
- method takes in **n** numbers of filters and apply them based on the logical operator. 
- if the passed boolean argument is true that means AND = true and logical operator applied to the filters  is AND and playlist is generated of the songs that satisfies all the search criteria specified by the client.
- if the passed boolean argument is false that means OR = true and logical operator applied to the filters is OR and playlist is generated of the songs that satisfied either one the search criteria specified by the client. 
- Both overloaded methods make sure that playlist is only generated if the list of songs returned by filters is not empty. 


# Design Decisions
### Requrement 4
![alt text](images/Filter.png)

