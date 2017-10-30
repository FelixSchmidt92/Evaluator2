package de.uni_due.s3.evaluator2.core.function.set1;

import java.util.HashSet;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.omutils.OMCreator;

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
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		List<Object> list1 = getListSyntax(arguments.get(0));
		java.util.Set<Object> set1 = new HashSet<>(list1);
		if (set1.size()==1 && set1.contains(OMSymbol.EDITOR1_INPUT_BOX)) {
			return OMCreator.createOMIOMF(0);
		}
		return OMCreator.createOMIOMF(set1.size());
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
