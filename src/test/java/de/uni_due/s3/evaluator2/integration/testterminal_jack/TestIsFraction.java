package de.uni_due.s3.evaluator2.integration.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
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
	public void testIsFraction1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction(1/3)", exerVar, fillIn));
	}

	@Test
	public void testIsFraction2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction(7/10)", exerVar, fillIn));
	}

	@Test
	public void testIsFraction3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction(90/65)", exerVar, fillIn));
	}

	@Test
	public void testIsFraction4() throws EvaluatorException, OpenMathException {
		assertEquals(true, !Evaluator.getBooleanResult("isFraction(1)", exerVar, fillIn));
	}

	@Test
	public void testIsFraction5() throws EvaluatorException, OpenMathException {
		assertEquals(true, !Evaluator.getBooleanResult("isFraction(10.76)", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([pos=1])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([pos=2])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([pos=3])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithInput4() throws EvaluatorException, OpenMathException {
		assertEquals(true, !Evaluator.getBooleanResult("isFraction([pos=4])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([var=a])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([var=b])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isFraction([var=c])", exerVar, fillIn));
	}

	@Test
	public void testIsFractionWithVariables4() throws EvaluatorException, OpenMathException {
		assertEquals(true, !Evaluator.getBooleanResult("isFraction([var=d])", exerVar, fillIn));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsFractionWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isFraction(a)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsFractionWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isFraction(3/2 , 0)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsFractionWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isFraction(3/2, 1/3, 3/1)", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIsFractionWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isFraction('[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIsFractionWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isFraction('[pos=42]')", exerVar, fillIn);
	}

}
