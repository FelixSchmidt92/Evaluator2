package de.uni_due.s3.evaluator.core.function.linalg_jack;

import static org.junit.Assert.assertEquals;


import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.linalg_jack.RandomMatrixEigenvalue;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomMatrixEigenvalue extends TestFunctionAbstract{
	
	Function func = new RandomMatrixEigenvalue();
	
	static OMOBJ omobj1 = null;
	static OMOBJ omobj2 = null;
	static OMOBJ omobj3 = null;
	
	
	@BeforeClass
	public static void init() throws FunctionNotImplementedException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		omobj3 = ExpressionParser.parse("matrix(matrixrow(1,0,0), matrixrow(0,1,0), matrixrow(0,0,1))", null, null);
		omobj2 = ExpressionParser.parse("matrix(matrixrow(1,0), matrixrow(0,1))", null, null);
		omobj1 = ExpressionParser.parse("matrix(matrixrow(1))", null, null);
	}
	
	@Test
	public void testRandomMatrixEigenValueWithIntegration1() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 1, '[1]', '[1]')", null, null);
		obj = OMExecutor.execute(obj);
		
		assertEquals(omobj1, obj);
	}
	
	
	@Test
	public void testRandomMatrixEigenValueCaseIntegration2() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 2, '[1,1]', '[1,1]')", null, null);
		obj = OMExecutor.execute(obj);
		
		assertEquals(omobj2, obj);
	}
	
	@Test
	public void testRandomMatrixEigenValueCaseIntegration3() throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 3, '[1,1,1]', '[1,1,1]')", null, null);
		obj = OMExecutor.execute(obj);
		
		assertEquals(omobj3, obj);
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testRandomMatrixEigenValueCaseIntegration4() throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 3, '[1,1,1]', 12)", null, null);
		obj = OMExecutor.execute(obj);
		
		assertEquals(omobj3, obj);
	}

}
