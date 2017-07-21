package de.uni_due.s3.evaluator.core.functionData;

import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * This Class is a Dataholder for all the OMSymbols in this Evaluator.
 * 
 * @author spobel
 */
public class OMSymbol {

	public static final OMS ARITH1_ABS = OMCreator.createOMS("arith1", "abs");
	public static final OMS ARITH1_DIVIDE = OMCreator.createOMS("arith1", "divide");
	public static final OMS ARITH1_GCD = OMCreator.createOMS("arith1", "gcd");
	public static final OMS ARITH1_MINUS = OMCreator.createOMS("arith1", "minus");
	public static final OMS ARITH1_PLUS = OMCreator.createOMS("arith1", "plus");
	public static final OMS ARITH1_POWER = OMCreator.createOMS("arith1", "power");
	public static final OMS ARITH1_ROOT = OMCreator.createOMS("arith1", "root");
	public static final OMS ARITH1_TIMES = OMCreator.createOMS("arith1", "times");
	public static final OMS ARITH1_UNARY_MINUS = OMCreator.createOMS("arith1", "unary_minus");

	public static final OMS ARITHJACK_MIN = OMCreator.createOMS("arithJACK", "min");
	public static final OMS ARITHJACK_MAX = OMCreator.createOMS("arithJACK", "max");

	public static final OMS BINARYJACK_CONVERTTOBINARY = OMCreator.createOMS("binaryJACK", "convertToBinary");
	public static final OMS BINARYJACK_EQUALSBINARY = OMCreator.createOMS("binaryJACK", "equalsBinary");

	public static final OMS CASJACK_EVALUATEINR = OMCreator.createOMS("casJACK", "evaluateInR");
	public static final OMS CASJACK_EVALUATEINSAGE = OMCreator.createOMS("casJACK", "evaluateInSage");
	public static final OMS CASJACK_EVALUATEINSYMJA = OMCreator.createOMS("casJACK", "evaluateInSymja");

	public static final OMS COMPLEXJACK_EVALCPLX = OMCreator.createOMS("complexJACK", "evalcplx");

	public static final OMS EVALJACK_EVAL = OMCreator.createOMS("evalJACK", "eval");

	public static final OMS INTEGER1_REMAINDER = OMCreator.createOMS("integer1", "remainder");

	public static final OMS LINALG2_VECTOR = OMCreator.createOMS("linalg2", "vector");
	public static final OMS LINALG2_MATRIX = OMCreator.createOMS("linalg2", "matrix");
	public static final OMS LINALG2_MATRIXROW = OMCreator.createOMS("linalg2", "matrixrow");
	public static final OMS LINALGJACK_EQUALBASIS = OMCreator.createOMS("linalgJACK", "equalBasis");
	public static final OMS LINALGJACK_ISLINEARLYINDEPENDENT = OMCreator.createOMS("linalgJACK",
			"isLinearlyIndependent");
	public static final OMS LINALGJACK_RANDOMMATRIXEIGENVALUE = OMCreator.createOMS("linalgJACK",
			"randomMatrixEigenvalue");
	public static final OMS LINALGJACK_RANDOMMATRIXRANK = OMCreator.createOMS("linalgJACK", "randomMatrixRank");

	public static final OMS LOGIC1_OR = OMCreator.createOMS("logic1", "or");
	public static final OMS LOGIC1_AND = OMCreator.createOMS("logic1", "and");
	public static final OMS LOGIC1_TRUE = OMCreator.createOMS("logic1", "true");
	public static final OMS LOGIC1_FALSE = OMCreator.createOMS("logic1", "false");
	public static final OMS LOGIC1_NOT = OMCreator.createOMS("logic1", "not");
	public static final OMS LOGICJACK_IFTHENELSE = OMCreator.createOMS("logicJACK", "ifthenelse");

	public static final OMS NUMS1_NAN = OMCreator.createOMS("nums1", "NaN");
	public static final OMS NUMS1_E = OMCreator.createOMS("nums1", "e");
	public static final OMS NUMS1_I = OMCreator.createOMS("nums1", "i");
	public static final OMS NUMS1_INFINITY = OMCreator.createOMS("nums1", "infinity");
	public static final OMS NUMS1_PI = OMCreator.createOMS("nums1", "pi");
	public static final OMS NUMS1_RATIONAL = OMCreator.createOMS("nums1", "rational");

