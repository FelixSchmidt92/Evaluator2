package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Ignore;
import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.polynomial_jack.DependsOn;
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

public class TestDependsOn extends TestFunctionAbstract{
	private static Function func = new DependsOn();

	@Test
	public void testDependsOnWithOneVariable() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"a\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("a"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	
	@Test
	public void testDependOnWithTwoVariables() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException {

		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"c\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("c"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testDependsOnWithIndependendVariable() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException {
		List<Object> args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"a\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(OMCreator.createOMSTR("c"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}


	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDependsOnWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDependsOnWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDependsOnWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(null);
		args.add(OMCreator.createOMI(10));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEqualsIntegration1() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("dependsOn('1+3+a*c','a')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

}
