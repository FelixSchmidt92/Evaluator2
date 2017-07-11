package de.uni_due.s3.evaluator.core.function.functions.string_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

@RunWith(Parameterized.class)
public class TestCharAt {

	static String[][] addition = { { "charAt('abc', 0)", "a", "charAt('abc', 3)" }, // [0]
			{ "charAt('abc', 1)", "b", "charAt('', 0)" }, { "charAt('abc', 2)", "c", "charAt('abc', -1)" },
			{ "charAt('Halloo', 0)", "H", "charAt('1', 1)" }, { "charAt('Halloo', 5)", "o", "charAt('1.0', 1)" },
			{ "charAt('Halloo', 3)", "l", "charAt('1', 'abc')" }, };

	private String parameter, expected, currentError;

	@Parameterized.Parameters
	public static Collection<String[]> test() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		for (String[] a : addition) {
			list.add(a);
		}
		return list;
	}

	public TestCharAt(String current, String expected, String currentError) {
		parameter = current;
		this.expected = expected;
		this.currentError = currentError;
	}

	@Test
	public void testCharAt() throws FunctionException, ParserRuntimeException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ t = ExpressionParser.parse(parameter, null, null);

		assertEquals(OMExecutor.execute(t).getOMSTR().getContent(), expected);

	}

	@Test(expected = FunctionException.class)
	public void testCharAtFunctionException() throws FunctionException, ParserRuntimeException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ t = ExpressionParser.parse(currentError, null, null);
		OMExecutor.execute(t);

	}

}