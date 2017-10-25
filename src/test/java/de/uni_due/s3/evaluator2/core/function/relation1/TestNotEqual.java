package de.uni_due.s3.evaluator2.core.function.relation1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.core.visitor.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestNotEqual extends TestFunctionAbstract {

	private final Function func = new NotEqual();

	@Test
	public void testNotEqualsOMI1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(10));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMI2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(120976));
		args.add(OMCreator.createOMI(12));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testNotEqualsOMF1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMF(0.546363));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMF2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(12.3045));
		args.add(OMCreator.createOMF(12.3044));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testNotEqualsOMS1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMSymbol.ARITH1_PLUS);
		args.add(OMSymbol.ARITH1_PLUS);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMS2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMSymbol.RELATION1_EQ);
		args.add(OMSymbol.ARITH1_PLUS);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testNotEqualsOMA1() throws OpenMathException, EvaluatorException {
		ArrayList<Object> arguments = new ArrayList<Object>(3);
		arguments.add(OMCreator.createOMI(10));
		arguments.add(OMCreator.createOMI(-10));
		arguments.add(OMCreator.createOMI(30));
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments));
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMA2() throws OpenMathException, EvaluatorException {
		ArrayList<Object> arguments = new ArrayList<Object>(3);
		ArrayList<Object> arguments2 = new ArrayList<Object>(3);

		arguments.add(OMCreator.createOMI(10));
		arguments.add(OMCreator.createOMI(-10));
		arguments.add(OMCreator.createOMI(30));
		arguments2.add(OMCreator.createOMI(10));
		arguments2.add(OMCreator.createOMI(20));
		arguments2.add(OMCreator.createOMI(30));

		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments));
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments2));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testNotEqualsOMSTR1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMSTR2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("tes t"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}
	
	@Test
	public void testEqualLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("1 != 3", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("1\\neq3", latex);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testNotEqualsWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testNotEqualsIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("notequal(10,3)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testNotEqualsSageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(1));
		assertEquals("10 != 1", func.getPartialSageSyntax(args));
	}
	
	@Test
	public void testNotEqualPalette() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(notequal()))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMS name=\"neq\" cd=\"relation1\"/>" + 
				"</OMA></OMA></OMOBJ>";
		assertEquals(expected, result.toString());
	}

}
