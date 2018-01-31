package de.uni_due.s3.evaluator2.core.dictionaries;

import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * This Class is a Dataholder for all the OMSymbols in this Evaluator.
 * 
 * @author spobel
 */
public class OMSymbol {

	public static final OMS ARITHJACK_IEEEREMAINDER = OMCreator.createOMS("arithJACK", "ieeeremainder");
	public static final OMS ARITHJACK_MAX = OMCreator.createOMS("arithJACK", "max");
	public static final OMS ARITHJACK_MIN = OMCreator.createOMS("arithJACK", "min");
	public static final OMS ARITHJACK_SHORTEN = OMCreator.createOMS("arithJACK", "shorten");

	public static final OMS ARITH1_ABS = OMCreator.createOMS("arith1", "abs");
	public static final OMS ARITH1_DIVIDE = OMCreator.createOMS("arith1", "divide");
	public static final OMS ARITH1_GCD = OMCreator.createOMS("arith1", "gcd");
	public static final OMS ARITH1_MINUS = OMCreator.createOMS("arith1", "minus");
	public static final OMS ARITH1_PLUS = OMCreator.createOMS("arith1", "plus");
	public static final OMS ARITH1_POWER = OMCreator.createOMS("arith1", "power");
	public static final OMS ARITH1_ROOT = OMCreator.createOMS("arith1", "root");
	public static final OMS ARITH1_TIMES = OMCreator.createOMS("arith1", "times");
	public static final OMS ARITH1_UNARY_MINUS = OMCreator.createOMS("arith1", "unary_minus");
	public static final OMS ARITH1_UNARY_PLUS = OMCreator.createOMS("arith1", "unary_plus");
	public static final OMS ARITH1_PRODUCT = OMCreator.createOMS("arith1", "product");
	public static final OMS ARITH1_SUM = OMCreator.createOMS("arith1", "sum");

	public static final OMS BINARYJACK_CONVERTTOBINARY = OMCreator.createOMS("binaryJACK", "convertToBinary");
	public static final OMS BINARYJACK_EQUALSBINARY = OMCreator.createOMS("binaryJACK", "equalsBinary");

	public static final OMS CASJACK_EVALUATEINR = OMCreator.createOMS("casJACK", "evaluateInR");
	public static final OMS CASJACK_EVALUATEINSAGE = OMCreator.createOMS("casJACK", "evaluateInSage");

	public static final OMS CALCULUS1_INT = OMCreator.createOMS("calculus1", "int");
	public static final OMS CALCULUS1_DEFINT = OMCreator.createOMS("calculus1", "defint");
	
	public static final OMS COMPLEX1_CARTESIAN_COMPLEX = OMCreator.createOMS("complex1", "complex_cartesian");

	public static final OMS EDITOR1_INPUT_BOX = OMCreator.createOMS("editor1", "input_box");

	public static final OMS EVALJACK_EVAL = OMCreator.createOMS("evalJACK", "eval");
	public static final OMS EVALJACK_EVALEQ = OMCreator.createOMS("evalJACK", "evalEq");
	public static final OMS EVALJACK_EVALPOLYNOMIAL = OMCreator.createOMS("evalJACK", "evalpolynomial");
	public static final OMS EVALJACK_EVALPOLYNOMIALCPLX = OMCreator.createOMS("evalJACK", "evalPolynomialCplx");
	public static final OMS EVALJACK_EVALTERM2 = OMCreator.createOMS("evalJACK", "evalterm2");

	public static final OMS ECC_TUPLE = OMCreator.createOMS("ecc", "Tuple"); // T is big, see OpenMath 2.0 ecc.Tuple

	public static final OMS FNS1_LAMBDA = OMCreator.createOMS("fns1", "lambda");

	public static final OMS INTEGER1_REMAINDER = OMCreator.createOMS("integer1", "remainder");
	public static final OMS INTEGER1_FACTORIAL = OMCreator.createOMS("integer1", "factorial");

