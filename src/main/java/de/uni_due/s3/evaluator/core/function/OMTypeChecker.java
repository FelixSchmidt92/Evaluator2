package de.uni_due.s3.evaluator.core.function;

import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;

/**
 * This class has methods for type checking openmath-Objects.
 * These methods are used in situations where you have to check if a given object is a specific openmath-object.
 * @author frichtscheid
 *
 */
public class OMTypeChecker {

	/**
	 * checks if the object is of type OMA
	 * @param obj
	 * @return true, if object is of type OMA
	 */
	public static boolean isOMA(Object obj) {
		return obj instanceof OMA;
	}

	/**
	 * checks if the object is of type OMA and has the given symbol (OMS)
	 * @param obj the object to check
	 * @param symbol the symbol which the obj should contain
	 * @return true, if object is a OMA and has the specified symbol
	 */
	public static boolean isOMAWithSymbol(Object obj, OMS symbol) {
		if (isOMA(obj)) {
			OMA oma = (OMA) obj;
			if (oma.getOmel() != null) {
				if (oma.getOmel().size() >= 1) {
					if (isOMS(oma.getOmel().get(0))) {
						return ((OMS) oma.getOmel().get(0)).equals(symbol);
					}
				}
			}
		}
		return false;
	}

	/**
	 * checks if the object is of type OMS
	 * @param obj
	 * @return true, if object is of type OMS
	 */
	public static boolean isOMS(Object obj) {
		return obj instanceof OMS;
	}

	/**
	 * checks if the object is of type OMF
	 * @param obj
	 * @return true, if object is of type OMF
	 */
	public static boolean isOMF(Object obj) {
		return obj instanceof OMF;
	}

	/**
	 * checks if the object is of type OMSTR
	 * @param obj
	 * @return true, if object is of type OMSTR
	 */
	public static boolean isOMSTR(Object obj) {
		return obj instanceof OMSTR;
	}

	/**
	 * checks if the object is of type OMI
	 * @param obj
	 * @return true, if object is of type OMI
	 */
	public static boolean isOMI(Object obj) {
		return obj instanceof OMI;
	}
	
	/**
	 * checks if the object is either of type OMF or OMI
	 * @param obj
	 * @return true, if object is of type OMF or OMI
	 */
	public static boolean isOMFOrOMI(Object obj){
		return (obj instanceof OMF)? true:(obj instanceof OMI)? true:false;
	}
}
