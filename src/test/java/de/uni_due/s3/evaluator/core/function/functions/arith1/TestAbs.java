package de.uni_due.s3.evaluator.core.function.functions.arith1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Abs;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestAbs {
	private Abs func = new Abs();

	@Test
	public void testAbsInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {

		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(-3));
		OMI result = (OMI) func.evaluate(args);
		assertEquals("3", result.getValue());
	}

	@Test
	public void testAbsFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(-2.5));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(2.5), result.getDec());
	}

	@Test
	public void testAbsIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("abs(-13)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("13", result.getOMI().getValue());

		omobj = ExpressionParser.parse("abs(10)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("10", result.getOMI().getValue());
	}

	@SuppressWarnings("unused")
	@Test
	public void testAbsSageSyntax() throws FunctionInvalidNumberOfArgumentsException,
			NoRepresentationAvailableException, CasException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMF omf = OMCreator.createOMF(1.0);
		OMI omi = OMCreator.createOMI(10);

		OMOBJ omobj = ExpressionParser.parse("abs(5)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS) args.get(0);
		args.remove(0);

		assertEquals("abs(5)", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(omf);
		assertEquals("abs(1.0)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAbsWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("abs('test')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());

		omobj = ExpressionParser.parse("abs('test')", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}

}
