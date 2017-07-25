package de.uni_due.s3.evaluator.core.function.linalg_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.linalg_jack.EqualBasis;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqualBasis extends TestFunctionAbstract {

	Function func = new EqualBasis();

	@Test
	public void testEqualBasis1() throws UndefinedFillInVariableException, UndefinedExerciseVariableException,
			ParserException, FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set1));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set2));
		args.add(OMCreator.createOMI(2));
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testEqualBasis2() throws UndefinedFillInVariableException, UndefinedExerciseVariableException,
			ParserException, FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set1));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set2));
		args.add(OMCreator.createOMI(2));
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testEqualBasisIntegration1()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse(
				"equalBasis(set(vector(1,2,3), vector(3,2,1)), set(vector(1,2,3), vector(3,2,1)), 3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualBasisIntegration2()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser
				.parse("equalBasis(set(vector(1,0), vector(0,1)), set(vector(1,0), vector(1,0)), 2)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualBasisWithWrongInput()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser
				.parse("equalBasis(set(vector(1,0), vector(0,1)), set(vector(1,0), vector(1,0)), 'Hello')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualBasisWithLess() throws UndefinedFillInVariableException, UndefinedExerciseVariableException,
			ParserException, FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("equalBasis(set(vector(1,0), vector(0,1)), set(vector(1,0), vector(1,0)))",
				null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualBasisWithMore() throws UndefinedFillInVariableException, UndefinedExerciseVariableException,
			ParserException, FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser
				.parse("equalBasis(set(vector(1,0), vector(0,1)), set(vector(1,0), vector(1,0)), 1, 2)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
