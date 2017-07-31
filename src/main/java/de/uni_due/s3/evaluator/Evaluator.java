package de.uni_due.s3.evaluator;

import java.util.HashMap;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * TODO
 * 
 * @author dlux,frichtscheid,spobel
 *
 */
public class Evaluator {

	private Evaluator() {
	}

	public static boolean getBooleanResult(String expression, HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) throws CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ result = evaluate(expression, exerciseVariableMap, fillInVariableMap);
		return (result.getOMS() != null && result.getOMS().equals(OMSymbol.LOGIC1_TRUE));
	}

	public static double getNumberResult(String expression, HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) throws CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, InvalidResultTypeException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ result = evaluate(expression, exerciseVariableMap, fillInVariableMap);
		try {
			return OMUtils.convertOMToDouble(result);
		} catch (InputMismatchException e) {
			throw new InvalidResultTypeException(
					"Type of result of expression:" + expression + "has to be integer, double or float.");
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
			HashMap<Integer, OMOBJ> fillInVariableMap) throws FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
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
	public static OMOBJ evaluate(OMOBJ omobj) throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		return OMExecutor.execute(omobj);
	}

}
