package de.uni_due.s3.evaluator2.core.visitor.primitve;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class OMToListVisitor extends OMToSyntaxVisitor<List<Object>> {

	private static OMToListVisitor visitor;
	
	private OMToListVisitor() throws EvaluatorException { }

	public static OMToSyntaxVisitor<List<Object>> getInstance() throws EvaluatorException {
		if (visitor != null) {
			return visitor;
		}
		visitor = new OMToListVisitor();
		return visitor;
	}
	
	@Override
	protected List<Object> visit(OMF omf) {
		List<Object> omList = new ArrayList<>();
		omList.add(omf);
		return omList;
	}

	@Override
	protected List<Object> visit(OMI omi) {
		List<Object> omList = new ArrayList<>();
		omList.add(omi);
		return omList;
	}

	@Override
	protected List<Object> visit(OMSTR omstr) throws NoRepresentationAvailableException {
		if (omstr.getContent().contains(";")) {
			List<Object> omList = new ArrayList<>();
			for (String element : omstr.getContent().split(";")) {
				omList.add(OMCreator.createOMSTR(element));
			}
			return omList;
		} else {
			List<Object> omList = new ArrayList<>();
			omList.add(omstr);
			return omList;
		}
	}
	
	@Override
	protected List<Object> visit(OMV omv) throws NoRepresentationAvailableException {
		List<Object> omList = new ArrayList<>();
		omList.add(omv);
		return omList;
	}

	@Override
	protected List<Object> getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel)
			throws EvaluatorException {
		if (omel == null) {
			omel = new ArrayList<>();
		}
		try {
			return function.getPartialListSyntax(omel);
		} catch (NoRepresentationAvailableException e) {
			omel.add(0, oms);
			return omel;
		}
	}

}
