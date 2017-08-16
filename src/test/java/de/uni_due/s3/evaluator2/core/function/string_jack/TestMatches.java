package de.uni_due.s3.evaluator2.core.function.string_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMatches extends TestFunctionAbstract {

	Function func = new Matches();

	@Test
	public void testMatchesWithAlphabet() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		OMSTR reg = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		args.add(in);
		args.add(reg);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testMatchesWithRegex() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		OMSTR reg = OMCreator.createOMSTR("[a-z]+");
		args.add(in);
		args.add(reg);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testMatchesWithNumber() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("2");
		OMSTR reg = OMCreator.createOMSTR("\\d");
		args.add(in);
		args.add(reg);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMatchesWithOMA() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMA in = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, new ArrayList<>());
		OMSTR reg = OMCreator.createOMSTR("\\d");
		args.add(in);
		args.add(reg);

		func.evaluate(args);
	}
}
