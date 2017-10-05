package de.uni_due.s3.evaluator2.core.function.linalg2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestVector extends TestFunctionAbstract {
	private static ConstructorFunction func = new Vector();

	@Test
	public void testVectorInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), result);
	}

	@Test
	public void testVectorFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), result);
	}

	@Test
	public void testVectorIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("vector(1,2,3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), result.getOMA());
	}

	@Test
	public void testVectorEvaluation() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = Evaluator.evaluate("vector(1,2,3)", null, null);
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), omobj.getOMA());
	}

	@Test
	public void testVectorSageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(1));
		assertEquals("vector([2,3,1])", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testVectorWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("vector()", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
	@Test
	public void testOMToLatexVisitorWithVector() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("vector(1,2,3,4)", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("\\left(\\begin{array}{r}" + 
				"1\\\\" + 
				"2\\\\" + 
				"3\\\\" + 
				"4\\\\" +
				
				
				"\\end{array}\\right)", latex);
	}
	
	@Test
	public void testOMToLatexVisitorWithVector2() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("vector(123,2.023,[var=PI])", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("\\left(\\begin{array}{r}" + 
				"123\\\\" + 
				"2.023\\\\" + 
				"\\pi\\\\" + 
				"\\end{array}\\right)", latex);
	}
}
