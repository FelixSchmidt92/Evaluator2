package de.uni_due.s3.evaluator2.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements compareToIgnoreCase operation for strings.
 * 
 * Compares two strings lexicographically, ignoring case differences. This
 * method returns an integer whose sign is that of calling compareTo with
 * normalized versions of the strings where case differences have been
 * eliminated by calling Character.toLowerCase(Character.toUpperCase(character))
 * on each character.
 * 
 * Note that this method does not take locale into account, and will result in
 * an unsatisfactory ordering for certain locales. The java.text package
 * provides collators to allow locale-sensitive ordering.
 * 
 * @author spobel
 *
 */
public class CompareToIgnoreCase extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
			String string1 = getStringSyntax(arguments.get(0));
			String string2 = getStringSyntax(arguments.get(1));
			return OMCreator.createOMI(string1.compareToIgnoreCase(string2));
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
