package de.uni_due.s3.evaluator2.core.function.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDependsOn extends TestFunctionAbstract {
	private static Function func = new DependsOn();

	@Test
	public void testDependsOnWithOneVariable() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"a\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("a"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testDependOnWithTwoVariables() throws OpenMathException, JAXBException, EvaluatorException {

		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"c\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("c"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testDependsOnWithIndependendVariable() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"a\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("c"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDependsOnWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDependsOnWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDependsOnWithWrongArguments() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(null);
		args.add(OMCreator.createOMI(10));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEqualsIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("dependsOn('1+3+a*c','a')", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testEqualsIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("dependsOn(a,'a')", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

}
