package de.uni_due.s3.evaluator.core.function;

import org.openmath.openmath.OMA;
import org.openmath.openmath.OMF;
import org.openmath.openmath.OMI;
import org.openmath.openmath.OMS;
import org.openmath.openmath.OMSTR;

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
