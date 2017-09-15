package de.uni_due.s3.evaluator2.core.visitor;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;

public class OMToBooleanVisitor extends OMToSyntaxVisitor<Boolean> {

	@Override
	protected Boolean visit(OMF omf) {
		return (omf.getDec() > 0);
	}

	@Override
	protected Boolean visit(OMI omi) {
		return (Integer.parseInt(omi.getValue()) > 0);
	}

	@Override
	protected Boolean visit(OMSTR omstr) {
		return new Boolean(omstr.getContent());
	}

	@Override
	protected Boolean getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel)
			throws EvaluatorException {
		try {
			return function.getPartialBooleanSyntax(omel);
		} catch (NoRepresentationAvailableException e) {
			return false;
		}
	}

}
