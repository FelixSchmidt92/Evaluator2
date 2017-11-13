package de.uni_due.s3.evaluator2.core.visitor.operation;

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

public class OMCountVisitor extends OMToSyntaxVisitor<Integer> {

	private static OMCountVisitor visitor;
	
	private OMCountVisitor() throws EvaluatorException { }

	public static OMToSyntaxVisitor<Integer> getInstance() throws EvaluatorException {
		if (visitor != null) {
			return visitor;
		}
		visitor = new OMCountVisitor();
		return visitor;
	}
	
	@Override
	protected Integer visit(OMF omf) {
		return 1;
	}

	@Override
	protected Integer visit(OMI omi) {
		return 1;
	}

	@Override
	protected Integer visit(OMSTR omstr) throws NoRepresentationAvailableException {
		return 1;
	}

	@Override
	protected Integer visit(OMV omv) throws NoRepresentationAvailableException {
		return 1;
	}

	@Override
	protected Integer getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel) {
		Integer result = 0;
		result++; //OMA
		result++; //OMS (this)
		for (Object omObj : omel) {
			try {
				result += visit(omObj);
			} catch (EvaluatorException e) {
				// do nothing
			}
		}
		return result;
	}

}
