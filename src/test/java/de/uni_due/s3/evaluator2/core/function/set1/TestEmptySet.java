package de.uni_due.s3.evaluator2.core.function.set1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEmptySet extends TestFunctionAbstract {

	@Test
	public void testInIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("emptySet()", null, null);
		assertEquals(OMSymbol.SET1_EMPTYSET, result.getOMS());
	}
	
	@Test
	public void testInIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("in(vector(1,3,2),emptySet())", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
	
	@Test
	public void testInIntegration3() throws EvaluatorException, OpenMathException {
		HashMap<Integer, OMOBJ> fillInVarMap = new HashMap<>();
		ArrayList<Object> setList = new ArrayList<Object>();
		setList.add(OMSymbol.EDITOR1_INPUT_BOX);
		fillInVarMap.put(1, OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.LIST1_LIST, setList)));
		
		OMOBJ result = Evaluator.evaluate("isSet([pos=1])", null, fillInVarMap);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testInIntegration4() throws EvaluatorException, OpenMathException {
		HashMap<Integer, OMOBJ> fillInVarMap = new HashMap<>();
		fillInVarMap.put(1, OMCreator.createOMOBJ(OMSymbol.SET1_EMPTYSET));
		
		OMOBJ result = Evaluator.evaluate("isSet([pos=1])", null, fillInVarMap);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
}
