package de.uni_due.s3.evaluator2.core.integration.polynomial_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
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

public class TestEqualsExpr extends TestIntegration {

	static HashMap<Integer, OMOBJ> equalsExprFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> equalsExprExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException, ErroneousExerciseVariableException, OpenMathException {
		equalsExprFillInVariableMap.put(1, ExpressionParser.parse("0", null, null));
		equalsExprFillInVariableMap.put(2, ExpressionParser.parse("x^2", null, null));

		equalsExprExerciseVariableMap.put("a", ExpressionParser.parse("0", null, null));
		equalsExprExerciseVariableMap.put("b", ExpressionParser.parse("1", null, null));
		equalsExprExerciseVariableMap.put("c", ExpressionParser.parse("5", null, null));
		equalsExprExerciseVariableMap.put("d", ExpressionParser.parse("x^2", null, null));
	}

	@Test
	public void testEqualsExpr1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('x^2-5*x+6','(x-2)*(x-3)')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExpr2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('x*x-5*x+6','(x-2)*(x-3)')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExpr3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('1/2*x^2+3*x-5','0.5*x^2+3*x-5')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExpr4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('(a-b)/(b^2-a^2)','(a-b)/(b^2-a^2)')",
				equalsExprExerciseVariableMap, equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExpr5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("equalsExpr('x^2+x+1','a^2+a+1')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExpr6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('(x^2)/2','0.5*x^2')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExpr7() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('1','1')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExprWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('0','[pos=1]')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExprWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('x*x','[pos=2]')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExprWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('[var=b]*x^2+[var=c]*x+[var=a]','x^2+5*x')",
				equalsExprExerciseVariableMap, equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExprWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('[var=d]','x^2')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExprWithEmptyStringArguments1() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("equalsExpr('x^2+x+1','')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExprWithEmptyStringArguments2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("equalsExpr('','x^2+x+1')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExprWithEmptyStringArguments3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr('','')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test (expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsExprWithEmptyArgument() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("equalsExpr()", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test
	public void testEqualsExprWithONECharacter() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsExpr(a, a)", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap));
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testEqualsExprWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsExpr([var=j], [var=j])", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testEqualsExprWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsExpr([pos=42], [pos=42])", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap);
		fail();
	}

	@Test
	public void testEqualsExprWithSemanticallyIncorrectInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsExpr('(4,2)', '4.2')", equalsExprExerciseVariableMap,
				equalsExprFillInVariableMap);
	}
}
