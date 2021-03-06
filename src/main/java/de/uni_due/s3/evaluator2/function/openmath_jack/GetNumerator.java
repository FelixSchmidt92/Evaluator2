package de.uni_due.s3.evaluator2.function.openmath_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the Nominator of the Division (a/b --> returns a)
 * 
 * @author dlux
 *
 */
public class GetNumerator extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ARITH1_DIVIDE)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Division");
		}
		
		OMA oma = (OMA) arguments.get(0);
		
		if (getDoubleSyntax(oma.getOmel().get(2)) == 0) {
			throw new FunctionInvalidArgumentException(this, "Denominator cannot be 0");
		}
		
		return OMToResultVisitor.getInstance().visit( ((OMA) arguments.get(0)).getOmel().get(1) );
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
