package de.uni_due.s3.evaluator.core.function.string_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.string_jack.Matches;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMatches extends TestFunctionAbstract {

	Function func = new Matches();
	
	@Test
	public void testMatchesWithAlphabet() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		OMSTR reg = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		args.add(in);
		args.add(reg);
		
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}
	
	
	@Test
	public void testMatchesWithRegex() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("abcdefghijklmnopqrstuvwxyz");
		OMSTR reg = OMCreator.createOMSTR("[a-z]+");
		args.add(in);
		args.add(reg);
		
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}
	
	
	@Test
	public void testMatchesWithNumber() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR in = OMCreator.createOMSTR("2");
		OMSTR reg = OMCreator.createOMSTR("\\d");
		args.add(in);
		args.add(reg);
		
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMatchesWithOMA() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMA in = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, new ArrayList<>());
		OMSTR reg = OMCreator.createOMSTR("\\d");
		args.add(in);
		args.add(reg);
		
		func.evaluate(args);
	}
}
