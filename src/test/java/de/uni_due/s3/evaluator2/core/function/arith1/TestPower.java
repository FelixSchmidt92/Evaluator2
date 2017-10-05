package de.uni_due.s3.evaluator2.core.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestPower extends TestFunctionAbstract {

	private Power func = new Power();

	@Test
	public void testPowerWithInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(8), result);
	}

	@Test
	public void testPowerWithFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.4));
		args.add(OMCreator.createOMF(3.9));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(Math.pow(2.4, 3.9)), result);
	}

	@Test
	public void testPowerWithMixedArgs() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMF(3.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(Math.pow(2, 3.5)), result);
	}

	@Test
	public void testPowerIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("pow(2,3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(8), result.getOMI());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testPowerWithInvalidArgumentType() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("pow(5,'Test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test
	public void testPowerSageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.45));
		args.add(OMCreator.createOMF(3));
		assertEquals("(1.45)^(3)", func.getPartialSageSyntax(args));
	}
	
	@Test
	public void testPowerPalette() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("palette(paletterow(power()))", null, null);
		String expected = "<OMOBJ><OMA>" + 
				"<OMS name=\"palette\" cd=\"editor1\"/>" +
				"<OMA>"	+
					"<OMS name=\"palette_row\" cd=\"editor1\"/>" + 
					"<OMS name=\"power\" cd=\"arith1\"/>" + 
				"</OMA></OMA></OMOBJ>";
		assertEquals(expected, result.toString());
	}

}
