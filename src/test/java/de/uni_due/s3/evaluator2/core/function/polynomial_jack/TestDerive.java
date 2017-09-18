package de.uni_due.s3.evaluator2.core.function.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDerive extends TestFunctionAbstract {

	private Function func = new Derive();

	@Test
	public void testDeriveWithOneVariable() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ term = OMConverter.toObject("<OMOBJ><OMA>" + "<OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA>"
				+ "<OMS cd =\"arith1\" name=\"minus\"/>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"power\" />"
				+ "<OMV name=\"x\"/>" + "<OMI>2</OMI>" + "</OMA>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"times\" />"
				+ "<OMI>5</OMI>" + "<OMV name=\"x\"/>" + "</OMA>" + "</OMA>" + "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(term.getOMA());
		args.add(OMCreator.createOMV("x"));

		OMOBJ expected = OMConverter.toObject(
				"<OMOBJ><OMA>" + "<OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"times\"/>"
						+ "<OMI>2</OMI>" + "<OMV name=\"x\"/>" + "</OMA>" + "<OMI>-5</OMI>" + "</OMA></OMOBJ>");
		Object result = func.evaluate(args);
		assertEquals(expected.getOMA(), result);
	}

	@Test
	public void testDeriveWithTwoVariable() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ term = OMConverter.toObject("<OMOBJ><OMA>" + "<OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA>"
				+ "<OMS cd =\"arith1\" name=\"minus\"/>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"power\" />"
				+ "<OMV name=\"x\"/>" + "<OMI>2</OMI>" + "</OMA>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"times\" />"
				+ "<OMI>5</OMI>" + "<OMV name=\"x\"/>" + "</OMA>" + "</OMA>" + "<OMV name=\"v\"/>" + "</OMA></OMOBJ>");
		args.add(term.getOMA());
		args.add(OMCreator.createOMV("x"));

		OMOBJ expected = OMConverter.toObject(
				"<OMOBJ><OMA>" + "<OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"times\"/>"
						+ "<OMI>2</OMI>" + "<OMV name=\"x\"/>" + "</OMA>" + "<OMI>-5</OMI>" + "</OMA></OMOBJ>");
		Object result = func.evaluate(args);
		assertEquals(expected.getOMA(), result);
	}

	@Test
	public void testDeriveWithTwoVariable2() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"b\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("b"));

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(-5), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDeriveWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDeriveWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDeriveWithWrongArguments() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR(""));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testDeriveIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("derive('1+x^3','x')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		OMOBJ expected = ExpressionParser.parse("3*x^2", null, null);
		assertEquals(expected, result);
	}

	@Test
	public void testDeriveSageSyntax() throws JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("a"));
		assertEquals("var('a x');derivative((((x)^(2) - 5 * a) + 6), a)", func.getPartialSageSyntax(args));
	}
}
