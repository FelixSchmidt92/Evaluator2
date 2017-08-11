package de.uni_due.s3.evaluator2.core.integration.binary_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqualsBinary extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(3, OMConverter.toObject("<OMOBJ><OMI>7</OMI></OMOBJ>"));

			exerciseVariableMap.put("c", OMConverter.toObject("<OMOBJ><OMI>7</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testEqualsBinary1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsBinary('15','1111')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEqualsBinary2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsBinary('3','00011')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEqualsBinary3() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equalsBinary('16','101')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEqualsBinaryWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsBinary('2','[pos=2][pos=1]')", exerciseVariableMap,
				fillInVariableMap));
	}

	@Test
	public void testEqualsBinaryWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("equalsBinary('[pos=3]','0111')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEqualsBinaryWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsBinary('2','[var=b][var=a]')", exerciseVariableMap,
				fillInVariableMap));
	}

	@Test
	public void testEqualsBinaryWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("equalsBinary('[var=c]','0111')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEqualsBinaryWithExpressions() throws EvaluatorException, OpenMathException {
		assertTrue((Evaluator.getBooleanResult("equalsBinary(equalsBinary('3','11'), equalsBinary('2','10'))",
				exerciseVariableMap, fillInVariableMap)));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualsBinaryWithWrongInputDecimalAsSecondArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsBinary('2','15')", exerciseVariableMap, fillInVariableMap);
	}

	@Test//(expected = FunctionInvalidArgumentTypeException.class) //Now PointNumbers are supported
	public void testEqualsBinaryWithWrongInputPointNumberAsFirstArgument()
			throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsBinary('2.6','11')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testEqualsBinaryWithWrongInputEmptyArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsBinary('','')", exerciseVariableMap, fillInVariableMap);
	}

	@Test//(expected = FunctionInvalidArgumentTypeException.class) Now works without String Input
	public void testEqualsBinaryWithTwoRationalArguments() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsBinary(7, 111)", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsBinaryWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsBinary('11')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsBinaryWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsBinary('7','111', '0111')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testEqualsBinaryWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsBinary('[pos=10]','11')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testEqualsBinaryWithMissingVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsBinary('[var=u]','11')", exerciseVariableMap, fillInVariableMap);
	}
}