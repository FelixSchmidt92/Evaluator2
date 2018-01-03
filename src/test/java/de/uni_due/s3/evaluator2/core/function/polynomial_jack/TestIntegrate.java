package de.uni_due.s3.evaluator2.core.function.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIntegrate extends TestFunctionAbstract {

	private Function func = new Integrate();

	@Test
	public void testIntegrateWithOneVariable()
			throws OpenMathException, JAXBException, EvaluatorException {
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
			throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ term = OMConverter.toObject(
				"<OMOBJ><OMA>" + "<OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA>" + "<OMS cd=\"arith1\" name=\"times\"/>"
						+ "<OMI>2</OMI>" + "<OMV name=\"x\"/>" + "</OMA>" + "<OMV name=\"v\"/>" + "</OMA></OMOBJ>");
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
			throws OpenMathException, JAXBException, EvaluatorException {
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
	
	@Test
	public void testIntegrateWithFunctions() throws JAXBException, EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ>" + "<OMA><OMS cd =\"transc1\" name=\"cos\"/>"
		+"<OMV name=\"x\" />"
		+ "</OMA>"
		+ "</OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("x"));
		
		OMOBJ expected = OMConverter.toObject("<OMOBJ>" + "<OMA><OMS cd =\"transc1\" name=\"sin\"/>"
				+"<OMV name=\"x\" />"
				+ "</OMA>"
				+ "</OMOBJ>");
		
		Object result = func.evaluate(args);
		assertEquals(expected.getOMA(), result);
	}
	
	@Test
	public void testIntegrateWithSquare() throws JAXBException, EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ>" + "<OMA><OMS cd =\"arith1\" name=\"times\"/>"
		+"<OMI>2</OMI><OMV name=\"x\" />"
		+ "</OMA>"
		+ "</OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("x"));
		
		OMOBJ expected = OMConverter.toObject("<OMOBJ>" + "<OMA><OMS cd =\"arith1\" name=\"power\"/>"
				+"<OMV name=\"x\" /><OMI>2</OMI>"
				+ "</OMA>"
				+ "</OMOBJ>");
		
		Object result = func.evaluate(args);
		assertEquals(expected.getOMA(), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIntegrateWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIntegrateWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIntegrateWithWrongArguments() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("''"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testIntegrateIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("integrate('2*x','x')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		OMOBJ expected = ExpressionParser.parse("x^2", null, null);
		assertEquals(expected, result);
	}

	@Test
	public void testIntegrateSageSyntax() throws EvaluatorException, OpenMathException, JAXBException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("a"));
		assertEquals("integral((( ( ((( (x)^(2) ) - ( (( ( (5) * (a) ) )) ))) + (6) ) )), a)", func.getPartialSageSyntax(args));
	}
}
