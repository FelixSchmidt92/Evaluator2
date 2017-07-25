package de.uni_due.s3.evaluator.core.function;

import java.util.List;

import de.uni_due.s3.evaluator.core.syntaxvisitor.OMToSageVisitor;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoSageRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Function is an abstract Class where functions can be executed with the
 * ArgumentList. All Classes which inherit this Class <b>have to</b> implement
 * minArgs, maxArgs and the execute method(where the logic should be).
 * 
 * If you want to add a new Function please refer to the OpenMath Content
 * Dictionaries (www.openmath.org/cd/) and add the new Function to the (maybe
 * new) correct Package If this Function is not listed in OpenMath, use the
 * *_jack package (then this Function is a jack-specific one, '*' can be linalg,
 * string etc..).
 * 
 * Getting an Function can be achieved with the OMSFunctionDictionary. The
 * OMSFunctionDictionary-Class will contain all available Functions for this
 * Evaluator. By Adding a new Function make this Function also available at
 * OMSFunctionDictionary.
 * 
 * @author dlux, frichtscheid, spobel
 */
public abstract class Function {

	/**
	 * Here happens the Logic of every single Function. The arguments-List is in
	 * Range of minArgs and maxArgs.
	 * 
	 * @param arguments
	 *            with a length beforeHand-Check like specified in minArgs and
	 *            maxArgs
	 * @return a new specific Object, the result after Execution
	 * @throws NoRepresentationAvailableException
	 * @throws CasNotAvailableException
	 * @throws CasEvaluationException
	 * @throws OpenMathException 
	 * @throws FunctionInvalidArgumentException
	 */
	abstract protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException;

	/**
	 * First: Check If maxArgs can be infinitely. Second:Check if arguments
	 * length is in Range of minArgs and maxArgs to ensure for execute() that
	 * the correct number of Arguments are passed. Then: call execute with the
	 * correct number of arguments.
	 * 
	 * @param arguments
	 *            A List of Arguments for this Function
	 * @return the result of execution as an EObject
	 * @throws FunctionException
	 * @throws NoRepresentationAvailableException
	 * @throws CasNotAvailableException
	 * @throws CasEvaluationException
	 * @throws OpenMathException 
	 * @throws FunctionInvalidNumberOfArgumentsException
	 *             if number of Arguments is not between
	 */
	public final Object evaluate(List<Object> arguments) throws FunctionException, FunctionInvalidArgumentException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		if(arguments == null)
			throw new FunctionInvalidArgumentException(this, "The List of arguments is of Type NULL");
		argsBetweenMinMax(arguments); // Check
		return execute(arguments);
	}

	/**
	 * This Method just tests if the length of the Arguments is in between
	 * minArgs and maxArgs. If not then a FunctionInvalidNumberOfArgumentsException is
	 * thrown.
	 * 
	 * @param arguments
	 *            the List of arguments
	 * @throws FunctionInvalidNumberOfArgumentsException
	 *             if number of Arguments is not between
	 */
	public final void argsBetweenMinMax(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException {
		// Check max, if infinitely set max to Integer.MAX_VALUE
		int max = maxArgs();
		if (maxArgs() < 0) {
			max = Integer.MAX_VALUE;
		}

		if (!(minArgs() <= arguments.size() && max >= arguments.size())) {
			// Arguments.size is not between minArgs and maxArgs so throw Error
			throw new FunctionInvalidNumberOfArgumentsException(this, minArgs(), maxArgs(), arguments.size());
		}
	}

	/**
	 * This method is called by OMVisitor, to tell it if it has to evaluate the
	 * arguments or not.
	 * 
	 * Override this method in a Function and set return to false to get the
	 * unevaluated List of arguments.
	 * 
	 * True: evaluate the Arguments False: not evaluate just pass through
	 * Arguments
	 * 
	 * @return true, if not implemented otherwise
	 */
	public boolean argumentsShouldBeEvaluated() {
		return true;
	}

	/**
	 * Specify here the minimum number of Arguments for your Function. If
	 * minArgs returns -1 (or 0) then the length can be the smallest amount
	 * possible (here explicitly 0, negative number of Arguments is not
	 * possible)
	 * 
	 * @Example minArgs returns 2, so number of arguments could be 2, 3, 4, ...
	 * 
	 * @return the minimum number of Arguments as Integer
	 */
	abstract protected int minArgs();

	/**
	 * Specify here the maximum number of Arguments for your Function. If
	 * maxArgs returns -1 (or any other negative number) then there is no limit
	 * how many Arguments this function can maximal get. (currently limited to
	 * Integer.MAX_Value)
	 * 
	 * @Example maxArgs returns 5, so number of arguments could be 5, 4, 3, ...
	 * 
	 * @return the maximum number of Arguments as Integer
	 */
	abstract protected int maxArgs();

	/**********************************************************/
	/****************** Translator Section **********************/
	/**********************************************************/

	/**
	 * Call this Function, if you need your argument in Sage Syntax.
	 * 
	 * In getPartialSageSyntax: Call this Function on every argument, to get the
	 * Sage-Syntax of this argument
	 * 
	 * @param omElement
	 *            the argument, which should be represented in Sage
	 * @return a String representation of this argument in Sage
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException 
	 * @throws CasException
	 */
	protected final String getSageSyntax(Object omElement)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		return new OMToSageVisitor().visit(omElement);
	}

	/**
	 * Define here how the Syntax should look like in Sage for this specific
	 * Function All Arguments that are passed here can be recursively called
	 * again with getSageSyntax(omElement). So only deal here with the
	 * Representation of this Function and call (usually) the arguments in
	 * getSageSyntax(omElement) For Examples: see Plus, Minus or Set
	 * 
	 * @param arguments
	 *            A List of Arguments for this Function. Note: The arguments are
	 *            not evaluated!
	 * @return A String Representation of this Function AND all innerFunction
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException 
	 * @throws CasException
	 */
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		throw new NoSageRepresentationAvailableException(
				"There is no sage representation for function " + this.getClass() + " implemented");
	}

	/**
	 * Add here more Translators from OMOBJ to CAS
	 */

	/**********************************************************/
	/****************** Translator Section **********************/
	/**********************************************************/

}
