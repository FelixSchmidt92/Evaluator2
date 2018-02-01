package de.uni_due.s3.evaluator2.visitor.syntax;

import java.util.LinkedList;
import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.AbstractFunction;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.openmath.jaxb.OME;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * 
 * @author frichtscheid
 *
 */

public class OMToLatexVisitor extends OMToSyntaxVisitor<String> {

	private static OMToLatexVisitor visitor;
	
	private OMToLatexVisitor() throws EvaluatorException{ }
	
	public static OMToSyntaxVisitor<String> getInstance() throws EvaluatorException {
		if (visitor != null) {
			return visitor;
		}
		visitor = new OMToLatexVisitor();
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
		return omi.getValue();
	}

	/**
	 * Wraps a string in quotation marks. If the string is empty or has already
	 * latex commands, the string will not be wrapped.
	 */
	@Override
	protected String visit(OMSTR omstr) {
		return omstr.getContent();
	}

	/**
	 * Returns the name of the variable
	 */
	@Override
	protected String visit(OMV omv) {
		return omv.getName();
	}

	/**
	 * Returns the content of an OME as a String. No special latex commands are
	 * used, because mathjax can't handle them (see jack2).
	 * @throws OpenMathException 
	 */
	@Override
	protected String visit(OME ome) throws NoRepresentationAvailableException, OpenMathException {
		String messages = "";
		for (Object m : ome.getOMSOrOMVOrOMI()) {
			try {
				messages += visit(m);
				messages += " ";
			} catch (EvaluatorException ee) {
				throw new NoRepresentationAvailableException("No representation for :" + m.toString());
			}
		}
		return "\\mbox{" + messages + "}";
	}

	/**
	 * Differentiate between a normal Function and a BinaryFunction. If the function
	 * is a BinaryFunction and the arguments contain another binary operator we have
	 * to but the arguments in brackets for this function. In this case the children
	 * will be converted into string and will be handed over to the
	 * getPartialSyntax-Method.
	 * 
	 * In every getPartialLatexSyntax() in BinaryFunction it is not needed to call
	 * getLatexSyntax() on the arguments.
	 * @throws OpenMathException 
	 */
	@Override
	protected String getSyntaxRepresentationForFunction(AbstractFunction function, OMS oms, List<Object> omel)
			throws EvaluatorException, OpenMathException {
		try {
			return function.getPartialLatexSyntax(omel);
		} catch (NoRepresentationAvailableException nr) {
			// standard latex implementation for functions
			List<String> children = new LinkedList<String>();
			for (Object child : omel) {
				children.add(visit(child));
			}
			return "\\mbox{" + oms.getName() + "}\\left(" + String.join(",", children) + "\\right)";
		}
	}
}
