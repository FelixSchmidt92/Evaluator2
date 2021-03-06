package de.uni_due.s3.evaluator2.function.logic_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.logic_jack.IfThenElse;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIfThenElse extends TestFunctionAbstract {

	private final Function func = new IfThenElse();

	private List<Object> args;
	private Object result;

	@Test
	public void testIfThenElseConditionOMF() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(3.0));
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("true"), result);

		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(-1.0));
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("false"), result);
	}

	@Test
	public void testIfThenElseConditionOMI() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("true"), result);

		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMI(-2));
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("false"), result);
	}

	@Test
	public void testIfThenElseConditionBoolean() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("true"), result);

		args = new ArrayList<Object>(3);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("false"), result);
	}

	@Test
	public void testIfThenElseAnyReturnType() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>();
		OMS obj =  OMSymbol.NUMS1_E;
		OMS obj2 =  OMSymbol.NUMS1_PI;
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(obj);
		args.add(obj2);
		result = func.evaluate(args);
		assertEquals(obj, result);

	}

	@Test
	public void testIfThenEleseIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("ifthenelse('5*3*9>=10','true','false')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals("true", result.getOMSTR().getContent());

		omobj = ExpressionParser.parse("ifthenelse('5*3*10==10','true','false')", null, null);
		result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals("false", result.getOMSTR().getContent());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIfThenEleseWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIfThenEleseWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIfThenEleseWithWrongArguments() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMV("a"));
		args.add(OMCreator.createOMSTR(null));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testIfThenElseSageSyntax() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMSTR("false"));
		Sage.evaluateInCAS(func.getPartialSageSyntax(args));
		fail();
	}
	
	public void testIfThenElsePartialEvaluation1() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMCreator.createOMI(3));
		args.add(null);
		assertEquals(func.evaluate(args), OMCreator.createOMI(3));
	}
	
	public void testIfThenElsePartialEvaluation2() throws EvaluatorException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(null);
		args.add(OMCreator.createOMSTR("false"));
		assertEquals(OMCreator.createOMSTR("false"), func.evaluate(args));
	}

}
