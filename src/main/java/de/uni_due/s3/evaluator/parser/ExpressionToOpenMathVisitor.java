package de.uni_due.s3.evaluator.parser;

import java.util.HashMap;

import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.UndefinedExerciseVariableRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.UndefinedFillInVariableRuntimeException;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.BinaryContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.BinaryOperatorForExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ExerciseVarNameContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.FillInVarNameContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.FloatValueContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.IntegerValueContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.NestedFunctionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.NestedFunctionInExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ParenthesisContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.SetContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.SetInExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.TextValueContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.UnaryContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.UnaryOperatorForExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParserBaseVisitor;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class ExpressionToOpenMathVisitor extends EvaluatorParserBaseVisitor<Object> {

	/**
	 * These variables are defined by the evaluator-class and are used to
	 * integrate the variables into the OM-tree
	 */
	private HashMap<String, OMOBJ> exerciseVariableMap;
	private HashMap<Integer, OMOBJ> fillInVariableMap;

	public ExpressionToOpenMathVisitor(HashMap<String, OMOBJ> exerciseVariableMap, HashMap<Integer, OMOBJ> fillInVariableMap) {
		this.exerciseVariableMap = exerciseVariableMap;
		this.fillInVariableMap = fillInVariableMap;
	}

	@Override
	public OMSTR visitTextValue(TextValueContext ctx) {
		OMSTR omstr = new OMSTR();
		// FIXME hier eine liste anstatt von OMSTR
		omstr.setContent(ctx.getText().substring(1, ctx.getText().length() - 1)); // delete
																					// '
																					// at
																					// beginning
																					// and
																					// end
		return omstr;
	}

	@Override
	public Object visitSetInExpression(SetInExpressionContext ctx) {
		// @Note: Do Not change Implementation of this Method
		return visitChildren(ctx);
	}

	@Override
	public OMA visitBinary(BinaryContext ctx) {
		OMS oms;
		switch (ctx.getChild(1).getText()) {
		case "+":
			oms = OMSymbol.ARITH1_PLUS;
			break;
		case "-":
			oms = OMSymbol.ARITH1_MINUS;
			break;
		case "*":
			oms = OMSymbol.ARITH1_TIMES;
			break;
		case "/":
			oms = OMSymbol.ARITH1_DIVIDE;
			break;
		case "%":
			oms = OMSymbol.INTEGER1_REMAINDER;
			break;
		case "<":
			oms = OMSymbol.RELATION1_LT;
			break;
		case "<=":
			oms = OMSymbol.RELATION1_LEQ;
			break;
		case ">":
			oms = OMSymbol.RELATION1_GT;
			break;
		case ">=":
			oms = OMSymbol.RELATION1_GEQ;
			break;
		case "=":
			oms = OMSymbol.RELATION1_EQ;
			break;
		case "==":
			oms = OMSymbol.RELATION1_EQ;
			break;
		case "!=":
			oms = OMSymbol.RELATION1_NEQ;
			break;
		case "&&":
			oms = OMSymbol.LOGIC1_AND;
			break;
		case "||":
			oms = OMSymbol.LOGIC1_OR;
			break;
		default:
			throw new FunctionNotImplementedRuntimeException("Binary Operator " + ctx.getChild(1) + " is not supported");
		}

		OMA oma = new OMA();
		oma.getOmel().add(oms); // add OMS and children
		oma.getOmel().add(visit(ctx.getChild(0))); // left side
		oma.getOmel().add(visit(ctx.getChild(2))); // right side

		return oma;
	}

	@Override
	public OMF visitFloatValue(FloatValueContext ctx) {
		OMF omf = new OMF();
		if (ctx.getText().startsWith("'")) { // delete ' at beginning and end if
												// exists
			omf.setDec(Double.parseDouble(ctx.getText().substring(1, ctx.getText().length() - 1)));
		} else {
			omf.setDec(Double.parseDouble(ctx.getText()));
		}
		return omf;
	}

	@Override
	public Object visitExerciseVarName(ExerciseVarNameContext ctx) {
		String var = ctx.getText(); // eg. [var=a]
		String varName = var.substring(var.indexOf('=') + 1, var.indexOf(']')); // eg.
																				// a
		if (exerciseVariableMap.containsKey(varName)) {
			OMOBJ varOmobj = exerciseVariableMap.get(varName);
			try {
				return OMConverter.toElement(varOmobj); // removes the
														// OMOBJ-tags
			} catch (OpenMathException e) {
				throw new ParserRuntimeException("Unable to convert OMOBJ ot Element:" + varOmobj.toString(), e);
			}
			// and returns the child of
			// the OMOBJ-Object
		} else {
			throw new UndefinedExerciseVariableRuntimeException(varName);
		}
	}

	@Override
	public OMI visitIntegerValue(IntegerValueContext ctx) {
		OMI omi = new OMI();
		if (ctx.getText().startsWith("'")) { // delete ' at beginning and end if
												// exists
			omi.setValue(ctx.getText().substring(1, ctx.getText().length() - 1));
		} else {
			omi.setValue(ctx.getText());
		}
		return omi;
	}

	@Override
	public Object visitNestedFunctionInExpression(NestedFunctionInExpressionContext ctx) {
		// @Note: Do Not change Implementation of this Method
		return visitChildren(ctx);
	}

	@Override
	public Object visitUnary(UnaryContext ctx) {
		OMS oms = new OMS();

		switch (ctx.getChild(0).getText()) {
		case "+":
			return visit(ctx.getChild(1));
		case "-":
			oms = OMSymbol.ARITH1_UNARY_MINUS;
			break;
		case "!":
			oms = OMSymbol.LOGIC1_NOT;
			break;
		default: 
			throw new FunctionNotImplementedRuntimeException("Unary Operator " + ctx.getChild(0) + " is not supported");
		}
		
		OMA oma = new OMA();
		oma.getOmel().add(oms);
		oma.getOmel().add(visit(ctx.getChild(1)));
		return oma;
	}

	@Override
	public Object visitFillInVarName(FillInVarNameContext ctx) {
		String var = ctx.getText(); // eg. [pos=1]
		int varNumber = Integer.parseInt(var.substring(var.indexOf('=') + 1, var.indexOf(']'))); // eg.
																									// 1
		if (fillInVariableMap.containsKey(varNumber)) {
			OMOBJ varOmobj = fillInVariableMap.get(varNumber);
			try {
				return OMConverter.toElement(varOmobj); // removes the
														// OMOBJ-tags
														// and returns the child
														// of
														// the OMOBJ-Object
			} catch (OpenMathException e) {
				throw new ParserRuntimeException("Unable to convert OMOBJ ot Element:" + varOmobj.toString(), e);
			}

		} else {
			throw new UndefinedFillInVariableRuntimeException(varNumber);
		}
	}

	@Override
	public Object visitParenthesis(ParenthesisContext ctx) {
		// visit second child, because first is "(" and third is ")" (both of
		// them return NULL-PointerException)
		return visit(ctx.getChild(1));
	}

	@Override
	public Object visitUnaryOperatorForExpression(UnaryOperatorForExpressionContext ctx) {
		// @Note: Do Not change Implementation of this Method
		return visitChildren(ctx);
	}

	@Override
	public Object visitBinaryOperatorForExpression(BinaryOperatorForExpressionContext ctx) {
		// @Note: Do Not change Implementation of this Method
		return visitChildren(ctx);
	}

	@Override
	public OMA visitSet(SetContext ctx) {
		OMA oma = new OMA();
		oma.getOmel().add(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("set"));
		for (ExpressionContext childctx : ctx.arguments) {
			oma.getOmel().add(visit(childctx));
		}
		return oma;
	}

	@Override
	public Object visitNestedFunction(NestedFunctionContext ctx) {
		OMA oma = new OMA();
		oma.getOmel().add(OMSEvaluatorSyntaxDictionary.getInstance().getOMS(ctx.name.getText()));
		for (ExpressionContext childctx : ctx.arguments) {
			oma.getOmel().add(visit(childctx));
		}
		return oma;
	}
}