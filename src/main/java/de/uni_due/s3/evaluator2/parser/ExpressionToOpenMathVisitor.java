package de.uni_due.s3.evaluator2.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTree;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.ErroneousExerciseVariableRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.ErroneousFillInVariableRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.UndefinedExerciseVariableRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.UndefinedFillInVariableRuntimeException;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorArithLineContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorArithLineInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorArithPointContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorArithPointInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorBooleanContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorBooleanInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorCircumflexInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorRelationalContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.BinaryOperatorRelationalInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ConstantContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ConstantInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ExerciseVarNameInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.FillInVarNameInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.FloatValueInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.FunctionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.FunctionInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.IntegerValueInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ListContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ListInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.ParenthesisInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.TextValueInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.UnaryOperatorContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.UnaryOperatorInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser.VariableInExpressionContext;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParserBaseVisitor;
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
 * Is used by the {@link antlr.EvaluatorParser parser} to convert a user's
 * expression to an openmath structure. Each method represents a grammatical
 * rule @see /antlr4/EvaluatorParser.g4.
 * 
 * @author frichtscheid,dlux,spobel
 *
 */
public class ExpressionToOpenMathVisitor extends EvaluatorParserBaseVisitor<Object> {

	/**
	 * These variables are defined by the {@link de.uni-due.s3.evaluator2.Evaluator
	 * evaluator} and are forwarded to this visitor, so that they can be integrated
	 * into the openmath-structure
	 */
	private HashMap<String, OMOBJ> exerciseVariableMap;
	private HashMap<Integer, OMOBJ> fillInVariableMap;
	final private static Pattern VARPOS = Pattern.compile("\\[var=[a-zA-Z0-9äöü]+?\\]|\\[pos=[0-9]+?\\]");

	/**
	 * Initialise the exercise- and fillinVariableMaps if they were not specified
	 * and add constants like PI and E to it. Every other constant that would be
	 * needed can be declared here.
	 * 
	 * @param exerciseVariableMap
	 *            Map with exerciseVariables
	 * @param fillInVariableMap
	 *            Map with fillInVariables
	 */
	public ExpressionToOpenMathVisitor(HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) {

		if (exerciseVariableMap != null) {
			this.exerciseVariableMap = exerciseVariableMap;
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
	 * Implements the grammatical rule "ArithLine" for + and - operations. This rule
	 * will be used on expressions like 3+2, 4-1 ...
	 * 
	 * @return OMA with the operation and two operands
	 */
	@Override
	public Object visitBinaryOperatorArithLineInExpression(BinaryOperatorArithLineInExpressionContext ctx) {
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA((OMS) visit(ctx.getChild(1)), omel);
	}

	/**
	 * Implements the dictionary for ArithLine Operators
	 * 
	 * @return OMS with ARITH LINE
	 */
	@Override
	public Object visitBinaryOperatorArithLine(BinaryOperatorArithLineContext ctx) {
		switch (ctx.operator.getText()) {
		case "+":
			return OMSymbol.ARITH1_PLUS;
		case "-":
			return OMSymbol.ARITH1_MINUS;
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Binary Operator " + ctx.operator.getText() + " is not supported");
		}
	}

	/**
	 * Implements the grammatical rule "ArithPoint" for *, / and % operations. This
	 * rule will be used on expressions like 3*2, 4/1 ...
	 * 
	 * @return OMA with the operation and two operands
	 */
	@Override
	public Object visitBinaryOperatorArithPointInExpression(BinaryOperatorArithPointInExpressionContext ctx) {
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA((OMS) visit(ctx.getChild(1)), omel);
	}

	/**
	 * Implements the dictionary for ArithPoint Operators
	 * 
	 * @return OMS with ARITH POINT
	 */
	@Override
	public Object visitBinaryOperatorArithPoint(BinaryOperatorArithPointContext ctx) {
		switch (ctx.operator.getText()) {
		case "*":
			return OMSymbol.ARITH1_TIMES;
		case "/":
			return OMSymbol.ARITH1_DIVIDE;
		case "%":
			return OMSymbol.INTEGER1_REMAINDER;
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Binary Operator " + ctx.operator.getText() + " is not supported");
		}
	}

	/**
	 * Implements the grammatical rule "Boolean" for logic operations like && or ||
	 * 
	 * @return OMA with the operation and two operands
	 */
	@Override
	public Object visitBinaryOperatorBooleanInExpression(BinaryOperatorBooleanInExpressionContext ctx) {
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA((OMS) visit(ctx.getChild(1)), omel);
	}

	/**
	 * Implements the dictionary for Boolean Operators
	 * 
	 * @return OMS with LOGIC
	 */
	@Override
	public Object visitBinaryOperatorBoolean(BinaryOperatorBooleanContext ctx) {
		switch (ctx.operator.getText()) {
		case "&&":
			return OMSymbol.LOGIC1_AND;
		case "||":
			return OMSymbol.LOGIC1_OR;
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Binary Operator " + ctx.operator.getText() + " is not supported");
		}
	}

	/**
	 * Implements the grammatical rule Circumflex for ^-operations. This rule will
	 * be used on expressions like 3^3, 9^2 ...
	 * 
	 * @return OMA with the operation and two operands
	 */
	@Override
	public Object visitBinaryOperatorCircumflexInExpression(BinaryOperatorCircumflexInExpressionContext ctx) {
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA(OMSymbol.ARITH1_POWER, omel);
	}

	/**
	 * Implements the grammatical rule "Relational" for <,<=,>,>=,=,==,!=
	 * operations. This rule will be used on expressions like 5>3 3==3 ...
	 * 
	 * @return OMA with the operation and two operands
	 */
	@Override
	public Object visitBinaryOperatorRelationalInExpression(BinaryOperatorRelationalInExpressionContext ctx) {
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(0))); // left side
		omel.add(visit(ctx.getChild(2))); // right side
		return OMCreator.createOMA((OMS) visit(ctx.getChild(1)), omel);
	}

