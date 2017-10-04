package de.uni_due.s3.evaluator2.core.function.list_jack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;

/**
 * Implements a get Function for List. First List will be ordered.
 * 
 * @author spobel
 *
 */
public class ChooseFromComplement extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		Set<Object> set = new HashSet<Object>();

		List<Object> list1 = getListSyntax(arguments.get(0));
		List<Object> list2 = getListSyntax(arguments.get(1));
		set.addAll(list1);
		set.removeAll(list2);
		
		if (set.size() == 0) {
			throw new FunctionInvalidArgumentException(this, "List has to have at least one element.");
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
