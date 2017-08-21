package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.jaxb.OMA;
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

		if (!OMTypeChecker.isOMNumber(arguments.get(2))) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set of vectors (1)Set of vectors (2)Integer");
		}

		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SET1_SET)
				&& !OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.LIST1_LIST))
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set of vectors (1)Set of vectors (2)Integer");

		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.SET1_SET)
				&& !OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.LIST1_LIST))
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set of vectors (1)Set of vectors (2)Integer");

		OMA left = (OMA) arguments.get(0);
		OMA right = (OMA) arguments.get(1);

		// Check if sets/lists contain only Vectors!
		for (int i = 1; i < left.getOmel().size(); i++) {
			if (!OMTypeChecker.isOMAWithSymbol(left.getOmel().get(i), OMSymbol.LINALG2_VECTOR)) {
				throw new FunctionInvalidArgumentException(this, "The sets can only contain Vectors for this Function");
			}
		}

		for (int i = 1; i < right.getOmel().size(); i++) {
			if (!OMTypeChecker.isOMAWithSymbol(right.getOmel().get(i), OMSymbol.LINALG2_VECTOR)) {
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
