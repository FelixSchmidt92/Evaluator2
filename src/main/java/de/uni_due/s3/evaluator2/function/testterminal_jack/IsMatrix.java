package de.uni_due.s3.evaluator2.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
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
public class IsMatrix extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.LINALG2_MATRIX)) {
			OMA matrix = (OMA) arguments.get(0);

			if (matrix.getOmel().size() <= 1)
				return OMSymbol.LOGIC1_FALSE;

			for (int i = 1; i < matrix.getOmel().size(); i++) {
				if (!OMTypeChecker.isOMAWithSymbol(matrix.getOmel().get(i), OMSymbol.LINALG2_MATRIXROW,
						OMSymbol.LINALG2_VECTOR))
					return OMSymbol.LOGIC1_FALSE;
			}
		} else {
			return OMSymbol.LOGIC1_FALSE;
		}
		return OMSymbol.LOGIC1_TRUE;
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}
}
