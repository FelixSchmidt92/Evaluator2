package de.uni_due.s3.evaluator.core.function.functions.logic1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestBooleanNot {

	private final Function func = OMSFunctionDictionary.getInstance()
			.getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("booleannot"));
	private List<Object> args;
	private Object result;
	
	@Test
	public void TestBooleanNotWithArgumentTrue() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);
		args.add(OMSymbol.LOGIC1_TRUE);
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE,result);
	}
	
	@Test
	public void TestBooleanNotWithArgumentFalse() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);
		args.add(OMSymbol.LOGIC1_FALSE);		
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE,result);	
	}
	
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanNotWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanNotWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testBooleanNotWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);	
		args.add(OMSymbol.ARITH1_DIVIDE);
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}
	
	@Test
	public void testBooleanNotIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj;
		OMOBJ result;
		
		omobj = ExpressionParser.parse("!(2==1)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
		
		omobj = ExpressionParser.parse("!(2>1)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test
	public void testBooleanNotSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException {
		args = new ArrayList<Object>(2);		
		args.add(OMSymbol.LOGIC1_TRUE);
		assertEquals("not(True)", func.getPartialSageSyntax(args));
	}

}
