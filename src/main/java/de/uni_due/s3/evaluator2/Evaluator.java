package de.uni_due.s3.evaluator2;

import java.util.HashMap;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
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

	public static boolean getBooleanResult(String expression, HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) throws EvaluatorException, OpenMathException {
		OMOBJ result = evaluate(expression, exerciseVariableMap, fillInVariableMap);
		return (result.getOMS() != null && result.getOMS().equals(OMSymbol.LOGIC1_TRUE));
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
			return OMUtils.convertOMToDouble(result);
		} catch (InputMismatchException e) {
			throw new FunctionException("Result of expression:" + expression + "can't be converted to a number");
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
}