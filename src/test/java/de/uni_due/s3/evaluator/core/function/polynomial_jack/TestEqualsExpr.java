package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.polynomial_jack.EqualsExpr;
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

public class TestEqualsExpr extends TestFunctionAbstract{

	private Function func = new EqualsExpr();
	private List<Object> args;
	
	@Test
	public void testEqualsExprWithCorrectPoly() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException{
		args = new ArrayList<Object>(1);
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		OMOBJ arg2 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"times\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
				+ 	"<OMV name=\"x\"/>"
				+   "<OMI>2</OMI>"
				+ "</OMA>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
				+ 	"<OMV name=\"x\"/>"
				+   "<OMI>3</OMI>"
				+ "</OMA>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(arg2.getOMA());
		
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE,result);
	}
	
	@Test
	public void testEqualsExprWithDiffrentPoly() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException, JAXBException{
		args = new ArrayList<Object>(1);
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"a\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		OMOBJ arg2 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"times\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
				+ 	"<OMV name=\"x\"/>"
				+   "<OMI>2</OMI>"
				+ "</OMA>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
				+ 	"<OMV name=\"x\"/>"
				+   "<OMI>3</OMI>"
				+ "</OMA>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(arg2.getOMA());
		
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE,result);
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsExpreWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsExprWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualsExprWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR(null));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEqualsExprIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("equalsExpr('1+x^3','x*x*x+1')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	
	@Test
	public void testEqualsExprSageSyntax() throws FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException, NoRepresentationAvailableException, JAXBException{
		ArrayList<Object> args = new ArrayList<Object>(1);
		OMOBJ arg1 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
				+ 	"<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"x\"/><OMI>2</OMI></OMA>"
				+   "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"x\"/></OMA>"
				+ "</OMA>"
				+ "<OMI>6</OMI>"
				+ "</OMA></OMOBJ>");
		OMOBJ arg2 = OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"times\"/>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
				+ 	"<OMV name=\"x\"/>"
				+   "<OMI>2</OMI>"
				+ "</OMA>"
				+ "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
				+ 	"<OMV name=\"x\"/>"
				+   "<OMI>3</OMI>"
				+ "</OMA>"
				+ "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(arg2.getOMA());
		
		assertEquals("R.<x>=RR[]; f=((x^2 - 5 * x) + 6); g=(x - 2) * (x - 3); f==g", func.getPartialSageSyntax(args));
	}
}
