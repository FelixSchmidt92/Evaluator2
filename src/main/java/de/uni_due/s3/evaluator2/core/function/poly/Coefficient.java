package de.uni_due.s3.evaluator2.core.function.poly;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns Coefficient of polynom to specified n. (2*x^3+x,x,3) -> 2;
 * (2*x^3+x,x,1) -> 1; (2*x^3+x,x,0) -> 0;
 * 
 * @author spobel
 *
 */
public class Coefficient extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (getStringSyntax(arguments.get(1)).equals("")
				||getStringSyntax(arguments.get(2)).equals("")) {
			throw new FunctionInvalidArgumentTypeException("(0)Expression, (1)Variable, (2)Integer");
		}

		return Sage.evaluateInCAS(getPartialSageSyntax(arguments));
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
		String polynom = getSageSyntax(arguments.get(0));
		String var = getSageSyntax(arguments.get(1));
		String n = getSageSyntax(arguments.get(2));

		String sageVar = Sage.getSagePreVariable(polynom + var);

		StringBuilder sb = new StringBuilder();
		sb.append(sageVar);
		sb.append("f = ");
		sb.append(polynom);
		sb.append("; f.coefficient(");
		sb.append(var);
		sb.append(",");
		sb.append(n);
		sb.append(")");

		return sb.toString();
	}

}
