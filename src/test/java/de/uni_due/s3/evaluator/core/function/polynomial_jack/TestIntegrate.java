package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import static org.junit.Assert.*;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
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
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIntegrate extends TestFunctionAbstract {

	private Function func = new Integrate();

	@Test
	public void testIntegrateWithOneVariable()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException {
		List<Object> args = new ArrayList<Object>();

		OMOBJ term = OMConverter.toObject(
				"<OMOBJ><OMA>" + "<OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"times\"/>"
						+ "<OMI>2</OMI>" + "<OMV name=\"x\"/>" + "</OMA>" + "<OMI>-5</OMI>" + "</OMA></OMOBJ>");
		args.add(term.getOMA());
		args.add(OMCreator.createOMV("x"));

		OMOBJ expected = OMConverter.toObject("<OMOBJ><OMA>" + "<OMS cd =\"arith1\" name=\"plus\"/>" + "<OMA>"
				+ "<OMS cd=\"arith1\" name=\"power\" />" + "<OMV name=\"x\"/>" + "<OMI>2</OMI>" + "</OMA>" + "<OMA>"
				+ "<OMS cd=\"arith1\" name=\"times\" />" + "<OMI>-5</OMI>" + "<OMV name=\"x\"/>" + "</OMA>"
				+ "</OMA></OMOBJ>");
		Object result = func.evaluate(args);
		assertEquals(expected.getOMA(), result);
	}

	@Test
	public void testIntegrateWithTwoVariable()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ term = OMConverter.toObject(
				"<OMOBJ><OMA>" + "<OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"times\"/>"
						+ "<OMI>2</OMI>" + "<OMV name=\"x\"/>" + "</OMA>" + "<OMI>v</OMI>" + "</OMA></OMOBJ>");
		args.add(term.getOMA());
		args.add(OMCreator.createOMV("x"));

		OMOBJ expected = OMConverter.toObject("<OMOBJ><OMA>" + "<OMS cd =\"arith1\" name=\"plus\"/>" + "<OMA>"
				+ "<OMS cd=\"arith1\" name=\"times\" />" + "<OMV name=\"x\"/>" + "<OMV name=\"v\"/>" + "</OMA>"
				+ "<OMA>" + "<OMS cd=\"arith1\" name=\"power\" />" + "<OMV name=\"x\"/>" + "<OMI>2</OMI>" + "</OMA>"
				+ "</OMA></OMOBJ>");
		Object result = func.evaluate(args);
		assertEquals(expected.getOMA(), result);
	}

	@Test
	public void testIntegrateWithTwoVariable2()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>2</OMI><OMV name=\"b\"/></OMA>" + "</OMA>"
				+ "</OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("b"));

		OMOBJ expected = OMConverter
				.toObject("<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/>" + "<OMA><OMS name=\"times\" cd=\"arith1\"/>"
						+ "<OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMV name=\"b\"/></OMA>" + "<OMA><OMS name=\"times\" cd=\"arith1\"/><OMI>-1</OMI>"
						+ "<OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"b\"/><OMI>2</OMI></OMA>"
						+ "</OMA></OMA></OMOBJ>");

		Object result = func.evaluate(args);
		assertEquals(expected.getOMA(), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIntegrateWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIntegrateWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIntegrateWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR(null));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testIntegrateIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("integrate('2*x','x')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		OMOBJ expected = ExpressionParser.parse("x^2", null, null);
		assertEquals(expected, result);
	}

	@Test
	public void testIntegrateSageSyntax() throws NoRepresentationAvailableException, JAXBException, FunctionException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("a"));
		assertEquals("var('a x');integral(((x^2 - 5 * a) + 6), a)", func.getPartialSageSyntax(args));
	}
}
