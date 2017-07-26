package de.uni_due.s3.evaluator.core.integration.binary_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestConvertToBinary extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>7</OMI></OMOBJ>"));

			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>7</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testConvertToBinary1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMSTR("10"),
				Evaluator.evaluate("convertToBinary(2)", exerciseVariableMap, fillInVariableMap).getOMSTR());
	}

	@Test
	public void testConvertToBinary2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMSTR("1101101111"),
				Evaluator.evaluate("convertToBinary(879)", exerciseVariableMap, fillInVariableMap).getOMSTR());
	}

	@Test
	public void testConvertToBinary3() throws EvaluatorException, OpenMathException {
		assertNotEquals(OMCreator.createOMSTR("-11"),
				Evaluator.evaluate("convertToBinary(-3)", exerciseVariableMap, fillInVariableMap).getOMSTR());
	}

	@Test
	public void testConvertToBinaryWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMSTR("111"),
				Evaluator.evaluate("convertToBinary([pos=2])", exerciseVariableMap, fillInVariableMap).getOMSTR());
	}

	@Test
	public void testConvertToBinaryWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMSTR("0"),
				Evaluator.evaluate("convertToBinary([pos=1])", exerciseVariableMap, fillInVariableMap).getOMSTR());
	}

	@Test
	public void testConvertToBinaryWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMSTR("0"),
				Evaluator.evaluate("convertToBinary([var=a])", exerciseVariableMap, fillInVariableMap).getOMSTR());
	}

	@Test
	public void testConvertToBinaryWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMSTR("111"),
				Evaluator.evaluate("convertToBinary([var=b])", exerciseVariableMap, fillInVariableMap).getOMSTR());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testConvertToBinaryWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("convertToBinary(a)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testConvertToBinaryWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("convertToBinary(3, 2)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testConvertToBinaryWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("convertToBinary(1, 2, 3)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testConvertToBinaryWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("convertToBinary([var=j])", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testConvertToBinaryWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("convertToBinary([pos=42])", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}