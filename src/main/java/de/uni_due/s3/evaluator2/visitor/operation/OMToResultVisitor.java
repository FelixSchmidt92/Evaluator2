package de.uni_due.s3.evaluator2.visitor.operation;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.AbstractFunction;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * A visitor for the OpenMath-Structure, represented by classes in the OpenMath
 * package, like OMOBJ{@link de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ}. It is
 * used to traverse the OpenMath structure and do specific actions for every
 * type of OpenMath object.
 * 
 * This Visitor executes the Tree by visiting!
 * 
 * @author frichtscheid, dlux, spobel
 *
 */

public class OMToResultVisitor extends OMToSyntaxVisitor<Object> {

	private static OMToResultVisitor visitor;

	private OMToResultVisitor() throws EvaluatorException {
	}

	public static OMToResultVisitor getInstance() throws EvaluatorException {
		if (visitor != null) {
			return visitor;
		}
		visitor = new OMToResultVisitor();
		return visitor;
	}

	@Override
	protected Object visit(OMF omf) throws NoRepresentationAvailableException {
		return omf;
	}

	@Override
	protected Object visit(OMI omi) throws NoRepresentationAvailableException {
		return omi;
	}

	@Override
	protected Object visit(OMSTR omstr) throws NoRepresentationAvailableException {
		return omstr;
	}

	@Override
	protected Object visit(OMV omv) throws NoRepresentationAvailableException {
		return omv;
	}

	/**
	 * Visits a OpenMath Application Object (OMA).
	 * 
	 * If the function (defined by the OMS) allows that Children of the type OMA can
	 * be visited, they will be visited and the Result of that will be used as an
	 * Argument instead of the OMA itself. All children of a OMA will be passed to
	 * the function as arguments and the function will be executed with evaluate.
	 * 
	 * @param oma
	 *            OpenMath application object <OMA>...</OMA>
	 * @return one of OMA,OMF,OMI,OMS,OMSTR or OMV. It depends on function used in
	 *         the OMS.
	 * @throws OpenMathException
	 */

	@Override
	protected Object getSyntaxRepresentationForFunction(AbstractFunction function, OMS oms, List<Object> omel)
			throws EvaluatorException, OpenMathException {
		for (int i = 0; i < omel.size(); i++) {
			Object o = omel.get(i);
			if (o instanceof OMA) {
				if (function.argumentsShouldBeEvaluated())
					omel.set(i, (visit((OMA) o)));
			}
		}
		try {
			return function.evaluate(omel);
		} catch (FunctionInvalidArgumentTypeException e) {
			for (Object omelement : omel) {
				if (OMSymbol.isSymbolicExpression(omelement))
					continue;
				throw e;
			}
			try {
				return function.getPartialSymbolicSyntax(omel);
			} catch (FunctionInvalidArgumentTypeException e1) {
				throw e; // Throwing e again, if this Function is not implemented and does not need to,
							// so the initial cause is represented by the user
			}
		}
	}

}
