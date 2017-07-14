package de.uni_due.s3.evaluator.core.function;

import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

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
	 * @throws OpenMathException 
	 */
	public static Double convertOMIOMFToDouble(Object obj) throws InputMismatchException {
		if (obj instanceof OMOBJ) {
			try {
				obj = OMConverter.toElement((OMOBJ) obj);
			} catch (OpenMathException e) {
				throw new InputMismatchException();
			}
		}
		
		if (obj instanceof OMI) {
			return Double.parseDouble(((OMI) obj).getValue());
		} else if (obj instanceof OMF) {
			return ((OMF) obj).getDec();
		} else {
			throw new InputMismatchException();
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

	public static int convertOMIToInteger(Object obj) throws InputMismatchException {
		if (obj instanceof OMOBJ) {
			try {
				obj = OMConverter.toElement((OMOBJ) obj);
			} catch (OpenMathException e) {
				throw new InputMismatchException();
			}
		}
		
		if (obj instanceof OMI) {
			return Integer.parseInt(((OMI) obj).getValue());
		} else {
			throw new InputMismatchException();
		}
	}
	
	public static String convertOMSTRToSring(Object obj) throws InputMismatchException {
		if (obj instanceof OMOBJ) {
			try {
				obj = OMConverter.toElement((OMOBJ) obj);
			} catch (OpenMathException e) {
				throw new InputMismatchException();
			}
		}
		
		if (obj instanceof OMSTR) {
			return ((OMSTR) obj).getContent();
		} else {
			throw new InputMismatchException();
		}
	}
}
