package de.uni_due.s3.evaluator2.function.openmath_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Function counts Basic Operations, like + - * and /
 * 
 * this does not Count the Unary Operator -
 * 
 * @author dlux
 *
 */
public class CountBasicOperations extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {

		if (arguments.size() == 1) {
			return OMCreator.createOMI(countAll(arguments.get(0), 0));

		} else {
			if (!OMTypeChecker.isOMSTR(arguments.get(0)))
				throw new FunctionInvalidArgumentTypeException("[(0)BasicOperationInString], (1)Expression");

			switch (((OMSTR) arguments.get(0)).getContent()) {

			case "+":
				return OMCreator.createOMI(countSpecificOne(arguments.get(1), 0, OMSymbol.ARITH1_PLUS));

			case "-":
				return OMCreator.createOMI(countSpecificOne(arguments.get(1), 0, OMSymbol.ARITH1_MINUS));
				
			case "*":
				return OMCreator.createOMI(countSpecificOne(arguments.get(1), 0, OMSymbol.ARITH1_TIMES));
				
			case "/":
				return OMCreator.createOMI(countSpecificOne(arguments.get(1), 0, OMSymbol.ARITH1_DIVIDE) 
						+ countSpecificOne(arguments.get(1), 0, OMSymbol.NUMS1_RATIONAL));
			default:
				throw new FunctionInvalidArgumentException(this,
						"The First Parameter hast to bei '+', '-', '*' or '/'");
			}
		}

	}

	private int countAll(Object arg, int counter) {
		if (OMTypeChecker.isOMA(arg)) {
			OMA oma = (OMA) arg;
			if (oma.getOmel().get(0).equals(OMSymbol.ARITH1_PLUS) || oma.getOmel().get(0).equals(OMSymbol.ARITH1_MINUS)
					|| oma.getOmel().get(0).equals(OMSymbol.ARITH1_TIMES)
					|| oma.getOmel().get(0).equals(OMSymbol.ARITH1_DIVIDE)
					|| oma.getOmel().get(0).equals(OMSymbol.NUMS1_RATIONAL)) {
				counter += 1;
			}
			for (int i = 1; i < oma.getOmel().size(); i++) {
				counter += countAll(oma.getOmel().get(i), 0);
			}
		}
		return counter;
	}

	private int countSpecificOne(Object arg, int counter, OMS oms) {
		if (OMTypeChecker.isOMA(arg)) {
			OMA oma = (OMA) arg;
			if (oma.getOmel().get(0).equals(oms)) {
				counter += 1;	
			}
			for (int i = 1; i < oma.getOmel().size(); i++) {
				counter += countSpecificOne(oma.getOmel().get(i), 0, oms);
			}
		}
		return counter;
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}
}
