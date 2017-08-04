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
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestLastIndexOf extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>oneString</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMSTR>b</OMSTR></OMOBJ>"));
			
			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>oneString</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMSTR>b</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testLastIndexOf1() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("lastIndexOf('abbbb','b',5)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testLastIndexOf2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("lastIndexOf('cabade','c',10)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testLastIndexOf3() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("lastIndexOf('cabade','test',10)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testLastIndexOf4() throws EvaluatorException, OpenMathException {
		assertEquals(6, Evaluator.getNumberResult("lastIndexOf('he   llo','lo',7)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testLastIndexOf5() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("lastIndexOf('','',0)", exerciseVariableMap, fillInVariableMap),0.0);
	}

	@Test
	public void testLastIndexOfWithInput1() throws EvaluatorException, OpenMathException {	
		assertEquals(3, Evaluator.getNumberResult("lastIndexOf('[pos=7]','S',7)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testLastIndexOfWithInput2() throws EvaluatorException, OpenMathException {	
		assertEquals(4, Evaluator.getNumberResult("lastIndexOf('abbbb','[pos=8]',5)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testLastIndexOfWithVariable1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("lastIndexOf('[var=g]','S',7)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testLastIndexOfWithVariable2() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("lastIndexOf('abbbb','[var=h]',5)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test(expected=ParserException.class)
	public void testLastIndexOfWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("lastIndexOf(ab, ab, ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testLastIndexOfWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("lastIndexOf('abab')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test
	public void testLastIndexOfWithTwoArguments() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("lastIndexOf('abab', 'ab')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testLastIndexOfWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("lastIndexOf('[var=j]', '[var=j]', '[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testLastIndexOfWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("lastIndexOf('[pos=42]', '[pos=42]', '[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}