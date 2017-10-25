package de.uni_due.s3.evaluator2.core.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDivide extends TestFunctionAbstract {

	private static Function func = new Divide();

	@Test
	public void testDivideInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(0.75), result);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testDivideZero() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(0));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testDivideFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.6));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(2.5 / 5.6), result);
	}

	@Test
	public void testDivideMixed() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1000));
		args.add(OMCreator.createOMF(2.00));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(500), result);
	}

	@Test
	public void testDivideIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10/5", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(2), result.getOMI());
	}

	@Test
	public void testDivideIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("divide(1,-4)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMF(-0.25), result.getOMF());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testDivideIntegrationWithWrongParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10/0", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testDivideIntegrationWithWrongParam2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10/(1-1.0)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test
	public void testDivideSageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(10));
		assertEquals("1 / 10", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDivideWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10/'test'", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
	
	@Test
	public void testDividePalette() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(divide()))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMS name=\"divide\" cd=\"arith1\"/>" + 
				"</OMA></OMA></OMOBJ>";
		assertEquals(expected, result.toString());
	}
	
	
	@Test
	public void testDivideLatex() throws EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.2));
		args.add(OMCreator.createOMI(5));
		
		String result = func.getPartialLatexSyntax(args);
		
		assertEquals("\\frac{1.2}{5}", result);
	}
	

}
