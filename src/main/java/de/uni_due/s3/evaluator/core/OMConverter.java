package de.uni_due.s3.evaluator.core;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.uni_due.s3.evaluator.exceptions.OMObjectNotSupportedException;
import de.uni_due.s3.openmath.OMOBJ;

/**
 * Converts OpenMath as a String to OpenMath objects and vice versa
 * @author frichtscheid
 *
 */
public class OMConverter {

	/**
	 * Converts OpenMath as String to OpenMath objects. You have to specify in which OpenMath object it should convert
	 * @param xmlOpenMath the openMath string
	 * @return 
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String xmlOpenMath) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance("de.uni_due.s3.openmath");
		Unmarshaller unmarshaller = context.createUnmarshaller();

		return (T) unmarshaller.unmarshal(new StringReader(xmlOpenMath));		
	}
	
	/**
	 * Converts an OpenMath Object into a string
	 * @param omobj
	 * @return
	 * @throws JAXBException
	 */
	public static String toString(Object omobj) throws JAXBException{
		JAXBContext context = JAXBContext.newInstance("de.uni_due.s3.openmath");
		Marshaller marshaller = context.createMarshaller();
		StringWriter sw = new StringWriter();
		
		marshaller.marshal(omobj, sw);	
		String result = sw.toString();
		//removing some unused xml tags
		result = result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
		if(result.contains("OMOBJ")){
			String toBeDeleted = " xmlns:ns2=\"http://www.openmath.org/OpenMath\"";
			if (result.contains(toBeDeleted)){
				result = result.replace(toBeDeleted, "");
			}
		}
		return result;
	
	}
	
	/**
	 * Returns the element which is wrapped in an OMOBJ-Object
	 * @param omobj
	 * @return OMA, OMF, OMI, OMS, OMSTR or OMV
	 */
	public static Object toElement(OMOBJ omobj){
		
		if (omobj.getOMA() != null) {
			return (omobj.getOMA());

		} else if (omobj.getOMF() != null) {
			return(omobj.getOMF());

		} else if (omobj.getOMI() != null) {
			return(omobj.getOMI());

		} else if (omobj.getOMS() != null) {
			return(omobj.getOMS());

		} else if (omobj.getOMSTR() != null) {
			return(omobj.getOMSTR());

		} else if (omobj.getOMV() != null) {
			return(omobj.getOMV());

		} else {
			// TODO
			throw new OMObjectNotSupportedException(omobj);
		}
	}
	
	
}
