package de.uni_due.s3.evaluator2.dictionaries;

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
	 * Symbols listed
	 * 
	 * If another Function for this Evaluator is written you have to add it here and
	 * in OMSFunctionDictionary
	 * 
	 * Also add the OMSymbol in OMSymbol, so referencing this new Function can be
	 * made easy!
	 */
	private OMSEvaluatorSyntaxDictionary() {

		functionSymbolMap.put("IEEEremainder", OMSymbol.ARITHJACK_IEEEREMAINDER);
		functionSymbolMap.put("max", OMSymbol.ARITHJACK_MAX);
		functionSymbolMap.put("min", OMSymbol.ARITHJACK_MIN);
		functionSymbolMap.put("shorten", OMSymbol.ARITHJACK_SHORTEN);

		functionSymbolMap.put("abs", OMSymbol.ARITH1_ABS);
		functionSymbolMap.put("divide", OMSymbol.ARITH1_DIVIDE);
		functionSymbolMap.put("gcd", OMSymbol.ARITH1_GCD);
		functionSymbolMap.put("minus", OMSymbol.ARITH1_MINUS);
		functionSymbolMap.put("plus", OMSymbol.ARITH1_PLUS);
		functionSymbolMap.put("pow", OMSymbol.ARITH1_POWER);
		functionSymbolMap.put("root", OMSymbol.ARITH1_ROOT);
		// Deprecated
		functionSymbolMap.put("sqrt", OMSymbol.ARITH1_ROOT);
		functionSymbolMap.put("times", OMSymbol.ARITH1_TIMES);
		functionSymbolMap.put("unaryMinus", OMSymbol.ARITH1_UNARY_MINUS);
		functionSymbolMap.put("unaryPlus", OMSymbol.ARITH1_UNARY_PLUS);

		// The evaluator has no command for these function, because the om-structure
		// will be created with the formeleditor in jack
		functionSymbolMap.put("product", OMSymbol.ARITH1_PRODUCT);
		functionSymbolMap.put("sum", OMSymbol.ARITH1_SUM);

		functionSymbolMap.put("convertToBinary", OMSymbol.BINARYJACK_CONVERTTOBINARY);
		functionSymbolMap.put("equalsBinary", OMSymbol.BINARYJACK_EQUALSBINARY);

		functionSymbolMap.put("evaluateInR", OMSymbol.CASJACK_EVALUATEINR);
		functionSymbolMap.put("evaluateInSage", OMSymbol.CASJACK_EVALUATEINSAGE);
		
		functionSymbolMap.put("complex", OMSymbol.COMPLEX1_CARTESIAN_COMPLEX);

		// TODO: should the integral calculus function be added?

		functionSymbolMap.put("tuple", OMSymbol.ECC_TUPLE);

		functionSymbolMap.put("eval", OMSymbol.EVALJACK_EVAL);
		
		//DEPRECATED: Redirecting to Expand
		functionSymbolMap.put("evalcplx", OMSymbol.POLYNOMIAL1_EXPAND); 
		
		functionSymbolMap.put("evalEq", OMSymbol.EVALJACK_EVALEQ);
		functionSymbolMap.put("evalpolynomial", OMSymbol.EVALJACK_EVALPOLYNOMIAL);
		functionSymbolMap.put("evalPolynomialCplx", OMSymbol.EVALJACK_EVALPOLYNOMIALCPLX);
		functionSymbolMap.put("evalterm2", OMSymbol.EVALJACK_EVALTERM2);

		functionSymbolMap.put("remainder", OMSymbol.INTEGER1_REMAINDER);
		functionSymbolMap.put("factorial", OMSymbol.INTEGER1_FACTORIAL);

		functionSymbolMap.put("equalBasis", OMSymbol.LINALGJACK_EQUALBASIS);
		functionSymbolMap.put("isLinearlyIndependent", OMSymbol.LINALGJACK_ISLINEARLYINDEPENDENT);
		functionSymbolMap.put("randomMatrixEigenvalue", OMSymbol.LINALGJACK_RANDOMMATRIXEIGENVALUE);
		functionSymbolMap.put("randomDiagonalizableMatrix", OMSymbol.LINALGJACK_RANDOMMATRIXEIGENVALUE);
		functionSymbolMap.put("randomMatrixRank", OMSymbol.LINALGJACK_RANDOMMATRIXRANK);
		functionSymbolMap.put("randomEchelonizableMatrix", OMSymbol.LINALGJACK_RANDOMMATRIXRANK);

		functionSymbolMap.put("det", OMSymbol.LINALG1_DETERMINANT);

		functionSymbolMap.put("matrix", OMSymbol.LINALG2_MATRIX);
		functionSymbolMap.put("matrixrow", OMSymbol.LINALG2_MATRIXROW);
		functionSymbolMap.put("vector", OMSymbol.LINALG2_VECTOR);

		functionSymbolMap.put("chooseFromComplement", OMSymbol.LISTJACK_CHOOSEFROMCOMPLEMENT);
		
		functionSymbolMap.put("gen_sentence_trans", OMSymbol.GEN_SENTENCE_TRANS);
		
		functionSymbolMap.put("getFromList", OMSymbol.LISTJACK_GETFROMLIST);
		functionSymbolMap.put("getFromOrderedList", OMSymbol.LISTJACK_GETFROMORDEREDLIST);
		functionSymbolMap.put("getRandomFromList", OMSymbol.LISTJACK_GETRANDOMFROMSET);
		// Deprecated
		functionSymbolMap.put("getFromOrderedSet", OMSymbol.LISTJACK_GETFROMORDEREDLIST);
		// Deprecated
		functionSymbolMap.put("getFromSet", OMSymbol.LISTJACK_GETFROMLIST);

		functionSymbolMap.put("list", OMSymbol.LIST1_LIST);

		functionSymbolMap.put("appendToList", OMSymbol.LIST2_APPEND);
		functionSymbolMap.put("sizeOfList", OMSymbol.LIST2_SIZE);

		functionSymbolMap.put("and", OMSymbol.LOGIC1_AND);
		functionSymbolMap.put("ifthenelse", OMSymbol.LOGICJACK_IFTHENELSE);
		functionSymbolMap.put("not", OMSymbol.LOGIC1_NOT);
		functionSymbolMap.put("or", OMSymbol.LOGIC1_OR);

		functionSymbolMap.put("mcindex", OMSymbol.MCJACK_MCINDEX);

		functionSymbolMap.put("conste", OMSymbol.NUMS1_E);
		functionSymbolMap.put("infinity", OMSymbol.NUMS1_INFINITY);
		functionSymbolMap.put("imaginary", OMSymbol.NUMS1_I);
		functionSymbolMap.put("constpi", OMSymbol.NUMS1_PI);
		functionSymbolMap.put("rational", OMSymbol.NUMS1_RATIONAL);

		functionSymbolMap.put("countBasicOperations", OMSymbol.OPENMATHJACK_COUNTBASICOPERATIONS);
		functionSymbolMap.put("countNodes", OMSymbol.OPENMATHJACK_COUNTNODES);
		functionSymbolMap.put("getDenominator", OMSymbol.OPENMATHJACK_GETDENOMINATOR);
		functionSymbolMap.put("getNumerator", OMSymbol.OPENMATHJACK_GETNUMERATOR);

		functionSymbolMap.put("factorOf", OMSymbol.POLY_COEFFICIENT);
		functionSymbolMap.put("deg", OMSymbol.POLY_DEGREE_WRT);

		//DEPRECATED: Redirecting to Equal
		functionSymbolMap.put("equalsemisem", OMSymbol.RELATION1_EQ);
		
		functionSymbolMap.put("equalsExpr", OMSymbol.POLYNOMIALJACK_EQUALSEXPR);
		functionSymbolMap.put("dependsOn", OMSymbol.POLYNOMIALJACK_DEPENDSON);
		functionSymbolMap.put("derive", OMSymbol.POLYNOMIALJACK_DERIVE);
		functionSymbolMap.put("integrate", OMSymbol.POLYNOMIALJACK_INTEGRATE);
		functionSymbolMap.put("numberOfVariables", OMSymbol.POLYNOMIALJACK_NUMBEROFVARIABLES);

		functionSymbolMap.put("expand", OMSymbol.POLYNOMIAL1_EXPAND);

		functionSymbolMap.put("random", OMSymbol.RANDOMJACK_RANDOM);
		functionSymbolMap.put("randomBetween", OMSymbol.RANDOMJACK_RANDOMBETWEEN);
		functionSymbolMap.put("randomIntegerBetween", OMSymbol.RANDOMJACK_RANDOMINTEGERBETWEEN);

		functionSymbolMap.put("equal", OMSymbol.RELATION1_EQ);
		functionSymbolMap.put("greaterThanOrEqual", OMSymbol.RELATION1_GEQ);
		functionSymbolMap.put("greaterThan", OMSymbol.RELATION1_GT);
		functionSymbolMap.put("lessThanOrEqual", OMSymbol.RELATION1_LEQ);
		functionSymbolMap.put("lessThan", OMSymbol.RELATION1_LT);
		functionSymbolMap.put("notEqual", OMSymbol.RELATION1_NEQ);

		functionSymbolMap.put("ceil", OMSymbol.ROUNDING1_CEILING);
		functionSymbolMap.put("floor", OMSymbol.ROUNDING1_FLOOR);
		functionSymbolMap.put("round", OMSymbol.ROUNDING1_ROUND);

		functionSymbolMap.put("rint", OMSymbol.ROUNDING_JACK);

		functionSymbolMap.put("equalSet", OMSymbol.SETJACK_EQUALSET);

		functionSymbolMap.put("emptySet", OMSymbol.SET1_EMPTYSET);
		functionSymbolMap.put("isElementOf", OMSymbol.SET1_IN);
		functionSymbolMap.put("intersect", OMSymbol.SET1_INTERSECT);
		functionSymbolMap.put("isNotElementOf", OMSymbol.SET1_NOTIN);
		functionSymbolMap.put("set", OMSymbol.LIST1_LIST);
		functionSymbolMap.put("complement", OMSymbol.SET1_SETDIFF);
		functionSymbolMap.put("sizeOfSet", OMSymbol.SET1_SIZE);
		functionSymbolMap.put("isSubsetOf", OMSymbol.SET1_SUBSET);
		functionSymbolMap.put("unite", OMSymbol.SET1_UNION);

		functionSymbolMap.put("cc", OMSymbol.SETNAME1_C);
		functionSymbolMap.put("nn", OMSymbol.SETNAME1_N);
		functionSymbolMap.put("pp", OMSymbol.SETNAME1_P);
		functionSymbolMap.put("qq", OMSymbol.SETNAME1_Q);
		functionSymbolMap.put("rr", OMSymbol.SETNAME1_R);
		functionSymbolMap.put("zz", OMSymbol.SETNAME1_Z);

		functionSymbolMap.put("querySparql", OMSymbol.SPARQLJACK_QUERYSPARQL);

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
		functionSymbolMap.put("levenshteinDistance", OMSymbol.STRINGJACK_LEVENSHTEINDISTANCE);
		functionSymbolMap.put("matches", OMSymbol.STRINGJACK_MATCHES);
		functionSymbolMap.put("startsWith", OMSymbol.STRINGJACK_STARTSWITH);
		functionSymbolMap.put("replace", OMSymbol.STRINGJACK_REPLACE);
		functionSymbolMap.put("substring", OMSymbol.STRINGJACK_SUBSTRING);
		functionSymbolMap.put("toLowerCase", OMSymbol.STRINGJACK_TOLOWERCASE);
		functionSymbolMap.put("toUpperCase", OMSymbol.STRINGJACK_TOUPPERCASE);
		functionSymbolMap.put("trim", OMSymbol.STRINGJACK_TRIM);

		functionSymbolMap.put("isEmpty", OMSymbol.TESTTERMINALJACK_ISEMPTY);
		functionSymbolMap.put("isFraction", OMSymbol.TESTTERMINALJACK_ISFRACTION);
		functionSymbolMap.put("isIntegerNumber", OMSymbol.TESTTERMINALJACK_ISINTEGERNUMBER);
		functionSymbolMap.put("isMatrix", OMSymbol.TESTTERMINALJACK_ISMATRIX);
		functionSymbolMap.put("isMNMatrix", OMSymbol.TESTTERMINALJACK_ISMNMATRIX);
		functionSymbolMap.put("isNaturalNumber", OMSymbol.TESTTERMINALJACK_ISNATURALNUMBER);
		functionSymbolMap.put("isNPolynomial", OMSymbol.TESTTERMINALJACK_ISNPOLYNOMIAL);
		functionSymbolMap.put("isPolynomial", OMSymbol.TESTTERMINALJACK_ISPOLYNOMIAL);
		functionSymbolMap.put("isRationalNumber", OMSymbol.TESTTERMINALJACK_ISRATIONALNUMBER);
		functionSymbolMap.put("isRealNumber", OMSymbol.TESTTERMINALJACK_ISREALNUMBER);
		functionSymbolMap.put("isNumber", OMSymbol.TESTTERMINALJACK_ISREALNUMBER);
		functionSymbolMap.put("isSet", OMSymbol.TESTTERMINALJACK_ISSET);
		functionSymbolMap.put("isTuple", OMSymbol.TESTTERMINALJACK_ISTUPLE);
		functionSymbolMap.put("isNTuple", OMSymbol.TESTTERMINALJACK_ISNTUPLE);
		
		functionSymbolMap.put("toDegree", OMSymbol.TRANSCJACK_TODEGREE);
		// Deprecated
		functionSymbolMap.put("toDegrees", OMSymbol.TRANSCJACK_TODEGREE);
		functionSymbolMap.put("toRadian", OMSymbol.TRANSCJACK_TORADIAN);
		// Deprecated
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
