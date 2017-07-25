package de.uni_due.s3.evaluator.core.function.set1;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.set1.Set;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSet extends TestFunctionAbstract {

	private Function func = new Set();
	private List<Object> args;
	private Object result;

	@Test
	public void testSetWithOMI() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(20));
		args.add(OMCreator.createOMI(30));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.SET1_SET, args), result);
	}

	@Test
	public void testSetWithOMF() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(10.0));
		args.add(OMCreator.createOMF(20.3));
		args.add(OMCreator.createOMF(30.12));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.SET1_SET, args), result);
	}

	@Test
	public void testSetWithOMA() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(10.0));
		args.add(OMCreator.createOMF(20.3));
		args.add(OMCreator.createOMF(30.12));

		ArrayList<Object> arguments = new ArrayList<Object>();
		arguments.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, args));
		arguments.add(OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, args));
		result = func.evaluate(arguments);
		assertEquals(true, result instanceof OMA);
		assertEquals(OMCreator.createOMA(OMSymbol.SET1_SET, arguments), result);
	}

	@Test
	public void testSetIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("{1;2;3}", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> value = result.getOMA().getOmel();
		assertEquals(OMCreator.createOMI(1), value.get(1));
		assertEquals(OMCreator.createOMI(2), value.get(2));
		assertEquals(OMCreator.createOMI(3), value.get(3));
	}
	
	@Test
	public void testSetIntegrationWithZero() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("{}", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMA(OMSymbol.SET1_SET, new ArrayList<Object>()), result.getOMA());
	}

	@Test
	public void testSetIntegrationAsFunction() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("set(1,2,3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> value = result.getOMA().getOmel();
		assertEquals(OMCreator.createOMI(1), value.get(1));
		assertEquals(OMCreator.createOMI(2), value.get(2));
		assertEquals(OMCreator.createOMI(3), value.get(3));
	}
}
