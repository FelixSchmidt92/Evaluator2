## About
The Evaluator 2.0 is the evaluation tool used by JACK for calculations of all kinds (for example, mathematical calculations). The evaluator is therefore used both for the generation of variables, as well as to evaluate the checking rules for correct answers or feedback messages in Fill-In Tasks. It provides a number of functions, e.g. simple arithmetic operations and functions on strings, but it is also able to talk to computer algebra systems and have them evaluated there. Currently, the evaluator is able to communicate with three external systems. These are the systems Symja, Sage and R. It is mostly backwards compatible to the Evaluator 1.0.

## DataStructure
A new DataStructure is used. Using JAXBOpenMath-Standard and creating a tree.
Functions implemented in this Evaluator directly calculate with the given 
subtree and return an OpenMath-Objects.

## Testing
The JUnit-Tests depend on a working Sage- and R-Connection.
A not working Sage- or R-Server leads to a failure in build, caused by not working Tests!

## Building locally
Since the Evaluator uses JAXBOpenMath provided by Artifactory https://harbor.paluno.uni-due.de/artifactory/ (works only inside the university) you need to copy ``.m2/settings.xml`` from this repository to your local ``.m2`` directory.

## Deployment pipeline
The master branch is automatically deployed to artifactory.

## Using it in your project
 The Evaluator can be used in S3-Artifactory enabled maven environments (see "Building locally") like this:
```xml
<dependency>
    <groupId>de.uni_due.s3</groupId>
    <artifactId>Evaluator</artifactId>
    <version>2.0.0</version>
</dependency>
```