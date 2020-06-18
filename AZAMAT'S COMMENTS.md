# My Comments
* This is not production level of code, test coverage is pretty poor, coding style is just
acceptable not good, should be generalized error handling and error to HTTP response mapping
* There is one  dirty hack in solution: I added exclude filter for my postprocessor code when it 
sets accessible to true. When spotbugs raised an error I didn't want to spend more time looking
for cleaner solution for test assignment. 
* Also I wanted to implement parallel calls to different translate engines but decided not to
do because it's just a test assignment.
* See all TODO's, there I tried to mark important things needs to be implemented.
 
* Now I understand why memsource uses mongoDB. Because of dynamic struture of documents.

# The question is how to handle multiple formats. 
* One of solutions can be to translate to json and handle as json.
* Another: came up with some very universal structure and have 2 converters for each format
At the moment I don't know what is the best.

# Thanks for the assignment.
* I have never before worked with dynamic jsons.

# Time spent:
* 17.6 ~4 hours, had solution without test and multi translate engines support. 
        ~2.5 hours I tried to handle dynamic just until I remembered JsonPath lib
* 18.6 3 hours.
 Total: ~7 hours. 
* In real conditions I will estimate such a task to half sprint, in case that ONLY
application logic should be created (with existing common-rest library, common approach
where and how to store docs). 
