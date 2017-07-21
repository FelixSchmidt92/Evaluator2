package de.uni_due.s3.evaluator.core.functionData;

import java.util.HashMap;
import java.util.Map;

import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.openmath.jaxb.OMS;

/**
 * This Class contains the Translation from Input of the User to the 
 * corresponding OMS.
 * 
 * Include here the Name (String) and the OMSymbol of the new created Function.
 * Don't forget to add it in OMSFunctionDictionary too! 
 * 
 * @author dlux, spobel, frichtscheid
 *
 */
public class OMSEvaluatorSyntaxDictionary {

	private Map<String, OMS> functionSymbolMap = new HashMap<String, OMS>();

	private static OMSEvaluatorSyntaxDictionary omsesd = new OMSEvaluatorSyntaxDictionary();

	public static OMSEvaluatorSyntaxDictionary getInstance() {
		return omsesd;
	}

	/**
	 * OMSEvaluatorSyntaxDictionary. Here are the Names and the corresponding Symbols listesd 
	 * 
	 * If another Function for this Evaluator is written you have to add it here and in
	 * OMSFunctionDictionary
	 * 
	 * Also add the OMSymbol in OMSymbol, so referencing this new Function can be made easy!
	 */
	private OMSEvaluatorSyntaxDictionary() {
		functionSymbolMap.put("plus", OMSymbol.ARITH1_PLUS);
		functionSymbolMap.put("minus", OMSymbol.ARITH1_MINUS);
		functionSymbolMap.put("times", OMSymbol.ARITH1_TIMES);
		functionSymbolMap.put("divide", OMSymbol.ARITH1_DIVIDE);
		functionSymbolMap.put("abs", OMSymbol.ARITH1_ABS);
		functionSymbolMap.put("gcd", OMSymbol.ARITH1_GCD);
		functionSymbolMap.put("root", OMSymbol.ARITH1_ROOT);
		functionSymbolMap.put("sqrt", OMSymbol.ARITH1_ROOT);
		functionSymbolMap.put("pow", OMSymbol.ARITH1_POWER);
		functionSymbolMap.put("unaryminus", OMSymbol.ARITH1_UNARY_MINUS);

		functionSymbolMap.put("min", OMSymbol.ARITHJACK_MIN);
		functionSymbolMap.put("max", OMSymbol.ARITHJACK_MAX);
		functionSymbolMap.put("ieeeremainder", OMSymbol.ARITHJACK_IEEEREMAINDER);

		// Integer
		functionSymbolMap.put("remainder", OMSymbol.INTEGER1_REMAINDER);
		// Rounding
		functionSymbolMap.put("ceil", OMSymbol.ROUNDING1_CEILING);
		functionSymbolMap.put("floor", OMSymbol.ROUNDING1_FLOOR);
		functionSymbolMap.put("round", OMSymbol.ROUNDING1_ROUND);
		// Binary
		functionSymbolMap.put("convertToBinary", OMSymbol.BINARYJACK_CONVERTTOBINARY);
		functionSymbolMap.put("equalsBinary", OMSymbol.BINARYJACK_EQUALSBINARY);
		// OpenMathFunctions
		functionSymbolMap.put("countBasicOperations", OMSymbol.OPENMATHJACK_COUNTBASICOPERATIONS);
		functionSymbolMap.put("countNodes", OMSymbol.OPENMATHJACK_COUNTNODES);
		functionSymbolMap.put("getDenominator", OMSymbol.OPENMATHJACK_GETDENOMINATOR);
		functionSymbolMap.put("getNumerator", OMSymbol.OPENMATHJACK_GETNUMERATOR);
		functionSymbolMap.put("random", OMSymbol.OPENMATHJACK_RANDOM);
		// Relationen
		functionSymbolMap.put("lessthan", OMSymbol.RELATION1_LT);
		functionSymbolMap.put("lessthanorequal", OMSymbol.RELATION1_LEQ);
		functionSymbolMap.put("greaterthan", OMSymbol.RELATION1_GT);
		functionSymbolMap.put("greaterthanorequal", OMSymbol.RELATION1_GEQ);
		functionSymbolMap.put("equal", OMSymbol.RELATION1_EQ);
		functionSymbolMap.put("notequal", OMSymbol.RELATION1_NEQ);
		functionSymbolMap.put("equalssemisem", OMSymbol.POLYNOMIALJACK_EQUALSEMISEM);
		// Logic
		functionSymbolMap.put("booleanor", OMSymbol.LOGIC1_OR);
		functionSymbolMap.put("booleanand", OMSymbol.LOGIC1_AND);
		functionSymbolMap.put("booleannot", OMSymbol.LOGIC1_NOT);
		functionSymbolMap.put("ifthenelse", OMSymbol.LOGICJACK_IFTHENELSE);
		// Trigonometrie
		functionSymbolMap.put("toDegrees", OMSymbol.TRANSCJACK_TODEGREE);//FIXME flag deprecated
		functionSymbolMap.put("toRadians", OMSymbol.TRANSCJACK_TORADIAN);//FIXME flag deprecated
		functionSymbolMap.put("toDegree", OMSymbol.TRANSCJACK_TODEGREE);
		functionSymbolMap.put("toRadian", OMSymbol.TRANSCJACK_TORADIAN);
		functionSymbolMap.put("sin", OMSymbol.TRANSC1_SIN);
		functionSymbolMap.put("cos", OMSymbol.TRANSC1_COS);
		functionSymbolMap.put("tan", OMSymbol.TRANSC1_TAN);
		functionSymbolMap.put("log", OMSymbol.TRANSC1_LOG);
		functionSymbolMap.put("asin", OMSymbol.TRANSC1_ARCSIN);
		functionSymbolMap.put("acos", OMSymbol.TRANSC1_ARCCOS);
		functionSymbolMap.put("atan", OMSymbol.TRANSC1_ARCTAN);
		functionSymbolMap.put("exp", OMSymbol.TRANSC1_EXP);
		functionSymbolMap.put("atan2", OMSymbol.TRANSC2_ARCTAN2);
		// Polynome
		//functionSymbolMap.put("deg", OMSymbol.POLYNOMIAL1_DEGREE);
		functionSymbolMap.put("deg", OMSymbol.POLY_DEGREE_WRT);
		functionSymbolMap.put("degh", OMSymbol.POLY_DEGREE); //FIXME: hier drüber sollte noch mal entschieden werden.
		functionSymbolMap.put("dependsOn", OMSymbol.POLYNOMIALJACK_DEPENDSON);
		functionSymbolMap.put("derive", OMSymbol.POLYNOMIALJACK_DERIVE);
		functionSymbolMap.put("equalsExpr", OMSymbol.POLYNOMIALJACK_EQUALSEXPR);
		functionSymbolMap.put("evalEq", OMSymbol.POLYNOMIALJACK_EVALEQ);
		functionSymbolMap.put("evalpolynomial", OMSymbol.POLYNOMIALJACK_EVALPOLYNOMIAL);
		functionSymbolMap.put("evalPolynomialCplx", OMSymbol.POLYNOMIALJACK_EVALPOLYNOMIALCPLX);
		functionSymbolMap.put("evalterm2", OMSymbol.POLYNOMIALJACK_EVALTERM2);
		functionSymbolMap.put("expand", OMSymbol.POLYNOMIAL1_EXPAND);
		functionSymbolMap.put("factorOf", OMSymbol.POLYNOMIALJACK_FACTOROF);
		functionSymbolMap.put("integrate", OMSymbol.POLYNOMIALJACK_INTEGRATE);
		functionSymbolMap.put("numberOfVariables", OMSymbol.POLYNOMIALJACK_NUMBEROFVARIABLES);
		// Lineare Algebra
		functionSymbolMap.put("vector", OMSymbol.LINALG2_VECTOR);
		functionSymbolMap.put("matrix", OMSymbol.LINALG2_MATRIX);
		functionSymbolMap.put("matrixrow", OMSymbol.LINALG2_MATRIXROW);
		functionSymbolMap.put("equalBasis", OMSymbol.LINALGJACK_EQUALBASIS);
		functionSymbolMap.put("isLinearlyIndependent", OMSymbol.LINALGJACK_ISLINEARLYINDEPENDENT);
		functionSymbolMap.put("randomMatrixEigenvalue", OMSymbol.LINALGJACK_RANDOMMATRIXEIGENVALUE);
		functionSymbolMap.put("randomMatrixRank", OMSymbol.LINALGJACK_RANDOMMATRIXRANK);
		// List
		functionSymbolMap.put("set", OMSymbol.SET1_SET);
		functionSymbolMap.put("size", OMSymbol.SET1_SIZE);
		functionSymbolMap.put("chooseFromComplement", OMSymbol.SETJACK_CHOOSEFROMCOMPLEMENT);
		functionSymbolMap.put("getFromOrderedSet", OMSymbol.SETJACK_GETFROMORDEREDSET);
		functionSymbolMap.put("getFromSet", OMSymbol.SETJACK_GETFROMSET);
		// CAS
		functionSymbolMap.put("evaluateInR", OMSymbol.CASJACK_EVALUATEINR);
		functionSymbolMap.put("evaluateInSage", OMSymbol.CASJACK_EVALUATEINSAGE);
		functionSymbolMap.put("evaluateInSymja", OMSymbol.CASJACK_EVALUATEINSYMJA);
		// String
		functionSymbolMap.put("charAt", OMSymbol.STRINGJACK_CHARAT);
		functionSymbolMap.put("compareTo", OMSymbol.STRINGJACK_COMPARETO);
		functionSymbolMap.put("compareToIgnoreCase", OMSymbol.STRINGJACK_COMPARETOIGNORECASE);
		functionSymbolMap.put("concat", OMSymbol.STRINGJACK_CONCAT);
		functionSymbolMap.put("endsWith", OMSymbol.STRINGJACK_ENDSWITH);
		functionSymbolMap.put("equals", OMSymbol.STRINGJACK_EQUALS);
		functionSymbolMap.put("equalsIgnoreCase", OMSymbol.STRINGJACK_EQUALSIGNORECASE);
		functionSymbolMap.put("indexOf", OMSymbol.STRINGJACK_INDEXOF);
		functionSymbolMap.put("lastIndexOf", OMSymbol.STRINGJACK_LASTINDEXOF);
		functionSymbolMap.put("length", OMSymbol.STRINGJACK_LENGTH);
		functionSymbolMap.put("startsWith", OMSymbol.STRINGJACK_STARTSWITH);
		functionSymbolMap.put("replace", OMSymbol.STRINGJACK_REPLACE);
		functionSymbolMap.put("substring", OMSymbol.STRINGJACK_SUBSTRING);
		functionSymbolMap.put("toLowerCase", OMSymbol.STRINGJACK_TOLOWERCASE);
		functionSymbolMap.put("toUpperCase", OMSymbol.STRINGJACK_TOUPPERCASE);
		functionSymbolMap.put("trim", OMSymbol.STRINGJACK_TRIM);
		functionSymbolMap.put("matches", OMSymbol.STRINGJACK_MATCHES);

		// Evaluate
		functionSymbolMap.put("eval", OMSymbol.EVALJACK_EVAL);
		// Complex
		functionSymbolMap.put("evalcplx", OMSymbol.COMPLEXJACK_EVALCPLX);
		// Constanten

		// TestTerminal
		functionSymbolMap.put("isEmpty", OMSymbol.TESTTERMINALJACK_ISEMPTY);
		functionSymbolMap.put("isPolynomial", OMSymbol.TESTTERMINALJACK_ISPOLYNOMIAL);
		functionSymbolMap.put("isSet", OMSymbol.TESTTERMINALJACK_ISSET);
		functionSymbolMap.put("isNumber", OMSymbol.TESTTERMINALJACK_ISNUMBER);
		functionSymbolMap.put("isFraction", OMSymbol.TESTTERMINALJACK_ISFRACTION);
		
		functionSymbolMap.put("matrix", OMSymbol.LINALG2_MATRIX);
		functionSymbolMap.put("matrixrow", OMSymbol.LINALG2_MATRIXROW);
		functionSymbolMap.put("vector", OMSymbol.LINALG2_VECTOR);
	}

	/**
	 * This method returns the specific OMSymbol
	 * 
	 * @param name
	 *            the name (key)(in lower case) of this OMSymbol
	 * @return the specific OMSymbol
	 * @throws FunctionNotImplementedRuntimeException
	 *             if Function is not found in HashMap
	 */
	public OMS getOMS(String name) throws FunctionNotImplementedRuntimeException {
		if (functionSymbolMap.containsKey(name)) {
			return functionSymbolMap.get(name);
		} else {
			throw new FunctionNotImplementedRuntimeException(name);
		}
	}
}
