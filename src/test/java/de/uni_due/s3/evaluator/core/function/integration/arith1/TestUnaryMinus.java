package de.uni_due.s3.evaluator.core.function.integration.arith1;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.function.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestUnaryMinus extends TestIntegration {
	
	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>1</OMI></OMOBJ>"));
			
			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>1</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen", e);
		}
	}
	
	@Test
	public void testUnaryMinus1() throws EvaluatorException, OpenMathException {
		assertEquals(-8, Evaluator.getNumberResult("-8", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testUnaryMinus2() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("-2", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testUnaryMinus3() throws EvaluatorException, OpenMathException {
		assertEquals(-2.2, Evaluator.getNumberResult("-2.2", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testUnaryMinusWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(-3, Evaluator.getNumberResult("-[pos=1]", exerciseVariableMap, fillInVariableMap),0.0001);
		
	}
	
	@Test
	public void testUnaryMinusWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("-[pos=2]", exerciseVariableMap, fillInVariableMap),0.0001);	
	}
	
	@Test
	public void testUnaryMinusWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(-3, Evaluator.getNumberResult("-[var=a]", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testUnaryMinusWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("-[var=b]", exerciseVariableMap, fillInVariableMap),0.0001);	
	}
	
	@Test
	public void testUnaryMinusWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("-(-5)", exerciseVariableMap, fillInVariableMap),0.0001);	
	}
	
	@Test
	public void testUnaryMinusWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(-4, Evaluator.getNumberResult("-(-(-4))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testUnaryMinusWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		assertEquals(-5, Evaluator.getNumberResult("-'a'", exerciseVariableMap, fillInVariableMap),0.0001);
	}
}