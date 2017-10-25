package de.uni_due.s3.evaluator2.core.function.set1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSize extends TestFunctionAbstract {

	private Function func = new Size();
	private List<Object> args;
	private Object result;

	@Test
	public void testSize() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>();
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(OMCreator.createOMI(10));
		arguments.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, arguments));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(1), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testSizeWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>(0);
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testSizeWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testSizeWithWrongArguments() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>();
		args.add(null);
		func.evaluate(args);
		fail();
	}

	@Test
	public void testSizeIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("sizeOfSet({1;2;3})", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(3), result.getOMI());
	}
}
