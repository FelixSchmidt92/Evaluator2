package de.uni_due.s3.evaluator.core.function.eval_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.eval_jack.EvalEq;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
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

public class TestEvalEq extends TestFunctionAbstract {

	private Function func = new EvalEq();
	private List<Object> args;

	@Test
	public void testEvalEqWithCorrectPoly()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException {
		args = new ArrayList<Object>(1);
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
	public void testEvalEqWithDiffrentPoly()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException {
		args = new ArrayList<Object>(1);
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		OMOBJ arg2 = OMConverter.toObject("<OMOBJ><OMV name=\"a\"/></OMOBJ>");

		OMOBJ expectation = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"minus\"/>"
				+ "<OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>" + "</OMA>"
				+ "<OMI>6</OMI>" + "</OMA>" + "<OMV name=\"a\"/></OMA></OMOBJ>");

		args.add(arg1);
		args.add(arg2);

		Object result = func.evaluate(args);
		assertEquals(expectation.getOMA(), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalEqWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalEqWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEvalEqWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR(null));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEvalEqIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("evalEq('1+x^3','x*x*x+1')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(0), result.getOMSTR());
	}

	@Test
	public void testEvalEqSageSyntax() throws FunctionException, NoRepresentationAvailableException, CasException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, JAXBException {
		ArrayList<Object> args = new ArrayList<Object>(1);
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
		assertEquals("R.<x>=RR[]; f=((x^2 - 5 * x) + 6); g=(x - 2) * (x - 3); f-g", func.getPartialSageSyntax(args));
	}
}
