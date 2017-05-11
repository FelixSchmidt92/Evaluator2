package de.uni_due.s3.evaluator.unaryOperator;

import de.uni_due.s3.evaluator.openmath.OpenMathObject;
import de.uni_due.s3.evaluator.openmath.OpenMathObjectCreator;
import de.uni_due.s3.evaluator.openmath.OpenMathToJavaConverter;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;

public class UnaryOperator {

	public static OpenMathObject evaluate(EvaluatorParser.UnaryOperatorContext ctx, OpenMathObject child) {
		switch (ctx.operator.getText()) {
		case "+":
			return evaluateUnaryPlus(child);
		case "-":
			return evaluateUnaryMinus(child);
		case "!":
			return evaluateUnaryNot(child);
		default:
			throw new IllegalArgumentException();//FIXME
		}
	}

	private static OpenMathObject evaluateUnaryNot(OpenMathObject child) {
//		if (child.isBoolean()) {
//			
//		} else {
			throw new IllegalArgumentException();//FIXME
//		}
	}

	private static OpenMathObject evaluateUnaryMinus(OpenMathObject child) {
		if (child.isFloat()) {
			return OpenMathObjectCreator.createOpenMathFloat(-1.0 * OpenMathToJavaConverter.omobjToFloat(child));
		} else if (child.isInteger()) {
			return OpenMathObjectCreator.createOpenMathInteger(-1 * OpenMathToJavaConverter.omobjToInteger(child));
		} else {
			throw new IllegalArgumentException();//FIXME
		}
	}

	private static OpenMathObject evaluateUnaryPlus(OpenMathObject child) {
		return child;
	}

}
