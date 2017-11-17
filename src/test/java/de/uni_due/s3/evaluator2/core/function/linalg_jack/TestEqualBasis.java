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
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqualBasis extends TestFunctionAbstract {

	Function func = new EqualBasis();

	@Test
	public void testEqualBasis1() throws OpenMathException, EvaluatorException {
		ArrayList<Object> vector1 = new ArrayList<>();
		vector1.add(OMCreator.createOMI(1));
		vector1.add(OMCreator.createOMI(0));
		ArrayList<Object> vector2 = new ArrayList<>();
		vector2.add(OMCreator.createOMI(0));
		vector2.add(OMCreator.createOMI(1));
		ArrayList<Object> vector3 = new ArrayList<>();
		vector3.add(OMCreator.createOMI(0));
		vector3.add(OMCreator.createOMI(1));
		ArrayList<Object> vector4 = new ArrayList<>();
		vector4.add(OMCreator.createOMI(0));
		vector4.add(OMCreator.createOMI(1));

		ArrayList<Object> set1 = new ArrayList<>();
		set1.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector1));
		set1.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector2));
		ArrayList<Object> set2 = new ArrayList<>();
		set2.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector3));
		set2.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector4));

		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set2));
		args.add(OMCreator.createOMI(2));
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testEqualBasis2() throws OpenMathException, EvaluatorException {
		ArrayList<Object> vector1 = new ArrayList<>();
		vector1.add(OMCreator.createOMI(1));
		vector1.add(OMCreator.createOMI(0));
		ArrayList<Object> vector2 = new ArrayList<>();
		vector2.add(OMCreator.createOMI(0));
		vector2.add(OMCreator.createOMI(1));
		ArrayList<Object> vector3 = new ArrayList<>();
		vector3.add(OMCreator.createOMI(1));
		vector3.add(OMCreator.createOMI(0));
		ArrayList<Object> vector4 = new ArrayList<>();
		vector4.add(OMCreator.createOMI(0));
		vector4.add(OMCreator.createOMI(1));

		ArrayList<Object> set1 = new ArrayList<>();
		set1.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector1));
		set1.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector2));
		ArrayList<Object> set2 = new ArrayList<>();
		set2.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector3));
		set2.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector4));

		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set2));
		args.add(OMCreator.createOMI(2));
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testEqualBasisIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse(
				"equalBasis(set(vector(1,2,3), vector(3,2,1)), set(vector(1,2,3), vector(3,2,1)), 3)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualBasisIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("equalBasis(set(vector(1,0), vector(0,1)), set(vector(1,0), vector(1,0)), 2)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualBasisWithWrongInput() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("equalBasis(set(vector(1,0), vector(0,1)), set(vector(1,0), vector(1,0)), 'Hello')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualBasisWithLess() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalBasis(set(vector(1,0), vector(0,1)), set(vector(1,0), vector(1,0)))",
				null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualBasisWithMore() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("equalBasis(set(vector(1,0), vector(0,1)), set(vector(1,0), vector(1,0)), 1, 2)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
	
	@Test
	public void testEqualBasisIntegration4() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("equalBasis(set(vector(1,1,0), vector(0,1,1)), set(vector(1,1,0), vector(0,1,1)), 3)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}
}
