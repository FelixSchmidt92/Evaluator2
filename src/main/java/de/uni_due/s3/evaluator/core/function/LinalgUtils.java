package de.uni_due.s3.evaluator.core.function;

import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;

public class LinalgUtils {

	public static boolean isOMAwithOMS(Object obj, OMS oms) {
		if (obj instanceof OMA) {
			OMA oma = (OMA) obj;
			if (oma.getOmel().size() > 0) {
				if (oma.getOmel().get(0) instanceof OMS) {
					OMS omstemp = (OMS) oma.getOmel().get(0);
					if (omstemp.equals(oms)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
