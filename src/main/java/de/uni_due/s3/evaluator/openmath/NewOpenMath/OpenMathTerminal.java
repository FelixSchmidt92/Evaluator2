package de.uni_due.s3.evaluator.openmath.NewOpenMath;

/**
 * This abstract Class is a Wrapper-Class for all Terminals.
 * 
 * All Terminals inherit from this class, like OpenMathString, OpenMathInteger, ...
 * This Class makes sure that always an Terminal returns form every Function.
 * If a Function tries to return an OpenMathObject or an OpenMatApplication
 * this results in an Error.
 * 
 * @author dlux
 *
 * @param <Key> The Key which can be specified (just copied from OpenMathObject)
 */
public abstract class OpenMathTerminal<Key> extends OpenMathObject<Key>{

}
