package de.uni_due.s3.evaluator2.core.function.transc1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Returns the natural logarithm (base e) of a double value.
 * 
 * 
 * @author spobel
 *
 */
public class Log extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		Object result;
		if (OMTypeChecker.isOMNumber(arguments.get(0))) {
			Double arg1 = getDoubleSyntax(arguments.get(0));
			if (arg1 <= 0.0) {
				throw new FunctionInvalidArgumentException(this, "Function Log expects values greater than zero.");
			}
			arg1 = Math.log(arg1);
			result = OMCreator.createOMIOMF(arg1);
		} else {
			if (OMTypeChecker.isOMV(arguments.get(0))
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SYMBOLIC_EXPRESSION)) {
				return OMCreator.createOMA(OMSymbol.TRANSC1_LOG, arguments);
			}
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double, variable");
		}
		return result;
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
