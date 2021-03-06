package de.uni_due.s3.evaluator2.integration.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

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
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDerive extends TestIntegration {

	static HashMap<Integer, OMOBJ> deriveFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> deriveExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		deriveFillInVariableMap.put(1, ExpressionParser.parse("x", null, null));
		deriveFillInVariableMap.put(2, ExpressionParser.parse("0.5", null, null));

		deriveExerciseVariableMap.put("a", ExpressionParser.parse("x", null, null));
		deriveExerciseVariableMap.put("b", ExpressionParser.parse("0.5", null, null));
	}

	@Test
	public void testDerive1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*x", null, null),
				Evaluator.evaluate("derive('x^2','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDerive2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("0", null, null),
				Evaluator.evaluate("derive('x^2','y')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDerive3() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*x+2", null, null),
				Evaluator.evaluate("derive('x^2+2*x','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDerive4() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("1*x", null, null),
				Evaluator.evaluate("derive('0.5*x^2','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDerive5() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("1*x", null, null),
				Evaluator.evaluate("derive('.5*x^2','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDerive6() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*x+1", null, null),
				Evaluator.evaluate("derive('x^2+x+3','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDerive7() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ expected = OMConverter
				.toObject("<OMOBJ>"
						+ "<OMA>"
							+ "<OMS name=\"times\" cd=\"arith1\"/>"
							+ "<OMI>2</OMI>"
							
							+ "<OMA>"
								+ "<OMS name=\"power\" cd=\"arith1\"/>"
								+ "<OMV name=\"y\"/>"
								+ "<OMI>2</OMI>"
							+ "</OMA>"
							
							+ "<OMV name=\"x\"/>"
						+ "</OMA>"
						+ "</OMOBJ>");
		
		assertEquals(expected.getOMA(), Evaluator
				.evaluate("derive('(x*y)^2','x')", deriveExerciseVariableMap, deriveFillInVariableMap).getOMA());
	}

	@Test
	public void testDeriveWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("1*x", null, null),
				Evaluator.evaluate("derive('0.5*x^2 + 3','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDeriveWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*x+2", null, null), Evaluator.evaluate(
				"derive('[pos=1]^2 + 2*[pos=1] + 4','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDeriveWithExpressions() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("1*x", null, null), Evaluator.evaluate("derive(derive('(1/6)*x^3', x),x)",
				deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDeriveWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("1*x", null, null),
				Evaluator.evaluate("derive('0.5*x^2 + 3','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDeriveWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*x+2", null, null), Evaluator.evaluate(
				"derive('[var=a]^2 + 2*[var=a] + 4','x')", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test
	public void testDeriveWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("1", null, null),
				Evaluator.evaluate("derive(a, a)", deriveExerciseVariableMap, deriveFillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDeriveWithEmptyStringArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("derive('', '')", deriveExerciseVariableMap, deriveFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDeriveWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("derive()", deriveExerciseVariableMap, deriveFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testDeriveWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("derive([var=j], [var=j])", deriveExerciseVariableMap, deriveFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testDeriveWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("derive([pos=42], [pos=42])", deriveExerciseVariableMap, deriveFillInVariableMap);
		fail();
	}
}
