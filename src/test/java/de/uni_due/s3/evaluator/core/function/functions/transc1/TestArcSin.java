package de.uni_due.s3.evaluator.core.function.functions.transc1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestArcSin extends TestFunction {

	private OMI omi = OMCreator.createOMI(1);
	private OMF omf = OMCreator.createOMF(0.0);
	private static Function func = OMSFunctionDictionary.getInstance()
			.getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("asin"));

	@Test
	public void testArcSinInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(omf);
		OMI result = (OMI) func.evaluate(args);
		assertEquals("0", result.getValue());
	}

	@Test
	public void testArcSinFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(omi);
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(1.57079632679490), result.getDec());
	}

	@Test
	public void testArcSinIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("asin(0)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("0", result.getOMI().getValue());
	}

	@Test
	public void testArcSinSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException {
		OMF omf = OMCreator.createOMF(1.0);
		List<Object> args = new ArrayList<>();
		args.add(omf);
		assertEquals("arcsin(1.0)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcSinWithLessThanMinParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("asin()", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcSinWithMoreThanMaxParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("asin(1,3)", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testArcSinWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("asin('Test')", null, null);
		OMExecutor.execute(omobj);
	}
}
