package de.uni_due.s3.evaluator2.core.function.openmath_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the Denominator of the Division (a/b --> returns b)
 * 
 * @author dlux
 *
 */
public class GetDenominator extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ARITH1_DIVIDE) && 
				!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.NUMS1_RATIONAL)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Division");
		}
		
		OMA oma = (OMA) arguments.get(0);
		if (getDoubleSyntax(oma.getOmel().get(2)) == 0) {
			throw new FunctionInvalidArgumentException(this, "Denominator cannot be 0");
		}
		return OMToResultVisitor.getInstance().visit(((OMA) arguments.get(0)).getOmel().get(2));

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
	
	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		OMA oma = (OMA) arguments.get(0);
		return Double.valueOf(getDoubleSyntax(oma.getOmel().get(2)));
	}

}
