package de.uni_due.s3.evaluator2.core.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRoot extends TestFunctionAbstract {
	private Function func = new Root();

	@Test
	public void testRootInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(9));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testRootFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(6.25));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(2.5), result);
	}

	@Test
	public void testRootIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root(25)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(5), result.getOMI());
	}

	@Test
	public void testRootIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root(25)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(5), result.getOMI());
	}
	

	@Test
	public void testRootLatexSyntaxOneArgument() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("root(3)", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("\\sqrt{3}", latex);
	}
	
	@Test
	public void testRootLatexSyntaxTwoArguments() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("root(10,3)", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("\\sqrt[3]{10}", latex);
	}

	@Test
	public void testRootIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root(125, 3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(5), result.getOMI());
	}

	@Test
	public void testRootIntegration4() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root(-125, 3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(-5), result.getOMI());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAbsWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root('test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
	@Test
	public void testRootPaletteStandard() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(root()))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMA>" + 
						"<OMS name=\"root\" cd=\"arith1\"/>" + 
						"<OMS name=\"input_box\" cd=\"editor1\"/>" +
						"<OMS name=\"input_box\" cd=\"editor1\"/>" +
					"</OMA>"+
				"</OMA>"
				+ "</OMA></OMOBJ>";
		assertEquals(expected,result.toString());
	}
	@Test
	public void testRootPaletteOneArgument() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(root(1)))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMA>" + 
						"<OMS name=\"root\" cd=\"arith1\"/>" + 
						"<OMI>1</OMI>" +
						"<OMS name=\"input_box\" cd=\"editor1\"/>" +
					"</OMA>"+
				"</OMA>"
				+ "</OMA></OMOBJ>";
		assertEquals(expected,result.toString());
	}
	@Test
	public void testRootPaletteTwoArguments() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(root(x,5)))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMA>" + 
						"<OMS name=\"root\" cd=\"arith1\"/>" + 
						"<OMS name=\"input_box\" cd=\"editor1\"/>" +
						"<OMI>5</OMI>" +
					"</OMA>"+
				"</OMA>"
				+ "</OMA></OMOBJ>";
		assertEquals(expected,result.toString());
	}

}
