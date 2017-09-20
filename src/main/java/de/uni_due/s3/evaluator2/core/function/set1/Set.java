package de.uni_due.s3.evaluator2.core.function.set1;

import java.util.LinkedList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Implements a list. Values can occur more than ones in this list. In a normal
 * set values could only occur ones!. We have to implement it this way,
 * otherwise we wouldn't be compatible with evaluator1
 * 
 * @author frichtscheid
 *
 */
public class Set extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		return OMCreator.createOMA(OMSymbol.SET1_SET, arguments);
	}

	@Override
	protected int minArgs() {
		return 0;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		List<String> args = new LinkedList<String>();
		for (Object arg : arguments) {
			args.add(getLatexSyntax(arg));
		}
		return "\\left\\{" + String.join(",", args) + "\\right\\}";
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		return omel;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		String set = "{";
		for (Object arg : arguments) {
			set += getSageSyntax(arg) + ", ";
		}
		set = set.substring(0, set.length() - 2); // Removing ", "
	
		return set + "}";
	}

}
