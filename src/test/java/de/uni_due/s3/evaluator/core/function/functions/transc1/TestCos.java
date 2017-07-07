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

public class TestCos extends TestFunction {

	private OMI omi = OMCreator.createOMI(1);
	private OMF omf = OMCreator.createOMF(0.0);
	private static Function func = OMSFunctionDictionary.getInstance()
			.getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("cos"));

	@Test
	public void testCosFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(omf);
		OMI result = (OMI) func.evaluate(args);
		assertEquals("1", result.getValue());
	}

	@Test
	public void testCosInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(omi);
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(0.54030230586814), result.getDec());
	}

	@Test
	public void testCosIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("cos(0)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("1", result.getOMI().getValue());
	}

	@Test
	public void testCosSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException {
		OMF omf = OMCreator.createOMF(1.0);
		List<Object> args = new ArrayList<>();
		args.add(omf);
		assertEquals("cos(1.0)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCosWithLessThanMinParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("cos()", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCosWithMoreThanMaxParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("cos(1,3)", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testCosWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("cos('Test')", null, null);
		OMExecutor.execute(omobj);
	}
}
