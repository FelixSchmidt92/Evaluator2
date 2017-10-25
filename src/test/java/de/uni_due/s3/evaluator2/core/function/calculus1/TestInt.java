package de.uni_due.s3.evaluator2.core.function.calculus1;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestInt extends TestFunctionAbstract{

	
	@Test
	public void testInt() throws JAXBException, OpenMathException, EvaluatorException {
		OMOBJ omobj = OMConverter.toObject("<OMOBJ><OMA>" + 
				"<OMS cd=\"calculus1\" name=\"int\"/>" + 
				"<OMBIND>" + 
				"<OMS cd=\"fns1\" name=\"lambda\"/>" + 
				"<OMBVAR>" + 
				"<OMV name=\"x\"/>" + 
				"</OMBVAR>" + 
				"<OMA>" + 
				"<OMS cd=\"arith1\" name=\"times\"/>" + 
				"<OMI>2</OMI>"+
				"<OMV name=\"x\"/>" + 
				"</OMA>" + 
				"</OMBIND>" + 
				"</OMA></OMOBJ>");
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		OMOBJ expected = OMConverter.toObject("<OMOBJ>"
				+"<OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+"</OMOBJ>");
		
		assertEquals(expected,result);
	}
	
	@Test
	public void testIntLatexSyntax() throws JAXBException, EvaluatorException {
		OMOBJ omobj = OMConverter.toObject("<OMOBJ><OMA>" + 
				"<OMS cd=\"calculus1\" name=\"int\"/>" + 
				"<OMBIND>" + 
				"<OMS cd=\"fns1\" name=\"lambda\"/>" + 
				"<OMBVAR>" + 
				"<OMV name=\"x\"/>" + 
				"</OMBVAR>" + 
				"<OMA>" + 
				"<OMS cd=\"arith1\" name=\"times\"/>" + 
				"<OMI>2</OMI>"+
				"<OMV name=\"x\"/>" + 
				"</OMA>" + 
				"</OMBIND>" + 
				"</OMA></OMOBJ>");
		String latex = Evaluator.getLaTeX(omobj);
		assertEquals("\\int 2 \\cdot x dx",latex);
	}
}
