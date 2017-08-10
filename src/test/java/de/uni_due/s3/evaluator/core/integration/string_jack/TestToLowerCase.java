package de.uni_due.s3.evaluator.core.integration.string_jack;

import static org.junit.Assert.assertEquals;
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

public class TestToLowerCase extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>HELLO</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMSTR>HELLO3</OMSTR></OMOBJ>"));

			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>HELLO</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMSTR>HELLO3</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test public void testToLowerCase1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz")), Evaluator.evaluate("toLowerCase('ABCDEFGHIJKLMNOPQRSTUVWXYZ')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test public void testToLowerCase2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("1234567890.!\"§$%&/()=?")), Evaluator.evaluate("toLowerCase('1234567890.!\"§$%&/()=?')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test public void testToLowerCase3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz")), Evaluator.evaluate("toLowerCase('abcdefghijklmnopqrstuvwxyz')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test public void testToLowerCase4() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("")), Evaluator.evaluate("toLowerCase('')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test public void testToLowerCase5() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR(" ")), Evaluator.evaluate("toLowerCase(' ')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testToLowerCaseWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello")), Evaluator.evaluate("toLowerCase('[pos=7]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testToLowerCaseWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello3")), Evaluator.evaluate("toLowerCase('[pos=8]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void TestToLowerCaseWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello")), Evaluator.evaluate("toLowerCase('[var=g]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void TestToLowerCaseWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello3")), Evaluator.evaluate("toLowerCase('[var=h]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testToLowerCaseWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a")), Evaluator.evaluate("toLowerCase(toLowerCase('A'))", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testToLowerCaseWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a")), Evaluator.evaluate("toLowerCase(toLowerCase(toLowerCase('A')))", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test(expected=ParserException.class)
	public void testToLowerCaseWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toLowerCase(ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testToLowerCaseWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toLowerCase('A', 'B')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testToLowerCaseWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toLowerCase('A', 'B', 'C')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testToLowerCaseWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toLowerCase('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testToLowerCaseWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toLowerCase('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}