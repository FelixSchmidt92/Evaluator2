package de.uni_due.s3.evaluator.parser;

import de.uni_due.s3.evaluator.openmath.OpenMathInteger;
import de.uni_due.s3.evaluator.openmath.OpenMathObject;
import de.uni_due.s3.evaluator.openmath.OpenMathObjectCreator;
import de.uni_due.s3.evaluator.openmath.OpenMathSymbols;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParserBaseVisitor;
import de.uni_due.s3.evaluator.unaryOperator.UnaryOperator;

public class ExpressionToOpenMathVisitor extends EvaluatorParserBaseVisitor<OpenMathObject> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitParentheses(EvaluatorParser.ParenthesesContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitUnaryOperator(EvaluatorParser.UnaryOperatorContext ctx) {
		OpenMathObject child = visit(ctx.expression());
		return UnaryOperator.evaluate(ctx, child);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitTextValue(EvaluatorParser.TextValueContext ctx) {
		return OpenMathObjectCreator.createOpenMathString(ctx.value.getText());
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitBinaryOperator(EvaluatorParser.BinaryOperatorContext ctx) {
		switch (ctx.operator.getText()) {
		case "*":
			return null;
		case "/":
			return null;
		case "%":
			return null;
		case "+":
			return null;
		case "-":
			return null;
		case "<":
			return null;
		case "<=":
			return null;
		case ">":
			return null;
		case ">=":
			return null;
		case "==":
			return null;
		case "!=":
			return null;
		case "&&":
			return null;
		case "||":
			return null;
		default:
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitFunction(EvaluatorParser.FunctionContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitFloatValue(EvaluatorParser.FloatValueContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitIntegerValue(EvaluatorParser.IntegerValueContext ctx) {
		String value = ctx.value.getText();
		if (value.startsWith("'") && value.endsWith("'")) {
			value = value.substring(1, value.length() - 1);
		}
		return OpenMathObjectCreator.createOpenMathInteger(Integer.parseInt(value));
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitFillInVarName(EvaluatorParser.FillInVarNameContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitUnaryoperator(EvaluatorParser.UnaryoperatorContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitBinaryoperator(EvaluatorParser.BinaryoperatorContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject visitNestedFunction(EvaluatorParser.NestedFunctionContext ctx) {
		return visitChildren(ctx);
	}
}