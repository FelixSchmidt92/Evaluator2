package de.uni_due.s3.evaluator2.core.visitor;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;

public class OMToRVisitor extends OMToSyntaxVisitor<String> {

	@Override
	protected String visit(OMF omf) {
		double value = omf.getDec();
		if (value == (int) value) {
			return Integer.toString((int) value);
		} else {
			return Double.toString(value);
		}
	}

	@Override
	protected String visit(OMI omi) {
		return omi.getValue();
	}

	@Override
	protected String visit(OMSTR omstr) {
		if (omstr.getContent().equals("")) {
			return "''";
		} else {
			return omstr.getContent();
		}
	}

	@Override
	protected String visit(OMV omv) {
		return omv.getName();
	}

	@Override
	protected String getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel)
			throws EvaluatorException {
		return function.getPartialRSyntax(omel);
	}

}
