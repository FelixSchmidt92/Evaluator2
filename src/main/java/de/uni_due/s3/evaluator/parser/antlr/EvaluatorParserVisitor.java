package de.uni_due.s3.evaluator.parser.antlr;

// Generated from EvaluatorParser.g4 by ANTLR 4.7
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
	 * Visit a parse tree produced by the {@code textValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextValue(EvaluatorParser.TextValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetInExpression(EvaluatorParser.SetInExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary(EvaluatorParser.BinaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatValue(EvaluatorParser.FloatValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exerciseVarName}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerValue(EvaluatorParser.IntegerValueContext ctx);
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
	 * Visit a parse tree produced by the {@code fillInVarName}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFillInVarName(EvaluatorParser.FillInVarNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(EvaluatorParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#unaryOperatorForExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperatorForExpression(EvaluatorParser.UnaryOperatorForExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#binaryOperatorForExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperatorForExpression(EvaluatorParser.BinaryOperatorForExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(EvaluatorParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#nestedFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedFunction(EvaluatorParser.NestedFunctionContext ctx);
}