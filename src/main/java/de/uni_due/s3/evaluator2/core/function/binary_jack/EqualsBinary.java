package de.uni_due.s3.evaluator2.core.function.binary_jack;

import java.util.List;
import java.util.regex.Pattern;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This function checks if the 2 given Binary Strings are equal to each other It
 * is insensitive to leading Zeros. The String can also contain a leading '-'.
 * 
 * @author dlux
 *
 */
public class EqualsBinary extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		try {
			String left = getStringSyntax(arguments.get(0)); // Normal Number
			String right = getStringSyntax(arguments.get(1)); // Binary Number

			// If BOOl-Input then change these to 1 or 0
			if (left.equals("True")) {
				left = "1";
			} else if (left.equals("False")) {
				left = "0";
			}
			if (right.equals("True")) {
				right = "1";
			} else if (right.equals("False")) {
				right = "0";
			}
			String leftBin = "";
			if (left.contains(".") || left.contains(",")) {
				leftBin = Long.toBinaryString(Double.doubleToRawLongBits(Double.parseDouble(left)));
			} else {
				// special Case if Integer is Negative, do not get the 2-Complement-Rep of
				// Integer.
				int t = Integer.parseInt(left);
				if (t < 0) {
					leftBin = "-" + Integer.toBinaryString(-1 * t);
				} else {
					leftBin = Integer.toBinaryString(t);
				}
			}
			if (!Pattern.matches("^-?[01]+", leftBin) || !Pattern.matches("^-?[01]+", right))
				throw new InputMismatchException();
			// replace leading 0's
			leftBin = leftBin.replaceFirst("^0+(?!$)", "");
			right = right.replaceFirst("^0+(?!$)", "");

			// replace leading -0's with -
			leftBin = leftBin.replaceFirst("^-0+(?!$)", "-");
			right = right.replaceFirst("^-0+(?!$)", "-");

			long l = Long.parseLong(leftBin, 2);
			long r = Long.parseLong(right, 2);

			if (l == r) {
				return OMSymbol.LOGIC1_TRUE;
			}

		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Number, (1)Binary");
		} catch (NumberFormatException e) {
			throw new FunctionInvalidArgumentException(this,
					"Either the Binary is to long or the Number has a wrong Format.");
		}

		return OMSymbol.LOGIC1_FALSE;
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
