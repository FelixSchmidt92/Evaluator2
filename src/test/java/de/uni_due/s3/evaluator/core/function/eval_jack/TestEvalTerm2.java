package de.uni_due.s3.evaluator.core.function.eval_jack;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.eval_jack.EvalTerm2;
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

public class TestEvalTerm2 extends TestFunctionAbstract{

	private Function func = new EvalTerm2();
	private List<Object> args;
	
	@Test(expected=CasEvaluationException.class)
	public void testEvalTerm2WithOneVariable() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException{
		args = new ArrayList<Object>(1);
		//x^2-5x+6
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("1"));
		args.add(OMCreator.createOMSTR("2"));
		Object result = func.evaluate(args);
		fail();
	}
	
	@Test
	public void testEvalTerm2WithTwoVariables() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException{
		args = new ArrayList<Object>(1);
		//x^2-5a+6
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("1"));
		args.add(OMCreator.createOMSTR("2"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(-3),result);
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalTerm2WithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalTerm2WithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEvalTerm2WithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(null,new LinkedList<Object>()));
		args.add(OMCreator.createOMV("test"));
		args.add(OMCreator.createOMV("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEvalTerm2Integration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("evalterm2('x+y','1','2')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(3), result.getOMI());
	}

	
	@Test
	public void testEvalTermSageSyntax() throws FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException, NoRepresentationAvailableException, JAXBException{
		ArrayList<Object> args = new ArrayList<Object>(1);
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("1"));
		args.add(OMCreator.createOMSTR("2"));
		assertEquals("R.<x,a>=RR[]; f=((x^2-5*a)+6); f(1,2)", func.getPartialSageSyntax(args));
	}
}