package de.uni_due.s3.evaluator.core.function.functions.set_jack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

public class GetFromOrderedSet extends Function {

	@Override
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentTypeException, FunctionInvalidArgumentException {

		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SET1_SET)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set, (1)Integer");
		}

		int pos = 0;
		try {
			pos = NumberUtils.convertOMIToInteger(arguments.get(1));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set, (1)Integer");
		}

		List<Object> set = ((OMA) arguments.get(0)).getOmel();
		set.remove(0); // OMS entfernen

		if (set.size() == 0) {
			throw new FunctionInvalidArgumentException(this, "Set has to have at least one element.");
		}

		if (set.size() <= pos) {
			throw new FunctionInvalidArgumentException(this,
					"Second Argument of getFromOrderedSet is invalid. Not in Range of Set.");
		}

		if (OMTypeChecker.isOMFOrOMI(set.get(0))) {
			List<Double> setForSort = new ArrayList<Double>();
			for (Object element : set) {
				try {
					setForSort.add(NumberUtils.convertOMIOMFToDouble(element));
				} catch (InputMismatchException e) {
					throw new FunctionInvalidArgumentTypeException(this,
							"Set elements have to be from either type String or Integer/Double/Float.");
				}
			}
			Collections.sort(setForSort);
			return NumberUtils.convertDoubleToOMIOMF(setForSort.get(pos));
			
		} else if (OMTypeChecker.isOMSTR(set.get(0))) {
			List<String> setForSort = new ArrayList<String>();
			for (Object element : set) {
				try {
					setForSort.add(NumberUtils.convertOMSTRToSring(element));
				} catch (InputMismatchException e) {
					throw new FunctionInvalidArgumentTypeException(this,
							"Set elements have to be from either type String or Integer/Double/Float.");
				}
			}
			Collections.sort(setForSort);
			return OMCreator.createOMSTR(setForSort.get(pos));
			
		} else {
			throw new FunctionInvalidArgumentTypeException(this,
					"Set elements have to be from either type String or Integer/Double/Float.");
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
