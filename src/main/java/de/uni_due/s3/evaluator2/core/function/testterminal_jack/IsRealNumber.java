package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;

/**
 * This Function tests if the first Argument can be casted to OMI|OMF, if it
 * does true will be returned (is a Number), false otherwise.
 * 
 * Note:
 * 	PI and E are also Numbers so true will be also returned
 * 
 * @author spobel
 *
 */
public class IsRealNumber extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		try {
			getDoubleSyntax(arguments.get(0));
		} catch (FunctionInvalidArgumentTypeException e) {
			return OMSymbol.LOGIC1_FALSE; 
		}
		return OMSymbol.LOGIC1_TRUE;
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
