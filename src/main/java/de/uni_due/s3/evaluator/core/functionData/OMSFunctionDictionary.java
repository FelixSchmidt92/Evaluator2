package de.uni_due.s3.evaluator.core.functionData;

import java.util.HashMap;

import de.uni_due.s3.evaluator.exceptions.FunctionNotImplementedException;
import de.uni_due.s3.JAXBOpenMath.OMUtils.OMCreator;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Abs;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Divide;
import de.uni_due.s3.evaluator.core.function.functions.arith1.GCD;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Minus;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Modulus;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Plus;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Times;
import de.uni_due.s3.evaluator.core.function.functions.binary_jack.ConvertToBinary;
import de.uni_due.s3.evaluator.core.function.functions.binary_jack.EqualsBinary;
import de.uni_due.s3.evaluator.core.function.functions.cas_jack.EvaluateInR;
import de.uni_due.s3.evaluator.core.function.functions.cas_jack.EvaluateInSage;
import de.uni_due.s3.evaluator.core.function.functions.cas_jack.EvaluateInSymja;
import de.uni_due.s3.evaluator.core.function.functions.eval_jack.Eval;
import de.uni_due.s3.evaluator.core.function.functions.integer1.Remainder;
import de.uni_due.s3.evaluator.core.function.functions.linalg2.Matrix;
import de.uni_due.s3.evaluator.core.function.functions.linalg2.MatrixRow;
import de.uni_due.s3.evaluator.core.function.functions.linalg2.Vector;
import de.uni_due.s3.evaluator.core.function.functions.linalg_jack.EqualBasis;
import de.uni_due.s3.evaluator.core.function.functions.linalg_jack.IsLinearlyIndependent;
import de.uni_due.s3.evaluator.core.function.functions.linalg_jack.RandomMatrixEigenvalue;
import de.uni_due.s3.evaluator.core.function.functions.linalg_jack.RandomMatrixRank;
import de.uni_due.s3.evaluator.core.function.functions.logic1.BooleanAnd;
import de.uni_due.s3.evaluator.core.function.functions.logic1.BooleanOr;
import de.uni_due.s3.evaluator.core.function.functions.logic_jack.IfThenElse;
import de.uni_due.s3.evaluator.core.function.functions.openmath_jack.CountBasicOperations;
import de.uni_due.s3.evaluator.core.function.functions.openmath_jack.CountNodes;
import de.uni_due.s3.evaluator.core.function.functions.openmath_jack.GetDenominator;
import de.uni_due.s3.evaluator.core.function.functions.openmath_jack.GetNumerator;
import de.uni_due.s3.evaluator.core.function.functions.polynomial1.Degree;
import de.uni_due.s3.evaluator.core.function.functions.polynomial1.Expand;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.DependsOn;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.Derive;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.EqualsExpr;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.EqualsSemiSem;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.EvalCplx;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.EvalEq;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.EvalPolynomial;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.EvalPolynomialCplx;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.EvalTerm2;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.FactorOf;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.Integrate;
import de.uni_due.s3.evaluator.core.function.functions.polynomial_jack.NumberOfVariables;
import de.uni_due.s3.evaluator.core.function.functions.relation1.Equal;
import de.uni_due.s3.evaluator.core.function.functions.relation1.GreaterThan;
import de.uni_due.s3.evaluator.core.function.functions.relation1.GreaterThanOrEqual;
import de.uni_due.s3.evaluator.core.function.functions.relation1.LessThan;
import de.uni_due.s3.evaluator.core.function.functions.relation1.LessThanOrEqual;
import de.uni_due.s3.evaluator.core.function.functions.relation1.NotEqual;
import de.uni_due.s3.evaluator.core.function.functions.rounding1.Ceiling;
import de.uni_due.s3.evaluator.core.function.functions.rounding1.Floor;
import de.uni_due.s3.evaluator.core.function.functions.set1.Set;
import de.uni_due.s3.evaluator.core.function.functions.set1.Size;
import de.uni_due.s3.evaluator.core.function.functions.set_jack.ChooseFromComplement;
import de.uni_due.s3.evaluator.core.function.functions.set_jack.GetFromOrderedSet;
import de.uni_due.s3.evaluator.core.function.functions.set_jack.GetFromSet;
import de.uni_due.s3.evaluator.core.function.functions.string_jack.CharAt;
import de.uni_due.s3.evaluator.core.function.functions.string_jack.CompareTo;
import de.uni_due.s3.evaluator.core.function.functions.string_jack.CompareToIgnoreCase;
import de.uni_due.s3.evaluator.core.function.functions.string_jack.Concat;
import de.uni_due.s3.evaluator.core.function.functions.string_jack.EndsWith;
import de.uni_due.s3.evaluator.core.function.functions.string_jack.EqualsIgnoreCase;
import de.uni_due.s3.evaluator.core.function.functions.string_jack.IndexOf;
import de.uni_due.s3.evaluator.core.function.functions.testterminal_jack.IsEmpty;
import de.uni_due.s3.evaluator.core.function.functions.testterminal_jack.IsPolynomial;
import de.uni_due.s3.evaluator.core.function.functions.transc1.ArcCos;
import de.uni_due.s3.evaluator.core.function.functions.transc1.ArcSin;
import de.uni_due.s3.evaluator.core.function.functions.transc1.ArcTan;
import de.uni_due.s3.evaluator.core.function.functions.transc1.Cos;
import de.uni_due.s3.evaluator.core.function.functions.transc1.Exp;
import de.uni_due.s3.evaluator.core.function.functions.transc1.Sin;
import de.uni_due.s3.evaluator.core.function.functions.transc1.Tan;
import de.uni_due.s3.evaluator.core.function.functions.transc2.ArcTan2;

