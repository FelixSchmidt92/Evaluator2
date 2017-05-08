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
	 * Visit a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(EvaluatorParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOperator}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(EvaluatorParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerNum}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerNum(EvaluatorParser.IntegerNumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatNum}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatNum(EvaluatorParser.FloatNumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fillInVariable}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFillInVariable(EvaluatorParser.FillInVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOperator}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperator(EvaluatorParser.BinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code function}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(EvaluatorParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exerciseVariable}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExerciseVariable(EvaluatorParser.ExerciseVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code text}
	 * labeled alternative in {@link EvaluatorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(EvaluatorParser.TextContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#integerNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerNumber(EvaluatorParser.IntegerNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#floatNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatNumber(EvaluatorParser.FloatNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#exerciseVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExerciseVar(EvaluatorParser.ExerciseVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#fillInVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFillInVar(EvaluatorParser.FillInVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#unaryoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryoperator(EvaluatorParser.UnaryoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#binaryoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryoperator(EvaluatorParser.BinaryoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EvaluatorParser#nestedFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedFunction(EvaluatorParser.NestedFunctionContext ctx);
}