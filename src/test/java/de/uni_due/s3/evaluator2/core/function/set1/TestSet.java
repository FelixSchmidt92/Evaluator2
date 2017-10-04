package de.uni_due.s3.evaluator2.core.function.set1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToRVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSet extends TestFunctionAbstract {

	private ConstructorFunction func = new Set();
	private List<Object> args;
	private Object result;

	@Test
	public void testSetWithOMI() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(20));
		args.add(OMCreator.createOMI(30));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LIST1_LIST, args), result);
	}

	@Test
	public void testSetWithOMF() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(10.0));
		args.add(OMCreator.createOMF(20.3));
		args.add(OMCreator.createOMF(30.12));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LIST1_LIST, args), result);
	}

	@Test
	public void testSetWithOMA() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(10.0));
		args.add(OMCreator.createOMF(20.3));
		args.add(OMCreator.createOMF(30.12));

		ArrayList<Object> arguments = new ArrayList<Object>();
		arguments.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, args));
		arguments.add(OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, args));
		result = func.evaluate(arguments);
		assertEquals(true, result instanceof OMA);
		assertEquals(OMCreator.createOMA(OMSymbol.LIST1_LIST, arguments), result);
	}

	@Test
	public void testSetIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("{1;2;3}", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> value = result.getOMA().getOmel();
		assertEquals(OMCreator.createOMI(1), value.get(1));
		assertEquals(OMCreator.createOMI(2), value.get(2));
		assertEquals(OMCreator.createOMI(3), value.get(3));
	}

	@Test
	public void testSetIntegrationWithZero() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("{}", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMA(OMSymbol.LIST1_LIST, new ArrayList<Object>()), result.getOMA());
	}

	@Test
	public void testSetIntegrationAsFunction() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("list(1,2,3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> value = result.getOMA().getOmel();
		assertEquals(OMCreator.createOMI(1), value.get(1));
		assertEquals(OMCreator.createOMI(2), value.get(2));
		assertEquals(OMCreator.createOMI(3), value.get(3));
	}
	
	@Test
	public void testSetIntegrationInBrace() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("{1;2;3}", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> value = result.getOMA().getOmel();
		assertEquals(OMCreator.createOMI(1), value.get(1));
		assertEquals(OMCreator.createOMI(2), value.get(2));
		assertEquals(OMCreator.createOMI(3), value.get(3));
	}
	
	@Test
	public void testSetLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("{1;2;3}", null,null);
		String latex = Evaluator.getLaTeX(result);
		assertEquals("\\left\\{1,2,3\\right\\}",latex);
		
	}
	
	@Test
	public void testSetRSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("list(1,2,3)", null,null);
		String r = new OMToRVisitor().visit(result);
		assertEquals("list(1, 2, 3)",r);
		
	}
}
