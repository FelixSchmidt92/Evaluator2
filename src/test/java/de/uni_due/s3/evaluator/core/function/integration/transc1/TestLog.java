package de.uni_due.s3.evaluator.core.function.integration.transc1;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.function.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestLog extends TestIntegration {
	
	@Before
	public void beforeTest() throws OpenMathException {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>5</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMF>13.7465</OMF></OMOBJ>"));
			
			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>5</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMF>13.7465</OMF></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen", e);
		}
	}
	
	@Test
	public void testLog1() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(100), Evaluator.getNumberResult("log(100)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLog2() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(197), Evaluator.getNumberResult("log(197)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLog3() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(100), Evaluator.getNumberResult("log('100')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLog4() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(197), Evaluator.getNumberResult("log('197')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(5), Evaluator.getNumberResult("log([pos=1])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(13.7465), Evaluator.getNumberResult("log([pos=2])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithVariables1() throws EvaluatorException, OpenMathException {	
		assertEquals(Math.log(5), Evaluator.getNumberResult("log([var=a])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithVariables2() throws EvaluatorException, OpenMathException {	
		assertEquals(Math.log(13.7465), Evaluator.getNumberResult("log([var=b])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(Math.log(12)), Evaluator.getNumberResult("log(log(12))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(Math.log(Math.log(12))), Evaluator.getNumberResult("log(log(log(12)))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithPointNumbers1() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(1.0), Evaluator.getNumberResult("log(1.0)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithPointNumbers2() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(.1), Evaluator.getNumberResult("log(0.1)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testLogWithPointNumbers3() throws EvaluatorException, OpenMathException {
		assertEquals(Math.log(.1234), Evaluator.getNumberResult("log(0.1234)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test (expected=FunctionInvalidArgumentException.class) //NaN
	public void testLogAtDefinition1() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("log(-1)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test (expected=FunctionInvalidArgumentException.class) //NaN
	public void testLogAtDefinition2() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("log(-0.1)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test (expected=FunctionInvalidArgumentException.class) //NaN
	public void testLogAtDefinition3() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("log(0)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=ParserException.class)
	public void testLogWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("log(a)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testLogWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("log(2, 1)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testLogWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("log(1, 23, 4)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testLogWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("log('[var=j]')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testLogWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("log('[pos=42]')", exerciseVariableMap, fillInVariableMap);
	}
}