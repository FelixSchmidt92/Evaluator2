package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

public class Modulus extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getPartialSage(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException{
		return getSageSyntax(arguments.get(0)) + " % " + getSageSyntax(arguments.get(1));
	}

}
