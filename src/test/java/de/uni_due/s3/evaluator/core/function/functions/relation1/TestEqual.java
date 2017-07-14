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
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqual {

	private final Function func = OMSFunctionDictionary.getInstance()
			.getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("equal"));
	
	private List<Object> args;
	private Object result;
	
	@Test
	public void testEqualsOMI() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);		
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(10));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
		
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(120976));
		args.add(OMCreator.createOMI(12));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);	
	}
	
	@Test
	public void testEqualsOMF() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);		
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMF(0.546363));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
		
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(12.3045));
		args.add(OMCreator.createOMF(12.3044));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);	
	}
	
	@Test
	public void testEqualsOMS() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);		
		args.add(OMCreator.createOMS("arith1","plus"));
		args.add(OMCreator.createOMS("arith1","plus"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
		
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMS("realtion1","eq"));
		args.add(OMCreator.createOMS("arith1","plus"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);	
	}
	
	@Test
	public void testEqualsOMA() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		OMS oms = OMCreator.createOMS("linalg2", "matrixrow");
		ArrayList<Object> arguments = new ArrayList<Object>(3);
		ArrayList<Object> arguments2 = new ArrayList<Object>(3);
		
		arguments.add(OMCreator.createOMI(10));
		arguments.add(OMCreator.createOMI(-10));
		arguments.add(OMCreator.createOMI(30));
		arguments2.add(OMCreator.createOMI(10));
		arguments2.add(OMCreator.createOMI(20));
		arguments2.add(OMCreator.createOMI(30));
		
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(oms,arguments));
		args.add(OMCreator.createOMA(oms,arguments));	
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
			
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(oms,arguments));
		args.add(OMCreator.createOMA(oms,arguments2));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);	
	}
	
	@Test
	public void testEqualsOMSTR() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);	
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
		
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("tes t"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);	
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		args.add(OMCreator.createOMSTR("test"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualsWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		args.add(null);
		args.add(OMCreator.createOMSTR(null));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}
	
	@Test
	public void testEqualsIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("equal(10,10)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualsSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException {
		args = new ArrayList<Object>(2);		
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(10));
		assertEquals("10 == 10", func.getPartialSageSyntax(args));
	}

}
