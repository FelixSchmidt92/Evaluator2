package de.uni_due.s3.evaluator.core.function.arith1;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openmath.openmath.OMOBJ;
import org.openmath.openmath.OMS;

import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoSageRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

@RunWith(Parameterized.class)
public class TestPlus {

	static String[][] addition = {
			//Integer
			{"1+1", "2"}, //[0]
			{"1+2", "3"},
			{"2+1", "3"},
			{"plus(1,1)", "2"},
			{"plus(1,2)", "3"},
			{"plus(2,1)", "3"},
			{"3+3+3", "9"},
			{"plus(plus(plus(1,1), 1), 1)", "4"},
			{"plus(  plus(plus(1,1), plus(1,1))    ,   (plus(1, plus(1,1)))    )", "7"},
			{"plus(1+1, 1+1)", "4"},
			
			//Float
			{"plus(1.0, 1.0)", "2"}, //[10]
			{"plus(1.0, 2.0)", "3"},
			{"plus(2.0, 1.0)", "3"},
			
			//TODO dlux vector, matrix, OMAS(negative numbers), maybe complex
			};
	
	private String parameter, expected;
	
	@Parameterized.Parameters
	public static Collection<String[]> test(){
		ArrayList<String[]> list = new ArrayList<String[]>();
		for (String[] a : addition){
			list.add(a);
		}
		return list;
	}
	
	public TestPlus(String current, String expected){
		parameter = current;
		this.expected = expected;
	}
	
	@Test
	public void testPlus(){
		assertEquals(ExpressionParser.parse(parameter,null,null).getOMI().getValue(), expected);
	}
	
	@Test
	public void testPlusSageSyntax() throws FunctionNotImplementedException, OMObjectNotSupportedException, OMOBJChildNotSupportedException, FunctionArgumentNumberException, NoRepresentationAvailableException{
		OMOBJ omobj = ExpressionParser.parse("plus(5,10)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS)args.get(0);
		args.remove(0);
		
		String syntax = OMSFunctionDictionary.getInstance().getFunction(oms).getPartialSageSyntax(args);
		assertEquals("5 + 10", syntax);
	}
}
