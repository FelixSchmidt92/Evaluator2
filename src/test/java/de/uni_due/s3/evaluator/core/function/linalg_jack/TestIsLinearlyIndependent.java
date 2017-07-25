package de.uni_due.s3.evaluator.core.function.linalg_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.linalg_jack.IsLinearlyIndependent;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
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

public class TestIsLinearlyIndependent extends TestFunctionAbstract {

	Function func = new IsLinearlyIndependent();

	@Test
	public void testIsLinearlyIndependent1() throws UndefinedFillInVariableException, UndefinedExerciseVariableException,
			ParserException, FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> vector1 = new ArrayList<>();
		vector1.add(OMCreator.createOMI(1));
		vector1.add(OMCreator.createOMI(0));
		ArrayList<Object> vector2 = new ArrayList<>();
		vector2.add(OMCreator.createOMI(0));
		vector2.add(OMCreator.createOMI(1));

		ArrayList<Object> set = new ArrayList<>();
		set.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector1));
		set.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector2));
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}
	
	@Test
	public void testIsLinearlyIndependent2() throws UndefinedFillInVariableException, UndefinedExerciseVariableException,
			ParserException, FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> vector1 = new ArrayList<>();
		vector1.add(OMCreator.createOMI(0));
		vector1.add(OMCreator.createOMI(2));
		ArrayList<Object> vector2 = new ArrayList<>();
		vector2.add(OMCreator.createOMI(0));
		vector2.add(OMCreator.createOMI(1));

		ArrayList<Object> set = new ArrayList<>();
		set.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector1));
		set.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector2));
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}
	
	@Test
	public void testIsLinearlyIndependentIntegration1()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(set(vector(1,2,3), vector(3,2,1)))", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testIsLinearlyIndependentIntegration2()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(set(vector(1,1,0), vector(1,1,1)))", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testIsLinearlyIndependentIntegration3()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent({vector(1,1,0); vector(1,1,1); vector(1.5, 1.1, 1.11)})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testIsLinearlyIndependentIntegration4()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(set(vector(1,1,0),  vector(2, 2, 0)))", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testIsLinearlyIndependentWithWrongInput1()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(set(vector(1,1,0),  vector(2, 2, 0), 3))", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsLinearlyIndependentWithWrongInput2()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(vector(1,1,0))", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsLinearlyIndependentWithLess()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent(vector(1,1,0), vector(1,1,0))", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsLinearlyIndependentWithMore()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isLinearlyIndependent()", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
