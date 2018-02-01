package de.uni_due.s3.evaluator2.visitor.operation;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.AbstractFunction;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

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

	protected Integer visit(OMS oms) throws EvaluatorException, OpenMathException {
		return getSyntaxRepresentationForFunction(null, oms, new ArrayList<>());
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
	protected Integer visit(OMA oma) throws OpenMathException, EvaluatorException {
		if (OMTypeChecker.isOMA(oma.getOmel().get(0))) {
			// Change Structure, so that always an OMS is the first argument in an OMA
			OMA second = (OMA) oma.getOmel().get(0);

			Object secondinner = second.getOmel().get(1);

			oma.getOmel().set(0, secondinner);

			second.getOmel().set(1, oma);

			oma = second;
		}

		List<Object> omel = new ArrayList<>();

		for (int i = 1; i < oma.getOmel().size(); i++) {
			omel.add(oma.getOmel().get(i)); // Deep Copy of List (oma.getOmel
											// should not be changed)
		}
		OMS oms = null;
		try {
			oms = (OMS) oma.getOmel().get(0); // First element of OMA is always OMS
		} catch (ClassCastException e) {
			throw new EvaluatorException("OpenMath-Object is invalid!", e);
		}
		Integer result = 0;
		result++; //OMA (this)
		return result + getSyntaxRepresentationForFunction(null, oms, omel);
	}
	
	@Override
	protected Integer getSyntaxRepresentationForFunction(AbstractFunction function, OMS oms, List<Object> omel) throws OpenMathException {
		Integer result = 0;
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
