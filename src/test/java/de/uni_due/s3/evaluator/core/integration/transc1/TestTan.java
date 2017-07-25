package de.uni_due.s3.evaluator.core.integration.transc1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestTan extends TestIntegration {
	
	@Before
	public void beforeTest() throws OpenMathException {
		fillInVariableMap.put(2, OMCreator.createOMOBJ(OMSymbol.NUMS1_PI));
	}
	
	@Test
	public void testTan1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('0')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testTan2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('Pi')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testTan3() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('-Pi')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testTan4() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("tan('Pi/4')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testTan5() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("tan('-Pi/4')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testTanWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('[pos=1]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testTanWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(1,Evaluator.getNumberResult("tan('[pos=2]/4')", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testTanWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('[var=a]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testTanWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("tan('[var=PI]/4')", exerciseVariableMap, fillInVariableMap),0.0001); //Rounding Error
	}

	@Test
	public void testTanWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan(tan(0))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testTanWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan(tan(tan(0)))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test(expected=ParserException.class)
	public void testTanWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan(a)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testTanWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan('')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testTanWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan()", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testTanWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan('[var=j]')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testTanWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan('[pos=42]')", exerciseVariableMap, fillInVariableMap);
	}
}