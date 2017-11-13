package de.uni_due.s3.evaluator2.core.visitor.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator2.core.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMB;
import de.uni_due.s3.openmath.jaxb.OMBIND;
import de.uni_due.s3.openmath.jaxb.OME;
import de.uni_due.s3.openmath.jaxb.OMFOREIGN;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMR;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

@RunWith(Parameterized.class)
public class TestOMToResultVisitor {

	static Object[][] parameters = { { "<OMOBJ><OMI>5</OMI></OMOBJ>",
			"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/><OMI>10</OMI><OMI>5</OMI></OMA></OMOBJ>",
			"<OMOBJ><OMI>15</OMI></OMOBJ>", new OMB() }, // [0]

			/**
			 * { "<OMOBJ><OMF dec=\"5.12345\"/></OMOBJ>", "<OMOBJ><OMA><OMS cd=\"arith1\"
			 * name=\"plus\"/><OMA><OMS cd=\"arith1\" name=\"plus\"/><OMF dec=\"5.12\"/><OMF
			 * dec=\"5.12\"/></OMA><OMF dec=\"5.12\"/></OMA></OMOBJ>", "<OMOBJ><OMF
			 * dec=\"15.36\"/></OMOBJ>", new OME()},
			 * 
			 * { "<OMOBJ><OMS cd=\"nums1\" name=\"e\"/></OMOBJ>", "<OMOBJ><OMA><OMS
			 * cd=\"arith1\" name=\"plus\"/><OMF dec=\"1.23\"/><OMI>4</OMI></OMA></OMOBJ>",
			 * "<OMOBJ><OMF dec=\"5.23\"/></OMOBJ>", new OMBIND()},
			 * 
			 * { "<OMOBJ><OMSTR>Hello</OMSTR></OMOBJ>", "<OMOBJ><OMA><OMS cd=\"arith1\"
			 * name=\"plus\"/><OMF dec=\"1.1\"/><OMI>1</OMI></OMA></OMOBJ>", "<OMOBJ><OMF
			 * dec=\"2.1\"/></OMOBJ>", new OMR()},
			 * 
			 * { "<OMOBJ><OMV name=\"x\" /></OMOBJ>", "<OMOBJ><OMA><OMS cd=\"arith1\"
			 * name=\"plus\"/><OMF dec=\"0.1\"/><OMI>0</OMI></OMA></OMOBJ>", "<OMOBJ><OMF
			 * dec=\"0.1\"/></OMOBJ>", null},
			 **/
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

	public TestOMToResultVisitor(String terminalStringToObject, String omaStringToObject, String resultStringToObject,
			Object foreignChild) {
		this.terminalStringToObject = terminalStringToObject;
		this.omaStringToObject = omaStringToObject;
		this.resultStringToObject = resultStringToObject;
		this.foreignChild = foreignChild;
	}

	@Test
	public void visitorTestOMOBJ() throws JAXBException, OpenMathException, EvaluatorException {
		// test OMI, OMF, OMS, OMSTR, OMV
		OMOBJ current = (OMOBJ) OMConverter.toObject(terminalStringToObject);
		OMOBJ curResult = OMToResultVisitor.getInstance().execute(current);
		OMOBJ expected = OMConverter.toObject(terminalStringToObject);
		assertEquals(expected, curResult);
	}

	@Test
	public void visitorTestOMA() throws JAXBException, OpenMathException, EvaluatorException {
		OMOBJ current = OMConverter.toObject(omaStringToObject);
		OMOBJ curResult = OMToResultVisitor.getInstance().execute(current);
		OMOBJ expected = OMConverter.toObject(resultStringToObject);
		assertEquals(expected, curResult);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testNotImplementedOMobject() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = new OMOBJ();

		if (foreignChild instanceof OMB)
			omobj.setOMB((OMB) foreignChild);
		if (foreignChild instanceof OME)
			omobj.setOME((OME) foreignChild);
		if (foreignChild instanceof OMFOREIGN)
			omobj.setOMBIND((OMBIND) foreignChild);
		if (foreignChild instanceof OMR)
			omobj.setOMR((OMR) foreignChild);

		OMToResultVisitor.getInstance().execute(omobj);
	}

	@Test
	public void testVisitAlternateTree() throws OpenMathException, EvaluatorException, JAXBException {
		OMOBJ eval = OMConverter.toObject(
				"<OMOBJ><OMA><OMA><OMS name=\"power\" cd=\"arith1\"/><OMS name=\"cos\" cd=\"transc1\"/><OMI>2</OMI></OMA><OMV name=\"x\"/></OMA></OMOBJ>");
		OMOBJ actual = OMToResultVisitor.getInstance().execute(eval);

		OMOBJ expected = OMConverter.toObject(
				"<OMOBJ><OMA><OMS name=\"power\" cd=\"arith1\"/><OMA><OMS name=\"cos\" cd=\"transc1\"/><OMV name=\"x\"/></OMA><OMI>2</OMI></OMA></OMOBJ>");
		assertEquals(expected, actual);
	}
	
	@Test
	public void isSingletonPattern() throws EvaluatorException {
		OMToSyntaxVisitor<?> obj1 = OMToResultVisitor.getInstance();
		OMToSyntaxVisitor<?> obj2 = OMToResultVisitor.getInstance();
		
		assertTrue(obj1 == obj2);
	}
}
