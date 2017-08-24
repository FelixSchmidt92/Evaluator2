package de.uni_due.s3.evaluator2.core.function.integer1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority.Priority;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements a modulus operation. Example: 3%5 = 3
 * 
 * @author frichtscheid
 *
 */
public class Remainder extends BinaryFunction {
	

	public Remainder() {
		super(OMSPriority.getPriority(OMSymbol.INTEGER1_REMAINDER));
	}

	/**
	 * Expects two argument of type OMI
	 * 
	 * @return OMI
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws OpenMathException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, OpenMathException {
		if (!(OMTypeChecker.isOMI(arguments.get(0)) && OMTypeChecker.isOMI(arguments.get(1))))
			throw new FunctionInvalidArgumentTypeException(this, "integer");

		try {
			if (OMUtils.convertOMToInteger(arguments.get(1)) == 0) {
				throw new FunctionInvalidArgumentException(this, "The Second Argument cannot be 0");
			}

			OMI left = (OMI) arguments.get(0);
			OMI right = (OMI) arguments.get(1);

			Double leftValue = OMUtils.convertOMToDouble(left);
			Double rightValue = OMUtils.convertOMToDouble(right);
			return OMUtils.convertDoubleToOMIOMF(leftValue % rightValue);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "integer");
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return getSageSyntax(arguments.get(0)) + " % " + getSageSyntax(arguments.get(1));
	}
	
	@Override
	public String getPartialLatexSyntax(List<String> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		
		return arguments.get(0)+"\\mathbin{\\%}"+arguments.get(1);
	}

}
