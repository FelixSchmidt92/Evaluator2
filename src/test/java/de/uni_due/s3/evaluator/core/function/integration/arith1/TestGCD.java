package de.uni_due.s3.evaluator.core.function.integration.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.function.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGCD extends TestIntegration {
	
	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>20</OMI></OMOBJ>"));
			fillInVariableMap.put(3, OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
			fillInVariableMap.put(4, OMConverter.toObject("<OMOBJ><OMI>-5</OMI></OMOBJ>"));
			fillInVariableMap.put(5, OMConverter.toObject("<OMOBJ><OMI>5</OMI></OMOBJ>"));
			
			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>20</OMI></OMOBJ>"));
			exerciseVariableMap.put("c", OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
			exerciseVariableMap.put("d", OMConverter.toObject("<OMOBJ><OMI>-5</OMI></OMOBJ>"));
			exerciseVariableMap.put("e", OMConverter.toObject("<OMOBJ><OMI>5</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen", e);
		}
	}
	
	@Test 
	public void testGCD1() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd('5', '5')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testGCD2() throws EvaluatorException, OpenMathException{
		assertEquals(3, Evaluator.getNumberResult("gcd('3', '-9')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testGCD3() throws EvaluatorException, OpenMathException{
		assertEquals(3, Evaluator.getNumberResult("gcd('-3', '9')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testGCD4() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd('-5', '-5')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testGCD5() throws EvaluatorException, OpenMathException{
		assertNotEquals(5, Evaluator.getNumberResult("gcd('5', '9')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testGCD6() throws EvaluatorException, OpenMathException{
		assertNotEquals(3, Evaluator.getNumberResult("gcd('-3', '7')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDAtDefinition1() throws EvaluatorException, OpenMathException{
		assertEquals(55, Evaluator.getNumberResult("gcd('0', '55')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDAtDefinition2() throws EvaluatorException, OpenMathException{
		assertEquals(11,Evaluator.getNumberResult("gcd('-11', '0')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDAtDefinition3() throws EvaluatorException, OpenMathException{
		assertEquals(0,Evaluator.getNumberResult("gcd('0', '0')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
		
	@Test
	public void testGCDWithInput1() throws EvaluatorException, OpenMathException{
		assertEquals(10, Evaluator.getNumberResult("gcd('[pos=2]', '[pos=3]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDWithInput2() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd('[pos=3]', '[pos=4]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDWithInput3() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd('[pos=1]', '[pos=4]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDWithVariables1() throws EvaluatorException, OpenMathException{
		assertEquals(10, Evaluator.getNumberResult("gcd('[var=b]', '[var=c]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDWithVariables2() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd('[var=c]', '[var=d]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDWithVariables3() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd('[var=a]', '[var=d]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDWithExpressions1() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd('gcd(5, [var=c])', '[var=c]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDWithExpressions2() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd('[var=c]', '(gcd([var=d], 5))')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testGCDWithExpressions3() throws EvaluatorException, OpenMathException{
		assertEquals(5, Evaluator.getNumberResult("gcd(gcd('[var=e]',gcd('[var=e]','5')),gcd('[var=b]','[pos=5]'))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testGCDWithWrongInputPointNumber() throws EvaluatorException, OpenMathException{
		Evaluator.getNumberResult("gcd('5.5','4')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testGCDWithWrongInputCharacter() throws EvaluatorException, OpenMathException{
		Evaluator.getNumberResult("gcd('a', '2')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testGCDWithOneArgument() throws EvaluatorException, OpenMathException{
		Evaluator.getNumberResult("gcd('7')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testGCDWithThreeArguments() throws EvaluatorException, OpenMathException{
		Evaluator.getNumberResult("gcd('4', '5', '7')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testGCDWithMissingExerciseVariable() throws EvaluatorException, OpenMathException{
		Evaluator.getNumberResult("gcd('[var=j]', '3')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testGCDWithMissingInput() throws EvaluatorException, OpenMathException{
		Evaluator.getNumberResult("gcd('[pos=42]', '3')", exerciseVariableMap, fillInVariableMap);
	}
}