	public static final OMS INTERVAL1_INTERVALCC = OMCreator.createOMS("interval1", "interval_cc");
	public static final OMS INTERVAL1_INTERVALCO = OMCreator.createOMS("interval1", "interval_co");
	public static final OMS INTERVAL1_INTERVALOC = OMCreator.createOMS("interval1", "interval_oc");
	public static final OMS INTERVAL1_INTERVALOO = OMCreator.createOMS("interval1", "interval_oo");
	public static final OMS INTERVAL1_INTEGER_INTERVAL = OMCreator.createOMS("interval1", "integer_interval");

	public static final OMS LINALGJACK_EQUALBASIS = OMCreator.createOMS("linalgJACK", "equalBasis");
	public static final OMS LINALGJACK_ISLINEARLYINDEPENDENT = OMCreator.createOMS("linalgJACK",
			"isLinearlyIndependent");
	public static final OMS LINALGJACK_RANDOMMATRIXEIGENVALUE = OMCreator.createOMS("linalgJACK",
			"randomMatrixEigenvalue");
	public static final OMS LINALGJACK_RANDOMMATRIXRANK = OMCreator.createOMS("linalgJACK", "randomMatrixRank");

	public static final OMS LINALG1_DETERMINANT = OMCreator.createOMS("linalg1", "determinant");
	public static final OMS LINALG2_MATRIX = OMCreator.createOMS("linalg2", "matrix");
	public static final OMS LINALG2_MATRIXROW = OMCreator.createOMS("linalg2", "matrixrow");
	public static final OMS LINALG2_VECTOR = OMCreator.createOMS("linalg2", "vector");

	public static final OMS LISTJACK_CHOOSEFROMCOMPLEMENT = OMCreator.createOMS("listJACK", "chooseFromComplement");
	public static final OMS LISTJACK_GETFROMLIST = OMCreator.createOMS("listJACK", "getFromList");
	public static final OMS LISTJACK_GETFROMORDEREDLIST = OMCreator.createOMS("listJACK", "getFromOrderedList");
	public static final OMS LISTJACK_GETRANDOMFROMSET = OMCreator.createOMS("listJACK", "getRandomFromSet");

	public static final OMS LIST1_LIST = OMCreator.createOMS("list1", "list");

	public static final OMS LIST2_APPEND = OMCreator.createOMS("list2", "append");
	public static final OMS LIST2_SIZE = OMCreator.createOMS("list2", "size");

	public static final OMS LOGICJACK_IFTHENELSE = OMCreator.createOMS("logicJACK", "ifthenelse");

	public static final OMS LOGIC1_AND = OMCreator.createOMS("logic1", "and");
	public static final OMS LOGIC1_FALSE = OMCreator.createOMS("logic1", "false");
	public static final OMS LOGIC1_NOT = OMCreator.createOMS("logic1", "not");
	public static final OMS LOGIC1_OR = OMCreator.createOMS("logic1", "or");
	public static final OMS LOGIC1_TRUE = OMCreator.createOMS("logic1", "true");

	public static final OMS MCJACK_MCINDEX = OMCreator.createOMS("mcJACK", "mcindex");

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

	public static final OMS POLY_COEFFICIENT = OMCreator.createOMS("poly", "coefficient");
	public static final OMS POLY_DEGREE_WRT = OMCreator.createOMS("poly", "degree_wrt");

	public static final OMS POLYNOMIALJACK_DEPENDSON = OMCreator.createOMS("polynomialJACK", "dependsOn");
	public static final OMS POLYNOMIALJACK_DERIVE = OMCreator.createOMS("polynomialJACK", "derive");
	public static final OMS POLYNOMIALJACK_INTEGRATE = OMCreator.createOMS("polynomialJACK", "integrate");
	public static final OMS POLYNOMIALJACK_NUMBEROFVARIABLES = OMCreator.createOMS("polynomialJACK",
			"numberOfVariables");
	public static final OMS POLYNOMIALJACK_EQUALSEXPR = OMCreator.createOMS("polynomialJACK", "equalsExpr");

	public static final OMS POLYNOMIAL1_EXPAND = OMCreator.createOMS("polynomial1", "expand");

