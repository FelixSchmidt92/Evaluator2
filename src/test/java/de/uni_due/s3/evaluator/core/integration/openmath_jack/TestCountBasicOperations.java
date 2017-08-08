package de.uni_due.s3.evaluator.core.integration.openmath_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCountBasicOperations extends TestIntegration {

	@Test
	public void testCountBasicOperations1() throws EvaluatorException, OpenMathException {
		assertEquals(4,
				Evaluator.getNumberResult("countBasicOperations('2+3*5/10-3')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testCountBasicOperations2() throws EvaluatorException, OpenMathException {
		assertEquals(0,
				Evaluator.getNumberResult("countBasicOperations('-2.1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCountBasicOperations3() throws EvaluatorException, OpenMathException {
		assertEquals(1,
				Evaluator.getNumberResult("countBasicOperations('-32+12')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testCountBasicOperations4() throws EvaluatorException, OpenMathException {
		assertEquals(2,
				Evaluator.getNumberResult("countBasicOperations('0+0-0')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testCountBasicOperations5() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("countBasicOperations('+', '-32+12-5-4/2-2*3')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testCountBasicOperations6() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("countBasicOperations('-', '-32+12-5-4/2-2*3')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testCountBasicOperations7() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("countBasicOperations('*', '-32+12-5-4/2-2*3')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testCountBasicOperations8() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("countBasicOperations('/', '-32+12-5-4/2-2*3')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testCountBasicOperationsWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("countBasicOperations('+', '[pos=2]+[pos=2]+[pos=2]')",
				exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCountBasicOperationsWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("countBasicOperations('*', '[var=b]*[var=b]*[var=b]')",
				exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test(expected = ParserException.class)
	public void testCountBasicOperationsWithWrongInputTwoCharacters() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countBasicOperations(ab, ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCountBasicOperationsWithWrongInputOneRational() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countBasicOperations(5)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCountBasicOperationsWithTwoRationalArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countBasicOperations(2, 2)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testCountBasicOperationsThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countBasicOperations('a', 'a', 'a')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testCountBasicOperationsWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countBasicOperations('[var=j]', '2+1')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testCountBasicOperationsWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countBasicOperations('[pos=42]', '4-1*8')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
