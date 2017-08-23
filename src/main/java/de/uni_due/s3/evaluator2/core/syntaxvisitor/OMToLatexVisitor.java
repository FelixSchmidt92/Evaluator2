package de.uni_due.s3.evaluator2.core.syntaxvisitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSFunctionDictionary;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;

/**
 * 
 * @author frichtscheid
 *
 */

public class OMToLatexVisitor extends OMToCasVisitor{

	@Override
	protected String visit(OMF omf) {
		double value = omf.getDec();
		if (value == (int) value) {
			return Integer.toString((int)value);
		} else {
			return Double.toString(value);
		}	
	}

	@Override
	protected String visit(OMI omi) {
		return omi.getValue();
	}

	@Override
	protected String visit(OMS oms) throws NoRepresentationAvailableException {
		if (oms.equals(OMSymbol.LOGIC1_TRUE)) {
			return "True";
		} else if (oms.equals(OMSymbol.LOGIC1_FALSE)) {
			return "False";
		} else if (oms.equals(OMSymbol.NUMS1_PI)) {
			return "\\pi";
		} else if (oms.equals(OMSymbol.NUMS1_E)) {
			return "e";
		} else if (oms.equals(OMSymbol.NUMS1_NAN)) {
			return "NaN";
		} else if (oms.equals(OMSymbol.NUMS1_I)) {
			return "I";
		} else if (oms.equals(OMSymbol.NUMS1_INFINITY)) {
			return "\\infty";
		} else {
			throw new NoRepresentationAvailableException(
					"There is no Sage Representation for OMS cd: " + oms.getCd() + ", name: " + oms.getName());
		}

	}

	@Override
	protected String visit(OMSTR omstr) {
		return  omstr.getContent();
	}

	@Override
	protected String visit(OMV omv) {
		return omv.getName();
	}

	/**
	 * Differentiate between a normal Function and a BinaryFunction. If the function is a
	 * BinaryFunction and the arguments contain another binary operator we have to but 
	 * the arguments in brackets for this function. In this case the children will be 
	 * converted into string and will be handed over to the getPartialSyntax-Method.
	 * 
	 * In every getPartialLatexSyntax() in BinaryFunction it is not needed to call getLatexSyntax() on the arguments.  
	 * TODO: weiter kommentieren
	 */
	@Override
	protected String getCASRepresentationForFunction(Function function, List<Object> omel)
			throws EvaluatorException {
		
		if(function instanceof BinaryFunction) {
			List<String> children = new ArrayList<String>(2);
			BinaryFunction parent = (BinaryFunction) function;
			
			children.add(getLatexSyntaxFromChild(omel.get(0), parent));
			children.add(getLatexSyntaxFromChild(omel.get(1),parent));
			return function.getPartialLatexSyntax(children);
			
		}else {
			List<String> children = new LinkedList<String>();
			for(Object child : omel) {
				children.add(visit(child));
			}
			return function.getPartialLatexSyntax(children);
		}
	}
	
	/**
	 * Put child in brackets if needed and calls the getPartialLatexSyntax() for this children. 
	 * ( if the priority of the child-operator is greater than the priority of the parent)
	 * 
	 * @param obj
	 * @param parent
	 * @return
	 * @throws EvaluatorException
	 */
	private String getLatexSyntaxFromChild(Object obj,BinaryFunction parent) throws EvaluatorException {
		//if the child is an OMA check their priority
		if(obj instanceof OMA) {
			OMA child = (OMA)obj;
			List<String> childOmel = new ArrayList<String>(child.getOmel().size()-1);
			OMS childOMS = (OMS) child.getOmel().get(0);
			Function childFunc = OMSFunctionDictionary.getInstance().getFunction(childOMS);
			
			for(int i=1;i<child.getOmel().size();i++) {
				childOmel.add(visit(child.getOmel().get(i)));
			}
						
			if(childFunc instanceof BinaryFunction) {
				if(((BinaryFunction) childFunc).priority.compareTo(parent.priority)>0){
					return"\\left("+childFunc.getPartialLatexSyntax(childOmel)+"\\right)";
				}else {
					return childFunc.getPartialLatexSyntax(childOmel);
				}
			}else {
				return childFunc.getPartialLatexSyntax(childOmel);
			}
		}else {
			return visit(obj);
		}
	}

}
