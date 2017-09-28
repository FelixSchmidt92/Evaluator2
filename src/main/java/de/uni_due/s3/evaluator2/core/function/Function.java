package de.uni_due.s3.evaluator2.core.function;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoSageRepresentationAvailableException;

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
public abstract class Function extends ConstructorFunction {

	/**
	 * Define here how the Syntax should look like in Double for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getDoubleSyntax(omElement). So only deal here with the Representation of
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
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Boolean-representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Define here how the Syntax should look like in Double for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getDoubleSyntax(omElement). So only deal here with the Representation of
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
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Double-representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Define here how the Syntax should look like in Double for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getDoubleSyntax(omElement). So only deal here with the Representation of
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
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Integer-representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * If a function should have a special latex representation, then this function
	 * has to be overwritten by that class.
	 * 
	 * @param omel
	 * @return
	 * @throws FunctionException
	 * @throws NoRepresentationAvailableException
	 */
	public String getPartialLatexSyntax(List<Object> omel) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no LaTeX representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * If a function should have a special latex representation, then this function
	 * has to be overwritten by that class.
	 * 
	 * @param omel
	 * @return
	 * @throws FunctionException
	 * @throws NoRepresentationAvailableException
	 */
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no List representation for function " + this.getClass().getSimpleName());
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

	/**
	 * Define here how the Syntax should look like in String for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getStringSyntax(omElement). So only deal here with the Representation of
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
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no String-representation for function " + this.getClass().getSimpleName());
	}

}
