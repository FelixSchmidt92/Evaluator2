package de.uni_due.s3.evaluator.core.syntaxvisitor;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator.core.dictionaries.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMObjectNotSupportedException;

/**
 * This is a Template! For implementing an OMOBJ to CAS inherit this class.
 * Implement all methods and Add "2" Methods in Function.
 * 
 * One Method (protected) which references to your implemented Class and calls
 * visit.
 * 
 * Second Method (protected) which is implemented and throws an Error
 * (NoFunctionToCASException). This method should be implemented in some
 * Functions. Some means only the Function which should also be evaluated in
 * this specific CAS. (Matrix or Vector is a good candidate)
 * 
 * 
 * This Visitor Pattern visits all its Terminals and Functions. By calling the
 * method visit(omElement) the String representation will be returned for this
 * omElement including its childs.
 * 
 * @author dlux, spobel, frichtscheid
 *
 */
public abstract class OMToCasVisitor {

	/**
	 * visits the specific omElement. If another OMA is found the method in
	 * callInFunctionTheMethod is called. This method is called From the
	 * Function!
	 * 
	 * @param omElement
	 *            the Element to visit
	 * @return the String Representation of this omElement (including its
	 *         children!)
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException 
	 * @throws CasException
	 */
	public String visit(Object omElement)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		String result = "";
		
		if(omElement == null)
			throw new NoRepresentationAvailableException("No Representation available for a Null-Object");

		switch (omElement.getClass().getSimpleName()) {
		case "OMOBJ":
			result = visit((OMOBJ) omElement);
			break;
		case "OMF":
			result = visit((OMF) omElement);
			break;

		case "OMI":
			result = visit((OMI) omElement);
			break;

		case "OMS":
			result = visit((OMS) omElement);
			break;

		case "OMSTR":
			result = visit((OMSTR) omElement);
			break;

		case "OMV":
			result = visit((OMV) omElement);
			break;

		case "OMA":
			result = visit((OMA) omElement);
			break;

		default:
			throw new NoRepresentationAvailableException("Unable to visit omElement: " + omElement.toString());
		}

		return result;
	}

	/**
	 * This method calls the visit-Method for Object. It just extracts the
	 * containing OM* in OMOBJ. This method is NOT called from the Function!!
	 * 
	 * @param omobj
	 *            the OMOBJ-"Container"
	 * @return the String representation of this OMOBJ excluding OMOBJ!!
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException 
	 * @throws CasException
	 */
	private String visit(OMOBJ omobj)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		if (omobj != null) {
			if (omobj.getOMF() != null) {
				return visit(omobj.getOMF());
			}
			if (omobj.getOMI() != null) {
				return visit(omobj.getOMI());
			}
			if (omobj.getOMSTR() != null) {
				return visit(omobj.getOMSTR());
			}
			if (omobj.getOMV() != null) {
				return visit(omobj.getOMV());
			}
			if (omobj.getOMS() != null) {
				return visit(omobj.getOMS());
			}
			if (omobj.getOMA() != null) {
				return visit(omobj.getOMA());
			}
		}
		throw new NoRepresentationAvailableException("Unable to extract omElement from OMOBJ: " + omobj.toString());
	}

	/**
	 * Implement here the String-Representation of this OMF-Element
	 * 
	 * @param omf
	 *            the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMF omf);

	/**
	 * Implement here the String-Representation of this OMI-Element
	 * 
	 * @param omi
	 *            the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMI omi);

	/**
	 * Implement here the String-Representation of this OMS-Element
	 * 
	 * Note: If this oms is visited, it is an Parameter in a Function, so a
	 * Terminal like pi, e or i
	 * 
	 * @param oms
	 *            the element which is visited now
	 * @return the String-Representation
	 * @throws NoRepresentationAvailableException 
	 */
	protected abstract String visit(OMS oms) throws NoRepresentationAvailableException;

	/**
	 * Implement here the String-Representation of this OMSTR-Element
	 * 
	 * @param omstr
	 *            the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMSTR omstr);

	/**
	 * Implement here the String-Representation of this OMV-Element
	 * 
	 * @param omv
	 *            the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMV omv);

	/**
	 * An OMA is visited here. Here the visit searches for the specific Function
	 * and calls with this found Function callInFunctionTheMethod The List given
	 * to this method is beforehand checked if Parameters are correct
	 * 
	 * @param oma
	 *            the visited oma
	 * @return the String Representation of this oma and its childs
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidArgumentTypeException 
	 * @throws CasException
	 */
	private String visit(OMA oma) throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {

		
		List<Object> omel = new ArrayList<>();

		for (int i = 1; i < oma.getOmel().size(); i++) {
			omel.add(oma.getOmel().get(i)); // Deep Copy of List (oma.getOmel
											// should not be changed)
		}

		OMS oms = (OMS) oma.getOmel().get(0); // First element of OMA is always
												// an OMS
		Function function = OMSFunctionDictionary.getInstance().getFunction(oms);
		// Get function

		function.argsBetweenMinMax(omel); // Check for Correct amount of
											// Arguments
		return getCASRepresentationForFunction(function, omel);
	}

	/**
	 * Define here which Method in all available Evaluator-Functions should be
	 * called This Function should return the Representation of it in an String
	 * 
	 * @param function
	 *            the specific Function which is next to be called
	 * @param omel
	 *            the Functions Parameter (in Range as in minArgs and maxArgs
	 *            defined) Note: These Parameter are evaluated if
	 *            argumentsShouldBeEvaluated returns true. Not evaluated if it
	 *            returns false
	 * @return a String which is in CAS-Syntax
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException 
	 * @throws @throws
	 *             OMOBJChildNotSupportedException
	 * @throws OMObjectNotSupportedException
	 */
	protected abstract String getCASRepresentationForFunction(Function function, List<Object> omel)
			throws NoRepresentationAvailableException, FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException;

}