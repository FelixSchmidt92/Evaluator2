package de.uni_due.s3.evaluator2.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.arith1.GCD;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGCD extends TestFunctionAbstract {

	private static Function func = new GCD();

	@Test
	public void testGcdInteger() throws EvaluatorException, OpenMathException {

		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(12));
		args.add(OMCreator.createOMI(9));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testGcdIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("gcd(10,5)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(5), result);
	}
	
	@Test
	public void testGcdLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("gcd(1,2)", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("\\mbox{gcd}\\left(1,2\\right)", latex);
	}

	@Test
	public void testGcdSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(1);
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(1));
		assertEquals("gcd(1,1)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGcdWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("gcd(2,'test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

}