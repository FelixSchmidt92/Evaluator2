package de.uni_due.s3.evaluator2.dictionaries;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator2.dictionaries.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.openmath.jaxb.OMS;

/**
 * This Test requires at least the 5 Symbols (in parameters specified) to work
 * correctly to Test this Class
 * 
 * @author dlux
 *
 */
@RunWith(Parameterized.class)
public class TestOMSEvaluatorSyntaxDictionary {

	private OMSEvaluatorSyntaxDictionary omsesd = OMSEvaluatorSyntaxDictionary.getInstance();

	static Object[][] parameters = { 
			{ "plus", OMSymbol.ARITH1_PLUS, "myPlus" }, // [0]
			{ "divide", OMSymbol.ARITH1_DIVIDE, "devide" }, 
			{ "minus", OMSymbol.ARITH1_MINUS, "" }, 
			{ "times", OMSymbol.ARITH1_TIMES, " " },
			{ "pow", OMSymbol.ARITH1_POWER, null}, 
	};

	
	private String inMap, notInMap;
	private OMS expected;

	@Parameterized.Parameters
	public static Collection<Object[]> test() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		for (Object[] a : parameters) {
			list.add(a);
		}
		return list;
	}
	

	public TestOMSEvaluatorSyntaxDictionary(String inMap, OMS expected, String notInMap) {
		this.inMap = inMap;
		this.expected = expected;
		this.notInMap = notInMap;
	}

	
	@Test
	public void testGetFunction() {
		OMS actual = omsesd.getOMS(inMap);
		assertEquals(expected, actual);
	}

	
	@Test(expected = FunctionNotImplementedRuntimeException.class)
	public void testGetFunctionWithException(){
		omsesd.getOMS(notInMap);
	}

}