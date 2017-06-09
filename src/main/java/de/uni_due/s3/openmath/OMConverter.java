package de.uni_due.s3.openmath;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
		if(!result.contains("OMOBJ")){
			String toBeDeleted = " xmlns:ns2=\"http://www.openmath.org/OpenMath\"";
			if (result.contains(toBeDeleted)){
				result = result.replace(toBeDeleted, "");
			}
		}
		return result;
	
	}
	
	
}
