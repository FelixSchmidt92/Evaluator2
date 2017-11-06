package de.uni_due.s3.evaluator2.core.visitor;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSFunctionDictionary;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OME;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * This is a Template! For implementing an OMOBJ to Something inherit this
 * class. Implement all methods and Add "2" Methods in Function.
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
 * method visit(omElement) the T representation will be returned for this
 * omElement including its childs.
 * 
 * @author dlux, spobel, frichtscheid
 *
 */
public abstract class OMToSyntaxVisitor<T> {

	/**
	 * visits the specific omElement. If another OMA is found the method in
	 * callInFunctionTheMethod is called. This method is called From the Function!
	 * 
	 * @param omElement
	 *            the Element to visit
	 * @return the String Representation of this omElement (including its children!)
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws FunctionInvalidArgumentException
	 * @throws CasException
	 */
	public T visit(Object omElement) throws EvaluatorException {
		if (omElement == null)
			throw new NoRepresentationAvailableException(
					"No Representation available for a Null-Object. Function got null instead of a parameter.");

		switch (omElement.getClass().getSimpleName()) {
		case "OMOBJ":
			return visit((OMOBJ) omElement);
		case "OMF":
			return visit((OMF) omElement);
		case "OMI":
			return visit((OMI) omElement);
		case "OMS":
			return visit((OMS) omElement);
		case "OMSTR":
			return visit((OMSTR) omElement);
		case "OMV":
			return visit((OMV) omElement);
		case "OMA":
			return visit((OMA) omElement);
		default:
			throw new NoRepresentationAvailableException(
					"Please inform JACK Admin! There is no Representation for OpenMath Object:"
							+ omElement.getClass().getSimpleName());
		}
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
	 * @throws FunctionInvalidArgumentException
	 * @throws CasException
	 */
	private T visit(OMOBJ omobj) throws EvaluatorException {
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
			if (omobj.getOME() != null) {
				return visit(omobj.getOME());
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
	protected abstract T visit(OMF omf) throws NoRepresentationAvailableException;

	/**
	 * Implement here the String-Representation of this OMI-Element
	 * 
	 * @param omi
	 *            the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract T visit(OMI omi) throws NoRepresentationAvailableException;

	/**
	 * Implement here the String-Representation of this OMS-Element
	 * 
	 * Note: If this OMS is visited, it is an Parameter in a Function, so a Terminal
	 * like pi, e, NaN or i
	 * 
	 * @param oms
	 *            the element which is visited now
	 * @return the String-Representation
	 * @throws NoRepresentationAvailableException
	 */
	private T visit(OMS oms) throws EvaluatorException {
		Function function = null;
		try {
			function = OMSFunctionDictionary.getInstance().getFunction(oms);
		} catch (FunctionNotImplementedRuntimeException er) {
			throw new FunctionNotImplementedException(er.getMessage());
		}
		return getSyntaxRepresentationForFunction(function, oms, new ArrayList<>());
	}

	/**
	 * Implement here the String-Representation of this OMSTR-Element
	 * 
	 * @param omstr
	 *            the element which is visited now
	 * @return the String-Representation
	 * @throws NoRepresentationAvailableException
	 */
	protected abstract T visit(OMSTR omstr) throws NoRepresentationAvailableException;

	/**
	 * Implement here the String-Representation of this OMV-Element
	 * 
	 * @param omv
	 *            the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract T visit(OMV omv) throws NoRepresentationAvailableException;

	/**
	 * Not every visitor has to implement the processing of OME objects. If a
	 * visitor needs to process OME, overwrite this function.
	 * 
	 * @param ome
	 *            the element which is visited now
	 * @return the String-Representation
	 * @throws EvaluatorException
	 */
	protected T visit(OME ome) throws NoRepresentationAvailableException {
		throw new NoRepresentationAvailableException("processing of OME not supported");
	}

	/**
	 * An OMA is visited here. Here the visit searches for the specific Function and
	 * calls with this found Function callInFunctionTheMethod The List given to this
	 * method is beforehand checked if Parameters are correct
	 * 
	 * @param oma
	 *            the visited oma
	 * @return the String Representation of this oma and its childs
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws NoRepresentationAvailableException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws FunctionInvalidArgumentException
	 * @throws CasException
	 */
	private T visit(OMA oma) throws EvaluatorException {

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

		Function function = null;
		try {
			function = OMSFunctionDictionary.getInstance().getFunction(oms);
		} catch (FunctionNotImplementedRuntimeException er) {
			throw new FunctionNotImplementedException(er.getMessage());
		}
		// Get function

		function.argsBetweenMinMax(omel); // Check for Correct amount of
											// Arguments
		return getSyntaxRepresentationForFunction(function, oms, omel);
	}

	/**
	 * Define here which Method in all available Evaluator-Functions should be
	 * called This Function should return the Representation of it in T
	 * 
	 * @param function
	 *            the specific Function which is next to be called
	 * @param omel
	 *            the Functions Parameter (in Range as in minArgs and maxArgs
	 *            defined) Note: These Parameter are evaluated if
	 *            argumentsShouldBeEvaluated returns true. Not evaluated if it
	 *            returns false
	 * @return a String which is in CAS-Syntax
	 * @throws EvaluatorException
	 * 
	 */
	protected abstract T getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel)
			throws EvaluatorException;

}
