package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.math.BigInteger;
import java.util.List;


import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMOBJChildNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMObjectNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

public class GCD extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentException {
		Object left = arguments.get(0);
		Object right = arguments.get(1);
		if(left instanceof OMI && right instanceof OMI){
			BigInteger leftValue = BigInteger.valueOf(NumberUtils.valueOf((OMI) left));
			BigInteger rightValue = BigInteger.valueOf(NumberUtils.valueOf((OMI)right));
			return NumberUtils.convertDoubleToOMIOMF(leftValue.gcd(rightValue).doubleValue());
		}else {
			throw new FunctionInvalidArgumentException("Function 'gcd' accepts only integer values.");
		}
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws OMObjectNotSupportedException, OMOBJChildNotSupportedException, FunctionArgumentNumberException, NoRepresentationAvailableException {
		return "gcd("+getSageSyntax(arguments.get(0))+","+getSageSyntax(arguments.get(1));
	}

}
