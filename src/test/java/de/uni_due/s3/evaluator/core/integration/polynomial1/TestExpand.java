package de.uni_due.s3.evaluator.core.integration.polynomial1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestExpand extends TestIntegration {

	HashMap<Integer, OMOBJ> expandFillInVariableMap = new HashMap<>();
	HashMap<String, OMOBJ> expandExerciseVariableMap = new HashMap<>();

	@Before
	public void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		expandFillInVariableMap.put(1, ExpressionParser.parse("1", null, null));
		expandFillInVariableMap.put(2, ExpressionParser.parse("5", null, null));

		expandExerciseVariableMap.put("a", ExpressionParser.parse("1", null, null));
		expandExerciseVariableMap.put("b", ExpressionParser.parse("5", null, null));
	}

	@Test
	public void testExpand1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'2*a+2*b'", null, null),
				Evaluator.evaluate("expand('2*(a+b)')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpand2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'3*a'", null, null),
				Evaluator.evaluate("expand('2*a+a')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpand3() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'2*a+c'", null, null),
				Evaluator.evaluate("expand('2*a+c')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpandWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'x+5*y'", null, null),
				Evaluator.evaluate("expand('[pos=1]x+[pos=2]y')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpandWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'x+5*y'", null, null),
				Evaluator.evaluate("expand('[var=a]x+[var=b]y')", expandExerciseVariableMap, expandFillInVariableMap));
	}

	@Test
	public void testExpandWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'a'", null, null),
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
