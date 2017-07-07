package de.uni_due.s3.evaluator;

import java.util.HashMap;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
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
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ result = evaluate(expression, exerciseVariableMap, fillInVariableMap);
		return (result.getOMS() != null && result.getOMS().equals(OMSymbol.LOGIC1_TRUE));
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
	 */
	public static OMOBJ evaluate(String expression, HashMap<String, OMOBJ> exerciseVariableMap,
			HashMap<Integer, OMOBJ> fillInVariableMap) throws FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException {
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
