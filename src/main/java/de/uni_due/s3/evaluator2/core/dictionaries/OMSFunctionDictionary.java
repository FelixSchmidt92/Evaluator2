package de.uni_due.s3.evaluator2.core.dictionaries;

import java.util.HashMap;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.arith1.Abs;
import de.uni_due.s3.evaluator2.core.function.arith1.Divide;
import de.uni_due.s3.evaluator2.core.function.arith1.GCD;
import de.uni_due.s3.evaluator2.core.function.arith1.Minus;
import de.uni_due.s3.evaluator2.core.function.arith1.Plus;
import de.uni_due.s3.evaluator2.core.function.arith1.Power;
import de.uni_due.s3.evaluator2.core.function.arith1.Product;
import de.uni_due.s3.evaluator2.core.function.arith1.Root;
import de.uni_due.s3.evaluator2.core.function.arith1.Sum;
import de.uni_due.s3.evaluator2.core.function.arith1.Times;
import de.uni_due.s3.evaluator2.core.function.arith1.UnaryMinus;
import de.uni_due.s3.evaluator2.core.function.arith_jack.IEEERemainder;
import de.uni_due.s3.evaluator2.core.function.arith_jack.Max;
import de.uni_due.s3.evaluator2.core.function.arith_jack.Min;
import de.uni_due.s3.evaluator2.core.function.binary_jack.ConvertToBinary;
import de.uni_due.s3.evaluator2.core.function.binary_jack.EqualsBinary;
import de.uni_due.s3.evaluator2.core.function.calculus1.DefInt;
import de.uni_due.s3.evaluator2.core.function.calculus1.Int;
import de.uni_due.s3.evaluator2.core.function.cas_jack.EvaluateInR;
import de.uni_due.s3.evaluator2.core.function.cas_jack.EvaluateInSage;
import de.uni_due.s3.evaluator2.core.function.cas_jack.EvaluateInSymja;
import de.uni_due.s3.evaluator2.core.function.eval_jack.Eval;
import de.uni_due.s3.evaluator2.core.function.eval_jack.EvalEq;
import de.uni_due.s3.evaluator2.core.function.eval_jack.EvalPolynomial;
import de.uni_due.s3.evaluator2.core.function.eval_jack.EvalPolynomialCplx;
import de.uni_due.s3.evaluator2.core.function.eval_jack.EvalTerm2;
import de.uni_due.s3.evaluator2.core.function.integer1.Remainder;
import de.uni_due.s3.evaluator2.core.function.interval1.IntervalCC;
import de.uni_due.s3.evaluator2.core.function.interval1.IntervalCO;
import de.uni_due.s3.evaluator2.core.function.interval1.IntervalOC;
import de.uni_due.s3.evaluator2.core.function.interval1.IntervalOO;
import de.uni_due.s3.evaluator2.core.function.linalg1.Determinant;
import de.uni_due.s3.evaluator2.core.function.linalg2.Matrix;
import de.uni_due.s3.evaluator2.core.function.linalg2.MatrixRow;
import de.uni_due.s3.evaluator2.core.function.linalg2.Vector;
import de.uni_due.s3.evaluator2.core.function.linalg_jack.EqualBasis;
import de.uni_due.s3.evaluator2.core.function.linalg_jack.IsLinearlyIndependent;
import de.uni_due.s3.evaluator2.core.function.linalg_jack.RandomMatrixEigenvalue;
import de.uni_due.s3.evaluator2.core.function.linalg_jack.RandomMatrixRank;
import de.uni_due.s3.evaluator2.core.function.list1.List;
import de.uni_due.s3.evaluator2.core.function.logic1.BooleanAnd;
import de.uni_due.s3.evaluator2.core.function.logic1.BooleanNot;
import de.uni_due.s3.evaluator2.core.function.logic1.BooleanOr;
import de.uni_due.s3.evaluator2.core.function.logic_jack.IfThenElse;
import de.uni_due.s3.evaluator2.core.function.openmath_jack.CountBasicOperations;
import de.uni_due.s3.evaluator2.core.function.openmath_jack.CountNodes;
import de.uni_due.s3.evaluator2.core.function.openmath_jack.GetDenominator;
import de.uni_due.s3.evaluator2.core.function.openmath_jack.GetNumerator;
import de.uni_due.s3.evaluator2.core.function.poly.Coefficient;
import de.uni_due.s3.evaluator2.core.function.poly.Degree_wrt;
import de.uni_due.s3.evaluator2.core.function.polynomial1.Expand;
import de.uni_due.s3.evaluator2.core.function.polynomial_jack.DependsOn;
import de.uni_due.s3.evaluator2.core.function.polynomial_jack.Derive;
import de.uni_due.s3.evaluator2.core.function.polynomial_jack.EqualsExpr;
import de.uni_due.s3.evaluator2.core.function.polynomial_jack.Integrate;
import de.uni_due.s3.evaluator2.core.function.polynomial_jack.NumberOfVariables;
import de.uni_due.s3.evaluator2.core.function.random_jack.Random;
import de.uni_due.s3.evaluator2.core.function.relation1.Equal;
import de.uni_due.s3.evaluator2.core.function.relation1.GreaterThan;
import de.uni_due.s3.evaluator2.core.function.relation1.GreaterThanOrEqual;
import de.uni_due.s3.evaluator2.core.function.relation1.LessThan;
import de.uni_due.s3.evaluator2.core.function.relation1.LessThanOrEqual;
import de.uni_due.s3.evaluator2.core.function.relation1.NotEqual;
import de.uni_due.s3.evaluator2.core.function.rounding1.Ceiling;
import de.uni_due.s3.evaluator2.core.function.rounding1.Floor;
import de.uni_due.s3.evaluator2.core.function.rounding1.Round;
import de.uni_due.s3.evaluator2.core.function.rounding_jack.Rint;
import de.uni_due.s3.evaluator2.core.function.set1.Set;
import de.uni_due.s3.evaluator2.core.function.set1.Size;
import de.uni_due.s3.evaluator2.core.function.set_jack.ChooseFromComplement;
import de.uni_due.s3.evaluator2.core.function.set_jack.GetFromOrderedSet;
import de.uni_due.s3.evaluator2.core.function.set_jack.GetFromSet;
import de.uni_due.s3.evaluator2.core.function.set_jack.GetRandomFromSet;
import de.uni_due.s3.evaluator2.core.function.string_jack.CharAt;
import de.uni_due.s3.evaluator2.core.function.string_jack.CompareTo;
import de.uni_due.s3.evaluator2.core.function.string_jack.CompareToIgnoreCase;
import de.uni_due.s3.evaluator2.core.function.string_jack.Concat;
import de.uni_due.s3.evaluator2.core.function.string_jack.EndsWith;
import de.uni_due.s3.evaluator2.core.function.string_jack.Equals;
import de.uni_due.s3.evaluator2.core.function.string_jack.EqualsIgnoreCase;
import de.uni_due.s3.evaluator2.core.function.string_jack.IndexOf;
import de.uni_due.s3.evaluator2.core.function.string_jack.LastIndexOf;
import de.uni_due.s3.evaluator2.core.function.string_jack.Length;
import de.uni_due.s3.evaluator2.core.function.string_jack.Matches;
import de.uni_due.s3.evaluator2.core.function.string_jack.Replace;
import de.uni_due.s3.evaluator2.core.function.string_jack.StartsWith;
import de.uni_due.s3.evaluator2.core.function.string_jack.Substring;
import de.uni_due.s3.evaluator2.core.function.string_jack.TextWithVariables;
import de.uni_due.s3.evaluator2.core.function.string_jack.ToLowerCase;
import de.uni_due.s3.evaluator2.core.function.string_jack.ToUpperCase;
import de.uni_due.s3.evaluator2.core.function.string_jack.Trim;
import de.uni_due.s3.evaluator2.core.function.testterminal_jack.IsEmpty;
import de.uni_due.s3.evaluator2.core.function.testterminal_jack.IsNumber;
import de.uni_due.s3.evaluator2.core.function.testterminal_jack.IsPolynomial;
import de.uni_due.s3.evaluator2.core.function.testterminal_jack.IsSet;
import de.uni_due.s3.evaluator2.core.function.testterminal_jack.isFraction;
import de.uni_due.s3.evaluator2.core.function.transc1.ArcCos;
import de.uni_due.s3.evaluator2.core.function.transc1.ArcSin;
import de.uni_due.s3.evaluator2.core.function.transc1.ArcTan;
import de.uni_due.s3.evaluator2.core.function.transc1.Cos;
import de.uni_due.s3.evaluator2.core.function.transc1.Exp;
import de.uni_due.s3.evaluator2.core.function.transc1.Log;
import de.uni_due.s3.evaluator2.core.function.transc1.Sin;
import de.uni_due.s3.evaluator2.core.function.transc1.Tan;
import de.uni_due.s3.evaluator2.core.function.transc2.ArcTan2;
import de.uni_due.s3.evaluator2.core.function.transc_jack.ToDegree;
import de.uni_due.s3.evaluator2.core.function.transc_jack.ToRadian;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator2.core.function.random_jack.RandomBetween;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * In This Dictionary all available Functions are registered in a HashMap.
 * 
 * @author dlux, frichtscheid, spobel
 */
