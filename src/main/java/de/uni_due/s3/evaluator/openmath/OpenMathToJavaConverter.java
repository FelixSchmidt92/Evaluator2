package de.uni_due.s3.evaluator.openmath;

public class OpenMathToJavaConverter {

	public static int omobjToInteger(OpenMathObject omobj) {
		if (omobj instanceof OpenMathInteger) {
			return ((OpenMathInteger) omobj).getValue();
		} else if (omobj instanceof OpenMathApplication && ((OpenMathApplication) omobj).getArguments().size() == 1) {
			// Applikation kann ein UnarySymbol haben, dann muss das argument der Integer sein!
			OpenMathApplication oma = (OpenMathApplication) omobj;
			OpenMathObject arg = oma.getArguments().get(0);
			if (oma.getHead() == OpenMathSymbols.ARITH1_UNARY_MINUS) {
				return -1 * omobjToInteger(arg);
			} else if (oma.getHead() == OpenMathSymbols.ARITH1_UNARY_PLUS) {
				return omobjToInteger(arg);
			} else {
				throw new OpenMathConvertException("No valid integer representation!");
			}
		} else {
			throw new OpenMathConvertException("OpenMathObject has no integer representation.");
		}
	}

	public static double omobjToFloat(OpenMathObject omobj) {
		if (omobj instanceof OpenMathFloat) {
			return ((OpenMathFloat) omobj).getValue();
		} else if (omobj instanceof OpenMathApplication && ((OpenMathApplication) omobj).getArguments().size() == 1) {
			// Applikation kann ein UnarySymbol haben, dann muss das argument der Float sein!
			OpenMathApplication oma = (OpenMathApplication) omobj;
			OpenMathObject arg = oma.getArguments().get(0);
			if (oma.getHead() == OpenMathSymbols.ARITH1_UNARY_MINUS) {
				return -1.0 * omobjToFloat(arg);
			} else if (oma.getHead() == OpenMathSymbols.ARITH1_UNARY_PLUS) {
				return omobjToFloat(arg);
			} else {
				throw new OpenMathConvertException("No valid float representation!");
			}
		} else {
			throw new OpenMathConvertException("OpenMathObject has no float representation.");
		}
	}
}
