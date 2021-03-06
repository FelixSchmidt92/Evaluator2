package de.uni_due.s3.evaluator2.function.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.polynomial_jack.EqualsExpr;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqualsExpr extends TestFunctionAbstract {

	private Function func = new EqualsExpr();
	private List<Object> args;

	@Test
	public void testEqualsExprWithCorrectPoly() throws OpenMathException, JAXBException, EvaluatorException {
		args = new ArrayList<Object>();
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
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualsExprWithDiffrentPoly() throws OpenMathException, JAXBException, EvaluatorException {
		args = new ArrayList<Object>();
		OMOBJ arg1 = OMConverter
				.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\"/>"
						+ "<OMA><OMS cd=\"arith1\" name=\"power\" /><OMV name=\"a\"/><OMI>2</OMI></OMA>"
						+ "<OMA><OMS cd=\"arith1\" name=\"times\" /><OMI>5</OMI><OMV name=\"a\"/></OMA>" + "</OMA>"
						+ "<OMI>6</OMI>" + "</OMA></OMOBJ>");
		OMOBJ arg2 = OMConverter.toObject(
				"<OMOBJ><OMA><OMS cd=\"arith1\" name=\"times\"/>" + "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
						+ "<OMV name=\"x\"/>" + "<OMI>2</OMI>" + "</OMA>" + "<OMA><OMS cd =\"arith1\" name=\"minus\" />"
						+ "<OMV name=\"x\"/>" + "<OMI>3</OMI>" + "</OMA>" + "</OMA></OMOBJ>");
		args.add(arg1.getOMA());
		args.add(arg2.getOMA());

		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsExpreWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsExprWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEqualsExprIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("equalsExpr('1+x^3','x*x*x+1')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualsExprSageSyntax() throws EvaluatorException, OpenMathException, JAXBException {
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

		assertEquals("bool((( ((( ((x)^(2)) ) - ( ( ( (5) * (x) ) ) ))) + (6) ))==( ( ((( x ) - ( 2 ))) * ((( x ) - ( 3 ))) ) ))", func.getPartialSageSyntax(args));
	}
	
	
	@Test(expected = FunctionException.class)
	public void testEqualsExprIntegrationWorngInput() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("equalsExpr('1+x^3','Something classified as NONSENSE')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
	}
	
}
