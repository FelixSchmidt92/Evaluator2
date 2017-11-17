package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsLinearlyIndependent extends TestFunctionAbstract {

	Function func = new IsLinearlyIndependent();

	@Test
	public void testIsLinearlyIndependent1() throws OpenMathException, EvaluatorException {
		ArrayList<Object> vector1 = new ArrayList<>();
		vector1.add(OMCreator.createOMI(1));
		vector1.add(OMCreator.createOMI(0));
		ArrayList<Object> vector2 = new ArrayList<>();
		vector2.add(OMCreator.createOMI(0));
		vector2.add(OMCreator.createOMI(1));

		ArrayList<Object> set = new ArrayList<>();
		set.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector1));
		set.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector2));

		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsLinearlyIndependent2() throws OpenMathException, EvaluatorException {
		ArrayList<Object> vector1 = new ArrayList<>();
		vector1.add(OMCreator.createOMI(0));
		vector1.add(OMCreator.createOMI(2));
		ArrayList<Object> vector2 = new ArrayList<>();
		vector2.add(OMCreator.createOMI(0));
		vector2.add(OMCreator.createOMI(1));

		ArrayList<Object> set = new ArrayList<>();
		set.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector1));
		set.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector2));

		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsLinearlyIndependentIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(set(vector(1,2,3), vector(3,2,1)))", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testIsLinearlyIndependentIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(set(vector(1,1,0), vector(1,1,1)))", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testIsLinearlyIndependentIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("isLinearlyIndependent({vector(1,1,0); vector(1,1,1); vector(1.5, 1.1, 1.11)})", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testIsLinearlyIndependentIntegration4() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(set(vector(1,1,0),  vector(2, 2, 0)))", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testIsLinearlyIndependentWithWrongInput1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(set(vector(1,1,0),  vector(2, 2, 0), 3))", null,
				null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test
	public void testIsLinearlyIndependentWithWrongInput2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(vector(1,1,0))", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsLinearlyIndependentWithLess() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(vector(1,1,0), vector(1,1,0))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsLinearlyIndependentWithMore() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}
