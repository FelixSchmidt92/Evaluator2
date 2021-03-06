package de.uni_due.s3.evaluator2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvaluator extends TestFunctionAbstract{

	private HashMap<String, OMOBJ> exerciseVariableMap;
	private HashMap<Integer, OMOBJ> fillInVariableMap;


	@Test
	public void testEvaluateString() throws JAXBException, OpenMathException, EvaluatorException {
		Assert.assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>4</OMI></OMOBJ>",
				OMConverter.toString(Evaluator.evaluate("1+3", exerciseVariableMap, fillInVariableMap)));
		Assert.assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>4</OMI></OMOBJ>",
				OMConverter.toString(Evaluator.evaluate("plus(1,3)", exerciseVariableMap, fillInVariableMap)));
	}

	@Test(expected = FunctionNotImplementedException.class)
	public void testEvaluateWithException() throws OpenMathException, JAXBException,
			EvaluatorException {
		Evaluator.evaluate("abcdefghxjc()", exerciseVariableMap, fillInVariableMap);
		Evaluator.evaluate("123cdefghxjc()", exerciseVariableMap, fillInVariableMap);
		Evaluator.evaluate("abcdefghxjc", exerciseVariableMap, fillInVariableMap); 
		Evaluator.evaluate((OMOBJ) OMConverter.toObject("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"abcdefggg\" cd=\"arith1\"/>"
				+ "<OMI>2</OMI>" + "<OMI>3</OMI>" + "</OMA></OMOBJ>"));
	}

	@Test
	public void testEvaluateOMOBJ() throws JAXBException, OpenMathException, EvaluatorException {
		Assert.assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>15</OMI></OMOBJ>",
				OMConverter.toString(Evaluator
						.evaluate((OMOBJ) OMConverter.toObject("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"plus\" cd=\"arith1\"/>"
								+ "<OMI>2</OMI>" + "<OMI>13</OMI>" + "</OMA></OMOBJ>"))));
	}
	
	@Test
	public void testEvaluatorGetList() throws JAXBException, OpenMathException, EvaluatorException {
		List<OMOBJ> expected = new ArrayList<>();
		expected.add(OMCreator.createOMOBJ(OMCreator.createOMI(1)));
		expected.add(OMCreator.createOMOBJ(OMCreator.createOMSTR("Hello World!")));
		expected.add(OMCreator.createOMOBJ(OMCreator.createOMI(5)));
		expected.add(OMCreator.createOMOBJ(OMCreator.createOMF(1.2345)));
		expected.add(OMCreator.createOMOBJ(OMCreator.createOMV("a")));
		
		List<OMOBJ> result = Evaluator.getList(Evaluator.evaluate("list(1,'Hello World!', 5, 1.2345, a)", null, null));
		assertEquals(expected, result);
	}
	
	@Test
	public void testEvaluatorRConnection() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("evaluateInR('11')", exerciseVariableMap, fillInVariableMap);
		Assert.assertEquals(OMCreator.createOMI(11),result.getOMI());
	}
	
	@Test
	public void testEvaluatorSageConnection() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("evaluateInSage('10')", exerciseVariableMap, fillInVariableMap);
		Assert.assertEquals(OMCreator.createOMI(10),result.getOMI());
	}
	
}
