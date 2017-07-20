package de.uni_due.s3.evaluator.core.function.functions.linalg_jack;

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
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsLinearlyIndependent extends TestFunction {

	Function func = new IsLinearlyIndependent();

	@Test
	public void testIsLinearlyIndependent1()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMOBJ omobj = ExpressionParser.parse("set(vector(1,2,3), vector(3,2,1))", null, null);
		args.add(omobj.getOMA());
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsLinearlyIndependent2()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMOBJ omobj = ExpressionParser.parse("set(vector(1,1,0), vector(1,1,1))", null, null);
		args.add(omobj.getOMA());
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsLinearlyIndependent3()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMOBJ omobj = ExpressionParser.parse("set(vector(1,1,0), vector(1,1,1), vector(1.5, 1.1, 1.11))", null, null);
		args.add(omobj.getOMA());
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsLinearlyIndependent4()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMOBJ omobj = ExpressionParser.parse("set(vector(1,1,0),  vector(2, 2, 0))", null, null);
		args.add(omobj.getOMA());
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsLinearlyIndependentWithWrongInput()
			throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMOBJ omobj = ExpressionParser.parse("vector(1,2,3)", null, null);
		args.add(omobj.getOMA());
		func.evaluate(args);
	}
}
