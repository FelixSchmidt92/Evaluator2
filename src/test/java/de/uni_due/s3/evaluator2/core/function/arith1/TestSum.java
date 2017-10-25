package de.uni_due.s3.evaluator2.core.function.arith1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSum extends TestFunctionAbstract{

	private final Function func = new Sum();
	
	@Test
	public void testSum() throws JAXBException, OpenMathException, EvaluatorException {
		OMOBJ omobj = OMConverter.toObject(""
				+ "<OMOBJ>" + 
				"                <OMA>" + 
				"                    <OMS cd=\"arith1\" name=\"sum\"/>" + 
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
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		OMOBJ expected = OMConverter.toObject("<OMOBJ>"
				+"<OMA>"
				+"<OMS cd=\"arith1\" name=\"times\"/>"
				+ "<OMI>12</OMI>"
				+ "<OMV name=\"x\"/>"
				+ "</OMA>"
				+"</OMOBJ>");
		
		assertEquals(expected,result);
	}
	
	@Test
	public void testSumSageSyntax() throws JAXBException, FunctionException, NoRepresentationAvailableException, EvaluatorException {
		OMOBJ omobj = OMConverter.toObject(""
				+ "<OMOBJ>" + 
				"                <OMA>" + 
				"                    <OMS cd=\"arith1\" name=\"sum\"/>" + 
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
		assertEquals("var('x'); var('n'); sum(3 * x for n in (2..5))",result);
	}
	
	@Test
	public void testSumLatexSyntax() throws JAXBException, EvaluatorException {
		OMOBJ omobj = OMConverter.toObject(""
				+ "<OMOBJ>" + 
				"                <OMA>" + 
				"                    <OMS cd=\"arith1\" name=\"sum\"/>" + 
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
		assertEquals("\\sum_{2}^{5} {3 \\cdot x}",latex);
	}
	
	@Test
	public void testSumPalette() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(sum()))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMA>" + 
						"<OMS name=\"sum\" cd=\"arith1\"/>" + 
						"<OMA>"+
							"<OMS name=\"integer_interval\" cd=\"interval1\"/>"+
							"<OMS name=\"input_box\" cd=\"editor1\"/>" +
							"<OMS name=\"input_box\" cd=\"editor1\"/>" +
						"</OMA>"+
						"<OMBIND>"+
							"<OMS name=\"lambda\" cd=\"fns1\"/>"+
							"<OMBVAR>"+
								"<OMV name=\"n\"/>"+
							"</OMBVAR>"+
							"<OMS name=\"input_box\" cd=\"editor1\"/>"+
						"</OMBIND>"+
					"</OMA>"+
				"</OMA>"
				+ "</OMA></OMOBJ>";
		assertEquals(expected,result.toString());
	}
}
