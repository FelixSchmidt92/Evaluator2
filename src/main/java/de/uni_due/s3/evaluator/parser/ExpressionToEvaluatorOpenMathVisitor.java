package de.uni_due.s3.evaluator.parser;

import de.uni_due.s3.evaluator.core.function.FunctionFactory;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParserBaseVisitor;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMS;
import de.uni_due.s3.openmath.OMSTR;

public class ExpressionToEvaluatorOpenMathVisitor extends EvaluatorParserBaseVisitor<Object> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Object visitParentheses(EvaluatorParser.ParenthesesContext ctx) {
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
	public Object visitUnaryOperator(EvaluatorParser.UnaryOperatorContext ctx) {
		OMS oms = new OMS();

		switch (ctx.operator.getText()){
		case "+":
			oms.setCd("arith1");
			oms.setName("unary_plus");
			break;

		case "-":
			oms.setCd("arith1");
			oms.setName("unary_minus");
			break;

		case "!":
			oms.setCd("logic1");
			oms.setName("not");
			break;
		}
		OMA oma = new OMA();
		oma.getOmel().add(oms);
		oma.getOmel().add(visitChildren(ctx));
		return oma;
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
	public Object visitTextValue(EvaluatorParser.TextValueContext ctx) {
		OMSTR omstr = new OMSTR();
		omstr.setContent(ctx.getText().substring(1, ctx.getText().length()-1)); //delete ' at beginning and end
		return omstr;
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
	public Object visitBinaryOperator(EvaluatorParser.BinaryOperatorContext ctx) {
		OMS oms = new OMS();
		
		switch (ctx.operator.getText()){
		case "+":
			oms.setCd("arith1");
			oms.setName("plus");
			break;

		case "-":
			oms.setCd("arith1");
			oms.setName("minus");
			break;

		case "*":
			oms.setCd("arith1");
			oms.setName("times");
			break;

		case "/":
			oms.setCd("arith1");
			oms.setName("divide");
			break;

//		case "%":   //TODO which CD and Name??
//			oms.setCd("arith1");
//			oms.setName("unary_plus");
//			break;

		case "<":
			oms.setCd("relation1");
			oms.setName("lt");
			break;

		case "<=":
			oms.setCd("relation1");
			oms.setName("leq");
			break;

		case ">":
			oms.setCd("relation1");
			oms.setName("gt");
			break;

		case ">=":
			oms.setCd("relation1");
			oms.setName("geq");
			break;

		case "=":
			oms.setCd("relation1");
			oms.setName("eq");
			break;

		case "==":
			oms.setCd("arith1");		//TODO Correct CD and Name?
			oms.setName("eq");
			break;

		case "!=":
			oms.setCd("relation1");
			oms.setName("neq");
			break;

		case "&&":
			oms.setCd("logic1");
			oms.setName("and");
			break;

		case "||":
			oms.setCd("logic1");
			oms.setName("or");
			break;
		}
		
		OMA oma = new OMA();
		oma.getOmel().add(oms);		//add OMS and children
		oma.getOmel().add(visit(ctx.getChild(0))); //left side
		oma.getOmel().add(visit(ctx.getChild(2))); // right side
		
		return oma;
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
	public Object visitFunction(EvaluatorParser.FunctionContext ctx) {
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
	public Object visitFloatValue(EvaluatorParser.FloatValueContext ctx) {
		OMF omf = new OMF();
		if (ctx.getText().startsWith("'")){  //delete ' at beginning and end if exists
			omf.setDec(Double.parseDouble(ctx.getText().substring(1, ctx.getText().length()-1))); 
		}else{
			omf.setDec(Double.parseDouble(ctx.getText()));
		}
		return omf;
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
	public Object visitExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx) {
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
	public Object visitIntegerValue(EvaluatorParser.IntegerValueContext ctx) {
		OMI omi = new OMI();
		if (ctx.getText().startsWith("'")){  //delete ' at beginning and end if exists
			omi.setValue(ctx.getText().substring(1, ctx.getText().length()-1)); 
		}else{
			omi.setValue(ctx.getText());
		}
		return omi;
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
	public Object visitFillInVarName(EvaluatorParser.FillInVarNameContext ctx) {
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
	public Object visitUnaryoperator(EvaluatorParser.UnaryoperatorContext ctx) {
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
	public Object visitBinaryoperator(EvaluatorParser.BinaryoperatorContext ctx) {
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
	public Object visitNestedFunction(EvaluatorParser.NestedFunctionContext ctx) {
		OMS oms = new OMS();
		oms.setCd(FunctionFactory.getInstance().getFunctionContendDictionary(ctx.name.getText()));
		oms.setName(FunctionFactory.getInstance().getFunctionName(ctx.name.getText()));
		
		OMA oma = new OMA();
		oma.getOmel().add(oms);
		
		for (ExpressionContext childctx : ctx.arguments){
			oma.getOmel().add(visit(childctx));
		}
		return oma;
	}
}