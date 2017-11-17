package de.uni_due.s3.evaluator2.core.function.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCompareToIgnoreCase extends TestFunctionAbstract {
	private static Function func = new CompareToIgnoreCase();

	@Test
	public void testCompareToIgnoreCaseEqualStrings() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMSTR("test"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testCompareToIgnoreCaseNotEqualStrings() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Testin"));
		args.add(OMCreator.createOMSTR("TesTer"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(4), result);
	}

	@Test
	public void testCompareToIgnoreCaseIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("compareToIgnoreCase('Hallo', 'Test')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMI(-12), result.getOMI());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCompareToIgnoreCaseWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("compareToIgnoreCase('Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCompareToIgnoreCaseWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("compareToIgnoreCase('Test', 'Test', 'Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testCompareToIgnoreCaseWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("compareToIgnoreCase('Test', vector(2))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}