package de.uni_due.s3.evaluator2.function.string_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.string_jack.Matches;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMatches extends TestFunctionAbstract {

	Function func = new Matches();

	@Test
	public void testMatchesWithAlphabet() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		OMSTR reg = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		args.add(in);
		args.add(reg);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testMatchesWithRegex() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		OMSTR reg = OMCreator.createOMSTR("[a-z]+");
		args.add(in);
		args.add(reg);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testMatchesWithNumber() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("2");
		OMSTR reg = OMCreator.createOMSTR("\\d");
		args.add(in);
		args.add(reg);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}
}
