package de.uni_due.s3.evaluator.core;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.OMObjectNotSupportedException;
import de.uni_due.s3.evaluator.omdictionary.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.omdictionary.OMSymbol;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMOBJ;
import de.uni_due.s3.openmath.OMS;
import de.uni_due.s3.openmath.OMSTR;
import de.uni_due.s3.openmath.OMV;

/**
 * A visitor for the OpenMath-structure, represented by classes in the OpenMath package, like OMOBJ{@link de.uni_due.s3.openmath.OMOBJ}.
 * It is used to traverse the OpenMath structure and do specific actions for every type of OpenMath object.
 * 
 * For now (09.06.17) it can only traverse OMOBJ,OMA,OMF,OMI,OMS,OMSTR and OMV. All other OpenMath objects are not supported.
 * 
 * @author frichtscheid
 *
 */
public class OMVisitor {
	
	/**
	 * Visits a OMOBJ object and return its child object. The kind of child is determined by the OMOBJ object.
	 * At the moment OMA,OMF,OMI,OMS,OMSTR and OMV are those children which will be returned.
	 * @param omobj the whole OpenMath object starting with <OMOBJ>...
	 * @return omobj-object.
	 */
	public static OMOBJ visit(OMOBJ omobj) {
		Object visitedElement = null;
		if (omobj.getOMA() != null) {
			visitedElement =  visit(omobj.getOMA());

		} else if (omobj.getOMF() != null) {
			visitedElement =  (omobj.getOMF());

		} else if (omobj.getOMI() != null) {
			visitedElement = (omobj.getOMI());

		} else if (omobj.getOMS() != null) {
			visitedElement = (omobj.getOMS());

		} else if (omobj.getOMSTR() != null) {
			visitedElement = (omobj.getOMSTR());

		} else if (omobj.getOMV() != null) {
			visitedElement = (omobj.getOMV());

		} else {
			// TODO
			throw new OMObjectNotSupportedException(omobj);
		}
		
		return OMCreator.createOMOBJ(visitedElement);
	}

	/**
	 * Visits a OpenMath application object (OMA).
	 * Although a OMA can have different kinds of children as its first child, like OMV or OMS,
	 * we assume that its first child is a OMS, because without a function their is no need for a OMV?
	 * Example: <OMA>
	 * 				<OMS cd="arith1" name="plus" />
	 * 				...	here are the arguments for the function
	 * 			</OMA>
	 * After the OMS any amount of OMF,OMI,OMS,OMSTR,OMV or another OMA can follow. 
	 * If the function (defined by the OMS) allows that children of the type OMA can be visited, they will be visited
	 * and the result of that will be used as argument instead of the OMA itself.
	 * All children of a OMA (without the first child) will be passed to the function as arguments and the function will be executed.
	 * 
	 * @param oma OpenMath application object <OMA>...</OMA>
	 * @return one of OMA,OMF,OMI,OMS,OMSTR or OMV. It depends on function used in the OMS.
	 */
	public static Object visit(OMA oma){
		List<Object> omel = oma.getOmel();
		Function function = visit((OMS) omel.get(0));
		System.out.println("visit oma "+ function.toString());
		List<Object> arguments = new ArrayList<Object>(omel.size()-1);

		for(int i=0;i<omel.size()-1;i++){
			Object o = omel.get(i+1);
			if(o instanceof OMA){
				if(function.argumentsShouldBeEvaluated())
					arguments.add(i, (visit((OMA)o)) );
				else
					arguments.add(i,(OMA)o );
			} else if (o instanceof OMF) {
				arguments.add(i,(OMF) o);

			} else if (o instanceof OMI) {
				arguments.add(i,(OMI) o);

			} else if (o instanceof OMS) {
				arguments.add(i,(OMS) o);

			} else if (o instanceof OMSTR) {
				arguments.add(i,(OMSTR) o);

			} else if (o instanceof OMV) {
				arguments.add(i,(OMV) o);
			} 
		}

		return function.evaluate(arguments);

	}

	/**
	 * Visits a OpenMath symbol and returns a concrete Implementation of the function specified by the OMS.
	 * 
	 * @param oms OpenMath symbol object.
	 * @return a concrete Implementation of the Function class.
	 * @throws FunctionNotImplementedException when the specified function does not exists or has another cd than the requested function has.
	 */

	public static Function visit(OMS oms){
		return OMSFunctionDictionary.getFunction(new OMSymbol(oms.getCd(), oms.getName()));
	}


}
