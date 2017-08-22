package de.uni_due.s3.evaluator2.core.function.list1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestList {

	private Function func = new de.uni_due.s3.evaluator2.core.function.list1.List();
	private List<Object> args;
	private Object result;

	@Test
	public void testListWithOMI() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(20));
		args.add(OMCreator.createOMI(30));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LIST1_LIST, args), result);
	}

	@Test
	public void testListWithOMF() throws OpenMathException, EvaluatorException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(10.0));
		args.add(OMCreator.createOMF(20.3));
		args.add(OMCreator.createOMF(30.12));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LIST1_LIST, args), result);
	}

	@Test
	public void testListWithOMA() throws OpenMathException, EvaluatorException {
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
	public void testListIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("list(1,2,3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> value = result.getOMA().getOmel();
		assertEquals(OMCreator.createOMI(1), value.get(1));
		assertEquals(OMCreator.createOMI(2), value.get(2));
		assertEquals(OMCreator.createOMI(3), value.get(3));
	}
	
	@Test
	public void testListLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("list(1,2,3)", null,null);
		String latex = Evaluator.getLaTeX(result);
		assertEquals("\\left\\{1,2,3\\right\\}",latex);
		
	}
}
