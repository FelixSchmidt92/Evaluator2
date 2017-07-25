package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator.core.PolyUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Tests if a given polynomial depends on a specified variable Example:
 * dependsOn("a+1","a") => true
 * 
 * @author frichtscheid
 *
 */
public class DependsOn extends Function {

	/**
	 * Expects two arguments of type String The first one is the polynomial, the
	 * second one should contain the dependend variable
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {

		//FIXME hier sollte ein Ausdruck und kein String erwartet werden !
		if (!OMTypeChecker.isOMSTR(arguments.get(0)) || !OMTypeChecker.isOMSTR(arguments.get(1))) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String, (1)String");
		}
		
		Set<String> variables = PolyUtils.getVariables(((OMSTR) arguments.get(0)).getContent());
		String variable = ((OMSTR) arguments.get(1)).getContent();

		return (variables.contains(variable)) ? OMSymbol.LOGIC1_TRUE : OMSymbol.LOGIC1_FALSE;
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

}
