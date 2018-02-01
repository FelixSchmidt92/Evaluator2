package de.uni_due.s3.evaluator2.function;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToBooleanVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToDoubleVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToIntegerVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToListVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToStringVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToRVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToSageVisitor;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Class inherits from the Abstract Function and uses a default
 * implementation for all partial methods, which inherit from this class.
 * 
 * There is also a method get*Syntax for each partial method which should be
 * called by every single function to get the specific result.
 * 
 * This class can be appended, so that every function does not need to
 * re-implement these methods.
 * 
 * 
 * @author dlux
 *
 */
public abstract class Function extends AbstractFunction {
	/**
	 * Add here more Translators from OMOBJ to Syntax
	 */

	/**
	 * Define here how the Syntax should look like with Symbols for this specific
	 * Function. All Arguments that are passed here can be recursively called again
	 * with getPartialSymbolicSyntax(omElement). So only deal here with the
	 * Representation of this Function and call (usually) the arguments in
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

}