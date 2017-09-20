package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This function Checks if the Two given Sets of Vectors have the same Basis.
 * 
 * 
 * If they have true will be returned, false otherwise.
 * 
 * @author dlux
 *
 */
public class EqualBasis extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {

		List<Object> list1 = getListSyntax(arguments.get(0));
		List<Object> list2 = getListSyntax(arguments.get(1));
		getIntegerSyntax(arguments.get(2)); //throws exception if not
		
		for (Object vector : list1) {
			if (!OMTypeChecker.isOMAWithSymbol(vector, OMSymbol.LINALG2_VECTOR)) {
				throw new FunctionInvalidArgumentException(this, "The sets can only contain Vectors for this Function");
			}
		}

		for (Object vector : list2) {
			if (!OMTypeChecker.isOMAWithSymbol(vector, OMSymbol.LINALG2_VECTOR)) {
				throw new FunctionInvalidArgumentException(this, "The sets can only contain Vectors for this Function");
			}
		}

		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));

		if (!(OMTypeChecker.isOMS(result)
				&& (((OMS) result).equals(OMSymbol.LOGIC1_TRUE) || ((OMS) result).equals(OMSymbol.LOGIC1_FALSE)))) {
			throw new InvalidResultTypeException(this, "true, false");
		}
		return result;
	}

	@Override
	protected int minArgs() {
		return 3;
	}

	@Override
	protected int maxArgs() {
		return 3;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return "V=QQ^" + getSageSyntax(arguments.get(2)) + ";U=V.span(" + getSageSyntax(arguments.get(0))
				+ ");W=V.span(" + getSageSyntax(arguments.get(1)) + ");U==W";
	}
}