	public static final OMS RANDOMJACK_RANDOM = OMCreator.createOMS("randomJACK", "random");
	public static final OMS RANDOMJACK_RANDOMBETWEEN = OMCreator.createOMS("randomJACK", "randomBetween");
	public static final OMS RANDOMJACK_RANDOMINTEGERBETWEEN = OMCreator.createOMS("randomJACK", "randomIntegerBetween");

	public static final OMS RELATION1_EQ = OMCreator.createOMS("relation1", "eq");
	public static final OMS RELATION1_GEQ = OMCreator.createOMS("relation1", "geq");
	public static final OMS RELATION1_GT = OMCreator.createOMS("relation1", "gt");
	public static final OMS RELATION1_LEQ = OMCreator.createOMS("relation1", "leq");
	public static final OMS RELATION1_LT = OMCreator.createOMS("relation1", "lt");
	public static final OMS RELATION1_NEQ = OMCreator.createOMS("relation1", "neq");

	public static final OMS ROUNDING1_CEILING = OMCreator.createOMS("rounding1", "ceiling");
	public static final OMS ROUNDING1_FLOOR = OMCreator.createOMS("rounding1", "floor");
	public static final OMS ROUNDING1_ROUND = OMCreator.createOMS("rounding1", "round");
	public static final OMS ROUNDING_JACK = OMCreator.createOMS("roundinJACK", "rint");

	public static final OMS SETJACK_EQUALSET = OMCreator.createOMS("setJACK", "equalSet");

	public static final OMS SET1_EMPTYSET = OMCreator.createOMS("set1", "emptyset");
	public static final OMS SET1_IN = OMCreator.createOMS("set1", "in");
	public static final OMS SET1_INTERSECT = OMCreator.createOMS("set1", "intersect");
	public static final OMS SET1_NOTIN = OMCreator.createOMS("set1", "notin");
	public static final OMS SET1_SET = OMCreator.createOMS("set1", "set");
	public static final OMS SET1_SETDIFF = OMCreator.createOMS("set1", "setdiff");
	public static final OMS SET1_SIZE = OMCreator.createOMS("set1", "size");
	public static final OMS SET1_SUBSET = OMCreator.createOMS("set1", "subset");
	public static final OMS SET1_UNION = OMCreator.createOMS("set1", "union");

	public static final OMS SETNAME1_C = OMCreator.createOMS("setname1", "C");
	public static final OMS SETNAME1_N = OMCreator.createOMS("setname1", "N");
	public static final OMS SETNAME1_P = OMCreator.createOMS("setname1", "P");
	public static final OMS SETNAME1_Q = OMCreator.createOMS("setname1", "Q");
	public static final OMS SETNAME1_R = OMCreator.createOMS("setname1", "R");
	public static final OMS SETNAME1_Z = OMCreator.createOMS("setname1", "Z");

	public static final OMS SPARQLJACK_QUERYSPARQL = OMCreator.createOMS("sparqljack", "querySparql");

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
	public static final OMS STRINGJACK_LEVENSHTEINDISTANCE = OMCreator.createOMS("stringJACK", "levenshteinDistance");
	public static final OMS STRINGJACK_REPLACE = OMCreator.createOMS("stringJACK", "replace");
	public static final OMS STRINGJACK_STARTSWITH = OMCreator.createOMS("stringJACK", "startsWith");
	public static final OMS STRINGJACK_SUBSTRING = OMCreator.createOMS("stringJACK", "substring");
	public static final OMS STRINGJACK_TOLOWERCASE = OMCreator.createOMS("stringJACK", "toLowerCase");
	public static final OMS STRINGJACK_TOUPPERCASE = OMCreator.createOMS("stringJACK", "toUpperCase");
	public static final OMS STRINGJACK_TRIM = OMCreator.createOMS("stringJACK", "trim");
	public static final OMS STRINGJACK_MATCHES = OMCreator.createOMS("stringJACK", "matches");
	public static final OMS STRINGJACK_TEXTWITHVARIABLES = OMCreator.createOMS("stringJACK", "textWithVariables");

