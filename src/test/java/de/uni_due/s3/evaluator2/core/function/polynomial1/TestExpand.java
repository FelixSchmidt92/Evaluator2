package de.uni_due.s3.evaluator2.core.function.polynomial1;

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

public class TestExpand extends TestFunctionAbstract {

	private Function func = new Expand();

	@Test
	public void testDegreeWrtWithOneVariable() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(ExpressionParser.parse("(2+x) * 2", null, null));
		Object result = func.evaluate(args);
		assertEquals(ExpressionParser.parse("2*x + 4", null, null).getOMA(), result);
	}

	@Test
	public void testDegreeWrtWithTwoVariable() throws OpenMathException, JAXBException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(1);
		args.add(ExpressionParser.parse("(2 + x) * (x - 2)", null, null));
		Object result = func.evaluate(args);
		assertEquals(OMConverter.toElement(OMConverter.toObject(
				"<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"x\"/><OMI>2</OMI></OMA><OMI>-4</OMI></OMA></OMOBJ>")),
				result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDegreeWrtWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDegreeWrtWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testDegreeWrtIntegration1() throws OpenMathException, JAXBException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("expand((2 + x) * (x - 2))", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMConverter.toElement(OMConverter.toObject(
				"<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"x\"/><OMI>2</OMI></OMA><OMI>-4</OMI></OMA></OMOBJ>")),
				result.getOMA());
	}

	@Test
	public void testDegreeWrtIntegration2() throws OpenMathException, JAXBException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("expand('(x - 2)*(2 + x)')", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMConverter.toElement(OMConverter.toObject(
				"<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA><OMS name=\"power\" cd=\"arith1\"/><OMV name=\"x\"/><OMI>2</OMI></OMA><OMI>-4</OMI></OMA></OMOBJ>")),
				result.getOMA());
	}

	@Test
	public void testDegreeWrtSageSyntax() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(ExpressionParser.parse("1+a^2-b", null, null));
		args.add(OMCreator.createOMV("a"));
		assertEquals("var('a b');f = (( (( ( (1) + ((a)^(2)) ) )) ) - ( b )); f.expand()", func.getPartialSageSyntax(args));
	}
}
