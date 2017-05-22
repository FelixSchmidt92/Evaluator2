package de.uni_due.s3.evaluator.parser;

import java.util.LinkedList;
import java.util.List;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathApplication;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathFloat;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathInteger;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathObject;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathString;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParserBaseVisitor;

public class ExpressionToOpenMathVisitor extends EvaluatorParserBaseVisitor<OpenMathObject<?>> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OpenMathObject<?> visitParentheses(EvaluatorParser.ParenthesesContext ctx) {
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
	public OpenMathObject<?> visitUnaryOperator(EvaluatorParser.UnaryOperatorContext ctx) {
		List<OpenMathObject<?>> child = new LinkedList<>();
		child.add(visit(ctx.expression()));
		if (ctx.unaryoperator().getText().equals("-")){
			return new OpenMathApplication("arith1", "unary_minus", child);
		}
		return new OpenMathApplication("arith1", "unary_plus", child);
		// TODO direct evaluation of unary Operators?
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
	public OpenMathObject<?> visitTextValue(EvaluatorParser.TextValueContext ctx) {
		return new OpenMathString(ctx.getText().substring(1, ctx.getText().length()-1));
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
	public OpenMathObject<?> visitBinaryOperator(EvaluatorParser.BinaryOperatorContext ctx) {
		List<OpenMathObject<?>> childs = new LinkedList<>();
		childs.add(visit(ctx.getChild(0))); // Left side
		childs.add(visit(ctx.getChild(2))); // Right side
		
		switch (ctx.operator.getText()) {
		case "*":
			return new OpenMathApplication("arith1", "times", childs);
		case "/":
			return new OpenMathApplication("arith1", "divide", childs);
		case "%":
			return new OpenMathApplication("NoRepresentation", "Modulus", childs); //TODO No Representation
		case "+":
			return new OpenMathApplication("arith1", "plus", childs);
		case "-":
			return new OpenMathApplication("arith1", "minus", childs);
		case "<":
			return new OpenMathApplication("relation1", "lt", childs);
		case "<=":
			return new OpenMathApplication("relation1", "leq", childs);
		case ">":
			return new OpenMathApplication("relation1", "gt", childs);
		case ">=":
			return new OpenMathApplication("relation1", "geq", childs);
		case "==":
			return new OpenMathApplication("relation1", "eq", childs);
		case "!=":
			return new OpenMathApplication("relation1", "neq", childs);
		case "&&":
			return new OpenMathApplication("logic1", "and", childs);
		case "||":
			return new OpenMathApplication("logic1", "or", childs);
		default:
			throw new EvaluatorException("BinaryOperator not supported");
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
	public OpenMathObject<?> visitFunction(EvaluatorParser.FunctionContext ctx) {
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
	public OpenMathObject<?> visitFloatValue(EvaluatorParser.FloatValueContext ctx) {
		String value = ctx.getText();
		if (value.startsWith("'")){
			value = value.substring(1, value.length()-1);
		}
		return new OpenMathFloat(Double.valueOf(value));
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
	public OpenMathObject<?> visitExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx) {
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
	public OpenMathObject<?> visitIntegerValue(EvaluatorParser.IntegerValueContext ctx) {
		String value = ctx.value.getText();
		if (value.startsWith("'") && value.endsWith("'")) {
			value = value.substring(1, value.length() - 1);
		}
		return new OpenMathInteger(Integer.valueOf(value));
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
	public OpenMathObject<?> visitFillInVarName(EvaluatorParser.FillInVarNameContext ctx) {
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
	public OpenMathObject<?> visitUnaryoperator(EvaluatorParser.UnaryoperatorContext ctx) {
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
	public OpenMathObject<?> visitBinaryoperator(EvaluatorParser.BinaryoperatorContext ctx) {
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
	public OpenMathObject<?> visitNestedFunction(EvaluatorParser.NestedFunctionContext ctx) {
		List<OpenMathObject<?>> childs = new LinkedList<>();
		for (ExpressionContext child : ctx.arguments){
			childs.add(visit(child));
		}
		return new OpenMathApplication("evaluator2", ctx.name.getText(), childs);
	}
}