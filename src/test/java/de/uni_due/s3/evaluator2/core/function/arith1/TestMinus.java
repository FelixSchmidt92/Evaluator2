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
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMinus extends TestFunctionAbstract {

	private Function func = new Minus();

	@Test
	public void testMinusInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testMinusFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.8));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(-3.3), result);
	}

	@Test
	public void testMinusMixed() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMF(2.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(-1.5), result);
	}

	@Test
	public void testMinusIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10-5", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(5), result.getOMI());
	}

	@Test
	public void testMinusIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("minus(10,17)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(-7), result.getOMI());
	}

	@Test
	public void testMinusSageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(1));
		assertEquals("(1 - 1)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMinusWithWrongArguments1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10-'test'", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMinusWithWrongArguments2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("minus('test',17)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
	
	@Test
	public void testMinusPalette() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(minus()))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMS name=\"minus\" cd=\"arith1\"/>" + 
				"</OMA></OMA></OMOBJ>";
		assertEquals(expected, result.toString());
	}

}
