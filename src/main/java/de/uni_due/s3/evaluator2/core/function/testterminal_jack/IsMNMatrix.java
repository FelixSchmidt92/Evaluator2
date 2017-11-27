package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * @Symja This Function works (mostly) as in Symja
 * 
 *        This Function checks if in given Expression the given Variable (or
 *        Number) is a Polynom
 * 
 * @author dlux
 *
 */
public class IsMNMatrix extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		int m = getIntegerSyntax(arguments.get(1));
		int n = getIntegerSyntax(arguments.get(2));

		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.LINALG2_MATRIX)) {
			return OMSymbol.LOGIC1_FALSE;
		}

		OMA matrix = (OMA) arguments.get(0);
		
		if (matrix.getOmel().size() - 1 != m) {
			return OMSymbol.LOGIC1_FALSE;
		}

		for (int i = 1; i < matrix.getOmel().size(); i++) {
			
			if (!OMTypeChecker.isOMAWithSymbol(matrix.getOmel().get(i), OMSymbol.LINALG2_MATRIXROW, OMSymbol.LINALG2_VECTOR)
					|| ((OMA) matrix.getOmel().get(i)).getOmel().size() - 1 != n) {
				return OMSymbol.LOGIC1_FALSE;
			}
		}
		return OMSymbol.LOGIC1_TRUE;
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
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}
}
