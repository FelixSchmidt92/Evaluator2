package de.uni_due.s3.evaluator.core.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoSageRepresentationAvailableException;
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
		protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
				CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
			return "evaluated";
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
	public void testExecute() throws CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		assertEquals("evaluated", myFunction.execute(null));
	}

	@Test
	public void testEvaluate() throws CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> arguments = new ArrayList<>();
		arguments.add(OMCreator.createOMI(15));
		assertEquals("evaluated", myFunction.evaluate(arguments));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvaluateWithWrongAmountOfArguments0() throws CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> arguments = new ArrayList<>();
		myFunction.evaluate(arguments);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvaluateWithWrongAmountOfArguments4() throws CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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

	@Test(expected = NoSageRepresentationAvailableException.class)
	public void testGetPartialSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		myFunction.getPartialSageSyntax(null);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		myFunction.getSageSyntax(null);
	}
}