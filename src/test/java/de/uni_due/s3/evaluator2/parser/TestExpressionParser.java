package de.uni_due.s3.evaluator2.parser;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestExpressionParser {

	@Test(expected = UndefinedFillInVariableException.class)
	public void testExpressionParserNullFillIns() throws FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			ErroneousFillInVariableException, ErroneousExerciseVariableException, OpenMathException {
		ExpressionParser.parse("[pos=1]", null, null);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testExpressionParserNullExerVars() throws FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			ErroneousFillInVariableException, ErroneousExerciseVariableException, OpenMathException {
		ExpressionParser.parse("[var=a]", null, null);
	}

	@Test(expected = ParserRuntimeException.class)
	public void testCreateParseTreewithEmptyString() {
		ExpressionParser.createParseTree("");
	}

	@Test(expected = ParserRuntimeException.class)
	public void testCreateParseTreewithNullString() {
		ExpressionParser.createParseTree(null);
	}

	@Test(expected = ParserRuntimeException.class)
	public void testCreateParseTreeWithNonParsableinput() {
		ExpressionParser.createParseTree("-");
	}

	@Test
	public void testCreateParseTreeWithParsableinput() {
		ExpressionParser.createParseTree("-1");
		ExpressionParser.createParseTree("-----1");
		ExpressionParser.createParseTree("--+--1");
		ExpressionParser.createParseTree("abc(a,b,c,1,2,3)");
	}

	@Test(expected = ParserRuntimeException.class)
	public void testCreateParseTreeWithLexarError1() {
		ExpressionParser.createParseTree("#");
	}

	@Test(expected = ParserRuntimeException.class)
	public void testCreateParseTreeWithLexarError2() {
		ExpressionParser.createParseTree("¬̣");
	}

	@Test(expected = ParserRuntimeException.class)
	public void testCreateParseTreeWithParserError1() {
		ExpressionParser.createParseTree("/ 2");
	}

	@Test(expected = ParserRuntimeException.class)
	public void testCreateParseTreeWithParserError2() {
		ExpressionParser.createParseTree("[pos=1]^");
	}

	@Test(expected = ParserRuntimeException.class)
	public void testCreateParseTreeWithParserError3() {
		ExpressionParser.createParseTree("[pos=1]abc");
	}
}
