package de.uni_due.s3.evaluator2.core.function;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;

/**
 * This is a wrapper class for ConstructorFuntion which provides a default implementation
 * for all getPartial...-functions, so that not every extending function has to 
 * implement those. You can implement those functions you need.
 * 
 * @author dlux, frichtscheid, spobel
 */
public abstract class Function extends ConstructorFunction {

	/**
	 * If a function should have a special latex representation, then this function
	 * has to be overwritten by that class.
	 * 
	 * @param omel
	 * @return
	 * @throws FunctionException
	 * @throws NoRepresentationAvailableException
	 */
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no LaTeX representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Define here how the Syntax should look like in Sage for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getSageSyntax(omElement). So only deal here with the Representation of
	 * this Function and call (usually) the arguments in getSageSyntax(omElement)
	 * For Examples: see Plus, Minus or Set
	 * 
	 * @param arguments
	 *            A List of Arguments for this Function. Note: The arguments are not
	 *            evaluated!
	 * @return A String Representation of this Function AND all innerFunction
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws FunctionInvalidArgumentException
	 * @throws CasException
	 */
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no R representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Define here how the Syntax should look like in Sage for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getSageSyntax(omElement). So only deal here with the Representation of
	 * this Function and call (usually) the arguments in getSageSyntax(omElement)
	 * For Examples: see Plus, Minus or Set
	 * 
	 * @param arguments
	 *            A List of Arguments for this Function. Note: The arguments are not
	 *            evaluated!
	 * @return A String Representation of this Function AND all innerFunction
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws FunctionInvalidArgumentException
	 * @throws CasException
	 */
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Sage representation for function " + this.getClass().getSimpleName());
	}

}
