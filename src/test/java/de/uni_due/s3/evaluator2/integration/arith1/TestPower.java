package de.uni_due.s3.evaluator2.integration.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestPower extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>2</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));

			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>2</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testPow1() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("pow(2,2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPow2() throws EvaluatorException, OpenMathException {
		assertEquals(1000, Evaluator.getNumberResult("pow(10,3)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPow3() throws EvaluatorException, OpenMathException {
		assertEquals(-8, Evaluator.getNumberResult("pow(-2,3)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPow4() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("pow(10,0)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPow5() throws EvaluatorException, OpenMathException {
		assertEquals(.1, Evaluator.getNumberResult("pow(10,-1)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPow6() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("pow(1,-55)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPow7() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("pow(0, 44)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPowWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1024, Evaluator.getNumberResult("pow(2,[pos=2])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPowWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(8, Evaluator.getNumberResult("pow([pos=1],3)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPowWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("pow(2,[var=a])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPowWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1000, Evaluator.getNumberResult("pow([var=b],3)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPowWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(16, Evaluator.getNumberResult("pow(pow(-2,2), 2)", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testPowWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(4096,
				Evaluator.getNumberResult("pow(pow(-2,3), pow(2, 2))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testPowWithExpressions3() throws EvaluatorException, OpenMathException {
		assertEquals(16,
				Evaluator.getNumberResult("pow(pow(-2,pow(2,1)), pow(2, 1))", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testPowWithPointNumbers() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("pow(1.0, 1.3)", exerciseVariableMap, fillInVariableMap), 0.0);
	}


	@Test
	public void testPowWithInputCharacters() throws EvaluatorException, OpenMathException {
		OMA oma = Evaluator.evaluate("pow(a, a)", exerciseVariableMap, fillInVariableMap).getOMA();
		assertEquals("<OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"a\"/><OMV name=\"a\"/></OMA>", oma.toString());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testPowWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("pow(4)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testPowWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("pow(2, 2, 2)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testPowWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("pow('[var=j]', '[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testPowWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("pow('[pos=42]', '[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}