package de.uni_due.s3.evaluator.core.function.functions.string_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMatches extends TestFunction {

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
	public void testMatchesWithOMI() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMI in = OMCreator.createOMI(1);
		OMSTR reg = OMCreator.createOMSTR("\\d");
		args.add(in);
		args.add(reg);
		
		func.evaluate(args);
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMatchesWithOMF() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMF in = OMCreator.createOMF(1.1);
		OMSTR reg = OMCreator.createOMSTR("\\d");
		args.add(in);
		args.add(reg);
		
		func.evaluate(args);
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMatchesWithOMV() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMV in = OMCreator.createOMV("x");
		OMSTR reg = OMCreator.createOMSTR("\\d");
		args.add(in);
		args.add(reg);
		
		func.evaluate(args);
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
