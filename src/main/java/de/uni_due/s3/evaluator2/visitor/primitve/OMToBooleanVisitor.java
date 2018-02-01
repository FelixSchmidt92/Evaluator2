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

public class OMToBooleanVisitor extends OMToSyntaxVisitor<Boolean> {

	private static OMToBooleanVisitor visitor;
	
	private OMToBooleanVisitor() throws EvaluatorException { }

	public static OMToSyntaxVisitor<Boolean> getInstance() throws EvaluatorException {
		if (visitor != null) {
			return visitor;
		}
		visitor = new OMToBooleanVisitor();
		return visitor;
	}
	
	@Override
	protected Boolean visit(OMF omf) {
		return (omf.getDec() > 0);
	}

	@Override
	protected Boolean visit(OMI omi) {
		return (Integer.parseInt(omi.getValue()) > 0);
	}

	@Override
	protected Boolean visit(OMSTR omstr) throws NoRepresentationAvailableException {
		throw new NoRepresentationAvailableException("Function expects Boolean instead of String:" + omstr.getContent());
	}

	@Override
	protected Boolean visit(OMV omv) throws NoRepresentationAvailableException {
		throw new NoRepresentationAvailableException("Function expects Boolean instead of Variable:" + omv.getName());
	}

	@Override
	protected Boolean getSyntaxRepresentationForFunction(AbstractFunction function, OMS oms, List<Object> omel)
			throws EvaluatorException, OpenMathException {
		try {
			return function.getPartialBooleanSyntax(omel);
		} catch (NoRepresentationAvailableException e) {
			return false;
		}
	}

}
