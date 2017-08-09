package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator.core.PolyUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMV;
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
	 * Expects two arguments.The first one is the polynomial (OMA), the
	 * second one should contain the dependent variable (OMSTR)
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		//Special Case if Only 1 Element is Polynom!
		if (arguments.get(0) instanceof OMV && arguments.get(1) instanceof OMV) {
			System.out.println(arguments.get(0).equals(arguments.get(1)));
			if (arguments.get(0).equals(arguments.get(1))) {
				return OMSymbol.LOGIC1_TRUE;
			}
		}
		
		//Special Case Term without Variables
		if (!OMTypeChecker.isOMA(arguments.get(0)) && OMTypeChecker.isOMV(arguments.get(1))) {
			if (arguments.get(0) instanceof OMI || arguments.get(0) instanceof OMF)
				return OMSymbol.LOGIC1_FALSE;
		}
		
		if (!OMTypeChecker.isOMA(arguments.get(0)) || !OMTypeChecker.isOMV(arguments.get(1))) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Term, (1)Char");
		}
		
		Set<String> variables = PolyUtils.getVariables((OMA) arguments.get(0));
		String variable = ((OMV) arguments.get(1)).getName();

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
