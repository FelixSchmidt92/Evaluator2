package de.uni_due.s3.evaluator.core.function.functions.arith1;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

@RunWith(Parameterized.class)
public class TestPlus {
	
	@BeforeClass
	public static void beforeClass(){
		List<String> aSageConnectionsList = new ArrayList<>();
		aSageConnectionsList.add("192.168.68.176:8989");
		Sage.init(aSageConnectionsList);
	}
	
	static String[][] addition = {
			//Integer
			{"1+1", "2", "1 + 1"}, //[0]
			{"1+2", "3", "1 + 2"},
			{"2+1", "3", "2 + 1"},
			{"plus(1,1)", "2", "1 + 1"},
			{"plus(1,2)", "3", "1 + 2"},
			{"plus(2,1)", "3", "2 + 1"},
			
			//Float
			{"plus(1.0, 1.0)", "2", "1.0 + 1.0"},
			{"plus(1.0, 2.0)", "3", "1.0 + 2.0"},
			{"plus(2.0, 1.0)", "3", "2.0 + 1.0"},
			
			//TODO dlux vector, matrix, OMAS(negative numbers), maybe complex
			};
	
	private String parameter, expected, sageString;
	
	@Parameterized.Parameters
	public static Collection<String[]> test(){
		ArrayList<String[]> list = new ArrayList<String[]>();
		for (String[] a : addition){
			list.add(a);
		}
		return list;
	}
	
	public TestPlus(String current, String expected, String sageString){
		parameter = current;
		this.expected = expected;
		this.sageString = sageString;
	}
	
	@Test
	public void testPlus() throws FunctionException, ParserException, OpenMathException, CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException{
		OMOBJ t = ExpressionParser.parse(parameter,null,null);
		
		assertEquals(OMExecutor.execute(t).getOMI().getValue(), expected);
	}
	
	@Test
	public void testPlusSageSyntax() throws FunctionInvalidNumberOfArgumentsException, CasException, NoRepresentationAvailableException, FunctionNotImplementedException{
		OMOBJ omobj = ExpressionParser.parse(parameter, null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS)args.get(0);
		args.remove(0);
		
		String syntax = OMSFunctionDictionary.getInstance().getFunction(oms).getPartialSageSyntax(args);
		assertEquals(sageString, syntax);
	}
}