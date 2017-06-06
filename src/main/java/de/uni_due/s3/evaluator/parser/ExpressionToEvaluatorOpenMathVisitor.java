package de.uni_due.s3.evaluator.parser;

import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParserBaseVisitor;
import de.uni_due.s3.evaluator.tree.EFloat;
import de.uni_due.s3.evaluator.tree.EInteger;
import de.uni_due.s3.evaluator.tree.EObject;
import de.uni_due.s3.evaluator.tree.EString;

public class ExpressionToEvaluatorOpenMathVisitor extends EvaluatorParserBaseVisitor<EObject> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public EObject visitParentheses(EvaluatorParser.ParenthesesContext ctx) {
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
	public EObject visitUnaryOperator(EvaluatorParser.UnaryOperatorContext ctx) {
		return null;
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
	public EObject visitTextValue(EvaluatorParser.TextValueContext ctx) {
		return new EString(ctx.value.getText());
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
	public EObject visitBinaryOperator(EvaluatorParser.BinaryOperatorContext ctx) {
		return null;
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
	public EObject visitFunction(EvaluatorParser.FunctionContext ctx) {
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
	public EObject visitFloatValue(EvaluatorParser.FloatValueContext ctx) {
		return new EFloat(Double.parseDouble(ctx.value.getText()));
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
	public EObject visitExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx) {
		return null; //TODO get the OpenMath of this variable!
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
	public EObject visitIntegerValue(EvaluatorParser.IntegerValueContext ctx) {
		return new EInteger(Integer.parseInt(ctx.value.getText()));
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
	public EObject visitFillInVarName(EvaluatorParser.FillInVarNameContext ctx) {
		return null; //TODO get the OpenMath of this variable!
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
	public EObject visitUnaryoperator(EvaluatorParser.UnaryoperatorContext ctx) {
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
	public EObject visitBinaryoperator(EvaluatorParser.BinaryoperatorContext ctx) {
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
	public EObject visitNestedFunction(EvaluatorParser.NestedFunctionContext ctx) {
		return null;
	}
}