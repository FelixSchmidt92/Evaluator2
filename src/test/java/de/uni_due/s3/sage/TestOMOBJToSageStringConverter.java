package de.uni_due.s3.sage;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

@RunWith(Parameterized.class)
public class TestOMOBJToSageStringConverter {
	
	private static Unmarshaller unmarshaller = null;
	private OMOBJToSageStringConverter converter = OMOBJToSageStringConverter.getInstance();
	
	private String float1, integer, string, var, constCd, constName;
	
	static String[][] testArray = {
			//Integer
			{"0.0", "0",  "str", "x", "nums1", "pi"}, //[0]
			{"1.1", "42", "str2", "y", "nums1", "infinity"},
			};
	
	
	public TestOMOBJToSageStringConverter(String float1, String integer, String string,
															String var, String constCd, String constName) {
		this.float1 = float1; this.integer = integer;
		this.string = string; this.var = var;
		this.constCd = constCd; this.constName = constName;
	}
	
	
	
	@BeforeClass
	public static void beforeClass() throws EvaluatorException{
		
		try {
			JAXBContext context = JAXBContext.newInstance("de.uni_due.s3.openmath");
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new EvaluatorException("Error at Test. Couldn't create Unmarshaller");
		}
		
	}
	
	@Parameterized.Parameters
	public static Collection<String[]> test(){
		ArrayList<String[]> list = new ArrayList<String[]>();
		for (String[] a : testArray){
			list.add(a);
		}
		return list;
	}
	
	
	
	@Test
	public void testOMF() throws JAXBException, SagePhrasebookException{
		OMOBJ f = (OMOBJ) unmarshaller.unmarshal(new StringReader("<OMOBJ><OMF dec=\""+ float1 +"\"/></OMOBJ>"));
		assertEquals(converter.convert(f), "RealNumber("+ float1 +")");
	}
	
	
	@Test
	public void testOMI() throws JAXBException, SagePhrasebookException{
		OMOBJ i = (OMOBJ) unmarshaller.unmarshal(new StringReader("<OMOBJ><OMI>"+ integer +"</OMI></OMOBJ>"));
		assertEquals(converter.convert(i), "Integer("+ integer +")");
		
	}
	
	/**Missing Tests*/
	
}
