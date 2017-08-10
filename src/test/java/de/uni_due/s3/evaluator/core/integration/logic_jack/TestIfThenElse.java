package de.uni_due.s3.evaluator.core.integration.logic_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIfThenElse extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>test</OMSTR></OMOBJ>"));

			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>test</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testIfthenelse1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'else'", null, null),
				Evaluator.evaluate("ifthenelse(0, 'then', 'else')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelse2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'then'", null, null),
				Evaluator.evaluate("ifthenelse(1, 'then', 'else')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelse3() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'five'", null, null),
				Evaluator.evaluate("ifthenelse(5.7, 'five', 'notFive')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'else'", null, null),
				Evaluator.evaluate("ifthenelse([pos=1], 'then', 'else')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'test'", null, null),
				Evaluator.evaluate("ifthenelse(1, '[pos=7]', 'else')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'test'", null, null), Evaluator
				.evaluate("ifthenelse([pos=2], '[pos=7]', 'notFive')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'else'", null, null),
				Evaluator.evaluate("ifthenelse([var=a], 'then', 'else')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'test'", null, null),
				Evaluator.evaluate("ifthenelse(1, '[var=g]', 'else')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("'test'", null, null), Evaluator
				.evaluate("ifthenelse([var=b], '[var=g]', 'notFive')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("a", null, null),
				Evaluator.evaluate("ifthenelse('5*2 +5 == 5*3', 'a', 'b')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("b", null, null), Evaluator
				.evaluate("ifthenelse(ifthenelse(0, '1', '0'), 'a', 'b')", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testIfthenelseWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("ifthenelse(ab, 'a', 'b')", exerciseVariableMap, fillInVariableMap);
	}

	@Test
	public void testIfthenelseWithTwoRationalAndOneTextArgument() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("0", null, null), Evaluator.evaluate("ifthenelse(1, 0, 'b')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testIfthenelseWithThreeRationalArguments() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("0", null, null), Evaluator.evaluate("ifthenelse(1, 0, 7)", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIfthenelseWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("ifthenelse(1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIfthenelseWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("ifthenelse(0, 'this')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIfthenelseWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("ifthenelse('[var=j]', 'a', 'b')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIfthenelseWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("ifthenelse('[pos=42]', 'a', 'b')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
