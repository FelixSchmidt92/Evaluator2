package de.uni_due.s3.evaluator.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OpenMathException;


/**
 * This class tests if the following Argument is a set. It returns iff its a
 * set, false otherwise.
 * 
 * @author dlux
 *
 */
public class IsSet extends Function{

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		Object obj = arguments.get(0);
		if(obj instanceof OMA) {
			OMA oma = (OMA) obj;
			if (oma.getOmel().size() != 0 && oma.getOmel().get(0).equals(OMSymbol.SET1_SET)) {
				return OMSymbol.LOGIC1_TRUE;
			}
		}
		
		return OMSymbol.LOGIC1_FALSE;
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
