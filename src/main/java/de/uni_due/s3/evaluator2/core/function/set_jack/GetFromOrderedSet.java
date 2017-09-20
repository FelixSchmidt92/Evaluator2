package de.uni_due.s3.evaluator2.core.function.set_jack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Implements a get Function for Set (List).
 * 
 * @author spobel
 *
 */
public class GetFromOrderedSet extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		int pos = getIntegerSyntax(arguments.get(0));

		List<Object> list1 = getListSyntax(arguments.get(1));

		if (list1.size() == 0) {
			throw new FunctionInvalidArgumentException(this, "Set has to have at least one element.");
		}

		if (list1.size() <= pos) {
			throw new FunctionInvalidArgumentException(this,
					"Second Argument of getFromOrderedSet is invalid. Not in Range of Set.");
		}

		if (OMTypeChecker.isOMNumber(list1.get(0))) {
			List<Double> setForSort = new ArrayList<Double>();
			for (Object element : list1) {
				try {
					setForSort.add(getDoubleSyntax(element));
				} catch (NoRepresentationAvailableException e) {
					throw new FunctionInvalidArgumentTypeException(this,
							"Set elements have to be from type String/Integer/Double/Float.");
				}
			}
			Collections.sort(setForSort);
			return OMCreator.createOMIOMF(setForSort.get(pos));

		} else {
			List<String> setForSort = new ArrayList<String>();
			for (Object element : list1) {
				try {
					setForSort.add(getStringSyntax(element));
				} catch (NoRepresentationAvailableException e) {
					throw new FunctionInvalidArgumentTypeException(this,
							"Set elements have to be from type String/Integer/Double/Float.");
				}
			}
			Collections.sort(setForSort);
			return OMCreator.createOMSTR(setForSort.get(pos));
		}
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

}
