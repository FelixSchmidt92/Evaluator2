package de.uni_due.s3.evaluator2;

import java.util.HashMap;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.visitor.OMToDoubleVisitor;
import de.uni_due.s3.evaluator2.core.visitor.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.core.visitor.OMToStringVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.r.R;
import de.uni_due.s3.evaluator2.r.RConn;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.evaluator2.sage.SageConnection;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This is the Evaluator. With This Class you can evaluate and get a result form
 * the Evaluator.
 * 
 * @author dlux,frichtscheid,spobel
 *
 */
public class Evaluator {

	private Evaluator() {
	}

	/**
	 * Initialize connections to R & Sage Server. This Method has to be called once!
	 * If the connections aren't initialized, methods like evaluateInR,
	 * evaluateInSage can't work
	 * 
	 * @param rConnections
	 * @param sageConnections
	 */
	public static void initializeCAS(List<RConn> rConnections, List<SageConnection> sageConnections) {
		Sage.init(sageConnections);
		R.init(rConnections);
	}

	public static boolean getBooleanResult(String expression, HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) throws EvaluatorException, OpenMathException {
		OMOBJ result = evaluate(expression, exerciseVariableMap, fillInVariableMap);
		if (result.getOMS() != null && result.getOMS().equals(OMSymbol.LOGIC1_TRUE)) {
			return true;
		} else if (result.getOMI() != null && result.getOMI().getValue().equals("1")) { // 1 ist auch True
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Evaluates an expression and returns the result as a number if possible.
	 * 
	 * @param expression
	 * @param exerciseVariableMap
	 * @param fillInVariableMap
	 * @return a double value
	 * @throws CasEvaluationException
	 * @throws FunctionException
	 *             If the expression doesn't return a number
	 * @throws CasNotAvailableException
	 *             If the CAS is not available
	 * @throws NoRepresentationAvailableException
	 * @throws OpenMathException
	 * @throws UndefinedFillInVariableException
	 * @throws UndefinedExerciseVariableException
	 * @throws ParserException
	 */
	public static double getNumberResult(String expression, HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) throws EvaluatorException, OpenMathException {
		OMOBJ result = evaluate(expression, exerciseVariableMap, fillInVariableMap);
		result = evaluate(result);
		try {
			return new OMToDoubleVisitor().visit(result);
		} catch (NoRepresentationAvailableException e) {
			throw new FunctionException("Error converting result into Double.", e);
		}
	}

	/**
	 * Evaluates an expression, like "1+3" or "sin(cos(1))"
	 * 
	 * @param expression
	 *            see requirements F2.*
	 * @return the result of the evaluated expression as OpenMath-Object (JAXB)
	 * @throws FunctionException
	 * @throws OpenMathException
	 * @throws NoRepresentationAvailableException
	 * @throws CasNotAvailableException
	 * @throws CasEvaluationException
	 * @throws ParserException
	 * @throws UndefinedExerciseVariableException
	 * @throws UndefinedFillInVariableException
	 */
	public static OMOBJ evaluate(String expression, HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) throws EvaluatorException, OpenMathException {

		OMOBJ omobj = ExpressionParser.parse(expression, exerciseVariableMap, fillInVariableMap);
		return OMExecutor.execute(omobj);
	}

	/**
	 * Evaluates an OpenMath-Object. Example for an Object: <OMOBJ> <OMA>
	 * <OMS cd=arith1 name="plus" /> <OMI>1</OMI> <OMI>3</OMI> </OMA> </OMOBJ>
	 * 
	 * @param omobj
	 * @return the evaluated OpenMath-Object
	 * @throws FunctionException
	 * @throws OpenMathException
	 * @throws NoRepresentationAvailableException
	 * @throws CasNotAvailableException
	 * @throws CasEvaluationException
	 */
	public static OMOBJ evaluate(OMOBJ omobj) throws EvaluatorException, OpenMathException {
		return OMExecutor.execute(omobj);
	}

	/**
	 * Converts a OpenMath-Object into latex
	 * 
	 * @param omobj
	 * @return latex
	 * @throws EvaluatorException
	 */
	public static String getLaTeX(OMOBJ omobj) throws EvaluatorException {
		return new OMToLatexVisitor().visit(omobj);
	}

	/**
	 * Converts a OpenMath-Object into String
	 * 
	 * @param omobj
	 * @return string
	 * @throws EvaluatorException
	 */
	public static String getString(OMOBJ omobj) throws EvaluatorException {
		return new OMToStringVisitor().visit(omobj);
	}
	
	/**
	 * Converts a OpenMath-Object into R Syntax
	 * 
	 * @param omobj
	 * @return r-syntax
	 * @throws EvaluatorException
	 */
	public static String getRSyntax(OMOBJ omobj) throws EvaluatorException {
		throw new EvaluatorException("CasVisitor is not implemented yet");
	}
}
