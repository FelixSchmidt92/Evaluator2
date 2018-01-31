package de.uni_due.s3.evaluator2.function.calculus1;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDefint extends TestFunctionAbstract{

	
	@Test
	public void testDefint() throws JAXBException, OpenMathException, EvaluatorException {
		OMOBJ omobj = OMConverter.toObject("<OMOBJ><OMA>" + 
				"<OMS cd=\"calculus1\" name=\"defint\"/>" + 
				"<OMA>" +
				  "<OMS cd=\"interval1\" name=\"integer_interval\" />" +
				  "<OMI>0</OMI>"+
				  "<OMS cd=\"nums1\" name=\"pi\" />" +
				"</OMA>"+
				"<OMBIND>" + 
					"<OMS cd=\"fns1\" name=\"lambda\"/>" + 
					"<OMBVAR>" + 
						"<OMV name=\"x\"/>" + 
					"</OMBVAR>" + 
					"<OMA>" + 
						"<OMS cd=\"transc1\" name=\"sin\"/>" + 
						"<OMV name=\"x\"/>" + 
					"</OMA>" + 
				"</OMBIND>" + 
				"</OMA></OMOBJ>");
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		OMOBJ expected = OMConverter.toObject("<OMOBJ>"
				+"<OMI>2</OMI>"
				+"</OMOBJ>");
		
		assertEquals(expected,OMCreator.createOMOBJ(result));
	}
	
	@Test
	public void testDefntLatexSyntax() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ omobj = OMConverter.toObject("<OMOBJ><OMA>" + 
				"<OMS cd=\"calculus1\" name=\"defint\"/>" + 
				"<OMA>" +
				  "<OMS cd=\"interval1\" name=\"integer_interval\" />" +
				  "<OMI>0</OMI>"+
				  "<OMF dec=\"1.5\" />"+
				"</OMA>"+
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
		assertEquals("\\int_{0}^{1.5} {2 \\cdot x} dx",latex);
	}
}
