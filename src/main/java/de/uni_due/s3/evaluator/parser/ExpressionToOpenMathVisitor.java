package de.uni_due.s3.evaluator.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTree;

import de.uni_due.s3.evaluator.core.dictionaries.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.UndefinedExerciseVariableRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.UndefinedFillInVariableRuntimeException;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.BinaryArithLineContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.BinaryArithPointContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.BinaryBooleanContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.BinaryCircumflexContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.BinaryRelationalContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ExerciseVarNameContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.FillInVarNameContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.FloatValueContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.IntegerValueContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.NestedFunctionInExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ParenthesisContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.SetContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.TextValueContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.UnaryContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.VariableContext;
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
	private HashMap<String, OMOBJ> exerciseVariableMap;
	private HashMap<Integer, OMOBJ> fillInVariableMap;

	public ExpressionToOpenMathVisitor(HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) {
		
		if (exerciseVariableMap != null){
			this.exerciseVariableMap = exerciseVariableMap;
			OMOBJ pi = new OMOBJ();
			pi.setOMS(OMSymbol.NUMS1_PI);
			exerciseVariableMap.put("PI", pi);
			OMOBJ e = new OMOBJ();
			e.setOMS(OMSymbol.NUMS1_PI);
			exerciseVariableMap.put("E", e);
		} else {
			exerciseVariableMap = new HashMap<>();
		}
		
		if (fillInVariableMap != null) {
			this.fillInVariableMap = fillInVariableMap;
		} else {
			fillInVariableMap = new HashMap<>();
		}
	}

	@Override
	public Object visitBinaryArithLine(BinaryArithLineContext ctx) {
		OMS oms;
		switch (ctx.operator.getText()) {
		case "+":
			oms = OMSymbol.ARITH1_PLUS;
			break;
		case "-":
			oms = OMSymbol.ARITH1_MINUS;
			break;
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Binary Operator " + ctx.operator.getText() + " is not supported");
		}
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA(oms, omel);
	}

	@Override
	public Object visitBinaryArithPoint(BinaryArithPointContext ctx) {
		OMS oms;
		switch (ctx.operator.getText()) {
		case "*":
			oms = OMSymbol.ARITH1_TIMES;
			break;
		case "/":
			oms = OMSymbol.ARITH1_DIVIDE;
			break;
		case "%":
			oms = OMSymbol.INTEGER1_REMAINDER;
			break;
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Binary Operator " + ctx.operator.getText() + " is not supported");
		}
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA(oms, omel);
	}

	@Override
	public Object visitBinaryBoolean(BinaryBooleanContext ctx) {
		OMS oms;
		switch (ctx.getChild(1).getText()) {
		case "&&":
			oms = OMSymbol.LOGIC1_AND;
			break;
		case "||":
			oms = OMSymbol.LOGIC1_OR;
			break;
		default:
			throw new FunctionNotImplementedRuntimeException("Binary Operator " + ctx.operator + " is not supported");
		}
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA(oms, omel);
	}

	@Override
	public Object visitBinaryCircumflex(BinaryCircumflexContext ctx) {
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA(OMSymbol.ARITH1_POWER, omel);
	}

	@Override
	public Object visitBinaryRelational(BinaryRelationalContext ctx) {
		OMS oms;
		switch (ctx.operator.getText()) {
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
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Binary Operator " + ctx.operator.getText() + " is not supported");
		}
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA(oms, omel);
	}

	@Override
	public Object visitExerciseVarName(ExerciseVarNameContext ctx) {
		String var = ctx.name.getText(); // eg. [var=a]
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
	public Object visitFillInVarName(FillInVarNameContext ctx) {
		String var = ctx.name.getText(); // eg. [pos=1]
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
	public OMF visitFloatValue(FloatValueContext ctx) {
		return OMCreator.createOMF(Double.parseDouble(ctx.value.getText()));
	}

	@Override
	public OMI visitIntegerValue(IntegerValueContext ctx) {
		return OMCreator.createOMI(Integer.parseInt(ctx.value.getText()));
	}

	@Override
	public Object visitNestedFunctionInExpression(NestedFunctionInExpressionContext ctx) {
		List<Object> omel = new ArrayList<>();
		for (ExpressionContext childctx : ctx.arguments) {
			omel.add(visit(childctx));
		}
		OMS oms = OMSEvaluatorSyntaxDictionary.getInstance().getOMS(ctx.name.getText());
		return OMCreator.createOMA(oms, omel);
	}

	@Override
	public Object visitParenthesis(ParenthesisContext ctx) {
		// visit second child, because first is "(" and third is ")" (both of
		// them return NULL-PointerException)
		return visit(ctx.getChild(1));
	}

	@Override
	public OMA visitSet(SetContext ctx) {
		List<Object> omel = new ArrayList<>();
		for (ExpressionContext childctx : ctx.arguments) {
			omel.add(visit(childctx));
		}
		OMS oms = OMSEvaluatorSyntaxDictionary.getInstance().getOMS("set");
		return OMCreator.createOMA(oms, omel);
	}

	/**
	 * TODO FIXME dlux spobel frichtscheid mayeb get a TextExpression,
	 * TextWithVariable and TextOnly visit methods with anltr?
	 */
	@Override
	public Object visitTextValue(TextValueContext ctx) {

		String val = ctx.getText().substring(1, ctx.getText().length() - 1); // the
																				// input

		/* Return if the InputString is an Expression */
		try {
			ParseTree tree = ExpressionParser.createParseTree(val);
			return this.visit(tree);
		} catch (ParserRuntimeException e) {
			// do nothing continue Code below (In String is no Expression!)
		}

		/* Return if the InputString is a Text with or without variables */
		ArrayList<Object> omel = new ArrayList<>();
		Pattern varposPattern = Pattern.compile("\\[var=[a-zA-Z0-9äöü]+?\\]|\\[pos=[0-9]+?\\]");
		Matcher varposMatcher = varposPattern.matcher(val); // PatternMatcher
															// finds [pos=*] or
															// [val=*]

		int stringPointer = 0; // Pointer to get string subsequences
		while (varposMatcher.find()) {
			OMSTR omstr = OMCreator.createOMSTR(val.substring(stringPointer, varposMatcher.start()));
			stringPointer = varposMatcher.end();

			if (omstr.getContent().length() != 0) {
				omel.add(omstr); // Add leading OMSTR of pos/val Variables
			}

			boolean isVar = val.substring(varposMatcher.start(), varposMatcher.end()).contains("var");
			String varposVal = val.substring(varposMatcher.start() + 5, varposMatcher.end() - 1); // remove
																						// or
																									// "[var="

			/* extracting the pos OR var value */
			if (isVar) {
				OMOBJ omobjvar = exerciseVariableMap.get(varposVal);
				try {
					Object varObj = OMConverter.toElement(omobjvar);
					omel.add(varObj);
				} catch (OpenMathException e) {
					throw new UndefinedExerciseVariableRuntimeException(varposVal);
				}
			} else {
				OMOBJ omobjpos = fillInVariableMap.get(Integer.parseInt(varposVal));
				try {
					Object posObj = OMConverter.toElement(omobjpos);
					omel.add(posObj);
				} catch (OpenMathException e1) {
					throw new UndefinedFillInVariableRuntimeException(Integer.parseInt(varposVal));
				}
			}
		}
		if (stringPointer != 0 && stringPointer < val.length()) {
			OMSTR omstr = OMCreator.createOMSTR(val.substring(stringPointer));
			omel.add(omstr); // Maybe add one OMSTR after pos/val Variables
		}

		if (omel.isEmpty()) {
			return OMCreator.createOMSTR(ctx.getText().substring(1, ctx.getText().length() - 1));
			// return OMSTR as usual (no pos or var Variables are found in
			// String)
		} else {
			return OMCreator.createOMA(OMSymbol.STRINGJACK_TEXTVALUEWITHVARIABLES, omel);
			// return jack-specific String with variables in String
		}
	}

	@Override
	public Object visitUnary(UnaryContext ctx) {
		OMS oms = new OMS();
		switch (ctx.operator.getText()) {
		case "+":
			return visit(ctx.getChild(1));
		case "-":
			oms = OMSymbol.ARITH1_UNARY_MINUS;
			break;
		case "!":
			oms = OMSymbol.LOGIC1_NOT;
			break;
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Unary Operator " + ctx.operator.getText() + " is not supported");
		}
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(1)));
		return OMCreator.createOMA(oms, omel);
	}

	@Override
	public Object visitVariable(VariableContext ctx) {
		return OMCreator.createOMV(ctx.name.getText());
	}
}