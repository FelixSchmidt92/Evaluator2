package de.uni_due.s3.evaluator2.visitor.primitve;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.AbstractFunction;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class OMToIntegerVisitor extends OMToSyntaxVisitor<Integer> {

	private static OMToIntegerVisitor visitor;
	
	private OMToIntegerVisitor() throws EvaluatorException { }

	public static OMToSyntaxVisitor<Integer> getInstance() throws EvaluatorException {
		if (visitor != null) {
			return visitor;
		}
		visitor = new OMToIntegerVisitor();
		return visitor;
	}
	
	@Override
	protected Integer visit(OMF omf) throws NoRepresentationAvailableException {
		double val = omf.getDec();
		if (val == (int) val)
			return (int) val;
		else
			throw new NoRepresentationAvailableException("Function expects Integer instead of Double: " + omf.getDec());
	}

	@Override
	protected Integer visit(OMI omi) {
		return Integer.parseInt(omi.getValue());
	}

	@Override
	protected Integer visit(OMSTR omstr) throws NoRepresentationAvailableException {
		try {
			return Integer.valueOf(omstr.getContent());
		} catch (NumberFormatException e) {
			throw new NoRepresentationAvailableException("Function expects Integer instead of String: " + omstr.getContent());
		}
	}

	@Override
	protected Integer visit(OMV omv) throws NoRepresentationAvailableException {
		try {
			return Integer.valueOf(omv.getName());
		} catch (NumberFormatException e) {
			throw new NoRepresentationAvailableException("Function expects Integer instead of Variable: " + omv.getName());
		}
	}

	@Override
	protected Integer getSyntaxRepresentationForFunction(AbstractFunction function, OMS oms, List<Object> omel)
			throws EvaluatorException, OpenMathException {
		return function.getPartialIntegerSyntax(omel);
	}

}
