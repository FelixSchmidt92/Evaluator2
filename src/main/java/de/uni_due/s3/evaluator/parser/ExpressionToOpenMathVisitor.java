package de.uni_due.s3.evaluator.parser;

import java.util.Map;

import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
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
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class ExpressionToOpenMathVisitor extends EvaluatorParserBaseVisitor<Object> {

	/**
	 * These variables are defined by the evaluator-class and are used to
	 * integrate the variables into the OM-tree
	 */
	private Map<String, OMOBJ> exerciseVariableMap;
	private Map<Integer, OMOBJ> fillInVariableMap;

	public ExpressionToOpenMathVisitor(Map<String, OMOBJ> exerciseVariableMap, Map<Integer, OMOBJ> fillInVariableMap) {
		this.exerciseVariableMap = exerciseVariableMap;
		this.fillInVariableMap = fillInVariableMap;
	}
	
	@Override
	public OMSTR visitTextValue(TextValueContext ctx) {
		OMSTR omstr = new OMSTR(); // FIXME enthält der Text eine Variable [pos=
																		// var=] muss diese ersetzt werden?!
		omstr.setContent(ctx.getText().substring(1, ctx.getText().length() - 1)); 	// delete
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
		OMS oms = new OMS();
		switch (ctx.getChild(1).getText()) {
		case "+":
			oms = OMCreator.createOMS("arith1", "plus");break;
		case "-":
			oms = OMCreator.createOMS("arith1", "minus");break;
		case "*":
			oms = OMCreator.createOMS("arith1", "times");break;
		case "/":
			oms = OMCreator.createOMS("arith1", "divide");break;
		case "%":
			oms = OMCreator.createOMS("jackbinary1", "modulus");break;
		case "<":
			oms = OMCreator.createOMS("relation1", "lt");break;
		case "<=":
			oms = OMCreator.createOMS("relation1", "leq");break;
		case ">":
			oms = OMCreator.createOMS("relation1", "gt");break;
		case ">=":
			oms = OMCreator.createOMS("relation1", "geq");break;
		case "=":
			oms = OMCreator.createOMS("relation1", "eq");break;
		case "==":
			oms = OMCreator.createOMS("logic1", "equivalent");break;
		case "!=":
			oms = OMCreator.createOMS("relation1", "neq");break;
		case "&&":
			oms = OMCreator.createOMS("logic1", "and");break;
		case "||":
			oms = OMCreator.createOMS("logic1", "or");break;
		default: 
			throw new FunctionNotImplementedException("Binary Operator " + ctx.getChild(1).getText() + " is not supported");
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
				return OMConverter.toElement(varOmobj);  // removes the OMOBJ-tags
			} catch (OpenMathException e) {
				throw new ParserException("Unable to convert OMOBJ ot Element:" + varOmobj.toString(), e);
			}
													// and returns the child of
													// the OMOBJ-Object
		} else {
			throw new UndefinedExerciseVariableException(varName);
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
	public OMA visitUnary(UnaryContext ctx) {
		OMS oms = new OMS();

		switch (ctx.getChild(0).getText()) {
		case "+":
			oms = OMCreator.createOMS("arith1", "unary_plus");break;
		case "-":
			oms = OMCreator.createOMS("arith1", "unary_minus");break;
		case "!":
			oms = OMCreator.createOMS("logic1", "not");break;
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
				return OMConverter.toElement(varOmobj); // removes the OMOBJ-tags
														// and returns the child of
														// the OMOBJ-Object
			} catch (OpenMathException e) {
				throw new ParserException("Unable to convert OMOBJ ot Element:" + varOmobj.toString(), e);
			}

		} else {
			throw new UndefinedFillInVariableException(varNumber);
		}
	}

	@Override
	public Object visitParenthesis(ParenthesisContext ctx) {
		// visit second child, because first is "(" and third is ")" (both of them return NULL-PointerException)
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
	public Object visitNestedFunction(NestedFunctionContext ctx){
		OMA oma = new OMA();
		oma.getOmel().add(OMSEvaluatorSyntaxDictionary.getInstance().getOMS(ctx.name.getText()));
		for (ExpressionContext childctx : ctx.arguments) {
			oma.getOmel().add(visit(childctx));
		}
		return oma;
	}
}