package de.uni_due.s3.evaluator.core.functionData;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Divide;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Minus;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Plus;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Power;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Times;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * This Test requires at least the 5 Symbols (in parameters specified) to work
 * correctly In Order to Test this Class
 * 
 * @author dlux
 *
 */
@RunWith(Parameterized.class)
public class TestOMSFunctionDictionary {

	private OMSFunctionDictionary omsfd = OMSFunctionDictionary.getInstance();

	static Object[][] parameters = { 
			{ OMSymbol.ARITH1_PLUS, new Plus(), OMCreator.createOMS("arith1", "myVeryOwnPlus") }, // [0]
			{ OMSymbol.ARITH1_DIVIDE, new Divide(), OMCreator.createOMS("arith2", "myVeryOwnPlus2") }, 
			{ OMSymbol.ARITH1_MINUS, new Minus(), OMCreator.createOMS("myCD", "myName") }, 
			{ OMSymbol.ARITH1_TIMES, new Times(), OMCreator.createOMS("thisCD", "anotherFunctionNotImplemented") },
			{ OMSymbol.ARITH1_POWER, new Power(), null}, 
	};

	
	private OMS inMap, notInMap;
	private Function expected;

	@Parameterized.Parameters
	public static Collection<Object[]> test() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		for (Object[] a : parameters) {
			list.add(a);
		}
		return list;
	}
	

	public TestOMSFunctionDictionary(OMS inMap, Function expected, OMS notInMap) {
		this.inMap = inMap;
		this.expected = expected;
		this.notInMap = notInMap;
	}

	
	@Test
	public void testGetFunction() {
		Function f = omsfd.getFunction(inMap);
		assertTrue(f.getClass().isInstance(expected));
	}

	
	@Test(expected = FunctionNotImplementedRuntimeException.class)
	public void testGetFunctionWithException(){
		omsfd.getFunction(notInMap);
	}

}