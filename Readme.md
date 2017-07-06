This is the new Evaluator.

It is (mostly) backwards compatible to the old Evaluator.

It features a new Tuple-language which represent a matrix or a vector, complex..

A new DataStructure is used. Using JAXBOpenMath-Standard and creating a tree.
Functions implemented in this Evaluator directly calculate with the given 
subtree and return an OpenMath-Objects.






Cloning this Repo:

Maven firstly checks if all Dependencies are available, but these have to be 
downloaded beforehand. So it results in an Project full of errors.

If you want to clone this repo in eclipse, do the following:

--> Import it as usual, via the Import-Wizard in eclipse (Import -> git Project)

--> After Importing the repo you have to run ONCE the getDependencies(.sh|.bat)
    file. Run this file from the root-project folder.
    This means the Console is at "~/git/Evaluator2/" and your call should look
    like:
    
    --> Windows: "lib\getDependencies.bat"
    
    --> Linux  : "lib/getDependencies.sh"
    
    
After this you should find all Dependencies needed for this Project in the
lib directory and you are ready to go!