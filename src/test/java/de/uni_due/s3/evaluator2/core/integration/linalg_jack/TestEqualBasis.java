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
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqualBasis extends TestIntegration {

	static HashMap<Integer, OMOBJ> equalBasisFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> equalBasisExerciseVariableMap = new HashMap<>();
	
	@BeforeClass
	public static void beforeTest() {
		try {
			//set(vector(1,2,3),vector(3,3,3))
			equalBasisFillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMA><OMS cd='list1' name='list'/><OMA><OMS cd='linalg2' name='vector'/><OMI>1</OMI><OMI>2</OMI><OMI>3</OMI></OMA><OMA><OMS cd='linalg2' name='vector'/><OMI>3</OMI><OMI>3</OMI><OMI>3</OMI></OMA></OMA></OMOBJ>"));
			//set(vector(2,5,6),vector(3,3,3))
			equalBasisFillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMA><OMS cd='list1' name='list'/><OMA><OMS cd='linalg2' name='vector'/><OMI>2</OMI><OMI>5</OMI><OMI>6</OMI></OMA><OMA><OMS cd='linalg2' name='vector'/><OMI>3</OMI><OMI>3</OMI><OMI>3</OMI></OMA></OMA></OMOBJ>"));

			//set(vector(1,2,3),vector(3,3,3))
			equalBasisExerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMA><OMS cd='list1' name='list'/><OMA><OMS cd='linalg2' name='vector'/><OMI>1</OMI><OMI>2</OMI><OMI>3</OMI></OMA><OMA><OMS cd='linalg2' name='vector'/><OMI>3</OMI><OMI>3</OMI><OMI>3</OMI></OMA></OMA></OMOBJ>"));
			//set(vector(2,5,6),vector(3,3,3))
			equalBasisExerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMA><OMS cd='list1' name='list'/><OMA><OMS cd='linalg2' name='vector'/><OMI>2</OMI><OMI>5</OMI><OMI>6</OMI></OMA><OMA><OMS cd='linalg2' name='vector'/><OMI>3</OMI><OMI>3</OMI><OMI>3</OMI></OMA></OMA></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}
	
	@Test
	public void testEqualBasis1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalBasis('{vector(1,1,0);vector(0,1,1)}', 'set(vector(1,1,0),vector(0,1,1))', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasis2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("equalBasis('set(vector(1,1,1),vector(0,1,1))', 'set(vector(1,1,0),vector(0,1,1))', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasis3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalBasis('{vector(1,1);vector(0,1)}', '{vector(1,1);vector(0,1)}', '2')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasis4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalBasis('set(vector(1,1),vector(0,1))', '{vector(1,1);vector(0,1)}', '2')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasis5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalBasis('set(vector(1,1,0),vector(0,1,1))', '{vector(1,1,0);vector(0,1,1)}', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasis6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalBasis('set(vector(1,1,0),vector(0,1,1))', 'set(vector(3,3,0),vector(0,1,1))', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasisWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalBasis('[pos=1]', '[pos=1]', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasisWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("equalBasis('[pos=1]', '[pos=2]', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasisWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalBasis('[var=a]', '[var=a]', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test
	public void testEqualBasisWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("equalBasis('[var=a]', '[var=b]', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap));
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testEqualBasisWithWrongInputCharacterAsThirdParameter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalBasis('(vector(QQ,(1,1,0)),vector(QQ,(0,1,1)))', '(vector(QQ,(3,3,0)),vector(QQ,(0,1,1)))', 'c')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testEqualBasisWithWrongInputToManyCommas() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalBasis('(vector(QQ,,(1,1,,0)),vector(QQ,(0,1,1)))', '(vector(QQ,(3,3,0)),vector(QQ,(0,1,1)))', '3')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap);
		fail();
	}
	
	@Test(expected=ParserException.class)
	public void TestEqualBasisWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalBasis(ab, ab, ab)", equalBasisExerciseVariableMap, equalBasisFillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testEqualBasisWithEmptyStringArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalBasis('', '', '')", equalBasisExerciseVariableMap, equalBasisFillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualBasisWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalBasis()", equalBasisExerciseVariableMap, equalBasisFillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testEqualBasisWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalBasis([var=j], [var=j], [var=j])", equalBasisExerciseVariableMap, equalBasisFillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testEqualBasisWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalBasis([pos=42], [pos=42], [pos=42])", equalBasisExerciseVariableMap, equalBasisFillInVariableMap);
		fail();
	}
}
