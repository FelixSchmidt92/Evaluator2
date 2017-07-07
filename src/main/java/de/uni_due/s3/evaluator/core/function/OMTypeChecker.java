package de.uni_due.s3.evaluator.core.function;

import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;

/**
 * 
 * 
 * @author 
 *
 */
public class OMTypeChecker {

	public static boolean isOMA(Object obj) {
		return obj instanceof OMA;
	}

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

	public static boolean isOMS(Object obj) {
		return obj instanceof OMS;
	}

	public static boolean isOMF(Object obj) {
		return obj instanceof OMF;
	}

	public static boolean isOMSTR(Object obj) {
		return obj instanceof OMSTR;
	}

	public static boolean isOMI(Object obj) {
		return obj instanceof OMI;
	}
}
