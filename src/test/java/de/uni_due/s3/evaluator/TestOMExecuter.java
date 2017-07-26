package de.uni_due.s3.evaluator;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.openmath.jaxb.OMB;
import de.uni_due.s3.openmath.jaxb.OMBIND;
import de.uni_due.s3.openmath.jaxb.OME;
import de.uni_due.s3.openmath.jaxb.OMFOREIGN;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMR;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMOBJChildNotSupportedException;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

@RunWith(Parameterized.class)
public class TestOMExecuter {
	
	static Object[][] parameters = {
			{ "<OMOBJ><OMI>5</OMI></OMOBJ>", 
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/><OMI>10</OMI><OMI>5</OMI></OMA></OMOBJ>", 
				"<OMOBJ><OMI>15</OMI></OMOBJ>",
				new OMB()}, // [0]
			
			{ "<OMOBJ><OMF dec=\"5.12345\"/></OMOBJ>", 
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/><OMA><OMS cd=\"arith1\" name=\"plus\"/><OMF dec=\"5.12\"/><OMF dec=\"5.12\"/></OMA><OMF dec=\"5.12\"/></OMA></OMOBJ>", 
				"<OMOBJ><OMF dec=\"15.36\"/></OMOBJ>",
				new OME()}, 
			
			{ "<OMOBJ><OMS cd=\"arith1\" name=\"plus\"/></OMOBJ>", 
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/><OMF dec=\"1.23\"/><OMI>4</OMI></OMA></OMOBJ>", 
				"<OMOBJ><OMF dec=\"5.23\"/></OMOBJ>",
				new OMBIND()},
			
			{ "<OMOBJ><OMSTR>Hello</OMSTR></OMOBJ>", 
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/><OMF dec=\"1.1\"/><OMI>1</OMI></OMA></OMOBJ>", 
				"<OMOBJ><OMF dec=\"2.1\"/></OMOBJ>",
				new OMR()}, 
			
			{ "<OMOBJ><OMV name=\"x\" /></OMOBJ>", 
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/><OMF dec=\"0.1\"/><OMI>0</OMI></OMA></OMOBJ>", 
				"<OMOBJ><OMF dec=\"0.1\"/></OMOBJ>",
				null}, 
	};
	
	private String terminalStringToObject, omaStringToObject, resultStringToObject;
	private Object foreignChild;
	
	
	@Parameterized.Parameters
	public static Collection<Object[]> test() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		for (Object[] a : parameters) {
			list.add(a);
		}
		return list;
	}
	
	
	public TestOMExecuter(String terminalStringToObject, String omaStringToObject, String resultStringToObject, Object foreignChild) {
		this.terminalStringToObject = terminalStringToObject;
		this.omaStringToObject = omaStringToObject;
		this.resultStringToObject = resultStringToObject;
		this.foreignChild = foreignChild;
	}
	

	@Test
	public void visitorTestOMOBJ() throws JAXBException, FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		// test OMI, OMF, OMS, OMSTR, OMV
		OMOBJ current = (OMOBJ) OMConverter.toObject(terminalStringToObject);
		OMOBJ curResult = OMExecutor.execute(current);
		OMOBJ expected = OMConverter.toObject(terminalStringToObject);
		assertEquals(expected, curResult);
	}

	
	@Test
	public void visitorTestOMA() throws JAXBException, FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ current = OMConverter.toObject(omaStringToObject);
		OMOBJ curResult = OMExecutor.execute(current);
		OMOBJ expected = OMConverter.toObject(resultStringToObject);
		assertEquals(expected, curResult);
	}
	
	
	@Test(expected = OMOBJChildNotSupportedException.class)
	public void testNotImplementedOMobject() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = new OMOBJ();
		
		if (foreignChild instanceof OMB)
			omobj.setOMB((OMB) foreignChild);
		if (foreignChild instanceof OME)
			omobj.setOME((OME) foreignChild);
		if (foreignChild instanceof OMFOREIGN)
			omobj.setOMBIND((OMBIND) foreignChild);
		if (foreignChild instanceof OMR)
			omobj.setOMR((OMR) foreignChild);
		
		OMExecutor.execute(omobj);
	}
}