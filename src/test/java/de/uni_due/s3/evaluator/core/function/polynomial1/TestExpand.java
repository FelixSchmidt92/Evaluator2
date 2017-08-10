package de.uni_due.s3.evaluator.core.function.polynomial1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import static org.junit.Assert.*;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestExpand extends TestFunctionAbstract {

	private Function func = new Expand();

	@Test
	public void testDegreeWrtWithOneVariable() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		List<Object> args = new ArrayList<Object>();
		args.add(ExpressionParser.parse("(2+x) * 2", null, null));
		Object result = func.evaluate(args);
		assertEquals(ExpressionParser.parse("2*x + 4", null, null).getOMA(), result);
	}

	@Test
	public void testDegreeWrtWithTwoVariable() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, JAXBException {
		List<Object> args = new ArrayList<Object>(1);
		args.add(ExpressionParser.parse("(2 + x) * (x - 2)", null, null));
		Object result = func.evaluate(args);
		assertEquals(
				OMConverter.toElement(OMConverter.toObject(
						"<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"x\"/><OMI>2</OMI></OMA><OMI>-4</OMI></OMA></OMOBJ>")),
				result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDegreeWrtWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDegreeWrtWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testDegreeWrtIntegration1() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, JAXBException {
		OMOBJ omobj = ExpressionParser.parse("expand((2 + x) * (x - 2))", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(
				OMConverter.toElement(OMConverter.toObject(
						"<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"x\"/><OMI>2</OMI></OMA><OMI>-4</OMI></OMA></OMOBJ>")),
				result.getOMA());
	}
	
	@Test
	public void testDegreeWrtIntegration2() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, JAXBException {
		OMOBJ omobj = ExpressionParser.parse("expand('(x - 2)*(2 + x)')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(
				OMConverter.toElement(OMConverter.toObject(
						"<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"x\"/><OMI>2</OMI></OMA><OMI>-4</OMI></OMA></OMOBJ>")),
				result.getOMA());
	}

	@Test
	public void testDegreeWrtSageSyntax() throws FunctionException, NoRepresentationAvailableException, CasException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(ExpressionParser.parse("1+a^2-b", null, null));
		args.add(OMCreator.createOMV("a"));
		assertEquals("var('a b');f = ((1 + (a)^(2)) - b); f.expand()", func.getPartialSageSyntax(args));
	}
}
