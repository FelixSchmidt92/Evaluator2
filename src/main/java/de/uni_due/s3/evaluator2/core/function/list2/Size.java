package de.uni_due.s3.evaluator2.core.function.list2;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openmath set1 size operation Example: size({1,2}) = 2
 * 
 * @author frichtscheid
 *
 */
public class Size extends Function {

	/**
	 * Expects one argument of type OMA with set-OMS
	 * 
	 * @return size of the set
	 * @throws EvaluatorException 
	 * @throws FunctionInvalidArgumentTypeException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		List<Object> list1 = getListSyntax(arguments.get(0));
		if (list1.size()==1 && list1.get(0).equals(OMSymbol.EDITOR1_INPUT_BOX)) {
			return OMCreator.createOMIOMF(0);
		}
		return OMCreator.createOMI(list1.size());
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
