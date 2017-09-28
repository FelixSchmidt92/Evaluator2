package de.uni_due.s3.evaluator2.core.visitor;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class OMToListVisitor extends OMToSyntaxVisitor<List<Object>> {

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
				omList.add(OMCreator.createOMSTR(element)); //vllt müssen wir hier auch alle teile parsen
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
	protected List<Object> getSyntaxRepresentationForFunction(ConstructorFunction function, OMS oms, List<Object> omel)
			throws EvaluatorException {
		try {
			return function.getPartialListSyntax(omel);
		} catch (NoRepresentationAvailableException e) {
			omel.add(0, oms);
			return omel;
		}
	}

}
