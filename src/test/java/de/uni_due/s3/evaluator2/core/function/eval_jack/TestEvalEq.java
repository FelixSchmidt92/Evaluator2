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

public class TestEvalEq extends TestFunctionAbstract {

	private Function func = new EvalEq();

	@Test
	public void testEvalEqWithCorrect() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		OMOBJ arg2 = OMConverter.toObject(
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"times\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
						+ "<OMV name=\"x\"/>" + "<OMI>2</OMI>" + "</OMA>" + "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
						+ "<OMV name=\"x\"/>" + "<OMI>3</OMI>" + "</OMA>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(arg2.getOMA());

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testEvalEqWithDiffrentPoly() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		OMOBJ arg2 = OMConverter.toObject("<OMOBJ><OMV name=\"a\"/></OMOBJ>");

		OMOBJ expectation = OMConverter
				.toObject("<OMOBJ>"
						+ "<OMA>"
							+ "<OMS name=\"plus\" cd=\"arith1\"/>"
							
							+ "<OMA>"
								+ "<OMS name=\"power\" cd=\"arith1\"/>"
								+ "<OMV name=\"x\"/>"
								+ "<OMI>2</OMI>"
							+ "</OMA>"
							
							+ "<OMA>"
								+ "<OMS name=\"times\" cd=\"arith1\"/>"
								+ "<OMI>-1</OMI>"
								+ "<OMV name=\"a\"/>"
							+ "</OMA>"
							
							+ "<OMA>"
								+ "<OMS name=\"times\" cd=\"arith1\"/>"
								+ "<OMI>-5</OMI>"
								+ "<OMV name=\"x\"/>"
							+ "</OMA>"
							
							+ "<OMI>6</OMI>"
						+ "</OMA>"
						+ "</OMOBJ>");

		args.add(arg1);
		args.add(arg2);

		Object result = func.evaluate(args);
		assertEquals(expectation.getOMA(), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalEqWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalEqWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEvalEqIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evalEq('1+x^3','x*x*x+1')", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(0), result.getOMI());
	}

	@Test
	public void testEvalEqSageSyntax() throws JAXBException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		OMOBJ arg2 = OMConverter.toObject(
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"times\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
						+ "<OMV name=\"x\"/>" + "<OMI>2</OMI>" + "</OMA>" + "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
						+ "<OMV name=\"x\"/>" + "<OMI>3</OMI>" + "</OMA>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(arg2.getOMA());
		assertEquals(
				"var('x');expand(((( ( ((( (x)^(2) ) - ( (( ( (5) * (x) ) )) ))) + (6) ) ))) - ((( ( ((( x ) - ( 2 ))) * ((( x ) - ( 3 ))) ) ))))",
				func.getPartialSageSyntax(args));
	}
}
