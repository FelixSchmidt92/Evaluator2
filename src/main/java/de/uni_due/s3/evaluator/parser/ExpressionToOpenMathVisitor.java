package de.uni_due.s3.evaluator.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTree;

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
import de.uni_due.s3.openmath.omutils.OMCreator;
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

	/**
	 * TODO FIXME dlux spobel frichtscheid mayeb get a TextExpression, TextWithVariable and 
	 * 			  TextOnly visit methods with anltr?
	 */
	@Override
	public Object visitTextValue(TextValueContext ctx) {

		
		String val = ctx.getText().substring(1, ctx.getText().length()-1); //the input
		
		/*Return if the InputString is an Expression*/
		try{
			ParseTree tree = ExpressionParser.createParseTree(val); 
			return this.visit(tree);
		}catch(ParserRuntimeException e){
			// do nothing continue Code below (In String is no Expression!)
		}
		
		/*Return if the InputString is a Text with or without variables*/
		ArrayList<Object> omel = new ArrayList<>();
		Pattern varposPattern = Pattern.compile("\\[var=[a-zA-Z0-9äöü]+?\\]|\\[pos=[0-9]+?\\]");
		Matcher varposMatcher = varposPattern.matcher(val); //PatternMatcher finds [pos=*] or [val=*]

		
		int stringPointer = 0; //Pointer to get string subsequences
		while (varposMatcher.find()){
			OMSTR omstr = OMCreator.createOMSTR(val.substring(stringPointer, varposMatcher.start()));
			stringPointer = varposMatcher.end();
			
			if(omstr.getContent().length() != 0){
				omel.add(omstr);	// Add leading OMSTR of pos/val Variables
			}
			
			boolean isVar = val.substring(varposMatcher.start(), varposMatcher.end()).contains("var");
			String varposVal = val.substring(varposMatcher.start() + 5, varposMatcher.end() -1); //remove "[pos=" and "]" or "[var="
			
			/*extracting the pos OR var value*/
			if (isVar){
				OMOBJ omobjvar = exerciseVariableMap.get(varposVal);
				try {
					Object varObj = OMConverter.toElement(omobjvar);
					omel.add(varObj);
				} catch (OpenMathException e) {
				throw new UndefinedExerciseVariableRuntimeException(varposVal);
				}
			}else{
				OMOBJ omobjpos = fillInVariableMap.get(Integer.parseInt(varposVal));
				try {
					Object posObj = OMConverter.toElement(omobjpos);
					omel.add(posObj);
				} catch (OpenMathException e1) {
					throw new UndefinedFillInVariableRuntimeException(Integer.parseInt(varposVal));
				}
			}
		}
		if(stringPointer != 0 && stringPointer < val.length()){
			OMSTR omstr = OMCreator.createOMSTR(val.substring(stringPointer));
			omel.add(omstr);	// Maybe add one OMSTR after pos/val Variables 
		}
		
		if (omel.isEmpty()){
			return OMCreator.createOMSTR(ctx.getText().substring(1, ctx.getText().length()-1));
			//return OMSTR as usual (no pos or var Variables are found in String)
		}else{
			return OMCreator.createOMA(OMCreator.createOMS("string_jack", "textValueWithVars"), omel);
			//return jack-specific String with variables in String
		}
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