	public static final OMS OPENMATHJACK_COUNTBASICOPERATIONS = OMCreator.createOMS("openmathJACK",
			"countBasicOperations");
	public static final OMS OPENMATHJACK_COUNTNODES = OMCreator.createOMS("openmathJACK", "countNodes");
	public static final OMS OPENMATHJACK_GETDENOMINATOR = OMCreator.createOMS("openmathJflrACK", "getDenominator");
	public static final OMS OPENMATHJACK_GETNUMERATOR = OMCreator.createOMS("openmathJACK", "getNumerator");
	public static final OMS OPENMATHJACK_RANDOM = OMCreator.createOMS("openmathJACK", "random");

	public static final OMS POLYNOMIAL1_DEGREE = OMCreator.createOMS("polynomial1", "degree");
	public static final OMS POLYNOMIAL1_EXPAND = OMCreator.createOMS("polynomial1", "expand");
	public static final OMS POLY_DEGREE = OMCreator.createOMS("poly", "degree");
	public static final OMS POLY_DEGREE_WRT = OMCreator.createOMS("poly", "degree_wrt");
	public static final OMS POLYNOMIALJACK_DEPENDSON = OMCreator.createOMS("polynomialJACK", "dependsOn");
	public static final OMS POLYNOMIALJACK_DERIVE = OMCreator.createOMS("polynomialJACK", "derive");
	public static final OMS POLYNOMIALJACK_FACTOROF = OMCreator.createOMS("polynomialJACK", "factorOf");
	public static final OMS POLYNOMIALJACK_INTEGRATE = OMCreator.createOMS("polynomialJACK", "integrate");
	public static final OMS POLYNOMIALJACK_NUMBEROFVARIABLES = OMCreator.createOMS("polynomialJACK",
			"numberOfVariables");
	public static final OMS POLYNOMIALJACK_EQUALSEMISEM = OMCreator.createOMS("polynomialJACK", "equalssemisem");
	public static final OMS POLYNOMIALJACK_EQUALSEXPR = OMCreator.createOMS("polynomialJACK", "equalsExpr");
	public static final OMS POLYNOMIALJACK_EVALEQ = OMCreator.createOMS("polynomialJACK", "evalEq");
	public static final OMS POLYNOMIALJACK_EVALPOLYNOMIAL = OMCreator.createOMS("polynomialJACK", "evalPolynomial");
	public static final OMS POLYNOMIALJACK_EVALPOLYNOMIALCPLX = OMCreator.createOMS("polynomialJACK",
			"evalPolynomialCplx");
	public static final OMS POLYNOMIALJACK_EVALTERM2 = OMCreator.createOMS("polynomialJACK", "evalterm2");

	public static final OMS RELATION1_LT = OMCreator.createOMS("relation1", "lt");
	public static final OMS RELATION1_LEQ = OMCreator.createOMS("relation1", "leq");
	public static final OMS RELATION1_GT = OMCreator.createOMS("relation1", "gt");
	public static final OMS RELATION1_GEQ = OMCreator.createOMS("relation1", "geq");
	public static final OMS RELATION1_EQ = OMCreator.createOMS("relation1", "eq");
	public static final OMS RELATION1_NEQ = OMCreator.createOMS("relation1", "neq");

	public static final OMS ROUNDING1_CEILING = OMCreator.createOMS("rounding1", "ceiling");
	public static final OMS ROUNDING1_FLOOR = OMCreator.createOMS("rounding1", "floor");
	public static final OMS ROUNDING1_ROUND = OMCreator.createOMS("rounding1", "round");

	public static final OMS SET1_SET = OMCreator.createOMS("set1", "set");
	public static final OMS SET1_SIZE = OMCreator.createOMS("set1", "size");
	public static final OMS SETJACK_CHOOSEFROMCOMPLEMENT = OMCreator.createOMS("setJACK", "chooseFromComplement");
	public static final OMS SETJACK_GETFROMORDEREDSET = OMCreator.createOMS("setJACK", "getFromOrderedSet");
	public static final OMS SETJACK_GETFROMSET = OMCreator.createOMS("setJACK", "getFromSet");

