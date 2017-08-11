// Generated from EvaluatorParser.g4 by ANTLR 4.7
package de.uni_due.s3.evaluator2.parser.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EvaluatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EvaluatorParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code binaryCircumflex}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryCircumflex(EvaluatorParser.BinaryCircumflexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryRelational}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryRelational(EvaluatorParser.BinaryRelationalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextValue(EvaluatorParser.TextValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatValue(EvaluatorParser.FloatValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedFunctionInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedFunctionInExpression(EvaluatorParser.NestedFunctionInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(EvaluatorParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(EvaluatorParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetInExpression(EvaluatorParser.SetInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(EvaluatorParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exerciseVarName}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryArithPoint}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryArithPoint(EvaluatorParser.BinaryArithPointContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValue(EvaluatorParser.IntegerValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryArithLine}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryArithLine(EvaluatorParser.BinaryArithLineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fillInVarName}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFillInVarName(EvaluatorParser.FillInVarNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryBoolean}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryBoolean(EvaluatorParser.BinaryBooleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#unaryOperatorForExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperatorForExpression(EvaluatorParser.UnaryOperatorForExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#binaryOperatorBoolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorBoolean(EvaluatorParser.BinaryOperatorBooleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#binaryOperatorRelational}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorRelational(EvaluatorParser.BinaryOperatorRelationalContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#binaryOperatorArithLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorArithLine(EvaluatorParser.BinaryOperatorArithLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#binaryOperatorArithPoint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorArithPoint(EvaluatorParser.BinaryOperatorArithPointContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(EvaluatorParser.SetContext ctx);
}