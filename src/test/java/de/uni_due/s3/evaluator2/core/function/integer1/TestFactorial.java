package de.uni_due.s3.evaluator2.core.function.integer1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestFactorial extends TestFunctionAbstract{

	Function func = new Factorial();
	
	@Test
	public void testFactorial1() throws EvaluatorException, OpenMathException {
		OMI omi = OMCreator.createOMI(5);
		ArrayList<Object> args = new ArrayList<>();
		
		args.add(omi);
		
		Object actual = func.evaluate(args);
		OMI expected = OMCreator.createOMI(120);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFactorial2() throws EvaluatorException, OpenMathException {
		OMI omi = OMCreator.createOMI(1);
		ArrayList<Object> args = new ArrayList<>();
		
		args.add(omi);
		
		Object actual = func.evaluate(args);
		OMI expected = OMCreator.createOMI(1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFactorial3() throws EvaluatorException, OpenMathException {
		OMI omi = OMCreator.createOMI(0);
		ArrayList<Object> args = new ArrayList<>();
		
		args.add(omi);
		
		Object actual = func.evaluate(args);
		OMI expected = OMCreator.createOMI(1);
		
		assertEquals(expected, actual);
	}
	
	@Test(expected= FunctionInvalidArgumentException.class)
	public void testFactorialInvalidInput() throws EvaluatorException, OpenMathException {
		OMI omi = OMCreator.createOMI(-1);
		ArrayList<Object> args = new ArrayList<>();
		
		args.add(omi);
		func.evaluate(args);
	}
	
	@Test(expected= FunctionInvalidArgumentTypeException.class)
	public void testFactorialInvalidArgument() throws EvaluatorException, OpenMathException {
		OMSTR omstr = OMCreator.createOMSTR("test");
		ArrayList<Object> args = new ArrayList<>();
		
		args.add(omstr);
		func.evaluate(args);
	}
	
	
	@Test
	public void testFactorialSageSyntax() throws EvaluatorException, OpenMathException {
		OMI omi = OMCreator.createOMI(3);
		ArrayList<Object> args = new ArrayList<>();
		
		args.add(omi);
		String actual = func.getPartialSageSyntax(args);
		String expected = "factorial(3)";
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testFactorialIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("factorial(5)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(120), result);
	}
}
