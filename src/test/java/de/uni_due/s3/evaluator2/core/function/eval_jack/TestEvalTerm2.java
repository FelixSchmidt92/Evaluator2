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

public class TestEvalTerm2 extends TestFunctionAbstract {

	private Function func = new EvalTerm2();

	@Test
	public void testEvalTerm2WithTwoVariables1() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		// x^2-5x+6
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");

		args.add(arg1.getOMA());
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testEvalTerm2WithTwoVariables2() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		// x^2-5a+6
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMI(5));
		args.add(OMCreator.createOMI(2));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(6), result);
	}

	@Test
	public void testEvalTerm2WithTwoVariables3() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		// x^2-5a+6
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMV name=\"b\"/>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(-12));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(-18), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalTerm2WithLessThanMinParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalTerm2WithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
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
		OMOBJ omobj = ExpressionParser.parse("evalterm2('x+y','1','2')", null, null);
		OMOBJ result = OMToResultVisitor.getInstance().execute(omobj);
		assertEquals(OMCreator.createOMI(3), result.getOMI());
	}

	@Test
	public void testEvalTerm2Integration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evalterm2('x+y','1', -3)", null, null);
		OMOBJ result = OMToResultVisitor.getInstance().execute(omobj);
		assertEquals(OMCreator.createOMI(-2), result.getOMI());
	}

	@Test
	public void testEvalTermSageSyntax() throws JAXBException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		assertEquals("var('a b x y');x = a =1;y = b =2;((( ( ((( (x)^(2) ) - ( (( ( (5) * (a) ) )) ))) + (6) ) )))", func.getPartialSageSyntax(args));
	}
}
