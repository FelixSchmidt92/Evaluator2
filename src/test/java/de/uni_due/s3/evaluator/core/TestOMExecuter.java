package de.uni_due.s3.evaluator.core;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMB;

import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMOBJChildNotSupportedException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

public class TestOMExecuter {

	@SuppressWarnings("unused")
	private static Unmarshaller unmarshaller;

	@BeforeClass
	public static void init() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance("de.uni_due.s3.openmath.jaxb");

		unmarshaller = context.createUnmarshaller();
	}

	@Test
	public void visitorTestOMOBJ() throws JAXBException, FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ result;
		OMOBJ omobj;
		// test OMI
		omobj = (OMOBJ) OMConverter.toObject("<OMOBJ><OMI>5</OMI></OMOBJ>");
		result = OMExecutor.execute(omobj);
		assertEquals(result.getOMI().getValue(), "5");

		// test OMF
		omobj = (OMOBJ) OMConverter.toObject("<OMOBJ><OMF dec=\"5.12345\"></OMF></OMOBJ>");
		result = OMExecutor.execute(omobj);
		assertTrue(result.getOMF().getDec().doubleValue() == 5.12345d);

		// test OMS
		omobj = (OMOBJ) OMConverter.toObject("<OMOBJ><OMS cd=\"arith1\" name=\"plus\"/></OMOBJ>");
		result = OMExecutor.execute(omobj);
		assertTrue(result.getOMS().getName().equals("plus"));

		// test OMSTR
		omobj = (OMOBJ) OMConverter.toObject("<OMOBJ><OMSTR>Hello</OMSTR></OMOBJ>");
		result = OMExecutor.execute(omobj);

		assertTrue(result.getOMSTR().getContent().equals("Hello"));

		// test OMV
		omobj = (OMOBJ) OMConverter.toObject("<OMOBJ><OMV name=\"x\" /></OMOBJ>");
		result = OMExecutor.execute(omobj);
		assertTrue(result.getOMV().getName().equals("x"));

	}

	@Test
	public void visitorTestOMA() throws JAXBException, FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ result;
		OMA oma;

		// 10+5
		// the plus function has to be implemented!
		oma = (OMA) ((OMOBJ) OMConverter.toObject(
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMI>10</OMI>" + "<OMI>5</OMI>" + "</OMA></OMOBJ>"))
						.getOMA();

		result = OMExecutor.execute(OMCreator.createOMOBJ(oma));
		assertTrue(result.getOMI().getValue().equals("15"));

	}

	@Test
	public void visitorTestNestedOMA() throws JAXBException, FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ result;
		OMA oma;

		// 2+3+5
		// the plus function has to be implemented
		oma = (OMA) ((OMOBJ) OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd=\"arith1\" name=\"plus\"/><OMI>3</OMI><OMI>2</OMI></OMA>" + "<OMI>5</OMI>"
				+ "</OMA></OMOBJ>")).getOMA();

		result = OMExecutor.execute(OMCreator.createOMOBJ(oma));
		assertTrue(result.getOMI().getValue().equals("10"));
	}

	@SuppressWarnings("unused")
	@Test(expected = FunctionNotImplementedRuntimeException.class)
	public void visitorTestWithWrongFunction() throws JAXBException, FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException {
		Object result;
		OMA oma;

		// 10+5
		// the plus function has to be implemented!
		oma = (OMA) ((OMOBJ) OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"iamunknown\"/>"
				+ "<OMI>10</OMI>" + "<OMI>5</OMI>" + "</OMA></OMOBJ>")).getOMA();

		result = OMExecutor.execute(OMCreator.createOMOBJ(oma));
	}

	@Test(expected = OMOBJChildNotSupportedException.class)
	public void testNotImplementedOMobject() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = new OMOBJ();
		omobj.setOMB(new OMB());
		OMExecutor.execute(omobj);
	}
}
