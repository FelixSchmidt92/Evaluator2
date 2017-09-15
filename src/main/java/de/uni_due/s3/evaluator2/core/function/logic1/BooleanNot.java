package de.uni_due.s3.evaluator2.core.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;

/**
 * Implement openmath logic not. Example: !true => false
 * 
 * @author spobel
 *
 */
public class BooleanNot extends BinaryFunction {

	public BooleanNot() {
		super(OMSPriority.getPriority(OMSymbol.LOGIC1_NOT));
	}

	/**
	 * Takes one boolean argument and returns the opposite value. Expects one
	 * argument of type OMSymbol.LOGIC_TRUE or LOGIC_FALSE
	 * 
	 * @return true or false as OMS
	 * @throws FunctionInvalidArgumentTypeException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		try {
			return (getBooleanSyntax(arguments.get(0))) ? OMSymbol.LOGIC1_FALSE : OMSymbol.LOGIC1_TRUE;
		} catch (Exception e) {
			throw new FunctionInvalidArgumentTypeException(this, "(1)Boolean|Integer|Float");
		}
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {

		return "not(" + getSageSyntax(arguments.get(0)) + ")";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {

		return "\\neg" + arguments.get(0);
	}
}
