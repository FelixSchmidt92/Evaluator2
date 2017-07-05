package de.uni_due.s3.evaluator.core.function;

import java.util.List;

import org.openmath.openmath.OMF;
import org.openmath.openmath.OMI;
import org.openmath.openmath.OMS;
import org.openmath.openmath.OMSTR;
import org.openmath.openmath.OMV;

import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

public class OMToSageVisitor extends OMToCasVisitor {
	
	@Override
	protected String visit(OMF omf) {
		return Double.toString(omf.getDec()) ;
	}

	@Override
	protected String visit(OMI omi) {
		return   omi.getValue();
	}

	@Override
	protected String visit(OMS oms) {
		String cd = oms.getCd();
		String name = oms.getName();
		
		//FIXME dlux vielleicht auslagern und terminale verallgemeineren?
		
		if (cd.equals("logic1") && name.equals("true")){
			return "True";
		}
		if (cd.equals("logic1") && name.equals("false")){
			return "False";
		}
		if (cd.equals("nums1") && name.equals("pi")){
			return "pi";
		}
		if (cd.equals("nums1") && name.equals("e")){
			return "e";
		}
		if (cd.equals("nums1") && name.equals("NaN")){
			return "NaN";
		}
		if (cd.equals("nums1") && name.equals("i")){
			return "I";
		}
		if (cd.equals("nums1") && name.equals("infinity")){
			return "Infinity";
		}
		return null;
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
	protected String getCASRepresentationForFunction(Function function, List<Object> omel) throws FunctionArgumentNumberException, NoRepresentationAvailableException, CasException {
		return function.getPartialSageSyntax(omel);
	}



	
}
