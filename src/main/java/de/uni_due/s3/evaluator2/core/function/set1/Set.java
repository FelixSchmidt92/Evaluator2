package de.uni_due.s3.evaluator2.core.function.set1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements a list. Values can occur more than ones in this list. In a normal
 * set values could only occur ones!. We have to implement it this way,
 * otherwise we wouldn't be compatible with evaluator1
 * 
 * @author spobel, frichtscheid
 *
 */
public class Set extends ConstructorFunction {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		//Wir erzeugen nur noch Listen, Sets sind im Evaluator nicht passend, 
		//wir wollen immer vollständige Eingaben haben, Sets würden doppelte Elemente löschen
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, arguments);
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
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		List<String> args = new ArrayList<String>();
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String set = "{";
		for (Object arg : arguments) {
			set += getSageSyntax(arg) + ", ";
		}
		set = set.substring(0, set.length() - 2); // Removing ", "
	
		return set + "}";
	}

	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String set = "{";
		for (Object arg : arguments) {
			set += getSageSyntax(arg) + ", ";
		}
		set = set.substring(0, set.length() - 2); // Removing ", "
	
		return set + "}";
	}

}
