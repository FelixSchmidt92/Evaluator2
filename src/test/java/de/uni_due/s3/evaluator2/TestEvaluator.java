package de.uni_due.s3.evaluator2;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvaluator {

	private HashMap<String, OMOBJ> exerciseVariableMap;
	private HashMap<Integer, OMOBJ> fillInVariableMap;


	@Test
	public void testEvaluateString() throws FunctionException, JAXBException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		Assert.assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>4</OMI></OMOBJ>",
				OMConverter.toString(Evaluator.evaluate("1+3", exerciseVariableMap, fillInVariableMap)));
		Assert.assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>4</OMI></OMOBJ>",
				OMConverter.toString(Evaluator.evaluate("plus(1,3)", exerciseVariableMap, fillInVariableMap)));
	}

	@Test(expected = FunctionNotImplementedException.class)
	public void testEvaluateWithException() throws FunctionException, OpenMathException, JAXBException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		Evaluator.evaluate("abcdefghxjc()", exerciseVariableMap, fillInVariableMap);
		Evaluator.evaluate("123cdefghxjc()", exerciseVariableMap, fillInVariableMap);
		Evaluator.evaluate("abcdefghxjc", exerciseVariableMap, fillInVariableMap); 
		Evaluator.evaluate((OMOBJ) OMConverter.toObject("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"abcdefggg\" cd=\"arith1\"/>"
				+ "<OMI>2</OMI>" + "<OMI>3</OMI>" + "</OMA></OMOBJ>"));
	}

	@Test
	public void testEvaluateOMOBJ() throws FunctionException, JAXBException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		Assert.assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>15</OMI></OMOBJ>",
				OMConverter.toString(Evaluator
						.evaluate((OMOBJ) OMConverter.toObject("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"plus\" cd=\"arith1\"/>"
								+ "<OMI>2</OMI>" + "<OMI>13</OMI>" + "</OMA></OMOBJ>"))));
	}

}