	public static final OMS STRINGJACK_CHARAT = OMCreator.createOMS("stringJACK", "charAt");
	public static final OMS STRINGJACK_COMPARETO = OMCreator.createOMS("stringJACK", "compareTo");
	public static final OMS STRINGJACK_COMPARETOIGNORECASE = OMCreator.createOMS("stringJACK", "compareToIgnoreCase");
	public static final OMS STRINGJACK_CONCAT = OMCreator.createOMS("stringJACK", "concat");
	public static final OMS STRINGJACK_ENDSWITH = OMCreator.createOMS("stringJACK", "endsWith");
	public static final OMS STRINGJACK_EQUALS = OMCreator.createOMS("stringJACK", "equals");
	public static final OMS STRINGJACK_EQUALSIGNORECASE = OMCreator.createOMS("stringJACK", "equalsIgnoreCase");
	public static final OMS STRINGJACK_INDEXOF = OMCreator.createOMS("stringJACK", "indexOf");
	public static final OMS STRINGJACK_LASTINDEXOF = OMCreator.createOMS("stringJACK", "lastIndexOf");
	public static final OMS STRINGJACK_LENGTH = OMCreator.createOMS("stringJACK", "length");
	public static final OMS STRINGJACK_REPLACE = OMCreator.createOMS("stringJACK", "replace");
	public static final OMS STRINGJACK_STARTSWITH = OMCreator.createOMS("stringJACK", "startsWith");
	public static final OMS STRINGJACK_SUBSTRING = OMCreator.createOMS("stringJACK", "substring");
	public static final OMS STRINGJACK_TOLOWERCASE = OMCreator.createOMS("stringJACK", "toLowerCase");
	public static final OMS STRINGJACK_TOUPPERCASE = OMCreator.createOMS("stringJACK", "toUpperCase");
	public static final OMS STRINGJACK_TRIM = OMCreator.createOMS("stringJACK", "trim");
	public static final OMS STRINGJACK_MATCHES = OMCreator.createOMS("stringJACK", "matches");
	
	public static final OMS TESTTERMINALJACK_ISEMPTY = OMCreator.createOMS("testterminalJACK", "isEmpty");
	public static final OMS TESTTERMINALJACK_ISPOLYNOMIAL = OMCreator.createOMS("testterminalJACK", "isPolynomial");
	public static final OMS TESTTERMINALJACK_ISSET = OMCreator.createOMS("testterminalJACK", "isSet");
	public static final OMS TESTTERMINALJACK_ISNUMBER = OMCreator.createOMS("testterminalJACK", "isNumber");
	public static final OMS TESTTERMINALJACK_ISFRACTION = OMCreator.createOMS("testterminalJACK", "isFraction");
	
	public static final OMS TRANSCJACK_TODEGREE = OMCreator.createOMS("transcjack", "toDegree");
	public static final OMS TRANSCJACK_TORADIAN = OMCreator.createOMS("transcjack", "toRadian");
	
	public static final OMS TRANSC1_ARCCOS = OMCreator.createOMS("transc1", "arccos");
	public static final OMS TRANSC1_ARCSIN = OMCreator.createOMS("transc1", "arcsin");
	public static final OMS TRANSC1_ARCTAN = OMCreator.createOMS("transc1", "arctan");
	public static final OMS TRANSC1_COS = OMCreator.createOMS("transc1", "cos");
	public static final OMS TRANSC1_EXP = OMCreator.createOMS("transc1", "exp");
	public static final OMS TRANSC1_LOG = OMCreator.createOMS("transc1", "log");
	public static final OMS TRANSC1_SIN = OMCreator.createOMS("transc1", "sin");
	public static final OMS TRANSC1_TAN = OMCreator.createOMS("transc1", "tan");

	public static final OMS TRANSC2_ARCTAN2 = OMCreator.createOMS("transc2", "arctan");
	
	public static final OMS SET1_EMPTYSET = OMCreator.createOMS("set1", "emptyset");
	
	public static final OMS EDITOR1_INPUT_BOX = OMCreator.createOMS("set1", "emptyset");
	

}
