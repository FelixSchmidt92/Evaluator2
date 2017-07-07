package de.uni_due.s3.evaluator.core.function;

import java.util.List;

import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;

public class OMToSageVisitor extends OMToCasVisitor {

	@Override
	protected String visit(OMF omf) {
		return Double.toString(omf.getDec());
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
			return "pi";
		} else if (oms.equals(OMSymbol.NUMS1_E)) {
			return "e";
		} else if (oms.equals(OMSymbol.NUMS1_NAN)) {
			return "NaN";
		} else if (oms.equals(OMSymbol.NUMS1_I)) {
			return "I";
		} else if (oms.equals(OMSymbol.NUMS1_INFINITY)) {
			return "Infinity";
		} else {
			throw new NoRepresentationAvailableException(
					"There is no Sage Representation for OMS cd: " + oms.getCd() + ", name: " + oms.getName());
		}

	}

	@Override
	protected String visit(OMSTR omstr) {
		return "'" + omstr.getContent() + "'";
	}

	@Override
	protected String visit(OMV omv) {
		return omv.getName();
	}

	@Override
	protected String getCASRepresentationForFunction(Function function, List<Object> omel)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		return function.getPartialSageSyntax(omel);
	}

}
