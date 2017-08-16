package de.uni_due.s3.evaluator2.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTree;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.ErroneousExerciseVariableRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.ErroneousFillInVariableRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.UndefinedExerciseVariableRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.UndefinedFillInVariableRuntimeException;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParserBaseVisitor;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryArithLineContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryArithPointContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryBooleanContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryCircumflexContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryRelationalContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ExerciseVarNameContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.FillInVarNameContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.FloatValueContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.IntegerValueContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.NestedFunctionInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ParenthesisContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.SetContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.TextValueContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.UnaryContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.VariableContext;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Is used by the {@link antlr.EvaluatorParser parser} to convert 
 * a user's expression to an openmath structure. Each method represents a grammatical rule @see /antlr4/EvaluatorParser.g4.
 * 
 * @author frichtscheid,dlux,spobel
 *
 */
public class ExpressionToOpenMathVisitor extends EvaluatorParserBaseVisitor<Object> {

	/**
	 * These variables are defined by the {@link de.uni-due.s3.evaluator2.Evaluator evaluator} and are forwarded 
	 * to this visitor, so that they can be integrated into the openmath-structure
	 */
	private HashMap<String, OMOBJ> exerciseVariableMap;
	private HashMap<Integer, OMOBJ> fillInVariableMap;
	final private static Pattern VARPOS = Pattern.compile("\\[var=[a-zA-Z0-9äöü]+?\\]|\\[pos=[0-9]+?\\]");

