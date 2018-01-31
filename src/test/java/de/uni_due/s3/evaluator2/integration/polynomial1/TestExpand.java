package de.uni_due.s3.evaluator2.integration.polynomial1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
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

public class TestExpand extends TestIntegration {

	static HashMap<Integer, OMOBJ> expandFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> expandExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException, ErroneousExerciseVariableException, OpenMathException {
		expandFillInVariableMap.put(1, ExpressionParser.parse("1", null, null));
		expandFillInVariableMap.put(2, ExpressionParser.parse("5", null, null));

		expandExerciseVariableMap.put("a", ExpressionParser.parse("1", null, null));
		expandExerciseVariableMap.put("b", ExpressionParser.parse("5", null, null));
	}

	@Test
	public void testExpand1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*a+2*b", null, null),
				Evaluator.evaluate("expand('2*(a+b)')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpand2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("3*a", null, null),
				Evaluator.evaluate("expand('2*a+a')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpand3() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*a+c", null, null),
				Evaluator.evaluate("expand('2*a+c')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpandWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("x+5*y", null, null),
				Evaluator.evaluate("expand('[pos=1]*x+[pos=2]*y')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpandWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("x+5*y", null, null),
				Evaluator.evaluate("expand('[var=a]*x+[var=b]*y')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpandWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("a", null, null),
				Evaluator.evaluate("expand(a)", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testExpandWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("expand('')", expandExerciseVariableMap, expandFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testExpandWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("expand()", expandExerciseVariableMap, expandFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testExpandWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("expand([var=j])", expandExerciseVariableMap, expandFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testExpandWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("expand([pos=42])", expandExerciseVariableMap, expandFillInVariableMap);
		fail();
	}
}
