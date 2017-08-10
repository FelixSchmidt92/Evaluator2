package de.uni_due.s3.evaluator.core.integration.transc1;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestExp extends TestIntegration {

	@BeforeClass
	public static void beforeTest() throws OpenMathException {
		try {
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));

			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testExp1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("exp(0)", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testExp2() throws EvaluatorException, OpenMathException {
		assertEquals(2.71828182845, Evaluator.getNumberResult("exp(1)", exerciseVariableMap, fillInVariableMap),
				0.0001);
	}

	@Test
	public void testExp3() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("exp(-1000)", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testExp4() throws EvaluatorException, OpenMathException {
		assertEquals(0.9048374180359595, Evaluator.getNumberResult("exp(-0.1)", exerciseVariableMap, fillInVariableMap),
				0.0001);
	}

	@Test
	public void testExpWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("exp([pos=1])", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testExpWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(22026.465794806718,
				Evaluator.getNumberResult("exp([pos=2])", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testExpWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("exp([var=a])", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testExpWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(22026.465794806718,
				Evaluator.getNumberResult("exp([var=b])", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testExpWithExpressions() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("exp(exp(-1000))", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testExpWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("exp(a)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testExpWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("exp(0, 1)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testExpWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("exp(1, 2.71,  2)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testExpWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("exp([var=j])", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testExpWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getDenominator([pos=42])", exerciseVariableMap, fillInVariableMap);
	}
}