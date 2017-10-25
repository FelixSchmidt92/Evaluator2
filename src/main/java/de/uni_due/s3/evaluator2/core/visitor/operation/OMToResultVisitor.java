package de.uni_due.s3.evaluator2.core.visitor.operation;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
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

public class OMToResultVisitor extends OMToSyntaxVisitor<Object>{

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
	 * If the function (defined by the OMS) allows that
	 * Children of the type OMA can be visited, they will be visited and the Result
	 * of that will be used as an Argument instead of the OMA itself. All children of a
	 * OMA will be passed to the function as arguments and
	 * the function will be executed with evaluate.
	 * 
	 * @param oma
	 *            OpenMath application object <OMA>...</OMA>
	 * @return one of OMA,OMF,OMI,OMS,OMSTR or OMV. It depends on function used in
	 *         the OMS.
	 * @throws OpenMathException
	 */

	@Override
	protected Object getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel)
			throws EvaluatorException {
		for (int i = 0; i < omel.size(); i++) {
			Object o = omel.get(i);
			if (o instanceof OMA) {
				if (function.argumentsShouldBeEvaluated())
					omel.set(i, (visit((OMA) o)));
			}
		}
		try {
			return function.evaluate(omel);
		} catch (OpenMathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	/**
	 * Visits a OMOBJ object and return its child object. The Class of child is
	 * determined by the OMOBJ-Object. 
	 * 
	 * @param omobj
	 *            the whole OpenMath object starting with <OMOBJ>...
	 * @return OMOBJ result
	 * @throws EvaluatorException
	 * @throws OpenMathException
	 */
	public OMOBJ execute(OMOBJ omobj) throws EvaluatorException, OpenMathException {
		Object result = visit(omobj);
		return OMCreator.createOMOBJ(result);
	}

}
