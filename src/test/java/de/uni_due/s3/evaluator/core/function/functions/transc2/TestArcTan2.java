package de.uni_due.s3.evaluator.core.function.functions.transc2;

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

public class TestArcTan2 extends TestFunction {

	private OMI omi = OMCreator.createOMI(1);
	private OMI omi0 = OMCreator.createOMI(0);
	private OMF omf1 = OMCreator.createOMF(1.0);
	private static Function func = OMSFunctionDictionary.getInstance()
			.getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("atan2"));

	@Test
	public void testArcTan2Float() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(omf1);
		args.add(omf1);
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(0.785398163397448), result.getDec());
	}

	@Test
	public void testArcTan2Integer() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(omi);
		args.add(omi0);
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(1.57079632679490), result.getDec());
	}

	@Test
	public void testArcTan2Integration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("atan2(2,1)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(new Double(1.10714871779409), result.getOMF().getDec());
	}

	@Test
	public void testArcTan2SageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException {
		OMF omf = OMCreator.createOMF(1.0);
		OMF omf1 = OMCreator.createOMF(1.1);
		List<Object> args = new ArrayList<>();
		args.add(omf);
		args.add(omf1);
		assertEquals("arctan2(1.0,1.1)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcTan2WithLessThanMinParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("atan2(1)", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcTan2WithMoreThanMaxParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("atan2(1,3,7)", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testArcTan2WithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException {
		OMOBJ omobj = ExpressionParser.parse("atan2('Test',2)", null, null);
		OMExecutor.execute(omobj);
	}
}
