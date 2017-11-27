package de.uni_due.s3.evaluator2.core.function.list_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.visitor.primitve.OMToStringVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Converts a list to a string representation suitable for fill-in fields in JACK.
 * 
 * Returns a string representation of the list
 * 
 * @author Michael Striewe
 *
 */
public class ToDropDownList extends Function {
	

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		List<Object> list = getListSyntax(arguments.get(0));

		if (list.size() == 0) {
			throw new FunctionInvalidArgumentException(this, "List has to have at least one element.");
		}

		StringBuilder builder = new StringBuilder();
		
		for (Object o : list) {
			if (builder.length() > 0) {
				builder.append(",");
			}
			builder.append(OMToStringVisitor.getInstance().visit(o));
		}
		
		return OMCreator.createOMSTR("{" + builder.toString() + "}");
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