	/**
	 * Implements the dictionary for Relational Operators
	 * 
	 * @return OMS with RELATION
	 */
	@Override
	public Object visitBinaryOperatorRelational(BinaryOperatorRelationalContext ctx) {
		switch (ctx.operator.getText()) {
		case "<":
			return OMSymbol.RELATION1_LT;
		case "<=":
			return OMSymbol.RELATION1_LEQ;
		case ">":
			return OMSymbol.RELATION1_GT;
		case ">=":
			return OMSymbol.RELATION1_GEQ;
		case "=":
			return OMSymbol.RELATION1_EQ;
		case "==":
			return OMSymbol.RELATION1_EQ;
		case "!=":
			return OMSymbol.RELATION1_NEQ;
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Binary Operator " + ctx.operator.getText() + " is not supported");
		}
	}

	@Override
	public Object visitConstantInExpression(ConstantInExpressionContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public Object visitConstant(ConstantContext ctx) {
		switch (ctx.name.getText()) {
		case "E":
			return OMSymbol.NUMS1_E;
		case "I":
			return OMSymbol.NUMS1_I;
		case "INFINITY":
			return OMSymbol.NUMS1_INFINITY;
		case "PI":
			return OMSymbol.NUMS1_PI;
		case "True":
		case "TRUE":
			return OMSymbol.LOGIC1_TRUE;
		case "False":
		case "FALSE":
			return OMSymbol.LOGIC1_FALSE;
		default:
			throw new FunctionNotImplementedRuntimeException("Constant " + ctx.name.getText() + " is not supported");
		}
	}

	/**
	 * Implements the grammatical rule "ExerciseVarName". Each exerciseVariable like
	 * [var=a] will be replaced by its corresponding openmath content which will
	 * then integrated into the om-structure. E and PI are also substituted here!
	 * 
	 * @return OM-Object depends on the content of the variable. Colud be a OMI,
	 *         OMF, OMA ...
	 */
	@Override
	public Object visitExerciseVarNameInExpression(ExerciseVarNameInExpressionContext ctx) {
		String var = ctx.name.getText(); // eg. [var=a]
		String varName = var.substring(var.indexOf('=') + 1, var.indexOf(']')); // eg. a

		if (varName.equals("E")) {
			return OMSymbol.NUMS1_E;
		} else if (varName.equals("PI")) {
			return OMSymbol.NUMS1_PI;
		} else if (exerciseVariableMap != null && exerciseVariableMap.containsKey(varName)) {
			// removes the OMOBJ-tags from the variable and returns its child
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
	 * Implements the grammatical rule "FillInVarName". Each fillInVariable like
	 * [pos=1] will be replaced by its corresponding openmath content which will
	 * then integrated into the om-structure.
	 * 
	 * @return OM-Object depends on the content of the variable. Colud be a OMI,
	 *         OMF, OMA ...
	 */
	@Override
	public Object visitFillInVarNameInExpression(FillInVarNameInExpressionContext ctx) {
		String var = ctx.name.getText(); // eg. [pos=1]
		int varNumber = 0;
		try {
			varNumber = Integer.parseInt(var.substring(var.indexOf('=') + 1, var.indexOf(']'))); // eg. 1
		} catch (NumberFormatException e) {
			throw new UndefinedFillInVariableRuntimeException(String.valueOf(varNumber) + ". Has to be Integer!");
		}
		// removes the OMOBJ-tags from the variable and returns its child
		if (fillInVariableMap != null && fillInVariableMap.containsKey(varNumber)) {
			OMOBJ varOmobj = fillInVariableMap.get(varNumber);
			try {
				return OMConverter.toElement(varOmobj);
			} catch (OpenMathException e) {
				throw new ErroneousFillInVariableRuntimeException(String.valueOf(varNumber));
			}
		} else {
			throw new UndefinedFillInVariableRuntimeException(String.valueOf(varNumber));
		}
	}

	/**
	 * Implements the grammatical rule "FloatValue". Converts the number of an
	 * expression like "123123.3030" into a double value.
	 * 
	 * @throws NumberFormatException
	 *             if the value is grater than the maximum double value
	 * 
	 * @return OMF
	 */
	@Override
	public OMF visitFloatValueInExpression(FloatValueInExpressionContext ctx) {
		try {
			return OMCreator.createOMF(Double.parseDouble(ctx.value.getText()));
		} catch (NumberFormatException e) {
			// fails converting to Double by : "123456789123456789.124212" -> in
			// short by real high or low Numbers
			throw new ParserRuntimeException("Could not Convert Number to Double. Number is to long", e);
		}
	}

	/**
	 * Implements the grammatical rule "IntegerValue". Converts the number of an
	 * expression like "123123" into a integer value.
	 * 
	 * @throws NumberFormatException
	 *             if the value is grater than the maximum integer value
	 * 
	 * @return OMI
	 */
	@Override
	public OMI visitIntegerValueInExpression(IntegerValueInExpressionContext ctx) {
		try {
			// Check if parasable
			Integer.parseInt(ctx.value.getText());

			OMI omi = new OMI();
			omi.setValue(ctx.value.getText());
			return omi;
		} catch (NumberFormatException e) {
			// fails converting to Integer by : "123456789123456789" -> in short
			// by real high or low Numbers
			throw new ParserRuntimeException("Could not Convert Number to Integer. Number is to long", e);
		}
	}

	@Override
	public Object visitFunctionInExpression(FunctionInExpressionContext ctx) {
		return visit(ctx.getChild(0));
	}

	/**
	 * Implements the grammatical rule "NestedFunctionInExpression" which will be
	 * used if a function was detected in the user's expression. It converts all
	 * arguments into openmath and looks in
	 * {@link de.unu-due.s3.evaluator2.core.dictionaries.OMSEvaluatorSyntaxDictionary
	 * OMSEvaluatorSyntaxDictionary} for the specified function.
	 * 
	 * @throws NumberFormatException
	 *             if the value is grater than the maximum integer value
	 * 
	 * @return OMA with the function and its arguments
	 */
	@Override
	public Object visitFunction(FunctionContext ctx) {
		List<Object> omel = new ArrayList<>();
		OMS oms = OMSEvaluatorSyntaxDictionary.getInstance().getOMS(ctx.name.getText());

		if (oms.getCd().equals("casJACK")) {
			ExpressionContext childctx = ctx.arguments.get(0);
			if (!childctx.getText().startsWith("'") || !childctx.getText().endsWith("'")) {
				throw new ParserRuntimeException("Function " + oms.getName()
						+ " is a CAS Function. It has to contain only one String. But got something without apos.");
			}
			String text = childctx.getText().substring(1, childctx.getText().length() - 1);
			omel.add(textWithVariablesGenerator(text));

		} else {
			for (ExpressionContext childctx : ctx.arguments) {
				omel.add(visit(childctx));
			}
		}

		return OMCreator.createOMA(oms, omel);
	}

	/**
	 * Implements the grammatical rule "Parenthesis" Parenthesis are not explicitly
	 * represented in openmath because they are represented by the openmath
	 * tree-structure. So it just visits the child in between the parenthesis
	 * 
	 * @return OM-Object depends on the expression in the parentheses
	 */
	@Override
	public Object visitParenthesisInExpression(ParenthesisInExpressionContext ctx) {
		// visit second child, because first is "(" and third is ")" (both of
		// them return NULL-PointerException)
		return visit(ctx.getChild(1));
	}

	@Override
	public Object visitListInExpression(ListInExpressionContext ctx) {
		return visit(ctx.getChild(0));
	}

	/**
	 * Implements the grammatical rule "Set" which converts a set in the user's
	 * expression to a OMA which represents this set
	 * 
	 * @return OMA with Set symbol and the content
	 */
	@Override
	public OMA visitList(ListContext ctx) {
		List<Object> omel = new ArrayList<>();
		for (ExpressionContext childctx : ctx.arguments) {
			omel.add(visit(childctx));
		}
		OMS oms = OMSymbol.LIST1_LIST;
		return OMCreator.createOMA(oms, omel);
	}

	/**
	 * Implements the grammatical rule "TextValue" and is used on every string in
	 * the user's expression. (1) First it tries to convert the string to any other
	 * element by parsing it again. A '2*3' would result in a 2*3 (OMA not a OMSTR).
	 * If this was successful a OMA will be returned with the original string and
	 * the result of the parsed string. (2) If (1) was not successful there will be
	 * an OMA created and all content of exercise and fillIn variables together with
	 * their string snippet will be appended to the OMA. If there is no variable in
	 * the string, a plain OMSTR which contains the string, will be returned
	 * 
	 * @return OMA or OMSTR
	 */
	@Override
	public Object visitTextValueInExpression(TextValueInExpressionContext ctx) {
		String text = ctx.getText().substring(1, ctx.getText().length() - 1); // the
																				// input

		/* Return if the InputString is an Expression */
		try {
			ParseTree tree = ExpressionParser.createParseTree(text);
			ArrayList<Object> omstrAndExpression = new ArrayList<>();
			omstrAndExpression.add(this.visit(tree));
			return this.visit(tree);

		} catch (ErroneousFillInVariableRuntimeException | ErroneousExerciseVariableRuntimeException
				| UndefinedFillInVariableRuntimeException | UndefinedExerciseVariableRuntimeException
				| ParserRuntimeException | FunctionNotImplementedRuntimeException e) {
			// do nothing continue Code below (In String is no Expression!)
		}

		return textWithVariablesGenerator(text);

	}

	/**
	 * Implements the grammatical rule "Unary" for unary operations like - and !
	 * Creates a OMA with the corresponding OMS and its argument
	 * 
	 * @return OMA
	 */
	@Override
	public Object visitUnaryOperatorInExpression(UnaryOperatorInExpressionContext ctx) {
		List<Object> omel = new ArrayList<>();
		omel.add(visit(ctx.getChild(1)));
		return OMCreator.createOMA((OMS) visit(ctx.getChild(0)), omel);
	}

	/**
	 * Implements the dictionary for Unary Operators
	 * 
	 * @return OMS with unary
	 */
	@Override
	public Object visitUnaryOperator(UnaryOperatorContext ctx) {
		switch (ctx.operator.getText()) {
		case "+":
			return OMSymbol.ARITH1_UNARY_PLUS;
		case "-":
			return OMSymbol.ARITH1_UNARY_MINUS;
		case "!":
			return OMSymbol.LOGIC1_NOT;
		default:
			throw new FunctionNotImplementedRuntimeException(
					"Unary Operator " + ctx.operator.getText() + " is not supported");
		}
	}

	/**
	 * Implements the grammatical rule "Variable". Creates a OMV with the name of
	 * the variable
	 * 
	 * @return OMV
	 */
	@Override
	public Object visitVariableInExpression(VariableInExpressionContext ctx) {
		return OMCreator.createOMV(ctx.name.getText());
	}

	private Object textWithVariablesGenerator(String text) {
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
			String varName = text.substring(varposMatcher.start() + 5, varposMatcher.end() - 1); // remove
			// or
			// "[var="

			/* extracting the pos OR var value */
			if (isVar) {
				if (varName.equals("E")) {
					omel.add(OMSymbol.NUMS1_E);
				} else if (varName.equals("PI")) {
					omel.add(OMSymbol.NUMS1_PI);
				} else if (exerciseVariableMap != null && exerciseVariableMap.containsKey(varName)) {
					OMOBJ omobjvar = exerciseVariableMap.get(varName);
					try {
						Object varObj = OMConverter.toElement(omobjvar);
						omel.add(varObj);
					} catch (OpenMathException e) {
						throw new ErroneousExerciseVariableRuntimeException(varName);
					}
				} else {
					throw new UndefinedExerciseVariableRuntimeException(varName);
				}
			} else {
				int varPosValue = 0;
				try {
					varPosValue = Integer.parseInt(varName);
				} catch (NumberFormatException e) {
					throw new UndefinedFillInVariableRuntimeException(varName + ". Has to be Integer!");
				}
				if (fillInVariableMap != null && fillInVariableMap.containsKey(varPosValue)) {
					OMOBJ omobjpos = fillInVariableMap.get(varPosValue);
					try {
						Object posObj = OMConverter.toElement(omobjpos);
						omel.add(posObj);
					} catch (OpenMathException e1) {
						throw new ErroneousFillInVariableRuntimeException(String.valueOf(varPosValue));
					}
				} else {
					throw new UndefinedFillInVariableRuntimeException(String.valueOf(varPosValue));
				}
			}
		}
		if (stringPointer != 0 && stringPointer < text.length()) {
			OMSTR omstr = OMCreator.createOMSTR(text.substring(stringPointer));
			omel.add(omstr); // Maybe add one OMSTR after pos/val Variables
		}

		if (omel.isEmpty()) {

			return OMCreator.createOMSTR(text);
			// return OMSTR as usual (no pos or var Variables are found in
			// String)

		} else {
			return OMCreator.createOMA(OMSymbol.STRINGJACK_TEXTWITHVARIABLES, omel);
			// return jack-specific String with variables in String
		}
	}
}