package de.uni_due.s3.evaluator2.core.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestTimes extends TestFunctionAbstract {

	private static Times func = new Times();

	@Test
	public void testTimesInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(12), result);
	}

	@Test
	public void testTimesFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.8));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(14.5), result);
	}

	@Test
	public void testTimesMixed() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(1000));
		args.add(OMCreator.createOMF(4.892));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(4892), result);
	}

	@Test
	public void testTimesIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10*5", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(50), result.getOMI());
	}

	@Test
	public void testTimesIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("times(10,-17)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(-170), result.getOMI());
	}

	@Test
	public void testTimesSageSyntax1() throws EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(10));
		assertEquals("(( ( (1) * (10) ) ))", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testTimesWithWrongArguments1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10*'test'", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testTimesWithWrongArguments2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("times('test',17)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
	
	@Test
	public void testPlusPalette() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(times()))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMS name=\"times\" cd=\"arith1\"/>" + 
				"</OMA></OMA></OMOBJ>";
		assertEquals(expected, result.toString());
	}

	@Test
	public void testTimesThreeArguments() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(24), result);
	}
	
	@Test
	public void testTimesFourArguments() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		args.add(OMCreator.createOMI(5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(120), result);
	}
	
	@Test
	public void testTimesFourArgumentsWithFirstVariable() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMV("a"));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		args.add(OMCreator.createOMI(5));
		Object result = func.evaluate(args);
		
		
		List<Object> expArgs = new ArrayList<Object>(2);
		expArgs.add(OMCreator.createOMI(60));
		expArgs.add(OMCreator.createOMV("a"));
		OMA expected = OMCreator.createOMA(OMSymbol.ARITH1_TIMES, expArgs);
		assertEquals(expected, result);
	}
	
	@Test
	public void testTimesFourArgumentsWithLastVariable() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		args.add(OMCreator.createOMV("a"));
		Object result = func.evaluate(args);
		
		
		List<Object> expArgs = new ArrayList<Object>(2);
		expArgs.add(OMCreator.createOMI(24));
		expArgs.add(OMCreator.createOMV("a"));
		OMA expected = OMCreator.createOMA(OMSymbol.ARITH1_TIMES, expArgs);
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testTimesFourArgumentsLatex() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		args.add(OMCreator.createOMI(5));
		String result = func.getPartialLatexSyntax(args);
		assertEquals("2 \\cdot 3 \\cdot 4 \\cdot 5", result);
	}
}
