package de.uni_due.s3.evaluator2.core.integration.linalg_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsLinearlyIndependent extends TestIntegration {

	static HashMap<Integer, OMOBJ> isLinearlyIndependentFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> isLinearlyIndependentExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		try {
			// set(vector(1,2,3),vector(3,3,3))
			isLinearlyIndependentFillInVariableMap.put(1, OMConverter.toObject(
					"<OMOBJ><OMA><OMS cd='list1' name='list'/><OMA><OMS cd='linalg2' name='vector'/><OMI>1</OMI><OMI>2</OMI><OMI>3</OMI></OMA><OMA><OMS cd='linalg2' name='vector'/><OMI>3</OMI><OMI>3</OMI><OMI>3</OMI></OMA></OMA></OMOBJ>"));
			isLinearlyIndependentFillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>1</OMI></OMOBJ>"));

			// set(vector(1,2,3),vector(3,3,3))
			isLinearlyIndependentExerciseVariableMap.put("a", OMConverter.toObject(
					"<OMOBJ><OMA><OMS cd='list1' name='list'/><OMA><OMS cd='linalg2' name='vector'/><OMI>1</OMI><OMI>2</OMI><OMI>3</OMI></OMA><OMA><OMS cd='linalg2' name='vector'/><OMI>3</OMI><OMI>3</OMI><OMI>3</OMI></OMA></OMA></OMOBJ>"));
			isLinearlyIndependentExerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>1</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testIsLinearlyIndependent1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("isLinearlyIndependent(set(vector(1,1), vector(1,2)))",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test
	public void testIsLinearlyIndependent2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("isLinearlyIndependent(set(vector(1)))",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test
	public void testIsLinearlyIndependent3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("isLinearlyIndependent(set(vector(1,1,0), vector(1,1,1)))",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test
	public void testIsLinearlyIndependent4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult(
				"isLinearlyIndependent(set(vector(1,0,0), vector(1,1,1), vector(1.5, 1.5, 1.8)))",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test
	public void testIsLinearlyIndependent5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("isLinearlyIndependent(set(vector(1,2,3), vector(3,3,3)))",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test
	public void testIsLinearlyIndependentWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("isLinearlyIndependent(set(vector(1,[pos=2],0), vector(1,1,[pos=2])))",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test
	public void testIsLinearlyIndependentWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("isLinearlyIndependent('[pos=1]')",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test
	public void testIsLinearlyIndependentWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("isLinearlyIndependent(set(vector(1,[var=b],0), vector(1,1,[var=b])))",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test
	public void testIsLinearlyIndependentWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("isLinearlyIndependent('[var=a]')",
				isLinearlyIndependentExerciseVariableMap, isLinearlyIndependentFillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void TestIsLinearlyIndependentWithWrongInputONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isLinearlyIndependent(ba)", isLinearlyIndependentExerciseVariableMap,
				isLinearlyIndependentFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testIsLinearlyIndependentWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isLinearlyIndependent('')", isLinearlyIndependentExerciseVariableMap,
				isLinearlyIndependentFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsLinearlyIndependentWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isLinearlyIndependent()", isLinearlyIndependentExerciseVariableMap,
				isLinearlyIndependentFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIsLinearlyIndependentWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isLinearlyIndependent('[var=j]')", isLinearlyIndependentExerciseVariableMap,
				isLinearlyIndependentFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIsLinearlyIndependentWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isLinearlyIndependent('[pos=42]')", isLinearlyIndependentExerciseVariableMap,
				isLinearlyIndependentFillInVariableMap);
		fail();
	}
}