	/**
	 * Initialise the exercise- and fillinVariableMaps if they were not specified
	 * and add constants like PI and E to it. Every other constant that would be needed
	 * can be declared here.
	 * 
	 * @param exerciseVariableMap	Map with exerciseVariables
	 * @param fillInVariableMap		Map with fillInVariables
	 */
	public ExpressionToOpenMathVisitor(HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) {

		if (exerciseVariableMap != null) {
			this.exerciseVariableMap = exerciseVariableMap;

			OMOBJ pi = new OMOBJ();
			pi.setOMS(OMSymbol.NUMS1_PI);
			exerciseVariableMap.put("PI", pi);

			OMOBJ e = new OMOBJ();
			e.setOMS(OMSymbol.NUMS1_E);
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

	/**
	 * Implements the grammatical rule "ArithLine" for + and - operations.
	 * This rule will be used on expressions like 3+2, 4-1 ...
	 * @return OMA with the operation and two operands
	 */
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

	/**
	 * Implements the grammatical rule "ArithPoint" for *, / and % operations.
	 * This rule will be used on expressions like 3*2, 4/1 ...
	 * @return OMA with the operation and two operands
	 */
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

	/**
	 * Implements the grammatical rule "Boolean" for logic operations like && or ||
	 * 
	 * @return OMA with the operation and two operands
	 */
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

	/**
	 * Implements the grammatical rule Circumflex for ^-operations.
	 * This rule will be used on expressions like 3^3, 9^2 ...
	 * @return OMA with the operation and two operands
	 */
	@Override
	public Object visitBinaryCircumflex(BinaryCircumflexContext ctx) {
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA(OMSymbol.ARITH1_POWER, omel);
	}

	/**
	 * Implements the grammatical rule "Relational" for <,<=,>,>=,=,==,!= operations.
	 * This rule will be used on expressions like 5>3 3==3 ...
	 * @return OMA with the operation and two operands
	 */
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

	/**
	 * Implements the grammatical rule "ExerciseVarName".
	 * Each exerciseVariable like [var=a] will be replaced by its corresponding openmath content
	 * which will then integrated into the om-structure.
	 * 
	 * @return OM-Object depends on the content of the variable. Colud be a OMI, OMF, OMA ...
	 */
	@Override
	public Object visitExerciseVarName(ExerciseVarNameContext ctx) {
		String var = ctx.name.getText(); // eg. [var=a]
		String varName = var.substring(var.indexOf('=') + 1, var.indexOf(']')); // eg. a
														
		// removes the OMOBJ-tags from the variable and returns its child
		if (exerciseVariableMap.containsKey(varName)) {
			OMOBJ varOmobj = exerciseVariableMap.get(varName);
			try {
				return OMConverter.toElement(varOmobj); 
			} catch (OpenMathException e) {
				throw new ErroneousExerciseVariableRuntimeException(varName);
			}
			
		} else {
			throw new UndefinedExerciseVariableRuntimeException(varName);
		}
	}

	/**
	 * Implements the grammatical rule "FillInVarName".
	 * Each fillInVariable like [pos=1] will be replaced by its corresponding openmath content
	 * which will then integrated into the om-structure.
	 * 
	 * @return OM-Object depends on the content of the variable. Colud be a OMI, OMF, OMA ...
	 */
	@Override
	public Object visitFillInVarName(FillInVarNameContext ctx) {
		String var = ctx.name.getText(); // eg. [pos=1]
		int varNumber = Integer.parseInt(var.substring(var.indexOf('=') + 1, var.indexOf(']'))); // eg. 1
		
		// removes the OMOBJ-tags from the variable and returns its child
		if (fillInVariableMap.containsKey(varNumber)) {
			OMOBJ varOmobj = fillInVariableMap.get(varNumber);
			try {
				return OMConverter.toElement(varOmobj); 
			} catch (OpenMathException e) {
				throw new ErroneousFillInVariableRuntimeException(varNumber);
			}
		} else {
			throw new UndefinedFillInVariableRuntimeException(varNumber);
		}
	}

	/**
	 * Implements the grammatical rule "FloatValue".
	 * Converts the number of an expression like "123123.3030" into a double value.
	 * 
	 * @throws NumberFormatException if the value is grater than the maximum double value
	 * 
	 * @return OMF 
	 */
	@Override
	public OMF visitFloatValue(FloatValueContext ctx) {
		try {
			return OMCreator.createOMF(Double.parseDouble(ctx.value.getText()));
		} catch (NumberFormatException e) {
			// fails converting to Double by : "123456789123456789.124212" -> in
			// short by real high or low Numbers
			throw new ParserRuntimeException("Could not Convert Number to Double. Number is to long", e);
		}
	}

	/**
	 * Implements the grammatical rule "IntegerValue".
	 * Converts the number of an expression like "123123" into a integer value.
	 * 
	 * @throws NumberFormatException if the value is grater than the maximum integer value
	 * 
	 * @return OMI 
	 */
	@Override
	public OMI visitIntegerValue(IntegerValueContext ctx) {
		try {
			return OMCreator.createOMI(Integer.parseInt(ctx.value.getText()));
		} catch (NumberFormatException e) {
			// fails converting to Integer by : "123456789123456789" -> in short
			// by real high or low Numbers
			throw new ParserRuntimeException("Could not Convert Number to Integer. Number is to long", e);
		}
	}

	/**
	 * Implements the grammatical rule "NestedFunctionInExpression" which will be used if 
	 * a function was detected in the user's expression.
	 * It converts all arguments into openmath and looks in {@link de.unu-due.s3.evaluator2.core.dictionaries.OMSEvaluatorSyntaxDictionary OMSEvaluatorSyntaxDictionary}
	 * for the specified function.
	 * 
	 * @throws NumberFormatException if the value is grater than the maximum integer value
	 * 
	 * @return OMA with the function and its arguments 
	 */
	@Override
	public Object visitNestedFunctionInExpression(NestedFunctionInExpressionContext ctx) {
		List<Object> omel = new ArrayList<>();
		for (ExpressionContext childctx : ctx.arguments) {
			omel.add(visit(childctx));
		}
		OMS oms = OMSEvaluatorSyntaxDictionary.getInstance().getOMS(ctx.name.getText());
		return OMCreator.createOMA(oms, omel);
	}

	/**
	 * Implements the grammatical rule "Parenthesis"
	 * Parenthesis are not explicitly represented in openmath because they are represented by the openmath tree-structure.
	 * So it just visits the child in between the parenthesis
	 * 
	 * @return OM-Object depends on the expression in the parentheses
	 */
	@Override
	public Object visitParenthesis(ParenthesisContext ctx) {
		// visit second child, because first is "(" and third is ")" (both of
		// them return NULL-PointerException)
		return visit(ctx.getChild(1));
	}

	/**
	 * Implements the grammatical rule "Set" which converts a set in 
	 * the user's expression to a OMA which represents this set
	 * 
	 * @return OMA with Set symbol and the content
	 */
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
	 * Searches for [var=] and [pos=] statements and replaces 
	 * these with the content of the variable, only if the content can be parsed to a string
	 * 
	 * @param text String in which the variables should be substituted
	 * @return String with substituted variables 
	 */
	private String substituteVariables(String text) {
		StringBuilder sb = new StringBuilder();
		Matcher varposMatcher = VARPOS.matcher(text);
		/* Pointer to get string subsequences */
		int stringPointer = 0;
		while (varposMatcher.find()) {
			sb.append(text.substring(stringPointer, varposMatcher.start()));
			stringPointer = varposMatcher.end();
			boolean isVar = text.substring(varposMatcher.start(), varposMatcher.end()).contains("var");
			String varposVal = text.substring(varposMatcher.start() + 5, varposMatcher.end() - 1);
			if (isVar) {
				OMOBJ var = exerciseVariableMap.get(varposVal);
				if (var == null)
					throw new UndefinedExerciseVariableRuntimeException(varposVal);
				try {
					sb.append(OMUtils.convertOMToString(var));
				} catch (InputMismatchException e) {
				}
			} else {
				OMOBJ var = fillInVariableMap.get(Integer.parseInt(varposVal));
				if (var == null)
					throw new UndefinedFillInVariableRuntimeException(Integer.parseInt(varposVal));
				try {
					sb.append(OMUtils.convertOMToString(var));
				} catch (InputMismatchException e) {
				}
			}
		}
		sb.append(text.substring(stringPointer));
		return sb.toString();
	}

	/**
	 * Implements the grammatical rule "TextValue" and is used on every string in the user's expression.
	 * (1) First it tries to convert the string to any other element by parsing it again. A '2*3' 
	 * would result in a 2*3 (OMA not a OMSTR). If this was successful a OMA will be returned 
	 * with the original string and the result of the parsed string.
	 * (2) If (1) was not successful there will be an OMA created and all content of exercise and fillIn 
	 * variables together with their string snippet will be appended to the OMA. If there is no variable
	 * in the string, a plain OMSTR which contains the string, will be returned
	 * 
	 * @return OMA or OMSTR
	 */
	@Override
	public Object visitTextValue(TextValueContext ctx) {
		String text = ctx.getText().substring(1, ctx.getText().length() - 1); // the
																				// input

		/* Return if the InputString is an Expression */
		try {
			ParseTree tree = ExpressionParser.createParseTree(text);
			ArrayList<Object> omstrAndExpression = new ArrayList<>();

			omstrAndExpression.add(OMCreator.createOMSTR(substituteVariables(text)));
			omstrAndExpression.add(this.visit(tree));
			return OMCreator.createOMA(OMSymbol.STRINGJACK_TEXTWITHEXPRESSION, omstrAndExpression);
		} catch (ParserRuntimeException e) {
			// do nothing continue Code below (In String is no Expression!)
		}

		/* Return if the InputString is a Text with or without variables */
		ArrayList<Object> omel = new ArrayList<>();

		Matcher varposMatcher = VARPOS.matcher(text); // PatternMatcher
														// finds [pos=*] or
														// [val=*]

		int stringPointer = 0; // Pointer to get string subsequences
		while (varposMatcher.find()) {
			OMSTR omstr = OMCreator.createOMSTR(text.substring(stringPointer, varposMatcher.start()));
			stringPointer = varposMatcher.end();

			if (omstr.getContent().length() != 0) {
				omel.add(omstr); // Add leading OMSTR of pos/val Variables
			}

			boolean isVar = text.substring(varposMatcher.start(), varposMatcher.end()).contains("var");
			String varposVal = text.substring(varposMatcher.start() + 5, varposMatcher.end() - 1); // remove
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
		if (stringPointer != 0 && stringPointer < text.length()) {
			OMSTR omstr = OMCreator.createOMSTR(text.substring(stringPointer));
			omel.add(omstr); // Maybe add one OMSTR after pos/val Variables
		}

		if (omel.isEmpty()) {
			return OMCreator.createOMSTR(ctx.getText().substring(1, ctx.getText().length() - 1));
			// return OMSTR as usual (no pos or var Variables are found in
			// String)
		} else {
			return OMCreator.createOMA(OMSymbol.STRINGJACK_TEXTWITHVARIABLES, omel);
			// return jack-specific String with variables in String
		}
	}

	/**
	 * Implements the grammatical rule "Unary" for unary operations like - and !
	 * Creates a OMA with the corresponding OMS and its argument
	 * 
	 * @return OMA
	 */
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

	/**
	 * Implements the grammatical rule "Variable".
	 * Creates a OMV with the name of the variable
	 * 
	 * @return OMV
	 */
	@Override
	public Object visitVariable(VariableContext ctx) {
		return OMCreator.createOMV(ctx.name.getText());
	}
}