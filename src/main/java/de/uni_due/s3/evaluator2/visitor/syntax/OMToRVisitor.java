package de.uni_due.s3.evaluator2.visitor.syntax;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class OMToRVisitor extends OMToSyntaxVisitor<String> {

	private static OMToRVisitor visitor;
	
	private OMToRVisitor() throws EvaluatorException { }
	
	
	public static OMToSyntaxVisitor<String> getInstance() throws EvaluatorException {
		if (visitor != null) {
			return visitor;
		}
		visitor = new OMToRVisitor();
		return visitor;
	}

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
		//remove leading, this might result in Error in Sage (Sage thinks its octaval)
		return String.valueOf(Integer.valueOf(omi.getValue()));
	}

	@Override
	protected String visit(OMSTR omstr) {
		if (omstr.getContent().equals("")) {
			return "\"\""; //return an empty String
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
			throws EvaluatorException, OpenMathException {
		return function.getPartialRSyntax(omel);
	}



}
