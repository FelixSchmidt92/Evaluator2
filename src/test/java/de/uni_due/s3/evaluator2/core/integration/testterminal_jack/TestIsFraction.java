package de.uni_due.s3.evaluator2.core.integration.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsFraction extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws JAXBException {
		OMOBJ six = new OMOBJ();
		six.setOMI(OMCreator.createOMI(6));

		exerVar.put("a", OMConverter
				.toObject("<OMOBJ><OMA><OMS name=\"divide\" cd=\"arith1\" /><OMI>1</OMI><OMI>5</OMI></OMA></OMOBJ>"));
		exerVar.put("b", OMConverter
				.toObject("<OMOBJ><OMA><OMS name=\"divide\" cd=\"arith1\" /><OMI>10</OMI><OMI>2</OMI></OMA></OMOBJ>"));
		exerVar.put("c", OMConverter
				.toObject("<OMOBJ><OMA><OMS name=\"divide\" cd=\"arith1\" /><OMI>7</OMI><OMI>9</OMI></OMA></OMOBJ>"));
		exerVar.put("d", six);

		fillIn.put(1, OMConverter
				.toObject("<OMOBJ><OMA><OMS name=\"divide\" cd=\"arith1\" /><OMI>1</OMI><OMI>5</OMI></OMA></OMOBJ>"));
		fillIn.put(2, OMConverter
				.toObject("<OMOBJ><OMA><OMS name=\"divide\" cd=\"arith1\" /><OMI>10</OMI><OMI>2</OMI></OMA></OMOBJ>"));
		fillIn.put(3, OMConverter
				.toObject("<OMOBJ><OMA><OMS name=\"divide\" cd=\"arith1\" /><OMI>7</OMI><OMI>9</OMI></OMA></OMOBJ>"));
		fillIn.put(4, six);
	}

	@Test
	public void testIsFraction1() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction(1/3)", exerVar, fillIn));
	}

	@Test
	public void testIsFraction2() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction(7/10)", exerVar, fillIn));
	}

	@Test
	public void testIsFraction3() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction(90/65)", exerVar, fillIn));
	}

	@Test
	public void testIsFraction4() throws OpenMathException, EvaluatorException {
		assertEquals(true, !Evaluator.getBooleanResult("isFraction(1)", exerVar, fillIn));
	}

	@Test
	public void testIsFraction5() throws OpenMathException, EvaluatorException {
		assertEquals(true, !Evaluator.getBooleanResult("isFraction(10.76)", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithInput1() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([pos=1])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithInput2() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([pos=2])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithInput3() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([pos=3])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithInput4() throws OpenMathException, EvaluatorException {
		assertEquals(true, !Evaluator.getBooleanResult("isFraction([pos=4])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithVariables1() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([var=a])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithVariables2() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([var=b])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithVariables3() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([var=c])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithVariables4() throws OpenMathException, EvaluatorException {
		assertEquals(true, !Evaluator.getBooleanResult("isFraction([var=d])", exerVar, fillIn));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsFractionWithWrongInputCharacter() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isFraction(a)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsFractionWithTwoArguments() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isFraction(3/2 , 0)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsFractionWithThreeArguments() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isFraction(3/2, 1/3, 3/1)", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIsFractionWithMissingExerciseVariable() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isFraction('[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIsFractionWithMissingInput() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isFraction('[pos=42]')", exerVar, fillIn);
	}

}