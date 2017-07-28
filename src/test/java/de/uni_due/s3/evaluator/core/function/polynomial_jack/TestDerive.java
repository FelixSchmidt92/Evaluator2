package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import static org.junit.Assert.*;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.polynomial_jack.Derive;
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

public class TestDerive extends TestFunctionAbstract{

	private Function func = new Derive();
	private List<Object> args;
	
	@Test
	public void testDeriveWithOneVariable() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException{
		args = new ArrayList<Object>(1);
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("x"));
		
		OMOBJ expected = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"minus\"/>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>2</OMI><OMV name=\"x\"/></OMA>"
				+	"<OMI>-5</OMI>"
				+ "</OMA></OMOBJ>");
		Object result = func.evaluate(args);
		assertEquals(expected.getOMA(),result);
	}
	
	@Test
	public void testDDeriveWithTwoVariable() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException{
		args = new ArrayList<Object>(1);
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"b\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("b"));
	
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(-5),result);
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDeriveWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDeriveWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDeriveWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR(null));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testDeriveIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("derive('1+x^3','x')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMSTR("3*x^2"), result.getOMSTR());
	}

	
	@Test
	public void testDeriveSageSyntax() throws FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException, NoRepresentationAvailableException, JAXBException{
		ArrayList<Object> args = new ArrayList<Object>(1);
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("a"));
		assertEquals("R.<x,a>=RR[]; f=((x^2 - 5 * a) + 6); f.derivative(a)", func.getPartialSageSyntax(args));
	}
}
