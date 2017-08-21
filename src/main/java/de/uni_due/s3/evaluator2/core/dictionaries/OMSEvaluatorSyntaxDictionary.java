package de.uni_due.s3.evaluator2.core.dictionaries;

import java.util.HashMap;
import java.util.Map;

import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedRuntimeException;
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
	 * OMSEvaluatorSyntaxDictionary. Here are the Names and the corresponding
	 * Symbols listesd
	 * 
	 * If another Function for this Evaluator is written you have to add it here
	 * and in OMSFunctionDictionary
	 * 
	 * Also add the OMSymbol in OMSymbol, so referencing this new Function can
	 * be made easy!
	 */
	private OMSEvaluatorSyntaxDictionary() {

		functionSymbolMap.put("IEEEremainder", OMSymbol.ARITHJACK_IEEEREMAINDER);
		functionSymbolMap.put("max", OMSymbol.ARITHJACK_MAX);
		functionSymbolMap.put("min", OMSymbol.ARITHJACK_MIN);

		functionSymbolMap.put("abs", OMSymbol.ARITH1_ABS);
		functionSymbolMap.put("divide", OMSymbol.ARITH1_DIVIDE);
		functionSymbolMap.put("gcd", OMSymbol.ARITH1_GCD);
		functionSymbolMap.put("minus", OMSymbol.ARITH1_MINUS);
		functionSymbolMap.put("plus", OMSymbol.ARITH1_PLUS);
		functionSymbolMap.put("pow", OMSymbol.ARITH1_POWER);
		functionSymbolMap.put("root", OMSymbol.ARITH1_ROOT);
		functionSymbolMap.put("sqrt", OMSymbol.ARITH1_ROOT);
		functionSymbolMap.put("times", OMSymbol.ARITH1_TIMES);
		functionSymbolMap.put("unaryminus", OMSymbol.ARITH1_UNARY_MINUS);

		functionSymbolMap.put("convertToBinary", OMSymbol.BINARYJACK_CONVERTTOBINARY);
		functionSymbolMap.put("equalsBinary", OMSymbol.BINARYJACK_EQUALSBINARY);

		functionSymbolMap.put("evaluateInR", OMSymbol.CASJACK_EVALUATEINR);
		functionSymbolMap.put("evaluateInSage", OMSymbol.CASJACK_EVALUATEINSAGE);
		functionSymbolMap.put("evaluateInSymja", OMSymbol.CASJACK_EVALUATEINSYMJA);
		
		functionSymbolMap.put("eval", OMSymbol.EVALJACK_EVAL);
		functionSymbolMap.put("evalcplx", OMSymbol.POLYNOMIAL1_EXPAND);
		functionSymbolMap.put("evalEq", OMSymbol.EVALJACK_EVALEQ);
		functionSymbolMap.put("evalpolynomial", OMSymbol.EVALJACK_EVALPOLYNOMIAL);
		functionSymbolMap.put("evalPolynomialCplx", OMSymbol.EVALJACK_EVALPOLYNOMIALCPLX);
		functionSymbolMap.put("evalterm2", OMSymbol.EVALJACK_EVALTERM2);

		functionSymbolMap.put("remainder", OMSymbol.INTEGER1_REMAINDER);

		functionSymbolMap.put("equalBasis", OMSymbol.LINALGJACK_EQUALBASIS);
		functionSymbolMap.put("isLinearlyIndependent", OMSymbol.LINALGJACK_ISLINEARLYINDEPENDENT);
		functionSymbolMap.put("randomMatrixEigenvalue", OMSymbol.LINALGJACK_RANDOMMATRIXEIGENVALUE);
		functionSymbolMap.put("randomDiagonalizableMatrix", OMSymbol.LINALGJACK_RANDOMMATRIXEIGENVALUE);
		functionSymbolMap.put("randomMatrixRank", OMSymbol.LINALGJACK_RANDOMMATRIXRANK);
		functionSymbolMap.put("randomEchelonizableMatrix", OMSymbol.LINALGJACK_RANDOMMATRIXRANK);
		

		functionSymbolMap.put("matrix", OMSymbol.LINALG2_MATRIX);
		functionSymbolMap.put("matrixrow", OMSymbol.LINALG2_MATRIXROW);
		functionSymbolMap.put("vector", OMSymbol.LINALG2_VECTOR);

		functionSymbolMap.put("list", OMSymbol.LIST1_LIST);

		functionSymbolMap.put("booleanand", OMSymbol.LOGIC1_AND);
		functionSymbolMap.put("ifthenelse", OMSymbol.LOGICJACK_IFTHENELSE);
		functionSymbolMap.put("booleannot", OMSymbol.LOGIC1_NOT);
		functionSymbolMap.put("booleanor", OMSymbol.LOGIC1_OR);

		functionSymbolMap.put("countBasicOperations", OMSymbol.OPENMATHJACK_COUNTBASICOPERATIONS);
		functionSymbolMap.put("countNodes", OMSymbol.OPENMATHJACK_COUNTNODES);
		functionSymbolMap.put("getDenominator", OMSymbol.OPENMATHJACK_GETDENOMINATOR);
		functionSymbolMap.put("getNumerator", OMSymbol.OPENMATHJACK_GETNUMERATOR);
		functionSymbolMap.put("isFraction", OMSymbol.OPENMATHJACK_ISFRACTION);
		
		functionSymbolMap.put("factorOf", OMSymbol.POLY_COEFFICIENT);
		functionSymbolMap.put("deg", OMSymbol.POLY_DEGREE_WRT);

		functionSymbolMap.put("equalsemisem", OMSymbol.RELATION1_EQ);
		functionSymbolMap.put("equalsExpr", OMSymbol.POLYNOMIALJACK_EQUALSEXPR);
		functionSymbolMap.put("dependsOn", OMSymbol.POLYNOMIALJACK_DEPENDSON);
		functionSymbolMap.put("derive", OMSymbol.POLYNOMIALJACK_DERIVE);
		functionSymbolMap.put("integrate", OMSymbol.POLYNOMIALJACK_INTEGRATE);
		functionSymbolMap.put("numberOfVariables", OMSymbol.POLYNOMIALJACK_NUMBEROFVARIABLES);
		
		functionSymbolMap.put("expand", OMSymbol.POLYNOMIAL1_EXPAND);
		
		functionSymbolMap.put("random", OMSymbol.RANDOMJACK_RANDOM);
		functionSymbolMap.put("randombetween", OMSymbol.RANDOMJACK_RANDOMBETWEEN);

		functionSymbolMap.put("equal", OMSymbol.RELATION1_EQ);
		functionSymbolMap.put("greaterthanorequal", OMSymbol.RELATION1_GEQ);
		functionSymbolMap.put("greaterthan", OMSymbol.RELATION1_GT);
		functionSymbolMap.put("lessthanorequal", OMSymbol.RELATION1_LEQ);
		functionSymbolMap.put("lessthan", OMSymbol.RELATION1_LT);
		functionSymbolMap.put("notequal", OMSymbol.RELATION1_NEQ);

		functionSymbolMap.put("ceil", OMSymbol.ROUNDING1_CEILING);
		functionSymbolMap.put("floor", OMSymbol.ROUNDING1_FLOOR);
		functionSymbolMap.put("round", OMSymbol.ROUNDING1_ROUND);
		
		functionSymbolMap.put("rint", OMSymbol.ROUNDING_JACK);

		functionSymbolMap.put("chooseFromComplement", OMSymbol.SETJACK_CHOOSEFROMCOMPLEMENT);
		functionSymbolMap.put("getFromOrderedSet", OMSymbol.SETJACK_GETFROMORDEREDSET);
		functionSymbolMap.put("getFromSet", OMSymbol.SETJACK_GETFROMSET);
		functionSymbolMap.put("getRandomFromSet", OMSymbol.SETJACK_GETRANDOMFROMSET);
		
		functionSymbolMap.put("set", OMSymbol.SET1_SET);
		functionSymbolMap.put("size", OMSymbol.SET1_SIZE);

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
		functionSymbolMap.put("matches", OMSymbol.STRINGJACK_MATCHES);
		functionSymbolMap.put("startsWith", OMSymbol.STRINGJACK_STARTSWITH);
		functionSymbolMap.put("replace", OMSymbol.STRINGJACK_REPLACE);
		functionSymbolMap.put("substring", OMSymbol.STRINGJACK_SUBSTRING);
		functionSymbolMap.put("toLowerCase", OMSymbol.STRINGJACK_TOLOWERCASE);
		functionSymbolMap.put("toUpperCase", OMSymbol.STRINGJACK_TOUPPERCASE);
		functionSymbolMap.put("trim", OMSymbol.STRINGJACK_TRIM);

		functionSymbolMap.put("isEmpty", OMSymbol.TESTTERMINALJACK_ISEMPTY);
		functionSymbolMap.put("isNumber", OMSymbol.TESTTERMINALJACK_ISNUMBER);
		functionSymbolMap.put("isPolynomial", OMSymbol.TESTTERMINALJACK_ISPOLYNOMIAL);
		functionSymbolMap.put("isSet", OMSymbol.TESTTERMINALJACK_ISSET);

		functionSymbolMap.put("toDegree", OMSymbol.TRANSCJACK_TODEGREE);
		functionSymbolMap.put("toDegrees", OMSymbol.TRANSCJACK_TODEGREE);
		functionSymbolMap.put("toRadian", OMSymbol.TRANSCJACK_TORADIAN);
		functionSymbolMap.put("toRadians", OMSymbol.TRANSCJACK_TORADIAN);

		functionSymbolMap.put("acos", OMSymbol.TRANSC1_ARCCOS);
		functionSymbolMap.put("asin", OMSymbol.TRANSC1_ARCSIN);
		functionSymbolMap.put("atan", OMSymbol.TRANSC1_ARCTAN);
		functionSymbolMap.put("cos", OMSymbol.TRANSC1_COS);
		functionSymbolMap.put("exp", OMSymbol.TRANSC1_EXP);
		functionSymbolMap.put("log", OMSymbol.TRANSC1_LOG);
		functionSymbolMap.put("sin", OMSymbol.TRANSC1_SIN);
		functionSymbolMap.put("tan", OMSymbol.TRANSC1_TAN);

		functionSymbolMap.put("atan2", OMSymbol.TRANSC2_ARCTAN2);
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
