package de.uni_due.s3.evaluator2.integration.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsSet extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws JAXBException {
		OMOBJ one = new OMOBJ();
		one.setOMI(OMCreator.createOMI(1));

		exerVar.put("a", OMConverter.toObject(
				"<OMOBJ><OMA><OMS cd='list1' name='list'/><OMA><OMS cd='linalg2' name='vector'/><OMI>1</OMI><OMI>2</OMI><OMI>3</OMI></OMA><OMA><OMS cd='linalg2' name='vector'/><OMI>3</OMI><OMI>3</OMI><OMI>3</OMI></OMA></OMA></OMOBJ>"));
		exerVar.put("b", one);

		fillIn.put(1, OMConverter.toObject(
				"<OMOBJ><OMA><OMS cd='list1' name='list'/><OMA><OMS cd='linalg2' name='vector'/><OMI>1</OMI><OMI>2</OMI><OMI>3</OMI></OMA><OMA><OMS cd='linalg2' name='vector'/><OMI>3</OMI><OMI>3</OMI><OMI>3</OMI></OMA></OMA></OMOBJ>"));
		fillIn.put(2, one);
	}

	@Test
	public void testIsSet() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isSet(set(vector(1,1), vector(1,2)))", exerVar, fillIn));
	}

	@Test
	public void testIsSetWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isSet('[pos=1]')", exerVar, fillIn));
	}

	@Test
	public void testIsSetWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isSet('[var=a]')", exerVar, fillIn));
	}
}