/**
 * In This Dictionary all available Functions are registered in a HashMap.
 * 
 * @author dlux, frichtscheid, spobel
 */
public class OMSFunctionDictionary {
	
	private HashMap<OMS, Function> functions = new HashMap<>();

	private static OMSFunctionDictionary omsfd = new OMSFunctionDictionary();
	
	public static OMSFunctionDictionary getInstance(){
		return omsfd;
	}
	
	/**
	 * Here you can add (register) the Functions which are available for the
	 * Evaluator in the HashMap.
	 * 
	 * The static only adds Functions to the HashMap. There is nothing else
	 * to do here.
	 * 
	 * Explanation: The key of this HashMap (First Parameter) defines the
	 * OMSymbol
	 * 
	 * The  Parameter: The Object which implements this functionName (can
	 * also be null)
	 */
	private OMSFunctionDictionary() {
		functions.put(OMSymbol.ARITH1_PLUS, new Plus());
		functions.put(OMSymbol.ARITH1_MINUS, new Minus());
		functions.put(OMSymbol.ARITH1_TIMES, new Times());
		functions.put(OMSymbol.ARITH1_DIVIDE, new Divide());
		functions.put(OMSymbol.ARITH1_ABS, new Abs());
		functions.put(OMSymbol.ARITH1_GCD, new GCD());
		functions.put(OMSymbol.ARITHJACK_MODULUS, new Modulus());
		// Integer
		functions.put(OMSymbol.INTEGER1_REMAINDER, new Remainder());
		// Rounding
		functions.put(OMSymbol.ROUNDING1_CEILING, new Ceiling());
		functions.put(OMSymbol.ROUNDING1_FLOOR, new Floor());
		// Binary
		functions.put(OMSymbol.BINARYJACK_CONVERTTOBINARY, new ConvertToBinary());
		functions.put(OMSymbol.BINARYJACK_EQUALSBINARY, new EqualsBinary());
		// OpenMathFunctions
		functions.put(OMSymbol.OPENMATHJACK_COUNTBASICOPERATIONS, new CountBasicOperations());
		functions.put(OMSymbol.OPENMATHJACK_COUNTNODES, new CountNodes());
		functions.put(OMSymbol.OPENMATHJACK_GETDENOMINATOR, new GetDenominator());
		functions.put(OMSymbol.OPENMATHJACK_GETNUMERATOR, new GetNumerator());
		// Relationen
		functions.put(OMSymbol.RELATION1_LT, new LessThan());
		functions.put(OMSymbol.RELATION1_LEQ, new LessThanOrEqual());
		functions.put(OMSymbol.RELATION1_GT, new GreaterThan());
		functions.put(OMSymbol.RELATION1_GEQ, new GreaterThanOrEqual());
		functions.put(OMSymbol.RELATION1_EQ, new Equal());
		// FIXME functions.put("equals", new Object[]{new Equal(), "relation1",
		// "eq"}); //TEXT und ZAHLEN
		functions.put(OMSymbol.RELATION1_NEQ, new NotEqual());
		functions.put(OMSymbol.POLYNOMIALJACK_EQUALSEMISEM, new EqualsSemiSem());
		// Logic
		functions.put(OMSymbol.LOGIC1_OR, new BooleanOr());
		functions.put(OMSymbol.LOGIC1_AND, new BooleanAnd());
		functions.put(OMSymbol.LOGICJACK_IFTHENELSE, new IfThenElse());
		// Trigonometrie
		functions.put(OMSymbol.TRANSC1_SIN, new Sin());
		functions.put(OMSymbol.TRANSC1_COS, new Cos());
		functions.put(OMSymbol.TRANSC1_TAN, new Tan());
		functions.put(OMSymbol.TRANSC1_ARCSIN, new ArcSin());
		functions.put(OMSymbol.TRANSC1_ARCCOS, new ArcCos());
		functions.put(OMSymbol.TRANSC1_ARCTAN, new ArcTan());
		functions.put(OMSymbol.TRANSC1_EXP, new Exp());
		functions.put(OMSymbol.TRANSC2_ARCTAN2, new ArcTan2());
		// Polynome
		functions.put(OMSymbol.POLYNOMIAL1_DEGREE, new Degree());
		functions.put(OMSymbol.POLYNOMIALJACK_DEPENDSON, new DependsOn());
		functions.put(OMSymbol.POLYNOMIALJACK_DERIVE, new Derive());
		functions.put(OMSymbol.POLYNOMIALJACK_EQUALSEXPR, new EqualsExpr());
		functions.put(OMSymbol.POLYNOMIALJACK_EVALEQ, new EvalEq());
		functions.put(OMSymbol.POLYNOMIALJACK_EVALPOLYNOMIAL, new EvalPolynomial());
		functions.put(OMSymbol.POLYNOMIALJACK_EVALPOLYNOMIALCPLX, new EvalPolynomialCplx());
		functions.put(OMSymbol.POLYNOMIALJACK_EVALTERM2, new EvalTerm2());
		functions.put(OMSymbol.POLYNOMIAL1_EXPAND, new Expand());
		functions.put(OMSymbol.POLYNOMIALJACK_FACTOROF, new FactorOf());
		functions.put(OMSymbol.POLYNOMIALJACK_INTEGRATE, new Integrate());
		functions.put(OMSymbol.POLYNOMIALJACK_NUMBEROFVARIABLES, new NumberOfVariables());
		// Lineare Algebra
		functions.put(OMSymbol.LINALG2_VECTOR, new Vector());
		functions.put(OMSymbol.LINALG2_MATRIX, new Matrix());
		functions.put(OMSymbol.LINALG2_MATRIXROW, new MatrixRow());
		functions.put(OMSymbol.LINALGJACK_EQUALBASIS, new EqualBasis());
		functions.put(OMSymbol.LINALGJACK_ISLINEARLYINDEPENDENT, new IsLinearlyIndependent());
		functions.put(OMSymbol.LINALGJACK_RANDOMMATRIXEIGENVALUE, new RandomMatrixEigenvalue());
		functions.put(OMSymbol.LINALGJACK_RANDOMMATRIXRANK, new RandomMatrixRank());
		// List
		functions.put(OMSymbol.SET1_SET, new Set());
		functions.put(OMSymbol.SET1_SIZE, new Size());
		functions.put(OMSymbol.SETJACK_CHOOSEFROMCOMPLEMENT, new ChooseFromComplement());
		functions.put(OMSymbol.SETJACK_GETFROMORDEREDSET, new GetFromOrderedSet());
		functions.put(OMSymbol.SETJACK_GETFROMSET, new GetFromSet());
		// CAS
		functions.put(OMSymbol.CASJACK_EVALUATEINR, new EvaluateInR());
		functions.put(OMSymbol.CASJACK_EVALUATEINSAGE, new EvaluateInSage());
		functions.put(OMSymbol.CASJACK_EVALUATEINSYMJA, new EvaluateInSymja());
		// String
		functions.put(OMSymbol.STRINGJACK_CHARAT, new CharAt());
		functions.put(OMSymbol.STRINGJACK_COMPARETO, new CompareTo());
		functions.put(OMSymbol.STRINGJACK_COMPARETOIGNORECASE, new CompareToIgnoreCase());
		functions.put(OMSymbol.STRINGJACK_CONCAT, new Concat());
		functions.put(OMSymbol.STRINGJACK_ENDSWITH, new EndsWith());
		functions.put(OMSymbol.STRINGJACK_EQUALSIGNORECASE, new EqualsIgnoreCase());
		functions.put(OMSymbol.STRINGJACK_INDEXOF, new IndexOf());
		// Evaluate
		functions.put(OMSymbol.EVALJACK_EVAL, new Eval());
		//Complex
		functions.put(OMSymbol.COMPLEXJACK_EVALCPLX, new EvalCplx());
		// Constanten

		// TestTerminal
		functions.put(OMSymbol.TESTTERMINALJACK_ISEMPTY, new IsEmpty());
		functions.put(OMSymbol.TESTTERMINALJACK_ISPOLYNOMIAL, new IsPolynomial());
	}


	/**
	 * This method returns the specific Function. 
	 * 
	 * @param name
	 *            the name (key)(in lower case) of this Function
	 * @return the specific Function
	 * @throws FunctionNotImplementedException
	 *             if Function is not found in HashMap
	 */
	public Function getFunction(OMS oms) {
		if (functions.containsKey(oms)) {
			return functions.get(oms);
		} else {
			throw new FunctionNotImplementedException(OMCreator.createOMS("", ""));
		}
	}
}
