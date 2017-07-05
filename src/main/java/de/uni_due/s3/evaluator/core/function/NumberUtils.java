package de.uni_due.s3.evaluator.core.function;

import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMSTR;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;

public class NumberUtils {

	/**
	 * Diese Funktion nimmt ein Object obj entgegen, dieses muss vom Typen OMF
	 * oder OMI sein. Sie erzeugt hieraus ein Double. OMI wird zu z.B. 5.0 oder
	 * -3.0, OMF wird zu z.B. 5.54 oder -0.234
	 * 
	 * @param obj
	 *            OMF oder OMI
	 * @return Double Wert von OMF bzw. OMI
	 * @throws InputMismatchException
	 *             wenn obj kein OMF oder OMI ist
	 */
	public static Double convertOMIOMFToDouble(Object obj) throws InputMismatchException {
		if (obj instanceof OMI) {
			return Double.parseDouble(((OMI) obj).getValue());
		} else if (obj instanceof OMF) {
			return ((OMF) obj).getDec();
		} else {
			throw new InputMismatchException(); // TODO vllt eine andere ?
		}
	}

	/**
	 * Diese Funktion nimmt einen Double entgegen und erzeugt hieraus ein OMF
	 * bzw. OMI. OMI wird erzeugt wenn es eine als Integer darstellbare Zahl
	 * ist. Sonst OMF.
	 * 
	 * @param result 
	 * @return OMF oder OMI
	 */
	public static Object convertDoubleToOMIOMF(Double result) {
		if (((double) result.intValue()) == result.doubleValue()) {
			OMI omiResult = new OMI();
			omiResult.setValue(String.valueOf(result.intValue()));
			return omiResult;
		} else {
			OMF omfResult = new OMF();
			omfResult.setDec(result);
			return omfResult;
		}
	}
	
	public static int valueOf(OMI omi){
		return Integer.parseInt(omi.getValue());
	}
	
	public static double valueOf(OMF omf){
		return omf.getDec();
	}
	
	public static String valueOf(OMSTR omstr){
		return omstr.getContent();
	}
}
