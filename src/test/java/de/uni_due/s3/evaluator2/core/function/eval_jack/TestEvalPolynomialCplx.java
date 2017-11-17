package de.uni_due.s3.evaluator2.core.function.eval_jack;

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
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvalPolynomialCplx extends TestFunctionAbstract {

	private Function func = new EvalPolynomialCplx();

	@Test
	public void testEvalPolynomialCplxWithTwoVariables1() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		// x^2-5x+6
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");

		args.add(arg1.getOMA());
		args.add(OMCreator.createOMV("x"));
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testEvalPolynomialWithTwoVariables2() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		// x^2-5a+6
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMConverter.toElement(OMConverter.toObject(
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA></OMOBJ>")));
		args.add(OMCreator.createOMI(5));
		Object result = func.evaluate(args);
		assertEquals(OMConverter.toElement(OMConverter.toObject("<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA>"
				+ "<OMS name=\"times\" cd=\"arith1\"/><OMI>-5</OMI><OMV name=\"a\"/></OMA>"
				+ "<OMI>11</OMI></OMA></OMOBJ>")), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalPolynomialCplxWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalPolynomialWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEvalTerm2Integration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evalPolynomialCplx('x+x', 'x', '1')", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testEvalPolynomialIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evalPolynomialCplx('x+2*x', 3*x, -4)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(-4), result);
	}

	@Test
	public void testEvalPolynomialSageSyntax() throws JAXBException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMI(-5));
		args.add(OMCreator.createOMI(1));
		assertEquals("((( ( ( ((x)^(2)) + ((( ( (1) * (a) ) ))) ) + (6) ) )))", func.getPartialSageSyntax(args));
	}
}
