package de.uni_due.s3.evaluator2.core.function.transc1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
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
	
	/**
	 * 
	 * @param arguments should be zero or a omi
	 * @return input_box || arg1 + input_box
	 * @throws FunctionNotImplementedException
	 */
	@Override
	public Object generatePalette(List<Object> arguments) throws FunctionNotImplementedException {
		List<Object> args = new ArrayList<Object>(arguments.size());
		switch(arguments.size()) {
			default:{
				args.add(OMSymbol.EDITOR1_INPUT_BOX);
				return OMCreator.createOMA(OMSymbol.TRANSC1_LOG, args);
			}
			case 1:{
				args.add(getPaletteSyntax(arguments.get(0)));
				args.add(OMSymbol.EDITOR1_INPUT_BOX);
				return OMCreator.createOMA(OMSymbol.TRANSC1_LOG, args);
			}
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

}
