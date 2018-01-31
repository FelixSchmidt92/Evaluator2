package de.uni_due.s3.evaluator2.function.arith1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Ignore;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.arith1.Product;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestProduct extends TestFunctionAbstract{

	private final Function func = new Product();
	
	@Ignore
	@Test
	public void testProduct() throws JAXBException, OpenMathException, EvaluatorException {
		OMOBJ omobj = OMConverter.toObject(""
				+ "<OMOBJ>" + 
				"                <OMA>" + 
				"                    <OMS cd=\"arith1\" name=\"product\"/>" + 
				"                    <OMA>" + 
				"                        <OMS cd=\"interval1\" name=\"integer_interval\"/>" + 
				"                        <OMI>2</OMI>" + 
				"                        <OMI>5</OMI>" + 
				"                    </OMA>" + 
				"                    <OMBIND>" + 
				"                        <OMS cd=\"fns1\" name=\"lambda\"/>" + 
				"                        <OMBVAR>" + 
				"                            <OMV name=\"n\"/>" + 
				"                        </OMBVAR>" + 
				"                        <OMA>" + 
				"                            <OMS cd=\"arith1\" name=\"times\"/>" + 
				"                            <OMI>3</OMI>" + 
				"                            <OMV name=\"x\"/>" + 
				"                        </OMA>" + 
				"                    </OMBIND>" + 
				"                </OMA>" + 
				"            </OMOBJ>");
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		OMOBJ expected = OMConverter.toObject("<OMOBJ>"
				+"<OMA>"
				+"<OMS cd=\"arith1\" name=\"times\"/>"
				+ "<OMI>81</OMI>"
				+ "<OMA>"
					+ "<OMS cd=\"arith1\" name=\"power\" />"
					+ "<OMV name=\"x\"/>"
					+ "<OMI>4</OMI>"
				+ "</OMA>"	
				+ "</OMA>"
				+"</OMOBJ>");
		
		assertEquals(expected,OMCreator.createOMOBJ(result));
	}
	
	@Test
	public void testProductSageSyntax() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ omobj = OMConverter.toObject(""
				+ "<OMOBJ>" + 
				"                <OMA>" + 
				"                    <OMS cd=\"arith1\" name=\"product\"/>" + 
				"                    <OMA>" + 
				"                        <OMS cd=\"interval1\" name=\"integer_interval\"/>" + 
				"                        <OMI>2</OMI>" + 
				"                        <OMI>5</OMI>" + 
				"                    </OMA>" + 
				"                    <OMBIND>" + 
				"                        <OMS cd=\"fns1\" name=\"lambda\"/>" + 
				"                        <OMBVAR>" + 
				"                            <OMV name=\"n\"/>" + 
				"                        </OMBVAR>" + 
				"                        <OMA>" + 
				"                            <OMS cd=\"arith1\" name=\"times\"/>" + 
				"                            <OMI>3</OMI>" + 
				"                            <OMV name=\"x\"/>" + 
				"                        </OMA>" + 
				"                    </OMBIND>" + 
				"                </OMA>" + 
				"            </OMOBJ>");
		omobj.getOMA().getOmel().remove(0);
		List<Object> omel = omobj.getOMA().getOmel();
		String result = func.getPartialSageSyntax(omel);
		assertEquals("var('x'); var('n'); prod(( ( (3) * (x) ) ) for n in (2..5))",result);
	}
	
	@Test
	public void testProductLatexSyntax() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ omobj = OMConverter.toObject(""
				+ "<OMOBJ>" + 
				"                <OMA>" + 
				"                    <OMS cd=\"arith1\" name=\"product\"/>" + 
				"                    <OMA>" + 
				"                        <OMS cd=\"interval1\" name=\"integer_interval\"/>" + 
				"                        <OMI>2</OMI>" + 
				"                        <OMI>5</OMI>" + 
				"                    </OMA>" + 
				"                    <OMBIND>" + 
				"                        <OMS cd=\"fns1\" name=\"lambda\"/>" + 
				"                        <OMBVAR>" + 
				"                            <OMV name=\"n\"/>" + 
				"                        </OMBVAR>" + 
				"                        <OMA>" + 
				"                            <OMS cd=\"arith1\" name=\"times\"/>" + 
				"                            <OMI>3</OMI>" + 
				"                            <OMV name=\"x\"/>" + 
				"                        </OMA>" + 
				"                    </OMBIND>" + 
				"                </OMA>" + 
				"            </OMOBJ>");
		String latex = Evaluator.getLaTeX(omobj);
		assertEquals("\\prod_{2}^{5} {3 \\cdot x}",latex);
	}

}
