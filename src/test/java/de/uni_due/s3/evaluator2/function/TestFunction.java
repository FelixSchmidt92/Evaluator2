package de.uni_due.s3.evaluator2.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestFunction {

	Function myFunction = new Function() {

		@Override
		protected int minArgs() {
			return 1;
		}

		@Override
		protected int maxArgs() {
			return 3;
		}

		@Override
		protected Object getPartialOpenMathElementResult(List<Object> arguments) throws FunctionException, CasEvaluationException,
				CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
			return OMCreator.createOMSTR("evaluated");
		}
	};

	@Test
	public void testMinMaxArgs() {
		assertEquals(1, myFunction.minArgs());
		assertEquals(3, myFunction.maxArgs());
	}

	@Test
	public void testArgumentsShouldBeEvaluated() {
		assertEquals(true, myFunction.argumentsShouldBeEvaluated());
	}

	@Test
	public void testExecute() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMSTR("evaluated"), myFunction.getPartialOpenMathElementResult(null));
	}

	@Test
	public void testEvaluate() throws EvaluatorException, OpenMathException {
		ArrayList<Object> arguments = new ArrayList<>();
		arguments.add(OMCreator.createOMI(15));
		assertEquals(OMCreator.createOMSTR("evaluated"), myFunction.evaluate(arguments));
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testEvaluateWithNull() throws EvaluatorException, OpenMathException {
		myFunction.evaluate(null);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvaluateWithWrongAmountOfArguments0() throws EvaluatorException, OpenMathException {
		ArrayList<Object> arguments = new ArrayList<>();
		myFunction.evaluate(arguments);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvaluateWithWrongAmountOfArguments4() throws EvaluatorException, OpenMathException {
		ArrayList<Object> arguments = new ArrayList<>();
		arguments.add("1");
		arguments.add("2");
		arguments.add("3");
		arguments.add("4");
		myFunction.evaluate(arguments);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArgsBetweenMinMax0() throws FunctionInvalidNumberOfArgumentsException {
		ArrayList<Object> arguments = new ArrayList<>();
		myFunction.argsBetweenMinMax(arguments);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArgsBetweenMinMax4() throws FunctionInvalidNumberOfArgumentsException {
		ArrayList<Object> arguments = new ArrayList<>();
		arguments.add("1");
		arguments.add("2");
		arguments.add("3");
		arguments.add("4");
		myFunction.argsBetweenMinMax(arguments);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetPartialSageSyntax() throws EvaluatorException, OpenMathException {
		myFunction.getPartialSageSyntax(null);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetSageSyntax() throws EvaluatorException, OpenMathException {
		myFunction.getSageSyntax(null);
	}
	
	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetPartialRSyntax() throws EvaluatorException, OpenMathException {
		myFunction.getPartialSageSyntax(null);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetRSyntax() throws EvaluatorException, OpenMathException {
		myFunction.getSageSyntax(null);
	}
}