package de.uni_due.s3.evaluator.parser.antlr;

// Generated from EvaluatorParser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EvaluatorParser}.
 */
public interface EvaluatorParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code textValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTextValue(EvaluatorParser.TextValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTextValue(EvaluatorParser.TextValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSetInExpression(EvaluatorParser.SetInExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSetInExpression(EvaluatorParser.SetInExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binary}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinary(EvaluatorParser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binary}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinary(EvaluatorParser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFloatValue(EvaluatorParser.FloatValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFloatValue(EvaluatorParser.FloatValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exerciseVarName}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exerciseVarName}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerValue(EvaluatorParser.IntegerValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerValue}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerValue(EvaluatorParser.IntegerValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedFunctionInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNestedFunctionInExpression(EvaluatorParser.NestedFunctionInExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedFunctionInExpression}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNestedFunctionInExpression(EvaluatorParser.NestedFunctionInExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unary}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnary(EvaluatorParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unary}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnary(EvaluatorParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fillInVarName}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFillInVarName(EvaluatorParser.FillInVarNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fillInVarName}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFillInVarName(EvaluatorParser.FillInVarNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(EvaluatorParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(EvaluatorParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link EvaluatorParser#unaryOperatorForExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperatorForExpression(EvaluatorParser.UnaryOperatorForExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EvaluatorParser#unaryOperatorForExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperatorForExpression(EvaluatorParser.UnaryOperatorForExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EvaluatorParser#binaryOperatorForExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperatorForExpression(EvaluatorParser.BinaryOperatorForExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EvaluatorParser#binaryOperatorForExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperatorForExpression(EvaluatorParser.BinaryOperatorForExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EvaluatorParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(EvaluatorParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link EvaluatorParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(EvaluatorParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link EvaluatorParser#nestedFunction}.
	 * @param ctx the parse tree
	 */
	void enterNestedFunction(EvaluatorParser.NestedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EvaluatorParser#nestedFunction}.
	 * @param ctx the parse tree
	 */
	void exitNestedFunction(EvaluatorParser.NestedFunctionContext ctx);
}