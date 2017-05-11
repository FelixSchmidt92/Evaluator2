package de.uni_due.s3.evaluator.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator.exceptions.ParserException;

/**
 * This class tests the ParserErrorStrategy.
 * 
 * Everytime ParserMismatch should be thrown.
 * 
 * @author dlux
 */
@RunWith(Parameterized.class)
public class TestParserErrorStrategy {

	private String parameter;
	private static String[] test = {
			"/ 2", "3 /", " * [var=a]", " / [pos=0]", " / 'abcd'", "((())", "((", "(()))", "))", 
			"a(", "(*)", 				 //which reportNoViableAlternative should catch (or sync)
			"w" ,"a", "bas", "a123", "b09",						//which recoverInLine should catch
			"1a", "[var=a]bv", "'abs'abs", "test(123)a", "1z"	//which sync should catch
	};
	
	@Parameterized.Parameters
	public static Collection<String> test(){
		ArrayList<String> list = new ArrayList<String>();
		for (String a : test){
			list.add(a);
		}
		return list;
	}
	
	public TestParserErrorStrategy(String current) {
		parameter = current;
	}
	
	@Test(expected=ParserException.class)
	public void testSyncANDRecoverInlineANDReportNoViableAlternative(){
		ExpressionParser.parse(parameter);
	}
}
