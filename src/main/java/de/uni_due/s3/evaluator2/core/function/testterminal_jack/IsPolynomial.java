package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;

/**
 * @Symja This Function works (mostly) as in Symja 
 * 
 *             This Function checks if in given Expression the given Variable
 *             (or Number) is a Polynom
 * 
 * @author dlux
 *
 */
public class IsPolynomial extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		if (arguments.get(0) instanceof OMSTR || arguments.get(1) instanceof OMSTR ) {
			throw new FunctionInvalidArgumentTypeException("(0)Polynom, (1)Variable");
		}
		if(visitArgs(arguments.get(0), arguments.get(1))) {
			return OMSymbol.LOGIC1_TRUE;
		}
		return OMSymbol.LOGIC1_FALSE;
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
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}

	private boolean visitArgs(Object obj, Object current) {
		if (obj instanceof OMA) {
			// Case OMA is argument
			boolean status = true;
			OMA oma = (OMA) obj;
			OMS oms = (OMS) oma.getOmel().get(0);
			for (int i = 1; i < oma.getOmel().size(); i++) {
				if (oma.getOmel().get(i) instanceof OMA) {
					// OMA in OMA visitArgs again
					status = visitArgs(oma.getOmel().get(i), current);
				} else if (oma.getOmel().get(i).equals(current)) {
					// current OM* equals argument check if OMA'S OMS is Polynom
					status = checkPolynomFunction(oms, i, oma.getOmel().get(i));
				}

				// If status set to false in one of these checks above false will be returned.
				// Somewhere current was not Polynomial
				if (!status) {
					return false;
				}

			}
		} else if (!(current instanceof OMV) && !(current instanceof OMI) && !(current instanceof OMF)) {
			// Case current is NOT OMV or OMI or OMF
			return false;
		}
		
		//last Case OMA has OM* in it (or not) which has to be Polynomial
		//and OM* is OMV OMI or OMF
		return true;
	}

	/**
	 * This method decides which {@link OMSymbol} can be seen as an Polynomial
	 * Function
	 * 
	 * @param oms
	 *            the {@link OMSymbol}
	 * @return true if Times, Divide ... false otherwise (like sqrt(x) or something
	 *         else)s
	 */
	private boolean checkPolynomFunction(OMS oms, int i, Object current) {
		if (oms.equals(OMSymbol.ARITH1_DIVIDE))
			return true;
		if (oms.equals(OMSymbol.ARITH1_MINUS))
			return true;
		if (oms.equals(OMSymbol.ARITH1_PLUS))
			return true;
		if (oms.equals(OMSymbol.ARITH1_POWER) && (i == 1 || !(current instanceof OMV)))
			//iff second arg is OMV then false!
			return true;
		if (oms.equals(OMSymbol.ARITH1_UNARY_MINUS))
			return true;
		if (oms.equals(OMSymbol.ARITH1_TIMES))
			return true;

		return false;

	}

}
