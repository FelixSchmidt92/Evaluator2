package de.uni_due.s3.evaluator.core.function;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.JAXBOpenMath.openmath.OMA;
import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.JAXBOpenMath.openmath.OMSTR;
import de.uni_due.s3.JAXBOpenMath.openmath.OMV;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;


/**
 * This is a Template! For implementing an OMOBJ to CAS inherit this class.
 * Implement all methods and Add "2" Methods in Function. 
 * 
 * One Method (protected) which references to your implemented Class and calls visit. 
 * 
 * Second Method (protected) which is implemented and throws an Error (NoFunctionToCASException).
 * This method should be implemented in some Functions. Some means only the Function which
 * should also be evaluated in this specific CAS. (Matrix or Vector is a good candidate)
 *  
 * 
 * This Visitor Pattern visits all its Terminals and Functions.
 * By calling the method visit(omElement) the String representation will be returned
 * for this omElement including its childs.
 * 
 * @author dlux, spobel
 *
 */
public abstract class Visitor {
	
	/**
	 * This method calls the visit-Method for Object. 
	 * It just extracts the containing OM* in OMOBJ.
	 * This method is NOT called from the Function!!
	 * 
	 * @param omobj the OMOBJ-"Container"
	 * @return the String representation of this OMOBJ excluding OMOBJ!!
	 */
	public String visit(OMOBJ omobj){
		if (omobj.getOMF() != null){
			return visit(omobj.getOMF());
		}
		if (omobj.getOMI() != null){
			return visit(omobj.getOMI());
		}
		if (omobj.getOMSTR() != null){
			return visit(omobj.getOMSTR());
		}
		if (omobj.getOMV() != null){
			return visit(omobj.getOMV());
		}
		if (omobj.getOMS() != null){
			return visit(omobj.getOMS());
		}
		if (omobj.getOMA() != null){
			return visit(omobj.getOMA());
		}
		return null;
	}
	
	/**
	 * visits the specific omElement. If another OMA is found the method in 
	 * callInFunctionTheMethod is called.
	 * This method is called From the Function!
	 * 
	 * @param omElement the Element to visit
	 * @return the String Representation of this omElement (including its childs!)
	 */
	public String visit(Object omElement){
		return visitMethod(omElement);	
	}
	
	/**
	 * Implement here the String-Representation of this OMF-Element
	 * 
	 * @param omf the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMF omf);
	
	/**
	 * Implement here the String-Representation of this OMI-Element
	 * 
	 * @param omi the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMI omi);
	
	/**
	 * Implement here the String-Representation of this OMS-Element
	 * 
	 * Note: If this oms is visited, it is an Parameter in a Function, so 
	 * a Terminal like pi, e or i
	 * 
	 * @param oms the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMS oms);
	
	/**
	 * Implement here the String-Representation of this OMSTR-Element
	 * 
	 * @param omstr the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMSTR omstr);
	
	/**
	 * Implement here the String-Representation of this OMV-Element
	 * 
	 * @param omv the element which is visited now
	 * @return the String-Representation
	 */
	protected abstract String visit(OMV omv);
	
	
	/**
	 * An OMA is visited here. Here the visit searches for the specific Function and calls
	 * with this found Function  callInFunctionTheMethod 
	 * The List given to this method is beforehand checked if Parameters are correct
	 * 
	 * @param oma the visited oma 
	 * @return the String Representation of this oma and its childs
	 */
	private String visit(OMA oma){
		List<Object> omel = new ArrayList<>();  
		
		for (int i = 1; i < oma.getOmel().size(); i++){
			omel.add(oma.getOmel().get(i));	//Deep Copy of List (oma.getOmel should not be changed)
		}
		
		OMS oms = (OMS) oma.getOmel().get(0); // First element of OMA is always an OMS
		Function function = OMSFunctionDictionary.getInstance().getFunction(oms);  
		//Get function
		
		function.argsBetweenMinMax(omel);	// Check for Correct amount of Arguments	
		return callInFunctionTheMethod(function, omel);
	}
	
	/**
	 * Define here which Method in all available Evaluator-Functions should be called
	 * This Function should return the Representation of it in an String
	 * 
	 * @param function the specific Function which is next to be called
	 * @param omel the Functions Parameter (in Range as in minArgs and maxArgs defined)
	 * 		  Note: These Parameter are evaluated if argumentsShouldBeEvaluated returns true.
	 * 				Not evaluated if it returns false
	 * @return a String which is in CAS-Syntax
	 */
	protected abstract String callInFunctionTheMethod(Function function, List<Object> omel);

	
	
	/**
	 * visits the specific method for the specific omElement
	 * 
	 * @param omElement the element which should be visited
	 * @return the String-Representation of this element.
	 */
	private  String visitMethod(Object omElement){
		String result = "";
		
		switch (omElement.getClass().getSimpleName()){
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
			
		default :
			return null;
		}
		return result;
	}

}
