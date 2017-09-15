package de.uni_due.s3.evaluator2.core.visitor;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;

public class OMToIntegerVisitor extends OMToSyntaxVisitor<Integer> {

	@Override
	protected Integer visit(OMF omf) throws NoRepresentationAvailableException {
		double val = omf.getDec();
		if (val == (int) val)
			return (int) val;
		else
			throw new NoRepresentationAvailableException("Unable to visit omElement: " + omf);
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
			throw new NoRepresentationAvailableException("Unable to visit omElement: " + omstr);
		}
	}

	@Override
	protected Integer getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel)
			throws EvaluatorException {
		return function.getPartialIntegerSyntax(omel);
	}

}
