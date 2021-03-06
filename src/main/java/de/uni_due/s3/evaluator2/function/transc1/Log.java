package de.uni_due.s3.evaluator2.function.transc1;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the natural logarithm (base e) of a double value.
 * 
 * 
 * @author spobel
 *
 */
public class Log extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Double arg1 = getDoubleSyntax(arguments.get(0));
		if (arg1 <= 0.0) {
			throw new FunctionInvalidArgumentException(this, "Function Log expects values greater than zero.");
		}
		arg1 = Math.log(arg1);
		return OMCreator.createOMIOMF(arg1);
	}
	
	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.TRANSC1_LOG, arguments);
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
