package de.uni_due.s3.evaluator2.function.list_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.list_jack.GetFromOrderedList;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetFromOrderedList extends TestFunctionAbstract {

	private static Function func = new GetFromOrderedList();

	@Test
	public void testGetFromOrderedList1() throws EvaluatorException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMI(1));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.123));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(3.123), result);
	}

	@Test
	public void testGetFromOrderedList2() throws EvaluatorException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMSTR("Hallo"));
		set.add(OMCreator.createOMSTR("Hello"));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(0));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Hallo"), result);
	}

	@Test
	public void testGetFromOrderedList3() throws EvaluatorException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMF(3.343));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.243));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(20), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedListWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedListWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromOrderedListWithWrongArguments1() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1)); // wrong
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedListWithWrongArguments2() throws EvaluatorException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set)); // wrong
		func.evaluate(args);
		fail();
	}

	@Test
	public void testGetFromOrderedListIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedList(0, {1;2})", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedListIntegrationWrongArgsInList() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedList({1;'Test'},1)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromOrderedListIntegrationWithOutOfBounds() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedList(2, {1})", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedListIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedList({matrix(matrixrow(1,2)); vector(1,2)},1)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromOrderedListWithZeroArgs() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedList(0, {})", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedListWithLessArgs() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedList({})", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedListWithMoreArgs() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedList({}, 0, 4)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}
