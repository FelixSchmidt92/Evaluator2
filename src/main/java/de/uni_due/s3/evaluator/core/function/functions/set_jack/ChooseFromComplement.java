package de.uni_due.s3.evaluator.core.function.functions.set_jack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

public class ChooseFromComplement extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException, FunctionInvalidArgumentException {
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SET1_SET)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set, (1)Set");
		}

		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.SET1_SET)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set, (1)Set");
		}

		java.util.Set<Object> set = new HashSet<Object>();
		List<Object> list1 = ((OMA) arguments.get(0)).getOmel();
		list1.remove(0); // OMS entfernen
		set.addAll(list1);
		
		List<Object> list2 = ((OMA) arguments.get(1)).getOmel();
		list2.remove(0); // OMS entfernen
		set.removeAll(list2);
		
		if (set.size() == 0) {
			throw new FunctionInvalidArgumentException(this, "Set has to have at least one element.");
		}
		
		List<Object> resultList = new ArrayList<>();
		resultList.addAll(set);

		return resultList.get(new Random().nextInt(set.size()));
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
