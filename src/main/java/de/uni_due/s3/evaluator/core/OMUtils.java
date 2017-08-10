package de.uni_due.s3.evaluator.core;

import java.util.List;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class OMUtils {

	/**
	 * Diese Funktion nimmt ein Object obj entgegen, dieses muss vom Typen OMF
	 * oder OMI sein. Sie erzeugt hieraus ein Double. OMI wird zu z.B. 5.0 oder
	 * -3.0, OMF wird zu z.B. 5.54 oder -0.234
	 * 
	 * @param obj
	 *            OMF oder OMI
	 * @return Double Wert von OMF bzw. OMI
	 * @throws InputMismatchException
	 *             wenn obj kein OMF, OMI oder NUMS1 Symbol ist
	 */
	public static Double convertOMToDouble(Object obj) throws InputMismatchException {
		// first if obj is OMOBJ get Element
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
		} else if (obj instanceof OMS) {
			OMS symbol = (OMS) obj;
			if (symbol.equals(OMSymbol.NUMS1_E)) {
				return Math.E;
			} else if (symbol.equals(OMSymbol.NUMS1_PI)) {
				return Math.PI;
			} else if (symbol.equals(OMSymbol.NUMS1_INFINITY)) {
				return Double.POSITIVE_INFINITY;
			} else if (obj.equals(OMSymbol.LOGIC1_FALSE)) {
				return 0.0;
			} else if (obj.equals(OMSymbol.LOGIC1_TRUE)) {
				return 1.0;
			} else {
				throw new InputMismatchException();
			}
		} else if (OMTypeChecker.isOMAWithSymbol(obj, OMSymbol.STRINGJACK_TEXTWITHEXPRESSION)) {
			return convertOMToDouble(((OMA) obj).getOmel().get(2));
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

	/**
	 * Diese Funktion nimmt einen OMI oder ein OMF entgegen und erzeugt hieraus
	 * einen Integer Im Falle eines OMF wird deren die Nachkommastellen einfach
	 * ausgelassen.
	 * 
	 * @param obj
	 *            OMI or OMF
	 * @return int
	 * @throws InputMismatchException
	 */
	public static int convertOMToInteger(Object obj) throws InputMismatchException {
		if (obj instanceof OMOBJ) {
			try {
				obj = OMConverter.toElement((OMOBJ) obj);
			} catch (OpenMathException e) {
				throw new InputMismatchException();
			}
		}

		if (obj instanceof OMI) {
			return Integer.parseInt(((OMI) obj).getValue());
		} else if (obj instanceof OMF) {
			double val = ((OMF) obj).getDec().doubleValue();
			if (val % 1 == 0)
				return (int) val;
			else
				throw new InputMismatchException();

		} else if (obj.equals(OMSymbol.LOGIC1_FALSE)) {
			return 0;
		} else if (obj.equals(OMSymbol.LOGIC1_TRUE)) {
			return 1;
		} else if (OMTypeChecker.isOMAWithSymbol(obj, OMSymbol.STRINGJACK_TEXTWITHEXPRESSION)) {
			return convertOMToInteger(((OMA) obj).getOmel().get(2));
		} else {
			throw new InputMismatchException();
		}
	}

	/**
	 * Diese Funktion nimmt einen OMSTR, OMI, OMF entgegen und erzeugt hieraus
	 * ein String
	 * 
	 * @param obj
	 *            OMSTR, OMI oder OMF
	 * @return String rep.
	 * @throws InputMismatchException
	 */
	public static String convertOMToString(Object obj) throws InputMismatchException {
		if (obj instanceof OMOBJ) {
			try {
				obj = OMConverter.toElement((OMOBJ) obj);
			} catch (OpenMathException e) {
				throw new InputMismatchException();
			}
		}

		if (obj instanceof OMSTR) {
			return ((OMSTR) obj).getContent();
		} else if (obj instanceof OMI) {
			return ((OMI) obj).getValue();
		} else if (obj instanceof OMF) {
			return Double.toString(((OMF) obj).getDec());
		} else if (obj instanceof OMV) {
			return ((OMV) obj).getName();
		} else if (obj.equals(OMSymbol.LOGIC1_FALSE)) {
			return "0";
		} else if (obj.equals(OMSymbol.LOGIC1_TRUE)) {
			return "1";
		} else if (OMTypeChecker.isOMAWithSymbol(obj, OMSymbol.STRINGJACK_TEXTWITHEXPRESSION)) {
			return convertOMToString(((OMA)obj).getOmel().get(1));
		} else if (OMTypeChecker.isOMAWithSymbol(obj, OMSymbol.STRINGJACK_TEXTWITHVARIABLES)) {
			List<Object> omel = ((OMA) obj).getOmel();
			omel.remove(0);
			String result = "";
			for (Object part : omel) {
				result = result + convertOMToString(part);
			}
			return result;
		} else {
			throw new InputMismatchException();
		}
	}

	public static boolean convertOMToBoolean(Object obj) throws InputMismatchException {
		if (obj instanceof OMOBJ) {
			try {
				obj = OMConverter.toElement((OMOBJ) obj);
			} catch (OpenMathException e) {
				throw new InputMismatchException();
			}
		}

		if (obj instanceof OMS) {
			if (obj.equals(OMSymbol.LOGIC1_TRUE) || obj.equals(OMSymbol.NUMS1_PI) || obj.equals(OMSymbol.NUMS1_E)
					|| obj.equals(OMSymbol.NUMS1_INFINITY)) {
				return true;
			} else {
				return false;
			}
		} else if (obj instanceof OMI) {
			if (Integer.parseInt(((OMI) obj).getValue()) > 0) {
				return true;
			} else {
				return false;
			}
		} else if (obj instanceof OMF) {
			if (((OMF) obj).getDec() > 0) {
				return true;
			} else {
				return false;
			}
		} else if (obj instanceof OMSTR) {
			if (((OMSTR) obj).getContent().equals("true")) {
				return true;
			} else {
				return false;
			}
		} else if (OMTypeChecker.isOMAWithSymbol(obj, OMSymbol.STRINGJACK_TEXTWITHEXPRESSION)) {
			return convertOMToBoolean(((OMA)obj).getOmel().get(2));
		} else {
			throw new InputMismatchException();
		}
	}

	public static OMS convertToLogicBoolean(Object obj) throws InputMismatchException {

		if (obj instanceof Integer) {
			if ((Integer) obj > 0) {
				return OMSymbol.LOGIC1_TRUE;
			} else {
				return OMSymbol.LOGIC1_FALSE;
			}
		} else if (obj instanceof Double) {
			if (((Double) obj) > 0) {
				return OMSymbol.LOGIC1_TRUE;
			} else {
				return OMSymbol.LOGIC1_FALSE;
			}

		} else if (obj instanceof Double) {
			if (((Double) obj) > 0) {
				return OMSymbol.LOGIC1_TRUE;
			} else {
				return OMSymbol.LOGIC1_FALSE;
			}
		} else if (obj instanceof Float) {
			if (((Float) obj) > 0) {
				return OMSymbol.LOGIC1_TRUE;
			} else {
				return OMSymbol.LOGIC1_FALSE;
			}
		} else if (obj instanceof Boolean) {
			if (((Boolean) obj) == true) {
				return OMSymbol.LOGIC1_TRUE;
			} else {
				return OMSymbol.LOGIC1_FALSE;
			}
		} else {
			throw new InputMismatchException();
		}
	}
}