public class OMSFunctionDictionary {

	private HashMap<OMS, Function> functions = new HashMap<>();

	private static OMSFunctionDictionary omsfd = new OMSFunctionDictionary();

	public static OMSFunctionDictionary getInstance() {
		return omsfd;
	}

	/**
	 * Here you can add (register) the Functions which are available for the
	 * Evaluator in the HashMap.
	 * 
	 * 
	 * Explanation: The key of this HashMap (First Parameter) defines the
	 * OMSymbol
	 * 
	 * The Parameter: The Object which implements this functionName (can also be
	 * null)
	 * 
	 * If you did add a new OMSymbol in OMSymbols and a name (String) in
	 * OMSEvaluatorSyntaxDictionary you can now refer here to the Key OMSSymbol
	 * to the corresponding Class of the Function
	 */
	private OMSFunctionDictionary() {

		functions.put(OMSymbol.ARITHJACK_IEEEREMAINDER, new IEEERemainder());
		functions.put(OMSymbol.ARITHJACK_MAX, new Max());
		functions.put(OMSymbol.ARITHJACK_MIN, new Min());

		functions.put(OMSymbol.ARITH1_ABS, new Abs());
		functions.put(OMSymbol.ARITH1_DIVIDE, new Divide());
		functions.put(OMSymbol.ARITH1_GCD, new GCD());
		functions.put(OMSymbol.ARITH1_MINUS, new Minus());
		functions.put(OMSymbol.ARITH1_PLUS, new Plus());
		functions.put(OMSymbol.ARITH1_POWER, new Power());
		functions.put(OMSymbol.ARITH1_ROOT, new Root());
		functions.put(OMSymbol.ARITH1_TIMES, new Times());
		functions.put(OMSymbol.ARITH1_UNARY_MINUS, new UnaryMinus());
		functions.put(OMSymbol.ARITH1_PRODUCT, new Product());
		functions.put(OMSymbol.ARITH1_SUM, new Sum());
		
		functions.put(OMSymbol.BINARYJACK_CONVERTTOBINARY, new ConvertToBinary());
		functions.put(OMSymbol.BINARYJACK_EQUALSBINARY, new EqualsBinary());

		functions.put(OMSymbol.CASJACK_EVALUATEINR, new EvaluateInR());
		functions.put(OMSymbol.CASJACK_EVALUATEINSAGE, new EvaluateInSage());
		functions.put(OMSymbol.CASJACK_EVALUATEINSYMJA, new EvaluateInSymja());

		functions.put(OMSymbol.CALCULUS1_INT, new Int());
		functions.put(OMSymbol.CALCULUS1_DEFINT, new DefInt());
		
		functions.put(OMSymbol.EVALJACK_EVAL, new Eval());
		functions.put(OMSymbol.EVALJACK_EVALEQ, new EvalEq());
		functions.put(OMSymbol.EVALJACK_EVALPOLYNOMIAL, new EvalPolynomial());
		functions.put(OMSymbol.EVALJACK_EVALPOLYNOMIALCPLX, new EvalPolynomialCplx());
		functions.put(OMSymbol.EVALJACK_EVALTERM2, new EvalTerm2());

		functions.put(OMSymbol.INTEGER1_REMAINDER, new Remainder());
		
		functions.put(OMSymbol.INTERVAL1_INTERVALCC, new IntervalCC());
		functions.put(OMSymbol.INTERVAL1_INTERVALCO, new IntervalCO());
		functions.put(OMSymbol.INTERVAL1_INTERVALOC, new IntervalOC());
		functions.put(OMSymbol.INTERVAL1_INTERVALOO, new IntervalOO());

		functions.put(OMSymbol.LOGICJACK_IFTHENELSE, new IfThenElse());

		functions.put(OMSymbol.LOGIC1_AND, new BooleanAnd());
		functions.put(OMSymbol.LOGIC1_OR, new BooleanOr());
		functions.put(OMSymbol.LOGIC1_NOT, new BooleanNot());

		functions.put(OMSymbol.LINALGJACK_EQUALBASIS, new EqualBasis());
		functions.put(OMSymbol.LINALGJACK_ISLINEARLYINDEPENDENT, new IsLinearlyIndependent());
		functions.put(OMSymbol.LINALGJACK_RANDOMMATRIXEIGENVALUE, new RandomMatrixEigenvalue());
		functions.put(OMSymbol.LINALGJACK_RANDOMMATRIXRANK, new RandomMatrixRank());

		functions.put(OMSymbol.LINALG1_DETERMINANT, new Determinant());
		functions.put(OMSymbol.LINALG2_MATRIX, new Matrix());
		functions.put(OMSymbol.LINALG2_MATRIXROW, new MatrixRow());
		functions.put(OMSymbol.LINALG2_VECTOR, new Vector());

		functions.put(OMSymbol.LIST1_LIST, new List());
		
		functions.put(OMSymbol.NUMS1_RATIONAL, new Divide());

		functions.put(OMSymbol.OPENMATHJACK_COUNTBASICOPERATIONS, new CountBasicOperations());
		functions.put(OMSymbol.OPENMATHJACK_COUNTNODES, new CountNodes());
		functions.put(OMSymbol.OPENMATHJACK_GETDENOMINATOR, new GetDenominator());
		functions.put(OMSymbol.OPENMATHJACK_GETNUMERATOR, new GetNumerator());
		functions.put(OMSymbol.OPENMATHJACK_ISFRACTION, new isFraction());

		functions.put(OMSymbol.POLY_COEFFICIENT, new Coefficient());
		functions.put(OMSymbol.POLY_DEGREE_WRT, new Degree_wrt());

		functions.put(OMSymbol.POLYNOMIALJACK_INTEGRATE, new Integrate());
		functions.put(OMSymbol.POLYNOMIALJACK_NUMBEROFVARIABLES, new NumberOfVariables());
		functions.put(OMSymbol.POLYNOMIALJACK_DEPENDSON, new DependsOn());
		functions.put(OMSymbol.POLYNOMIALJACK_DERIVE, new Derive());
		functions.put(OMSymbol.POLYNOMIALJACK_EQUALSEXPR, new EqualsExpr());

		functions.put(OMSymbol.POLYNOMIAL1_EXPAND, new Expand());
		
		functions.put(OMSymbol.RANDOMJACK_RANDOM, new Random());
		functions.put(OMSymbol.RANDOMJACK_RANDOMBETWEEN, new RandomBetween());

		functions.put(OMSymbol.RELATION1_EQ, new Equal());
		functions.put(OMSymbol.RELATION1_GEQ, new GreaterThanOrEqual());
		functions.put(OMSymbol.RELATION1_GT, new GreaterThan());
		functions.put(OMSymbol.RELATION1_LEQ, new LessThanOrEqual());
		functions.put(OMSymbol.RELATION1_LT, new LessThan());
		functions.put(OMSymbol.RELATION1_NEQ, new NotEqual());

		functions.put(OMSymbol.ROUNDING1_CEILING, new Ceiling());
		functions.put(OMSymbol.ROUNDING1_FLOOR, new Floor());
		functions.put(OMSymbol.ROUNDING1_ROUND, new Round());
		
		functions.put(OMSymbol.ROUNDING_JACK, new Rint());

		functions.put(OMSymbol.SETJACK_CHOOSEFROMCOMPLEMENT, new ChooseFromComplement());
		functions.put(OMSymbol.SETJACK_GETFROMORDEREDSET, new GetFromOrderedSet());
		functions.put(OMSymbol.SETJACK_GETFROMSET, new GetFromSet());
		functions.put(OMSymbol.SETJACK_GETRANDOMFROMSET, new GetRandomFromSet());

		functions.put(OMSymbol.SET1_SET, new Set());
		functions.put(OMSymbol.SET1_SIZE, new Size());

		functions.put(OMSymbol.STRINGJACK_CHARAT, new CharAt());
		functions.put(OMSymbol.STRINGJACK_COMPARETO, new CompareTo());
		functions.put(OMSymbol.STRINGJACK_COMPARETOIGNORECASE, new CompareToIgnoreCase());
		functions.put(OMSymbol.STRINGJACK_CONCAT, new Concat());
		functions.put(OMSymbol.STRINGJACK_ENDSWITH, new EndsWith());
		functions.put(OMSymbol.STRINGJACK_EQUALS, new Equals());
		functions.put(OMSymbol.STRINGJACK_EQUALSIGNORECASE, new EqualsIgnoreCase());
		functions.put(OMSymbol.STRINGJACK_INDEXOF, new IndexOf());
		functions.put(OMSymbol.STRINGJACK_LASTINDEXOF, new LastIndexOf());
		functions.put(OMSymbol.STRINGJACK_LENGTH, new Length());
		functions.put(OMSymbol.STRINGJACK_MATCHES, new Matches());
		functions.put(OMSymbol.STRINGJACK_REPLACE, new Replace());
		functions.put(OMSymbol.STRINGJACK_STARTSWITH, new StartsWith());
		functions.put(OMSymbol.STRINGJACK_SUBSTRING, new Substring());
		functions.put(OMSymbol.STRINGJACK_TOLOWERCASE, new ToLowerCase());
		functions.put(OMSymbol.STRINGJACK_TOUPPERCASE, new ToUpperCase());
		functions.put(OMSymbol.STRINGJACK_TRIM, new Trim());
		functions.put(OMSymbol.STRINGJACK_TEXTWITHVARIABLES, new TextWithVariables());

		functions.put(OMSymbol.TESTTERMINALJACK_ISEMPTY, new IsEmpty());
		functions.put(OMSymbol.TESTTERMINALJACK_ISNUMBER, new IsNumber());
		functions.put(OMSymbol.TESTTERMINALJACK_ISPOLYNOMIAL, new IsPolynomial());
		functions.put(OMSymbol.TESTTERMINALJACK_ISSET, new IsSet());

		functions.put(OMSymbol.TRANSCJACK_TODEGREE, new ToDegree());
		functions.put(OMSymbol.TRANSCJACK_TORADIAN, new ToRadian());

		functions.put(OMSymbol.TRANSC1_ARCCOS, new ArcCos());
		functions.put(OMSymbol.TRANSC1_ARCSIN, new ArcSin());
		functions.put(OMSymbol.TRANSC1_ARCTAN, new ArcTan());
		functions.put(OMSymbol.TRANSC1_COS, new Cos());
		functions.put(OMSymbol.TRANSC1_EXP, new Exp());
		functions.put(OMSymbol.TRANSC1_LOG, new Log());
		functions.put(OMSymbol.TRANSC1_SIN, new Sin());
		functions.put(OMSymbol.TRANSC1_TAN, new Tan());

		functions.put(OMSymbol.TRANSC2_ARCTAN2, new ArcTan2());
	}

	/**
	 * This method returns the specific Function.
	 * 
	 * @param name
	 *            the name (key)(in lower case) of this Function
	 * @return the specific Function
	 * @throws FunctionNotImplementedRuntimeException
	 *             if Function is not found in HashMap
	 */
	public Function getFunction(OMS oms) throws FunctionNotImplementedRuntimeException {
		if (oms == null)
			throw new FunctionNotImplementedRuntimeException(OMCreator.createOMS("NULL", "NULL"));

		if (functions.containsKey(oms)) {
			return functions.get(oms);
		} else {
			throw new FunctionNotImplementedRuntimeException(oms);
		}
	}
}
