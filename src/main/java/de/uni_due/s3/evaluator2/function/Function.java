package de.uni_due.s3.evaluator2.function;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidResultTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.visitor.operation.OMCountVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToBooleanVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToDoubleVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToIntegerVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToListVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToStringVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToRVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToSageVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMVariableVisitor;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Function is an abstract Class which all Function inherit it and can be
 * executed with their ArgumentList. All Classes which inherit this Class
 * <b>have to</b> implement minArgs, maxArgs and the execute method(where the
 * logic of the specific Function should be).
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
public abstract class Function{

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
	abstract protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException;

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
	 * Define here how the Syntax should look like with Symbols for this specific
	 * Function. All Arguments that are passed here can be recursively called again
	 * with getPartialSymbolicSyntax(omElement). So only deal here with the Representation
	 * of this Function and call (usually) the arguments in
	 * getPartialSymbolicSyntax(omElement) For Examples: see Plus, Minus
	 * 
	 * @param arguments
	 *            A List of Arguments for this Function. Note: The arguments are not
	 *            evaluated!
	 * @return A Symbolic Representation of this Function AND all innerFunction
	 * @throws EvaluatorException
	 * @throws OpenMathException
	 */
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new FunctionInvalidArgumentTypeException(this, "integer, float, double, variable, symbolic");
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

	/**********************************************************/
	/***************** Translator Section *********************/
	/**********************************************************/

	/**
	 * Add here more Translators from OMOBJ to Syntax
	 */

	/**
	 * Call this Function, if you need your argument in Boolean Syntax.
	 * 
	 * In getPartialBooleanSyntax: Call this Function on every argument, to get the
	 * Boolean-Syntax of this argument
	 * 
	 * @param omElement
	 *            the argument, which should be represented in Boolean
	 * @return a String representation of this argument in Boolean
	 * @throws OpenMathException 
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws CasException
	 */
	protected final Boolean getBooleanSyntax(Object omElement) throws EvaluatorException, OpenMathException {
		try {
			return OMToBooleanVisitor.getInstance().visit(omElement);
		} catch (NoRepresentationAvailableException e) {
			throw new FunctionInvalidArgumentTypeException(this, "Boolean");
		}
	}

