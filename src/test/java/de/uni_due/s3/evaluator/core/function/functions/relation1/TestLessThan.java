package de.uni_due.s3.evaluator.core.function.functions.relation1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestLessThan {

	private final Function func = OMSFunctionDictionary.getInstance()
			.getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("lessthan"));
	
	private List<Object> args;
	private Object result;
	
	@Test
	public void testLessThanOMI() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);		
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(1));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
		
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(109345));
		args.add(OMCreator.createOMI(9000000));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);	
	}
	
	@Test
	public void testLessThanOMF() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);		
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMF(0.546362));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
		
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(12.3045));
		args.add(OMCreator.createOMF(12.3046));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);	
	}
	
	@Test
	public void testLessThanOMFAndOMI() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);		
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMI(-3));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
		
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMF(12.3046));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);	
	}
	
	
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testLessThanWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		args.add(OMCreator.createOMSTR("test"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testLessThanWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testLessThanWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		args.add(null);
		args.add(OMCreator.createOMSTR(null));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}
	
	@Test
	public void testLessThanIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("5 < 10", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testLessThanSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException {
		args = new ArrayList<Object>(2);		
		args.add(OMCreator.createOMI(5));
		args.add(OMCreator.createOMI(9));
		assertEquals("5 < 9", func.getPartialSageSyntax(args));
	}

}
