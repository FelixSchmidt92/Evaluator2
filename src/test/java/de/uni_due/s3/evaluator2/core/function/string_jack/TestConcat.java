
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

public class TestConcat extends TestFunctionAbstract {
	private static Function func = new Concat();

	@Test
	public void testConcatEqualStrings() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("to"));
		args.add(OMCreator.createOMSTR("get"));
		args.add(OMCreator.createOMSTR("her"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("together"), result);
	}

	@Test
	public void testConcatCaseIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("concat('Hello', ' ', 'World', '!')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMSTR("Hello World!"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testConcatWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("concat('Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testConcatWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("concat('Test', vector(2))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}