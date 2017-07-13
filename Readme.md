This is the new Evaluator.

It is (mostly) backwards compatible to the old Evaluator.

It features a new Tuple-language which represent a matrix or a vector, complex..

A new DataStructure is used. Using JAXBOpenMath-Standard and creating a tree.
Functions implemented in this Evaluator directly calculate with the given 
subtree and return an OpenMath-Objects.






Cloning this Repo:

Maven has an old version of the Jars in the lib-Folder. (To satisfy Maven)

This may cause Errors by some tests or by Evaluator2 iteself.

To refresh and get the actual dependencies, do the following -->

--> After importing the repo you have to run Maven generate-Ressources (or another Maven LifeCycle)
	After Maven completed its procedure it automatically downloaded the newest Version of the 
	Dependencies, which are included it the lib folder.
	
    
After this, you are ready to go!