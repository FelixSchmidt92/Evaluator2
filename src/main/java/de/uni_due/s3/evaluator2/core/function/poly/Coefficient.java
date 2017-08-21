package de.uni_due.s3.evaluator2.core.function.poly;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.PolyUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
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
		try {
			if (OMUtils.convertOMToString(arguments.get(1)).equals("")
					|| OMUtils.convertOMToString(arguments.get(2)).equals("")) {
				throw new FunctionInvalidArgumentTypeException("(0)Expression, (1)Variable, (2)Integer");
			}

		} catch (InputMismatchException e) {
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

		String sageVar = PolyUtils.getSageSyntaxVariableRepresentation(polynom + var);

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
