package de.uni_due.s3.evaluator2.core.syntaxvisitor;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;

public class OMToStringVisitor extends OMToCasVisitor {

	@Override
	protected String visit(OMF omf) {
		double value = omf.getDec();
		if (value == (int) value) {
			return Integer.toString((int)value);
		} else {
			return Double.toString(value);
		}	
	}

	@Override
	protected String visit(OMI omi) {
		return omi.getValue();
	}

	@Override
	protected String visit(OMS oms) throws NoRepresentationAvailableException {
		if (oms.equals(OMSymbol.LOGIC1_TRUE)) {
			return "True";
		} else if (oms.equals(OMSymbol.LOGIC1_FALSE)) {
			return "False";
		} else if (oms.equals(OMSymbol.NUMS1_PI)) {
			return "PI";
		} else if (oms.equals(OMSymbol.NUMS1_E)) {
			return "E";
		} else if (oms.equals(OMSymbol.NUMS1_NAN)) {
			return "NaN";
		} else if (oms.equals(OMSymbol.NUMS1_I)) {
			return "I";
		} else if (oms.equals(OMSymbol.NUMS1_INFINITY)) {
			return "Infinity";
		} else {
			throw new NoRepresentationAvailableException(
					"There is no String Representation for OMS cd: " + oms.getCd() + ", name: " + oms.getName());
		}

	}

	@Override
	protected String visit(OMSTR omstr) {
		return omstr.getContent();
	}

	@Override
	protected String visit(OMV omv) {
		return omv.getName();
	}

	@Override
	protected String getCASRepresentationForFunction(Function function, List<Object> omel)
			throws EvaluatorException {
		return function.getPartialStringSyntax(omel);
	}

}
