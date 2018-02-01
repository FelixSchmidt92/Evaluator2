package de.uni_due.s3.evaluator2.function;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidResultTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.visitor.IPartialMethods;
import de.uni_due.s3.evaluator2.visitor.operation.OMCountVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMVariableVisitor;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * 
 * AbstractFunction is an abstract Class which all Function inherit it and can
 * be executed with their ArgumentList. All Classes which inherit this Class
 * <b>have to</b> implement minArgs, maxArgs and the
 * getPartialOpenMathElementResult-method(where the logic of the specific
 * Function should be).
 * 
 * If you want to add a new Function please refer to the OpenMath Content
 * Dictionaries (www.openmath.org/cd/) and add the new Function to the
 * corresponding Package. If the Function is not listed in OpenMath, use the
 * *_jack package (then this Function is a jack-specific one, '*' can be linalg,
 * string etc..).
 * 
 * Getting a Function can be achieved with the OMSFunctionDictionary. The
 * OMSFunctionDictionary-Class will contain all available Functions for this
 * Evaluator. By Adding a new Function make this Function also available at
 * OMSFunctionDictionary.
 * 
 * 
 * @author dlux, frichtscheid, spobel
 */
public abstract class AbstractFunction implements IPartialMethods {

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
	abstract protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException;

	/**
	 * First: Check If maxArgs can be infinitely. Second:Check if arguments length
	 * is in Range of minArgs and maxArgs to ensure for execute() that the correct
	 * number of Arguments are passed. Then: call execute with the correct number of
	 * arguments.
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
	public final Object evaluate(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (arguments == null)
			throw new FunctionInvalidArgumentException(this, "The List of arguments is of Type NULL");
		argsBetweenMinMax(arguments); // Double-Check!
		Object result = getPartialOpenMathElementResult(arguments);

		if (OMSymbol.isTerminal(result)) {
			return result;
		}
		throw new FunctionInvalidResultTypeException(this);
	}

	/**
	 * This Method just tests if the length of the Arguments is in between minArgs
	 * and maxArgs. If not then a FunctionInvalidNumberOfArgumentsException is
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
			if (minArgs() == max) {
				throw new FunctionInvalidNumberOfArgumentsException(this,
						" got " + arguments.size() + " but needs " + minArgs() + " Arguments.");
			} else {
				throw new FunctionInvalidNumberOfArgumentsException(this,
						" got " + arguments.size() + " but needs Arguments between " + minArgs() + " and " + max + ".");
			}
		}
	}

	/**
	 * This method is called by OMVisitor, to tell it if it has to evaluate the
	 * arguments or not.
	 * 
	 * Override this method in a Function and set return to false to get the
	 * unevaluated List of arguments.
	 * 
	 * True: evaluate the Arguments False: not evaluate just pass through Arguments
	 * 
	 * @return true, if not implemented otherwise
	 */
	public boolean argumentsShouldBeEvaluated() {
		return true;
	}

	/**
	 * Specify here the minimum number of Arguments for your Function. If minArgs
	 * returns -1 (or 0) then the length can be the smallest amount possible (here
	 * explicitly 0, negative number of Arguments is not possible)
	 * 
	 * @Example minArgs returns 2, so number of arguments could be 2, 3, 4, ...
	 * 
	 * @return the minimum number of Arguments as Integer
	 */
	abstract protected int minArgs();

	/**
	 * Specify here the maximum number of Arguments for your Function. If maxArgs
	 * returns -1 (or any other negative number) then there is no limit how many
	 * Arguments this function can maximal get. (currently limited to
	 * Integer.MAX_Value)
	 * 
	 * @Example maxArgs returns 5, so number of arguments could be 5, 4, 3, ...
	 * 
	 * @return the maximum number of Arguments as Integer
	 */
	abstract protected int maxArgs();

	/**
	 * Call this Function for getting number of OpenMath Nodes.
	 * 
	 * @param omElement
	 * @return number of OpenMath Nodes
	 * @throws EvaluatorException
	 * @throws OpenMathException
	 */
	protected final Integer countNodes(Object omElement) throws EvaluatorException, OpenMathException {
		return OMCountVisitor.getInstance().visit(omElement);
	}

	/**
	 * Call this Function before you use something in Sage to initialize all
	 * Variables before.
	 * 
	 * TODO DOKU!
	 * 
	 * @param omElement
	 * @return
	 * @throws EvaluatorException
	 * @throws OpenMathException
	 */
	protected final Set<OMV> getVariablesAsOMVSet(Object omElement) throws EvaluatorException, OpenMathException {
		return OMVariableVisitor.getInstance().visit(omElement);
	}

}
