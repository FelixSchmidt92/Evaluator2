package de.uni_due.s3.evaluator.core.function.functions.arith1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Minus;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
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

public class TestMinus extends TestFunction {

	private OMI omi = OMCreator.createOMI(1);
	private OMF omf = OMCreator.createOMF(2.5);

	@Test
	public void testMinusInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		Minus func = new Minus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(omi);
		args.add(omi);
		OMI result = (OMI) func.evaluate(args);
		assertEquals("0", result.getValue());
	}

	@Test
	public void testMinusFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		Minus func = new Minus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(omf);
		args.add(OMCreator.createOMF(5.8));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(-3.3), result.getDec());
	}

	@Test
	public void testMinusMixed() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		Minus func = new Minus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(omi);
		args.add(omf);
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(-1.5), result.getDec());
	}

	@Test
	public void testMinusIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("10-5", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());

		omobj = ExpressionParser.parse("minus(10,17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}

	@SuppressWarnings("unused")
	@Test
	public void testMinusSageSyntax() throws FunctionInvalidNumberOfArgumentsException,
			NoRepresentationAvailableException, CasException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		Minus func = (Minus) OMSFunctionDictionary.getInstance()
				.getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("minus"));
		OMF omf = OMCreator.createOMF(1.0);
		OMI omi = OMCreator.createOMI(10);

		OMOBJ omobj = ExpressionParser.parse("minus(5,10)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS) args.get(0);
		args.remove(0);

		assertEquals("5 - 10", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(omf);
		args.add(omf);
		assertEquals("1.0 - 1.0", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMinusWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("10-'test'", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());

		omobj = ExpressionParser.parse("minus('test',17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}

}
