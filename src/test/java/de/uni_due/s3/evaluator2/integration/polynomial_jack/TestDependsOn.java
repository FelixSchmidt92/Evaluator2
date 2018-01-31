package de.uni_due.s3.evaluator2.integration.polynomial_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDependsOn extends TestIntegration {

	static HashMap<Integer, OMOBJ> dependsOnFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> dependsOnExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		dependsOnFillInVariableMap.put(1, ExpressionParser.parse("x", null, null));
		dependsOnFillInVariableMap.put(2, ExpressionParser.parse("y", null, null));

		dependsOnExerciseVariableMap.put("a", ExpressionParser.parse("x", null, null));
		dependsOnExerciseVariableMap.put("b", ExpressionParser.parse("y", null, null));
	}

	@Test
	public void testDependsOn1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("dependsOn('2*x+3*m-n+2*b','m')", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOn2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("dependsOn('2*x+3*a-x+2*b','y')", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOn3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("dependsOn(2*x+3*m-n+2*b, m)", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOn4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("dependsOn(m, m)", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOn5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("dependsOn(0, a)", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOnWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("dependsOn('2*x+3*a-x+2*b','[pos=2]')", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOnWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("dependsOn('[pos=1]','x')", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOnWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("dependsOn('2*[var=a]+3*a-[var=a]+2*b','x')",
				dependsOnExerciseVariableMap, dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOnWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("dependsOn('2*[var=b]+3*a-x+2*b','y')", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOnWithVariables3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("dependsOn('2*a+a+4','[var=a]')", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test
	public void testDependsOnWithONECharacter() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("dependsOn(a, a)", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDependsOnWithEmptyStringArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("dependsOn('', '')", dependsOnExerciseVariableMap, dependsOnFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDependsOnWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("dependsOn()", dependsOnExerciseVariableMap, dependsOnFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testDependsOnWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("dependsOn([var=j], [var=j])", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testDependsOnWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("dependsOn([pos=42], [pos=42])", dependsOnExerciseVariableMap,
				dependsOnFillInVariableMap);
		fail();
	}
}
