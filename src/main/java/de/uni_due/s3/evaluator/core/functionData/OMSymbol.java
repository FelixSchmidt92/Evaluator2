package de.uni_due.s3.evaluator.core.functionData;

public class OMSymbol {

	private String cd, name;
	
	public OMSymbol(String cd, String name){
		this.cd = cd;
		this.name = name;
	}
	
	/**
	 * Auto-generated HashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd == null) ? 0 : cd.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	 * Auto-generated Equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OMSymbol other = (OMSymbol) obj;
		if (cd == null) {
			if (other.cd != null)
				return false;
		} else if (!cd.equals(other.cd))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	public String getCd(){
		return cd;
	}
	
	public String getName(){
		return name;
	}
	
	
	
	
	
	
	
	public static final OMSymbol ARITH1_PLUS = new OMSymbol("arith1", "plus");
	public static final OMSymbol ARITH1_MINUS = new OMSymbol("arith1", "minus");
	public static final OMSymbol ARITH1_TIMES = new OMSymbol("arith1", "times");
	public static final OMSymbol ARITH1_DIVIDE = new OMSymbol("arith1", "divide");
	public static final OMSymbol ARITH1_ABS = new OMSymbol("arith1", "abs");
	public static final OMSymbol ARITH1_GCD = new OMSymbol("arith1", "gcd");
	public static final OMSymbol ARITHJACK_MODULUS = new OMSymbol("arithjack", "modulus"); // FIXME
																								// Hier
																								// sollte
																								// eigentlich
																								// eine
																								// arithJACK

	public static final OMSymbol INTEGER1_REMAINDER = new OMSymbol("integer1", "remainder"); // IEEERemainde
																								// (effizienter
																								// als
																								// MOD)

	public static final OMSymbol ROUNDING1_CEILING = new OMSymbol("rounding1", "ceiling");
	public static final OMSymbol ROUNDING1_FLOOR = new OMSymbol("rounding1", "floor");

	public static final OMSymbol BINARYJACK_CONVERTTOBINARY = new OMSymbol("binaryJACK", "convertToBinary");
	public static final OMSymbol BINARYJACK_EQUALSBINARY = new OMSymbol("binaryJACK", "equalsBinary");

	public static final OMSymbol OPENMATHJACK_COUNTBASICOPERATIONS = new OMSymbol("openmathJACK",
			"countBasicOperations");
	public static final OMSymbol OPENMATHJACK_COUNTNODES = new OMSymbol("openmathJACK", "countNodes");
	public static final OMSymbol OPENMATHJACK_GETDENOMINATOR = new OMSymbol("openmathJflrACK", "getDenominator");
	public static final OMSymbol OPENMATHJACK_GETNUMERATOR = new OMSymbol("openmathJACK", "getNumerator");

	// Für Zahlen
	public static final OMSymbol RELATION1_LT = new OMSymbol("relation1", "lt");
	public static final OMSymbol RELATION1_LEQ = new OMSymbol("relation1", "leq");
	public static final OMSymbol RELATION1_GT = new OMSymbol("relation1", "gt");
	public static final OMSymbol RELATION1_GEQ = new OMSymbol("relation1", "geq");
	public static final OMSymbol RELATION1_EQ = new OMSymbol("relation1", "eq");
	public static final OMSymbol RELATION1_NEQ = new OMSymbol("relation1", "neq");

	public static final OMSymbol LOGIC1_OR = new OMSymbol("logic1", "or");
	public static final OMSymbol LOGIC1_AND = new OMSymbol("logic1", "and");
	public static final OMSymbol LOGICJACK_IFTHENELSE = new OMSymbol("logicJACK", "ifthenelse");

	public static final OMSymbol TRANSC1_SIN = new OMSymbol("transc1", "sin");
	public static final OMSymbol TRANSC1_COS = new OMSymbol("transc1", "cos");
	public static final OMSymbol TRANSC1_TAN = new OMSymbol("transc1", "tan");
	public static final OMSymbol TRANSC1_ARCSIN = new OMSymbol("transc1", "arcsin");
	public static final OMSymbol TRANSC1_ARCCOS = new OMSymbol("transc1", "arccos");
	public static final OMSymbol TRANSC1_ARCTAN = new OMSymbol("transc1", "arctan");
	public static final OMSymbol TRANSC1_EXP = new OMSymbol("transc1", "exp");
	public static final OMSymbol TRANSC2_ARCTAN2 = new OMSymbol("transc2", "arctan");

	public static final OMSymbol POLYNOMIAL1_DEGREE = new OMSymbol("polynomial1", "degree");
	public static final OMSymbol POLYNOMIAL1_EXPAND = new OMSymbol("polynomial1", "expand");
	public static final OMSymbol POLYNOMIALJACK_DEPENDSON = new OMSymbol("polynomialJACK", "dependsOn");
	public static final OMSymbol POLYNOMIALJACK_DERIVE = new OMSymbol("polynomialJACK", "derive");
	public static final OMSymbol POLYNOMIALJACK_FACTOROF = new OMSymbol("polynomialJACK", "factorOf");
	public static final OMSymbol POLYNOMIALJACK_INTEGRATE = new OMSymbol("polynomialJACK", "integrate");
	public static final OMSymbol POLYNOMIALJACK_NUMBEROFVARIABLES = new OMSymbol("polynomialJACK",
			"numberOfVariables");
	public static final OMSymbol POLYNOMIALJACK_EQUALSEMISEM = new OMSymbol("polynomialJACK", "equalssemisem");
	public static final OMSymbol POLYNOMIALJACK_EQUALSEXPR = new OMSymbol("polynomialJACK", "equalsExpr");
	public static final OMSymbol POLYNOMIALJACK_EVALEQ = new OMSymbol("polynomialJACK", "evalEq");
	public static final OMSymbol POLYNOMIALJACK_EVALPOLYNOMIAL = new OMSymbol("polynomialJACK", "evalPolynomial");
	public static final OMSymbol POLYNOMIALJACK_EVALPOLYNOMIALCPLX = new OMSymbol("polynomialJACK",
			"evalPolynomialCplx");
	public static final OMSymbol POLYNOMIALJACK_EVALTERM2 = new OMSymbol("polynomialJACK", "evalterm2");

	public static final OMSymbol LINALG2_VECTOR = new OMSymbol("linalg2", "vector");
	public static final OMSymbol LINALG2_MATRIX = new OMSymbol("linalg2", "matrix");
	public static final OMSymbol LINALG2_MATRIXROW = new OMSymbol("linalg2", "matrixrow");
	public static final OMSymbol LINALGJACK_EQUALBASIS = new OMSymbol("linalgJACK", "equalBasis");
	public static final OMSymbol LINALGJACK_ISLINEARLYINDEPENDENT = new OMSymbol("linalgJACK",
			"isLinearlyIndependent");
	public static final OMSymbol LINALGJACK_RANDOMMATRIXEIGENVALUE = new OMSymbol("linalgJACK",
			"randomMatrixEigenvalue");
	public static final OMSymbol LINALGJACK_RANDOMMATRIXRANK = new OMSymbol("linalgJACK", "randomMatrixRank");

	public static final OMSymbol SET1_SET = new OMSymbol("set1", "set");
	public static final OMSymbol SET1_SIZE = new OMSymbol("set1", "size");
	public static final OMSymbol SETJACK_CHOOSEFROMCOMPLEMENT = new OMSymbol("setJACK", "chooseFromComplement");
	public static final OMSymbol SETJACK_GETFROMORDEREDSET = new OMSymbol("setJACK", "getFromOrderedSet");
	public static final OMSymbol SETJACK_GETFROMSET = new OMSymbol("setJACK", "getFromSet");

	public static final OMSymbol CASJACK_EVALUATEINR = new OMSymbol("casJACK", "evaluateInR");
	public static final OMSymbol CASJACK_EVALUATEINSAGE = new OMSymbol("casJACK", "evaluateInSage");
	public static final OMSymbol CASJACK_EVALUATEINSYMJA = new OMSymbol("casJACK", "evaluateInSymja");

	public static final OMSymbol STRINGJACK_CHARAT = new OMSymbol("stringJACK", "charAt");
	public static final OMSymbol STRINGJACK_COMPARETO = new OMSymbol("stringJACK", "compareTo");
	public static final OMSymbol STRINGJACK_COMPARETOIGNORECASE = new OMSymbol("stringJACK", "compareToIgnoreCase");
	public static final OMSymbol STRINGJACK_CONCAT = new OMSymbol("stringJACK", "concat");
	public static final OMSymbol STRINGJACK_ENDSWITH = new OMSymbol("stringJACK", "endsWith");
	public static final OMSymbol STRINGJACK_EQUALSIGNORECASE = new OMSymbol("stringJACK", "equalsIgnoreCase");
	public static final OMSymbol STRINGJACK_INDEXOF = new OMSymbol("stringJACK", "indexOf");

	public static final OMSymbol EVALJACK_EVAL = new OMSymbol("evalJACK", "eval");
	
	public static final OMSymbol COMPLEXJACK_EVALCPLX = new OMSymbol("complexJACK", "evalcplx");

	public static final OMSymbol TESTTERMINALJACK_ISEMPTY = new OMSymbol("testterminalJACK", "isEmpty");
	public static final OMSymbol TESTTERMINALJACK_ISPOLYNOMIAL = new OMSymbol("polynomialJACK", "isPolynomial");
}
