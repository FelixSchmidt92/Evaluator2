package de.uni_due.s3.evaluator2.integration.eval_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvalEq extends TestIntegration {

	@Test
	public void testEvalEq1() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ expected = OMConverter.toObject(
			"<OMOBJ><OMA>"
				+ "<OMS name=\"plus\" cd=\"arith1\"/>"
				+ "<OMA>"
					+ "<OMS name=\"power\" cd=\"arith1\"/>"
					+ "<OMV name=\"x\"/>"
					+ "<OMI>2</OMI>"
				+ "</OMA>"
				+ "<OMA>"
					+ "<OMS name=\"times\" cd=\"arith1\"/>"
					+ "<OMI>-1</OMI>"
					+ "<OMV name=\"x\"/>"
				+ "</OMA>"
				
				+ "<OMI>-1</OMI>"
			+ "</OMA></OMOBJ>"); //returns x^2+(-1*x)+-1
		assertEquals(expected,
				Evaluator.evaluate("evalEq('x^2', 'x+1')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEq2() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ expected = OMConverter.toObject(
			"<OMOBJ>"
			+ "<OMA>"
				+ "<OMS name=\"plus\" cd=\"arith1\"/>"
				+ "<OMA>"
					+ "<OMS name=\"power\" cd=\"arith1\"/>"
					+ "<OMV name=\"x\"/>"
					+ "<OMI>2</OMI>"
				+ "</OMA>"
				+ "<OMA>"
					+ "<OMS name=\"times\" cd=\"arith1\"/>"
					+ "<OMI>-1</OMI>"
					+ "<OMV name=\"y\"/>"
				+ "</OMA>"
				+ "<OMI>-1</OMI>"
			+ "</OMA></OMOBJ>"); //returns   x^2  +  -1*y + -1
		assertEquals(expected,
				Evaluator.evaluate("evalEq('x^2', 'y+1')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEq3() throws EvaluatorException, OpenMathException, JAXBException {
		assertEquals(OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"),
				Evaluator.evaluate("evalEq('5', '5')", exerciseVariableMap, fillInVariableMap));
		
	}

	@Test
	public void testEvalEqWithInput1() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ expected = OMConverter.toObject(
			"<OMOBJ><OMA>"
				+ "<OMS name=\"plus\" cd=\"arith1\"/>"
				+ "<OMA>"
					+ "<OMS name=\"times\" cd=\"arith1\"/>"
					+ "<OMI>-1</OMI>"
					+ "<OMA>"
						+ "<OMS name=\"power\" cd=\"arith1\"/>"
						+ "<OMV name=\"x\"/>"
						+ "<OMI>2</OMI>"
					+ "</OMA>"
				+ "</OMA>"
				+ "<OMI>-4</OMI>"
			+ "</OMA></OMOBJ>"); //returns  -1*x^2  + -4  
		assertEquals(expected,
				Evaluator.evaluate("evalEq('[pos=1]','x^2+4')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithInput2() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ expected = OMConverter.toObject(
				"<OMOBJ><OMA>"
						+ "<OMS name=\"plus\" cd=\"arith1\"/>"
						+ "<OMA>"
							+ "<OMS name=\"times\" cd=\"arith1\"/>"
							+ "<OMI>3</OMI>"
							+ "<OMV name=\"x\"/>"
						+ "</OMA>"
						+ "<OMI>-3</OMI>"
				+ "</OMA></OMOBJ>"); //returns 3*x  + -3 
		assertEquals(expected,
				Evaluator.evaluate("evalEq('3*x-2','[pos=2]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithVariables1() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ expected = OMConverter.toObject(
				"<OMOBJ><OMA>"
					+ "<OMS name=\"plus\" cd=\"arith1\"/>"
					+ "<OMA>"
						+ "<OMS name=\"times\" cd=\"arith1\"/>"
						+ "<OMI>-1</OMI>"
						+ "<OMA>"
							+ "<OMS name=\"power\" cd=\"arith1\"/>"
							+ "<OMV name=\"x\"/>"
							+ "<OMI>2</OMI>"
						+ "</OMA>"
					+ "</OMA>"
					+ "<OMI>-4</OMI>"
				+ "</OMA></OMOBJ>"); //returns  -1*x^2  + -4  
		assertEquals(expected,
				Evaluator.evaluate("evalEq('[var=a]','x^2+4')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithVariables2() throws EvaluatorException, OpenMathException, JAXBException {
		OMOBJ expected = OMConverter.toObject(
				"<OMOBJ><OMA>"
						+ "<OMS name=\"plus\" cd=\"arith1\"/>"
						+ "<OMA>"
							+ "<OMS name=\"times\" cd=\"arith1\"/>"
							+ "<OMI>3</OMI>"
							+ "<OMV name=\"x\"/>"
						+ "</OMA>"
						+ "<OMI>-3</OMI>"
				+ "</OMA></OMOBJ>"); //returns 3*x  + -3 
		assertEquals(expected,
				Evaluator.evaluate("evalEq('3*x-2','[var=b]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMI(0)),
				Evaluator.evaluate("evalEq(a, a)", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testEvalEqWithEmptyArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalEq('' , '')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testEvalEqWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalEq([var=j], [var=j])", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testEvalEqWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalEq([pos=42], [pos=42])", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
