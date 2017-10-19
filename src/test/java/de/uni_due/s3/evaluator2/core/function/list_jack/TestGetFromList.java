package de.uni_due.s3.evaluator2.core.function.list_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetFromList extends TestFunctionAbstract {

	private static Function func = new GetFromList();

	@Test
	public void testGetFromList1() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.123));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(20), result);
	}

	@Test
	public void testGetFromList2() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.123));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(0));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Test"), result);
	}

	@Test
	public void testGetFromList3() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromListWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromListWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromListWithWrongArguments1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1)); // wrong
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromListWithWrongArguments2() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set)); // wrong
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testGetFromListIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromList(0,{1;2})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}

	@Test
	public void testGetFromListIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromList(1, {1;'Test'})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMSTR("Test"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromListIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromList(2, {1})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test
	public void testGetFromListIntegration4() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromList(1, {1; matrix(matrixrow(1,2));getFromList(0, {vector(1,2)})})",
				null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix), result.getOMA());
	}

	@Test
	public void testGetFromListIntegration5() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("getFromList(2, {1; matrix(matrixrow(1,2)); getFromList(0, {vector(1,2,7.1); 0})})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMF(7.1));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), result.getOMA());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromListWithZeroArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromList(0, {})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromListWithLessArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromList({})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromListWithMoreArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromList({}, 0, 4)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
	@Test
	public void testGetFromListIntegration7() throws OpenMathException, EvaluatorException {
		HashMap<String, OMOBJ> vars = new HashMap<>();
		OMOBJ p0 = new OMOBJ();
		OMOBJ p00 = new OMOBJ();
		p0.setOMI(OMCreator.createOMI(2));
		p00.setOMI(OMCreator.createOMI(1));
		vars.put("p0", p0);
		vars.put("p00",p00);
		
		OMOBJ omobj = ExpressionParser.parse("getFromList(1, '[var=p0];[var=p00];0')", vars, null);
		OMOBJ result = OMExecutor.execute(omobj);
		
		OMOBJ expected = OMCreator.createOMOBJ(OMCreator.createOMI(1));
		assertEquals(expected, result);
	}
}
