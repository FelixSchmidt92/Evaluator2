package de.uni_due.s3.evaluator2.core.visitor;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;

public class OMToDoubleVisitor extends OMToSyntaxVisitor<Double> {

	@Override
	protected Double visit(OMF omf) {
		return omf.getDec();
	}

	@Override
	protected Double visit(OMI omi) {
		return Double.valueOf(omi.getValue());
	}

	@Override
	protected Double visit(OMSTR omstr) throws NoRepresentationAvailableException {
		try {
			return Double.valueOf(omstr.getContent());
		} catch (NumberFormatException e) {
			throw new NoRepresentationAvailableException("Unable to visit omElement: " + omstr);
		}
	}

	@Override
	protected Double getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel)
			throws EvaluatorException {
		return function.getPartialDoubleSyntax(omel);
	}

}