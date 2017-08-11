package de.uni_due.s3.evaluator2.core.function.openmath_jack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.function.openmath_jack.CountBasicOperations;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCountBasicOperations extends TestFunctionAbstract{

	Function func = new CountBasicOperations();
	
	@Test
	public void TestCountBasicOperationsWithIntegration() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ parsed = ExpressionParser.parse("countBasicOperations('2+1-3/2*2-1-2*sin(x)')", null, null);
		
		OMOBJ actual = OMExecutor.execute(parsed);
		assertEquals(OMCreator.createOMI(7), actual.getOMI());
	}
	
	@Test
	public void TestCountBasicOperationsWithIntegration1() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ parsed = ExpressionParser.parse("countBasicOperations('-', '2+1+1*1*1--1')", null, null);
		
		OMOBJ actual = OMExecutor.execute(parsed);
		assertEquals(OMCreator.createOMI(1), actual.getOMI());
	}
	
	@Test
	public void TestCountBasicOperationsWithIntegration2() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ parsed = ExpressionParser.parse("countBasicOperations('/', '2+1+1*1*1--1')", null, null);
		
		OMOBJ actual = OMExecutor.execute(parsed);
		assertEquals(OMCreator.createOMI(0), actual.getOMI());
	}
	
	@Test
	public void TestCountBasicOperationsWithIntegration3() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ parsed = ExpressionParser.parse("countBasicOperations('*', '2+1+1*1*1--1')", null, null);
		
		OMOBJ actual = OMExecutor.execute(parsed);
		assertEquals(OMCreator.createOMI(2), actual.getOMI());
	}
	
	@Test(expected = FunctionInvalidArgumentException.class)
	public void TestCountBasicOperationsWithIntegrationWrongArguments() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ parsed = ExpressionParser.parse("countBasicOperations('#', '2+1+1*1*1--1')", null, null);
		OMExecutor.execute(parsed);
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestCountBasicOperationsWithIntegrationWrongArgumentType() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ parsed = ExpressionParser.parse("countBasicOperations(2, '2+1+1*1*1--1')", null, null);
		OMExecutor.execute(parsed);
	}
}
