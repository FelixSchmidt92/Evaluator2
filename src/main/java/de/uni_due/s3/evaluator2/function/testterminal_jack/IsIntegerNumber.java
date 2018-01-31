package de.uni_due.s3.evaluator2.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Function tests if the first Argument can be casted to OMI, if it does
 * true will be returned (is a Integer), false otherwise.
 * 
 * 
 * @author spobel
 *
 */
public class IsIntegerNumber extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		try {
			getIntegerSyntax(arguments.get(0));
		} catch (FunctionInvalidArgumentTypeException e) {
			return OMSymbol.LOGIC1_FALSE;
		}
		return OMSymbol.LOGIC1_TRUE;
	}
	
	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

}