	/**
	 * Define here how the Syntax should look like in Boolean for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getBooleanSyntax(omElement). So only deal here with the Representation
	 * of this Function and call (usually) the arguments in
	 * getBooleanSyntax(omElement) For Examples: see Plus, Minus or Set
	 * 
	 * @param arguments
	 *            A List of Arguments for this Function. Note: The arguments are not
	 *            evaluated!
	 * @return A Boolean Representation of this Function AND all innerFunction
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws FunctionInvalidArgumentException
	 * @throws CasException
	 */
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new NoRepresentationAvailableException(
				"There is no Boolean-representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Call this Function, if you need your argument in Double Syntax.
	 * 
	 * In getPartialDoubleSyntax: Call this Function on every argument, to get the
	 * Double-Syntax of this argument
	 * 
	 * @param omElement
	 *            the argument, which should be represented in Double
	 * @return a Double representation of this argument in Double
	 * @throws OpenMathException 
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws CasException
	 */
	protected final Double getDoubleSyntax(Object omElement) throws EvaluatorException, OpenMathException {
		try {
			return OMToDoubleVisitor.getInstance().visit(omElement);
		} catch (NoRepresentationAvailableException e) {
			throw new FunctionInvalidArgumentTypeException(this, "Double");
		}
	}

	/**
	 * Define here how the Syntax should look like in Double for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getDoubleSyntax(omElement). So only deal here with the Representation of
	 * this Function and call (usually) the arguments in getDoubleSyntax(omElement)
	 * 
	 * @param arguments
	 *            A List of Arguments for this Function. Note: The arguments are not
	 *            evaluated!
	 * @return A Double Representation of this Function AND all innerFunction
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws FunctionInvalidArgumentException
	 * @throws CasException
	 */
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new NoRepresentationAvailableException(
				"There is no Double-representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Call this Function, if you need your argument in Integer Syntax.
	 * 
	 * In getPartialIntegerSyntax: Call this Function on every argument, to get the
	 * Integer-Syntax of this argument
	 * 
	 * @param omElement
	 *            the argument, which should be represented in Integer
	 * @return a Integer representation of this argument in Integer
	 * @throws OpenMathException 
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws CasException
	 */
	protected final Integer getIntegerSyntax(Object omElement) throws EvaluatorException, OpenMathException {
		try {
			return OMToIntegerVisitor.getInstance().visit(omElement);
		} catch (NoRepresentationAvailableException e) {
			throw new FunctionInvalidArgumentTypeException(this, "Integer");
		}
	}

	/**
	 * Define here how the Syntax should look like in Integer for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getIntegerSyntax(omElement). So only deal here with the Representation
	 * of this Function and call (usually) the arguments in
	 * getIntegerSyntax(omElement)
	 * 
	 * @param arguments
	 *            A List of Arguments for this Function. Note: The arguments are not
	 *            evaluated!
	 * @return A Integer Representation of this Function AND all innerFunction
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws FunctionInvalidArgumentException
	 * @throws CasException
	 */
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new NoRepresentationAvailableException(
				"There is no Integer-representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * This Function can be called if you want to have the Latex Syntax of the given
	 * element
	 * 
	 * @param omElement
	 *            OM-Object of which you want the latex syntax
	 * @return Latex-String
	 * @throws EvaluatorException
	 * @throws OpenMathException 
	 */
	protected final String getLatexSyntax(Object omElement) throws EvaluatorException, OpenMathException {
		if (this instanceof BinaryFunction) {
			return ((BinaryFunction) this).getBinaryLatex(omElement);
		} else {
			return OMToLatexVisitor.getInstance().visit(omElement);
		}
	}

	/**
	 * If a function should have a special Latex representation, then this Function
	 * has to be overwritten by that class.
	 * 
	 * @param omel
	 * @return the specific Latex-Representation for that Function
	 * @throws FunctionException
	 * @throws NoRepresentationAvailableException
	 */
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new NoRepresentationAvailableException(
				"There is no LaTeX representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * This Function can be called if you want to have the List-Syntax of the given
	 * element
	 * 
	 * @param omElement
	 *            OM-Object of which you want the List-Syntax
	 * @return A List
	 * @throws EvaluatorException
	 * @throws OpenMathException 
	 */
	protected final List<Object> getListSyntax(Object omElement) throws EvaluatorException, OpenMathException {
		return OMToListVisitor.getInstance().visit(omElement);
	}

	/**
	 * If a Function should have a special List-Representation, then this Function
	 * has to be overwritten by that Class.
	 * 
	 * @param omel
	 * @return the specific List-Representation for that Function
	 * @throws OpenMathException 
	 * @throws FunctionException
	 * @throws NoRepresentationAvailableException
	 */
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException, OpenMathException {
		throw new NoRepresentationAvailableException(
				"There is no List representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Call this Function, if you need your argument in R Syntax.
	 * 
	 * In getPartialRSyntax: Call this Function on every argument, to get the
	 * R-Syntax of this argument
	 * 
	 * @param omElement
	 *            the argument, which should be represented in R
	 * @return a String representation of this argument in R
	 * @throws OpenMathException 
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws CasException
	 */
	protected final String getRSyntax(Object omElement) throws EvaluatorException, OpenMathException {
		return OMToRVisitor.getInstance().visit(omElement);
	}

	/**
	 * Define here how the Syntax should look like in R for this specific Function.
	 * All Arguments that are passed here can be recursively called again with
	 * getRSyntax(omElement). So only deal here with the Representation of this
	 * Function and call (usually) the arguments in getRSyntax(omElement) For
	 * Examples: see Plus, Minus or Set
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
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new NoRepresentationAvailableException(
				"There is no R representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Call this Function, if you need your argument in Sage Syntax.
	 * 
	 * In getPartialSageSyntax: Call this Function on every argument, to get the
	 * Sage-Syntax of this argument
	 * 
	 * @param omElement
	 *            the argument, which should be represented in Sage
	 * @return a String representation of this argument in Sage
	 * @throws OpenMathException 
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws CasException
	 */
	protected final String getSageSyntax(Object omElement) throws EvaluatorException, OpenMathException {
		return OMToSageVisitor.getInstance().visit(omElement);

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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new NoRepresentationAvailableException(
				"There is no Sage representation for function " + this.getClass().getSimpleName());
	}

	/**
	 * Add here more Translators from OMOBJ to CAS
	 */

	/**
	 * Call this Function, if you need your argument in String Syntax.
	 * 
	 * In getPartialStringSyntax: Call this Function on every argument, to get the
	 * String-Syntax of this argument
	 * 
	 * @param omElement
	 *            the argument, which should be represented in String
	 * @return a String representation of this argument in String
	 * @throws OpenMathException 
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws CasException
	 */
	protected final String getStringSyntax(Object omElement) throws EvaluatorException, OpenMathException {
		try {
			return OMToStringVisitor.getInstance().visit(omElement);
		} catch (NoRepresentationAvailableException e) {
			throw new FunctionInvalidArgumentTypeException(this, "String");
		}
	}

	/**
	 * Define here how the Syntax should look like in String for this specific
	 * Function All Arguments that are passed here can be recursively called again
	 * with getStringSyntax(omElement). So only deal here with the Representation of
	 * this Function and call (usually) the arguments in getStringSyntax(omElement)
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
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new NoRepresentationAvailableException(
				"There is no String-representation for function " + this.getClass().getSimpleName());
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