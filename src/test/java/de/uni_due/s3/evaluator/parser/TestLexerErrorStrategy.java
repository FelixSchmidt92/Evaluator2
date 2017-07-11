package de.uni_due.s3.evaluator.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;

/**
 * This class tests the LexerErrorStrategy
 * 
 * Every test expects the ParserException
 * 
 * @author dlux
 */
@RunWith(Parameterized.class)
public class TestLexerErrorStrategy {

	static String SpecialCharacters = "#?&^°_\";:@~§`|µ¸}{¬̣··"; // Characters
																	// which are
																	// not
	private String parameter; // expected for the Syntax

	@Parameterized.Parameters
	public static Collection<String> test() {
		ArrayList<String> list = new ArrayList<String>();
		for (char a : SpecialCharacters.toCharArray()) {
			list.add(String.valueOf(a));
		}
		return list;
	}

	public TestLexerErrorStrategy(String current) {
		parameter = current;
	}

	@Test(expected = ParserRuntimeException.class)
	public void testSyntaxError() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		ExpressionParser.parse(parameter, null, null);
	}
}