	public static final OMS TESTTERMINALJACK_ISEMPTY = OMCreator.createOMS("testterminalJACK", "isEmpty");
	public static final OMS TESTTERMINALJACK_ISFRACTION = OMCreator.createOMS("testterminalJACK", "isFraction");
	public static final OMS TESTTERMINALJACK_ISINTEGERNUMBER = OMCreator.createOMS("testterminalJACK",
			"isIntegerNumber");
	public static final OMS TESTTERMINALJACK_ISMATRIX = OMCreator.createOMS("testterminalJACK", "isMatrix");
	public static final OMS TESTTERMINALJACK_ISMNMATRIX = OMCreator.createOMS("testterminalJACK", "isMNMatrix");
	public static final OMS TESTTERMINALJACK_ISNATURALNUMBER = OMCreator.createOMS("testterminalJACK",
			"isNaturalNumber");
	public static final OMS TESTTERMINALJACK_ISNPOLYNOMIAL = OMCreator.createOMS("testterminalJACK", "isNPolynomial");
	public static final OMS TESTTERMINALJACK_ISPOLYNOMIAL = OMCreator.createOMS("testterminalJACK", "isPolynomial");
	public static final OMS TESTTERMINALJACK_ISRATIONALNUMBER = OMCreator.createOMS("testterminalJACK", "isRational");
	public static final OMS TESTTERMINALJACK_ISREALNUMBER = OMCreator.createOMS("testterminalJACK", "isRealNumber");
	public static final OMS TESTTERMINALJACK_ISSET = OMCreator.createOMS("testterminalJACK", "isSet");
	public static final OMS TESTTERMINALJACK_ISTUPLE = OMCreator.createOMS("testterminalJACK", "isTuple");
	public static final OMS TESTTERMINALJACK_ISNTUPLE = OMCreator.createOMS("testterminalJACK", "isNTuple");

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

	public static final OMS[] SYMBOLIC_EXPRESSION = { ARITH1_ABS, ARITH1_DIVIDE, ARITH1_MINUS, ARITH1_PLUS,
			ARITH1_POWER, ARITH1_ROOT, ARITH1_TIMES, ARITH1_UNARY_MINUS, TRANSC1_ARCCOS, TRANSC1_ARCSIN, TRANSC1_ARCTAN,
			TRANSC1_COS, TRANSC1_EXP, TRANSC1_LOG, TRANSC1_SIN, TRANSC1_TAN };

	public static final OMS[] TERMINALS = { ECC_TUPLE, INTERVAL1_INTERVALCC, INTERVAL1_INTERVALCO, INTERVAL1_INTERVALOC,
			INTERVAL1_INTERVALOO, LINALG2_MATRIX, LINALG2_MATRIXROW, LINALG2_VECTOR, LIST1_LIST, MCJACK_MCINDEX,
			NUMS1_RATIONAL, SET1_SET, STRINGJACK_TEXTWITHVARIABLES, COMPLEX1_CARTESIAN_COMPLEX };

	public static boolean isSymbolicExpression(Object obj) {
		return (OMTypeChecker.isOMI(obj) || OMTypeChecker.isOMF(obj) || OMTypeChecker.isOMV(obj)
				|| OMTypeChecker.isOMS(obj) || OMTypeChecker.isOMAWithSymbol(obj, TERMINALS)
				|| OMTypeChecker.isOMAWithSymbol(obj, SYMBOLIC_EXPRESSION));
	}

	public static boolean isTerminal(Object obj) {
		return (OMTypeChecker.isOMI(obj) || OMTypeChecker.isOMF(obj) || OMTypeChecker.isOMV(obj)
				|| OMTypeChecker.isOMS(obj) || OMTypeChecker.isOMSTR(obj)
				|| OMTypeChecker.isOMAWithSymbol(obj, TERMINALS)
				|| OMTypeChecker.isOMAWithSymbol(obj, SYMBOLIC_EXPRESSION));
	}
}
