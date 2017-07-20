package de.uni_due.s3.evaluator.core.function.functions.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Function tests if the first Argument can be casted to OMI|OMF, if it
 * does true will be returned (is a Number), false otherwise.
 * 
 * Note:
 * 	PI and E are also Numbers so true will be also returned
 * 
 * @author dlux
 *
 */
public class IsNumber extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		if (arguments.get(0) instanceof OMS) {
			OMS oms = (OMS) arguments.get(0);
			if(oms.equals(OMSymbol.NUMS1_PI) || oms.equals(OMSymbol.NUMS1_E)) {
				return OMSymbol.LOGIC1_TRUE;
			}
		}
		
		if (arguments.get(0) instanceof OMI || arguments.get(0) instanceof OMF) {
			return OMSymbol.LOGIC1_TRUE;
		} else {
			return OMSymbol.LOGIC1_FALSE;
		}
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

}
