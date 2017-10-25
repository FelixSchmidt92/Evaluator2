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
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.core.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestLessThanOrEqual extends TestFunctionAbstract {

	private final Function func = new LessThanOrEqual();

	@Test
	public void testLessThanOrEqualOMI1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testLessThanOrEqualOMI2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(109345));
		args.add(OMCreator.createOMI(109345));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void Less1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMF(0.546362));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void Less2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(12.3045));
		args.add(OMCreator.createOMF(12.3045));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testLessThanOrEqualOMFAndOMI1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMI(-3));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testLessThanOrEqualOMFAndOMI2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(20));
		args.add(OMCreator.createOMF(20.00f));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGreaterThanOrEqualWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail("there should be an error");
	}
	
	@Test
	public void testEqualLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("1 <= 3", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("1\\leq3", latex);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testLessThanOrEqualWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail("there should be an error");
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testLessThanOrEqualWithWrongArguments() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(null);
		args.add(OMCreator.createOMSTR(null));
		func.evaluate(args);
		fail("there should be an error");
	}

	@Test
	public void testLessThanOrEqualIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10 <= 5", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test
	public void testLessThanSageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(5));
		args.add(OMCreator.createOMI(9));
		assertEquals("5 <= 9", func.getPartialSageSyntax(args));
	}
	
	@Test
	public void testLessThanOrEqualPalette() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(lessThanOrEqual()))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMS name=\"leq\" cd=\"relation1\"/>" + 
				"</OMA></OMA></OMOBJ>";
		assertEquals(expected, result.toString());
	}

}
