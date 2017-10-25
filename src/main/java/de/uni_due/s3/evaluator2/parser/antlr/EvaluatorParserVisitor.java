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
	 * Visit a parse tree produced by the {@code parenthesisInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisInExpression(EvaluatorParser.ParenthesisInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOperatorArithLineInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorArithLineInExpression(EvaluatorParser.BinaryOperatorArithLineInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOperatorRelationalInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorRelationalInExpression(EvaluatorParser.BinaryOperatorRelationalInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatValueInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatValueInExpression(EvaluatorParser.FloatValueInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOperatorInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperatorInExpression(EvaluatorParser.UnaryOperatorInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerValueInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValueInExpression(EvaluatorParser.IntegerValueInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textValueInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextValueInExpression(EvaluatorParser.TextValueInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInExpression(EvaluatorParser.VariableInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantInExpression(EvaluatorParser.ConstantInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOperatorArithPointInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorArithPointInExpression(EvaluatorParser.BinaryOperatorArithPointInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOperatorBooleanInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorBooleanInExpression(EvaluatorParser.BinaryOperatorBooleanInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionInExpression(EvaluatorParser.FunctionInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code listInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListInExpression(EvaluatorParser.ListInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exerciseVarNameInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExerciseVarNameInExpression(EvaluatorParser.ExerciseVarNameInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fillInVarNameInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFillInVarNameInExpression(EvaluatorParser.FillInVarNameInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOperatorCircumflexInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorCircumflexInExpression(EvaluatorParser.BinaryOperatorCircumflexInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(EvaluatorParser.UnaryOperatorContext ctx);
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
	 * Visit a parse tree produced by {@link EvaluatorParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(EvaluatorParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(EvaluatorParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(EvaluatorParser.ListContext ctx);
}