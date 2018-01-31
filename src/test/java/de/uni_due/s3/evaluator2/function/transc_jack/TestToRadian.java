package de.uni_due.s3.evaluator2.function.transc_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.transc_jack.ToRadian;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestToRadian extends TestFunctionAbstract {

	private static Function func = new ToRadian();

	@Test
	public void testToRadianPI() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(180));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(Math.PI), result);
	}

	@Test
	public void testToRadianMinusPI() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(-180));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(-Math.PI), result);
	}

	@Test
	public void testToRadianFloat() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testToRadianIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toRadian(180)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMF(Math.PI), result.getOMF());
	}

	@Test
	public void testToRadianIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toRadian(720)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMF(Math.PI * 4), result.getOMF());
	}
	
	@Test
	public void testToRadianLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toRadian(120)", null, null);
		String latex = Evaluator.getLaTeX(omobj);
		assertEquals("\\mbox{toRadian}\\left(120\\degree\\right)",latex);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToRadianWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toRadian()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToRadianWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toRadian(1,3)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testToRadianWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toRadian('Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}
