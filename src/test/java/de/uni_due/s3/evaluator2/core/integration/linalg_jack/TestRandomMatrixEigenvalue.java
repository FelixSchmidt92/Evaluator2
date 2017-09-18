package de.uni_due.s3.evaluator2.core.integration.linalg_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomMatrixEigenvalue extends TestIntegration {

	static HashMap<Integer, OMOBJ> randomMatrixEigenvalueFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> randomMatrixEigenvalueExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		randomMatrixEigenvalueFillInVariableMap.put(1, ExpressionParser.parse("'[1,1,1]'", null, null));

		randomMatrixEigenvalueExerciseVariableMap.put("a", ExpressionParser.parse("'[1,1,1]'", null, null));
	}

	@Test
	public void testRandomMatrixEigenValue1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("matrix(matrixrow(1,0,0), matrixrow(0,1,0), matrixrow(0,0,1))", null, null),
				Evaluator.evaluate("randomMatrixEigenvalue('QQ', '3', '[1,1,1]', '[1,1,1]')",
						randomMatrixEigenvalueExerciseVariableMap, randomMatrixEigenvalueFillInVariableMap));
	}

	@Test
	public void testRandomMatrixEigenValue2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("matrix(matrixrow(1,0), matrixrow(0,1))", null, null),
				Evaluator.evaluate("randomMatrixEigenvalue('RR', '2', '[1,1]', '[1,1]')",
						randomMatrixEigenvalueExerciseVariableMap, randomMatrixEigenvalueFillInVariableMap));
	}

	@Test
	public void testRandomMatrixEigenValue3() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("matrix(matrixrow(1))", null, null),
				Evaluator.evaluate("randomMatrixEigenvalue('ZZ', '1', '[1]', '[1]')",
						randomMatrixEigenvalueExerciseVariableMap, randomMatrixEigenvalueFillInVariableMap));
	}

	@Test
	public void testRandomMatrixEigenValueWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("matrix(matrixrow(1,0,0), matrixrow(0,1,0), matrixrow(0,0,1))", null, null),
				Evaluator.evaluate("randomMatrixEigenvalue('QQ', '3', '[var=a]', '[var=a]')",
						randomMatrixEigenvalueExerciseVariableMap, randomMatrixEigenvalueFillInVariableMap));
	}

	@Test
	public void testRandomMatrixEigenValueWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("matrix(matrixrow(1,0,0), matrixrow(0,1,0), matrixrow(0,0,1))", null, null),
				Evaluator.evaluate("randomMatrixEigenvalue('QQ', '3', '[pos=1]', '[pos=1]')",
						randomMatrixEigenvalueExerciseVariableMap, randomMatrixEigenvalueFillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testRandomMatrixEigenValueWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixEigenvalue(ab, ab, ab, ab)", randomMatrixEigenvalueExerciseVariableMap,
				randomMatrixEigenvalueFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testRandomMatrixEigenValueWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixEigenvalue('', '', '', '')", randomMatrixEigenvalueExerciseVariableMap,
				randomMatrixEigenvalueFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomMatrixEigenValueWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixEigenvalue()", randomMatrixEigenvalueExerciseVariableMap,
				randomMatrixEigenvalueFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testRandomMatrixEigenValueWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixEigenvalue('[var=j]', '[var=j]', '[var=j]', '[var=j]')",
				randomMatrixEigenvalueExerciseVariableMap, randomMatrixEigenvalueFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testRandomMatrixEigenValueWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixEigenvalue('[pos=42]', '[pos=42]', '[pos=42]', '[pos=42]')",
				randomMatrixEigenvalueExerciseVariableMap, randomMatrixEigenvalueFillInVariableMap);
		fail();
	}
}
