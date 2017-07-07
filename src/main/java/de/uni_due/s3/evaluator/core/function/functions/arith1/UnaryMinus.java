package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openMath unary_minus for numbers
 * Example: -3, -4.56
 * @author frichtscheid
 *
 */
public class UnaryMinus extends Function{

	/**
	 * Expects one argument of type OMI or OMF
	 * @return OMI or OMF
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		// TODO Auto-generated method stub
		if(! OMTypeChecker.isOMFOrOMI(arguments.get(0)))
			throw new FunctionInvalidArgumentTypeException(this,"integer, float, double");
		
		if( OMTypeChecker.isOMF(arguments.get(0))){
			OMF omf = (OMF) arguments.get(0);
			return OMCreator.createOMF(omf.getDec()*-1);
		}else{
			OMI omi = (OMI) arguments.get(0);
			return OMCreator.createOMI(Integer.parseInt(omi.getValue())*-1);
		}
			
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		// TODO Auto-generated method stub
		return "-"+getSageSyntax(arguments.get(0));
	}

}
