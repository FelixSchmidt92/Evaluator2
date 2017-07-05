package de.uni_due.s3.JAXBOpenMath.OMUtils;

import java.util.List;

import de.uni_due.s3.JAXBOpenMath.openmath.OMA;
import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.JAXBOpenMath.openmath.OMSTR;
import de.uni_due.s3.JAXBOpenMath.openmath.OMV;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMObjectNotSupportedException;

public class OMCreator {

	/**
	 * This method just gets an unknown OpenMathObject and wraps it into an OMOBJ.
	 * Only add Objects which an OMOBJ can store in here otherwise null will be returned.
	 * 
	 * @param omElement The Object which should be wrapped into OMOBJ (has to be one of the JAXB-Classes)
	 * @return an OMOBJ containing the omOnknown at the correct spot
	 */
	public static OMOBJ createOMOBJ(Object omElement) throws EvaluatorException{
		OMOBJ omobj = new OMOBJ();
		if(omElement != null){
			switch (omElement.getClass().getSimpleName()){
			case "OMF":
				omobj.setOMF((OMF) omElement);
				break;
				
			case "OMI":
				omobj.setOMI((OMI) omElement);
				break;
				
			case "OMS":
				omobj.setOMS((OMS) omElement);
				break;
				
			case "OMSTR":
				omobj.setOMSTR((OMSTR) omElement);
				break;
				
			case "OMV":
				omobj.setOMV((OMV) omElement);
				break;
				
			case "OMA":
				omobj.setOMA((OMA) omElement);
				break;
				
			default :
				throw new OMObjectNotSupportedException(omElement);
			}
		}else {
			throw new OMObjectNotSupportedException(null);
		}
		return omobj;
	}

	/**
	 * Creates an OMA by specifying the oms and the arguments for the oms.
	 * @param oms oms of the oma
	 * @param elements arguments for the oms
	 * @return OMA
	 */
	public static OMA createOMA(final OMS oms, final List<Object> elements){
		OMA oma = new OMA();
		oma.getOmel().add(oms);
		oma.getOmel().addAll(elements);
		
		return oma;
	}
	
	/**
	 * Creates an OMS by specifying the cd and the name
	 * @param cd
	 * @param name
	 * @return OMS
	 */
	public static OMS createOMS(final String cd, final String name){

		OMS oms = new OMS();
		oms.setCd(cd);
		oms.setName(name);
		
		return oms;
	}
	
	/**
	 * Creates an OMI by specifying its value
	 * @param value
	 * @return OMI
	 */
	public static OMI createOMI(final int value){
		OMI omi = new OMI();
		omi.setValue(Integer.toString(value));
		
		return omi;
	}
	
	/**
	 * Creates an OMF by specifying its value
	 * @param value
	 * @return OMF
	 */
	public static OMF createOMF(final double value){
		OMF omf = new OMF();
		omf.setDec(value);
		
		return omf;
	}
	
	/**
	 * Creates an OMSTR by specifying its text
	 * @param text
	 * @return OMSTR
	 */
	public static OMSTR createOMSTR(final String text){
		OMSTR omstr = new OMSTR();
		omstr.setContent(text);
		
		return omstr;
	}
	
	/**
	 * Creates an OMV by specifying its name
	 * @param name
	 * @return
	 */
	public static OMV createOMV(final String name){
		OMV omv = new OMV();
		omv.setName(name);
		
		return omv;
	}
}
