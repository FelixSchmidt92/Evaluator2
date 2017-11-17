package de.uni_due.s3.evaluator2.core.function.list_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetRandomFromList extends TestFunctionAbstract {

	private static Function func = new GetRandomFromList();

	@Test
	public void testGetRandomFromList1() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();

		OMF omf = OMCreator.createOMF(1.1);
		OMI omi = OMCreator.createOMI(10);
		OMSTR omstr = OMCreator.createOMSTR("test");
		set.add(omstr);
		set.add(omi);
		set.add(omf);

		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		Object result = func.evaluate(args);
		assertTrue(result.equals(omf) || result.equals(omi) || result.equals(omstr));
	}

	@Test
	public void testGetRandomFromList2() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();

		OMF omf = OMCreator.createOMF(1.1);
		OMI omi = OMCreator.createOMI(10);
		OMSTR omstr = OMCreator.createOMSTR("test");
		set.add(omstr);
		set.add(omi);
		set.add(omf);

		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		Object result = func.evaluate(args);
		assertTrue(result.equals(omf) || result.equals(omi) || result.equals(omstr));

	}

	@Test
	public void testGetRandomFromList3() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();

		OMA oma = OMCreator.createOMA(OMSymbol.ARITH1_ABS, new ArrayList<Object>());
		set.add(oma);
		set.add(oma);
		set.add(oma);

		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		Object result = func.evaluate(args);
		assertTrue(result.equals(oma));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromListWithLessThanMinParam() throws OpenMathException, EvaluatorException {

		List<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromListWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromListWithWrongArguments1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test
	public void testGetRandomFromListIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getRandomFromList({1})", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetRandomFromListWithEmptyArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getRandomFromList({})", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromListWithLessArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getRandomFromList()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromListWithMoreArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getRandomFromList({}, 0, 4)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}
