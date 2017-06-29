package de.uni_due.s3.evaluator.core.function;

import java.util.List;

import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.JAXBOpenMath.openmath.OMSTR;
import de.uni_due.s3.JAXBOpenMath.openmath.OMV;

public class SageVisitor extends Visitor {
	
	@Override
	protected String visit(OMF omf) {
		return "RealNumber(" + omf.getDec() + ")";
	}

	@Override
	protected String visit(OMI omi) {
		return  "Integer(" + omi.getValue() + ")";
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
		return "var('" + omv.getName() +"')";
	}

	@Override
	protected String callInFunctionTheMethod(Function function, List<Object> omel) {
		return function.getPartialSageSyntax(omel);
	}



	
}
